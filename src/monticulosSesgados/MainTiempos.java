package monticulosSesgados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class MainTiempos {


	public static void main(String[] args) {
		int op;
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce una opción:\n 1.- Coste amortizado de la unión O(logn)\n 2.- Coste mínimo O(1)");
		op = sc.nextInt();

		switch(op) {
		case 1: costeAmortizUnion(); break;
		case 2: costeMinimo(); break;


		default: break;
		}

		sc.close();
	}





	private static void costeMinimo() {
		int nodosm = 1;
		Sesgado s = new Sesgado(new Arbin<Integer>(new Nodo<Integer>(7)));

		String ruta = "minimo.txt";

		File f = new File(ruta);
		BufferedWriter bw = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(f));
		} catch (IOException e) {
			System.out.println("Error con el fichero");
		}

		for (int i = 1; i <= 3000; i++) {
			long media = 0;

			for (int j = 0; j < 3; j++) {

				long startTime = System.currentTimeMillis();
				for (int k = 0; k <9999999; k++) {
					for (int k2 = 0; k2 <999999999; k2++) {
						s.minimo();
					}
				}
				long endTime = System.currentTimeMillis();
				media += (endTime-startTime);
			}

			media = media /3;

			try {

				bw.write( (nodosm)+ " " + media +"\n");
			} catch (IOException e) {
				System.out.println("Error con el fichero");
			}

			System.out.println ("Nodos " + (nodosm) + ": Ha tardado " + media + " ms"); 

			Random aleatorio = new Random(System.currentTimeMillis());

			s.insertar(new Nodo<Integer>(100 + aleatorio.nextInt(10000)));
			nodosm++;
		
		}
		try {
			bw.close();
		} catch (IOException e) {
			System.out.println("Error con el fichero");
		}

	}





	private static void costeAmortizUnion() {
		int nodosm1 = 1;
		int nodosm2= 1;
		Sesgado s1 = new Sesgado(new Arbin<Integer>(new Nodo<Integer>(11)));
		Sesgado s2 = new Sesgado(new Arbin<Integer>(new Nodo<Integer>(7)));

		String ruta = "amortizado.txt";

		File f = new File(ruta);
		BufferedWriter bw = null;
		
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(f));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 1; i <= 3000; i++) {
			long media = 0;
			Arbin<Integer> sesgadoUnion = null;
			for (int j = 0; j < 3; j++) {

				long startTime = System.currentTimeMillis();
				for (int k = 0; k <1000000; k++) {
					sesgadoUnion = Sesgado.union(s1.arbin, s2.arbin);
				}
				long endTime = System.currentTimeMillis();
				media += (endTime-startTime);
			}

			media = media /3;

			try {

				bw.write( (nodosm1+nodosm2)+ " " + media +"\n");
			} catch (IOException e) {
				System.out.println("Error con el fichero");
			}
			System.out.println ("Nodos " + (nodosm1+nodosm2) + ": Ha tardado " + media + " ms"); 

			Random aleatorio = new Random(System.currentTimeMillis());
			s1.insertar(new Nodo<Integer>(aleatorio.nextInt(10000)+1));
			aleatorio.setSeed(aleatorio.nextLong());
			nodosm1++;
			s2.insertar(new Nodo<Integer>(100 + aleatorio.nextInt(10000)));
			nodosm2++;
			System.out.println(sesgadoUnion);
		}
		try {
			bw.close();
		} catch (IOException e) {
			System.out.println("Error con el fichero");
		}
	}


}
