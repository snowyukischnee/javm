package com.tuannh.javm.classfile.constantpool;

import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;


@Getter
@ToString
public class ConstantPoolString extends ConstantPoolInfo {
    //    CONSTANT_String_info {
    //        u1 tag;
    //        u2 string_index;
    //    }
    private short stringIndex;

    public ConstantPoolString(byte[] bytes) {
        super(ConstantPoolTag.STRING);
        this.stringIndex = ByteBuffer.wrap(bytes).getShort();
    }

    public ConstantPoolString(short stringIndex) {
        super(ConstantPoolTag.STRING);
        this.stringIndex = stringIndex;
    }
}