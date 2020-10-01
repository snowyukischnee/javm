package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import lombok.Getter;

@Getter
public class StackMapFrameSameLocals1StackItem extends StackMapFrame {
    public StackMapFrameSameLocals1StackItem(StackMapFrameTag tag) {
        super(tag);
    }

    @Override
    public String debugPrint(int padding) {
        return "SameLocals1StackItem";
    }
}
