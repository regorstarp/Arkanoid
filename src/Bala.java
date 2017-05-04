
public class Bala extends Element {
	
	public Bala(int fila, int col)throws Exception{
	//pre
	if (fila < 0){
		throw new IllegalArgumentException("fila negativa");
	}
	if (col < 0){
		throw new IllegalArgumentException("columna negativa");
	}
    setPos(fila,col);
	
	}
}
