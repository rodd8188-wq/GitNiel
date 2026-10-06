

public final class Point {

    private final int 
    	x, y;

    public Point(int x, int y) {
    	
        this.x = x;
        this.y = y;
        
    }
    
    int distanceTo(Point other) {
    	
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
        
    }
}