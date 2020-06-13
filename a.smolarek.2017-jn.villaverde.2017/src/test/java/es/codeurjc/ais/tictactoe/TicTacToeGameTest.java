package es.codeurjc.ais.tictactoe;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import static org.hamcrest.MatcherAssert.assertThat;

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
	void primeroGanaTest() {		
		verificarJuegoEmpezado();
		
		int[] movimientos = {0, 1, 3, 2};
		marcarYcomprobar(movimientos);
		
		//ultimo movimiento
		g.mark(6);
		
		ArgumentCaptor<WinnerValue> argumentc1 = ArgumentCaptor.forClass(WinnerValue.class);
		ArgumentCaptor<WinnerValue> argumentc2 = ArgumentCaptor.forClass(WinnerValue.class);
		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argumentc1.capture());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argumentc2.capture());
		
		assertThat(argumentc1.getValue().player, equalTo(p1));
		assertThat(argumentc2.getValue().player, equalTo(p1));
	}
	
	@DisplayName("Empate")
	@Test
	void empateTest() {		
		verificarJuegoEmpezado();
				
		int[] movimientos = {0, 6, 4, 1, 2, 8, 3, 5};
		marcarYcomprobar(movimientos);
		
		//ultimo movimiento
		g.mark(7);
		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), isNull());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), isNull());
		
	}
	
	
	@DisplayName("El primer jugador que pone ficha pierde")
	@Test
	void primeroPierdeTest() {		
		verificarJuegoEmpezado();
				
		int[] movimientos = {0, 6, 4, 1, 2, 8, 3};
		marcarYcomprobar(movimientos);
		
		//ultimo movimiento
		g.mark(7);
		
		ArgumentCaptor<WinnerValue> argumentc1 = ArgumentCaptor.forClass(WinnerValue.class);
		ArgumentCaptor<WinnerValue> argumentc2 = ArgumentCaptor.forClass(WinnerValue.class);
		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argumentc1.capture());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argumentc2.capture());
		
		assertThat(argumentc1.getValue().player, equalTo(p2));
		assertThat(argumentc2.getValue().player, equalTo(p2));				
	}
	
	
	
	private void verificarJuegoEmpezado() {
		verify(c1, times(2)).sendEvent(eq(EventType.JOIN_GAME), eq(players));
		verify(c2, times(2)).sendEvent(eq(EventType.JOIN_GAME), eq(players));
		
	}
	
	private void marcarYcomprobar(int[] movimientos) {
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(players.get(0)));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(players.get(0)));
		
		int cont = 1;
		for (int i : movimientos) {
			g.mark(i);
			verify(c1).sendEvent(eq(EventType.SET_TURN), eq(players.get(cont%2)));
			verify(c2).sendEvent(eq(EventType.SET_TURN), eq(players.get(cont%2)));
			reset(c1);
			reset(c2);
			cont++;
		}
		
	}
}
