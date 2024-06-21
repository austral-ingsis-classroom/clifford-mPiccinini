package edu.austral.ingsis.clifford.directories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Package implements Directory {
  private final List<Directory> children;
  private final String name;
  private final Package parent;

  public Package(String name, Package parent) {
    this.parent = parent;
    this.name = name;
    this.children = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Package getParent() {
    return parent;
  }

  public Optional<List<Directory>> getChildren() {
    if (children.isEmpty()) return Optional.empty();
    return Optional.of(children);
  }

  public void addChild(Directory directory) {
    children.add(directory);
  }

  public void removeChild(Directory directory) {
    children.remove(directory);
  }

  public Directory getChild(Directory name) {
    return children.get(children.indexOf(name));
  }
}
