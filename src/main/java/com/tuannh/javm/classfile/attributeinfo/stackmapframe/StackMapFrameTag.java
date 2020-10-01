package com.tuannh.javm.classfile.attributeinfo.stackmapframe;

public enum StackMapFrameTag {
    SAME_FRAME,
    SAME_LOCALS_1_STACK_ITEM_FRAME,
    SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED,
    CHOP_FRAME,
    SAME_FRAME_EXTENDED,
    APPEND_FRAME,
    FULL_FRAME;

    @SuppressWarnings("java:S112")
    public static StackMapFrameTag fromInt(int value) {
        if (value >= 0 && value <= 63) {
            return SAME_FRAME;
        } else if (value >= 64 && value <= 127) {
            return SAME_LOCALS_1_STACK_ITEM_FRAME;
        } else if (value == 247) {
            return SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED;
        } else if (value >= 248 && value <= 250) {
            return CHOP_FRAME;
        } else if (value == 251) {
            return SAME_FRAME_EXTENDED;
        } else if (value >= 252 && value <= 254) {
            return APPEND_FRAME;
        } else if (value == 255) {
            return FULL_FRAME;
        } else {
            throw new RuntimeException(String.format("Stack Frame tag not recognized: 0x%s", Integer.toHexString(value)));
        }
    }
}
