package com.hln.challenge.entities;

import java.util.List;

public class Filter {
    private List<Character> commands;
    private double maxPrice;
    private double minPrice;


    public Filter(double minPrice, double maxPrice,List<Character> commands) {
            this.commands = commands;
            this.maxPrice = maxPrice;
            this.minPrice = minPrice;
    }

    public  List<Character> getCommands() {
        return commands;
    }

    public void setCommands( List<Character> commands) {
        this.commands = commands;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }
}
