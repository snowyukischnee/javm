package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class StackMapFrameParser {
    private StackMapFrameParser() {}

    @SuppressWarnings("java:S112")
    public static StackMapFrame[] parseStackMapFrame(DataInputStream stream, int numberOfEntries, ConstantPoolInfo[] constantPool) throws IOException {
        StackMapFrame[] frames = new StackMapFrame[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            int frameType = stream.readUnsignedByte();
            StackMapFrameTag tag = StackMapFrameTag.fromInt(frameType);
            switch (tag) {
                case SAME_FRAME:
                    frames[i] = new StackMapFrameSame(frameType);
                    break;
                case SAME_LOCALS_1_STACK_ITEM_FRAME:
                    frames[i] = new StackMapFrameSameLocals1StackItem(frameType, stream, constantPool);
                    break;
                case SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED:
                    frames[i] = new StackMapFrameSameLocals1StackItemExtended(frameType, stream, constantPool);
                    break;
                case CHOP_FRAME:
                    frames[i] = new StackMapFrameChop(frameType, ByteUtils.readBytes(stream, 2));
                    break;
                case SAME_FRAME_EXTENDED:
                    frames[i] = new StackMapFrameSameExtended(frameType, stream);
                    break;
                case APPEND_FRAME:
                    frames[i] = new StackMapFrameAppend(frameType, stream, frameType - 251, constantPool);
                    break;
                case FULL_FRAME:
                    frames[i] = new StackMapFrameFull(frameType, stream, constantPool);
                    break;
                default:
                    throw new RuntimeException("Unrecognized StackMapFrame");
            }
        }
        return frames;
    }

    public static StackMapFrame[] parseStackMapFrame(byte[] bytes, int numberOfEntries, ConstantPoolInfo[] constantPool) throws IOException {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(bytes));
        return parseStackMapFrame(stream, numberOfEntries, constantPool);
    }
}
