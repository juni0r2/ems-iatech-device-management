package br.com.iatech.device.management.common;

import io.hypersistence.tsid.TSID;

public class IdGenerator {

    private static final TSID.Factory factore = TSID.Factory.builder().build();
    private IdGenerator() {
    }

    public static TSID generateTSID() {
        return factore.generate();
    }
}
