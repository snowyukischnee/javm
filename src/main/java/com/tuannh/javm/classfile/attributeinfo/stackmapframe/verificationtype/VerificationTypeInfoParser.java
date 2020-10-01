package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class VerificationTypeInfoParser {
    private VerificationTypeInfoParser() {}

    @SuppressWarnings("java:S112")
    public static VerificationTypeInfo[] parseVerificationTypeInfo(DataInputStream stream, int count, ConstantPoolInfo[] constantPool) throws IOException {
        VerificationTypeInfo[] verificationTypeInfo = new VerificationTypeInfo[count];
        for (int i = 0; i < count; i++) {
            VerificationTypeInfoTag tag = VerificationTypeInfoTag.getVerificationTypeInfoTag(stream.readByte());
            switch (tag) {
                case TOP_VARIABLE_INFO:
                    verificationTypeInfo[i] = new VerificationTypeInfoTop();
                    break;
                case INTEGER_VARIABLE_INFO:
                    verificationTypeInfo[i] = new VerificationTypeInfoInteger();
                    break;
                case FLOAT_VARIABLE_INFO:
                    verificationTypeInfo[i] = new VerificationTypeInfoFloat();
                    break;
                case NULL_VARIABLE_INFO:
                    verificationTypeInfo[i] = new VerificationTypeInfoNull();
                    break;
                case UNINITIALIZEDTHIS_VARIABLE_INFO:
                    verificationTypeInfo[i] = new VerificationTypeInfoUninitializedThis();
                    break;
                case OBJECT_VARIABLE_INFO:
                    verificationTypeInfo[i] = new VerificationTypeInfoObject(ByteUtils.readBytes(stream, 2), constantPool);
                    break;
                case UNINITIALIZED_VARIABLE_INFO:
                    verificationTypeInfo[i] = new VerificationTypeInfoUninitialized(ByteUtils.readBytes(stream, 2));
                    break;
                case LONG_VARIABLE_INFO:
                    verificationTypeInfo[i] = new VerificationTypeInfoLong();
                    break;
                case DOUBLE_VARIABLE_INFO:
                    verificationTypeInfo[i] = new VerificationTypeInfoDouble();
                    break;
                default:
                    throw new RuntimeException("VariableInfo not recognized");
            }
        }
        return verificationTypeInfo;
    }

    public static VerificationTypeInfo[] parseVerificationTypeInfo(byte[] bytes, int count, ConstantPoolInfo[] constantPool) throws IOException {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(bytes));
        return parseVerificationTypeInfo(stream, count, constantPool);
    }
}
