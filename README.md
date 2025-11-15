# Hangman Game

A classic word guessing game implemented in Java with a console interface. Try to guess the hidden word by suggesting letters before running out of attempts!

## Features

- Classic Hangman gameplay with a console interface
- Russian word dictionary included
- Case-insensitive input
- Visual representation of the hangman's state
- Win/loss tracking

## How to Play

1. The game will select a random word from the dictionary
2. Guess letters one at a time
3. You have a limited number of incorrect guesses (typically 6)
4. Guess the word before you run out of attempts to win!

## Project Structure

```
hangman/
├── src/
│   └── main/
│       ├── java/com/stubedavd/
│       │   ├── Game.java          # Main game logic
│       │   ├── Main.java          # Entry point
│       │   ├── GameState.java     # Game state management
│       │   ├── Dictionary.java    # Word dictionary handling
│       │   └── ScaffoldState.java # Visual hangman states
│       └── resources/
│           └── russian_words.txt  # Word dictionary
├── pom.xml                       # Maven configuration
└── README.md                     # This file
```

## Project Requirements

This project was created as part of a Java learning course. The original requirements can be found [here](https://zhukovsd.github.io/java-backend-learning-course/projects/hangman/).


