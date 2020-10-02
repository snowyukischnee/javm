package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.attributeinfo.stackmapframe.StackMapFrame;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;

import static com.tuannh.javm.classfile.attributeinfo.stackmapframe.StackMapFrameParser.parseStackMapFrame;
import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

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
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("StackMapFrame(number_of_entries=%d):%n", numberOfEntries));
        for (int i = 0; i < numberOfEntries; i++) {
            builder.append(String.format("%s\t%s", PADDING[padding], frames[i].debugPrint(padding + 1)));
        }
        return builder.toString();
    }
}
