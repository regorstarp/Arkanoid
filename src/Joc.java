import java.util.concurrent.TimeUnit;

public class Joc {
    public static final int UP = -1;
    public static final int RIGHT = 1;
    public static final int DOWN = 1;
    public static final int LEFT = -1;
    
    public static void main(String[] args) throws Exception{
       
    	Board t = new Board(15,15);
    	mockDB DB = new mockDB();
    	String[][] punts = null;
    	String query = "SELECT * FROM Puntuacions";
    	Interface i = null;

        while (true){
        	i = new Interface(t,null);
        	i.play();

            t.moveBall();
            TimeUnit.MILLISECONDS.sleep(500);
            if (t.gameOver){
            	break;
            }
        }
        
        if (t.gameOver){
        	TimeUnit.MILLISECONDS.sleep(500);
        	
        	if(!DB.connect()){
        		throw new Exception("Connexio a la base de dades incorrecte");
        	}
        	
        	punts = DB.getPuntuacions(query);

        	if(!DB.close()){
        		throw new Exception("Connexio a la base de dades tancada amb errors");
        	}
        	
        	i = new Interface(t,punts);
        	i.play();
        	
        }
    }



}