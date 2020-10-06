package mainpck;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class PlayerGamePiece extends GamePiece  //Moving piece
{
	public PlayerGamePiece (int row, int col) {
		super (row, col);
	}

	public PlayerGamePiece (Position pos) {
		this (pos.r, pos.c);
	}
	
	protected boolean validNonJump (int dr, int dc, GameSquares squares)
	{
		if ((dr == -2) && ( dc == 5 ))
		{
			if(pos.r+dr==27 && pos.c+dc==29){
			if (squares.getSquare(pos.r + dr, pos.c + dc).getPiece() == null)
				if (squares.getSquare(pos.r + dr, pos.c + dc).getSquareColor()==Color.WHITE)
				return true;
		}
		}
		if ((dr == -1) && ( dc == 0 ))
		{
			if (squares.getSquare(pos.r + dr, pos.c + dc).getPiece() == null)
				if (squares.getSquare(pos.r + dr, pos.c + dc).getSquareColor()==Color.WHITE)
				return true;
		}
		if ((dr == 1) && ( dc == 0) )
		{
			if (squares.getSquare(pos.r + dr, pos.c + dc).getPiece() == null)
				if (squares.getSquare(pos.r + dr, pos.c + dc).getSquareColor()==Color.WHITE)
				return true;
		}
		
		if ((dr == 0) && ( dc == -1 ))
		{
			if (squares.getSquare(pos.r + dr, pos.c + dc).getPiece() == null)
				if (squares.getSquare(pos.r + dr, pos.c + dc).getSquareColor()==Color.WHITE)
					return true;
		}
		if ((dr == 0) && ( dc == 1) )
		{
			if (squares.getSquare(pos.r + dr, pos.c + dc).getPiece() == null)
				if (squares.getSquare(pos.r + dr, pos.c + dc).getSquareColor()==Color.WHITE)
				return true;
		}
		return false;
	}
	
	protected boolean validJump (int dr, int dc, GameSquares squares)
	{		
		return false;
	}
	
	public void drawPiece (Graphics2D g2, int x, int y, int width, int height)
	{
		Ellipse2D.Double outline = new Ellipse2D.Double (x + width * DIST_FROM_EDGE + LINE_WIDTH / 2,
														 y + height * DIST_FROM_EDGE + LINE_WIDTH / 2,
														 width * (1 - 2 * DIST_FROM_EDGE) - LINE_WIDTH,
														 height * (1 - 2 * DIST_FROM_EDGE) - LINE_WIDTH);

		g2.setColor (Color.RED);

		BasicStroke stroke = new BasicStroke (LINE_WIDTH);
		g2.setStroke(stroke);
		g2.fill (outline);

		if (selected)
		{
			g2.setColor (Color.GREEN);
			g2.draw (outline);
		}

	}
}