/**
 * Created by rogerprats on 27/09/2016.
 */
public class Element {
    public int powerUp;
    public int[] pos = new int[2];
    public Element(){
    }
    public Element(int fila, int col)throws Exception{
    	if (fila < 0){
    		throw new IllegalArgumentException("fila negativa");
    	}
    	if (col < 0){
    		throw new IllegalArgumentException("columna negativa");
    	}
        setPos(fila,col);
       
    }

	public void setPos(int fila, int col) throws Exception{
		if (fila < 0){
    		throw new IllegalArgumentException("fila negativa");
    	}
		if (col < 0){
    		throw new IllegalArgumentException("columna negativa");
    	}
        this.pos[0] = fila;
        this.pos[1] = col;
    }
    public int[] getPos(){
        return this.pos;
    }
    public void collision() throws Exception{
    }
    public void setPowerUp(int powerUp){
    	if (powerUp < 0){
    		throw new IllegalArgumentException("powerup negatiu");
    	}
        this.powerUp=powerUp;
    }
    public int getPowerUp(){
        return this.powerUp;
    }
    public int getStrength() throws Exception{
        return 0;

    }

}
