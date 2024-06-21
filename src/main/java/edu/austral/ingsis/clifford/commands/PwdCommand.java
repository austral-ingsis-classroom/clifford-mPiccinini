package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.directories.Package;
import java.util.Objects;

public class PwdCommand implements Command {
  @Override
  public String execute(String params, FileSystem fileSystem) {
    return getRoute(fileSystem);
  }

  private String getRoute(FileSystem fileSystem) {
    Package current = fileSystem.getCurrentDirectory();
    Package parent = current.getParent();
    String currentRoute = current.getName();
    while (true) {
      if (Objects.equals(parent.getName(), "/")) {
        currentRoute = "/" + currentRoute;
        break;
      }
      currentRoute = parent.getName() + "/" + currentRoute;
      parent = parent.getParent();
    }
    return currentRoute;
  }
}
