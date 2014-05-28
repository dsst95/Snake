package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Model.Interface.IConstants;
import Model.Interface.IDataBaseConnection;
import Properties.Player;
import Properties.PlayerHighscore;

/**
 * 
 * 
 */
public class DatabaseConnectionModel implements IDataBaseConnection {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private String player_table = IConstants.TABLE_PLAYER;
	private String highscore_table = IConstants.TABLE_HIGHSCORE;
	private String url = IConstants.DATABASE_PATH;
	private String sql;
	private Vector<Player> playerVector = null;

	@Override
	public void createConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			// initialise connection
			connection = DriverManager.getConnection(url);
			connection.setReadOnly(false);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void createPlayer(String playerName) {
		createConnection();
		sql = "INSERT INTO " + player_table + " (player_name) values (?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, playerName);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param playerName
	 * @return
	 */
	public PlayerHighscore getSinglePlayer(String playerName) {
		createConnection();
		Player player = null;
		PlayerHighscore playerHighscore = null;
		sql = "SELECT player.*, highscore.highscore FROM " + player_table
				+ " player" + " JOIN " + highscore_table + " highscore ON"
				+ " player.player_id = highscore.player_id"
				+ " WHERE player_name = '" + playerName + "'";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				player = new Player(resultSet.getInt("player_id"),
						resultSet.getString("player_name"), 0);
				playerHighscore = new PlayerHighscore(player, resultSet.getInt("highscore"));
				System.out.println(player.getPlayerId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playerHighscore;
	}

	@Override
	public Vector<Player> getPlayers() {
		createConnection();
		Player player = new Player();
		playerVector = new Vector<Player>();
		sql = "SELECT * FROM " + player_table;
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				player = new Player(resultSet.getInt("player_id"),
						resultSet.getString("player_name"), 0);
				playerVector.add(player);
				player = null;
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playerVector;
	}
}