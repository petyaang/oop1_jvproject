package bg.tu_varna.sit;

import java.util.Objects;

public class Line  extends BasicShape{
    private int x1;
    private int y1;
    private int strokeWidth;

    public Line(String shapeName, int x, int y, String fill, int x1, int y1, int strokeWidth) {
        super(shapeName, x, y, fill);
        this.x1 = x1;
        this.y1 = y1;
        this.strokeWidth = strokeWidth;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return x1 == line.x1 && y1 == line.y1 && strokeWidth == line.strokeWidth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, strokeWidth);
    }

    @Override
    public String toString(){
        return super.toString() + x1 + " " + y1 + " " + super.getFill() + " " + strokeWidth;
    }
}
