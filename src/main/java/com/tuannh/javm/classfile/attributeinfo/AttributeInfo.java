package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.common.DebugPrint;
import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolUtf8;
import lombok.Getter;
import lombok.ToString;

//attribute_info {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u1 info[attribute_length];
//}
@SuppressWarnings("java:S125")
@Getter
@ToString
public abstract class AttributeInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]>, DebugPrint {
    private int attributeNameIndex;
    private int attributeLength;
    private String attributeName;

    public AttributeInfo(int attributeNameIndex, int attributeLength) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        attributeName = ((ConstantPoolUtf8)constantPool[attributeNameIndex - 1]).getValue();
    }
}
