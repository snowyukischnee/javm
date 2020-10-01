package com.tuannh.javm.classfile.methodinfo;

import com.tuannh.javm.classfile.accessflag.MethodAccessFlag;
import com.tuannh.javm.classfile.attributeinfo.AttributeInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolUtf8;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import static com.tuannh.javm.classfile.attributeinfo.AttributeInfoParser.parseAttributeInfo;

public class MethodInfoParser {
    private MethodInfoParser() {}

    public static MethodInfo[] parseMethodInfo(DataInputStream stream, int methodCount, ConstantPoolInfo[] constantPool) throws IOException {
        MethodInfo[] methods = new MethodInfo[methodCount];
        for (int i = 0; i < methodCount; i++) {
            MethodAccessFlag[] accessFlag = MethodAccessFlag.getAccessFlags(stream.readUnsignedShort());
            int nameIndex = stream.readUnsignedShort();
            int descriptorIndex = stream.readUnsignedShort();
            int attributesCount = stream.readUnsignedShort();
            AttributeInfo[] attributes = parseAttributeInfo(stream, attributesCount, constantPool);
            String name = ((ConstantPoolUtf8)constantPool[nameIndex - 1]).getValue();
            String descriptor = ((ConstantPoolUtf8)constantPool[descriptorIndex - 1]).getValue();
            methods[i] = new MethodInfo(accessFlag, nameIndex, descriptorIndex, attributesCount, attributes, name, descriptor);
        }
        return methods;
    }

    public static MethodInfo[] parseMethodInfo(byte[] bytes, int methodCount, ConstantPoolInfo[] constantPool) throws IOException {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(bytes));
        return parseMethodInfo(stream, methodCount, constantPool);
    }
}
