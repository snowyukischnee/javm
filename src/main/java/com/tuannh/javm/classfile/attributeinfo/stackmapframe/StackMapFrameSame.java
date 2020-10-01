package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

public class StackMapFrameSame extends StackMapFrame {
    public StackMapFrameSame(StackMapFrameTag tag) {
        super(tag);
    }

    @Override
    public String debugPrint(int padding) {
        return "Same";
    }
}
