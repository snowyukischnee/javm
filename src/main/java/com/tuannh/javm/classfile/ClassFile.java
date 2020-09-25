package com.tuannh.javm.classfile;

import com.tuannh.javm.classfile.constantpool.ConstantPoolClass;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import lombok.*;

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
@AllArgsConstructor
@Getter
@ToString
public class ClassFile {
    public static final int MAGIC_CONSTANT = 0xCAFEBABE;
    private int magic;
    private short minorVersion;
    private short majorVersion;
    private int constantPoolCount;
    private ConstantPoolInfo[] constantPool;
    private AccessFlag[] accessFlags;
    private ConstantPoolClass thisClass;
    private ConstantPoolClass superClass;
    private int interfacesCount;
    private ConstantPoolClass[] interfaces;
    private int fieldsCount;
    private FieldInfo[] fields;
}
