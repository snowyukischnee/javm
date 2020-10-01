package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolUtf8;
import com.tuannh.javm.util.ByteUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class AttributeInfoParser {
    private AttributeInfoParser() {}

    public static AttributeInfo[] parseAttributeInfo(DataInputStream stream, int attributesCount, ConstantPoolInfo[] constantPool) throws IOException {
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            int attributeNameIndex = stream.readUnsignedShort();
            int attributeLength = stream.readInt();
            String attributeName = ((ConstantPoolUtf8)constantPool[attributeNameIndex- 1]).getValue();
            AttributeInfoType attributeType = AttributeInfoType.fromStr(attributeName);
            // -------------------------------------------------------------
            // TODO
            switch (attributeType) {
                case CONSTANT_VALUE:
                    attributes[i] = new AttributeInfoConstantValue(attributeNameIndex, attributeLength, ByteUtils.readBytes(stream, attributeLength), constantPool);
                    break;
                case CODE:
                    attributes[i] = new AttributeInfoCode(attributeNameIndex, attributeLength, ByteUtils.readBytes(stream, attributeLength), constantPool);
                    break;
                case STACK_MAP_TABLE:
                case EXCEPTIONS:
                case BOOTSTRAP_METHODS:
                case INNER_CLASSES:
                case ENCLOSING_METHOD:
                case SYNTHETIC:
                case SIGNATURE:
                case RUNTIME_VISIBLE_ANNOTATIONS:
                case RUNTIME_INVISIBLE_ANNOTATIONS:
                case RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                case RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                case RUNTIME_VISIBLE_TYPE_ANNOTATIONS:
                case RUNTIME_INVISIBLE_TYPE_ANNOTATIONS:
                case ANNOTATION_DEFAULT:
                case METHOD_PARAMETERS:
                case SOURCE_FILE:
                case SOURCE_DEBUG_EXTENSION:
                case LINE_NUMBER_TABLE:
                case LOCAL_VARIABLE_TABLE:
                case LOCAL_VARIABLE_TYPE_TABLE:
                case DEPRECATED:
                default:
                    stream.skipBytes(attributeLength);
            }
            // -------------------------------------------------------------
        }
        return attributes;
    }

    public static AttributeInfo[] parseAttributeInfo(byte[] bytes, int attributesCount, ConstantPoolInfo[] constantPool) throws IOException {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(bytes));
        return parseAttributeInfo(stream, attributesCount, constantPool);
    }
}
