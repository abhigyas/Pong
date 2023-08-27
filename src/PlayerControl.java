import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class PlayerControl {
    public Rect rect;
    public KL keyListener = null;
    public PlayerControl(Rect rect, KL keyListener){
        this.rect=rect;
        this.keyListener = keyListener;  
    }
    public PlayerControl(Rect rect){
        this.rect = rect;
        this.keyListener = null;
    }
    public void update(double deltaT){
        if(keyListener != null){
            if(keyListener.isKeypressed(KeyEvent.VK_W)){
            moveUp(deltaT);
            }
            if(keyListener.isKeypressed(KeyEvent.VK_UP)){
            moveUp(deltaT);
            }
            else if(keyListener.isKeypressed(KeyEvent.VK_S)){
            moveDown(deltaT);
            }
            else if(keyListener.isKeypressed(KeyEvent.VK_DOWN)){
            moveDown(deltaT);
            }
        }
    }   
    public void moveUp(double deltaT){
          if(rect.y - deltaT > Constants.tHeight){
                this.rect.y -= 1*deltaT;
            }
    }
    public void moveDown(double deltaT){
        if((rect.y+deltaT)+ rect.h < Constants.sHeight - Constants.iBottom){
                this.rect.y += 1*deltaT;
            }
    }
}
