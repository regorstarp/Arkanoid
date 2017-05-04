
public class mockDB {

	String[][] puntuacions = new String[4][2];
	
	public mockDB(){
		//Nom - Puntuacio
		this.puntuacions[0][0] = "Ivan";
		this.puntuacions[0][1] = "110";
		
		this.puntuacions[1][0] = "Pepe";
		this.puntuacions[1][1] = "50";
		
		this.puntuacions[2][0] = "aaaaaa";
		this.puntuacions[2][1] = "10";

		this.puntuacions[3][0] = "Manolo";
		this.puntuacions[3][1] = "0";
	}
	
    public  boolean connect(){
        return true;
    }
    
    public boolean close(){
        return true;
    }
    
    public String[][] getPuntuacions(String query){
    	
    	return this.puntuacions;
    }
}
