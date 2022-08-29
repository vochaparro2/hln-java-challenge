package com.hln.challenge;

import com.hln.challenge.comparators.DynamicBundleComparator;
import com.hln.challenge.comparators.MultiComparator;
import com.hln.challenge.entities.*;
import com.hln.challenge.service.FileService;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class BundleManager {

    private List<Bundle> bundles = new ArrayList<>();
    private Filter filter;
    private FileService fs = new FileService();

    public BundleManager(Filter filter){
        this.filter = filter;
    }

    /**
     * Processes a wood map into bundles and sorts them based on a filter
     *
     * @param woodMap
     */
    public void process(Map<WoodType, List<Wood>> woodMap) {
        if(woodMap != null && woodMap.size()>0) {
            createBundles(woodMap);
            sortBundles();
        } else {
            throw new RuntimeException("File is empty");
        }
    }

    public List<Bundle> getBundles(){
        return this.bundles;
    }

    /**
     *  Creates a cartesitan production based on woods map
     */
    private  void createBundles(Map<WoodType, List<Wood>> woodMap){
        List<List<Wood>> woodLists = woodMap.values().stream().collect(Collectors.toList());
        //Get all possible combinations of woods based on provided lists
        List<List<Wood>> cartesianProduct = cartesianProduct(woodLists);

        //Create bundles based on all possible combinations
        cartesianProduct.stream().forEach(woodList -> {
           Optional.ofNullable(createBundle(woodList)).ifPresent(this.bundles::add);
        });
    }

    /**
     * Creates a bundle based on a combinations of woods
     * and checks if bundle should be added based on filter
     *
     * @param woods
     * @return
     */
    private Bundle createBundle(List<Wood> woods){
        Bundle bundle = new Bundle(woods);
        if(bundle.filter(this.filter)){
            return bundle;
        }
        return null;
    }

    /**
     * Creates all possible combinations of woods from the lists filtered
     *
     * @param lists
     * @return
     */
    private List<List<Wood>> cartesianProduct(List<List<Wood>> lists) {
        List<List<Wood>> resultLists = new ArrayList<>();
        if (lists.size() == 0) {
            resultLists.add(new ArrayList<>());
            return resultLists;
        } else {
            List<Wood> firstList = lists.get(0);
            List<List<Wood>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
            for (Wood wood : firstList) {
                for (List<Wood> remainingList : remainingLists) {
                    ArrayList<Wood> resultList = new ArrayList<>();
                    resultList.add(wood);
                    resultList.addAll(remainingList);
                    resultLists.add(resultList);
                }
            }
        }
        return resultLists;
    }

    /**
     * Sorts bundles based on the filter commands
     *
     */
    private void sortBundles() {
        if(this.filter != null && this.filter.getCommands().size() > 0) {
            List<Comparator<Bundle>> comparatorList = new ArrayList<>();
            //loop through the commands and create comparator based on filter commands
            this.filter.getCommands().stream().forEachOrdered(command -> {
                comparatorList.add(new DynamicBundleComparator(command));
            });
            //process comparator list
            this.bundles.sort(new MultiComparator<>(comparatorList));
        }
    }

}
