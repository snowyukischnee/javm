package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

//BootstrapMethods_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 num_bootstrap_methods;
//    {   u2 bootstrap_method_ref;
//        u2 num_bootstrap_arguments;
//        u2 bootstrap_arguments[num_bootstrap_arguments];
//    } bootstrap_methods[num_bootstrap_methods];
//}
//
//num_bootstrap_methods
//The value of the num_bootstrap_methods item determines the number of bootstrap method specifiers in the
// bootstrap_methods array.
//
//bootstrap_methods[]
//Each entry in the bootstrap_methods table contains an index to a CONSTANT_MethodHandle_info structure (§4.4.8) which
// specifies a bootstrap method, and a sequence (perhaps empty) of indexes to static arguments for the bootstrap method.
//
//Each bootstrap_methods entry must contain the following three items:
//
//bootstrap_method_ref
//The value of the bootstrap_method_ref item must be a valid index into the constant_pool table. The constant_pool
// entry at that index must be a CONSTANT_MethodHandle_info structure (§4.4.8).
//
//The form of the method handle is driven by the continuing resolution of the call site specifier in §invokedynamic,
// where execution of invoke in java.lang.invoke.MethodHandle requires that the bootstrap method handle be adjustable to the actual arguments being passed, as if by a call to java.lang.invoke.MethodHandle.asType. Accordingly, the reference_kind item of the CONSTANT_MethodHandle_info structure should have the value 6 or 8 (§5.4.3.5), and the reference_index item should specify a static method or constructor that takes three arguments of type java.lang.invoke.MethodHandles.Lookup, String, and java.lang.invoke.MethodType, in that order. Otherwise, invocation of the bootstrap method handle during call site specifier resolution will complete abruptly.
//
//num_bootstrap_arguments
//The value of the num_bootstrap_arguments item gives the number of items in the bootstrap_arguments array.
//
//bootstrap_arguments[]
//Each entry in the bootstrap_arguments array must be a valid index into the constant_pool table. The constant_pool
// entry at that index must be a CONSTANT_String_info, CONSTANT_Class_info, CONSTANT_Integer_info, CONSTANT_Long_info,
// CONSTANT_Float_info, CONSTANT_Double_info, CONSTANT_MethodHandle_info, or CONSTANT_MethodType_info structure
// (§4.4.3, §4.4.1, §4.4.4, §4.4.5, §4.4.8, §4.4.9).

@SuppressWarnings("java:S125")
@Getter
@ToString
public class AttributeInfoBootstrapMethods extends AttributeInfo {
    private int numBootstrapMethods;
    private BootstrapMethods[] bootstrapMethods;

    public AttributeInfoBootstrapMethods(int attributeNameIndex, int attributeLength, byte[] bytes, ConstantPoolInfo[] constantPool) throws IOException {
        super(attributeNameIndex, attributeLength, constantPool);
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(bytes));
        numBootstrapMethods = stream.readUnsignedShort();
        bootstrapMethods = new BootstrapMethods[numBootstrapMethods];
        for (int i = 0; i < numBootstrapMethods; i++) {
            bootstrapMethods[i] = new BootstrapMethods(stream, constantPool);
        }
    }

    @Override
    public String debugPrint(int padding) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("BootstrapMethods(count=%d):%n", numBootstrapMethods));
        for (int i = 0; i < numBootstrapMethods; i++) {
            builder.append(String.format("%s\t%d:\t%s%n", PADDING[padding], i, bootstrapMethods[i].debugPrint(padding + 1)));
        }
        return builder.toString();
    }
}
