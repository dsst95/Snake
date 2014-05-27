package Model;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import javax.imageio.ImageIO;

import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IElement;
import View.GamePanelView;

public class BulletModel extends Observable implements IActor, IElement {
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;
	private boolean opponentAlive = true;
	private Rectangle2D.Double bounding;
	private int nextJumpY = 0;
	private int nextJumpX = 0;
	private int maxJumpLength = 100;
	private int minJumpLength = 50;
	

	public BulletModel(double midOfSnakeHeadGraphicX, double midOfSnakeHeadGraphicY, double x , double y,List<BulletModel> bullets) {
		this.gamePanelView = gamePanelView;
		try {
			this.bufferedImages = ImageIO.read(new File(
					IConstants.BULLET_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bounding = new Rectangle2D.Double(x, y, bufferedImages.getWidth(),
				bufferedImages.getHeight());
	}

//	public void moveOpponent() {
//		bounding.x += getNextMovement(true);
//		bounding.y += getNextMovement(false);
//	}

//	private int getNextMovement(boolean movementOfX) {
//		int nextJump = 0;
//		int nextDirectionValue = 0;
//		if (movementOfX) {
//			nextJump = nextJumpX;
//		} else {
//			nextJump = nextJumpY;
//		}
//		if (nextJump == 0) {
//			Random random = new Random();
//			Boolean direction = random.nextBoolean();
//			nextJump = (int) (Math.random() * (maxJumpLength - minJumpLength) + minJumpLength);
//			if (direction) {
//				nextJump *= -1;
//			}
//		}
//		if (nextJump < 0) {
//			nextJump += 1;
//			nextDirectionValue = 1;
//		} else if (nextJump > 0) {
//			nextJump -= 1;
//			nextDirectionValue = -1;
//		}
//		if (movementOfX) {
//			nextJumpX = nextJump;
//		} else {
//			nextJumpY = nextJump;
//		}
//		return nextDirectionValue;
//	}
//
//	private void checkMovement() {
//		if (bounding.x <= bufferedImages.getWidth()) {
//			bounding.x = bufferedImages.getWidth();
//			nextJumpX = nextJumpX * -1;
//		}
//		if (bounding.x >= gamePanelView.getWidth() - bufferedImages.getWidth()) {
//			bounding.x = gamePanelView.getWidth() - bufferedImages.getWidth();
//			nextJumpX = nextJumpX * -1;
//		}
//		if (bounding.y <= bufferedImages.getHeight()) {
//			bounding.y = bufferedImages.getHeight();
//			nextJumpY = nextJumpY * -1;
//		}
//		if (bounding.y >= gamePanelView.getHeight()
//				- bufferedImages.getHeight()) {
//			bounding.y = gamePanelView.getHeight() - bufferedImages.getHeight();
//			nextJumpY = nextJumpY * -1;
//		}
//	}

	public Rectangle2D getBounding() {
		return bounding;
	}

	public void actuate(double delta) {

		if (opponentAlive) {
//			checkMovement();
//			moveOpponent();
			setChanged();
			notifyObservers();
		}
	}

	public void checkCollision(IActor actor) {
		if(bounding.intersects(actor.getBounding()) && actor instanceof SnakeHeadModel){
			setOpponentAlive(false);
//			((SnakeHeadModel) actor).setSnakeAlive(false);
			// TODO Abfrage, wenn es eine Snake ist, setAllive = false
			// ((SnakeHeadModel) actor).increaseLength();
		}

	}

	public void setOpponentAlive(boolean alive) {
		this.opponentAlive = alive;
	}
	
	public BufferedImage getBufferedImage(){
		return this.bufferedImages;
	}

	@Override
	public boolean checkPosition(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

}