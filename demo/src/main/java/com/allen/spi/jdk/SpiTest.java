package com.allen.spi.jdk;

import java.util.ServiceLoader;

public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<SpiInterface> serviceLoaderList = ServiceLoader.load(SpiInterface.class);

        for (SpiInterface spiInterface : serviceLoaderList) {
            spiInterface.helloWorld();
        }
    }

}
