import junit.framework.TestCase;

/**
 * Created by rogerprats on 11/10/2016.
 */
public class BrickTest extends TestCase {
	
	public void testConstructor() throws Exception{
		Brick b = null;
		 try {
             b = new Brick(1,-1,0);
        }catch (Exception e){
            assertEquals("fila negativa", e.getMessage());
        }
		 try {
             b = new Brick(1,5,-1);
        }catch (Exception e){
            assertEquals("columna negativa", e.getMessage());
        }
	}
	
    public void testCollision() throws Exception {
        Brick b = new Brick(3,0,0);
        b.collision();
        assertEquals(2, b.getStrength());
        b.collision();
        assertEquals(1, b.getStrength());
        b.collision();
        assertEquals(0, b.getStrength());

    }


    public void testSetStrength() throws Exception {
        Brick b = new Brick(0,0,0);
        assertEquals(0, b.getStrength());
        b = new Brick(5,0,0);
        assertEquals(5, b.getStrength());
        try {
             b = new Brick(-1,0,0);
        }catch (Exception e){
            assertEquals("strength negativa", e.getMessage());
        }
        b = new Brick(5,0,0);      
        try {
        	b.setStrength(-1);
       }catch (Exception e){
           assertEquals("strength negativa", e.getMessage());
       }
        
    }


    public void testSetpowerUp() throws Exception {
        Brick b = new Brick(0,0,0);
        assertEquals(0,b.getPowerUp());
        b.setPowerUp(1);
        assertEquals(1,b.getPowerUp());
		 try {
             b.setPowerUp(-1);
        }catch (Exception e){
            assertEquals("powerup negatiu", e.getMessage());
        }
    }

}