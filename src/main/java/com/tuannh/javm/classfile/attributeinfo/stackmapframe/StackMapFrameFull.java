package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype.VerificationTypeInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import lombok.Getter;

import java.io.DataInputStream;
import java.io.IOException;

import static com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype.VerificationTypeInfoParser.parseVerificationTypeInfo;
import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

@Getter
public class StackMapFrameFull extends StackMapFrame {
    private int offsetDelta;
    private int numberOfLocals;
    private VerificationTypeInfo[] locals;
    private int numberOfStackItems;
    private VerificationTypeInfo[] stack;

    public StackMapFrameFull(int frameType, DataInputStream stream, ConstantPoolInfo[] constantPool) throws IOException {
        super(frameType, StackMapFrameTag.FULL_FRAME);
        offsetDelta = stream.readUnsignedShort();
        numberOfLocals = stream.readUnsignedShort();
        locals = parseVerificationTypeInfo(stream, numberOfLocals, constantPool);
        numberOfStackItems = stream.readUnsignedShort();
        stack = parseVerificationTypeInfo(stream, numberOfStackItems, constantPool);
    }

    @Override
    public String debugPrint(int padding) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("frame_type = %d\t/* %s */%n", getFrameType(), getTag()));
        // ----------------------------------------
        builder.append(String.format("%slocals(entries=%d):%n", PADDING[padding], numberOfLocals));
        for (int i = 0; i < numberOfLocals; i++) {
            builder.append(String.format("%s\t%s%n", PADDING[padding], locals[i].debugPrint(padding + 1)));

        }
        // ----------------------------------------
        builder.append(String.format("%sstack(entries=%d):%n", PADDING[padding], numberOfStackItems));
        for (int i = 0; i < numberOfStackItems; i++) {
            builder.append(String.format("%s\t%s%n", PADDING[padding], stack[i].debugPrint(padding + 1)));

        }
        return builder.toString();
    }
}
