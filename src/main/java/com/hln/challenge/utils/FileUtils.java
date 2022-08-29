package com.hln.challenge.utils;

import com.hln.challenge.entities.Bundle;
import com.hln.challenge.entities.Wood;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {

    public static Stream<String> readFile(String path) throws FileNotFoundException {
        try {
            Path filePath = Paths.get(path);
            if(!Files.exists(filePath)){
                throw new FileNotFoundException("Input file not found " + path);
            }
            return Files.lines(filePath);
        } catch(IOException ex){
            throw new FileNotFoundException("Could not read input file " + path);
        }
    }


    public static void writeToFile(String path, List<Bundle> bundles) throws IOException {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(path));
            for (Bundle bundle : bundles)
                pw.println(bundle);
            pw.close();
        } catch(IOException ex){
            throw new IOException("Could not create output file " + path);
        }

    }


}
