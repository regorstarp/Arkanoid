/**
 * Created by rogerprats on 27/09/2016.
 */
public class Slider extends Element{
    public Slider(int fila, int col) throws Exception{
    	//pre
    	if (fila < 0 || col < 0){
    		throw new IllegalArgumentException("fila o columna negativa");
    	}
        this.setPos(fila,col);
    }
}