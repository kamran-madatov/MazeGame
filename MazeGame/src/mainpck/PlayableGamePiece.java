package mainpck;

public interface PlayableGamePiece
{
	public boolean validMove (int dr, int dc, GameSquares squares);
	public boolean validMove (Position dp, GameSquares squares);
	
	public void setPieceSelected (boolean b);
	public boolean isPieceSelected ();
}
