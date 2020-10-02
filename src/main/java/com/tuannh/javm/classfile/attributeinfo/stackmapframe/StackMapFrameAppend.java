package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype.VerificationTypeInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import lombok.Getter;

import java.io.DataInputStream;
import java.io.IOException;

import static com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype.VerificationTypeInfoParser.parseVerificationTypeInfo;
import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

@Getter
public class StackMapFrameAppend extends StackMapFrame {
    private int offsetDelta;
    private int numberOfLocals;
    private VerificationTypeInfo[] locals;

    public StackMapFrameAppend(int frameType, DataInputStream stream, int localsLength, ConstantPoolInfo[] constantPool) throws IOException {
        super(frameType, StackMapFrameTag.APPEND_FRAME);
        offsetDelta = stream.readUnsignedShort();
        numberOfLocals = localsLength;
        locals = parseVerificationTypeInfo(stream, numberOfLocals, constantPool);
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
        return builder.toString();
    }
}
