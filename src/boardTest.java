import junit.framework.TestCase;


public class boardTest extends TestCase {
	public Board b = null;
	int[] pos = null;
	int []dir = null;
	
	public void setUp() throws Exception{
		b = new Board(30,30);
		pos = new int[2];
		dir = new int[2];
	}
	
    public void testCollisionBrick() throws Exception {

        setUp();
        //prova trencar bricks de les 4 cantonades
        //trencar brick de pos 0 0
        pos[0] = 0;
        pos[1] = 0;
        b.boardMatrix[pos[0]][pos[1]].setPowerUp(0);
        assertEquals(Brick.class, b.boardMatrix[pos[0]][pos[1]].getClass());
        b.collisionBrick(pos);
        assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Brick);
        //trencar brick de pos 0 14
        pos[0] = 0;
        pos[1] = 14;
        b.boardMatrix[pos[0]][pos[1]].setPowerUp(0);
        assertEquals(Brick.class, b.boardMatrix[pos[0]][pos[1]].getClass());
        b.collisionBrick(pos);
        assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Brick);
        //trencar brick de pos 14 0
        pos[0] = 14;
        pos[1] = 0;
        b.boardMatrix[pos[0]][pos[1]].setPowerUp(0);
        assertEquals(Brick.class, b.boardMatrix[pos[0]][pos[1]].getClass());
        b.collisionBrick(pos);
        assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Brick);
        //trencar brick de pos 14 14
        pos[0] = 14;
        pos[1] = 14;
        b.boardMatrix[pos[0]][pos[1]].setPowerUp(0);
        assertEquals(Brick.class, b.boardMatrix[pos[0]][pos[1]].getClass());
        b.collisionBrick(pos);
        assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Brick);
        //trencar brick de pos 5 5 (bick en mig del board)
        pos[0] = 5;
        pos[1] = 5;
        b.boardMatrix[pos[0]][pos[1]].setPowerUp(0);
        assertEquals(Brick.class, b.boardMatrix[pos[0]][pos[1]].getClass());
        b.collisionBrick(pos);
        assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Brick);
        //intent de aplicar la funcio collisionBrick a una posicio on no hi ha Brick
        try{
            pos[0] = 17;
            pos[1] = 17;
            assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Brick);
            b.collisionBrick(pos);
        }catch(Exception e){
        	assertEquals("Collision brick en un element que no es un Brick",e.getMessage());
        }
        //passar null a la funcio
        try{
            b.collisionBrick(null);
        }catch(Exception e){
        	assertEquals("posicio null",e.getMessage());
        }
        //comprovar que els powerups s'activen
        //powerup de la bola, comprovem que de moment nomes hi ha una bola
        assertEquals(null,b.ball2);
        pos[0] = 6;
        pos[1] = 6;
        b.boardMatrix[pos[0]][pos[1]].setPowerUp(1);
        assertEquals(Brick.class, b.boardMatrix[pos[0]][pos[1]].getClass());
        //xoquem amb el brick
        b.collisionBrick(pos);
        assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Brick);
        //comprovem que s'ha activat la segona bola
        assertEquals(Ball.class,b.ball2.getClass());
        //provem a xocar amb un altre brick amb powerup mentre esta la segona bola activa
        pos[0] = 7;
        pos[1] = 7;
        b.boardMatrix[pos[0]][pos[1]].setPowerUp(1);
        assertEquals(Brick.class, b.boardMatrix[pos[0]][pos[1]].getClass());
        //xoquem amb el brick
        b.collisionBrick(pos);
        assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Brick);
        //comprovem que la segona bola segueix activada
        assertEquals(Ball.class,b.ball2.getClass());
        //prova brick strength = 2
        pos[0] = 2;
        pos[1] = 2;
        Brick brick = new Brick(2,pos[0],pos[1]);
        b.boardMatrix[pos[0]][pos[1]] = brick;
        b.collisionBrick(pos);
        brick.setPowerUp(0);
        b.collisionBrick(pos);
        b.boardMatrix[pos[0]][pos[1]] = new Element(2,2);
        try{
        	b.collisionBrick(pos);
        }catch (Exception e){
        	assertEquals("Collision brick en un element que no es un Brick", e.getMessage());
        }
        
    }

    public void testCorrectMovement() throws Exception {
   
    	setUp();
        //xoca dreta dir up
        pos[0] = 27;
        pos[1] = b.boardMatrix[0].length-1;
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;
        b.ball.setDir(dir[0], dir[1]);
        b.correctMovement(pos,dir,b.ball);
        int [] dir1 = b.ball.getDir();
        assertEquals(Joc.UP,dir1[0]);
        assertEquals(Joc.LEFT,dir1[1]);
        //xoca dreta dir down
        pos[0] = 27;
        pos[1] = b.boardMatrix[0].length-1;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        b.ball.setDir(dir[0], dir[1]);
        b.correctMovement(pos,dir,b.ball);
        dir1 = b.ball.getDir();
        assertEquals(Joc.DOWN,dir1[0]);
        assertEquals(Joc.LEFT,dir1[1]);
        //xoca esquerra dir up
        pos[0] = 27;
        pos[1] = 0;
        dir[0] = Joc.UP;
        dir[1] = Joc.LEFT;
        b.ball.setDir(dir[0], dir[1]);
        b.correctMovement(pos,dir,b.ball);
        dir1 = b.ball.getDir();
        assertEquals(Joc.UP,dir1[0]);
        assertEquals(Joc.RIGHT,dir1[1]);
        //xoca esquerra dir down
        pos[0] = 27;
        pos[1] = 0;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.LEFT;
        b.ball.setDir(dir[0], dir[1]);
        b.correctMovement(pos,dir,b.ball);
        dir1 = b.ball.getDir();
        assertEquals(Joc.DOWN,dir1[0]);
        assertEquals(Joc.RIGHT,dir1[1]);
        //xoca abaix dir right
        pos[0] = b.boardMatrix.length -1;
        pos[1] = 4;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        b.ball.setDir(dir[0], dir[1]);
        b.correctMovement(pos,dir,b.ball);
        dir1 = b.ball.getDir();
        assertEquals(Joc.UP,dir1[0]);
        assertEquals(Joc.RIGHT,dir1[1]);
        //xoca abaix dir left
        pos[0] = b.boardMatrix.length -1;
        pos[1] = 4;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.LEFT;
        b.ball.setDir(dir[0], dir[1]);
        b.correctMovement(pos,dir,b.ball);
        dir1 = b.ball.getDir();
        assertEquals(Joc.UP,dir1[0]);
        assertEquals(Joc.LEFT,dir1[1]);
        
        //posicio, dir o Ball null
        try{
        	b.correctMovement(null, dir,b.ball);
        }catch (Exception e) {
        	 assertEquals("posicio null", e.getMessage());
        }
        try{
        	b.correctMovement(pos, null,b.ball);
        }catch (Exception e) {
        	 assertEquals("direccio null", e.getMessage());
        }
        try{
        	b.correctMovement(pos, dir, null);
        }catch (Exception e) {
        	 assertEquals("ball null", e.getMessage());
        }
        
      //caixa blanca
        int[] auxdir = null;
        //colisio amb parets
        pos[0] = 0;
        pos[1] = 3;
        dir[0] = Joc.UP;
        dir[1] = Joc.LEFT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.boardMatrix[1][4] = new Element(1,4);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.DOWN,auxdir[0]);
        assertEquals(Joc.RIGHT,auxdir[1]);
        
        pos[0] = 0;
        pos[1] = 3;
        dir[0] = Joc.UP;
        dir[1] = Joc.LEFT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.boardMatrix[1][2] = new Element(1,2);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.DOWN,auxdir[0]);
        assertEquals(Joc.LEFT,auxdir[1]);
        
        pos[0] = 3;
        pos[1] = 29;
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.boardMatrix[4][28] = new Element(4,28);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.DOWN,auxdir[0]);
        assertEquals(Joc.LEFT,auxdir[1]);
        
        pos[0] = 3;
        pos[1] = 0;
        dir[0] = Joc.UP;
        dir[1] = Joc.LEFT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.boardMatrix[4][1] = new Element(4,1);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.DOWN,auxdir[0]);
        assertEquals(Joc.RIGHT,auxdir[1]);
        
        //colisio amb brick
        //cas up
        pos[0] = 25;
        pos[1] = 20;
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.DOWN,auxdir[0]);
        assertEquals(Joc.RIGHT,auxdir[1]);
        
        pos[0] = 20;
        pos[1] = 20;
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.boardMatrix[21][21] = new Brick(1, 21,21);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.DOWN,auxdir[0]);
        assertEquals(Joc.LEFT,auxdir[1]);
        
        pos[0] = 20;
        pos[1] = 20;
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.boardMatrix[21][21] = new Brick(1, 21,21);
        b.boardMatrix[21][19] = new Brick(1, 21,19);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.UP,auxdir[0]);
        assertEquals(Joc.LEFT,auxdir[1]);
        
        //cas down
        pos[0] = 25;
        pos[1] = 20;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.UP,auxdir[0]);
        assertEquals(Joc.RIGHT,auxdir[1]);
        
        pos[0] = 25;
        pos[1] = 20;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.boardMatrix[24][21] = new Brick(1, 24,21);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.UP,auxdir[0]);
        assertEquals(Joc.LEFT,auxdir[1]);
        
        pos[0] = 25;
        pos[1] = 20;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.boardMatrix[24][19] = new Brick(1, 24,19);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.DOWN,auxdir[0]);
        assertEquals(Joc.LEFT,auxdir[1]);
        
        //cas right
        pos[0] = 25;
        pos[1] = 20;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.DOWN,auxdir[0]);
        assertEquals(Joc.LEFT,auxdir[1]);
        
        //cas xoca amb la part de abaix (cas imposible en el joc)
        pos[0] = 29;
        pos[1] = 27;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.boardMatrix[28][28] = new Brick(1, 21,21);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.UP,auxdir[0]);
        assertEquals(Joc.LEFT,auxdir[1]);
        
        //cas cantonada adalt esquerra
        pos[0] = 0;
        pos[1] = 0;
        dir[0] = Joc.UP;
        dir[1] = Joc.LEFT;
        b.ball.setPos(pos[0], pos[1]);
        b.ball.setDir(dir[0], dir[1]);
        b.boardMatrix[1][1] = new Element(1,1);
        b.correctMovement(b.ball.getPos(), b.ball.getDir(), b.ball);
        auxdir = b.ball.getDir();
        assertEquals(Joc.DOWN,auxdir[0]);
        assertEquals(Joc.RIGHT,auxdir[1]);

    }


    public void testPossibleMovement() throws Exception {

    	setUp();
        pos[0] = 27;
        pos[1] = 27;

        //27,27
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        assertTrue(b.possibleMovement(pos, dir));
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;
        assertTrue(b.possibleMovement(pos, dir));
        dir[0] = Joc.UP;
        dir[1] = Joc.LEFT;
        assertTrue(b.possibleMovement(pos, dir));
        dir[0] = Joc.DOWN;
        dir[1] = Joc.LEFT;
        assertTrue(b.possibleMovement(pos, dir));

        //0, max amb brick
        pos[0] = 0;
        pos[1] = b.boardMatrix[0].length -1;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.LEFT;
        assertFalse(b.possibleMovement(pos, dir));
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;
        assertFalse(b.possibleMovement(pos, dir));
        dir[0] = Joc.UP;
        dir[1] = Joc.LEFT;
        assertFalse(b.possibleMovement(pos, dir));
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        assertFalse(b.possibleMovement(pos, dir));

        //max, max
        pos[0] = b.boardMatrix.length -1;
        pos[1] = b.boardMatrix[0].length -1;
        dir[0] = Joc.UP;
        dir[1] = Joc.LEFT;
        assertTrue(b.possibleMovement(pos, dir));
        dir[0] = Joc.DOWN;
        dir[1] = Joc.LEFT;
        assertFalse(b.possibleMovement(pos, dir));
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;
        assertFalse(b.possibleMovement(pos, dir));
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        assertFalse(b.possibleMovement(pos, dir));

        //max, 0
        pos[0] = b.boardMatrix.length -1;
        pos[1] = 0;
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;
        assertTrue(b.possibleMovement(pos, dir));
        dir[0] = Joc.DOWN;
        dir[1] = Joc.LEFT;
        assertFalse(b.possibleMovement(pos, dir));
        dir[0] = Joc.UP;
        dir[1] = Joc.LEFT;
        assertFalse(b.possibleMovement(pos, dir));
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        assertFalse(b.possibleMovement(pos, dir));

        //max/2, 0
        pos[0] = (b.boardMatrix.length -1) / 2;
        pos[1] = 0;
        dir[0] = Joc.UP;
        dir[1] = Joc.RIGHT;
        assertFalse(b.possibleMovement(pos, dir));
        dir[0] = Joc.DOWN;
        dir[1] = Joc.LEFT;
        assertFalse(b.possibleMovement(pos, dir));
        dir[0] = Joc.UP;
        dir[1] = Joc.LEFT;
        assertFalse(b.possibleMovement(pos, dir));
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        assertTrue(b.possibleMovement(pos, dir));

        //slider
        pos[0] = b.boardMatrix.length -2;
        pos[1] = 1;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.LEFT;
        assertFalse(b.possibleMovement(pos, dir));
        pos[0] = b.boardMatrix.length -2;
        pos[1] = 0;
        dir[0] = Joc.DOWN;
        dir[1] = Joc.RIGHT;
        b.ball.setPos(pos[0], pos[1]);
        assertFalse(b.possibleMovement(pos, dir));
        
       //si posicio o dir es null
        try{
        	b.possibleMovement(null, dir);
        }catch (Exception e) {
        	 assertEquals("posicio null", e.getMessage());
        }
        try{
        	b.possibleMovement(pos, null);
        }catch (Exception e) {
        	 assertEquals("direccio null", e.getMessage());
        }

    }

    public void testBoard() throws Exception {
        Board board= new Board(5, 30);
        assertEquals(5,board.boardMatrix.length);
        assertEquals(30,board.boardMatrix[0].length);
        board= new Board(6,30);
        assertEquals(6,board.boardMatrix.length);
        assertEquals(30,board.boardMatrix[0].length);
        board= new Board(5,29);
        assertEquals(5,board.boardMatrix.length);
        assertEquals(29,board.boardMatrix[0].length);

        board= new Board(30, 5);
        assertEquals(30,board.boardMatrix.length);
        assertEquals(5,board.boardMatrix[0].length);
        board= new Board(30,6);
        assertEquals(30,board.boardMatrix.length);
        assertEquals(6,board.boardMatrix[0].length);
        board= new Board(29,5);
        assertEquals(29,board.boardMatrix.length);
        assertEquals(5,board.boardMatrix[0].length);

        try{
            board= new Board(4, 30);
        } catch (Exception e) {
            assertEquals("Rang de la mida de files i columnes (5-30)", e.getMessage());
        }
        try{
            board= new Board(30, 4);
        } catch (Exception e) {
            assertEquals("Rang de la mida de files i columnes (5-30)", e.getMessage());
        }
        try{
            board= new Board(5, 31);
        } catch (Exception e) {
            assertEquals("Rang de la mida de files i columnes (5-30)", e.getMessage());
        }
        try{
            board= new Board(31, 5);
        } catch (Exception e) {
            assertEquals("Rang de la mida de files i columnes (5-30)", e.getMessage());
        }
        try{
            board= new Board(-3, 30);
        } catch (Exception e) {
            assertEquals("Rang de la mida de files i columnes (5-30)", e.getMessage());
        }
        try{
            board= new Board(30, -3);
        } catch (Exception e) {
            assertEquals("Rang de la mida de files i columnes (5-30)", e.getMessage());
        }
        try{
            board= new Board(5, 40);
        } catch (Exception e) {
            assertEquals("Rang de la mida de files i columnes (5-30)", e.getMessage());
        }
        try{
            board= new Board(40, 5);
        } catch (Exception e) {
            assertEquals("Rang de la mida de files i columnes (5-30)", e.getMessage());
        }
    }
    public void testSetBoard() throws Exception{
    	
    	int files = 30;
    	int columnes = 30;
    	
    	setUp();
    	
        for (int f=0; f < 30; f++){
            for (int c=0; c< columnes; c++){
                if (f==files-2 && c==0){
                    assertTrue(b.boardMatrix[f][c] instanceof Ball);
                }
                else if (f==files-1 && c==0){
                    assertTrue(b.boardMatrix[f][c] instanceof Slider);
                }
                else if (f < files/2){
                    assertTrue(b.boardMatrix[f][c] instanceof Brick);
                }
                else {
                    assertTrue(b.boardMatrix[f][c] != null);
                }

            }
        }
    }
    public void testMoveBall() throws Exception{

    	setUp();
        b.moveBall();
        //moviment amunt dreta
        pos = b.ball.getPos();
        assertEquals(27,pos[0]);
        assertEquals(1,pos[1]);
        //moviment amunt dreta
        b.moveBall();
        pos = b.ball.getPos();
        assertEquals(26,pos[0]);
        assertEquals(2,pos[1]);
        //moviment amunt esquerra
        b.ball.setDir(Joc.UP, Joc.LEFT);
        b.moveBall();
        pos = b.ball.getPos();
        assertEquals(25,pos[0]);
        assertEquals(1,pos[1]);
        //moviment avall esquerra
        b.ball.setDir(Joc.DOWN, Joc.LEFT);
        b.moveBall();
        pos = b.ball.getPos();
        assertEquals(26,pos[0]);
        assertEquals(0,pos[1]);
        //moviment avall dreta
        b.ball.setDir(Joc.DOWN, Joc.RIGHT);
        b.moveBall();
        pos = b.ball.getPos();
        assertEquals(27,pos[0]);
        assertEquals(1,pos[1]);
        b.ball.setPos(29, 0);
        b.ball.setDir(Joc.DOWN, Joc.LEFT);
        b.moveBall();
        b.boardMatrix[27][2] = new Brick(1,27,2);
        b.moveBall();
    }
    
    public void testmoveSlider() throws Exception{

    	setUp();
    	pos = b.slider.getPos();
    	assertEquals(29,pos[0]);
    	assertEquals(0,pos[1]);
    	//intentem moure a l'esquerra (no es pot)
    	b.moveSlider(Joc.LEFT);
    	pos = b.slider.getPos();
    	assertEquals(29,pos[0]);
    	assertEquals(0,pos[1]);
    	//movem a la dreta
    	b.moveSlider(Joc.RIGHT);
    	pos = b.slider.getPos();
    	assertEquals(29,pos[0]);
    	assertEquals(1,pos[1]);
    	//movem a la dreta
    	b.moveSlider(Joc.RIGHT);
    	pos = b.slider.getPos();
    	assertEquals(29,pos[0]);
    	assertEquals(2,pos[1]);
    	//movem a l'esquerra
    	b.moveSlider(Joc.LEFT);
    	pos = b.slider.getPos();
    	assertEquals(29,pos[0]);
    	assertEquals(1,pos[1]);
    	//posem el slider a la posicio mes a la dreta possible
    	b.slider.setPos(29, 29);
    	//intentem moure a la dreta
    	b.moveSlider(Joc.RIGHT);
    	pos = b.slider.getPos();
    	assertEquals(29,pos[0]);
    	assertEquals(29,pos[1]);
    	//movem a l'esquerra
    	b.moveSlider(Joc.LEFT);
    	pos = b.slider.getPos();
    	assertEquals(29,pos[0]);
    	assertEquals(28,pos[1]);
    	//intentem passar valors sense sentit
    	b.moveSlider(45);
    	pos = b.slider.getPos();
    	assertEquals(29,pos[0]);
    	assertEquals(28,pos[1]);
    	b.moveSlider(-25);
    	pos = b.slider.getPos();
    	assertEquals(29,pos[0]);
    	assertEquals(28,pos[1]);
    	b.moveSlider(0);
    	pos = b.slider.getPos();
    	assertEquals(29,pos[0]);
    	assertEquals(28,pos[1]);
    }

    
    public void testcheckVides() throws Exception{
    	
    	setUp();
    	//cas en que ball2 arriba al final
    	//fem que hi hagi el powerup de dues boles
    	pos[0] = 6;
        pos[1] = 6;
        b.boardMatrix[pos[0]][pos[1]].setPowerUp(1);
        b.collisionBrick(pos);
        //posem la ball 2 al final
    	pos[0] = 28;
        pos[1] = 29;
       	b.ball2.setPos(pos[0], pos[1]);
       	b.ball2.setDir(Joc.DOWN, Joc.LEFT);
       	b.boardMatrix[pos[0]][pos[1]] = b.ball2;
       	b.moveBall();
       	assertEquals(null,b.ball2);
       	
       	//cas en que ball(1) arriba al final
       	//fem que hi hagi el powerup de dues boles
    	pos[0] = 7;
        pos[1] = 7;
        b.boardMatrix[pos[0]][pos[1]].setPowerUp(1);
        b.collisionBrick(pos);
    	//posem la ball(1) al final
        pos[0] = 28;
        pos[1] = 29;
       	b.ball.setPos(pos[0], pos[1]);
       	b.ball.setDir(Joc.DOWN, Joc.LEFT);
       	b.boardMatrix[pos[0]][pos[1]] = b.ball;
       	b.moveBall();
       	assertEquals(null,b.ball2);
       	assertTrue(b.ball instanceof Ball);
       	
       	//cas en que quedaran 0 vides (es perd 3 cops)
       	b.ball.setPos(pos[0], pos[1]);
       	b.ball.setDir(Joc.DOWN, Joc.LEFT);
       	b.boardMatrix[pos[0]][pos[1]] = b.ball;
       	b.moveBall();
       	b.ball.setPos(pos[0], pos[1]);
       	b.ball.setDir(Joc.DOWN, Joc.LEFT);
       	b.boardMatrix[pos[0]][pos[1]] = b.ball;
       	b.moveBall();
    	b.ball.setPos(pos[0], pos[1]);
       	b.ball.setDir(Joc.DOWN, Joc.LEFT);
       	b.boardMatrix[pos[0]][pos[1]] = b.ball;
       	b.moveBall();
       	assertEquals(0,b.vides);
        

    }
    
   	public void testmoveBala() throws Exception{
   		setUp();
   		//activem el powerup 2
   		pos[0] = 1;
   		pos[1] = 1;
   		b.boardMatrix[pos[0]][pos[1]].setPowerUp(2);
   		b.collisionBrick(pos);
   		assertEquals(20,b.powerUp2);
   		
   		
   		//simulem que es dispara una bala i cas normal (es mou la bala)
   		b.activeBala = new Bala(27,5);
   		b.moveBala();
   		pos = b.activeBala.getPos();
   		assertEquals(26,pos[0]);
   		
   		//cas en que hi ha un brick adalt (el trenca i esborra la bala)
   		b.boardMatrix[pos[0]-1][pos[1]] = new Brick(1,pos[0]-1,pos[1]);
   		b.moveBala();
   		assertEquals(null,b.activeBala);
   		assertFalse(b.boardMatrix[pos[0]-1][pos[1]] instanceof Brick);
   		assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Bala);
   		
   		//cas en que la bala ha viatjat fins al limit de la matriu
   		pos[0] = 0;
   		pos[1] = 0;
   		b.activeBala = new Bala(pos[0],pos[1]);
   		b.boardMatrix[0][0] = new Bala(pos[0],pos[1]);
   		b.moveBala();
   		assertEquals(null,b.activeBala);
   		assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Bala);
   		
   		//cas en que acaba el timer del powerup i hi ha una bala activa
   		pos[0] = 23;
   		pos[1] = 23;
   		b.activeBala = new Bala(pos[0],pos[1]);
   		b.boardMatrix[0][0] = new Bala(pos[0],pos[1]);
   		b.powerUp2 = 1;
   		b.moveBall();
   		assertEquals(null,b.activeBala);
   		assertFalse(b.boardMatrix[pos[0]][pos[1]] instanceof Bala);
   	}
}
