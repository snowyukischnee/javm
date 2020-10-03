package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

//Deprecated_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//}
//
//attribute_name_index
//The value of the attribute_name_index item must be a valid index into the constant_pool table. The constant_pool entry at that index must be a CONSTANT_Utf8_info structure (§4.4.7) representing the string "Deprecated".
//
//attribute_length
//The value of the attribute_length item must be zero.

@SuppressWarnings("java:S125")
@Getter
@ToString
public class AttributeInfoDeprecated extends AttributeInfo {

    @SuppressWarnings("java:S112")
    public AttributeInfoDeprecated(int attributeNameIndex, int attributeLength, byte[] bytes, ConstantPoolInfo[] constantPool) {
        super(attributeNameIndex, attributeLength, constantPool);
    }

    @Override
    public String debugPrint(int padding) {
        return "Deprecated";
    }
}
