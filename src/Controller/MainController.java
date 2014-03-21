package Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.SpriteView;

public class MainController {

	private static MainController mainController;
	private MainView mainView;
	private GamePanelView gamePanelView;
	private Vector<SpriteView> actors;
	private BufferedImage[] apple;
	private AppleView appleView;
	private Thread thread;

	public boolean IsGameRunning = false;
	public boolean IsWindowCreated = false;

	/**
	 * Makes it impossible to instantite
	 */
	private MainController() {
	}

	public BufferedImage[] loadImages(String path, int image) {
		BufferedImage[] bufferedImages = new BufferedImage[image];
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(path));
			bufferedImages[0] = bufferedImage;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return bufferedImages;
	}

	public static MainController getInstance() {
		if (mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}

	private void createWindow() {
		mainView = new MainView();
		mainView.setBounds(1024, 600, 1024, 600);
		gamePanelView = new GamePanelView(mainView.getWidth(),
				mainView.getHeight());
		mainView.add(gamePanelView);
		mainView.setVisible(true);
		IsWindowCreated = true;
	}

	private void spawnApples() {
		actors = new Vector<SpriteView>();
		apple = MainController.getInstance().loadImages(
				"./resources/apple_sprite.png", 1);
		appleView = new AppleView(apple, Math.random() * 100.234,
				Math.random() * 50.234, gamePanelView);
		actors.add(appleView);
		gamePanelView.setActors(actors);
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						appleView.x = Math.random() * 100.234;
						appleView.y = Math.random() * 100.234;
						mainView.repaint();
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread = new Thread(runnable);
	}

	/**
	 * Main Funktion
	 * Gezeichnet wird nur, wenn der Benutzer das "Spiel" gestartet hat
	 * @param args
	 */
	public static void main(String[] args) {
		getInstance().createWindow();
		while (getInstance().IsWindowCreated) {
			if (getInstance().IsGameRunning) {
				getInstance().spawnApples();
				getInstance().thread.run();
			}
		}
	}

}
