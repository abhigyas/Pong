import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
public class MainMenu extends JFrame implements Runnable{

    public Graphics2D g2;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public Text startGame,exitGame,pong;
    public boolean isRunning = true;
    public MainMenu(){
        this.setSize(Constants.sWidth, Constants.sHeight);
        this.setTitle(Constants.sTitle);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.startGame = new Text("Start Game",new Font("Times New Roman", Font.PLAIN, 40),Constants.sWidth/2.0 - 140.0, Constants.sHeight/2.0,Color.WHITE);
        this.exitGame = new Text("Exit",new Font("Times New Roman", Font.PLAIN, 40),Constants.sWidth/2.0 - 80.0, Constants.sHeight/2.0 + 60.0,Color.WHITE);
        this.pong = new Text("Pong",new Font("Times New Roman",Font.BOLD,100),Constants.sWidth/2.0-155.0,200,Color.WHITE);
        g2=(Graphics2D)getGraphics();
    }
    public void update (double deltaT){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbGraphics = dbImage.getGraphics();
        this.draw(dbGraphics);
        g2.drawImage(dbImage,0,0,this);
        if(mouseListener.getMouseX()>startGame.x && mouseListener.getMouseX()<startGame.x+startGame.w && mouseListener.getMouseY()>startGame.y - startGame.h /2.0 && mouseListener.getMouseY()<startGame.y+startGame.h / 2.0){
            startGame.color = new Color(158,158,158);
            if(mouseListener.isMousePressed()){
                App.changeState(1);
            }
        } else{
            startGame.color = Color.WHITE;
        }
        if(mouseListener.getMouseX()>exitGame.x && mouseListener.getMouseX()<exitGame.x+exitGame.w && mouseListener.getMouseY()>exitGame.y - exitGame.h /2.0 && mouseListener.getMouseY()<exitGame.y+exitGame.h / 2.0){
            exitGame.color = new Color(158,158,158);
            if(mouseListener.isMousePressed()){
                App.changeState(2);
            }
        } else{
            exitGame.color = Color.WHITE;
        }

    }
    public void draw (Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.sWidth, Constants.sHeight);
        startGame.draw(g2);
        exitGame.draw(g2);
        pong.draw(g2);
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
