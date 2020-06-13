package es.codeurjc.ais.tictactoe;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class BoardTest {

	Board board;
	
	@BeforeEach
	void init() {
		board = new Board();
	}
	
	@Test
	@DisplayName("Test si ganador J1")
	void Estado2Test() {
		
		int[] movimientos = {0, 1, 3, 2, 6};
		setValorCeldas(movimientos);
		
		int[] celdasganadoras = {0, 3, 6};
		assertThat(board.getCellsIfWinner("J1"), equalTo(celdasganadoras));
		assertThat(board.checkDraw(), equalTo(false));
	}
	
	@Test
	@DisplayName("Test si empate")
	void Estado3Test() {
		
		int[] movimientos = {0, 1, 2, 4, 3, 6, 7, 8, 5};
		setValorCeldas(movimientos);
		
		assertThat(board.getCellsIfWinner("J1"), equalTo(null));
		assertThat(board.getCellsIfWinner("J2"), equalTo(null));
		assertThat(board.checkDraw(), equalTo(true));
	}
	
	@Test
	@DisplayName("Test si ganador J2")
	void Estado4Test() {
		
		int[] movimientos = {1, 4, 7, 5, 0, 3};
		setValorCeldas(movimientos);
		
		int[] celdasganadoras = {3, 4, 5};
		assertThat(board.getCellsIfWinner("J2"), equalTo(celdasganadoras));
		assertThat(board.checkDraw(), equalTo(false));
	}

	
	private void setValorCeldas(int[] movimientos) {
		int cont = 0;
		for (int i : movimientos) {
			if (cont%2==0) 
				board.getCell(i).value = "J1";
			else
				board.getCell(i).value = "J2";
			cont++;
		}
	}
	

}
