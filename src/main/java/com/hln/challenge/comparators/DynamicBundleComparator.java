package com.hln.challenge.comparators;

import com.hln.challenge.entities.Bundle;
import com.hln.challenge.entities.CommandsMap;
import com.hln.challenge.entities.WoodType;

import java.util.Comparator;

public class DynamicBundleComparator implements Comparator<Bundle> {
    private  char compareBy;

    public DynamicBundleComparator(char compareBy) {
        this.compareBy = compareBy;
    }

    public DynamicBundleComparator compareBy(char compareBy) {
        this.compareBy = compareBy;
        return this;
    }

    /**
     * Returns which comparator should be used
     *
     * @param a
     * @param b
     * @return
     */
    public int compare(Bundle a, Bundle b) {
        if(this.compareBy == 'b'){
            return Double.compare(a.getTotal(),b.getTotal());
        } else {
            WoodType wt = CommandsMap.commandsMap.get(String.valueOf(this.compareBy));
            return Double.compare(getWoodPrice(a,wt),getWoodPrice(b,wt));
        }
    }

    private double getWoodPrice(Bundle b, WoodType woodType) {
        return b.getWoods().get(woodType).getPrice();
    }
}