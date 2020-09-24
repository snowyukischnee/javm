package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

@Getter
@ToString
public class ConstantPoolUtf8 extends ConstantPoolInfo {
//    CONSTANT_Utf8_info {
//        u1 tag;
//        u2 length;
//        u1 bytes[length];
//    }
    private short length;
    private byte[] bytes;

    public ConstantPoolUtf8(byte[] bytes) {
        super(ConstantPoolTag.UTF8);
        this.length = ByteBuffer.wrap(ByteUtils.slice(bytes, 0, 2)).getShort();
        this.bytes = ByteUtils.slice(bytes, 2, bytes.length);
    }

    public ConstantPoolUtf8(short length, byte[] bytes) {
        super(ConstantPoolTag.UTF8);
        this.length = length;
        this.bytes = bytes;
    }
}
