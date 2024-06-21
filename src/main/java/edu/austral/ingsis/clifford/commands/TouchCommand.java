package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.directories.File;

public class TouchCommand implements Command {
  @Override
  public String execute(String params, FileSystem fileSystem) {
    return createFile(params, fileSystem);
  }

  private String createFile(String params, FileSystem fileSystem) {
    if (params.contains("/")) {
      return "File names cannot contain '/'";
    }
    if (params.contains(" ")) {
      return "File names cannot contain spaces";
    }
    fileSystem.getCurrentDirectory().addChild(new File(params, fileSystem.getCurrentDirectory()));
    return "'" + params + "' file created";
  }
}
