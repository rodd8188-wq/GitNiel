
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ManhattanDistanceTest {

    @Test
    void samePointHasZeroDistance() {
        Point p = new Point(3, 4);
        assertEquals(0, ManhattanDistance.manhattanDistance(p, p));
    }

    @Test
    void positiveCoordinates() {
        assertEquals(7, ManhattanDistance.manhattanDistance(new Point(0, 0), new Point(3, 4)));
    }

    @Test
    void negativeCoordinates() {
        assertEquals(10, ManhattanDistance.manhattanDistance(new Point(-2, -3), new Point(2, 3)));
    }

    @Test
    void isSymmetric() {
        Point a = new Point(1, 5);
        Point b = new Point(-4, 2);
        assertEquals(
            ManhattanDistance.manhattanDistance(a, b),
            ManhattanDistance.manhattanDistance(b, a));
    }

    @Test
    void onlyHorizontalMovement() {
        assertEquals(5, ManhattanDistance.manhattanDistance(new Point(1, 2), new Point(6, 2)));
    }

    @Test
    void onlyVerticalMovement() {
        assertEquals(4, ManhattanDistance.manhattanDistance(new Point(1, 2), new Point(1, 6)));
    }
}