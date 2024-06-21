package edu.austral.ingsis.clifford.commands;

import java.util.Optional;

public class CommandFactory {
  public static Optional<Command> getCommand(String command) {
    return switch (command) {
      case "ls" -> Optional.of(new LsCommand());
      case "mkdir" -> Optional.of(new MkdirCommand());
      case "cd" -> Optional.of(new CdCommand());
      case "pwd" -> Optional.of(new PwdCommand());
      case "touch" -> Optional.of(new TouchCommand());
      case "rm" -> Optional.of(new RmCommand());
      default -> Optional.empty();
    };
  }
}
