package se.liu.ida.josha248.tdde30.tetris;

public enum Move {

    DOWN(1,0), RIGHT(0,1), LEFT(0,-1);

    /**
     * X coordinate , when poly falls down, fallingX will increment with 1
     */
    public final int deltaX;

    /**
     * Y coordinate, that represents sideways movement, when Poly is told to fall DOWN,
     * fallingY will increment with 0
     */
    public final int deltaY;


    Move(final int deltaX, final int deltaY) {
	this.deltaX = deltaX;
	this.deltaY = deltaY;
    }
}