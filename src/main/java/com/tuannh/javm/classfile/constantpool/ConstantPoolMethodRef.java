package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.DebugPrintConstants;
import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_Methodref_info {
//        u1 tag;
//        u2 class_index;
//        u2 name_and_type_index;
//    }
//
//class_index
//        The class_index item of a CONSTANT_Methodref_info structure must be a class type, not an interface type.
//name_and_type_index
//        The value of the name_and_type_index item must be a valid index into the constant_pool table.
//        The constant_pool entry at that index must be a CONSTANT_NameAndType_info structure (§4.4.6).
//        This constant_pool entry indicates the name and descriptor of the field or method.
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolMethodRef extends ConstantPoolInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private int classIndex;
    private int nameAndTypeIndex;
    private ConstantPoolClass clazz;
    private ConstantPoolNameAndType nameAndType;

    public ConstantPoolMethodRef(byte[] bytes) {
        super(ConstantPoolTag.METHOD_REF);
        this.classIndex = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
        this.nameAndTypeIndex = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 2, 4));
    }

    public ConstantPoolMethodRef(short classIndex, short nameAndTypeIndex) {
        super(ConstantPoolTag.METHOD_REF);
        this.classIndex = Conversion.shortToInt(classIndex);
        this.nameAndTypeIndex = Conversion.shortToInt(nameAndTypeIndex);
    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        clazz = (ConstantPoolClass) constantPool[classIndex - 1];
        nameAndType = (ConstantPoolNameAndType) constantPool[nameAndTypeIndex - 1];
    }

    @Override
    public String debugPrint() {
        return String.format("%-25s#%d.#%d%15s %s.%s:%s", getTag(), classIndex, nameAndTypeIndex, DebugPrintConstants.SEPERATOR, clazz.getName(), nameAndType.getName(), nameAndType.getDescriptor());
    }
}
