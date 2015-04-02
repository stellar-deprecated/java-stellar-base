package org.stellar.base;

import org.stellar.base.xdr.XdrDataInputStream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by andrewrogers on 4/1/15.
 */
public class Xdr {
    public static byte[] TEST_XDR = {
        0x01, 0x00, 0x00, 0x00, // boolean
        0x03, 0x00, 0x00, 0x01, // int 1
        0x00, 0x00, 0x00, 0x03, // string l 3
        0x24, 0x45, 0x57, 0x00, // string
        0x00, 0x00, 0x00, 0x02, // int arr l 2
        0x00, 0x00, 0x00, 0x02, // int[0]
        0x00, 0x00, 0x00, 0x02, // int[1]
        0x00, 0x00, 0x00, 0x02, // foat arr l 2
        0x00, 0x00, 0x00, 0x02, // float[0]
        0x00, 0x34, 0x00, 0x02, // float[1]
        0x00, 0x00, 0x00, 0x01, // double arr l 1
        0x00, 0x00, 0x00, 0x02, // double[0]
        0x00, 0x34, 0x00, 0x02, // double[0]
    };

    public static void main(String[] args) throws IOException {
        XdrDataInputStream xdis = new XdrDataInputStream(new ByteArrayInputStream(TEST_XDR));
        boolean truth = xdis.readBoolean();
        xdis.pad();
        int i = xdis.readInt();
        String str = xdis.readString();
        System.out.println(truth + " " + i + " " + str);
        int[] iarr = xdis.readIntArray();
        float[] farr = xdis.readFloatArray();
        double[] darr = xdis.readDoubleArray();


    }
}
