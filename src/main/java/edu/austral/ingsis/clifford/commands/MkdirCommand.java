package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.directories.Package;

public class MkdirCommand implements Command {
  @Override
  public String execute(String params, FileSystem fileSystem) {
    return createPackage(params, fileSystem);
  }

  private String createPackage(String params, FileSystem fileSystem) {
    if (params.contains("/")) {
      return "Directory names cannot contain '/'";
    }
    if (params.contains(" ")) {
      return "Directory names cannot contain spaces";
    }
    fileSystem
        .getCurrentDirectory()
        .addChild(new Package(params, fileSystem.getCurrentDirectory()));
    return "'" + params + "' directory created";
  }
}
