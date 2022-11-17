package Model;

/**
 * Point holds 2-D coordinates
 *
 * @author yagaa
 * @version 1.0
 */
public class Point {

    private int x;
    private int y;

    public Point(int xCoordinate, int yCoordinate) {
        x = xCoordinate;
        y = yCoordinate;
    }

    /**
     * @return x-coordinate of the point
     */
    public int xCoord() {
        return x;
    }

    /**
     * @return y-coordinate of the point
     */
    public int yCoord() {
        return y;
    }

    /**
     * Setter for x
     * @param xCoordinate x-coordinate of the point
     */
    public void setXCoord(int xCoordinate) {
        x = xCoordinate;
    }

    /**
     * Setter for y
     * @param yCoordinate y-coordinate of the point
     */
    public void setYCoord(int yCoordinate) {
        y = yCoordinate;
    }
}

