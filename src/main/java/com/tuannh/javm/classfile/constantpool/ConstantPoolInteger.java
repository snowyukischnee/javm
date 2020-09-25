package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.ImmediatelyResolvable;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_Integer_info {
//        u1 tag;
//        u4 bytes;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolInteger extends ConstantPoolInfo implements ImmediatelyResolvable {
    private byte[] bytes;
    private int value;

    public ConstantPoolInteger(byte[] bytes) {
        super(ConstantPoolTag.INTEGER);
        this.bytes = bytes;
    }

    @Override
    public void resolve() {
        value = ByteBuffer.wrap(bytes).getInt();
    }

    @Override
    public String debugPrint() {
        return String.format("%-25s%d", getTag(), value);
    }
}