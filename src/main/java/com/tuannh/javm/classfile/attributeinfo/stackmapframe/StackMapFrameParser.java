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
            StackMapFrameTag tag = StackMapFrameTag.fromInt(stream.readByte());
            // TODO
            switch (tag) {
                case SAME_FRAME:
                    frames[i] = new StackMapFrameSame();
                    break;
                case SAME_LOCALS_1_STACK_ITEM_FRAME:
                    frames[i] = new StackMapFrameSameLocals1StackItem(ByteUtils.readBytes(stream, 1), constantPool);
                    break;
                case SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED:
                    frames[i] = new StackMapFrameSameLocals1StackItem(ByteUtils.readBytes(stream, 3), constantPool);
                    break;
                case CHOP_FRAME:
                    frames[i] = new StackMapFrameSameLocals1StackItem(ByteUtils.readBytes(stream, 2), constantPool);
                    break;
                case SAME_FRAME_EXTENDED:
                    break;
                case APPEND_FRAME:
                    break;
                case FULL_FRAME:
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
