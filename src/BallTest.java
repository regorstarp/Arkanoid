import junit.framework.TestCase;

/**
 * Created by rogerprats on 11/10/2016.
 */
public class BallTest extends TestCase {
    public void testSetDir() throws Exception {

        try {
        	Ball b = new Ball(-1,0);
        }catch (Exception e){
            assertEquals("fila o columna negativa", e.getMessage());
        }
        try {
        	Ball b = new Ball(1,-7);
        }catch (Exception e){
            assertEquals("fila o columna negativa", e.getMessage());
        }
    	
        Ball b = new Ball(0,2);
        b.setDir(Joc.DOWN, Joc.LEFT);
        int []dir = b.getDir();
        assertEquals(Joc.DOWN, dir[0]);
        assertEquals(Joc.LEFT, dir[1]);
        try {
            b.setDir(-2,1);
        }catch (Exception e){
            assertEquals("Direccio incorrecte", e.getMessage());
        }
        try {
            b.setDir(2,1);
        }catch (Exception e){
            assertEquals("Direccio incorrecte", e.getMessage());
        }
        try {
            b.setDir(1,-2);
        }catch (Exception e){
            assertEquals("Direccio incorrecte", e.getMessage());
        }
        try {
            b.setDir(1,2);
        }catch (Exception e){
            assertEquals("Direccio incorrecte", e.getMessage());
        }
        try {
            b.setDir(1,0);
        }catch (Exception e){
            assertEquals("Direccio incorrecte", e.getMessage());
        }
        try {
            b.setDir(0,1);
        }catch (Exception e){
            assertEquals("Direccio incorrecte", e.getMessage());
        }
    }


    public void testMoveUpRigth() throws Exception {
        Ball b = new Ball(2,2);
        b.moveUpRigth();
        int[] nextpos = new int[2];
        nextpos[0] = 1;
        nextpos[1] = 3;
        int[] pos = new int[2];
        pos = b.getPos();
        assertEquals(nextpos[0], pos[0]);
        assertEquals(nextpos[1], pos[1]);

    }

    public void testMoveUpLeft() throws Exception {
        Ball b = new Ball(2,2);
        b.moveUpLeft();
        int[] nextpos = new int[2];
        nextpos[0] = 1;
        nextpos[1] = 1;
        int[] pos = new int[2];
        pos = b.getPos();
        assertEquals(nextpos[0], pos[0]);
        assertEquals(nextpos[1], pos[1]);
    }

    public void testMoveDownRigth() throws Exception {
        Ball b = new Ball(0,2);
        b.moveDownRigth();
        int[] nextpos = new int[2];
        nextpos[0] = 1;
        nextpos[1] = 3;
        int[] pos = new int[2];
        pos = b.getPos();
        assertEquals(nextpos[0], pos[0]);
        assertEquals(nextpos[1], pos[1]);
    }

    public void testMoveDownLeft() throws Exception {
        Ball b = new Ball(0,2);
        b.moveDownLeft();
        int[] nextpos = new int[2];
        nextpos[0] = 1;
        nextpos[1] = 1;
        int[] pos = new int[2];
        pos = b.getPos();
        assertEquals(nextpos[0], pos[0]);
        assertEquals(nextpos[1], pos[1]);
    }

}