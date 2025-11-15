package com.stubedavd;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary("russian_words.txt");

        (new Game(dictionary)).gameLoop();
    }
}