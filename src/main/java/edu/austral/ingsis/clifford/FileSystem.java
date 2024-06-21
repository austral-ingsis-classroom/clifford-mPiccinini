package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.CommandFactory;
import edu.austral.ingsis.clifford.directories.Package;
import java.util.Arrays;
import java.util.Optional;

public class FileSystem {
  private final Package root;
  private Package currentDirectory;

  public String executeCommand(String command) {
    String[] words = command.split(" ");
    String firstEntry = words[0];
    String[] params = Arrays.copyOfRange(words, 1, words.length);
    Optional<Command> commandToExecute = CommandFactory.getCommand(firstEntry);
    if (commandToExecute.isEmpty()) {
      return "Command not found: " + firstEntry;
    }
    return commandToExecute.get().execute(String.join(" ", params), this);
  }

  public FileSystem(Package root) {
    this.root = root;
    this.currentDirectory = root;
  }

  public void setCurrentDirectory(Package currentDirectory) {
    this.currentDirectory = currentDirectory;
  }

  public Package getCurrentDirectory() {
    return currentDirectory;
  }

  public Package getRoot() {
    return root;
  }
}
