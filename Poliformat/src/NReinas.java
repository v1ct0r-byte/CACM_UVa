
package es.upv.etsinf.backtraking;

class NReinas
{
	private boolean	finalizado; // No se utiliza en este caso porque debemos contar todas las soluciones posibles y no parar en la primera que encontremos
    private int     n;
	private int		numSoluciones;
    private int     solucion[];

	public NReinas()
	{
		numSoluciones=0;
	}

	private boolean esSolucion( int k )
	{
		return ( k == n );
	}
    /*
        En este método se realiza la poda de manera implícita pues no se 
        procesan las columnas de aquellas filas que ya no puede colocarse una reina.
        Este método sólo devuelve como posibles aquellas posiciones de la fila k-esima
        que ninguna de las reinas ya colocadas en las filas de la 0 a la k-1 no la
        alcanzan.
    */
	private void construyeCandidatos( int k, boolean posibles[] )
	{
		for( int i=0; i < n; i++ ) {

			boolean legal = true;

			for( int j=0; j < k && legal; j++ ) {
				if ( i == solucion[j] ) legal = false;
				if ( Math.abs( k - j ) == Math.abs( i - solucion[j] ) ) legal = false;
			}
			posibles[i] = legal;
		}
	}
	private void procesaSolucion()
	{
		numSoluciones++;
	}

	private void backtrack( int k )
	{
		if ( esSolucion( k ) ) {

			procesaSolucion();

		} else {

			boolean posibles[] = new boolean [n];

			construyeCandidatos( k, posibles );
			for( int i=0; i < posibles.length; i++ ) {
				if ( posibles[i] ) {
					solucion[k] = i;
					backtrack( k+1 );
                    solucion[k] = -1;
				}
				if ( finalizado ) return;
			}
		}
	}

	public void calculaSoluciones( int n )
	{
        this.n = n;
		this.finalizado = false;
		this.numSoluciones = 0;
        this.solucion = new int [n];

		backtrack( 0 );
	}
	public int numSoluciones()
	{
		return numSoluciones;
	}

	public static void main( String args[] )
	{
		NReinas	tablero = new NReinas();

		int n = 8;

		if ( args.length > 0 ) n = Integer.parseInt(args[0]);

		tablero.calculaSoluciones( n );

		System.out.println( tablero.numSoluciones() );
	}
}
