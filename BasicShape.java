package bg.tu_varna.sit;

public abstract class BasicShape {
    private String shapeName;
    private int x;
    private int y;
    private String fill;

    public BasicShape(String shapeName, int x, int y, String fill){
        this.shapeName=shapeName;
        this.x=x;
        this.y=y;
        this.fill=fill;
    }


    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    @Override
    public String toString() {
        return shapeName + " " + x + " " + y + " ";
    }
}
