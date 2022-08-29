package com.hln.challenge.service;

import com.hln.challenge.entities.Filter;
import com.hln.challenge.entities.Wood;
import com.hln.challenge.entities.WoodType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface WoodService {

    Map<WoodType, List<Wood>> retrieveWoodData(String filename, Filter filter) throws Exception;
}
