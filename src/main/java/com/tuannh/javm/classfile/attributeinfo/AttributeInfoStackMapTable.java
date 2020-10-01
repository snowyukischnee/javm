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

import java.io.IOException;
import java.nio.ByteBuffer;

import static com.tuannh.javm.classfile.attributeinfo.stackmapframe.StackMapFrameParser.parseStackMapFrame;

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
        frames = parseStackMapFrame(ByteUtils.slice(bytes, 2, bytes.length), numberOfEntries, constantPool);
    }

    @Override
    public String debugPrint(int padding) {
        return null;
    }
}
