package monticulosSesgados;



public class Sesgado {

	public Arbin<Integer> arbin;

	public Sesgado(Arbin<Integer> a) {
		this.arbin = a;
	}
	public static Arbin<Integer> union (Arbin<Integer> t1, Arbin<Integer> t2) {
		if (t1.esVacio()) {
			return t2;
		}
		else if (t2.esVacio()) {
			return t1;
		}
		else {
			Arbin<Integer> iz1 = t1.hijoIz();
			Arbin<Integer> iz2 = t2.hijoIz();
			Nodo<Integer> n1 = t1.get_ra();
			Nodo<Integer> n2 = t2.get_ra();
			int elem1 = n1.get_elem();
			int elem2 = n2.get_elem();
			Arbin<Integer> der1 = t1.hijoDer();
			Arbin<Integer> der2 = t2.hijoDer();
			
			if (elem1 < elem2) {
				return new Arbin<Integer>(union(der1, t2), elem1, iz1);
			}
			else {
				return new Arbin<Integer>(union(der2, t1), elem2, iz2);
			}
		}
	}
	
	public void insertar (Nodo<Integer> n) {
		Arbin<Integer> t = new Arbin<Integer>(n);
		this.arbin = union(this.arbin, t);
	}
	
	/**
	 * Elimina el mínimo de un montículo (la raíz), 
	 */
	public void borraMin() {
		Nodo<Integer> raiz = arbin.get_ra();
		Nodo<Integer> der = raiz.get_der();
		Nodo<Integer> iz = raiz.get_iz();
		Arbin<Integer> arbiz = new Arbin<Integer>(iz);
		Arbin<Integer> arbder = new Arbin<Integer>(der);
		this.arbin = union(arbder, arbiz);
		
	}
	
	/**
	 * Calcula el elemento mínimo de un montículo
	 * @return elemento mínimo 
	 */
	public Nodo<Integer> minimo(){
		return this.arbin.get_ra();
	}
	
	/**
	 * Modifica la clave de un nodo al de un valor dado
	 * @param n Puntero al nodo de la clave que quiere modificarse
	 * @param valor Nuevo valor
	 */
	public void modificarClave(Nodo<Integer> n, int valor) {
		//1) Guardar montículo con raíz en el nodo a modificar
		Arbin<Integer> nuevo = new Arbin<Integer>(n.get_iz());
		
		//2) Eliminar n del árbol
		n.set_iz(null);
		
		//3) Guardar hijo izquierdo e hijo derecho de nuevo
		Arbin<Integer> hiz = nuevo.hijoIz();
		Arbin<Integer> hder = nuevo.hijoDer();
		
		//4) Unir hijo derecho e hijo izquierdo
		Arbin<Integer> nuevo2 = Sesgado.union(hiz, hder);
		
		//5) Unir lo anterior con el arbol original
		Arbin<Integer> a = this.arbin;
		this.arbin = Sesgado.union(nuevo2, a);
		
		//6)Insertar el nuevo valor
		Nodo<Integer> nodo = new Nodo<Integer>(valor);
		this.insertar(nodo);
		
	}
	
	public String toString() {
		return this.arbin.toString();
	}
	
	public Arbin<Integer> getArbin() {
		return this.arbin;
	}
	
	
	
	
}
