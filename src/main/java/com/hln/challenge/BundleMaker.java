package com.hln.challenge;

import com.hln.challenge.entities.Filter;
import com.hln.challenge.entities.Wood;
import com.hln.challenge.entities.WoodType;
import com.hln.challenge.service.FileService;
import com.hln.challenge.service.WoodService;
import com.hln.challenge.utils.FileUtils;
import com.hln.challenge.utils.ValidationUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class BundleMaker
{

    public static void main(final String[] argv) {
        try {
            if (argv.length > 0 && argv.length == 5) {
                System.out.println("The command line arguments are:");
                Arrays.asList(argv).stream().forEach(System.out::println);
                String inputFilename = argv[0];
                String outputFilename = argv[4];

                Filter filter = ValidationUtil.validateInputs(argv);
                System.out.println("---------------------------------------");
                WoodService woodService = new FileService();
                BundleManager bm = new BundleManager(filter);
                System.out.println("Retrieving data...");
                Map<WoodType, List<Wood>> woods = woodService.retrieveWoodData(inputFilename, filter);
                if (woods.isEmpty()) {
                    System.err.println("Input file is empty");
                    return;
                }
                System.out.println("Creating Bundles...");
                bm.process(woods);
                System.out.println("Creating Bundles File...");
                FileUtils.writeToFile(outputFilename, bm.getBundles());

                System.out.println("Bundles created successfully in file " + outputFilename);
            } else {
                System.err.println("Invalid number of arguments. \n  Usage: Input Filename, Minimum Bundle Price, Maximum Bundle Price, Command, Output Filename ");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

