package com.tuannh.javm.classfile;

import com.tuannh.javm.classfile.constantpool.*;
import com.tuannh.javm.util.ByteUtils;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

// https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html

//ClassFile {
//        u4             magic;
//        u2             minor_version;
//        u2             major_version;
//        u2             constant_pool_count;
//        cp_info        constant_pool[constant_pool_count-1];
//        u2             access_flags;
//        u2             this_class;
//        u2             super_class;
//        u2             interfaces_count;
//        u2             interfaces[interfaces_count];
//        u2             fields_count;
//        field_info     fields[fields_count];
//        u2             methods_count;
//        method_info    methods[methods_count];
//        u2             attributes_count;
//        attribute_info attributes[attributes_count];
//        }
@SuppressWarnings("java:S125")
public class Parser {
    private Parser() {}

    public static ClassFile parseClassFile(String filePath) throws IOException {
        return parseClassFile(new DataInputStream(new FileInputStream(filePath)));
    }

    @SuppressWarnings("java:S112")
    public static ClassFile parseClassFile(DataInputStream stream) throws IOException {
        int magic = stream.readInt();
        if (magic != ClassFile.MAGIC_CONSTANT) {
            throw new RuntimeException(String.format("Magic number not recognized: %d", magic));
        }
        short minorVersion = stream.readShort();
        short majorVersion = stream.readShort();
        int constantPoolCount = stream.readUnsignedShort();
        if (constantPoolCount < 1) {
            throw new RuntimeException("ConstantPoolCount smaller than 0");
        }
        ConstantPoolInfo[] constantPool = parseConstantPool(stream, constantPoolCount - 1);

        return new ClassFile(
                magic,
                minorVersion,
                VersionNumber.fromShort(majorVersion),
                constantPoolCount,
                constantPool,
                null
        );
    }

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

//    private static void resolveConstantPoolInfo(ConstantPoolInfo[] constantPool) {
//        for (ConstantPoolInfo constantPoolInfo : constantPool) {
//            if (constantPoolInfo instanceof ConstantPoolClass) {
//                ConstantPoolClass obj = (ConstantPoolClass) constantPoolInfo;
//                obj.setName(constantPool[obj.getNameIndex() - 1]);
//            }
//        }
//    }
}
