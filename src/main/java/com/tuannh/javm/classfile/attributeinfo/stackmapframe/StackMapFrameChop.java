package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;

import java.io.DataInputStream;
import java.io.IOException;

import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

@Getter
public class StackMapFrameChop extends StackMapFrame {
    private int offsetDelta;

    public StackMapFrameChop(int frameType, byte[] bytes) {
        super(frameType, StackMapFrameTag.CHOP_FRAME);
        offsetDelta = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
    }

    public StackMapFrameChop(int frameType, DataInputStream stream) throws IOException {
        super(frameType, StackMapFrameTag.CHOP_FRAME);
        offsetDelta = stream.readUnsignedShort();
    }

    @Override
    public String debugPrint(int padding) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("frame_type = %d\t/* %s */%n", getFrameType(), getTag()));
        builder.append(String.format("%soffset_delta = %d%n", PADDING[padding], offsetDelta));
        return builder.toString();
    }
}
