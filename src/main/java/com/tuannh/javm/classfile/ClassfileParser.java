package com.tuannh.javm.classfile;

import com.tuannh.javm.classfile.accessflag.ClassAccessFlag;
import com.tuannh.javm.classfile.constantpool.*;
import com.tuannh.javm.classfile.fieldinfo.FieldInfo;
import com.tuannh.javm.classfile.methodinfo.MethodInfo;
import com.tuannh.javm.util.ByteUtils;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static com.tuannh.javm.classfile.constantpool.ConstantPoolParser.parseConstantPool;
import static com.tuannh.javm.classfile.constantpool.ConstantPoolParser.resolveConstantPoolInfo;
import static com.tuannh.javm.classfile.fieldinfo.FieldInfoParser.parseFieldInfo;
import static com.tuannh.javm.classfile.methodinfo.MethodInfoParser.parseMethodInfo;

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
public class ClassfileParser {
    private ClassfileParser() {}

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
        resolveConstantPoolInfo(constantPool);
        ClassAccessFlag[] accessFlag = ClassAccessFlag.getAccessFlags(stream.readShort());
        int thisClassIdx = stream.readUnsignedShort();
        int superClassIdx = stream.readUnsignedShort();
        ConstantPoolClass thisClass = (ConstantPoolClass) constantPool[thisClassIdx - 1];
        ConstantPoolClass superClass = (ConstantPoolClass) constantPool[superClassIdx - 1];
        int interfacesCount = stream.readUnsignedShort();
        ConstantPoolClass[] interfaces = parseInterfaces(stream, interfacesCount, constantPool);
        int fieldsCount = stream.readUnsignedShort();
        FieldInfo[] fields = parseFieldInfo(stream, fieldsCount, constantPool);
        int methodsCount = stream.readUnsignedShort();
        MethodInfo[] methods = parseMethodInfo(stream, methodsCount, constantPool);
        return new ClassFile(
                magic,
                minorVersion,
                majorVersion,
                constantPoolCount,
                constantPool,
                accessFlag,
                thisClass,
                superClass,
                interfacesCount,
                interfaces,
                fieldsCount,
                fields,
                methodsCount,
                methods
        );
    }

    public static ConstantPoolClass[] parseInterfaces(DataInputStream stream, int interfaceLength, ConstantPoolInfo[] constantPool) throws IOException {
        // interfaces[]
        //Each value in the interfaces array must be a valid index into the constant_pool table.
        // The constant_pool entry at each value of interfaces[i], where 0 ≤ i < interfaces_count,
        // must be a CONSTANT_Class_info structure representing an interface that is a direct superinterface of
        // this class or interface type, in the left-to-right order given in the source for the type.
        int[] interfacesIdx = ByteUtils.readUnsignedShorts(stream, interfaceLength);
        ConstantPoolClass[] interfaces = new ConstantPoolClass[interfaceLength];
        for (int i = 0; i < interfaceLength; i++) {
            interfaces[i] = (ConstantPoolClass) constantPool[interfacesIdx[i] - 1];
        }
        return interfaces;
    }
}
