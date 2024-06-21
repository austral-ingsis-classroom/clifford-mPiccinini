package edu.austral.ingsis;

import edu.austral.ingsis.clifford.FileSystem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSystemRunner {
  private final FileSystem fileSystem;

  public FileSystemRunner(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();
    for (String command : commands) {
      results.add(fileSystem.executeCommand(command));
    }
    return Collections.unmodifiableList(results);
  }
}
