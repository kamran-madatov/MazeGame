package mainpck;
import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;



public class MazeLayout  extends JPanel implements ActionListener, KeyListener 
{
	protected final static int SQUARES_1D = 31;
	private GameSquares squares;
	private GamePiece selectedPiece = null;
	private GamePiece selectedPiece1 = null;
	private int numPieceSpots=0;
	
	protected Position pos1;
	protected Position pos3;
	protected Position pos4;
	public MazeLayout() throws Exception
	{
		super ();
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		setLayout(new GridBagLayout ());
		squares = new GameSquares();
		setSize(1500,1600);
		
		GridBagConstraints col = new GridBagConstraints();
		
		        String excelFilePath = "Mazes.xlsx";
		        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));  //read excel file
		         
		        Workbook workbook = new XSSFWorkbook(inputStream);   
		        Sheet firstSheet = workbook.getSheetAt(0); //workbook function 
		        Iterator<Row> iterator = firstSheet.iterator(); 
		         
		        while (iterator.hasNext()) {
		            Row nextRow = iterator.next();
		            Iterator<Cell> cellIterator = nextRow.cellIterator();
		             
		            while (cellIterator.hasNext()) {
		                Cell cell = cellIterator.next();		                 
		                switch (cell.getCellType()) {  // create the maze from the excel file
		                case Cell.CELL_TYPE_STRING:
		                        String excelf=cell.getStringCellValue();
		                        int r=cell.getRowIndex();
		                        int minus=cell.getColumnIndex();
		                        String a="a";
		                        String b="b";
		                        if(excelf.equals(a)){
		                        	
		                        	squares.setSquare (new GameSquare (r, minus, Color.GREEN));
		            				col.gridx = minus;
		            				col.gridy = r;
		            				col.ipadx=20;
		            				col.ipady=20;	
		            				add (squares.getSquare(r, minus), col);
		                        }
		                        
		                        if(excelf.equals(b)){
		                        	squares.setSquare (new GameSquare (r, minus, Color.WHITE));
		            				final GameSquare sq = squares.getSquare(r, minus);
		            				sq.addMouseListener (new GameSquareMouseListener (sq));
		                        	
		            				if (minus==0 && r==1){
		            					squares.getSquare(r, minus).setPiece (new PlayerGamePiece (r, minus));
		            					pos1=squares.getSquare(r, minus).getPosition();
		            				}
		            				if (minus==10 && r==17){
		            					squares.getSquare(r, minus).setPiece (new BlockerGamePiece (r, minus));
		            					pos4=squares.getSquare(r, minus).getPosition();
		            				
		            				}
		            				
		            				if (minus==16 && r==4){
		            					squares.getSquare(r, minus).setPiece (new BlockerGamePiece (r, minus));
		            					pos3=squares.getSquare(r, minus).getPosition();
		            					
		            				}
		            				
		            				numPieceSpots++;
		            				col.ipadx=20;
		            				col.ipady=20;
		            				col.gridx = minus;
		            				col.gridy = r;
		            				add (squares.getSquare(r, minus), col);
		                        }
		                }
		               
		            }
		            
		        }
		         
		        
		        
		        repaint();
	
		  final int DELAY = 2000;           //Blocker movements
		  Timer ta = new Timer(DELAY, new
			         ActionListener()
		  {         
			            public void actionPerformed(ActionEvent event)
			            {
			            	
			            	
			            	if (pos3.r!=SQUARES_1D-1){
			            		if (pos3.r==6){
		            				pos3.r=4;
		            				if(squares.getSquare(pos3).getPiece()==null){
		            					pos3.r=6;
		            					squares.getSquare(pos3).setPiece (null);
		            					pos3.r=4;
		            					squares.getSquare(pos3.r, pos3.c).setPiece (new BlockerGamePiece (pos3));}
		            			
		            				else
		            				
		            					pos3.r=6;
	
		            			}
			            		else{
			            		GameSquare selSq = squares.getSquare (pos3);
				            	selectedPiece1 = selSq.getPiece();
			            		pos3.r+=1;	
		            		
			            		if (selectedPiece1.validMove (pos3, squares)){
			            			GameSquare selSq1 = squares.getSquare (pos3);
			            			selectedPiece1.move (pos3, squares);	
			            			selSq1.setPiece (selectedPiece1);			// new square containing selected piece
			            			selSq.setPiece (null);
			            			
			            		}
			            		else{
			        				pos3.r-=1;
			        			}
			            		
			            	}
			            }
			            	repaint();
			         }
		});
		  
		  final int DELAY1 = 1500;                   //Blocker2 movements
		  Timer tag = new Timer(DELAY1, new
			         ActionListener()                
		  {         
			            public void actionPerformed(ActionEvent event)
			            {
			            	
			            	if (pos4.c!=SQUARES_1D-1){
			            		if (pos4.c==12){
		            				pos4.c=10;
		            				if(squares.getSquare(pos4).getPiece()==null){
		            					pos4.c=12;
		            					squares.getSquare(pos4).setPiece (null);
		            					pos4.c=10;
		            					squares.getSquare(pos4.r, pos4.c).setPiece (new BlockerGamePiece (pos4));}
		            			
		            				else
		            				
		            					pos4.c=12;
	
		            			}
			            		else{
			            		GameSquare selSq = squares.getSquare (pos4);
				            	selectedPiece1 = selSq.getPiece();
			            		pos4.c+=1;	
		            		
			            		if (selectedPiece1.validMove (pos4, squares)){
			            			GameSquare selSq1 = squares.getSquare (pos4);
			            			selectedPiece1.move (pos4, squares);	
			            			selSq1.setPiece (selectedPiece1);			// new square containing selected piece
			            			selSq.setPiece (null);
			            		}
			            		else{
			        				pos4.c-=1;
			        			}
			            		
			            	}
			            }
			            	repaint();
			         }
		});  
		  
		  ta.start();  //start the times on the path blockers
		  tag.start();
		  
	}
	
	public void keyPressed (KeyEvent e) { //key movement
        int c = e.getKeyCode ();
        GameSquare selSq = squares.getSquare (pos1);
    	selectedPiece = selSq.getPiece();
        if (c==KeyEvent.VK_UP) {   
        	
        	if (pos1.r!=0){
        		pos1.r-=1;
        		if (selectedPiece.validMove (pos1, squares)){
        			GameSquare selSq1 = squares.getSquare (pos1);
        			selectedPiece.move (pos1, squares);	
        			selSq1.setPiece (selectedPiece);			// new square containing selected piece
        			selSq.setPiece (null);
        		}
        			else{
        				pos1.r+=1;
        			}
        	}
        	
        	
        	 
        	 
        } else if(c==KeyEvent.VK_DOWN) {  
        	
        	if (pos1.r!=SQUARES_1D-1){
        		pos1.r+=1;
        		if (selectedPiece.validMove (pos1, squares)){
        			GameSquare selSq1 = squares.getSquare (pos1);
        			selectedPiece.move (pos1, squares);	
        			selSq1.setPiece (selectedPiece);			// new square containing selected piece
        			selSq.setPiece (null);
        		}
        			else{
        				pos1.r-=1;
        			}
        	}
        			
        				
        		
        	
        } else if(c==KeyEvent.VK_LEFT) {   
        	
        	if (pos1.c!=0){
        		pos1.c-=1;
        		if (selectedPiece.validMove (pos1, squares)){
        			GameSquare selSq1 = squares.getSquare (pos1);
        			selectedPiece.move (pos1, squares);	
        			selSq1.setPiece (selectedPiece);			// new square containing selected piece
        			selSq.setPiece (null);
        		}
        			else{
        				pos1.c+=1;
        			}
        	}
        			
        } else if(c==KeyEvent.VK_RIGHT) {    
        	
        	if (pos1.c!=SQUARES_1D-1){
        		pos1.c+=1;
        		if (selectedPiece.validMove (pos1, squares)){
        			GameSquare selSq1 = squares.getSquare (pos1);
        			selectedPiece.move (pos1, squares);	
        			selSq1.setPiece (selectedPiece);			// new square containing selected piece
        			selSq.setPiece (null);
        		}
        			else{
        				pos1.c-=1;
        			}
        	}
        			
        }
        
        if (pos1.r==29 && pos1.c==24)  //fallen into the black hole
		{  
        	selSq = squares.getSquare (pos1);
        	selectedPiece = selSq.getPiece();
        	pos1.r=27;
        	pos1.c=29;
        	if (selectedPiece.validMove (pos1, squares)){
    			GameSquare selSq1 = squares.getSquare (pos1);
    			selectedPiece.move (pos1, squares);	
    			selSq1.setPiece (selectedPiece);			// new square containing selected piece
    			selSq.setPiece (null);
    			revalidate();
    		}
		}
        
		if (pos1.r==29 && pos1.c==30)
			{  
			int confirm = JOptionPane.showOptionDialog(null,
					 "You have won, press OK to close ",
                    "Exit Confirmation", JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.PLAIN_MESSAGE, null, null, null);
            if (confirm != JOptionPane.PLAIN_MESSAGE) {
                System.exit(0);
            	}
            
            
			}
        repaint();
        
    }
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	
	public class GameSquareMouseListener extends MouseAdapter //mouselistner if you need to use it
	{
		GameSquare sq;

		public GameSquareMouseListener (GameSquare sq) {
			this.sq = sq;
		}

		public void mousePressed (MouseEvent event)
		{
			Point mousePoint = event.getPoint();

			if (sq.contains (mousePoint))
			{
				if (selectedPiece == null)
				{
					if (sq.getPiece() != null)
					{
						selectedPiece = sq.getPiece();
						sq.getPiece().setPieceSelected (true);
					}
				}
				else
				{
					Position pos = sq.getPosition();
					GameSquare selSq = squares.getSquare (selectedPiece.getPosition());

					if (selectedPiece.getPosition().equals (sq.getPosition()))
					{
						selectedPiece.setPieceSelected(false);
						selectedPiece = null;
					}
					else if (selectedPiece.validMove (pos, squares))
					{
						selectedPiece.move (pos, squares);		// move selected piece
						sq.setPiece (selectedPiece);			// new square containing selected piece
						pos1=pos;
						selSq.setPiece (null);					// square that used to contain selected piece
						if (pos1.r==29 && pos1.c==30)
						{  
						int confirm = JOptionPane.showOptionDialog(null,   
								 "You have won, press OK to close ",
			                    "Exit Confirmation", JOptionPane.PLAIN_MESSAGE,
			                    JOptionPane.PLAIN_MESSAGE, null, null, null);
			            if (confirm != JOptionPane.PLAIN_MESSAGE) {
			                System.exit(0);
			            }
			            
			            
						}
					
					}
				}
			}
			
			repaint();
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}