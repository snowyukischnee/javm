package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;

@Getter
public class StackMapFrameSameExtended extends StackMapFrame {
    private int offsetDelta;

    public StackMapFrameSameExtended(byte[] bytes) {
        super(StackMapFrameTag.SAME_FRAME_EXTENDED);
        offsetDelta = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("SameExtended: offset_delta=%d", offsetDelta);
    }
}
