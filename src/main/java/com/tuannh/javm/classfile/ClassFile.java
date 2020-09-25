package com.tuannh.javm.classfile;

import com.tuannh.javm.classfile.accessflag.ClassAccessFlag;
import com.tuannh.javm.classfile.common.DebugPrint;
import com.tuannh.javm.classfile.constantpool.ConstantPoolClass;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import lombok.*;

import java.util.Arrays;

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
public class ClassFile implements DebugPrint {
    public static final int MAGIC_CONSTANT = 0xCAFEBABE;
    private int magic;
    private short minorVersion;
    private short majorVersion;
    private int constantPoolCount;
    private ConstantPoolInfo[] constantPool;
    private ClassAccessFlag[] accessFlags;
    private ConstantPoolClass thisClass;
    private ConstantPoolClass superClass;
    private int interfacesCount;
    private ConstantPoolClass[] interfaces;
    private int fieldsCount;
    private FieldInfo[] fields;

    @Override
    public String debugPrint() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClassFile\n");
        builder.append(String.format("magic:\t0x%s%n", Integer.toHexString(magic).toUpperCase()));
        builder.append(String.format("minor_version:\t%d (0x%s)%n", minorVersion, Integer.toHexString(minorVersion).toUpperCase()));
        builder.append(String.format("major_version:\t%d (0x%s)%n", majorVersion, Integer.toHexString(majorVersion).toUpperCase()));
        builder.append(String.format("access_flags:\t%s%n", Arrays.toString(accessFlags)));
        builder.append(String.format("this_class:\t%s%n", thisClass.debugPrint()));
        builder.append(String.format("super_class:\t%s%n", superClass.debugPrint()));
        // ---
        builder.append(String.format("constant_pool_count: %d\t", constantPoolCount));
        builder.append(String.format("interfaces_count: %d\t", interfacesCount));
        builder.append(String.format("fields_count: %d\t", fieldsCount));
        builder.append("\n");
        // ---
        builder.append(String.format("Constant pool(count=%d):%n", constantPoolCount));
        for (int i = 0; i < constantPoolCount - 1; i++) {
            if (constantPool[i] == null) {
                continue;
            }
            builder.append(String.format("\t#%d\t%s%n", i + 1, constantPool[i].debugPrint()));
        }
        builder.append(String.format("Interfaces(count=%d):%n", interfacesCount));
        for (int i = 0; i < interfacesCount; i++) {
            builder.append(String.format("\t#%d\t%s%n", i + 1, interfaces[i].debugPrint()));
        }
        builder.append(String.format("Fields(count=%d):%n", fieldsCount));
        for (int i = 0; i < fieldsCount; i++) {
            builder.append(String.format("\t#%d%n%s%n", i + 1, fields[i].debugPrint()));
        }
        return builder.toString();
    }
}
