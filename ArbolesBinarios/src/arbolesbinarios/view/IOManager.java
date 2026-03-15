package arbolesbinarios.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import arbolesbinarios.model.List;
import arbolesbinarios.model.ListNode;

/**
 * IOManager handles all console input and output for this application.
 *
 * <p>This class follows the view design used in class (see professor's
 * {@code IOManager.java}): a single {@code BufferedReader} is shared
 * across all instances, and each method either prints to
 * {@code System.out} or reads a line from standard input.</p>
 *
 * <p>Input methods declare {@code throws IOException} so that the
 * calling controller decides how to handle read errors.</p>
 *
 * <p>This class contains no business logic — it only formats and
 * displays data that the controller passes to it.</p>
 */
public class IOManager {

    /** Shared reader for standard input, following the professor's pattern. */
    private static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    /**
     * Prints any message to the console followed by a newline.
     *
     * @param message the text to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a prompt and returns the line typed by the user.
     *
     * @param message the prompt to show before reading
     * @return the trimmed line entered by the user
     * @throws IOException if the input stream cannot be read
     */
    public String getString(String message) throws IOException {
        System.out.println(message);
        return br.readLine();
    }

    /**
     * Prints a prompt and returns the integer typed by the user.
     *
     * @param message the prompt to show before reading
     * @return the integer entered by the user
     * @throws IOException if the input stream cannot be read
     */
    public int getInt(String message) throws IOException {
        System.out.println(message);
        return Integer.parseInt(br.readLine());
    }

    /**
     * Displays all values in a {@link List}, followed by the total count.
     *
     * <p>Example output:
     * <pre>
     *   Preorder: 24, 27, 32, 4, 3, 6, 5, 12, 1, 8, 2  (11 nodes)
     * </pre>
     * </p>
     *
     * @param label the heading to print before the list values
     * @param list  the List whose values will be displayed
     */
    public void showList(String label, List list) {
        System.out.print(label + " ");
        ListNode current = list.getFirst();
        while (current != null) {
            System.out.print(current.getValue());
            if (current.getNext() != null) {
                System.out.print(", ");
            }
            current = current.getNext();
        }
        System.out.println("  (" + list.size() + " nodes)");
    }

    /**
     * Displays a label followed by an integer value on one line.
     * Used to show the tree height.
     *
     * @param label the heading to print before the integer
     * @param value the integer value to display
     */
    public void showInt(String label, int value) {
        System.out.println(label + " " + value);
    }
}
