package monticulosSesgados;

import java.util.Scanner;


public class MainManual {
	private static String in;
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Lee el montículo introducido por pantalla y lo convierte
	 * @return el montículo como una estructura Arbin
	 */
	public static Arbin<Integer> leeArbol() {
		boolean seguir = true;
		String c = ""+in.charAt(0);
		in = in.substring(1, in.length());
		switch (c) {
		case "#": return new Arbin<Integer>();
		case "[": {

			c = ""+in.charAt(0);

			while (seguir) {
				char a = in.charAt(1); //el siguiente
				if (a !='#' && a != ']' && a != ')' && a != '(' && a != '[' ) {
					c+=a;
					in = in.substring(1, in.length());
				}
				else seguir = false;
			}
			in = in.substring(1, in.length());
			seguir = true;
			int raiz = Integer.parseInt(c);
			in = in.substring(1, in.length());

			return new Arbin<Integer>(new Nodo<Integer>(raiz));
		}
		case "(": {
			Arbin<Integer> iz = leeArbol();

			c = ""+in.charAt(0);
			while (seguir) {
				char a = in.charAt(1); //el siguiente
				if (a !='#' && a != ']' && a != ')' && a != '(' && a != '[' ) {
					c+=a;
					in = in.substring(1, in.length());
				}
				else seguir = false;
			}
			in = in.substring(1, in.length());
			seguir = true;
			int raiz = Integer.parseInt(c);

			Arbin<Integer> dr = leeArbol();

			in = in.substring(1, in.length()); //)
			return new Arbin<Integer>(iz, raiz, dr);
		}
		default: return new Arbin<Integer>();
		}
	}

	public static void main(String[] args) {

		menu();

	}

	/**
	 * Muestra el menú y llama a los respectivos métodos
	 */
	private static void menu() {
		boolean salir = false;
		Scanner sc = new Scanner(System.in);
		
		
		while (!salir) {
			
			System.out.println("Seleccione una opción:");
			System.out.println("1) Unir");
			System.out.println("2) Insertar");
			System.out.println("3) Eliminar mínimo");
			System.out.println("4) Mínimo");
			System.out.println("5) Modificar clave");
			System.out.println("(Cualquier otro) Salir");

			String opcion = sc.nextLine();

			switch(opcion) {
			case "1":
				union();
				break;

			case "2":
				insercion();
				break;

			case "3":
				elimMin();
				break;

			case "4":
				minimo();
				break;

			case "5":
				modClave();
				break;

			default:
				salir = true;
				System.out.println("Has salido");

			}
		}
		sc.close();


	}

	/**
	 * Arranca la operación modificar clave
	 */
	private static void modClave() {
		System.out.println("---------------MODIFICAR CLAVE---------------");
		System.out.println("Introduce el montículo de donde se quiere modificar una clave:");

		String sMod = sc.nextLine();

		in = sMod;

		if(!in.equals("#")) {
			Arbin<Integer> aMod = leeArbol();
			Sesgado sesgMod = new Sesgado(aMod);
			//El nodo cuya clave se modifica va a ser el hijo izquerdo del izquierdo de la raíz
			Nodo<Integer> mod = aMod.get_ra().get_iz();

			System.out.println("Introduce el valor nuevo: ");
			int valor = sc.nextInt();
			sesgMod.modificarClave(mod, valor);
			System.out.println("Montículo después de modificar la clave (izquierdo del izquierdo de la raíz):");
			System.out.println(sesgMod.toString()+"\n\n");
		}
		else {
			System.out.println("VACÍO");
		}

	}


	/**
	 * Arranca la operación obtener el mínimo
	 */
	private static void minimo() {
		System.out.println("---------------MÍNIMO---------------");
		System.out.println("Introduce el montículo de donde se quiere sacar el mínimo:");

		String sMin = sc.nextLine();

		in = sMin;
		if (!in.equals("#")){
			Arbin<Integer> aMin = leeArbol();
			Sesgado sesgMin = new Sesgado (aMin);
		
			Nodo<Integer> minimo = sesgMin.minimo();
	

			System.out.println("El mínimo del montículo es: " + minimo.get_elem()+"\n\n");

		
		}
		else {
			System.out.println("MONTÍCULO VACÍO");
		}
	}

	private static void elimMin() {
		System.out.println("---------------ELIMINAR MÍNIMO---------------");
		System.out.println("Introduce el montículo de donde se quiere eliminar el mínimo:");

		String sElim = sc.nextLine();

		in = sElim;

		if (!in.equals("#")) {
			Arbin<Integer> aElim = leeArbol();
			Sesgado sesgElim = new Sesgado(aElim);
			sesgElim.borraMin();

			System.out.println("Montículo después de eliminar el mínimo: ");

			if(!sesgElim.arbin.esVacio()) {
				System.out.print(sesgElim.toString()+"\n\n");
			}
			else {
				System.out.print("VACÍO");
			}
		}

		else {
			System.out.println("MONTÍCULO VACÍO");
		}
	}



	/**
	 * Arranca la operación de inserción
	 */
	private static void insercion() {
		System.out.println("---------------INSERCIÓN---------------");

		System.out.println("Introduce el montículo donde se quiere insertar:");

		String sInser = sc.nextLine();
		in = sInser;

		Arbin<Integer> aInser = leeArbol();
		Sesgado sesginsercion = new Sesgado(aInser);

		System.out.println("Introduce el elemento a insertar:");
		int sNodoInser = sc.nextInt();
		Nodo<Integer> n = new Nodo<Integer>(sNodoInser);

		System.out.println("Montículo después de la inserción: ");
		sesginsercion.insertar(n);
		System.out.print(sesginsercion.toString()+"\n\n");

	}

	/**
	 * Arranca la operación de unión
	 */
	private static void union() {
		System.out.println("---------------UNIÓN---------------");
		System.out.println("Introduce el montículo 1:");
		String sUn1 = sc.nextLine();
		in = sUn1;
		Arbin<Integer> aUn1 = leeArbol();
	
		System.out.println("Introduce el montículo 2:");
		String sUn2 = sc.nextLine();
		in = sUn2;
		Arbin<Integer> aUn2 = leeArbol();

		Arbin<Integer> sesgadoUnion = Sesgado.union(aUn1, aUn2);

		System.out.println("Unión de ambos montículos: ");
		if (!sesgadoUnion.esVacio())
			System.out.print(sesgadoUnion.toString()+"\n\n");
		else
			System.out.print("VACÍO");

	}
}
