package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.common.DebugPrintWithRequiredObj;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//ConstantValue_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 constantvalue_index;
//}
//
//constantvalue_index
//The value of the constantvalue_index item must be a valid index into the constant_pool table.
// The constant_pool entry at that index gives the constant value represented by this attribute.
// The constant_pool entry must be of a type appropriate to the field, as specified in Table 4.7.2-A.
//
//Table 4.7.2-A. Constant value attribute types
//
//    Field Type	Entry Type
//    long	CONSTANT_Long
//    float	CONSTANT_Float
//    double	CONSTANT_Double
//    int, short, char, byte, boolean	CONSTANT_Integer
//    String	CONSTANT_String

@SuppressWarnings("java:S125")
@Getter
@ToString
public class AttributeInfoConstantValue extends AttributeInfo {
    private int constantValueIndex;
    private ConstantPoolInfo value;

    @SuppressWarnings("java:S112")
    public AttributeInfoConstantValue(int attributeNameIndex, int attributeLength, short constantValueIndex) {
        super(attributeNameIndex, attributeLength);
        if (attributeLength != 2) {
            throw new RuntimeException("The value of the attribute_length item of a ConstantValue_attribute structure must be two");
        }
        this.constantValueIndex = Conversion.shortToInt(constantValueIndex);
    }

    @SuppressWarnings("java:S112")
    public AttributeInfoConstantValue(int attributeNameIndex, int attributeLength, byte[] bytes) {
        super(attributeNameIndex, attributeLength);
        if (attributeLength != 2) {
            throw new RuntimeException("The value of the attribute_length item of a ConstantValue_attribute structure must be two");
        }
        this.constantValueIndex = ByteBufferUtils.getUnsignedShort(bytes);
    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        super.resolve(constantPool);
        value = constantPool[constantValueIndex - 1];
    }

    @Override
    public String debugPrint() {
        return String.format("ConstantValue:\t\t%s", value.debugPrint());
    }
}
