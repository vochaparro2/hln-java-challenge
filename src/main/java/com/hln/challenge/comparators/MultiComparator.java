package com.hln.challenge.comparators;

import java.util.Comparator;
import java.util.List;

public class MultiComparator<Bundle> implements Comparator<Bundle> {
    private final List<Comparator<Bundle>> comparators;

    public MultiComparator(List<Comparator<Bundle>> comparators) {
        this.comparators = comparators;
    }


    /**
     * Compares bundles against a list of comparators
     *
     * @param o1
     * @param o2
     * @return
     */
    public int compare(Bundle o1, Bundle o2) {
        for (Comparator<Bundle> c : comparators) {
            int result = c.compare(o1, o2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

}