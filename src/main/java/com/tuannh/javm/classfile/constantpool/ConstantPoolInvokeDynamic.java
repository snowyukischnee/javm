package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.DebugPrintConstants;
import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

//    CONSTANT_InvokeDynamic_info {
//        u1 tag;
//        u2 bootstrap_method_attr_index;
//        u2 name_and_type_index;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolInvokeDynamic extends ConstantPoolInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    private ConstantPoolNameAndType nameAndType;

    public ConstantPoolInvokeDynamic(byte[] bytes) {
        super(ConstantPoolTag.INVOKE_DYNAMIC);
        this.bootstrapMethodAttrIndex = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
        this.nameAndTypeIndex = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 2, 4));
    }

    public ConstantPoolInvokeDynamic(short bootstrapMethodAttrIndex, short nameAndTypeIndex) {
        super(ConstantPoolTag.INVOKE_DYNAMIC);
        this.bootstrapMethodAttrIndex = Conversion.shortToInt(bootstrapMethodAttrIndex);
        this.nameAndTypeIndex = Conversion.shortToInt(nameAndTypeIndex);
    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        nameAndType = (ConstantPoolNameAndType) constantPool[nameAndTypeIndex - 1];
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("%-25s%d.#%d%15s #%d:%s:%s", getTag(), bootstrapMethodAttrIndex, nameAndTypeIndex, DebugPrintConstants.SEPERATOR, bootstrapMethodAttrIndex, nameAndType.getName(), nameAndType.getDescriptor());
    }
}
