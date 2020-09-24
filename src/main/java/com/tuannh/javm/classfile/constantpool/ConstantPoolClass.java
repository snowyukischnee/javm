package com.tuannh.javm.classfile.constantpool;

import java.nio.ByteBuffer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//    CONSTANT_Class_info {
//        u1 tag;
//        u2 name_index;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolClass extends ConstantPoolInfo {
    private short nameIndex;
    @Setter
    private String name;

    public ConstantPoolClass(byte[] bytes) {
        super(ConstantPoolTag.CLASS);
        this.nameIndex = ByteBuffer.wrap(bytes).getShort();
    }

    public ConstantPoolClass(short nameIndex) {
        super(ConstantPoolTag.CLASS);
        this.nameIndex = nameIndex;
    }
}
