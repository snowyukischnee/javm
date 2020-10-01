package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype.VerificationTypeInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;

import java.io.IOException;

import static com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype.VerificationTypeInfoParser.parseVerificationTypeInfo;

@Getter
public class StackMapFrameSameLocals1StackItemExtended extends StackMapFrame {
    private VerificationTypeInfo[] stack;
    private int offsetDelta;

    public StackMapFrameSameLocals1StackItemExtended(byte[] bytes, ConstantPoolInfo[] constantPool) throws IOException {
        super(StackMapFrameTag.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED);
        offsetDelta = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
        stack = parseVerificationTypeInfo(ByteUtils.slice(bytes, 2, 3), 1, constantPool);
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("SameLocals1StackItemExtended: offset_delta=%d, %s", offsetDelta, stack[0].debugPrint(padding + 1));
    }
}
