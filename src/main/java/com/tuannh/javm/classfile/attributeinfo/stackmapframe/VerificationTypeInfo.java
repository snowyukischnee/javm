package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VerificationTypeInfo {
    TOP_VARIABLE_INFO((byte)0),
    INTEGER_VARIABLE_INFO((byte)1),
    FLOAT_VARIABLE_INFO((byte)2),
    NULL_VARIABLE_INFO((byte)5),
    UNINITIALIZEDTHIS_VARIABLE_INFO((byte)6),
    OBJECT_VARIABLE_INFO((byte)7),
    UNINITIALIZED_VARIABLE_INFO((byte)8),
    LONG_VARIABLE_INFO((byte)4),
    DOUBLE_VARIABLE_INFO((byte)3);

    private byte value;

    private VerificationTypeInfo getVerificationTypeInfo(byte x) {
        for (VerificationTypeInfo tag : VerificationTypeInfo.values()) {
            if (x == tag.getValue()) {
                return tag;
            }
        }
        throw new RuntimeException(String.format("VerificationTypeInfo %d is not defined", x));
    }
}
