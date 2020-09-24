package com.tuannh.javm.classfile.constantpool;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_String_info {
//        u1 tag;
//        u2 string_index;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolString extends ConstantPoolInfo {
    private short stringIndex;
    @Setter
    private String value;

    public ConstantPoolString(byte[] bytes) {
        super(ConstantPoolTag.STRING);
        this.stringIndex = ByteBuffer.wrap(bytes).getShort();
    }

    public ConstantPoolString(short stringIndex) {
        super(ConstantPoolTag.STRING);
        this.stringIndex = stringIndex;
    }
}