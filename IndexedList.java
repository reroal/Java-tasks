/** Realización de una lista indizada por medio de un arreglo, el cual es 
  * doblado cuando el tamaño de la lista excede la capacidad del arreglo.
  *
  */
public class ArregloListaIndiceCircular<E> implements IndexList<E> {
  private E[] A; // arreglo almacenando los elementos de la lista indizada
  private int capacidad = 16;  // tamaño inicial del arreglo A
	private int tam=0, f=0;
  // número de elementos guardados en la lista 
  /** Crear la lista indizada con capacidad inicial de 16. */
  public ArregloListaIndiceCircular() { 
    A = (E[]) new Object[capacidad]; // el compilador podría advertir, 
                                     // pero está bien
  }
 public ArregloListaIndiceCircular(int n) {
	capacidad = n; 
    A = (E[]) new Object[capacidad]; // el compilador podría advertir, 
                                     // pero está bien
  }
  /** Insertar un elemento en el índice dado. */
  public void add(int r, E e) 
    throws IndexOutOfBoundsException {
    revisarIndice(r, size() + 1);
    if (tam == capacidad) {  // un sobreflujo
      capacidad *= 2;
      E[] B =(E[]) new Object[capacidad]; // el compilador podría advertir
      for (int i=0; i<tam; i++) 
        B[i] = A[i];
      A = B;
    }
		if(r==0){//agregar al inicio
			if(A[f]!=null)
				f = (capacidad-1+f)%capacidad;
			A[f] = e;
			return;
		}
		if(r == size()){//agregar al final
			A[size()] = e;
			tam = (tam +1)%capacidad;
			return;
		}
		for (int i=tam-1; i>=r; i--)  // desplazar elementos hacia adelante
      A[i+1] = A[i];
    A[r] = e;
    tam++;
  }
  /** Quitar el elemento guardado en el índice dado. */
  public E remove(int r) 
    throws IndexOutOfBoundsException {
    revisarIndice(r, size());
		if(r == size()-1){//remover último
				tam = (tam-1)%capacidad; 
				E temp = A[tam];
				A[tam] = null;				
				return	temp;
		}
		if(r == 0){ //remover al principio
			E temp = A[f];
			A[f] = null;
			f = (f+1)%capacidad;
			return temp;
		}				
    E temp = A[r];
    for (int i=r; i<tam-1; i++)  // desplazar elementos hacia atrás
      A[i] = A[i+1];
    tam--;
    return temp;
  }
  /** Revisa si el índice dado está en el rango [0, n - 1] */
  protected void revisarIndice(int r, int n) // 
    throws IndexOutOfBoundsException {  // 
    if (r < 0 || r >= n)
      throw new IndexOutOfBoundsException("Índice ilegal : " + r);
  }
  /** Regresar el número de elementos en la lista indizada. */
  public int size() {
    return (capacidad-f+tam)%capacidad;
  }
  /** Indica si la lista indizada está vacía. */
  public boolean isEmpty() {
    return size() == 0; 
  }
  /** Regresa el elemento guardado en el índice dado. */
  public E get(int r) 
    throws IndexOutOfBoundsException {
    revisarIndice(r, size());
    return A[r]; 
  }
  /** Reemplaza el elemento guardado en el índice dado. */
  public E set(int r, E e) 
    throws IndexOutOfBoundsException {
    revisarIndice(r, size());
		if(r == size()-1){//modificar último 
				E temp = A[(tam-1)%capacidad];
				A[(tam-1)%capacidad] = e;				
				return	temp;
		}
		if(r == 0){ //modificar al principio
			E temp = A[f];
			A[f] = e;
			return temp;
		}				
    E temp = A[r];
    A[r] = e;
    return temp;
  }
	public String toString(){
		String m = "[";
		for(int i=0; i<capacidad; i++)
			if(A[i] != null)
				m += (m == "[" ? A[i] : ", " + A[i]);
		m += "]";
		return m;
	}
		
	public static void main(String[] args){
		ArregloListaIndiceCircular<String> AC = new ArregloListaIndiceCircular<String>(5);
		AC.add(0,"Pancho");
		AC.add(0,"Barraza");
		AC.add(1,"Panchito");
		AC.add(1,"Federico");
		AC.add(3,"Ponciano");
		System.out.println(AC);
		AC.add(3,"Mariano");
		System.out.println(AC);
	}
}
