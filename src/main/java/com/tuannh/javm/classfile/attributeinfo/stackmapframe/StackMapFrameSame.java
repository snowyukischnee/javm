package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

public class StackMapFrameSame extends StackMapFrame {
    public StackMapFrameSame() {
        super(StackMapFrameTag.SAME_FRAME);
    }

    @Override
    public String debugPrint(int padding) {
        return "Same";
    }
}
