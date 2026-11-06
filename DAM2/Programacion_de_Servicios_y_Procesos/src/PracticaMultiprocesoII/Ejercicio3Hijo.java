package PracticaMultiprocesoII;

public class Ejercicio3Hijo {

	public static void main(String[] args) {
		
		if (args.length == 0 || args[0].isEmpty()) {
            System.exit(-1);
        } else {
        	
        	try {
        		
        		String reversa = "";
        		
        		for(int i = args[0].length() - 1; i >= 0; i--)
        		    reversa += args[0].charAt(i);
        		
        		if(args[0].equals(reversa))
        			System.out.println(0);
        		else
        			System.out.println(-1);
				
			} catch (Exception e) {
				System.exit(-1);
			}
        	
        }
	
		
	}
}
