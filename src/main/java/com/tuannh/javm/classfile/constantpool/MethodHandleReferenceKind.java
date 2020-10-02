package com.tuannh.javm.classfile.constantpool;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MethodHandleReferenceKind {
    REF_GETFIELD((byte)1),
    REF_GETSTATIC((byte)2),
    REF_PUTFIELD((byte)3),
    REF_PUTSTATIC((byte)4),
    REF_INVOKEVIRTUAL((byte)5),
    REF_INVOKESTATIC((byte)6),
    REF_INVOKESPECIAL((byte)7),
    REF_NEWINVOKESPECIAL((byte)8),
    REF_INVOKEINTERFACE((byte)9);

    private byte value;

    @SuppressWarnings("java:S112")
    public static MethodHandleReferenceKind fromByte(byte v) {
        for (MethodHandleReferenceKind tag : MethodHandleReferenceKind.values()) {
            if (v == tag.getValue()) {
                return tag;
            }
        }
        throw new RuntimeException(String.format("ReferenceType %d is not defined", v));
    }
}
