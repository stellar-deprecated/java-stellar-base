package org.stellar.base;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

public class StrKeyTest extends TestCase {
    @Test
    public void testDecodeEncode() throws IOException, FormatException {
        String seed = "SDJHRQF4GCMIIKAAAQ6IHY42X73FQFLHUULAPSKKD4DFDM7UXWWCRHBE";
        byte[] secret = StrKey.decodeCheck(StrKey.VersionByte.SEED, seed);
        String encoded = StrKey.encodeCheck(StrKey.VersionByte.SEED, secret);
        assertEquals(seed, encoded);
    }

    @Test()
    public void testDecodeInvalidVersionByte() {
        String address = "GCZHXL5HXQX5ABDM26LHYRCQZ5OJFHLOPLZX47WEBP3V2PF5AVFK2A5D";
        try {
            StrKey.decodeCheck(StrKey.VersionByte.SEED, address);
            fail();
        } catch (FormatException e) {}
    }

    @Test()
    public void testDecodeInvalidSeed() {
        String seed = "SAA6NXOBOXP3RXGAXBW6PGFI5BPK4ODVAWITS4VDOMN5C2M4B66ZML";
        try {
            StrKey.decodeCheck(StrKey.VersionByte.SEED, seed);
            fail();
        } catch (FormatException e) {}
    }

    // TODO more tests
}
