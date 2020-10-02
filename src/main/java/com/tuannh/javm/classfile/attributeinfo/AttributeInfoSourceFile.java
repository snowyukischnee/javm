package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolUtf8;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

//SourceFile_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 sourcefile_index;
//}

//sourcefile_index
//The value of the sourcefile_index item must be a valid index into the constant_pool table. The constant_pool entry
// at that index must be a CONSTANT_Utf8_info structure (§4.4.7) representing a string.

@SuppressWarnings("java:S125")
@Getter
@ToString
public class AttributeInfoSourceFile extends AttributeInfo {
    private int sourceFileIndex;

    private ConstantPoolUtf8 sourceFile;

    public AttributeInfoSourceFile(int attributeNameIndex, int attributeLength, byte[] bytes, ConstantPoolInfo[] constantPool) {
        super(attributeNameIndex, attributeLength, constantPool);
        sourceFileIndex = ByteBufferUtils.getUnsignedShort(bytes);

        sourceFile = (ConstantPoolUtf8) constantPool[sourceFileIndex - 1];
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("SourceFile:\t\t%s", sourceFile.getValue());
    }
}
