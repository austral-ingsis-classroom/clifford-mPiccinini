package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.directories.Directory;
import edu.austral.ingsis.clifford.directories.Package;
import java.util.*;

public class LsCommand implements Command {
  @Override
  public String execute(String params, FileSystem fileSystem) {
    return listFiles(fileSystem.getCurrentDirectory(), params);
  }

  private String listFiles(Package directory, String params) {
    Optional<List<Directory>> optionalDirectories = directory.getChildren();
    if (optionalDirectories.isEmpty()) {
      return "";
    }
    List<Directory> children = optionalDirectories.get();
    List<String> childrenNames = new ArrayList<>(children.size());
    for (Directory child : children) {
      childrenNames.add(child.getName());
    }
    sort(childrenNames, params);
    return String.join(" ", childrenNames);
  }

  private void sort(List<String> childrenNames, String params) {
    switch (params) {
      case "--ord=asc" -> Collections.sort(childrenNames);
      case "--ord=desc" -> {
        Collections.sort(childrenNames);
        Collections.reverse(childrenNames);
      }
    }
  }
}
