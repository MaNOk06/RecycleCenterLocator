public class Location {
    // name of the place
    private String placeName;
    // x coordinate
    private int x;
    // y coordinate
    private int y;

    // constructor to set the values
    public Location(String placeName, int x, int y) {
        this.placeName = placeName;
        this.x = x;
        this.y = y;
    }

    // returns the place name
    public String getPlaceName() {
        return placeName;
    }

    // returns x value
    public int getX() {
        return x;
    }

    // returns y value
    public int getY() {
        return y;
    }

    // calculates the distance between this location and another one
    public double computeDistance(Location other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}