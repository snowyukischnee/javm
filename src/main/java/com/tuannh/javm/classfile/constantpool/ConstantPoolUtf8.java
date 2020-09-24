package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

//    CONSTANT_Utf8_info {
//        u1 tag;
//        u2 length;
//        u1 bytes[length];
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolUtf8 extends ConstantPoolInfo implements ImmediatelyResolvableConstantPoolType {
    private short length;
    private byte[] bytes;
    @Setter
    private String value;

    public ConstantPoolUtf8(byte[] bytes) {
        super(ConstantPoolTag.UTF8);
        this.length = ByteBuffer.wrap(ByteUtils.slice(bytes, 0, 2)).getShort();
        this.bytes = ByteUtils.slice(bytes, 2, bytes.length);
        resolve();
    }

    public ConstantPoolUtf8(short length, byte[] bytes) {
        super(ConstantPoolTag.UTF8);
        this.length = length;
        this.bytes = bytes;
        resolve();
    }

    @Override
    public void resolve() {
        value = new String(bytes, StandardCharsets.UTF_8);
    }
}
