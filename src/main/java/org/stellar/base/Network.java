package org.stellar.base;

import java.io.UnsupportedEncodingException;

public class Network {
    private final static String PUBLIC = "Public Global Stellar Network ; September 2015";
    private final static String TESTNET = "Test SDF Network ; September 2015";
    private static Network current = new Network(TESTNET);

    private final String networkPassphrase;

    Network(String networkPassphrase) {
        this.networkPassphrase = networkPassphrase;
    }

    public String getNetworkPassphrase() {
        return networkPassphrase;
    }

    public byte[] getNetworkId() {
        try {
            return Util.hash(current.getNetworkPassphrase().getBytes("UTF8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static Network current() {
        return current;
    }

    public static void use(Network network) {
        current = network;
    }

    public static void usePublicNetwork() {
        Network.use(new Network(PUBLIC));
    }

    public static void useTestNetwork() {
        Network.use(new Network(TESTNET));
    }
}
