package PracticaMultiprocesoII;

public class Ejercicio1Hijo {
	
	public static void main(String[] args) {
		
		if (args.length == 0 || args[0].isEmpty()) {
            System.exit(-1);
        } else {
        	
        	try {
        		
        		int n = Integer.parseInt(args[0]);
        		
        		//System.exit(n);
        		
        		if(n > 0)
        			System.exit(-3);
        		else
        			System.exit(0);
				
			} catch (Exception e) {
				System.exit(-2);
			}
        	
        }
		
	}	// main
}	// class