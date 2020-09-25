package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.DebugPrintConstants;
import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_Fieldref_info {
//        u1 tag;
//        u2 class_index;
//        u2 name_and_type_index;
//    }
//
//class_index
//        The class_index item of a CONSTANT_Fieldref_info structure may be either a class type or an interface type.
//name_and_type_index
//        The value of the name_and_type_index item must be a valid index into the constant_pool table.
//        The constant_pool entry at that index must be a CONSTANT_NameAndType_info structure (§4.4.6).
//        This constant_pool entry indicates the name and descriptor of the field or method.
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolFieldRef extends ConstantPoolInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private short classIndex;
    private short nameAndTypeIndex;
    private ConstantPoolClass clazz;
    private ConstantPoolNameAndType nameAndType;

    public ConstantPoolFieldRef(byte[] bytes) {
        super(ConstantPoolTag.FIELD_REF);
        this.classIndex = ByteBuffer.wrap(ByteUtils.slice(bytes, 0, 2)).getShort();
        this.nameAndTypeIndex = ByteBuffer.wrap(ByteUtils.slice(bytes, 2, 4)).getShort();
    }

    public ConstantPoolFieldRef(short classIndex, short nameAndTypeIndex) {
        super(ConstantPoolTag.FIELD_REF);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
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
