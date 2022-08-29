package com.hln.challenge.service;

import com.hln.challenge.entities.Filter;
import com.hln.challenge.entities.Wood;
import com.hln.challenge.entities.WoodType;
import com.hln.challenge.utils.FileUtils;
import com.hln.challenge.utils.ValidationUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService implements WoodService{



    public FileService() {
    }

    /**
     * Populates a map grouped by wood type based on a file
     *
     * @param filename
     * @throws IOException
     */
    @Override
    public Map<WoodType, List<Wood>> retrieveWoodData(String filename, Filter filter) throws IOException {
        Map<WoodType, List<Wood>> woodMap;

        try (Stream<String> lines = FileUtils.readFile(filename)) {
            woodMap = lines.map(ValidationUtil::validateWood).filter(w -> w.filter(filter)).collect(Collectors.groupingBy(Wood::getType));
        }

        return woodMap;
    }

}
