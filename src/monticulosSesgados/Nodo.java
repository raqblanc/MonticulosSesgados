package monticulosSesgados;

public class Nodo<T> {

	private Nodo<T> _iz;
	private Nodo<T> _der;
	private int _nrefs;
	private T _elem;

	/** Constructor; construye un nodo para un árbol unitario (sin
    hijo izquierdo, sin hijo derecho)

	@param el valor a alojar en el nodo
	 */ 
	public Nodo(T elem){
		this._elem = elem;
		this._iz = null;
		this._der = null;
		this._nrefs = 0; 
	}

	/** Constructor; construye un nodo para un árbol con
    hijo izquierdo e hijo derecho

	@param iz,  el hijo izquierdo
    @param elem, el valor a alojar en la raíz				
	@param dr, el hijo derecho
	 */ 				
	Nodo(Nodo<T> iz, T elem, Nodo<T> der) {
		this._elem = elem;
		this._iz = iz;
		this._der = der;
		this._nrefs = 0; 
		if (_iz != null) _iz.addRef();
		if (_der != null) _der.addRef();
	}
	
	/** Contabiliza una nueva referencia al nodo
     */ 	
	public void addRef() {
		this._nrefs++;
	}
	
	/** Decrementa el contador de referencias al nodo
     */ 
	public void rmRef() {
		this._nrefs--;
	}

	/**
	 * Devuelve el hijo izquierdo
	 * @return El hijo izquierdo
	 */
	public Nodo<T> get_iz() {
		return _iz;
	}

	
	/**
	 * Devuelve el hijo derecho
	 * @return El hijo derecho
	 */
	public Nodo<T> get_der() {
		return _der;
	}

	/**
	 * Devuelve el elemento del nodo
	 * @return El elemento
	 */
	public T get_elem() {
		return _elem;
	}

	/**
	 * Cambia el valor del hijo izquierdo
	 * @param n Nodo por el que se cambia
	 */
	public void set_iz(Nodo<T> n) {
		this._iz = n;
		
	}

	

}
