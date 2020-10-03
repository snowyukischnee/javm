package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.accessflag.InnerClassAccessFlag;
import com.tuannh.javm.classfile.common.DebugPrint;
import com.tuannh.javm.classfile.constantpool.ConstantPoolClass;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolUtf8;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
public class InnerClass implements DebugPrint {
    private int innerClassInfoIndex;
    private int outerClassInfoIndex;
    private int innerNameIndex;
    private int innerClassAccessFlags;

    private ConstantPoolClass innerClassInfo;
    private ConstantPoolClass outerClassInfo;
    private ConstantPoolUtf8 innerName;
    private InnerClassAccessFlag[] innerClassFlags;

    public InnerClass(int innerClassInfoIndex, int outerClassInfoIndex, int innerNameIndex, int innerClassAccessFlags, ConstantPoolInfo[] constantPool) {
        this.innerClassInfoIndex = innerClassInfoIndex;
        this.outerClassInfoIndex = outerClassInfoIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerClassAccessFlags = innerClassAccessFlags;

        innerClassInfo = (ConstantPoolClass) constantPool[innerClassInfoIndex - 1];
        if (outerClassInfoIndex > 0) {
            outerClassInfo = (ConstantPoolClass) constantPool[outerClassInfoIndex - 1];
        } else {
            outerClassInfo = null;
        }
        if (innerNameIndex > 0) {
            innerName = (ConstantPoolUtf8) constantPool[innerNameIndex - 1];
        } else {
            innerName = null;
        }
        innerClassFlags = InnerClassAccessFlag.getAccessFlags(innerClassAccessFlags);
    }

    @Override
    public String debugPrint(int padding) {
        return String.format(
                "%s\t#%d= #%d of #%d;\t// %s=%s of class %s",
                Arrays.toString(innerClassFlags),
                innerNameIndex,
                innerClassInfoIndex,
                outerClassInfoIndex,
                innerName.getValue(),
                (innerClassInfo != null) ? innerClassInfo.getName() : "\"Anonymous\"",
                (outerClassInfo != null) ? outerClassInfo.getName() : "\"Anonymous\"");
    }
}
