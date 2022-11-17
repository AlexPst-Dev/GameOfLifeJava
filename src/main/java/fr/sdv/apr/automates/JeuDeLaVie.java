package fr.sdv.apr.automates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class JeuDeLaVie {

	/**
	 * C'est ici que le projet se lance
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(JeuDeLaVie.class, args);

		final int NB_TOURS = 25;
		final int NB_ROWS = 10;
		final int NB_COLUMNS = 10;

		Board board = new Board(NB_ROWS, NB_COLUMNS);

		for (int i = 0; i < NB_TOURS; i++) {

			System.out.println("\n \n");
			System.out.println("###### Tour numéro : " + i);
			System.out.println("\n \n");

			board.displayBoard();
			board.browseBoard();

			TimeUnit.SECONDS.sleep(1);
		}
	}

}
