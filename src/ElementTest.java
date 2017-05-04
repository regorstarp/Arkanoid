import junit.framework.TestCase;

/**
 * Created by rogerprats on 11/10/2016.
 */
public class ElementTest extends TestCase {
	
	public void testConstructor() throws Exception {
		Element ele = new Element();
		ele = new Element(1,1);
		
		int[] pos = ele.getPos();
		
		assertEquals(1,pos[0]);
		assertEquals(1,pos[1]);
		
        try{
            ele = new Element(-1,2);
        }catch (Exception e){
            assertEquals("fila negativa", e.getMessage());

        }
        try{
            ele = new Element(1,-2);
        }catch (Exception e){
            assertEquals("columna negativa", e.getMessage());

        }
		
	}
	
	
    public void testSetPos() throws Exception {


        Element ele = new Element(0,2);
        int [] pos = ele.getPos();
        assertEquals(0, pos[0]);
        assertEquals(2, pos[1]);
        ele = new Element(2,0);
        pos = ele.getPos();
        assertEquals(2, pos[0]);
        assertEquals(0, pos[1]);

        try{
            ele.setPos(-1, 0);
        }catch (Exception e){
            assertEquals("fila negativa", e.getMessage());

        }
        try{
        	ele.setPos(1,-2);
        }catch (Exception e){
            assertEquals("columna negativa", e.getMessage());

        }
        

    }

    public void testsetPowerUp() throws Exception {
    	Element ele = new Element(0,2);
    	ele.getStrength();
    	ele.collision();
    	
    	ele.setPowerUp(2);
    	assertEquals(2,ele.getPowerUp());
    	
    	 try{
             ele.setPowerUp(-1);
         }catch (Exception e){
             assertEquals("powerup negatiu", e.getMessage());

         }
    }

}