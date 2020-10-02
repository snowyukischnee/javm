package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype.VerificationTypeInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import lombok.Getter;

import java.io.DataInputStream;
import java.io.IOException;

import static com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype.VerificationTypeInfoParser.parseVerificationTypeInfo;
import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

@Getter
public class StackMapFrameSameLocals1StackItem extends StackMapFrame {
    private VerificationTypeInfo[] stack;

    public StackMapFrameSameLocals1StackItem(int frameType, DataInputStream stream, ConstantPoolInfo[] constantPool) throws IOException {
        super(frameType, StackMapFrameTag.SAME_LOCALS_1_STACK_ITEM_FRAME);
        stack = parseVerificationTypeInfo(stream, 1, constantPool);
    }

    @Override
    public String debugPrint(int padding) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("frame_type = %d\t/* %s */%n", getFrameType(), getTag()));
        // ----------------------------------------
        builder.append(String.format("%sstack:%n", PADDING[padding]));
        builder.append(String.format("%s\t%s%n", PADDING[padding], stack[0].debugPrint(padding + 1)));
        return builder.toString();
    }
}
