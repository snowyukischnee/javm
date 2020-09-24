package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_Long_info {
//        u1 tag;
//        u4 high_bytes;
//        u4 low_bytes;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolLong extends ConstantPoolInfo implements ImmediatelyResolvableConstantPoolType {
    private byte[] highBytes;
    private byte[] lowBytes;
    @Setter
    private long value;

    public ConstantPoolLong(byte[] bytes) {
        super(ConstantPoolTag.LONG);
        this.highBytes = ByteUtils.slice(bytes, 0, 4);
        this.lowBytes = ByteUtils.slice(bytes, 4, 8);
        resolve();
    }

    public ConstantPoolLong(byte[] highBytes, byte[] lowBytes) {
        super(ConstantPoolTag.LONG);
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
        resolve();
    }

    @Override
    public void resolve() {
        value = ByteBuffer.wrap(ByteUtils.concat(highBytes, lowBytes)).getLong();
    }
}