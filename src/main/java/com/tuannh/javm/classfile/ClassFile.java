package com.tuannh.javm.classfile;

import com.tuannh.javm.classfile.accessflag.ClassAccessFlag;
import com.tuannh.javm.classfile.attributeinfo.AttributeInfo;
import com.tuannh.javm.classfile.common.DebugPrint;
import com.tuannh.javm.classfile.constantpool.ConstantPoolClass;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.fieldinfo.FieldInfo;
import com.tuannh.javm.classfile.methodinfo.MethodInfo;
import lombok.*;

import java.util.Arrays;

import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

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
    private int methodsCount;
    private MethodInfo[] methods;
    private int attributeCount;
    private AttributeInfo[] attributes;

    @Override
    public String debugPrint(int padding) {
        StringBuilder builder = new StringBuilder();
        builder.append("ClassFile\n");
        builder.append(String.format("%smagic:\t0x%s%n", PADDING[padding], Integer.toHexString(magic).toUpperCase()));
        builder.append(String.format("%sminor_version:\t%d (0x%s)%n", PADDING[padding], minorVersion, Integer.toHexString(minorVersion).toUpperCase()));
        builder.append(String.format("%smajor_version:\t%d (0x%s)%n", PADDING[padding], majorVersion, Integer.toHexString(majorVersion).toUpperCase()));
        builder.append(String.format("%saccess_flags:\t%s%n", PADDING[padding], Arrays.toString(accessFlags)));
        builder.append(String.format("%sthis_class:\t%s%n", PADDING[padding], thisClass.debugPrint(0)));
        builder.append(String.format("%ssuper_class:\t%s%n", PADDING[padding], superClass.debugPrint(0)));
        // ---
        builder.append(PADDING[padding]);
        builder.append(String.format("constant_pool_count: %d\t", constantPoolCount));
        builder.append(String.format("interfaces_count: %d\t", interfacesCount));
        builder.append(String.format("fields_count: %d\t", fieldsCount));
        builder.append(String.format("methods_count: %d\t", methodsCount));
        builder.append(String.format("attribute_count: %d\t", attributeCount));
        builder.append("\n");
        // ---
        final String TABLE_ITEM_FMT_1 = "%s\t%d:\t%s%n";
        final String TABLE_ITEM_FMT_0 = "%s\t#%d\t%s%n";
        builder.append(String.format("%sConstant pool(count=%d):%n", PADDING[padding], constantPoolCount));
        for (int i = 0; i < constantPoolCount - 1; i++) {
            if (constantPool[i] == null) {
                continue;
            }
            builder.append(String.format(TABLE_ITEM_FMT_0, PADDING[padding], i + 1, constantPool[i].debugPrint(padding + 1)));
        }
        builder.append(String.format("Interfaces(count=%d):%n", interfacesCount));
        for (int i = 0; i < interfacesCount; i++) {
            builder.append(String.format(TABLE_ITEM_FMT_0, PADDING[padding], i + 1, interfaces[i].debugPrint(padding + 1)));
        }
        builder.append(String.format("Fields(count=%d):%n", fieldsCount));
        for (int i = 0; i < fieldsCount; i++) {
            builder.append(String.format(TABLE_ITEM_FMT_0, PADDING[padding], i + 1, fields[i].debugPrint(padding + 1)));
        }
        builder.append(String.format("Methods(count=%d):%n", methodsCount));
        for (int i = 0; i < methodsCount; i++) {
            builder.append(String.format(TABLE_ITEM_FMT_0, PADDING[padding], i + 1, methods[i].debugPrint(padding + 1)));
        }
        builder.append(String.format("Attribute(count=%d):%n", attributeCount));
        for (int i = 0; i < attributeCount; i++) {
            if (attributes[i] != null) {
                builder.append(String.format(TABLE_ITEM_FMT_0, PADDING[padding], i + 1, attributes[i].debugPrint(padding + 1)));
            }
        }
        return builder.toString();
    }
}
