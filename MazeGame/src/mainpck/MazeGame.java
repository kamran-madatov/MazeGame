package mainpck;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//Kamran Madatov 
// MazeGame main file

public class MazeGame 
{
   public static void main(String[] args) throws Exception
   {
      JFrame frame = new JFrame();

      
      JPanel front= new JPanel();
      
      front.setLayout(new GridLayout(0,1));
      
      JButton startbutton= new JButton("Start Game");
      JLabel info=new JLabel("How to: Get the redpiece to the finish line using arrow keys or mouse select to move");
      JLabel  Intro1=new JLabel("Maze Game, KM");
      JLabel  Intro2=new JLabel("Maze Game developed by Kamran Madatov");
      frame.add(Intro1,BorderLayout.NORTH);
      front.add(Intro2);
      front.add(startbutton);
      front.add(info);
      
      front.setVisible(true);
      frame.add(front, BorderLayout.PAGE_START);
      front.setVisible(true);
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.setSize (800, 800);
      
      startbutton.addActionListener(new ActionListener() {  //Start the game when the button is pressed
	         public void actionPerformed(ActionEvent e) { 
	        	 MazeLayout scene1 = null;
				try {
					scene1 = new MazeLayout();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			
	             frame.add (scene1, BorderLayout.CENTER);
	        	 frame.remove(front);
	        	 frame.validate();
	        	 
	         }
	      });
      frame.setVisible (true);
   }
}
