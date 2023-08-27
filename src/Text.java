import java.awt.*;
public class Text {
    public String text;
    public Font font;
    public double x,y,w,h;
    public Color color = Color.WHITE;
    public Text (String text, Font font,double x, double y, Color color){
        this.text = text;
        this.font=font;
        this.x=x;
        this.y=y;
        this.w=font.getSize()*text.length();
        this.h = font.getSize();
        this.color = color;
    }
    public Text (int text, Font font,double x, double y){
        this.text ="" + text;
        this.font=font;
        this.x=x;
        this.y=y;
    }
    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.setFont(font);
        g2.drawString(text,(float)x,(float)y);
    }
}
