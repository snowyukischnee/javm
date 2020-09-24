package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

@Getter
@ToString
public class ConstantPoolFieldRef extends ConstantPoolInfo {
    //    CONSTANT_Fieldref_info {
    //        u1 tag;
    //        u2 class_index;
    //        u2 name_and_type_index;
    //    }
    private short classIndex;
    private short nameAndTypeIndex;

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
