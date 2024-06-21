package edu.austral.ingsis.clifford.directories;

public class File implements Directory {
  private final String name;
  private final Package parent;

  public File(String name, Package parent) {
    this.name = name;
    this.parent = parent;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Package getParent() {
    return parent;
  }
}
