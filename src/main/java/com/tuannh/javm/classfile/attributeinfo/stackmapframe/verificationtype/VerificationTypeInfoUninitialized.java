package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

import com.tuannh.javm.util.ByteBufferUtils;
import lombok.Getter;

@Getter
public class VerificationTypeInfoUninitialized extends VerificationTypeInfo {
    private int offset;

    public VerificationTypeInfoUninitialized(byte[] bytes) {
        super(VerificationTypeInfoTag.UNINITIALIZED_VARIABLE_INFO);
        offset = ByteBufferUtils.getUnsignedShort(bytes);
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("Uninitialized_variable_info: offset=%d", offset);
    }
}
