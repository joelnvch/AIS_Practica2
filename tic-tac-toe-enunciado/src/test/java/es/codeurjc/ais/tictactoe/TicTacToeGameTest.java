package es.codeurjc.ais.tictactoe;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


class TicTacToeGameTest {
	
	private TicTacToeGame g;
	private Connection c1,c2;
	private Player p1,p2;
	private List<Player> players;
	
	@BeforeEach
	public void setupTest() {
		g = new TicTacToeGame();
		
		c1 = mock(Connection.class);
		c2 = mock(Connection.class);
		
		g.addConnection(c1);
		g.addConnection(c2);
		
		p1 = new Player(0, "P1", "Jugador 1");
		p2 = new Player(1, "P2", "Jugador 2");
		
		g.addPlayer(p1);
		g.addPlayer(p2);
				
		players = new CopyOnWriteArrayList<>();
		players.add(p1);
		players.add(p2);
	}
	
	
	@DisplayName("El primer jugador que pone ficha gana")
	@Test
	void test() {		
		verify(c1, times(2)).sendEvent(EventType.JOIN_GAME, players);
		verify(c2, times(2)).sendEvent(EventType.JOIN_GAME, players);
				
		g.mark(0);
		g.mark(1);
		g.mark(3);
		g.mark(2);
		
		reset(c1);
		reset(c2);
		
		g.mark(6);
		
		ArgumentCaptor<WinnerValue> argumentc1 = ArgumentCaptor.forClass(WinnerValue.class);
		ArgumentCaptor<WinnerValue> argumentc2 = ArgumentCaptor.forClass(WinnerValue.class);
		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argumentc1.capture());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argumentc2.capture());
		Object eventc1 = argumentc1.getValue();
		Object eventc2 = argumentc2.getValue();
		assertNotNull(eventc1);
		assertNotNull(eventc2);
	}
	
	@DisplayName("Empate")
	@Test
	void test1() {		
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
		
		ArgumentCaptor<WinnerValue> argumentc1 = ArgumentCaptor.forClass(WinnerValue.class);
		ArgumentCaptor<WinnerValue> argumentc2 = ArgumentCaptor.forClass(WinnerValue.class);
		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argumentc1.capture());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argumentc2.capture());
		
		Object eventc1 = argumentc1.getValue();
		Object eventc2 = argumentc2.getValue();
		assertNull(eventc1);
		assertNull(eventc2);	
	}
	
	
	@DisplayName("El primer jugador que pone ficha pierde")
	@Test
	void test2() {		
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
	
		ArgumentCaptor<WinnerValue> argumentc1 = ArgumentCaptor.forClass(WinnerValue.class);
		ArgumentCaptor<WinnerValue> argumentc2 = ArgumentCaptor.forClass(WinnerValue.class);
		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argumentc1.capture());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argumentc2.capture());
		Object eventc1 = argumentc1.getValue();
		Object eventc2 = argumentc2.getValue();
		assertNotNull(eventc1);
		assertNotNull(eventc2);					
	}
}
