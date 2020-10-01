package com.tuannh.javm.classfile.instruction;

import com.tuannh.javm.classfile.common.DebugPrint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@AllArgsConstructor
@ToString
@Getter
public class Instruction implements DebugPrint {
    private Opcode op;
    private byte[] bytes;

    @Override
    public String debugPrint(int padding) {
        return String.format("%-20s%s", op, Arrays.toString(bytes));
    }
}
