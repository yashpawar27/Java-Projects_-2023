
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class KnightsTour {

    private final int ROWS = 8;
    private final int COLS = 8;
    private int startRow   = 0;
    private int startCol   = 0;

    private int[][] board;
    private KnightsTourGUI GUI;
    private long numMoves;
    private boolean displayGUI;

    public KnightsTour(boolean displayGUI) {
        this.displayGUI = displayGUI;
        if (displayGUI) {
            GUI = new KnightsTourGUI();
            GUI.createGUI(ROWS, COLS);
        }
    }

    public void runTour() {

        board = new int[ROWS][COLS];
        numMoves = 0;
        System.out.println("\nStarting row: " + startRow + "\t" + "col: " + startCol);

        long start = System.nanoTime();
        boolean success = tour(startRow, startCol, 1, false);
        long time = System.nanoTime() - start;

        if (success) {
            System.out.printf("Moves = %,d\n", numMoves);
        } else {
            System.out.println("Tour not found.");
        }
        System.out.printf("Time = %.4f seconds.\n", time / 1_000_000_000.0);
    }

    private boolean tour(int row, int col, int count, boolean success) {

        numMoves++;
        board[row][col] = count;
        if (displayGUI) {
            GUI.button[row * COLS + col].setText("" + count);
        }

	// Your code goes here











        if (!success) { // back up to previous square
            board[row][col] = 0;
            if (displayGUI) {
                GUI.button[row * COLS + col].setText("");
            }
        }

        return success;
    }

    
    class KnightsTourGUI {

        private JFrame frame;
        protected JButton[] button;

        public final void createGUI(int nRows, int nCols) {
            frame = new JFrame("Knight's Tour");
            button = new JButton[nRows * nCols];
            for (int i = 0; i < button.length; i++) {
                button[i] = new JButton("");
                button[i].setFont(new Font("Arial", Font.BOLD, 22));
                button[i].setBackground(new Color(255, 200, 175));
                if ((i / nCols % 2 == 0 && i % 2 == 0) || (i / nCols % 2 == 1 && i % 2 == 1)) {
                    button[i].setBackground(new Color(150, 150, 150));
                }
                frame.add(button[i]);
            }
            frame.setLayout(new GridLayout(nRows, nCols));
            frame.setSize(nCols * 60 + 45, nRows * 60 + 45);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
    }
}

