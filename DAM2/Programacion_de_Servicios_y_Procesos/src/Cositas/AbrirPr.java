package Cositas;

public class AbrirPr {
	public static void main(String[] args) {
		try{
			ProcessBuilder process1 = new ProcessBuilder("firefox");
			ProcessBuilder process2 = new ProcessBuilder("gedit");
			ProcessBuilder process3 = new ProcessBuilder("nemo");
			for(int i=0; i<10000000; i++) {
				Process proces1 = process1.start();
				Process proces2 = process2.start();
				Process proces3 = process3.start();
			}
		} catch(Exception e) {
			System.out.println("No va");
			e.printStackTrace();

		}
	}
}
