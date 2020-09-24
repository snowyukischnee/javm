package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_MethodHandle_info {
//        u1 tag;
//        u1 reference_kind;
//        u2 reference_index;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolMethodHandle extends ConstantPoolInfo {
    private byte referenceKind;
    private short referenceIndex;

    public ConstantPoolMethodHandle(byte[] bytes) {
        super(ConstantPoolTag.METHOD_HANDLE);
        this.referenceKind = ByteBuffer.wrap(ByteUtils.slice(bytes, 0, 1)).get();
        this.referenceIndex = ByteBuffer.wrap(ByteUtils.slice(bytes, 1, 3)).getShort();
    }

    public ConstantPoolMethodHandle(byte referenceKind, short referenceIndex) {
        super(ConstantPoolTag.METHOD_HANDLE);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }
}
