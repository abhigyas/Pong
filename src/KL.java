import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KL implements KeyListener {

    private boolean keyPressed[] = new boolean[128];
    @Override
    public void keyTyped(KeyEvent keyEvent){

    }
    @Override
    public void keyPressed(KeyEvent keyEvent){
        keyPressed[keyEvent.getKeyCode()] = true;
    }
    @Override
    public void keyReleased(KeyEvent keyEvent){
        keyPressed[keyEvent.getKeyCode()] = false;
    }
    public boolean isKeypressed(int keyCode){
        return keyPressed[keyCode];
    }
}