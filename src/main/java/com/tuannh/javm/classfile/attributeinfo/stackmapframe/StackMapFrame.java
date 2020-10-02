package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import com.tuannh.javm.classfile.common.DebugPrint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public abstract class StackMapFrame implements DebugPrint {
    private int frameType;
    private StackMapFrameTag tag;
}
