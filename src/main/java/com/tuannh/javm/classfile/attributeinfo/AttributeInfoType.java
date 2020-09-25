package com.tuannh.javm.classfile.attributeinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttributeInfoType {
    // Five attributes are critical to correct interpretation of the class file by the Java Virtual Machine:
    CONSTANT_VALUE("ConstantValue"),
    CODE("Code"),
    STACK_MAP_TABLE("StackMapTable"),
    EXCEPTIONS("Exceptions"),
    BOOTSTRAP_METHODS("BootstrapMethods"),
    // Twelve attributes are critical to correct interpretation of the class file by the class
    // libraries of the Java SE platform:
    INNER_CLASSES("InnerClasses"),
    ENCLOSING_METHOD("EnclosingMethod"),
    SYNTHETIC("Synthetic"),
    SIGNATURE("Signature"),
    RUNTIME_VISIBLE_ANNOTATIONS("RuntimeVisibleAnnotations"),
    RUNTIME_INVISIBLE_ANNOTATIONS("RuntimeInvisibleAnnotations"),
    RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS("RuntimeVisibleParameterAnnotations"),
    RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS("RuntimeInvisibleParameterAnnotations"),
    RUNTIME_VISIBLE_TYPE_ANNOTATIONS("RuntimeVisibleTypeAnnotations"),
    RUNTIME_INVISIBLE_TYPE_ANNOTATIONS("RuntimeInvisibleTypeAnnotations"),
    ANNOTATION_DEFAULT("AnnotationDefault"),
    METHOD_PARAMETERS("MethodParameters"),
    // Six attributes are not critical to correct interpretation of the class file by either the
    // Java Virtual Machine or the class libraries of the Java SE platform, but are useful for tools:
    SOURCE_FILE("SourceFile"),
    SOURCE_DEBUG_EXTENSION("SourceDebugExtension"),
    LINE_NUMBER_TABLE("LineNumberTable"),
    LOCAL_VARIABLE_TABLE("LocalVariableTable"),
    LOCAL_VARIABLE_TYPE_TABLE("LocalVariableTypeTable"),
    DEPRECATED("Deprecated");

    private String value;

    @SuppressWarnings("java:S112")
    public static AttributeInfoType fromStr(String value) {
        for (AttributeInfoType type : AttributeInfoType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new RuntimeException(String.format("AttributeInfoType %s is not defined", value));
    }
}
