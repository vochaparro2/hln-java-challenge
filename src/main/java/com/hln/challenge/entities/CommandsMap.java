package com.hln.challenge.entities;

import java.util.HashMap;
import java.util.Map;

public class CommandsMap {
    public static final Map<String, WoodType> commandsMap = new HashMap<>();
    static {
        commandsMap.put("b",WoodType.BUNDLE);
        commandsMap.put("m",WoodType.MAPLE);
        commandsMap.put("o",WoodType.OAK);
        commandsMap.put("p",WoodType.PINE);
    }
}
