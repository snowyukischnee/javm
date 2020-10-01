package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VerificationTypeInfoTag {
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

    @SuppressWarnings("java:S112")
    public static VerificationTypeInfoTag getVerificationTypeInfoTag(byte x) {
        for (VerificationTypeInfoTag tag : VerificationTypeInfoTag.values()) {
            if (x == tag.getValue()) {
                return tag;
            }
        }
        throw new RuntimeException(String.format("VerificationTypeInfoTag %d is not defined", x));
    }
}
