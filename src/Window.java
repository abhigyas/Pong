import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
public class Window extends JFrame implements Runnable{

    public Graphics2D g2;
    public KL keyListener = new KL();
    public Rect p1,cpu,ballRect;
    public Ball ball;
    public PlayerControl pControl; 
    public cpuControl cpuController;    
    public Text leftScoreText, rightScoreText;
    public boolean isRunning = true;
    public Window(){
        this.setSize(Constants.sWidth, Constants.sHeight);
        this.setTitle(Constants.sTitle);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        Constants.tHeight = this.getInsets().top;
        Constants.iBottom = this.getInsets().bottom;
        leftScoreText = new Text (0,new Font ("Times New Roman",Font.PLAIN, 20),10,50);
        rightScoreText = new Text (0,new Font ("Times New Roman",Font.PLAIN, 20),Constants.sWidth-10-16,50);
        g2 = (Graphics2D)this.getGraphics();
        p1 = new Rect(Constants.hPadding, 40, Constants.pWidth,Constants.pHeight,Constants.pColor);
        pControl = new PlayerControl(p1, keyListener);
        cpu = new Rect(Constants.sWidth - Constants.pWidth - Constants.hPadding,40,Constants.pWidth,Constants.pHeight,Constants.pColor);
        ballRect = new Rect(Constants.sWidth/2,Constants.sHeight/2,Constants.bDimension,Constants.bDimension,Constants.bColor);
        ball = new Ball(ballRect,p1,cpu,leftScoreText,rightScoreText);
        cpuController = new cpuControl(new PlayerControl(cpu),ballRect);
        

    }
    public void update (double deltaT){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbGraphics = dbImage.getGraphics();
        this.draw(dbGraphics);
        pControl.update(deltaT);
        cpuController.update(deltaT);
        ball.update(deltaT);
        g2.drawImage(dbImage,0,0,this);
    }
    public void draw (Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.sWidth, Constants.sHeight);
        leftScoreText.draw(g2);
        rightScoreText.draw(g2);
        p1.draw(g2);
        cpu.draw(g2);
        ballRect.draw(g2);
    }
    public void stop(){
        isRunning = false;
    }


    public void run(){
        while (isRunning){
            double lastFrameTime = 0.0;
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;
            update(deltaTime);
        }
        this.dispose();
        return;
    }
    
}
