package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.ImmediatelyResolvable;
import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class ConstantPoolParser {
    private ConstantPoolParser() {}

    @SuppressWarnings({"java:S112", "java:S127", "java:S117"})
    public static ConstantPoolInfo[] parseConstantPool(DataInputStream stream, int constantPoolLen) throws IOException {
        ConstantPoolInfo[] constantPool = new ConstantPoolInfo[constantPoolLen];
        int i = 0;
        while (i < constantPoolLen) {
            byte bTag = stream.readByte();
            ConstantPoolTag tag = ConstantPoolTag.fromByte(bTag);
            ConstantPoolInfo constantPoolInfo = null;
            switch (tag) {
                case CLASS:
//                    CONSTANT_Class_info {u1 tag; u2 name_index;}
                    constantPoolInfo = new ConstantPoolClass(ByteUtils.readBytes(stream, 2));
                    break;
                case FIELD_REF:
//                    CONSTANT_Fieldref_info {u1 tag; u2 class_index; u2 name_and_type_index;}
                    constantPoolInfo = new ConstantPoolFieldRef(ByteUtils.readBytes(stream, 4));
                    break;
                case METHOD_REF:
//                    CONSTANT_Methodref_info {u1 tag; u2 class_index; u2 name_and_type_index;}
                    constantPoolInfo = new ConstantPoolMethodRef(ByteUtils.readBytes(stream, 4));
                    break;
                case INTERFACE_METHOD_REF:
//                    CONSTANT_InterfaceMethodref_info {u1 tag; u2 class_index; u2 name_and_type_index;}
                    constantPoolInfo = new ConstantPoolInterfaceMethodRef(ByteUtils.readBytes(stream, 4));
                    break;
                case STRING:
//                    CONSTANT_String_info {u1 tag; u2 string_index;}
                    constantPoolInfo = new ConstantPoolString(ByteUtils.readBytes(stream, 2));
                    break;
                case INTEGER:
//                    CONSTANT_Integer_info {u1 tag; u4 bytes;}
                    constantPoolInfo = new ConstantPoolInteger(ByteUtils.readBytes(stream, 4));
                    break;
                case FLOAT:
//                    CONSTANT_Float_info {u1 tag; u4 bytes;}
                    constantPoolInfo = new ConstantPoolFloat(ByteUtils.readBytes(stream, 4));
                    break;
                case LONG:
//                    CONSTANT_Long_info {u1 tag; u4 high_bytes; u4 low_bytes;}
                    constantPoolInfo = new ConstantPoolLong(ByteUtils.readBytes(stream, 8));
                    break;
                case DOUBLE:
//                    CONSTANT_Double_info {u1 tag; u4 high_bytes; u4 low_bytes;}
                    constantPoolInfo = new ConstantPoolDouble(ByteUtils.readBytes(stream, 8));
                    break;
                case NAME_AND_TYPE:
//                    CONSTANT_NameAndType_info {u1 tag; u2 name_index; u2 descriptor_index;}
                    constantPoolInfo = new ConstantPoolNameAndType(ByteUtils.readBytes(stream, 4));
                    break;
                case UTF8:
//                    CONSTANT_Utf8_info {u1 tag; u2 length; u1 bytes[length];}
                    short len_ = stream.readShort();
                    byte[] bytes_ = ByteUtils.readBytes(stream, len_);
                    constantPoolInfo = new ConstantPoolUtf8(len_, bytes_);
                    break;
                case METHOD_HANDLE:
//                    CONSTANT_MethodHandle_info {u1 tag; u1 reference_kind; u2 reference_index;}
                    constantPoolInfo = new ConstantPoolMethodHandle(ByteUtils.readBytes(stream, 3));
                    break;
                case METHOD_TYPE:
//                    CONSTANT_MethodType_info {u1 tag; u2 descriptor_index;}
                    constantPoolInfo = new ConstantPoolMethodType(ByteUtils.readBytes(stream, 2));
                    break;
                case INVOKE_DYNAMIC:
//                    CONSTANT_InvokeDynamic_info {u1 tag; u2 bootstrap_method_attr_index; u2 name_and_type_index;}
                    constantPoolInfo = new ConstantPoolInvokeDynamic(ByteUtils.readBytes(stream, 4));
                    break;
                default:
                    throw new RuntimeException("Unrecognized ConstantPoolTag");
            }
            constantPool[i] = constantPoolInfo;
            // long and double take over 2 entries
            if (tag == ConstantPoolTag.LONG || tag == ConstantPoolTag.DOUBLE) {
                i += 2;
            } else {
                i++;
            }
        }
        return constantPool;
    }

    public static ConstantPoolInfo[] parseConstantPool(byte[] bytes, int constantPoolLen) throws IOException {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(bytes));
        return parseConstantPool(stream, constantPoolLen);
    }

    @SuppressWarnings({"java:S112", "StatementWithEmptyBody", "java:S3776", "unchecked"})
    public static void resolveConstantPoolInfo(ConstantPoolInfo[] constantPool) {
        for (ConstantPoolInfo constantPoolInfo : constantPool) {
            if (constantPoolInfo instanceof ImmediatelyResolvable) {
                ((ImmediatelyResolvable) constantPoolInfo).resolve();
            }
        }
        for (ConstantPoolInfo constantPoolInfo : constantPool) {
            if (constantPoolInfo instanceof ImmediatelyResolvable) {
                // skip
            } else if (constantPoolInfo instanceof ResolvableWithRequiredObj) {
                ((ResolvableWithRequiredObj<ConstantPoolInfo[]>) constantPoolInfo).resolve(constantPool);
            } else if (constantPoolInfo == null) {
                // null entry, produced by ConstantPoolLong and ConstantPoolDouble, which take 2 ConstantPool entries
            } else {
                throw new RuntimeException("Unrecognized ConstantPool type");
            }
        }
    }
}
