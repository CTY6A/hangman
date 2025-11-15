package com.stubedavd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary {
    private static List<String> words;

    public Dictionary(String filename) {
        try (Stream<String> lines = Files.lines(Paths.get("src", "main", "resources", filename))) {
            words = lines.collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading dictionary file: " + e.getMessage());
            System.exit(1);
        }
    }

    public String getRandomWord() {
        return words.get(new Random().nextInt(words.size()));
    }
}
