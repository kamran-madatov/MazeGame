package mainpck;
import java.awt.Graphics2D;


public abstract class GamePiece implements PlayableGamePiece 
{
	protected final static double DIST_FROM_EDGE = 0.1;
	protected final static int LINE_WIDTH = 5;

	protected boolean selected = false;
	protected Position pos = null;
	

	public GamePiece (int row, int col) {
		pos = new Position (row, col);
		
	}

	public GamePiece (Position pos) {
		this (pos.r, pos.c);
	}

	public Position getPosition () {
		return pos;
	}

	public void setPosition (Position pos) {
		pos = new Position (pos);
	}
	


	public void setPieceSelected (boolean b)
	{
		if ((b == true) )
			selected = true;
		else
			selected = false;
	}

	public boolean isPieceSelected() {
		return selected;
	}

	public boolean validMove (int targetRow, int targetCol, GameSquares squares) {
		return validMove (new Position (targetRow, targetCol), squares);
	}
	
	public boolean validMove (Position targetPos, GameSquares squares)
	{
		Position dp = targetPos.offset (pos);
		int dr = dp.r;
		int dc = dp.c;
		
		if (validNonJump (dr, dc, squares))
			return true;
		else if (validJump (dr, dc, squares))
			return true;
				
		return false;
	}
	
	protected abstract boolean validNonJump (int dr, int dc, GameSquares squares);	
	
	protected abstract boolean validJump (int dr, int dc, GameSquares squares);
	
	public void move (Position newPos, GameSquares squares)
	{
		Position dp = newPos.offset (pos);
		int dr = dp.r;
		int dc = dp.c;
		
		if (validNonJump (dr, dc, squares))
			pos = new Position (newPos);
		else if (validJump (dr, dc, squares))
			pos = new Position (newPos);

	}
	
	public abstract void drawPiece (Graphics2D g2, int x, int y, int width, int height);
}
