package com.simactivation.util;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressMapper implements Mapper{

    public void writeConfig() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimAddressLayoutConfig testSimAddressLayout = new SimAddressLayoutConfig();
        List<String> cities = new ArrayList<>();
        cities.add("mumbai");
        cities.add("delhi");
        cities.add("banglore");
        testSimAddressLayout.setCities(cities);
        List<String> states = new ArrayList<>();
        states.add("mumbai");
        states.add("delhi");
        states.add("banglore");
        testSimAddressLayout.setStates(states);
        objectMapper.writeValue(new File("C:/Users/bhanu singh/Desktop/sim_activation/src/main/resources/config/SimAddressLayout.json"),testSimAddressLayout);
    }

    public SimAddressLayoutConfig getConfig() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimAddressLayoutConfig testSimAddressLayout = objectMapper.readValue(new File("C:/Users/bhanu singh/Desktop/sim_activation/src/main/resources/config/SimAddressLayout.json"),SimAddressLayoutConfig.class);
        return testSimAddressLayout;
    }
}
