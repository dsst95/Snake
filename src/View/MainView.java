package View;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {
	private JMenuBar menuBar;
	private JMenu menuOption;
	private JMenuItem menuItemStart;
	private JMenuItem menuItemPause;
	private JMenuItem menuItemReset;


	/**
	 * Create the frame.
	 */
	public MainView() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		{
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				menuOption = new JMenu("Optionen");
				menuBar.add(menuOption);
				{
					menuItemStart = new JMenuItem("Start");
					menuItemStart.addActionListener(this);
					menuItemStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
					menuOption.add(menuItemStart);
				}
				{
					menuItemPause = new JMenuItem("Pause");
					menuItemPause.addActionListener(this);
					menuItemPause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
					menuOption.add(menuItemPause);
				}
				{
					menuItemReset = new JMenuItem("Reset");
					menuItemReset.addActionListener(this);
					menuItemReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
					menuOption.add(menuItemReset);
				}
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == menuItemReset) {
			menuItemResetActionPerformed(arg0);
		}
		if (arg0.getSource() == menuItemPause) {
			menuItemPauseActionPerformed(arg0);
		}
		if (arg0.getSource() == menuItemStart) {
			menuItemStartActionPerformed(arg0);
		}
	}
	
	protected void menuItemStartActionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(this, "Test");
	}
	protected void menuItemPauseActionPerformed(ActionEvent arg0) {
	}
	protected void menuItemResetActionPerformed(ActionEvent arg0) {
	}
}
