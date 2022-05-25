package bg.tu_varna.sit;

import java.util.Objects;

public class Circle  extends BasicShape{
    private int r;

    public Circle(String shapeName, int x, int y, String fill, int r) {
        super(shapeName, x, y, fill);
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return r == circle.r;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r);
    }

    @Override
    public String toString(){
        return super.toString() + r + " " + super.getFill();
    }
}
