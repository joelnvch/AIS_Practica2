package es.codeurjc.ais.tictactoe;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


class TicTacToeGameTest {
	
	@DisplayName("El primer jugador que pone ficha gana")
	@Test
	void test() {
		TicTacToeGame g = new TicTacToeGame();
		
		Connection c1 = mock(Connection.class);
		Connection c2 = mock(Connection.class);
		
		g.addConnection(c1);
		g.addConnection(c2);
		
		Player p1 = new Player(0, "P1", "Jugador 1");
		Player p2 = new Player(1, "P2", "Jugador 2");
		
		g.addPlayer(p1);
		g.addPlayer(p2);
		
		
		List<Player> players = new CopyOnWriteArrayList<>();
		players.add(p1);
		players.add(p2);
		verify(c1, times(2)).sendEvent(EventType.JOIN_GAME, players);
		verify(c2, times(2)).sendEvent(EventType.JOIN_GAME, players);
		
		
		g.mark(0);
		g.mark(1);
		g.mark(3);
		g.mark(2);
		
		reset(c1);
		reset(c2);
		
		g.mark(6);
		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), notNull());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), notNull());
	}
	
	@DisplayName("Empate")
	@Test
	void test1() {
		TicTacToeGame g = new TicTacToeGame();
		
		Connection c1 = mock(Connection.class);
		Connection c2 = mock(Connection.class);
		
		g.addConnection(c1);
		g.addConnection(c2);
		
		Player p1 = new Player(0, "P1", "Jugador 1");
		Player p2 = new Player(1, "P2", "Jugador 2");
		
		g.addPlayer(p1);
		g.addPlayer(p2);
		
		
		List<Player> players = new CopyOnWriteArrayList<>();
		players.add(p1);
		players.add(p2);
		verify(c1, times(2)).sendEvent(EventType.JOIN_GAME, players);
		verify(c2, times(2)).sendEvent(EventType.JOIN_GAME, players);
		
		
		g.mark(0);
		g.mark(6);
		g.mark(4);
		g.mark(1);
		g.mark(2);
		g.mark(8);
		g.mark(3);
		g.mark(5);
		
		reset(c1);
		reset(c2);
		
		g.mark(7);
		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), isNull());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), isNull());	
	}
	
	
	@DisplayName("El primer jugador que pone ficha pierde")
	@Test
	void test2() {
		TicTacToeGame g = new TicTacToeGame();
		
		Connection c1 = mock(Connection.class);
		Connection c2 = mock(Connection.class);
		
		g.addConnection(c1);
		g.addConnection(c2);
		
		Player p1 = new Player(0, "P1", "Jugador 1");
		Player p2 = new Player(1, "P2", "Jugador 2");
		
		g.addPlayer(p1);
		g.addPlayer(p2);
		
		
		List<Player> players = new CopyOnWriteArrayList<>();
		players.add(p1);
		players.add(p2);
		verify(c1, times(2)).sendEvent(EventType.JOIN_GAME, players);
		verify(c2, times(2)).sendEvent(EventType.JOIN_GAME, players);
		
		
		g.mark(0);
		g.mark(6);
		g.mark(4);
		g.mark(1);
		g.mark(2);
		g.mark(8);
		g.mark(3);
		
		reset(c1);
		reset(c2);
		
		g.mark(7);
	
		verify(c1).sendEvent(eq(EventType.GAME_OVER), notNull());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), notNull());
	
		
		
	}
}
