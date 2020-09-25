package com.tuannh.javm.classfile;

import com.tuannh.javm.classfile.attributeinfo.AttributeInfo;
import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolUtf8;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//field_info {
//    u2             access_flags;
//    u2             name_index;
//    u2             descriptor_index;
//    u2             attributes_count;
//    attribute_info attributes[attributes_count];
//}
@SuppressWarnings("java:S125")
@Getter
@ToString
public class FieldInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private AccessFlag[] accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private AttributeInfo[] attributes;
    private String name;
    private String descriptor;

    public FieldInfo(AccessFlag[] accessFlag, int nameIndex, int descriptorIndex, int attributesCount, AttributeInfo[] attributes) {
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        name = ((ConstantPoolUtf8)constantPool[nameIndex - 1]).getValue();
        descriptor = ((ConstantPoolUtf8)constantPool[descriptorIndex - 1]).getValue();
    }
}
