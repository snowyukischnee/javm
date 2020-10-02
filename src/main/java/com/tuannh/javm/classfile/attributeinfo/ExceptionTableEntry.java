package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.common.DebugPrint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ExceptionTableEntry implements DebugPrint {
    private int startPc;
    private int endPc;
    private int handlePc;
    private int catchType;


    @Override
    public String debugPrint(int padding) {
        return String.format("start: 0x%s,\t\tend: 0x%s,\t\thandle: 0x%s,\t\tcatch_type: 0x%s", startPc, endPc, handlePc, catchType);
    }
}
