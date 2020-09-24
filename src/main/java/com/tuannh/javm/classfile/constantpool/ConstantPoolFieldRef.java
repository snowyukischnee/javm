package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_Fieldref_info {
//        u1 tag;
//        u2 class_index;
//        u2 name_and_type_index;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolFieldRef extends ConstantPoolInfo {
    private short classIndex;
    private short nameAndTypeIndex;
    @Setter
    private ConstantPoolClass clazz;
    @Setter
    private ConstantPoolNameAndType nameAndType;

    public ConstantPoolFieldRef(byte[] bytes) {
        super(ConstantPoolTag.FIELD_REF);
        this.classIndex = ByteBuffer.wrap(ByteUtils.slice(bytes, 0, 2)).getShort();
        this.nameAndTypeIndex = ByteBuffer.wrap(ByteUtils.slice(bytes, 2, 4)).getShort();
    }

    public ConstantPoolFieldRef(short classIndex, short nameAndTypeIndex) {
        super(ConstantPoolTag.FIELD_REF);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
