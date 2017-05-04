/**
 * Created by rogerprats on 27/09/2016.
 */
public class Ball extends Element {
    private int[] dir = new int[2];
    public Ball(int fila, int col)throws Exception{
    	//pre
    	if (fila < 0 || col < 0){
    		throw new IllegalArgumentException("fila o columna negativa");
    	}
        setPos(fila,col);
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;

    }

    public void setDir(int yDir, int xDir) throws Exception{
    	//pre
        if (yDir != Joc.DOWN && yDir != Joc.UP || xDir != Joc.LEFT && xDir != Joc.RIGHT){
        	throw new IllegalArgumentException("Direccio incorrecte");
        }
        dir[0] = yDir;
        dir[1] = xDir;
    }
    public int[] getDir(){
        return dir;
    }

    public void moveUpRigth() throws Exception{
        setPos(pos[0] + Joc.UP, pos[1] + Joc.RIGHT);
        checkInvariants();
    }
    public void moveUpLeft() throws Exception{
        setPos(pos[0] + Joc.UP, pos[1] + Joc.LEFT);
        checkInvariants();
    }
    public void moveDownRigth()throws Exception{
        setPos(pos[0] + Joc.DOWN, pos[1] + Joc.RIGHT);
        checkInvariants();
    }
    public void moveDownLeft()throws Exception{
        setPos(pos[0] + Joc.DOWN, pos[1] + Joc.LEFT);
        checkInvariants();
    }

	private void checkInvariants() {
		
	}
}
