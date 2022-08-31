package ru.games.rps.exception;

public class GameIsNotActiveException extends RuntimeException {
    public GameIsNotActiveException(Long id) {
        super(String.format("The game with id %s was terminated or finished!", id));
    }
}
