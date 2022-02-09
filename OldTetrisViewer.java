package se.liu.ida.josha248.tdde30.tetris;

import javax.swing.*;
import java.awt.*;

public class OldTetrisViewer extends JFrame {

    private Board board = null;
    private JTextArea textArea;
    private JFrame frame = new JFrame("Tetris");

    public OldTetrisViewer(Board board) {
        this.textArea = new JTextArea(board.getWidth(), board.getHeight());
    }

    public void showWindow() {
        textArea.setText(BoardToTextConverter.convertToText(board));
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(textArea, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

}
