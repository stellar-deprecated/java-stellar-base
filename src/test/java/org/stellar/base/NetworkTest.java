package org.stellar.base;

import junit.framework.TestCase;

import org.junit.Test;

public class NetworkTest extends TestCase {

    public void tearDown() {
        Network.useTestNetwork();
    }

    @Test
    public void testDefaultTestNetwork() {
        assertEquals("Test SDF Network ; September 2015", Network.current().getNetworkPassphrase());
    }

    @Test
    public void testSwitchToPublicNetwork() {
        Network.usePublicNetwork();
        assertEquals("Public Global Stellar Network ; September 2015", Network.current().getNetworkPassphrase());
        Network.useTestNetwork();
    }
}
