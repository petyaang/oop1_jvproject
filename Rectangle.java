package bg.tu_varna.sit;

import java.util.Objects;

public class Rectangle extends BasicShape{
    private int width;
    private int height;

    public Rectangle(String shapeName, int x, int y, String fill, int width, int height){
        super(shapeName, x, y, fill);
        this.width=width;
        this.height=height;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return width == rectangle.width && height == rectangle.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString(){
        return super.toString() + width + " " + height + " " + super.getFill();
    }
}
