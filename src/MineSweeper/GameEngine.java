package MineSweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;


/**
 * class GameEngine to start game
 */
public class GameEngine {
	private JFrame frame = new JFrame();
	private int GameRows=9,GameColumns=9;
	private int numOfMines=10;
	private GameGrid GameBoard;
	private int width=500,length=500;
	public static void main(String []args) {
		new GameEngine();
	}
	
	/**
	 * GameEngine Ctor
	 */
	public GameEngine() {
		this.frame = this.AddFrame();
		this.frame.getContentPane().setBackground( Color.BLACK );
		this.AddGrid();
		this.frame.setVisible(true);
	}
	/**
	 * function to add a new frame with GameGrid
	 */
	private JFrame AddFrame() {
		JFrame frame = new JFrame("MineSweeper Game");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(this.width,this.length));
		frame.add(CreateSortPanel(),BorderLayout.EAST);
		frame.add(CreateSortPanel(),BorderLayout.WEST);
		frame.add(CreateSortPanel(),BorderLayout.SOUTH);
		frame.setJMenuBar(CreateMenuBar());
		return frame;
	}
	
	/**
	 * function to Create Menu Bar
	 * @return JMenuBar
	 */
	private JMenuBar CreateMenuBar() {
		JMenuBar MenuBar = new JMenuBar();
		JMenuItem Begginer=new JMenuItem(new AbstractAction("Beginner") {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
		    	BeginnerGame();
		    	RemoveGrid();
				AddGrid(); 
		    }
		});  
		JMenuItem Medium=new JMenuItem (new AbstractAction("Medium") {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
		    	MediumGame();
		    	RemoveGrid();
				AddGrid(); 
		    } 
		});  
		JMenuItem Hard=new JMenuItem (new AbstractAction("Hard") {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
		    	HardGame();
		    	RemoveGrid();
				AddGrid(); 
		    } 
		}); 
		JMenu menu =new JMenu("Menu");
		JMenu Submenu = new JMenu("New Game");

		Submenu.add(Begginer);
		Submenu.add(Medium);
		Submenu.add(Hard);
		menu.add(Submenu);
		MenuBar.add(menu);
		return MenuBar;
	}
	/**
	 * function to create a panel for frame looks
	 * @return JPanel
	 */
	private JPanel CreateSortPanel() {
		JPanel Panel = new JPanel();
		Panel.setBackground(Color.black);
		return Panel;
	}
	/**
	 * function to remove current GameGrid
	 */
	private void RemoveGrid() {
		this.frame.remove(this.GameBoard);
		this.frame.revalidate();
	}
	/**
	 * function to add GameGrid
	 */
	private void AddGrid() {
		this.GameBoard = new GameGrid(this.GameRows,this.GameColumns,this.numOfMines);
		this.frame.add(this.GameBoard,BorderLayout.CENTER);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		this.frame.revalidate();
		this.frame.setVisible(true);
		
	}
	/**
	 * function to set GameGrid to Beginner level
	 */
	private void BeginnerGame() {
		this.GameColumns=9;
		this.GameRows =9;
		this.numOfMines=10;
		this.frame.setVisible(false);
		this.frame.setSize(new Dimension(500,500));
	}
	/**
	 * function to set GameGrid to Medium level
	 */
	private void MediumGame() {
		this.GameColumns=16;
		this.GameRows =16;
		this.numOfMines=20;
		this.frame.setVisible(false);
		this.frame.setSize(new Dimension(800,600));
	}
	/**
	 * function to set GameGrid to  Hard level
	 */
	private void HardGame() {
		this.GameColumns=30;
		this.GameRows =16;
		this.numOfMines=99;
		this.frame.setVisible(false);
		this.frame.setSize(new Dimension(1200,700));
	}
	/**
	 * function to Build a wait Panel for between screens
	 */
}
