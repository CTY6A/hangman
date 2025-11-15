package com.stubedavd;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    public static final int MAX_COUNT_MISTAKES = 6;

    private static final Scanner scanner = new Scanner(System.in);

    private final Dictionary dictionary;

    private int mistakeCounter;
    private String secretWord;
    private GameState gameState;
    private Set<String> guessedLetters;
    private Set<String> wrongLetters;

    public Game(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void gameLoop() {
        System.out.println("Welcome to Hangman!");
        System.out.println("Rules: guess the word letter by letter. You have 6 attempts.");

        while (true) {
            System.out.println("Would you like to play new game? (Y/N)");
            if (!scanner.nextLine().equalsIgnoreCase("Y")) {
                break;
            }

            initializeNewGame();

            while (gameState == GameState.NOT_FINISHED) {
                printGameState();

                processUserGuess();

                checkGameState();
            }

            printGameState();

            if (gameState == GameState.WON) {
                System.out.println("Congratulations! You won!");
            } else {
                System.out.println("Game over! You lost.");
            }

            System.out.println("The word was: " + secretWord);
        }

        System.out.println("Thank you for playing! Goodbye!");
        scanner.close();
    }

    private void initializeNewGame() {
        this.mistakeCounter = 0;
        this.secretWord = dictionary.getRandomWord();
        this.guessedLetters = new HashSet<>();
        this.wrongLetters = new LinkedHashSet<>();
        this.gameState = GameState.NOT_FINISHED;
    }

    public void printGameState() {
        System.out.println(ScaffoldStates.values()[mistakeCounter]);

        System.out.println("Word: " + getMaskedWord());

        if (!wrongLetters.isEmpty()) {
            System.out.println("Mistakes (" + mistakeCounter + "/" + MAX_COUNT_MISTAKES + "): " +
                    String.join(", ", wrongLetters.stream()
                            .map(String::valueOf)
                            .toArray(String[]::new)));
        }

        System.out.println("-".repeat(30));
    }

    private String getMaskedWord() {
        StringBuilder masked = new StringBuilder();
        for (String letter : secretWord.split("")) {
            if (guessedLetters.contains(letter)) {
                masked.append(letter);
            } else {
                masked.append("_");
            }
            masked.append(" ");
        }
        return masked.toString();
    }

    private void processUserGuess() {
        String letter = inputLetter();

        if (secretWord.contains(letter)) {
            guessedLetters.add(letter);
            System.out.println("Letter found!");
        } else {
            if (!wrongLetters.contains(letter)) {
                mistakeCounter++;
                wrongLetters.add(letter);
            }
            System.out.println("Letter not in the word!");
        }
    }

    private String inputLetter() {
        String letter;
        while (true) {
            System.out.print("Enter a letter: ");

            letter = scanner.nextLine().toLowerCase();
            if (letter.length() != 1) {
                System.out.println("Please enter exactly one letter.");
                continue;
            }

            if (letter.charAt(0) >= 'а' && letter.charAt(0) <= 'я' || letter.charAt(0) == 'ё') {
                break;
            } else {
                System.out.println("Please enter a Russian letter (а-я, ё).");
            }
        }
        return letter;
    }

    private void checkGameState() {
        if (guessedLetters.containsAll(Set.of(secretWord.split("")))) {
            gameState = GameState.WON;
        } else if (mistakeCounter >= MAX_COUNT_MISTAKES) {
            gameState = GameState.LOST;
        }
    }
}