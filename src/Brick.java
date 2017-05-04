
public class Brick extends Element{
    private int strength;
    private int powerUp;
    public Brick(int strength, int fila, int col)throws Exception{
    	if (strength < 0){
    		throw new IllegalArgumentException("strength negativa");
    	}
    	if (fila < 0){
    		throw new IllegalArgumentException("fila negativa");
    	}
    	if (col < 0){
    		throw new IllegalArgumentException("columna negativa");
    	}
        setStrength(strength);
        setPowerUp(0);
        this.setPos(fila,col);
    }
	public int getStrength() {

        return strength;
    }
    public void setStrength(int strength) throws Exception {
        if (strength  < 0){
            throw new Exception("strength negativa");
        }
        this.strength = strength;
    }
    public int getPowerUp(){
        return super.getPowerUp();
    }
    public void setPowerUp(int powerUp){
    	if (powerUp < 0){
    		throw new IllegalArgumentException("powerup negatiu");
    	}
        super.setPowerUp(powerUp);

    }
    public void collision() throws Exception{
        setStrength(getStrength()-1);
    }

}
