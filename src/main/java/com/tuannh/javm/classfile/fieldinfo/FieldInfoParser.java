package com.tuannh.javm.classfile.fieldinfo;

import com.tuannh.javm.classfile.accessflag.FieldAccessFlag;
import com.tuannh.javm.classfile.attributeinfo.AttributeInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import static com.tuannh.javm.classfile.attributeinfo.AttributeInfoParser.parseAttributeInfo;

public class FieldInfoParser {
    private FieldInfoParser() {}

    public static FieldInfo[] parseFieldInfo(DataInputStream stream, int fieldLength, ConstantPoolInfo[] constantPool) throws IOException {
        FieldInfo[] fields = new FieldInfo[fieldLength];
        for (int i = 0; i < fieldLength; i++) {
            FieldAccessFlag[] accessFlag = FieldAccessFlag.getAccessFlags(stream.readShort());
            int nameIndex = stream.readUnsignedShort();
            int descriptorIndex = stream.readUnsignedShort();
            int attributesCount = stream.readUnsignedShort();
            AttributeInfo[] attributes = parseAttributeInfo(stream, attributesCount, constantPool);
            fields[i] = new FieldInfo(accessFlag, nameIndex, descriptorIndex, attributesCount, attributes, constantPool);
        }
        return fields;
    }

    public static FieldInfo[] parseFieldInfo(byte[] bytes, int fieldLength, ConstantPoolInfo[] constantPool) throws IOException {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(bytes));
        return parseFieldInfo(stream, fieldLength, constantPool);
    }
}
