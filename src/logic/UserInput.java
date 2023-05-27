package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.DecimalFormatSymbols;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

/**
 * This is an API utility for novice Java students to get simple user input in their programs.
 * It's intended as a simpler safer replacement for java.util.UserInput with one data item per line
 * and simple diagnose/retry handling for invalid user input
 * <BR>
 * <br> Each "get" method takes a String that is output to System.out as a prompt to the user.
 * <br> User input is parsed as tolerantly as possible. Invalid input is rejected and re-tried.
 * <br> After three consecutive failed tries, a runtime exception is thrown.
 * <br> An alternative constructor allows input to be read from a test data file
 * <br> Console input may be written to a test file for later reuse
 * <br> None of the methods in this class throws any checked exceptions. Unrecoverable errors
 * will cause a runtime exception, thus avoiding any necessity for exception handling
 *
 * @author DaniWeb members. This is free and unencumbered software released into the public domain.
 */
public class UserInput {

    /**
     * Just some test/demo code for this class
     *
     * @param args optional filename for reading user input.
     */
    public static void main(String[] args) {
        UserInput ui = (args.length == 0) ? new UserInput() : new UserInput(args[0]);

        do {
            System.out.println("Name is " + ui.nextLine("Enter name: "));
            System.out.println("Age is " + ui.nextInt("Enter age: "));
            System.out.println("Wage is " + ui.getDouble("Enter wage: "));
        } while (ui.isYes("Another?"));
        ui.setlogging(false);
        if (args.length == 0) {
            if (ui.isYes("Save input to file? ")) //  ui.writeInputToFile(ui.getString("Please enter filename: "));
            {
                ui.writeInputToFile();
            }
        }
    }

    private final BufferedReader br;
    private boolean echoInput = false;   // echo input only if we are reading from a file
    private boolean logInput = true;     // copy all input for optional data file
    // copy of all user input (unless logInput is turned off). Used to create optional data file...
    private List<String> userInput = new LinkedList<>();

    /**
     * Default constructor, user input will be taken from System.in
     */
    public UserInput() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Constructor, user input will be taken from a file
     * <BR> (Intended for running repeatable test cases)
     *
     * @param filename a file to read from or write to
     * @throws RuntimeException an unrecoverable error occurred
     */
    public UserInput(String filename) {
        File file = new File(filename);
        System.out.println("Reading from file " + file.getAbsolutePath());
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        echoInput = true;
    }

    /**
     * Asks the user to enter a string and returns the user's input
     *
     * @param prompt text to display to the user before input
     * @return the string entered by the user
     * @throws RuntimeException an unrecoverable error occurred
     */
    public String nextLine(String prompt) {
        System.out.print(prompt);
        String answer;
        try {
            answer = br.readLine();
            if (logInput) {
                userInput.add(answer); // logInput input
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        if (answer == null) {
            throw new RuntimeException("Unexpected end of input");
        }
        if (echoInput) {
            System.out.println(answer);
        }
        return answer;
    }

    public String nextLine() {
        String answer;
        try {
            answer = br.readLine();
            if (logInput) {
                userInput.add(answer); // logInput input
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        if (answer == null) {
            throw new RuntimeException("Unexpected end of input");
        }
        if (echoInput) {
            System.out.println(answer);
        }
        return answer;
    }

    /**
     * Asks the user to enter an integer number and returns the user's input
     * <br> handles locale-dependent thousands separators correctly
     * <br> allows three tries before raising an unrecoverable error
     *
     * @param prompt text to display to the user before input
     * @return the integer value entered by the user
     * @throws RuntimeException an unrecoverable error occurred
     */
    public int nextInt(String prompt) {
        int errorCount = 0;
        while (errorCount++ < 3) {  // no risk of an infinite loop
            String input = nextLine(prompt);
            input = input.trim(); // ignore leading and trailing spaces
            input = removeThousandsSeparators(input);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error! Invalid integer. Try again.");
            }
        }
        // three consecutive failures - time to give up.
        throw new RuntimeException("Repeated invalid user input");
    }
    public int nextInt() {
        int errorCount = 0;
        while (errorCount++ < 3) {  // no risk of an infinite loop
            String input = nextLine();
            input = input.trim(); // ignore leading and trailing spaces
            input = removeThousandsSeparators(input);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error! Invalid integer. Try again.");
            }
        }
        // three consecutive failures - time to give up.
        throw new RuntimeException("Repeated invalid user input");
    }

    /**
     * Asks the user to enter a double number and returns the user's input
     * <br> handles locale-dependent thousands separators correctly
     * <br> allows three tries before raising an unrecoverable error
     *
     * @param prompt text to display to the user before input
     * @return the double value entered by the user
     * @throws RuntimeException an unrecoverable error occurred
     */
    public double getDouble(String prompt) {
        int errorCount = 0;
        while (errorCount++ < 3) {  // no risk of an infinite loop
            String input = nextLine(prompt);
            input = input.trim(); // ignore leading and trailing spaces
            input = removeThousandsSeparators(input);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error! Invalid double. Try again.");
            }
        }
        // three consecutive failures - time to give up.
        throw new RuntimeException("Repeated invalid user input");
    }

    /**
     * Asks the user to enter a yes or no and returns true for yes, false for no
     * <br> allows three tries before raising an unrecoverable error
     *
     * @param prompt text to display to the user before input
     * @return true if user enters y or yes, false for n or no.
     * <BR> All other input is rejected/retried. Input is case-insensitive
     * @throws RuntimeException an unrecoverable error occurred
     */
    public boolean isYes(String prompt) {
        int errorCount = 0;
        while (errorCount++ < 3) {
            String input = nextLine(prompt).toLowerCase().trim();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Error! Answer yes or no");
        }
        throw new RuntimeException("Repeated invalid user input");
    }

    // utility to remove thousands separators before using parseInt or parseDouble
    private static final char G_S = DecimalFormatSymbols.getInstance().getGroupingSeparator();
    private static final char D_S = DecimalFormatSymbols.getInstance().getDecimalSeparator();
    private static final Pattern S_P = Pattern.compile("(\\d)" + G_S + "(\\d\\d\\d)");

    private String removeThousandsSeparators(String s) {
        // remove thousands separators (comma in US/UK, period in France etc)...
        // look for (digit) group separator (digit digit digit)
        // replace with (digit) (digit digit digit)

        // make sure that we do not strip any separators AFTER a decimal for doubles.
        // Note that this has no effect on any string without a decimal.
        int decimalIndex = s.indexOf(D_S);
        if (decimalIndex != -1) {
            return removeThousandsSeparators(s.substring(0, decimalIndex)) + s.substring(decimalIndex);
        }

        String str = S_P.matcher(s).replaceAll("$1$2");
        if (s.equals(str)) {
            return str; // no match.
        }
        return removeThousandsSeparators(str); // match.  But is it the only match?  Send it through again.
    }

    /**
     * Stop or re-start logging of user input
     * <BR> (Intended for running repeatable test cases)
     *
     * @param on if false, stop logInput. if true, continue logInput.
     * <BR> (Logging is on by default)
     */
    public void setlogging(boolean on) {
        logInput = on;
    }

    /**
     * Writes all input to a file for later use test cases.
     * <BR> (Intended for running repeatable test cases)
     *
     * @param fileName the name of the file to write to
     * (may include path information).
     * @throws RuntimeException an unrecoverable error occurred
     */
    public void writeInputToFile(String fileName) {
        writeInputToFile(new File(fileName));
    }

    /**
     * Shows a file save dialog and writes all input to the selected file for later use test cases.
     * <BR> NOTE: If run in an IDE such as NetBeans, the FileChooser Dialog
     * may open behind the NetBeans window, hidden, out of view, rather than in front of it.  
     * If this occurs, minimize the other windows in order to see the FileChooser Dialog.
     * @throws RuntimeException an unrecoverable error occurred
     */
    public void writeInputToFile() {
        JFileChooser fc = new JFileChooser();
        int returnCode = fc.showSaveDialog(null);
        if (returnCode == JFileChooser.APPROVE_OPTION) {
            writeInputToFile(fc.getSelectedFile());
        }
    }

    private void writeInputToFile(File file) {
        System.out.println("Writing input to file " + file.getAbsolutePath());
        try {
            Files.write(file.toPath(), userInput); // Java 8
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

    }

    /*  This version of writeInputToFile(File file) is compatible with Java versions before Java 8
     public void writeInputToFile(File file) {
     System.out.println("Writing input to file " + file.getAbsolutePath());
     try {
     java.io.PrintStream outputStream = new java.io.PrintStream(file);
     for (String s : userInput) {
     outputStream.println(s);
     }
     outputStream.close();
     } catch (FileNotFoundException ex) {
     ex.printStackTrace();
     throw new RuntimeException(ex);
     }
     }
     */
    
}