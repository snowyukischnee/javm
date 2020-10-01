package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype.VerificationTypeInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import lombok.Getter;

import java.io.IOException;

import static com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype.VerificationTypeInfoParser.parseVerificationTypeInfo;

@Getter
public class StackMapFrameSameLocals1StackItem extends StackMapFrame {
    private VerificationTypeInfo[] stack;

    public StackMapFrameSameLocals1StackItem(byte[] bytes, ConstantPoolInfo[] constantPool) throws IOException {
        super(StackMapFrameTag.SAME_LOCALS_1_STACK_ITEM_FRAME);
        stack = parseVerificationTypeInfo(bytes, 1, constantPool);
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("SameLocals1StackItem: %s", stack[0].debugPrint(padding + 1));
    }
}
