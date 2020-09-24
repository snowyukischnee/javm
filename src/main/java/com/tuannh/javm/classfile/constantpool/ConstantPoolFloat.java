package com.tuannh.javm.classfile.constantpool;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_Float_info {
//        u1 tag;
//        u4 bytes;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolFloat extends ConstantPoolInfo implements ImmediatelyResolvableConstantPoolType {
    private byte[] bytes;
    @Setter
    private float value;

    public ConstantPoolFloat(byte[] bytes) {
        super(ConstantPoolTag.FLOAT);
        this.bytes = bytes;
        resolve();
    }

    @Override
    public void resolve() {
        value = ByteBuffer.wrap(bytes).getFloat();
    }
}