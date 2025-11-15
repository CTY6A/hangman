package com.stubedavd;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private int mistakeCounter;
    private String wordToGuess;
    private boolean wordFound;
    private final Set<Character> guessedLetters;
    private final Set<Character> incorrectLetters;
    private final BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    public Game() {
        this.mistakeCounter = 0;
        this.wordFound = false;
        this.guessedLetters = new HashSet<>();
        this.incorrectLetters = new HashSet<>();
    }

    public void gameLoop() {
        renderer.printWelcome();

        while (askToPlayNewGame()) {
            resetGame();
            renderer.printRules();

            while (mistakeCounter < 6 && !wordFound) {
                renderer.printGameState(mistakeCounter, getMaskedWord(), incorrectLetters);

                processUserGuess();
                checkIfWordIsGuessed();
            }

            displayFinalState();
        }
    }

    private void resetGame() {
        mistakeCounter = 0;
        wordFound = false;
        guessedLetters.clear();
        incorrectLetters.clear();
        wordToGuess = Words.getRandomWord();
    }

    private void displayFinalState() {
        renderer.printGameState(mistakeCounter, getMaskedWord(), incorrectLetters);

        if (wordFound) {
            renderer.printVictory(wordToGuess);
        } else {
            renderer.printGameOver(wordToGuess);
        }
    }

    private String getMaskedWord() {
        StringBuilder masked = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            if (guessedLetters.contains(c) || c == ' ') {
                masked.append(c);
            } else {
                masked.append("_");
            }
            masked.append(" ");
        }
        return masked.toString().trim();
    }

    private void processUserGuess() {
        renderer.printLetterPrompt();
        char letter = scanner.nextLine().toLowerCase().charAt(0);

        if (wordToGuess.indexOf(letter) != -1) {
            guessedLetters.add(letter);
            renderer.printLetterFound();
        } else {
            if (!incorrectLetters.contains(letter)) {
                mistakeCounter++;
                incorrectLetters.add(letter);
            }
            renderer.printLetterNotFound();
        }
    }

    private void checkIfWordIsGuessed() {
        wordFound = true;
        for (char c : wordToGuess.toCharArray()) {
            if (c != ' ' && !guessedLetters.contains(c)) {
                wordFound = false;
                break;
            }
        }
    }

    private boolean askToPlayNewGame() {
        renderer.printPlayAgainPrompt();
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("Y")) {
            return true;
        } else {
            renderer.printGoodbye();
            scanner.close();
            return false;
        }
    }
}