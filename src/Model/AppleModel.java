package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IElement;
import View.GamePanelView;

public class AppleModel extends Observable implements IActor, IElement{
	double applePosition_x;
	double applePosition_y;
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;
	private long respawnSpeed = 500;
	private long lastRespawn = 0;
	private boolean respawn = true;

	public AppleModel(GamePanelView gamePanelView) {
		this.gamePanelView = gamePanelView;
		try {
			this.bufferedImages = ImageIO.read(new File(IConstants.APPLE_PAHT));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void moveApple() {
		applePosition_x = Math.random()
				* ((double) (gamePanelView.getWidth()) - ((double) bufferedImages
						.getWidth()));
		applePosition_y = Math.random()
				* ((double) (gamePanelView.getHeight()) - ((double) bufferedImages
						.getHeight()));
	}


	@Override
	public double getX() {
		return applePosition_x;
	}

	@Override
	public double getY() {
		return applePosition_y;
	}

	@Override
	public void actuate(long delta) {
		if(respawn){
			respawn = false;
			lastRespawn+=(delta/1000000);
			if(lastRespawn>respawnSpeed){
				lastRespawn = 0;
				moveApple();
				setChanged();
				notifyObservers();
			}
		}
	}
}
