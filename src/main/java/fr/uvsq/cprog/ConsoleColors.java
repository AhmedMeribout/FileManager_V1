package fr.uvsq.cprog;

/**
 * The {@code ConsoleColors} class provides constants for ANSI escape 
 * codes to manipulate text colors,
 * backgrounds, and styles in the console output.
 *
 * <p>It offers a range of color constants and combinations for regular and high-intensity colors,
 * bold and underline styles, background colors, and color-reset codes. These constants enable
 * developers to format console output with various text styles and colors for improved visibility
 * and presentation.
 *
 * <p>The class contains ANSI escape sequences for various text formatting and coloring options.
 * These constants can be used to apply color and style effects to console output by adding the
 * respective escape codes to strings to be printed to the console.
 *
 * <p>Example usage:
 * <pre>
 *     System.out.println(ConsoleColors.GREEN + "Success message" + ConsoleColors.RESET);
 * </pre>
 *
 * @author [Author Name]
 * @version 1.0
 * @since [Date]
 */

public class ConsoleColors {
  // Reset
  public static final String RESET = "\033[0m"; // Text Reset

  // Regular Colors
  public static final String BLACK = "\033[0;30m"; // BLACK
  public static final String RED = "\033[0;31m"; // RED
  public static final String GREEN = "\033[0;32m"; // GREEN
  public static final String YELLOW = "\033[0;33m"; // YELLOW
  public static final String BLUE = "\033[0;34m"; // BLUE
  public static final String PURPLE = "\033[0;35m"; // PURPLE
  public static final String CYAN = "\033[0;36m"; // CYAN
  public static final String WHITE = "\033[0;37m"; // WHITE

  // Bold
  public static final String BLACK_BOLD = "\033[1;30m"; // BLACK
  public static final String RED_BOLD = "\033[1;31m"; // RED
  public static final String GREEN_BOLD = "\033[1;32m"; // GREEN
  public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
  public static final String BLUE_BOLD = "\033[1;34m"; // BLUE
  public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
  public static final String CYAN_BOLD = "\033[1;36m"; // CYAN
  public static final String WHITE_BOLD = "\033[1;37m"; // WHITE

  // Underline
  public static final String BLACK_UNDERLINED = "\033[4;30m"; // BLACK
  public static final String RED_UNDERLINED = "\033[4;31m"; // RED
  public static final String GREEN_UNDERLINED = "\033[4;32m"; // GREEN
  public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
  public static final String BLUE_UNDERLINED = "\033[4;34m"; // BLUE
  public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
  public static final String CYAN_UNDERLINED = "\033[4;36m"; // CYAN
  public static final String WHITE_UNDERLINED = "\033[4;37m"; // WHITE

  // Background
  public static final String BLACK_BACKGROUND = "\033[40m"; // BLACK
  public static final String RED_BACKGROUND = "\033[41m"; // RED
  public static final String GREEN_BACKGROUND = "\033[42m"; // GREEN
  public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
  public static final String BLUE_BACKGROUND = "\033[44m"; // BLUE
  public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
  public static final String CYAN_BACKGROUND = "\033[46m"; // CYAN
  public static final String WHITE_BACKGROUND = "\033[47m"; // WHITE

  // High Intensity
  public static final String BLACK_BRIGHT = "\033[0;90m"; // BLACK
  public static final String RED_BRIGHT = "\033[0;91m"; // RED
  public static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN
  public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
  public static final String BLUE_BRIGHT = "\033[0;94m"; // BLUE
  public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
  public static final String CYAN_BRIGHT = "\033[0;96m"; // CYAN
  public static final String WHITE_BRIGHT = "\033[0;97m"; // WHITE

  // Bold High Intensity
  public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
  public static final String RED_BOLD_BRIGHT = "\033[1;91m"; // RED
  public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
  public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m"; // YELLOW
  public static final String BLUE_BOLD_BRIGHT = "\033[1;94m"; // BLUE
  public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m"; // PURPLE
  public static final String CYAN_BOLD_BRIGHT = "\033[1;96m"; // CYAN
  public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

  // High Intensity backgrounds
  public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m"; // BLACK
  public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m"; // RED
  public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m"; // GREEN
  public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m"; // YELLOW
  public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m"; // BLUE
  public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
  public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; // CYAN
  public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m"; // WHITE

  public static final String AppMessage = ConsoleColors.WHITE_BOLD 
                     + "\nUSE ONE OF THE FOLLOWING COMMANDS : \n" + ConsoleColors.RESET
                + ConsoleColors.WHITE_BOLD_BRIGHT 
                + "mkdir" + ConsoleColors.RESET 
                + ConsoleColors.YELLOW_BOLD_BRIGHT 
                + " | "
                + ConsoleColors.WHITE_BOLD_BRIGHT 
                + "copy" + ConsoleColors.RESET
                + ConsoleColors.YELLOW_BOLD_BRIGHT 
                + " | " + ConsoleColors.RESET 
                + ConsoleColors.WHITE_BOLD_BRIGHT
                + "cut" + ConsoleColors.RESET
                + ConsoleColors.YELLOW_BOLD_BRIGHT
                + " | " + ConsoleColors.RESET 
                + ConsoleColors.WHITE_BOLD_BRIGHT
                + "paste" + ConsoleColors.RESET 
                + " | " + ConsoleColors.RESET 
                + ConsoleColors.WHITE_BOLD_BRIGHT
                + "remove" + ConsoleColors.RESET + ConsoleColors.YELLOW_BOLD_BRIGHT 
                + " | " + ConsoleColors.RESET 
                + ConsoleColors.WHITE_BOLD_BRIGHT
                + "find" + ConsoleColors.RESET + ConsoleColors.YELLOW_BOLD_BRIGHT 
                + " | " + ConsoleColors.RESET 
                + ConsoleColors.WHITE_BOLD_BRIGHT
                + "visu" + ConsoleColors.RESET + ConsoleColors.YELLOW_BOLD_BRIGHT 
                + " | " + ConsoleColors.RESET 
                + ConsoleColors.WHITE_BOLD_BRIGHT
                + "+" + ConsoleColors.RESET + ConsoleColors.YELLOW_BOLD_BRIGHT 
                + " | " + ConsoleColors.RESET 
                + ConsoleColors.WHITE_BOLD_BRIGHT
                + "-" + ConsoleColors.RESET + ConsoleColors.YELLOW_BOLD_BRIGHT 
                + " | " + ConsoleColors.RESET 
                + ConsoleColors.WHITE_BOLD_BRIGHT
                + "." + ConsoleColors.RESET + ConsoleColors.YELLOW_BOLD_BRIGHT 
                + " | " + ConsoleColors.RESET 
                + ConsoleColors.WHITE_BOLD_BRIGHT
                + ".." + ConsoleColors.RESET + ConsoleColors.YELLOW_BOLD_BRIGHT 
                + " | " + ConsoleColors.RESET 
                + ConsoleColors.WHITE_BOLD_BRIGHT
                + "exit" + ConsoleColors.RESET
                + ConsoleColors.YELLOW_BOLD_BRIGHT;

  
}
