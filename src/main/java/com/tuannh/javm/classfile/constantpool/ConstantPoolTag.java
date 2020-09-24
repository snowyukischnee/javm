package com.tuannh.javm.classfile.constantpool;

// https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4

//Constant Type	Value
//CONSTANT_Class	7
//CONSTANT_Fieldref	9
//CONSTANT_Methodref	10
//CONSTANT_InterfaceMethodref	11
//CONSTANT_String	8
//CONSTANT_Integer	3
//CONSTANT_Float	4
//CONSTANT_Long	5
//CONSTANT_Double	6
//CONSTANT_NameAndType	12
//CONSTANT_Utf8	1
//CONSTANT_MethodHandle	15
//CONSTANT_MethodType	16
//CONSTANT_InvokeDynamic	18

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConstantPoolTag {
    CLASS((byte)7),
    FIELD_REF((byte)9),
    METHOD_REF((byte)10),
    INTERFACE_METHOD_REF((byte)11),
    STRING((byte)8),
    INTEGER((byte)3),
    FLOAT((byte)4),
    LONG((byte)5),
    DOUBLE((byte)6),
    NAME_AND_TYPE((byte)12),
    UTF8((byte)1),
    METHOD_HANDLE((byte)15),
    METHOD_TYPE((byte)16),
    INVOKE_DYNAMIC((byte)18);

    private byte tag;

    @SuppressWarnings("java:S112")
    public static ConstantPoolTag fromInt(int value) {
        for (ConstantPoolTag tag : ConstantPoolTag.values()) {
            if (value == tag.getTag()) {
                return tag;
            }
        }
        throw new RuntimeException(String.format("ConstantPoolTag %d is not defined", value));
    }

    @SuppressWarnings("java:S112")
    public static ConstantPoolTag fromByte(byte value) {
        for (ConstantPoolTag tag : ConstantPoolTag.values()) {
            if (value == tag.getTag()) {
                return tag;
            }
        }
        throw new RuntimeException(String.format("ConstantPoolTag %d is not defined", value));
    }
}
