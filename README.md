# Sokocraft

Minecraft-themed Sokoban puzzle game developed with JavaFX. The project recreates the classic box-pushing puzzle experience with five themed levels, player progress saving, step tracking, custom visual assets, sound effects, and database persistence.

## Overview

Sokocraft is a desktop puzzle game inspired by the classic Sokoban mechanics. The player moves through a grid-based map and pushes objects onto specific target positions to complete each level.

The game uses a Minecraft-inspired visual style, with each level representing a different environment such as a village, the Nether, a mine, the End, and a final dragon encounter. The project combines JavaFX UI development, game logic, persistence, audio management, and structured application architecture.

## Key Features

* Minecraft-themed Sokoban gameplay
* Five progressive puzzle levels
* Grid-based movement and collision logic
* Object pushing mechanics
* Step counter
* Save and load system
* Oracle XE database persistence
* Player registration and progress tracking
* Level transition system
* Undo and reset functionality
* Soft-lock detection for invalid puzzle states
* Custom visual assets and UI styling
* Background music and sound effects

## Tech Stack

* Java
* JavaFX
* Maven
* Oracle XE
* EclipseLink
* JPA
* JDBC
* FXML
* CSS
* JFoenix

## Architecture

The project follows a JavaFX MVC-inspired structure. The application is divided into controllers, models, data access objects, data transfer objects, FXML views, resources, and game-specific classes.

### Application Layer

The main application starts from the JavaFX entry point and manages navigation between screens such as the main menu, player registration, level selection, and gameplay scenes.

### Game Engine

Each level is implemented as a separate controller. The game engine uses a matrix-based model to represent walls, empty spaces, player position, movable objects, and target locations.

The logic validates movement, checks whether objects can be pushed, updates the current matrix, tracks player steps, and verifies the win condition after each move.

### Rendering System

The visual layer is built with JavaFX components. The game grid is rendered using image-based tiles, where each character in the matrix maps to a specific visual asset.

This keeps the internal game logic separated from the graphical representation.

### Persistence Layer

The game includes a persistence layer connected to an Oracle XE database. Player data, current level, step count, and serialized game state can be saved and loaded.

The data layer uses Entity, DAO, and DTO classes to separate database operations from the user interface and gameplay logic.

### Audio and Assets

The project includes a custom asset system with Minecraft-inspired textures, fonts, CSS styling, background music, and sound effects. Audio playback is managed through a dedicated sound utility class.

## Levels

The game includes five themed levels:

1. **House:** Introductory village-themed level.
2. **Hell:** Nether-inspired level with lava and gold/piglin mechanics.
3. **Mine:** Cave-themed level with rails, spiderwebs, and more complex puzzles.
4. **End:** End-dimension level with tighter puzzle layouts.
5. **Dragon:** Final boss-style level with multi-phase mechanics.

## Getting Started

### Requirements

* Java 11
* Maven
* JavaFX
* Oracle XE
* NetBeans or another Java IDE

### Run the Project

Install dependencies and run the application using Maven:

```bash
mvn clean javafx:run
```

The project can also be executed from NetBeans using the configured Maven actions.

## Database Setup

The project requires a local Oracle XE database to enable save and load functionality. The database scripts included in the project define the player table used to store progress, current level, steps, and serialized game state.

Before running the save/load system, configure the local database connection according to your own Oracle XE environment.

## Project Purpose

This project was created to practice desktop application development, JavaFX scene management, puzzle game logic, matrix-based state handling, database persistence, and multimedia integration.

It demonstrates the ability to build a complete interactive application with gameplay mechanics, visual design, audio, data persistence, and structured Java architecture.
