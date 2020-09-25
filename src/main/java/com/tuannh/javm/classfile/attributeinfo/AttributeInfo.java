package com.tuannh.javm.classfile.attributeinfo;

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
public class AttributeInfo {
    private int attributeNameIndex;
    private int attributeLength;
    @Setter
    private String attributeName;

    public AttributeInfo(int attributeNameIndex, int attributeLength) {

    }
}
