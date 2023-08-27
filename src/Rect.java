import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rect {
    public double x,y,w,h;
    public Color color;
    public Rect(double x,double y, double w, double h, Color color){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.color = color;
    }
    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fill(new Rectangle2D.Double(x, y, w, h));
    }
}
