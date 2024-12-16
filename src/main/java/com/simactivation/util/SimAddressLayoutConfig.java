package com.simactivation.util;

import java.util.List;

public class SimAddressLayoutConfig implements Config {

    private List<String> cities;
    private List<String> states;

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

}
