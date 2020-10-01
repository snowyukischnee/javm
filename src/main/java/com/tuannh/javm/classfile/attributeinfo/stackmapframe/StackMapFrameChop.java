package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;

@Getter
public class StackMapFrameChop extends StackMapFrame {
    private int offsetDelta;

    public StackMapFrameChop(byte[] bytes) {
        super(StackMapFrameTag.CHOP_FRAME);
        offsetDelta = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("Chop: offset_delta=%d", offsetDelta);
    }
}
