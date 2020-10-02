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
                    attributes[i] = new AttributeInfoStackMapTable(attributeNameIndex, attributeLength, ByteUtils.readBytes(stream, attributeLength), constantPool);
                    break;
                case EXCEPTIONS:
                    stream.skipBytes(attributeLength);
                    break;
                case BOOTSTRAP_METHODS:
                    stream.skipBytes(attributeLength);
                    break;
                case INNER_CLASSES:
                    stream.skipBytes(attributeLength);
                    break;
                case ENCLOSING_METHOD:
                    stream.skipBytes(attributeLength);
                    break;
                case SYNTHETIC:
                    stream.skipBytes(attributeLength);
                    break;
                case SIGNATURE:
                    stream.skipBytes(attributeLength);
                    break;
                case RUNTIME_VISIBLE_ANNOTATIONS:
                    stream.skipBytes(attributeLength);
                    break;
                case RUNTIME_INVISIBLE_ANNOTATIONS:
                    stream.skipBytes(attributeLength);
                    break;
                case RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                    stream.skipBytes(attributeLength);
                    break;
                case RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                    stream.skipBytes(attributeLength);
                    break;
                case RUNTIME_VISIBLE_TYPE_ANNOTATIONS:
                    stream.skipBytes(attributeLength);
                    break;
                case RUNTIME_INVISIBLE_TYPE_ANNOTATIONS:
                    stream.skipBytes(attributeLength);
                    break;
                case ANNOTATION_DEFAULT:
                    stream.skipBytes(attributeLength);
                    break;
                case METHOD_PARAMETERS:
                    stream.skipBytes(attributeLength);
                    break;
                case SOURCE_FILE:
                    stream.skipBytes(attributeLength);
                    break;
                case SOURCE_DEBUG_EXTENSION:
                    stream.skipBytes(attributeLength);
                    break;
                case LINE_NUMBER_TABLE:
                    stream.skipBytes(attributeLength);
                    break;
                case LOCAL_VARIABLE_TABLE:
                    stream.skipBytes(attributeLength);
                    break;
                case LOCAL_VARIABLE_TYPE_TABLE:
                    stream.skipBytes(attributeLength);
                    break;
                case DEPRECATED:
                    stream.skipBytes(attributeLength);
                    break;
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
