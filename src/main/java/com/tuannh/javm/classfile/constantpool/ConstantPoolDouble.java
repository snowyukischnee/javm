package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.ImmediatelyResolvable;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_Double_info {
//        u1 tag;
//        u4 high_bytes;
//        u4 low_bytes;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolDouble extends ConstantPoolInfo implements ImmediatelyResolvable {
    private byte[] highBytes;
    private byte[] lowBytes;
    private double value;

    public ConstantPoolDouble(byte[] bytes) {
        super(ConstantPoolTag.DOUBLE);
        this.highBytes = ByteUtils.slice(bytes, 0, 4);
        this.lowBytes = ByteUtils.slice(bytes, 4, 8);
    }

    public ConstantPoolDouble(byte[] highBytes, byte[] lowBytes) {
        super(ConstantPoolTag.DOUBLE);
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }

    @Override
    public void resolve() {
        value = ByteBuffer.wrap(ByteUtils.concat(highBytes, lowBytes)).getDouble();
    }
}