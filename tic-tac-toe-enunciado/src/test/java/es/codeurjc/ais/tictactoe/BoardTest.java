package es.codeurjc.ais.tictactoe;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import es.codeurjc.ais.tictactoe.Board;
import es.codeurjc.ais.tictactoe.TicTacToeGame.Cell;
import static org.assertj.core.api.Assertions.*;
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
	void testEstado1() {
		board.getCell(1).value = "J1";
		board.getCell(2).value = "J2";
		board.getCell(4).value = "J1";
		board.getCell(7).value = "J2";
		board.getCell(0).value = "J1";
		board.getCell(2).value = "J2";
		board.getCell(8).value = "J1";
		
		int[] a = {0, 4, 8};
		assertThat(board.getCellsIfWinner("J1"), equalTo(a));
		assertThat(board.checkDraw(), equalTo(false));
		
	}
	
	@Test
	@DisplayName("Test si ganador J1")
	void testEstado2() {
		board.getCell(0).value = "J1";
		board.getCell(1).value = "J2";
		board.getCell(3).value = "J1";
		board.getCell(2).value = "J2";
		board.getCell(6).value = "J1";
		
		int[] a = {0, 3, 6};
		assertThat(board.getCellsIfWinner("J1"), equalTo(a));
		assertThat(board.checkDraw(), equalTo(false));
	}
	
	@Test
	@DisplayName("Test si empate")
	void testEstado3() {
		board.getCell(0).value = "J1";
		board.getCell(1).value = "J2";
		board.getCell(2).value = "J1";
		board.getCell(4).value = "J2";
		board.getCell(3).value = "J1";
		board.getCell(6).value = "J2";
		board.getCell(7).value = "J1";
		board.getCell(8).value = "J2";
		board.getCell(5).value = "J1";
		
		assertThat(board.getCellsIfWinner("J1"), equalTo(null));
		assertThat(board.getCellsIfWinner("J2"), equalTo(null));
		assertThat(board.checkDraw(), equalTo(true));
	}
	
	@Test
	@DisplayName("Test si ganador J2")
	void testEstado4() {
		board.getCell(1).value = "J1";
		board.getCell(4).value = "J2";
		board.getCell(7).value = "J1";
		board.getCell(5).value = "J2";
		board.getCell(0).value = "J1";
		board.getCell(3).value = "J2";
		
		int[] a = {3, 4, 5};
		assertThat(board.getCellsIfWinner("J2"), equalTo(a));
		assertThat(board.checkDraw(), equalTo(false));
	}
	
	@Test
	@DisplayName("Test si ganador J2")
	void testEstado5() {
		board.getCell(2).value = "J1";
		board.getCell(3).value = "J2";
		board.getCell(4).value = "J1";
		board.getCell(8).value = "J2";
		board.getCell(5).value = "J1";
		board.getCell(0).value = "J2";
		board.getCell(7).value = "J1";
		board.getCell(6).value = "J2";
		
		int[] a = {0, 3, 6};
		assertThat(board.getCellsIfWinner("J2"), equalTo(a));
		assertThat(board.checkDraw(), equalTo(false));
	}

	

}
