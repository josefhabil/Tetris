package se.liu.ida.josha248.tdde30.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.AbstractMap;
import java.util.EnumMap;

public class TetrisComponent extends JComponent implements BoardListener {

    /**
     * Constants for the sizes of the squares in the GUI and the margin between squares.
     */
    public static final int SQUARE_LENGTH = 30;
    private final static int MARGIN = 4;

    private Board board;
    private AbstractMap<SquareType,Color> colorMap;

    public TetrisComponent(Board board) {
        colorMap = new EnumMap<>(SquareType.class);
        colorMap.put(SquareType.I, Color.CYAN);
        colorMap.put(SquareType.J, Color.BLUE);
        colorMap.put(SquareType.L, Color.ORANGE);
        colorMap.put(SquareType.O, Color.YELLOW);
        colorMap.put(SquareType.S, Color.GREEN);
        colorMap.put(SquareType.T, Color.MAGENTA);
        colorMap.put(SquareType.Z, Color.RED);
        colorMap.put(SquareType.EMPTY, Color.darkGray);
        this.board = board;
        this.setKeyBindings();
    }

    public Dimension getPreferredSize() {
        return new Dimension(board.getWidth() * SQUARE_LENGTH + MARGIN, board.getHeight() * SQUARE_LENGTH + MARGIN);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        Poly poly = board.getFalling();
        g2d.setColor(new Color(SQUARE_LENGTH, SQUARE_LENGTH, SQUARE_LENGTH));
        g2d.fillRect(0, 0, board.getWidth() * SQUARE_LENGTH + MARGIN, board.getHeight() * SQUARE_LENGTH + MARGIN);
        for(int x=0; x < board.getHeight();x++){
            for(int y=0; y < board.getWidth(); y++){
                if (onFallingPoly(x,y,board,poly) ){
                    int xPos = x -board.getFallingX();
                    int yPos = y- board.getFallingY();
                    g2d.setColor(colorMap.get(poly.getSquare(xPos, yPos)));
                }else{
                    g2d.setColor(colorMap.get(board.getSquare(x, y)));
                }
                g2d.fillRect(y * SQUARE_LENGTH + MARGIN,x*SQUARE_LENGTH + MARGIN,SQUARE_LENGTH - MARGIN,SQUARE_LENGTH - MARGIN);
            }
        }
    }

    public static boolean onFallingPoly(int row, int column, Board board, Poly poly){
        int xPos = row - board.getFallingX();
        int yPos = column - board.getFallingY();
        return ((xPos >= 0) && (xPos < poly.getPolys().length) &&
                (yPos >= 0) && yPos < poly.getPolys().length && poly.getSquare(xPos, yPos) != SquareType.EMPTY);
    }

    @Override
    public void boardChanged() {
        this.repaint();
    }

    private Action moveRight = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.movePoly(Move.RIGHT);
        }
    };

    private Action moveLeft = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.movePoly(Move.LEFT);
        }
    };

    private Action descend = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.movePoly(Move.DOWN);
        }
    };

    private Action rotateRight = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e){
            board.rotate(true);
        }
    };

    public void setKeyBindings() {
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"moveLeft");
        this.getActionMap().put("moveLeft", moveLeft);

        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"moveRight");
        this.getActionMap().put("moveRight", moveRight);

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "rotateRight");
        this.getActionMap().put("rotateRight",rotateRight);

        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "descend");
        this.getActionMap().put("descend",descend);

    }
}
