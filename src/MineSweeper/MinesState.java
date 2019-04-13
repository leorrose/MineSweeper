package MineSweeper;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * class that represents a JLabel of number of mines in grid
 */
public class MinesState extends JPanel{
	private static final long serialVersionUID = 1L;
	int numOfMines =0;
	JLabel State;
	
	/**
	 * MinesState Ctor
	 * @param int numOfMines for Grid number Mines
	 */
	public MinesState(int numOfMines) {
		this.numOfMines = numOfMines;
		this.State = new JLabel("Mines Left : "+this.numOfMines);
		this.State.setForeground (Color.WHITE);
		this.State.setFont(new Font("TimesRoman", Font.ITALIC, 50));
		this.add(new JLabel(""));
		this.add(State);
		this.add(new JLabel(""));
		this.setBackground(Color.BLACK);
	}

	/**
	 * function to set number of Mines
	 * @param int numOfMines for Grid number Mines
	 */
	public void SetNumOfMines(int numOfMines) {
		this.numOfMines= numOfMines;
	}
	/**
	 * function to set Mines state of Grid
	 * @param int numOfMines for Grid number Mines
	 */
	public void setState(int numOfMines) {
		this.SetNumOfMines(numOfMines);
		State.setText("Mines Left : "+this.numOfMines);
	}
}
