import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

class Autor
{
	private static int identificador=1;

	private String				nombre;
	private int					id, erdos_number;
	private LinkedList<Autor>	coautores;
	private boolean				en_cola;

	public Autor( String nombre )
	{
		this.nombre = nombre;
		erdos_number=Integer.MAX_VALUE;
		id = identificador++;
		en_cola = false;

		coautores = new LinkedList<Autor>();
	}

	public void conectar_con( Autor coautor )
	{
		this.coautores.add( coautor );
	}

	public String getNombre() { return nombre; }
	public int getErdosNumber() { return erdos_number; }
	public void setErdosNumber( int n ) { erdos_number = n; }

	public LinkedList<Autor> getCoautores()
	{
		return coautores;
	}

	public void setEnCola( boolean en_cola ) { this.en_cola = en_cola; }
	public boolean getEnCola() { return en_cola; }

	public String toString()
	{
		if ( erdos_number < Integer.MAX_VALUE ) {
			return nombre + " " + erdos_number;
		} else {
			return nombre + " infinity";
		}
	}


}

class Diccionario
{
	private final int	numSimbolos=96;
	private final char	simboloBase=' ';

	// Subclases
	private class Arco
	{
		private Nodo	nodo;
		private char	simbolo;

		public Arco( Nodo nodo, char simbolo )
		{
			this.nodo = nodo;
			this.simbolo = simbolo;
		}

		public Nodo getNodo()
		{
			return nodo;
		}
		public char getSimbolo()
		{
			return simbolo;
		}
	}
	private class Nodo
	{
		private Arco	siguientes[];
		private Autor	autor;

		public Nodo()
		{
			siguientes = new Arco [ numSimbolos ];
			this.autor = null;	
		}
		public Nodo( Autor autor )
		{
			this();
			this.autor = autor;
		}

		public Autor getAutor()
		{
			return autor;
		}
		public void setAutor( Autor autor )
		{
			this.autor = autor;
		}

		public Nodo getSiguiente( char simbolo )
		{
			try {
				int indice = simbolo - simboloBase;	

				if ( indice < 0 || indice > siguientes.length ) {
					throw new Exception( "Imposible alcanzar siguiente nodo con |" + simbolo + "|" );
				}

				Arco arco = siguientes[indice];

				if ( arco == null ) return null;

				if ( arco.getSimbolo() != simbolo ) {
					throw new Exception( "Imposible alcanzar siguiente nodo con |" + simbolo + "|" );
				}

				return siguientes[indice].getNodo();
			}
			catch( Exception e )
			{
				e.printStackTrace( System.err );
				System.exit(-1);
			}
			return null;
		}

		public void anyadeSiguiente( Nodo nodo, char simbolo )
		{
			try {
				int indice = simbolo - simboloBase;	

				if ( indice < 0 || indice > siguientes.length ) {

					throw new Exception( "Imposible preparar nuevo nodo con |" + simbolo + "|" );
				}

				siguientes[indice] = new Arco( nodo, simbolo );
			}
			catch( Exception e )
			{
				e.printStackTrace( System.err );
				System.exit(-1);
			}
		}

		public void recorre( String str )
		{
			if ( autor != null ) System.out.printf( "[%s] %s\n", str, autor.toString() );

			for( int i=0; i < siguientes.length; i++ ) {
				Arco arco = siguientes[i];
				if ( arco != null ) arco.getNodo().recorre( str + arco.getSimbolo() );
			}
		}
	}

	// Atributos de la clase Diccionario

	private Nodo	raiz;

	public Diccionario()
	{
		raiz = new Nodo();
	}

	public void sustituyeSimbolosNoAscii7Bits( char clave[] )
	{

	}
	public void add( String cadena, Autor autor )
	{
		char clave[] = cadena.toCharArray();
		sustituyeSimbolosNoAscii7Bits( clave );

		Nodo actual = raiz;
		int	 i = 0;

		while( actual != null && i < clave.length ) {
			
			char simbolo = clave[i];
			Nodo siguiente = actual.getSiguiente( simbolo );

			if ( siguiente == null ) {
				siguiente = new Nodo();
				actual.anyadeSiguiente( siguiente, simbolo );
			}

			actual = siguiente;
			i++;
		}
		if ( actual != null ) actual.setAutor( autor );
	}
	public Autor get( String cadena )
	{
		char clave[] = cadena.toCharArray();
		sustituyeSimbolosNoAscii7Bits( clave );

		Nodo actual = raiz;
		int	 i = 0;

		while( actual != null && i < clave.length ) {
			
			char simbolo = clave[i];
			Nodo siguiente = actual.getSiguiente( simbolo );

			if ( siguiente == null ) return null;

			actual = siguiente;
			i++;
		}

		if ( actual != null ) return actual.getAutor();

		return null;
	}

	public void recorre()
	{
		raiz.recorre( "" );
	}
/*
	public static void main( String args[] )
	{
		Diccionario	dicc = new Diccionario();

		int i=1;
		dicc.add( "SÃ³crates", new Integer(i++) );
		dicc.add( "PlatÃ³n", new Integer(i++) );
		dicc.add( "AristÃ³teles", new Integer(i++) );
		dicc.add( "HerÃ¡clito", new Integer(i++) );
		dicc.add( "DemÃ³crito", new Integer(i++) );
		dicc.add( "PitÃ¡goras", new Integer(i++) );
		dicc.add( "Euclides", new Integer(i++) );
		dicc.add( "EratÃ³stenes", new Integer(i++) );

		dicc.recorre();
	}
*/
}

class Main
{
	static Scanner entrada = new Scanner( System.in );

	public static void main( String args[] )
	{
		int escenario=0;
		int	escenarios = entrada.nextInt();

		while( ++escenario <= escenarios ) {
			
			int P = entrada.nextInt();
			int N = entrada.nextInt();
			entrada.nextLine();

			Diccionario	dicc = new Diccionario();
			// A ErdÃ¶s lo aÃ±adimos a piÃ±Ã³n fijo. Porque nos pueden poner un caso
			// trampa en el que no estÃ© ErdÃ¶s y la bÃºsqueda falle estrepitosamente.
			Autor erdos = new Autor( "Erdos, P." );
			dicc.add( erdos.getNombre(), erdos );

			// Carga de los P artÃ­culos
			for( int p=0; p < P; p++ ) {

				String linea = entrada.nextLine().trim();
				String trozos[] = linea.split( ":" );
				String autores[] = trozos[0].split( "," );

				ArrayList<Autor> lista_autores_este_articulo = new ArrayList<Autor>();

				for( int i=0; i < autores.length; i+=2 ) {
					String nombre_autor = autores[i].trim();
					if ( i+1 < autores.length ) nombre_autor += ", " + autores[i+1].trim();

					Autor autor = dicc.get( nombre_autor );
					if ( autor == null ) {	
						autor = new Autor( nombre_autor );
						dicc.add( nombre_autor, autor );
					}
					lista_autores_este_articulo.add( autor );

				}
				for( int i=0; i < lista_autores_este_articulo.size(); i++ ) {
					Autor a1 = lista_autores_este_articulo.get(i);
					for( int j=i+1; j < lista_autores_este_articulo.size(); j++ ) {
						Autor a2 = lista_autores_este_articulo.get(j);

						if ( a1 != a2 ) {
							// Crear arco entre a1 y a2
							a1.conectar_con( a2 );
							a2.conectar_con( a1 );
						}
					}
				}
			}

			// Carga de los N autores
			LinkedList<Autor> autores_a_probar_en_este_escenario = new LinkedList<Autor>();

			for( int n=0; n < N; n++ ) {
				String nombre_autor = entrada.nextLine().trim();
				Autor a = dicc.get( nombre_autor );
				if ( a == null ) {
					a = new Autor( nombre_autor );
					dicc.add( a.getNombre(), a );
				}
				autores_a_probar_en_este_escenario.add( a );
			}

			// Calcular los nÃºmeros de ErdÃ¶s mediante un Ã¡rbol de expansiÃ³n mÃ­nima

			//We must implement a BFS as if we computed the accesibility matrix of a graph 
			//but taking into account the height which will be the erdos number for that node

			Queue<Autor> authors_adjoining = new LinkedList<>();
			erdos = dicc.get("Erdos, P.");
			erdos.setErdosNumber(0);
			erdos.setEnCola(true);
			authors_adjoining.add(erdos);

			while(!authors_adjoining.isEmpty()){

				Autor a = authors_adjoining.poll();

				//we add to the queue the new nodes and process them
				for (Autor coautor : a.getCoautores()) {
					if (!coautor.getEnCola()) {
						//here we determine his erdos number
						coautor.setErdosNumber(Math.min(a.getErdosNumber() + 1, coautor.getErdosNumber()));
						coautor.setEnCola(true);
						authors_adjoining.add(coautor);
					}
				} 
			}

			// Mostrarlos
			System.out.printf( "Scenario %d\n", escenario );
			for( Autor a : autores_a_probar_en_este_escenario ) {
				System.out.println( a.toString() );
			}
		}
	}
}