package com.hln.challenge.entities;

public class Wood{
    private String id;
    private WoodType type;
    private Double price;

    public Wood(String id, String type, Double price) {
        this.id = id;
        this.type =  WoodType.valueOf(type);
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WoodType getType() {
        return type;
    }

    public void setType(String type) {
        this.type = WoodType.valueOf(type);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean filter(Filter filter) {
        return filter.getCommands().contains(this.type.getIdentifier());
    }

    @Override
    public String toString() {
        if(this.type != null) {
            return String.format("%s\t%-10s\t%.2f\t", this.type, this.id, this.price);
        } else {
            return String.format("%s\t%10s","");
        }
    }

}
