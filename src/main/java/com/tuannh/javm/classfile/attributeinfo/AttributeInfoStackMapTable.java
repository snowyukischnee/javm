package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.attributeinfo.stackmapframe.StackMapFrame;
import com.tuannh.javm.classfile.attributeinfo.stackmapframe.StackMapFrameSame;
import com.tuannh.javm.classfile.attributeinfo.stackmapframe.StackMapFrameTag;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

//StackMapTable_attribute {
//    u2              attribute_name_index;
//    u4              attribute_length;
//    u2              number_of_entries;
//    stack_map_frame entries[number_of_entries];
//}

@SuppressWarnings("java:S125")
@Getter
@ToString
public class AttributeInfoStackMapTable extends AttributeInfo {
    private int numberOfEntries;
    private StackMapFrame[] frames;

    public AttributeInfoStackMapTable(int attributeNameIndex, int attributeLength, byte[] bytes, ConstantPoolInfo[] constantPool) throws IOException {
        super(attributeNameIndex, attributeLength, constantPool);
        numberOfEntries = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
        int currentRead = 2;
        frames = new StackMapFrame[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            StackMapFrameTag tag = StackMapFrameTag.fromInt(Conversion.byteToInt(ByteBuffer.wrap(ByteUtils.slice(bytes, currentRead, currentRead + 1)).get()));
            // TODO
            switch (tag) {
                case SAME_FRAME:
                    frames[i] = new StackMapFrameSame(tag);
                    break;
                case SAME_LOCALS_1_STACK_ITEM_FRAME:
                case SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED:
                case CHOP_FRAME:
                case SAME_FRAME_EXTENDED:
                case APPEND_FRAME:
                case FULL_FRAME:
            }
        }
    }

    @Override
    public String debugPrint(int padding) {
        return null;
    }
}
