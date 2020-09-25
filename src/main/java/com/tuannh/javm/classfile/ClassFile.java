package com.tuannh.javm.classfile;

import com.tuannh.javm.classfile.accessflag.ClassAccessFlag;
import com.tuannh.javm.classfile.common.DebugStr;
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
public class ClassFile implements DebugStr {
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
        builder.append(String.format("\tmagic:\t0x%s%n", Integer.toHexString(magic).toUpperCase()));
        builder.append(String.format("\tminorVersion:\t%d (0x%s)%n", minorVersion, Integer.toHexString(minorVersion).toUpperCase()));
        builder.append(String.format("\tmajorVersion:\t%d (0x%s)%n", majorVersion, Integer.toHexString(majorVersion).toUpperCase()));
        builder.append(String.format("\taccessFlags:\t%s%n", Arrays.toString(accessFlags)));
        builder.append(String.format("\tthisClass:\t%s%n", thisClass.debugPrint()));
        builder.append(String.format("\tsuperClass:\t%s%n", superClass.debugPrint()));
        // ---
        builder.append(String.format("\tconstantPoolCount: %d\t", constantPoolCount));
        builder.append(String.format("\tinterfacesCount: %d\t", interfacesCount));
        builder.append(String.format("\tfieldsCount: %d\t", fieldsCount));
        builder.append("\n");
        // ---
        builder.append("\tConstant pool:\n");
        for (int i = 0; i < constantPoolCount - 1; i++) {
            if (constantPool[i] == null) {
                continue;
            }
            builder.append(String.format("\t\t#%d\t%s%n", i + 1, constantPool[i].debugPrint()));
        }
        builder.append(String.format("\tinterfacesCount\t%d%n", interfacesCount));
        for (int i = 0; i < interfacesCount; i++) {
            builder.append(interfaces[i]).append("\n");
        }
        builder.append(String.format("\tfieldsCount\t%d%n", fieldsCount));
        for (int i = 0; i < fieldsCount; i++) {
            builder.append(fields[i]).append("\n");
        }
        return builder.toString();
    }
}
