package ArraysBidimensionales;

import java.util.Scanner;

public class BuscaTesoro {
	static final int VACIO = 0;
	static final int MINA = 1;
	static final int TESORO = 2;
	static final int INTENTO = 3;

	public static void pintaFinal(int[][] n) {

		String c = "";
		int fila = n.length;
		int columna = n[0].length;
		
		System.out.println("FIN DE PARTIDA");
		for (int i = 0; i < fila; i++) {
			System.out.print("Fila: " + i+"  ");
			for (int j = 0; j < columna; j++) {
				switch (n[i][j]) {
						case VACIO:
							c = "  ";
							break;
						case MINA:
							c = "* ";
							break;
						case TESORO:
							c = "€ ";
							break;
						case INTENTO:
							c = "X ";
							break;
						default:
				}
				System.out.print(c);

			}
			System.out.println();
		}

	}

	public static void pinta(int[][] n) {

		int fila = n.length;
		// System.out.println("numero de filas " + fila);
		// el numero ded cols es la longitud de cada fila por ejemplo la 0
		int columna = n[0].length;
		// System.out.println("numero de columnas " + columna);
		System.out.println("PINTA JUEGO");
		for (int i = 0; i < fila; i++) {
			System.out.print("Fila: " + i + "  ");
			for (int j = 0; j < columna; j++) {

				// ojo con lo que pintamos
				if (n[i][j] == INTENTO)
					System.out.print("X ");
				else
					System.out.print("? ");
				// System.out.printf("%10d ", n[i][j]);
				// Thread.sleep(1000); // retardo de un segundo
			}
			System.out.println();
		}

	}

	public static void main(String[] args) throws InterruptedException {

		Scanner esc = new Scanner(System.in);
		int x = 0;
		int y = 0;
		int[][] cuadrante = new int[5][4];

		// inicializa el array con ceros
		for (x = 0; x < 5; x++) {
			for (y = 0; y < 4; y++) {
				cuadrante[x][y] = VACIO;
			}
		}

		// coloca la mina
		int minaX = (int) (Math.random() * 5);
		int minaY = (int) (Math.random() * 4);
		cuadrante[minaX][minaY] = MINA;
		
		// coloca el tesoro
		int tesoroX;
		int tesoroY;

		do {
			tesoroX = (int) (Math.random() * 5);
			tesoroY = (int) (Math.random() * 4);
		} while ((minaX == tesoroX) && (minaY == tesoroY));
		// no puede coincidir con la mina
		cuadrante[tesoroX][tesoroY] = TESORO;
		
		// JUEGO
		System.out.println("¡BUSCA EL TESORO!");
		boolean salir = false;
		
		do {

			// pinta el cuadrante
			pinta(cuadrante);

			// pedir disparo
			System.out.print("Coordenada x: ");
			x = esc.nextInt();
			// (int) Integer.parseInt(System.console().readLine());
			System.out.print("Coordenada y: ");
			y = esc.nextInt();
			// (int) Integer.parseInt(System.console().readLine());
			System.out.println("disparas a fila: " + x + " columna: " + y);
			esc.nextLine();

			// comprobar
			switch (cuadrante[x][y]) {
				case VACIO:
					cuadrante[x][y] = INTENTO; // pone 3
					break;
				case MINA:
					System.out.println("Lo siento, has perdido.");
					salir = true;
					break;
				case TESORO:
					System.out.println("Enhorabuena, has encontrado el tesoro.");
					salir = true;
					break;
				default:
			}

		} while (!salir);

		// se descubre el juego
		pintaFinal(cuadrante);
	}
}
