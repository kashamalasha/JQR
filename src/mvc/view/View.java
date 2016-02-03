package mvc.view;

import javax.swing.*;
import java.io.IOException;

public class View extends JFrame {
    //Attributes
    //Constructor
    //Methods
    /**
     * The main menu
     */
    public void showMainMenu(){
        this.clearScreen();
        System.out.println();
        System.out.println("  <=         Main menu         =>  ");
        System.out.println("  -------------------------------  ");
        System.out.println();
        System.out.println("  1 = Insert text to code ");
        System.out.println("  0 = Exit ");
        System.out.println();
        System.out.println("  -------------------------------  ");
          System.out.print("  Command => ");
    }

    public void showDataAsk() {
        this.clearScreen();
        System.out.println();
        System.out.println("  <=   Enter the data to code  =>  ");
        System.out.println("  -------------------------------  ");
        System.out.println();
          System.out.print("  Command => ");
    }

    public void showTheResult(String path) {
        JFrame frame = new JFrame();
        ImageIcon image = new ImageIcon(path);
        JLabel imageLabel = new JLabel(image);

        frame.add(imageLabel);
        frame.setBounds(100, 100, 100, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
     /**
     * Clear the terminal
     * WINDOWS: cmd -> cls
     * UNIX: ANSI ESCAPE SYMBOLS:
     *   ANSI_CLS = "\u001b[2J"
     *   ANSI_HOME = "\u001b[H"
     */
    private void clearScreen() {
        final String OS = System.getProperty("os.name");
        if (OS.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.print("\u001b[2J\u001b[H");
            System.out.flush();
        }
    }
}
