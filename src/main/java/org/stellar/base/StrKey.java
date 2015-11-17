package org.stellar.base;

import org.apache.commons.codec.binary.Base32;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

class StrKey {
    public enum VersionByte {
        ACCOUNT_ID((byte)0x30),
        SEED((byte)0x90);
        private final byte value;
        VersionByte(byte value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }

    public static String encodeStellarAddress(byte[] data) {
        return encodeCheck(VersionByte.ACCOUNT_ID, data);
    }

    public static String encodeStellarSecretSeed(byte[] data) {
        return encodeCheck(VersionByte.SEED, data);
    }

    public static byte[] decodeStellarAddress(String data) {
        return decodeCheck(VersionByte.ACCOUNT_ID, data);
    }

    public static byte[] decodeStellarSecretSeed(String data) {
        return decodeCheck(VersionByte.SEED, data);
    }

    protected static String encodeCheck(VersionByte versionByte, byte[] data) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(versionByte.getValue());
            outputStream.write(data);
            byte payload[] = outputStream.toByteArray();
            byte checksum[] = StrKey.calculateChecksum(payload);
            outputStream.write(checksum);
            byte unencoded[] = outputStream.toByteArray();
            Base32 base32Codec = new Base32();
            return base32Codec.encodeAsString(unencoded);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    protected static byte[] decodeCheck(VersionByte versionByte, String encoded) {
        Base32 base32Codec = new Base32();
        byte[] decoded = base32Codec.decode(encoded);
        byte decodedVersionByte = decoded[0];
        byte[] payload  = Arrays.copyOfRange(decoded, 0, decoded.length-2);
        byte[] data     = Arrays.copyOfRange(payload, 1, payload.length);
        byte[] checksum = Arrays.copyOfRange(decoded, decoded.length-2, decoded.length);

        if (decodedVersionByte != versionByte.getValue()) {
            throw new FormatException("Version byte is invalid");
        }

        byte[] expectedChecksum = StrKey.calculateChecksum(payload);

        if (!Arrays.equals(expectedChecksum, checksum)) {
            throw new FormatException("Checksum invalid");
        }

        return data;
    }

    protected static byte[] calculateChecksum(byte[] bytes) {
        // This code calculates CRC16-XModem checksum
        // Ported from https://github.com/alexgorbatchev/node-crc
        int crc = 0x0000;
        int count = bytes.length;
        int i = 0;
        int code;

        while (count > 0) {
            code = crc >>> 8 & 0xFF;
            code ^= bytes[i++] & 0xFF;
            code ^= code >>> 4;
            crc = crc << 8 & 0xFFFF;
            crc ^= code;
            code = code << 5 & 0xFFFF;
            crc ^= code;
            code = code << 7 & 0xFFFF;
            crc ^= code;
            count--;
        }

        // little-endian
        return new byte[] {
            (byte)crc,
            (byte)(crc >>> 8)};
    }
}
