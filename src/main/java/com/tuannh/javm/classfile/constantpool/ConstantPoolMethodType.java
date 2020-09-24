package com.tuannh.javm.classfile.constantpool;

import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_MethodType_info {
//        u1 tag;
//        u2 descriptor_index;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolMethodType extends ConstantPoolInfo {
    private short descriptorIndex;

    public ConstantPoolMethodType(byte[] bytes) {
        super(ConstantPoolTag.METHOD_TYPE);
        this.descriptorIndex = ByteBuffer.wrap(bytes).getShort();
    }

    public ConstantPoolMethodType(short descriptorIndex) {
        super(ConstantPoolTag.METHOD_TYPE);
        this.descriptorIndex = descriptorIndex;
    }
}
