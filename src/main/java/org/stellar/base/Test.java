package org.stellar.base;

/**
 * Created by andrewrogers on 4/2/15.
 */
public enum Test {
    FDR(0),;

    private int mValue;

    Test(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }

    static Test fromValue(int value) throws Exception {
        switch (value) {
            case 0:
                return FDR;
            default:
                throw new Exception();
        }
    }

    private class Tester {
        Test testf;
        Test[] test;

        private Tester() throws Exception {
            testf = Test.fromValue(0);
        }
    }
}

