import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.util.Random;

public class SlidingPuzzle extends JApplet implements MouseListener {
	
	private final static int numCols = 3;
	private int numPieces = numCols*numCols;
	
	private PuzzlePiece[][] puzzle = new PuzzlePiece[numCols][numCols];
	private JLabel jlblStatus = new JLabel("Click a number next to the blank piece to move it");
	
	public SlidingPuzzle() {
		// panel to hold puzzle pieces
		JPanel p = new JPanel(new GridLayout(numCols,numCols,0,0));
		
		int count = 0;
		
		for (int i=0; i<numCols; i++) {
			for (int j=0; j<numCols; j++) {
				p.add(puzzle[i][j] = new PuzzlePiece(count));
				
				count++;
			}
		}
		
		p.setBorder(new LineBorder(Color.pink, 5));
		
		this.getContentPane().add(p, BorderLayout.CENTER);
		this.getContentPane().add(jlblStatus, BorderLayout.SOUTH);
		
		this.shufflePuzzle();
		
		addMouseListener(this);
	
		
	}
	
	public boolean isSolved() {
		int count = 1;
		
		for (int i=0; i<numCols; i++) {
			for (int j=0; j<numCols; j++) {
				if (count!=0 && puzzle[i][j].getNum() == count) {
					count++; 
				} else if(count==numPieces && puzzle[numCols-1][numCols-1].getNum() == 0) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	public void shufflePuzzle() {
		Random rand = new Random();
		int count = 0;
		
		for (int i=0; i<numCols; i++) {
			for (int j=0; j<numCols; j++) {
				// selects random number from remaining slots and swaps current puzzle piece with that one
				int randNum = rand.nextInt(((numPieces-1)-count) + 1 ) + count;
				
				int k = randNum/numCols;
				int l = randNum%numCols;
				
				switchPuzzlePieces(i, j, k, l);
				
				count++;
			}
		}
	}
	
	public void switchPuzzlePieces(int a, int b, int x, int y) {
		int temp = puzzle[a][b].getNum();
		puzzle[a][b].setNum(puzzle[x][y].getNum());
		puzzle[x][y].setNum(temp);
		
	}
	
	public boolean trySwitch(int x, int y) {
		
		if( y+1 < numCols && puzzle[x][y+1].getNum() == 0) {
			 switchPuzzlePieces(x, y, x, y+1);
			 return true;
		} else if( x+1 < numCols && puzzle[x+1][y].getNum() == 0) {
			 switchPuzzlePieces(x, y, x+1, y);
			 return true;
		} else if( x-1 >= 0 && puzzle[x-1][y].getNum() == 0) {
			 switchPuzzlePieces(x, y, x-1, y);
			 return true;
		} else if( y-1 >= 0 && puzzle[x][y-1].getNum() == 0) {
			 switchPuzzlePieces(x, y, x, y-1);
			 return true;
		} else {
			return false;
		}
	}
	
	/**
	 * get click information
	 * check if puzzle is solved, if not:
	 * if the piece clicked is next to empty/0 piece, move it
	 */
	public void mouseClicked(MouseEvent e) { 
		// get width and height of puzzle pieces in case user has adjusted it
		int width = getWidth()/numCols;
		int height = getHeight()/numCols;
		
		// determine which cell the user has clicked
		int col = e.getX()/width;
		int row = e.getY()/height;
		
		// check if puzzle is solved, if not, keep going, else kill
		if(!isSolved()) {
			trySwitch(row, col);
		} else {
			jlblStatus.setText("You did it! The game is over.");
			//System.out.println("You solved it!");
		}
	}

	
	public void mousePressed(MouseEvent e) { }
	
	public void mouseReleased(MouseEvent e) { }
	
	public void mouseEntered(MouseEvent e) { }
	
	public void mouseExited(MouseEvent e) { }
}

