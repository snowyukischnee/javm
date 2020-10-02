package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.ImmediatelyResolvable;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_Float_info {
//        u1 tag;
//        u4 bytes;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolFloat extends ConstantPoolInfo implements ImmediatelyResolvable {
    private byte[] bytes;
    private float value;

    public ConstantPoolFloat(byte[] bytes) {
        super(ConstantPoolTag.FLOAT);
        this.bytes = bytes;
    }

    @Override
    public void resolve() {
        value = ByteBuffer.wrap(bytes).getFloat();
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("%-25s%ff", getTag(), value);
    }
}