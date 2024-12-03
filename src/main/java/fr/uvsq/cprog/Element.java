package fr.uvsq.cprog;

/**
 * Represents an element with attributes including name, number, note, and type.
 * <p>
 * This class encapsulates information about an element identified by its name, number
 * , note, and type.
 * </p>
 */

public class Element {
  String name;
  Integer number;
  String note;
  String type;
  /**
     * Constructs an element with the provided attributes.
     *
     * @param name   The name of the element.
     * @param number The number associated with the element.
     * @param note   Additional notes or description for the element.
     * @param type   The type/category of the element.
     */

  public Element(String name, Integer number, String note, String type) {
    this.name = name;
    this.number = number;
    this.type = type;
    this.note = note;
  }

  @Override
    public String toString() {
    return ConsoleColors.PURPLE_BOLD + this.number + ConsoleColors.RESET 
      + ConsoleColors.YELLOW_BOLD_BRIGHT + " | "
                + ConsoleColors.RESET + ConsoleColors.BLUE + this.name + ConsoleColors.RESET
                + ConsoleColors.YELLOW_BOLD_BRIGHT + " | " + ConsoleColors.RESET + this.note
                + ConsoleColors.YELLOW_BOLD_BRIGHT + " | " + ConsoleColors.RESET 
                + ConsoleColors.GREEN_UNDERLINED
                + this.type + ConsoleColors.RESET;

  }

}
