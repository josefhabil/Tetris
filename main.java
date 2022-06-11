package se.liu.ida.josha248.tdde30.tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

public final class BoardTest {

    public static void main(String[] args) {
        final Board board = new Board(20, 10);
        new TetrisViewer(board);
        /**
         * Game timer
         */
        final Action doGameStep = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                board.tick();
            }
        };
        final Timer clockTimer = new Timer(700, doGameStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();
    }

}
