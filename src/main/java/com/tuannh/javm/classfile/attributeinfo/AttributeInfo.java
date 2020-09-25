package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolUtf8;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//attribute_info {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u1 info[attribute_length];
//}
@SuppressWarnings("java:S125")
@Getter
@ToString
public class AttributeInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private int attributeNameIndex;
    private int attributeLength;
    private String attributeName;

    public AttributeInfo(int attributeNameIndex, int attributeLength) {

    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        attributeName = ((ConstantPoolUtf8)constantPool[attributeNameIndex - 1]).getValue();
    }
}
