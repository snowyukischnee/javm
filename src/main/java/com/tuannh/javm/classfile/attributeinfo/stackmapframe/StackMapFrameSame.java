package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

public class StackMapFrameSame extends StackMapFrame {
    public StackMapFrameSame(int frameType) {
        super(frameType, StackMapFrameTag.SAME_FRAME);
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("frame_type = %d\t/* %s */%n", getFrameType(), getTag());
    }
}
