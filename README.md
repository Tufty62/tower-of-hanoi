# Tower of Hanoi

A Java implementation of the Tower of Hanoi puzzle, with a JavaFX graphical interface.

## About

This project implements the Tower of Hanoi puzzle using object-oriented programming principles. The game logic is separated from the graphical interface, with `Hanoi` managing the game and `Peg` managing the discs placed on each peg.

The project was built as a way to practise Java and object-oriented design before extending the game with a graphical interface.

## Features

* Configurable number of discs and pegs
* Validates legal disc movements
* Prevents larger discs being placed on smaller discs
* Tracks the state of each peg
* JavaFX graphical interface

## Technologies

* Java
* JavaFX
* Object-Oriented Programming

## Project Structure

```text
├── Main.java      # JavaFX application and GUI
├── Hanoi.java     # Game state and move validation
├── Peg.java       # Peg state and disc management
└── .vscode/       # VS Code project configuration
```

## How It Works

The game represents each peg as an object containing its discs. The `Hanoi` class manages the collection of pegs and controls whether moves are valid.

The GUI is kept separate from the game logic, allowing the underlying Hanoi implementation to be used independently of how the game is displayed.

## Running the Project

This project currently uses the JavaFX installation provided by Ubuntu.

Compile:

```bash
javac --module-path /usr/share/openjfx/lib --add-modules javafx.controls Main.java Hanoi.java Peg.java
```

Run:

```bash
java --module-path /usr/share/openjfx/lib --add-modules javafx.controls Main
```

## Future Improvements

* Add mouse-based disc movement
* Add move counter
* Add win-state detection
* Add difficulty/settings controls
* Improve the visual design and animations
