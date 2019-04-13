package MineSweeper;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
* class of Game Gird
 */
public class GameGrid extends JPanel{

	private static final long serialVersionUID = 1L;
	private Square [][] buttons;
	private int Rows ,Columns,NumOfMines;
	private Boolean GameOver =false;
	private MinesState MineStatus;
	private JPanel GameStatus = new JPanel();
	private JPanel GameBoard = new JPanel();
	
	/**
	 * GameGrid Ctor
	 * @param int row,column for Grid Size ang int NumOfMines for mines in grid
	 */
	public GameGrid(int row ,int column,int NumOfMines) {
		if(row!=0 && column!=0) {
			this.buttons = new Square[row][column];
			this.Rows = row;
			this.Columns = column;
			this.NumOfMines =NumOfMines;
			this.MineStatus = new MinesState(NumOfMines);
			this.setLayout(new BorderLayout(0,0));
			this.setBorder(new LineBorder(Color.BLACK, 0));
			GameStatus.setBackground(Color.black);
			GameStatus.setLayout(new GridLayout(2,1,0,0));
			GameStatus.add(new BoardTitle());
			GameStatus.add(MineStatus);
			GameBoard.setLayout(new GridLayout(row,column));
			GameBoard.setBorder( new LineBorder(Color.BLACK, 0));
			this.createGrid();
			this.LayMineS(this.NumOfMines);
			this.add(GameStatus,BorderLayout.PAGE_START);
			this.add(GameBoard,BorderLayout.CENTER);
		}
	}
	
	/**
	 * function of create game Grid
	 */
	private void createGrid() {
		for (int i =0; i<this.Rows; i++ ) {
			for (int j =0; j<this.Columns; j++ ) {
				this.buttons[i][j]=new Square(0,i,j);
				this.buttons[i][j].setBackground(Color.white);
				this.buttons[i][j].setBorder(new LineBorder(Color.BLACK, 1));
				this.buttons[i][j].addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e) {
						if(SwingUtilities.isLeftMouseButton(e)){
							RightClickOnSquare((Square)e.getSource());
						}
						else if(SwingUtilities.isRightMouseButton(e)) {
							LeftClickOnSquare((Square)e.getSource());
						}
					}
				});
				GameBoard.add(this.buttons[i][j]);
			}
		}
		
	}
	/**
	 * function of event when Square was right mouse Clciked
	 * @param Square Cell that was clicked
	 */
	public void RightClickOnSquare(Square Cell) {
		if(GameOver==false){
			if(!Cell.getPresed() && Cell.getChecked()==false){
				this.GameOver = Cell.ClickOnButton();
				FloodFillAlgo(Cell.getRow(), Cell.getColumn());
				getStatus();
			}
		}
	}
	/**
	 * function of event when Square was left mouse Clciked
	 * @param Square Cell that was clicked
	 */
	public void LeftClickOnSquare(Square Cell) {
		if(GameOver==false) {
			Cell.AddFlag();
			if(Cell.getChecked()==true) {
				MineStatus.setState(NumOfMines-1);
				NumOfMines--;
			}
			else {
				MineStatus.setState((NumOfMines+1));
				NumOfMines++;
			}
		}
	}
	
	/**
	 * function to open board if game is over
	 */
	public void OpenBoard() {
		for (Square[] i : this.buttons) {
			for (Square j: i) {
					j.ShowCell();
				}
			}
	}
	
	/**
	 * function to get the game status and open board if game is over
	 */
	public void getStatus() {
		if(this.GameOver==true) {
			this.OpenBoard();
		}
		for (int i=0 ; i<this.Rows; i++) {
			for (int j=0; j<this.Columns; j++) {
				if(this.buttons[i][j].getPresed() != true && this.buttons[i][j].getVal()!=-1)
					return; 
			}
		}
		JOptionPane.showMessageDialog(this,"You Won");
		this.OpenBoard();
		this.GameOver=true;
	}
	
	/**
	 * function to lay mine randomly on the grid
	 * @param int number of mines to lay
	 */
	public void LayMineS(int Number) {
		Random rand=new Random();
		int row=0,column=0;
		while(Number>0){
			row=rand.nextInt(this.Rows);
			row =rand.nextInt(this.Rows);
			column= rand.nextInt(this.Columns);
			column= rand.nextInt(this.Columns);
			if(this.buttons[row][column].getVal()!=-1) {
				this.buttons[row][column].setVal(-1);
				this.updateNeighbor(row-1,column-1);
				this.updateNeighbor(row-1,column);
				this.updateNeighbor(row-1,column+1);
				this.updateNeighbor(row,column-1);
				this.updateNeighbor(row,column+1);
				this.updateNeighbor(row+1,column-1);
				this.updateNeighbor(row+1,column);
				this.updateNeighbor(row+1,column+1);
				Number--;
			}
		}
	}
	/**
	 * function to update rows and columns values next to a mine
	 * @param int row,column for Square row location to update value
	 */
	private void updateNeighbor(int row, int column) {
		if(row>=0 && row<this.Rows && column>=0 && column<this.Columns) {
			if(this.buttons[row][column].getVal()!=-1)
				this.buttons[row][column].setVal(this.buttons[row][column].getVal() + 1 );
		}
	}
	
	/**
	 * function to fulfill Flood Fill algorithm
	 * @param int row,column for Square row location to start algorithm
	 */
	private void FloodFillAlgo(int row, int column) {
		if(row>=0 && row<this.Rows && column>=0 && column<this.Columns ) {
			if (this.buttons[row][column].getPresed()==false && this.GameOver==false && this.buttons[row][column].getChecked() ==false) {
				this.buttons[row][column].ClickOnButton();
				this.buttons[row][column].setPresed(true);
				if (this.buttons[row][column].getVal()==0) {
					this.FloodFillAlgo(row-1,column-1);
					this.FloodFillAlgo(row-1,column);
					this.FloodFillAlgo(row-1,column+1);
					this.FloodFillAlgo(row,column-1);
					this.FloodFillAlgo(row,column+1);
					this.FloodFillAlgo(row+1,column-1);
					this.FloodFillAlgo(row+1,column);
					this.FloodFillAlgo(row+1,column+1);
				}
			}
		}
		return;
	}
}
