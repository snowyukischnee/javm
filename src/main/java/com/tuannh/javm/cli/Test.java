package com.tuannh.javm.cli;

import com.tuannh.javm.classfile.ClassFile;
import com.tuannh.javm.classfile.FieldInfo;
import com.tuannh.javm.classfile.Parser;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        final String classPath = "/home/tuannh/git/javm/target/classes/com/tuannh/javm/cli/TestParser.class";
        ClassFile classFile = Parser.parseClassFile(classPath);
        System.out.println(classFile);
        for (FieldInfo field : classFile.getFields()) {
            System.out.println(field);
        }
    }
}
