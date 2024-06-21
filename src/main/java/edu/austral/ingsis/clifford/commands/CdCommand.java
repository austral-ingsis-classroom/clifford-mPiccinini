package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.directories.Directory;
import edu.austral.ingsis.clifford.directories.Package;
import java.util.List;
import java.util.Optional;

public class CdCommand implements Command {
  @Override
  public String execute(String params, FileSystem fileSystem) {
    return moveToDirectory(params, fileSystem);
  }

  private String moveToDirectory(String params, FileSystem fileSystem) {
    switch (params) {
      case "." -> {
        fileSystem.setCurrentDirectory(fileSystem.getCurrentDirectory());
        return "moved to directory '" + fileSystem.getCurrentDirectory().getName() + "'";
      }
      case ".." -> {
        if (fileSystem.getCurrentDirectory().getParent() == null) return ("moved to directory '/'");
        fileSystem.setCurrentDirectory(fileSystem.getCurrentDirectory().getParent());
        return "moved to directory '" + fileSystem.getCurrentDirectory().getName() + "'";
      }
      case "/" -> {
        fileSystem.setCurrentDirectory(fileSystem.getRoot());
        return "moved to directory '" + fileSystem.getCurrentDirectory().getName() + "'";
      }
      default -> {
        return setToRoot(params, fileSystem);
      }
    }
  }

  private String setToRoot(String params, FileSystem fileSystem) {
    String[] directories = params.split("/");
    for (String directory : directories) {
      Boolean found = false;
      Optional<List<Directory>> children = fileSystem.getCurrentDirectory().getChildren();
      if (children.isEmpty()) {
        return "'" + params + "' directory does not exist";
      }
      for (Directory child : children.get()) {
        if (child.getName().equals(directory) && child instanceof Package) {
          fileSystem.setCurrentDirectory((Package) child);
          found = true;
        }
      }
      if (!found) {
        return "'" + params + "' directory does not exist";
      }
    }
    return "moved to directory '" + fileSystem.getCurrentDirectory().getName() + "'";
  }
}
