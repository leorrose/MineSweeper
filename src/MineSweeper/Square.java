package MineSweeper;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Class that represents a Square in GameGrid
 */
public class Square extends JButton{
	private static final long serialVersionUID = 1L;
	private int CellValue=0,CellRow,CellColumn;
	private boolean BeenPressed=false,Checked=false;
	private BufferedImage Mine = null;
	private BufferedImage Flag = null;
	
	/**
	 * Square Ctor
	 * @param int row,column for Square row location, int val for Square value
	 */
	public Square(int val,int row,int column) {
		this.CellValue =val;
		this.CellRow = row;
		this.CellColumn = column;
	    try
	    {
	    	this.Mine = ImageIO.read(new File("Mine.Png"));
	    	this.Flag = ImageIO.read(new File("Flag.Png"));
	    }
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	/* Setters */
	/**
	 * function to set Square value
	 * @param int val for Square value
	 */
	public void setVal(int val) {
		this.CellValue=val;
	}

	/**
	 * function to set Square state, if been pressed or not
	 * @param Boolean state for Squares state
	 */
	public void setPresed(boolean state) {
		this.BeenPressed = state;
	}
	
	/* Getters */
	/**
	 * function to get Square Column
	 * @return int Squares Column
	 */
	public int getColumn() {
		return CellColumn;
	}
	/**
	 * function to get Square Presed state if been pressed or not
	 * @return boolean Squares Pressed
	 */
	public boolean getPresed() {
		return BeenPressed;
	}
	/**
	 * function to get Square Row
	 * @return int Squares Row
	 */
	public int getRow() {
		return CellRow;
	}
	/**
	 * function to get Square Value
	 * @return int Squares Value
	 */
	public int getVal() {
		return this.CellValue;
	}
	/**
	 * function to get Square Checked, if been checked by flag or not
	 * @return boolean Squares Checked
	 */
	public boolean getChecked() {
		return this.Checked;
	}
	
	/**
	 * action of Square been clicked
	 * @return true if square us mine else false
	 */
	public Boolean ClickOnButton() {
		this.setBackground(Color.lightGray);
		if (this.getVal()==-1) {
			this.setIcon(new ImageIcon(this.Mine.getScaledInstance(30, 30, 30)));
			JOptionPane.showMessageDialog(null,"You Lost");
			return true;
		}
		else if(this.getVal()!=0)
			this.setText(this.getVal()+"");
		return false;
	}
	
	/**
	 * function to add a flag on square
	 */
	public void AddFlag() {
		if (this.Checked==false) {
			this.Checked=true;
			this.setIcon(new ImageIcon(this.Flag.getScaledInstance(30, 30, 30)));
		}
		else {
			this.Checked=false;
			this.setIcon(null);
		}
	}
	
	/**
	 * function to open a Square
	 */
	public void ShowCell() {
		this.setBackground(Color.lightGray);
		if(this.CellValue==-1) {
			this.setIcon(new ImageIcon(this.Mine.getScaledInstance(30, 30, 30)));
		}
		else {
			this.setIcon(null);
			if(this.getVal()!=0) {
				this.setText(this.getVal()+"");
			}
		}
	}
}
