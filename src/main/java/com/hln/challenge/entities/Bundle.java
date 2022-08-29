package com.hln.challenge.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Bundle implements Serializable {

    private BigDecimal total;
    private Map<WoodType,Wood> woods;

    public Bundle() {
    }

    /**
     * Creates a bundle based a list of woods
     * Sets the total price after all woods have been added to the map
     *
     * @param woodList
     */
    public Bundle(List<Wood> woodList) {
        //Cast list of woods to map
        this.setWoods(woodList.stream().collect(Collectors.toMap(wood -> wood.getType(), wood -> wood)));
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal() {
        //sum all wood prices in map

        this.total = woods.values().stream().map(Wood::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2,RoundingMode.HALF_UP);

    }

    public Map<WoodType, Wood> getWoods() {
        return woods;
    }

    public void setWoods(Map<WoodType, Wood> woods) {
        this.woods = woods;
        setTotal();
    }

    public Wood getWood(WoodType type) {
        return getWoods().get(type);
    }

    public boolean filter(Filter filter){
        //Checks if bundle should be filtered or not
        return filter.getMaxPrice() >= this.total.doubleValue() && filter.getMinPrice() <= this.total.doubleValue();
    }

    @Override
    public String toString() {
        StringBuffer sb  = new StringBuffer();
        Arrays.asList(WoodType.values()).stream().forEach( wt -> {
            if(wt.equals(WoodType.BUNDLE)){
                sb.append(String.format("BUNDLE\t%.2f\t",total));
            } else {
                sb.append(printWood(woods.get(wt)));
            }
        });

        return sb.toString();
    }

    public String printWood(Wood wood){
        return wood!=null?wood.toString():"\t\t";
    }


    public BigDecimal getWoodPrice(WoodType woodType) {
        return woods.get(woodType).getPrice();
    }

    public BigDecimal getWoodPrice(Character woodType) {
        return woods.get(CommandsMap.commandsMap.get(woodType.toString())).getPrice();
    }


}
