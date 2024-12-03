package fr.uvsq.cprog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.fusesource.jansi.AnsiConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

/**
 * The {@code App} class represents a simple file explorer 
 * application. It allows users to navigate through directories,
 * perform various file operations (such as creating, copying, 
 * deleting files/directories), and visualize file contents.
 *
 * <p>The class offers methods to interact with the file system 
 * and execute user commands provided via a command-line
 * interface. It utilizes JLine for command-line interaction, 
 * ANSI console colors for formatting, and performs file
 * operations using utility classes such as {@link DirectoryFunction},
 *  {@link DeleteFunction}, {@link CsvReader},
 * {@link CopyPaste}, {@link VisuReader}, and {@link FileFunctions}.
 *
 * <p>Example usage:
 * <pre>
 *     App app = new App("root");
 *     app.run();
 * </pre>
 *
 */

public class App {
  public final File root;
  public String path;
  public Integer currentelement = -1;
  public boolean running = true;
  public String currentelempath;
  // To store the element marked for copying
  public File elementMarkedForCopy = null;
  public boolean cutflag;
  public ArrayList<Element> elementList = new ArrayList<Element>();

  /**
 * Constructs an instance of the {@code App} class with 
 * the specified path as the initial root directory.
 * It initializes the file explorer application with 
 * the given root directory path, creating the root directory
 * if it does not exist.
 *
 * @param path The path representing the root directory for the file explorer.
 *             If the directory doesn't exist, it will attempt to create it.
 */

  public App(String path) {
    try {
      Files.createDirectories(Paths.get(path));
    } catch (IOException e) {
      System.out.println("Error creating the root directory");
    }
    this.path = path;
    File currentDir = new File(this.path);
    this.root = currentDir.getParentFile();
  }

  void selectelement(ArrayList<String> strArrayList, Integer index) {
    this.currentelement = Integer.parseInt(strArrayList.get(index));
    this.currentelempath = this.path + File.separator 
      + this.elementList.get(this.currentelement).name;
    output(this.elementList.get(this.currentelement).note);
  }

  void fetchdata() {
    // Clear old data
    elementList.clear();
    // Creating a File object for directory
    File directoryPath = new File(this.path);
    File csvfile = new File(path + File.separator + "note.csv");
    // If note files doesn't exist we create one
    try {
      csvfile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // List of all files and directories
    String[] contents = directoryPath.list();
    for (int i = 0; i < contents.length; i++) {
      File file = new File(this.path + File.separator + contents[i]);
      if (!file.isDirectory()) {
        elementList.add(new Element(contents[i], i,
             CsvReader.searchCsvLine(this.path, 0, contents[i]),
                        FilenameUtils.getExtension(this.path + File.separator + contents[i])));
      } else {
        elementList
                      .add(new Element(contents[i], i, 
                      CsvReader.searchCsvLine(this.path, 0, contents[i]), "folder"));
      }
    }
  }

  void output(String message) {
    Functions.clearScreen();
    AnsiConsole.out().println(message);
    fetchdata();
    for (int i = 0; i < this.elementList.size(); i++) {
      if (i == this.currentelement) {
        AnsiConsole.out().println(ConsoleColors.WHITE_BACKGROUND 
                  + ConsoleColors.BLACK_BOLD_BRIGHT + "->"
                        + elementList.get(i) + ConsoleColors.RESET);
        continue;
      }
      AnsiConsole.out().println(elementList.get(i));
    }
  }

  void output() {
    Functions.clearScreen();
    fetchdata();
    for (int i = 0; i < this.elementList.size(); i++) {
      if (i == this.currentelement) {
        AnsiConsole.out().println(ConsoleColors.WHITE_BACKGROUND 
               + ConsoleColors.BLACK_BOLD_BRIGHT + "->"
                        + elementList.get(i) + ConsoleColors.RESET);
        continue;
      }
            
      AnsiConsole.out().println(elementList.get(i));
    }
  }

  void changedirectory() {

    File directoryPath = new File(path + File.separator 
           + elementList.get(this.currentelement).name);
    if (directoryPath.isDirectory()) {
      this.path = directoryPath.getPath();
      this.currentelement = -1;
      output();
    } else {
      output(ConsoleColors.RED_BACKGROUND + "Selected element is not a Directory" 
            + ConsoleColors.RESET);
    }

  }

  void goback() {
    File currentDir = new File(this.path);
    File parentDir = currentDir.getParentFile();

    if (parentDir != this.root && parentDir.isDirectory()) {
      this.path = parentDir.getPath();
      currentelement = -1;
      output();
    } else {
      output(ConsoleColors.YELLOW_BACKGROUND + "Cannot go back. Already in the root directory."
                    + ConsoleColors.RESET);
    }
  }

  void runcommand(ArrayList<String> strArrayList) {
    // System.out.println(strArrayList);

    for (int i = 0; i < strArrayList.size(); i++) {
      if (Functions.isInteger(strArrayList.get(i))
                    && Integer.parseInt(strArrayList.get(i)) < this.elementList.size()) {
        selectelement(strArrayList, i);
      } else {
        switch (strArrayList.get(i)) {

          case "mkdir":
            if (i + 1 < strArrayList.size()) {
              String message = DirectoryFunction.createdirectory(this.path, strArrayList.get(i 
                             + 1));
              output(message);
              i = i + 1;
            } else {
              output(ConsoleColors.YELLOW_BACKGROUND
                    + "Please put a name for the Folder you want to create" + ConsoleColors.RESET);
            }

            break;
          case "..":
            goback();
            break;
          case ".":
            if (currentelement != -1) {
              changedirectory();
            } else {
              output(ConsoleColors.YELLOW_BACKGROUND 
                      + "Please select The folder you wantto get into first"
                      + ConsoleColors.RESET);
            }

            break;

          case "remove":
            if (currentelement != -1) {
              String message = DeleteFunction.deleteSelectedElement(this.currentelempath);
              currentelement = -1;
              output(message);
            } else {
              output(ConsoleColors.YELLOW_BACKGROUND + "Please select an element first"
                      + ConsoleColors.RESET);
            }

            break;

          case "copy":
            if (currentelement != -1) {
              this.elementMarkedForCopy = CopyPaste.elementForCopy(this.currentelempath);
              output(ConsoleColors.GREEN_UNDERLINED + this.elementMarkedForCopy.getName()
                    + ConsoleColors.RESET + "  " + ConsoleColors.GREEN_BACKGROUND
                    + "This element has been marked for copy" + ConsoleColors.RESET);
            } else {
              output(ConsoleColors.YELLOW_BACKGROUND + "Please select an element first"
                      + ConsoleColors.RESET);
            }
            break;
          case "cut":
            if (currentelement != -1) {
              this.elementMarkedForCopy = CopyPaste.elementForCopy(this.currentelempath);
              cutflag = true;
            } else {
              output(ConsoleColors.YELLOW_BACKGROUND + "Please select an element first"
                      + ConsoleColors.RESET);
            }
            break;
          case "paste":
            System.out.println(cutflag);
            output(CopyPaste.pasteElement(this.path, this.elementMarkedForCopy, cutflag));
            cutflag = false;
            break;

          case "visu":
            if (currentelement != -1) {
              try {
                System.out.println(VisuReader.visualizeFileContent(this.currentelempath,
                                        elementList.get(this.currentelement).type));
              } catch (IOException e) {
                output("Somethign went wrong");
              }
            } else {
              output(ConsoleColors.YELLOW_BACKGROUND + "Please select an element first"
                      + ConsoleColors.RESET);
            }

            break;
          case "find":
            try {
              output(FileFunctions.findFunction(this.path, strArrayList.get(i + 1)));
              i++;
            } catch (Exception e) {
              output(ConsoleColors.YELLOW_BACKGROUND
                    + "Please spesify the File you want to find" + ConsoleColors.RESET);
            }
            break;
          case "-":
            if (currentelement != -1) {
              output(CsvReader.deleteCsvLine(this.path, 0, elementList.get(currentelement).name));
            } else {
              output("Select an element first");
            }
            
            break;
          case "+":
            if (currentelement != 1) {
              if (strArrayList.get(i + 1).contains("\"")) {
                i++;
                String s = String.join(" ", strArrayList.subList(i, strArrayList.size()));
                ArrayList<String> cutted = Functions.splitString(s, "\"");
                output(CsvReader.addCsvLine(this.path, elementList.get(currentelement).name,
                                    cutted.get(1)));
                strArrayList = new ArrayList<>(cutted.subList(2, cutted.size()));
                runcommand(Functions.trimArrayList(strArrayList));
              } else {
                System.out.println("Please put the text that you want "
                            + "to append between double quotes \"");
              }
            } else {
              output("Select an element first");
            }
            break;
          default:
            output(ConsoleColors.RED_BACKGROUND + "Unknow command" + ConsoleColors.RESET 
                  + ConsoleColors.AppMessage);
            strArrayList.clear();
        }

      }
    }
  }

  void run() {
    Functions.clearScreen(); 
    output(ConsoleColors.WHITE_BOLD_BRIGHT + " WELCOME TO THE APP\n" + ConsoleColors.RESET);
    System.err.println(ConsoleColors.AppMessage);
    List<String> commandList = Arrays.asList("mkdir", "cut", "visu", "exit",
                  "copy", "find", "paste", "remove");
    StringsCompleter completer = new StringsCompleter(commandList);

    try {
      Terminal terminal = TerminalBuilder.terminal();
      LineReader lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(completer)
                    .build();
      String userInput;
      while (this.running) {
        userInput = lineReader
                .readLine(ConsoleColors.WHITE_BOLD_BRIGHT + this.path + "$ " + ConsoleColors.RESET);
        ArrayList<String> strArrayList = Functions.splitString(userInput, " ");
        if (userInput.equalsIgnoreCase("exit")) {
          this.running = false;
        } else {
          runcommand(strArrayList);
        }
      }
    } catch (Exception e) {
      System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Exiting..." + ConsoleColors.RESET);
    }
  }

  /**
 * The main entry point for the file explorer application.
 * It initializes and starts the file explorer by creating an instance of the {@code App} class,
 * setting up the application environment, and launching the interactive user interface.
 *
 * @param args Command-line arguments (unused in this implementation).
 */
  public static void main(String[] args) {
    AnsiConsole.systemInstall();
    App app = new App("root");
    app.run();
    AnsiConsole.systemUninstall();

  }
}