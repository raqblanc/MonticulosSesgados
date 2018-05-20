package monticulosSesgados;

public class Arbin<T> {

	private Nodo<T> _ra;
	private int n = 0;

	/** Constructor; operación ArbolVacío */
	public Arbin() {
		_ra = null;
	};

	/** Constructor; Crea un nuevo árbol con una raíz dada
    @param ra El nodo raíz del árbol
	 */		 
	public Arbin(Nodo<T> ra) {
		this._ra = ra;
		if (_ra != null) _ra.addRef();
		n++;
	}

	/** Constructor; operación Cons (construye un árbol con un hijo izquierdo,
    una raíz y un hijo derecho)

       @param iz El hijo izquierdo 
	   @param elem El valor a poner en la raíz	
	   @param dr El hijo derecho
	 */	 

	public Arbin(Arbin<T> iz, T elem, Arbin<T> dr) {
		this._ra = new Nodo<T>(iz._ra, elem, dr._ra);
		this._ra.addRef();
	}

	/**
	 * Devuelve la raíz del árbol
	 * @return La raiz
	 */
	public Nodo<T> get_ra() {

		return _ra;
	}

	/** Devuelve el hijo izquierdo del árbol
	 * @return El hijo izquierdo
	 */	  
	public Arbin<T> hijoIz()  {  

		return new Arbin<T>(_ra.get_iz());
	}  


	/** Devuelve el hijo derecho del árbol    
    @return El hijo derecho
	 */
	public Arbin<T> hijoDer() {  

		return new Arbin<T>(_ra.get_der());
	} 


	/** Determina si el árbol está vacío.

    @return true si el árbol está vacío; false en otro caso
	 */	
	boolean esVacio() {
		return _ra == null; 	
	}

	/**
	 * Determina si dos árboles son iguales o no
	 * @param a Árbol a comparar
	 * @return true si son iguales, false si no
	 */
	public boolean equals(Arbin<T> a) {
		return sonIguales(this._ra, a.get_ra());

	}

	/** Comprobación de la igualdad entre dos estructuras de nodo
    @param n0, primera estructura
	 @param n1, segunda estructura
	 @return true si los punteros apuntan a estructuras que representan 
	         el mismo árbol, false en otro caso

	 */
	public boolean sonIguales(Nodo<T> n0, Nodo<T> n1) {
		return n0 == n1  
				||
				n0 != null && n1 != null &&
				n0.get_elem() == n1.get_elem() &&
				sonIguales(n0.get_iz(), n1.get_iz()) &&
				sonIguales(n0.get_der(), n1.get_der());

	}


/**
 * @return La representación del árbol
 */
	public String toString() {
		String s = "";
		Arbin<T> iz = null;
		Arbin<T> der = null;
		if (this._ra!= null) {
			iz = new Arbin<T>(this._ra.get_iz());
			der = new Arbin<T>(this._ra.get_der());

			if (der.get_ra()== null && iz.get_ra() == null) {
				T raiz = this._ra.get_elem();
				s+="[" +  raiz + "]";
			}
			else if (der.get_ra() != null && iz.get_ra() == null) {
				s+="(#" + this.get_ra().get_elem() + der.toString() + ")";
			}
			else if(der.get_ra() == null && iz.get_ra() != null) {
				s+="("+ iz.toString() + this.get_ra().get_elem() +  "#)";
			}
			else if (der.get_ra() != null && iz.get_ra() != null){
				s+="("+ iz.toString()+ this.get_ra().get_elem() + der.toString() + ")";
			}

		}
		return s;
	}
	public int getN() {
		return n;
	}
	
	
	

}
