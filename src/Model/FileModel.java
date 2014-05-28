package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Properties;

import Model.Interface.IConstants;
import Properties.Player;
import Properties.PlayerHighscore;
import Properties.SnakeSpeed;

public class FileModel extends Observable {
	private File file;

	public FileModel() {
		file = new File(IConstants.CONFIG_PATH);
	}

	public void writeToIniFile(PlayerHighscore playerHighscore, int snake_speed) {
		Properties properties = new Properties();
		properties.setProperty("player_name",
				String.valueOf(playerHighscore.getPlayer().getPlayerName()));
		properties.setProperty("player_id",
				String.valueOf(playerHighscore.getPlayer().getPlayerId()));
		properties.setProperty("highscore",
				String.valueOf(playerHighscore.getHighscore()));
		properties.setProperty("highscore_id",
				String.valueOf(playerHighscore.getHighscore_id()));
		properties.setProperty("snake_speed", String.valueOf(snake_speed));
		try {
			properties.store(new FileOutputStream(getFile()), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PlayerHighscore getLastPlayerFromFile() {
		PlayerHighscore playerHighscore = null;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(getFile()));
			playerHighscore = new PlayerHighscore(new Player(
					Integer.valueOf(properties.getProperty("player_id")),
					properties.getProperty("player_name")),
					Integer.valueOf(properties.getProperty("highscore")),
					Integer.valueOf(properties.getProperty("highscore_id")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playerHighscore;
	}
	
	public SnakeSpeed getSnakeSpeedFromFile() {
		SnakeSpeed snakeSpeed = null;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(getFile()));
			snakeSpeed = new SnakeSpeed(Integer.valueOf(properties.getProperty("snake_speed", "150")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return snakeSpeed;
	}

	public File getFile() {
		return file;
	}
}
