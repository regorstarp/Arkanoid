import java.util.Random;

public class Board {
    public Element[][] boardMatrix;
    public Ball ball;
    public Slider slider;
    public Ball ball2 = null;
    public int files = 0;
    public int vides = 3;
	public boolean gameOver = false;
	public Bala activeBala = null;
	public int powerUp2 = 0;
	
    public Board(int files, int columnes) throws Exception{
        if (files < 5 || columnes < 5 || files > 30 || columnes > 30){
            throw new Exception("Rang de la mida de files i columnes (5-30)");
        }
        this.files = files;
        setBoard(files , columnes);
    }
    public void setBoard(int files, int columnes) throws Exception{
    	Random random = new Random();
    	int randomNumber;
        this.boardMatrix = new Element[files][columnes];
        for (int f = 0; f < files; f++){
            for (int c = 0; c < columnes; c++) {
                if (f < files/2) {
                    Brick b = new Brick(1, f, c);
                    //hi ha una probabilitat de 1 entre 10 de que tingui powerup
                    random = new Random();
                	randomNumber = random.nextInt(10);
                	if(randomNumber == 9){
                		b.setPowerUp(1);
                	}
                	if(randomNumber == 8){
                		b.setPowerUp(2);
                	}
                    
                    this.boardMatrix[f][c] = b;
                } else {
                    Element e = new Element(f, c);
                    this.boardMatrix[f][c] = e;
                }
            }
        }
        //ball
        this.ball = new Ball(files-2,0);
        this.boardMatrix[files-2][0] = this.ball;

        //Slider
        this.slider = new Slider(files-1,0);
        this.boardMatrix[files-1][0] = this.slider;

        //brick amb powerup	
        this.boardMatrix[(files/2)-1][7].setPowerUp(2);
    }

    public void moveBall() throws Exception{
    	
    	if(this.powerUp2>0)
    		--this.powerUp2;   	
    	moveBala();
    	
        Ball ball = null;
        int i = 0;
        while( i < 2){
            if (i == 0){
                ball = this.ball;
            }
            else {
                ball = this.ball2;
            }
            if (ball != null){
                int[] pos = ball.getPos().clone();
                int[] dir = ball.getDir().clone();
                int []nextPos = new int[2];
                nextPos[0] = pos[0] + dir[0];
                nextPos[1] = pos[1] + dir[1];

                if (!possibleMovement(pos, dir)){
                    correctMovement(pos,dir,ball);
                }
                if (nextPos[0] >= 0 && nextPos[0] < this.boardMatrix.length && nextPos[1] >= 0
                        && nextPos[1] < this.boardMatrix[0].length){
                    if (this.boardMatrix[nextPos[0]][nextPos[1]] instanceof Brick){
                        collisionBrick(nextPos);
                    }
                }


                dir = ball.getDir().clone();
                //amunt i dreta
                if (dir[0] == Joc.UP && dir[1] == Joc.RIGHT) {
                    ball.moveUpRigth();
                }
                //amunt i esquerra
                else if (dir[0]  == Joc.UP && dir[1] == Joc.LEFT) {
                    ball.moveUpLeft();
                }
                //avall i dreta
                else if (dir[0] == Joc.DOWN && dir[1] == Joc.RIGHT) {
                    ball.moveDownRigth();
                }
                //avall i esquerra
                else {
                    if (dir[0] == Joc.DOWN && dir[1] == Joc.LEFT) 
                    	ball.moveDownLeft();
                }
                int[] pos1 = ball.getPos();

                Ball temp = ball;

                this.boardMatrix[pos[0]][pos[1]] = new Element(pos[0],pos[1]);
                this.boardMatrix[pos1[0]][pos1[1]] = temp;
            }
            i +=1;
        }
        checkVides();

    }

    private void checkVides() throws Exception {
    	int pos[];
    	//cas en que estan les 2 balls actives
		if (this.ball2 !=null){
			pos = this.ball2.getPos();
			if (pos[0] == files -1){
				this.boardMatrix[pos[0]][pos[1]] = new Element(pos[0],pos[1]);
				this.ball2 = null;
			}
			pos = this.ball.getPos();
			if (pos[0] == files -1){
				this.boardMatrix[pos[0]][pos[1]] = new Element(pos[0],pos[1]);
				this.ball = this.ball2;
				this.ball2 = null;
			}
		}
		//cas en que nomes hi ha una ball activa
		pos = this.ball.getPos();
		if (pos[0] == files -1){
			this.boardMatrix[pos[0]][pos[1]] = new Element(pos[0],pos[1]);
			this.vides -= 1;
			if (this.vides == 0){
				gameIsOver();
			}
			//ball
	        this.ball = new Ball(files-2,0);
	        this.boardMatrix[files-2][0] = this.ball;
		}
		
	}
	private void gameIsOver() {
		this.gameOver  = true;
		
	}
	public void correctMovement(int[] pos, int[] dir, Ball ball) throws Exception{
		if (pos == null){
    		throw new IllegalArgumentException("posicio null");
    	}
		if (dir == null){
    		throw new IllegalArgumentException("direccio null");
    	}
		if (ball == null){
    		throw new IllegalArgumentException("ball null");
    	}
        int []nextPos = new int[2];
        nextPos[0] = pos[0] + dir[0];
        nextPos[1] = pos[1] + dir[1];
        boolean visited = false;

        int[] prevPos = new int[2];
        prevPos[0] = pos[0] + dir[0]*(-1);


        //CASOS AMB QUE XOCA AMB LA PARET (LIMIT MATRIU)
        //xoca a dalt
        if (nextPos[0] < 0 && !(nextPos[1] < 0)){
            dir[0] *= -1;
            if (possibleMovement(pos, dir)) {
                ball.setDir(dir[0], dir[1]);
            }
            else{
                dir[1] *= -1;
                if (possibleMovement(pos,dir)) {
                    ball.setDir(dir[0], dir[1]);
                }
            }

            visited = true;
            //else

        }
        //xoca a dreta
        if (nextPos[1] >= boardMatrix[0].length){
            dir[1] *= -1;
            if (possibleMovement(pos, dir)) {
                ball.setDir(dir[0], dir[1]);
            }
            else{
                dir[0] *= -1;
                if (possibleMovement(pos,dir)) {
                    ball.setDir(dir[0], dir[1]);
                }
            }
            visited = true;
        }
        //xoca a esquerra
        if (nextPos[1] < 0){
            dir[1] *= -1;
            if (possibleMovement(pos, dir)) {
                ball.setDir(dir[0], dir[1]);
            }
            else{
                dir[0] *= -1;
                if (possibleMovement(pos,dir)) {
                    ball.setDir(dir[0], dir[1]);
                }
            }
            visited = true;
        }
        //xoca a baix
        if (nextPos[0] >= boardMatrix.length){
            dir[0] *= -1;
            if (possibleMovement(pos, dir)) {
                ball.setDir(dir[0], dir[1]);
            }
            else{
                dir[1] *= -1;
                if (possibleMovement(pos,dir)) {
                    ball.setDir(dir[0], dir[1]);
                }

            }
            visited = true;
        }

        if (!visited){
        	//casos en que no xoca amb la paret
            //up
            if (pos[0] > nextPos[0]){
                dir[0] *= -1;
                if (possibleMovement(pos, dir)) {
                    ball.setDir(dir[0], dir[1]);
                }
                else{
                    dir[1] *= -1;
                    if (possibleMovement(pos,dir)) {
                        ball.setDir(dir[0], dir[1]);
                    }
                    else{
                        dir[0] *= -1;
                        if (possibleMovement(pos,dir)){
                            ball.setDir(dir[0], dir[1]);
                        }
                    }
                }
            }
            //down
            else{
                dir[0] *= -1;
                if (possibleMovement(pos, dir)) {
                    ball.setDir(dir[0], dir[1]);
                }
                else{
                    dir[1] *= -1;
                    if (possibleMovement(pos,dir)) {
                        ball.setDir(dir[0], dir[1]);
                    }
                    else{
                        dir[0] *= -1;
                        if (possibleMovement(pos,dir)){
                            ball.setDir(dir[0], dir[1]);
                        }
                    }
                }
            }
        }



    }

    public boolean possibleMovement(int []pos, int[]dir) throws Exception{
    	if (pos == null){
    		throw new IllegalArgumentException("posicio null");
    	}
    	if (dir == null){
    		throw new IllegalArgumentException("direccio null");
    	}
        int []nextPos = new int[2];
        nextPos[0] = pos[0] + dir[0];
        nextPos[1] = pos[1] + dir[1];
        if (nextPos[0] < 0 || nextPos[0] >= boardMatrix.length || nextPos[1] < 0
                || nextPos[1] >= boardMatrix[0].length){
            return false;
        }
        //col·lisio amb brick
        if (this.boardMatrix[nextPos[0]][nextPos[1]] instanceof Brick){

            return false;
        }

        if (this.boardMatrix[nextPos[0]][nextPos[1]] instanceof Slider){
            return false;
        }
        if(pos[0] == this.boardMatrix.length-2){
            if (this.boardMatrix[pos[0]+1][pos[1]] instanceof Slider && dir[0]==Joc.DOWN){
                return false;
            }


        }

            return true;
    }
    public void collisionBrick(int[] pos) throws Exception{
    	//pre
    	if (pos == null){
    		throw new IllegalArgumentException("posicio null");
    	}
        Element b = this.boardMatrix[pos[0]][pos[1]];
        if(!(b instanceof Brick)){
        	throw new Exception("Collision brick en un element que no es un Brick");
        }
        b.collision();
        if (b.getStrength() <= 0){
            int power = b.getPowerUp();
            switch (power){
                case 0:
                    break;
                    
                case 1: 
                	//powerup dues boles
                	//si ja hi ha 2 boles no en creem mes
                	if(this.ball2 == null){
                        this.ball2 = new Ball(files-2, 1);
                	}
                    break;
                    
                case 2:
                	//powerUp disparar des de slider amb barra espaiadora
                	this.powerUp2 = 20;

            }
            this.boardMatrix[pos[0]][pos[1]] = new Element(pos[0],pos[1]);
        }

    }
    public void moveSlider(int dir) throws Exception{
        int [] pos = this.slider.getPos().clone();
        if ((pos[1] > 0 && dir == Joc.LEFT) || (pos[1] < this.boardMatrix[0].length-1 && dir == Joc.RIGHT)){
            this.slider.setPos(pos[0] , pos[1] + dir);
            int [] pos1 = this.slider.getPos().clone();
            Slider temp = this.slider;
            
            this.boardMatrix[pos[0]][pos[1]] = new Element(pos[0],pos[1]);
            this.boardMatrix[pos1[0]][pos1[1]] = temp;
        }
    }
    
    public void moveBala() throws Exception{
    	
    	if(this.activeBala != null)
    	{
        	if(this.powerUp2<=0){
        		//si sha acabat el timer del powerup eliminem la bala
        		int[] pos = this.activeBala.getPos().clone();    		
        		this.activeBala = null;
    			this.boardMatrix[pos[0]][pos[1]] = new Element(pos[0],pos[1]);
        	}        	
        	else
        	{  		
        		int[] pos = this.activeBala.getPos().clone();
        		
        		//primer comprovem si la bala no esta a la posicio mes adalt possible (fila 0)
        		//per evitar que surti de la matriu
        		if(pos[0] == 0)
        		{
        			this.activeBala = null;
        			this.boardMatrix[pos[0]][pos[1]] = new Element(pos[0],pos[1]);
        		}
        		else{
    	    		//cas en que a la posicio superior hi ha un brick
    	    		if(this.boardMatrix[pos[0]-1][pos[1]] instanceof Brick){
    	    			
    	    			//eliminem la bala del board
    	    			this.boardMatrix[pos[0]][pos[1]] = new Element(pos[0],pos[1]);
    	    			
    	    			//fem la colisio amb el brick
    	    			pos[0] = pos[0]-1;		
    	    			collisionBrick(pos);
    	    			
    	    			//posem la bala a null
    	    			this.activeBala = null;
    	    			
    	    		}
    	    		else{
    	    			//cas en que a la posicio superior no hi ha res (Element)
    	    			//movem la bala una posicio cap adalt
    	    			this.activeBala.setPos(pos[0]-1, pos[1]);
    	        		this.boardMatrix[pos[0]][pos[1]] = new Element(pos[0],pos[1]);
    	                this.boardMatrix[pos[0]-1][pos[1]] = this.activeBala;
    	    		}
        		}
        	}
    	}

    }
}
