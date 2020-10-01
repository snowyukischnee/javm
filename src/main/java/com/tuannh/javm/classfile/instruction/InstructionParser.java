package com.tuannh.javm.classfile.instruction;

import com.tuannh.javm.util.ByteUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class InstructionParser {
    private InstructionParser() {}

    @SuppressWarnings({"java:S112", "java:S3776"})
    public static Instruction[] parseInstruction(DataInputStream stream, int codeLength) throws IOException {
        Instruction[] instructions = new Instruction[codeLength];
        int readBytes = 0;
        int idx = 0;
        while (idx < codeLength) {
            Opcode op = Opcode.getOpcode(stream.readByte());
            readBytes++;
            int argc = op.getArgc();
            byte[] bytes = null;
            if (argc == -1) { // variable-length instruction
                if (op == Opcode.TABLESWITCH) {
                    // padding bytes
                    int padSize = (readBytes % 4 == 0) ? 0 : (4 - (readBytes % 4));
                    byte[] pad = ByteUtils.readBytes(stream, padSize);
                    readBytes += padSize;
                    // default bytes
                    byte[] defaultBytes = ByteUtils.readBytes(stream, 4);
                    readBytes += 4;
                    // low bytes
                    byte[] lowBytes = ByteUtils.readBytes(stream, 4);
                    readBytes += 4;
                    // high bytes
                    byte[] highBytes = ByteUtils.readBytes(stream, 4);
                    readBytes += 4;
                    // jump offsets
                    int jumpOffsetLen = 4 * (ByteBuffer.wrap(highBytes).getInt() - ByteBuffer.wrap(lowBytes).getInt() + 1);
                    byte[] jumpOffsets = ByteUtils.readBytes(stream, jumpOffsetLen);
                    readBytes += jumpOffsetLen;
                    // construct bytes array
                    bytes = ByteUtils.concat(pad, defaultBytes, lowBytes, highBytes, jumpOffsets);
                } else if (op == Opcode.LOOKUPSWITCH) {
                    // padding bytes
                    int padSize = (readBytes % 4 == 0) ? 0 : (4 - (readBytes % 4));
                    byte[] pad = ByteUtils.readBytes(stream, padSize);
                    readBytes += padSize;
                    // default bytes
                    byte[] defaultBytes = ByteUtils.readBytes(stream, 4);
                    readBytes += 4;
                    // npairs
                    byte[] nPairs = ByteUtils.readBytes(stream, 4);
                    readBytes += 4;
                    // npairs
                    // Each of the npairs pairs consists of an int match and a signed 32-bit offset
                    int pairsSize = 8 * ByteBuffer.wrap(nPairs).getInt();
                    byte[] pairs = ByteUtils.readBytes(stream, pairsSize);
                    readBytes += pairsSize;
                    // construct bytes array
                    bytes = ByteUtils.concat(pad, defaultBytes, nPairs, pairs);
                } else if (op == Opcode.WIDE) {
                    // opcode
                    byte[] opcodeB = ByteUtils.readBytes(stream, 1);
                    readBytes += 1;
                    Opcode opcode = Opcode.getOpcode(opcodeB[0]);
                    byte[] params = null;
                    if (opcode == Opcode.IINC) {
                        params = ByteUtils.readBytes(stream, 4); // index, const
                        readBytes += 4;
                    } else {
                        params = ByteUtils.readBytes(stream, 2); // index
                        readBytes += 2;
                    }
                    // construct bytes array
                    bytes = ByteUtils.concat(opcodeB, params);
                } else {
                    throw new RuntimeException(String.format("Unrecognized variable-length instruction: %s", op));
                }
            } else if (argc == -2) { // reserved instruction
                throw new RuntimeException(String.format("Instruction not yet supported: %s", op));
            } else {
                bytes = ByteUtils.readBytes(stream, argc);
                readBytes += argc;
            }
            instructions[idx] = new Instruction(op, bytes);
            idx = readBytes;
        }
        return instructions;
    }

    public static Instruction[] parseInstruction(byte[] code, int codeLength) throws IOException {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(code));
        return parseInstruction(stream, codeLength);
    }
}
