package com.tuannh.javm.cli;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;

class TestInheritance {
    private static final String SPR = "AA";
}

interface TestInterface {
    void gg() throws IOException;

    static void ff() { }
}

public class TestParser extends TestInheritance implements TestInterface {
    public static final String TESTSTR = "TESTDDD";
    public static final long MAGICTEST = 1452352345342345624L;
    public static final double FFF = 123456.789;
    public static final int AASD = 43253445;
    public static final float FFSS = 123456.789F;

    public final Double x = 1.2;

    public static void main(String[] args) {
        System.out.println(TESTSTR);
        TestInterface.ff();
        getV(null);
    }

    @Override
    public void gg() throws IOException {
        try {
            System.out.println(MAGICTEST);
            throw new IOException("dd");
        } catch (IOException e) {
            throw e;
        }
    }

    protected static TestInheritance getV(TestInterface x) {
        return null;
    }

    private <T> T vv(Object a, Double b, Boolean d) {
        return null;
    }
}
