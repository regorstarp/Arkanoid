import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by rogerprats on 24/10/2016.
 */
public class Interface extends JFrame implements KeyListener{
    
	Draw db;
    Board board;
    

    
    public Interface(Board board, String[][] puntuacions){
         this.board = board;
         db = new Draw(this.board.boardMatrix, board, puntuacions);
         this.add(db);
         
    }
    

    public void play(){
    	
        this.addKeyListener(this);
        this.setSize(400, 400);
        
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(Color.black);
        
       // if (this.board.gameOver){
        	//
       // }
       // else{
       // 	this.add(db);
       // }
        
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_LEFT:
                try {
                        this.board.moveSlider(Joc.LEFT);
                	
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            case KeyEvent.VK_RIGHT :
            	try{
                    this.board.moveSlider(Joc.RIGHT);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            case KeyEvent.VK_SPACE:
            	try{
	            	//nomes es pot disparar si hi ha el powerup 2 actiu i no hi ha una bala disparada
	            	//if((this.board.powerUp2) && (this.board.activeBala == null)){
            		if((this.board.powerUp2>0) && (this.board.activeBala == null)){
            			int[] auxpos = board.slider.getPos();
	                	board.activeBala = new Bala(auxpos[0]-1,auxpos[1]);
	            	}
            	}catch(Exception e1) {
                    e1.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
    class Draw extends JPanel{
        public Element [][] board;
        public Board b;
        public String[][] punts;

        private static final int Ball_radius = 7;
        private static final int Brick_length = 9;
        private static final int Brick_width = 7;
        private static final int Slider_length = 12;
        private static final int Slider_width = 7;
        private static final int Bala_length = 4;
        private static final int Bala_width = 7;
        
        public Draw(Element[][] board, Board b,String[][] puntuacions){
            this.board = board;
            this.b = b;
            this.punts = puntuacions;
        }
        
        public void paintComponent(Graphics g){
        	

        	g.setColor(Color.BLACK);
        	g.fillRect(0, 0, (int)this.getSize().getWidth(), (int)this.getSize().getHeight());
        	int x,y;
        	
            super.paintComponents(g);
            if(this.punts==null){
            	          
	            if (!b.gameOver){
	            	for (y = 0; y < this.board.length; y++){
	                    for (x = 0; x < this.board[0].length; x++){
	                        if (board[x][y] instanceof Slider){
	                            drawSlider(g ,y, x);
	                        }
	                        if (board[x][y] instanceof Brick){
	                            drawBrick(g, y, x,board[x][y].powerUp);
	                        }
	                        if (board[x][y] instanceof Ball){
	                            drawBall(g, y, x);
	                        }
	                        if (board[x][y] instanceof Bala){
	                            drawBala(g, y, x);
	                        }
	                    }
	                }
	            }
	            else{
	            	g.setColor(Color.WHITE);
	            	g.drawString("GAME OVER", 150, 190);
	            }
            }
            else{
            	
            	x=150;
            	y=120;
            	
            	g.setColor(Color.WHITE);
            	g.drawString("PUNTUACIONS", x, y);
            	

            	for(int i=0;i<this.punts.length;++i){
            		y = y+30;
            		g.drawString(this.punts[i][0], x, y);
            		g.drawString(this.punts[i][1], x+70, y);
            	}
            }
            
        }
        public void drawBall(Graphics g, int x, int y){

            	x *=10;
                y *=10;
                g.setColor(Color.WHITE);
                g.fillOval(x, y, Ball_radius, Ball_radius);
        
        }
        public void drawBrick(Graphics g, int x, int y, int powerup){
            x *=10;
            y *=10;
            if(powerup == 1){
            	g.setColor(Color.RED);
            }
            else if(powerup == 2){
            	g.setColor(Color.GREEN);
            }
            else{
            	g.setColor(Color.WHITE);
            }
            
            g.fillRect(x, y, Brick_length, Brick_width);
        }
        public void drawSlider(Graphics g, int x, int y){
            x *=10;
            y *=10;
            if(this.b.powerUp2>0){
            	g.setColor(Color.GREEN);
            }
            else{
            	g.setColor(Color.WHITE);
            }            
            g.fillRect(x, y, Slider_length, Slider_width);
        }
        
        public void drawBala(Graphics g, int x, int y){
        	x *=10;
            y *=10;
            g.setColor(Color.GREEN);
            g.fillRect(x, y, Bala_length, Bala_width);
        }

    }





