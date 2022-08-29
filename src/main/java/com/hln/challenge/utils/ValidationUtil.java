package com.hln.challenge.utils;

import com.hln.challenge.entities.CommandsMap;
import com.hln.challenge.entities.Filter;
import com.hln.challenge.entities.Wood;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationUtil {

    public static Filter validateInputs(String [] argv){
        String minBundlePrice = argv[1];
        String maxBundlePrice = argv[2];
        String command = argv[3];
        double maxPrice;
        double minPrice;
        maxPrice =  getPrice(maxBundlePrice);
        minPrice = getPrice(minBundlePrice);

        if(minPrice>=maxPrice){
            throw new RuntimeException("Min price must be smaller than max price");
        }

        List<Character> commands = command.chars().mapToObj(c -> (char) c).distinct().collect(Collectors.toList());

        if(commands == null || commands.size() == 0){
            throw new RuntimeException("Command is required");
        }

        commands.stream().forEach(c ->  {
            if(!CommandsMap.commandsMap.containsKey(c.toString())){
                throw new RuntimeException(String.format("Invalid command %s in %s ",c,command));
            }
        });

        return new Filter(minPrice,maxPrice,commands);
    }


    public static Wood validateWood(String line){
        String [] woodAttributes = splitLine(line);

        if(woodAttributes==null || woodAttributes.length!=3){
            throw new RuntimeException("Invalid input file");
        }

        String type = woodAttributes[0];
        String id = woodAttributes[1];
        String price = woodAttributes[2];

        checkEmptyString(type,"Wood type");
        checkEmptyString(id,"Wood id");
        checkEmptyString(price,"Wood price");

        double woodPrice =  getWoodPrice(price);

        return new Wood(id,type,woodPrice);
    }


    public static String [] splitLine(String line) {
        if(line!=null){
            return line.split("\\t");
        }
        return null;
    }

    private static void checkEmptyString(String value, String name) {
        if(value == null || value.equals("")){
            throw new RuntimeException(String.format("%s cannot be empty in input file",name));
        }
    }

    private static double getPrice(String minBundlePrice) {
        double minPrice;
        try {
            minPrice =  Double.parseDouble(minBundlePrice);
        } catch(Exception e){
            throw new NumberFormatException("Invalid  price " + minBundlePrice);
        }
        return minPrice;
    }

    private static double getWoodPrice(String price) {
        double minPrice;
        try {
            minPrice =  Double.parseDouble(price.replace("$",""));
        } catch(Exception e){
            throw new NumberFormatException("Invalid price in file " + price);
        }
        return minPrice;
    }
}
