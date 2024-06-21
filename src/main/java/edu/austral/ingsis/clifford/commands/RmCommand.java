package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.directories.Directory;
import edu.austral.ingsis.clifford.directories.File;
import edu.austral.ingsis.clifford.directories.Package;
import java.util.List;
import java.util.Optional;

public class RmCommand implements Command {
  @Override
  public String execute(String params, FileSystem fileSystem) {
    return remove(params, fileSystem);
  }

  private String remove(String params, FileSystem fileSystem) {
    Package currentPackage = fileSystem.getCurrentDirectory();
    Optional<List<Directory>> children = currentPackage.getChildren();
    if (children.isEmpty()) {
      return "Directory not in " + currentPackage.getName() + " directory";
    }
    String[] paramsArray = params.split(" ");
    if (paramsArray[0].equals("--recursive")) {
      for (Directory child : children.get()) {
        if (child.getName().equals(paramsArray[1])) {
          if (child instanceof Package) {
            currentPackage.removeChild(child);
            return "'" + paramsArray[1] + "' removed";
          }
          return "Cannot remove file '" + paramsArray[1] + "' recursively";
        }
      }
    } else {
      for (Directory child : children.get()) {
        if (child.getName().equals(paramsArray[0])) {
          if (child instanceof File) {
            currentPackage.removeChild(child);
            return "'" + paramsArray[0] + "' removed";
          }
          return "cannot remove '" + paramsArray[0] + "', is a directory";
        }
      }
    }
    return "Directory not in " + currentPackage.getName() + " directory";
  }
}
