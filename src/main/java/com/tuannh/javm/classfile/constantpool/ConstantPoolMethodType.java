package com.tuannh.javm.classfile.constantpool;

import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

@Getter
@ToString
public class ConstantPoolMethodType extends ConstantPoolInfo {
//    CONSTANT_MethodType_info {
//        u1 tag;
//        u2 descriptor_index;
//    }
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
