package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ElementTest {

    @Test
    public void testElementConstructor() {
        // Arrange
        // Create elements for testing
        String name = "File1";
        Integer number = 1;
        String note = "This is a note";
        String type = "txt";

        // Act
        Element e = new Element(name, number, note, type);

        // Assert
        assertEquals(name, e.name);
        assertEquals(number, e.number);
        assertEquals(note, e.note);
        assertEquals(type, e.type);
    }

    @Test
    public void testToString() {
        // Arrange
        String name = "File1";
        Integer number = 1;
        String note = "This is a note";
        String type = "txt";
        Element e = new Element(name, number, note, type);

        // Act
        String result = e.toString();

        // Assert
        String expected = ConsoleColors.PURPLE_BOLD + number + ConsoleColors.RESET +
                ConsoleColors.YELLOW_BOLD_BRIGHT + " | " + ConsoleColors.RESET +
                ConsoleColors.BLUE + name + ConsoleColors.RESET +
                ConsoleColors.YELLOW_BOLD_BRIGHT + " | " + ConsoleColors.RESET +
                note +
                ConsoleColors.YELLOW_BOLD_BRIGHT + " | " + ConsoleColors.RESET +
                ConsoleColors.GREEN_UNDERLINED + type + ConsoleColors.RESET;

        assertEquals(expected, result);
    }


}

