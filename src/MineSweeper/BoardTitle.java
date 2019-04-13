package MineSweeper;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * class that represents a JLabel of Game Title
 */
public class BoardTitle extends JPanel{
	private static final long serialVersionUID = 1L;

	/**
	 * MinesState Ctor
	 */
	public BoardTitle() {
		JLabel Title =new JLabel("Minesweeper");
		Title.setForeground (Color.WHITE);
		Title.setFont(new Font("TimesRoman", Font.ITALIC, 50));
		
		this.add(new JLabel(""));
		this.add(Title);
		this.add(new JLabel(""));
		this.setBackground(Color.BLACK);
	}
}
