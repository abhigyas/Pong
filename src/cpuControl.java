public class cpuControl {
    public PlayerControl pControl;
    public Rect ball;
    public cpuControl(PlayerControl plrControl, Rect ball){
        this.pControl = plrControl;
        this.ball = ball;
    }
    public void update(double deltaT){
        pControl.update(deltaT);
        if(ball.y < pControl.rect.y){
            pControl.moveUp(deltaT);
        }
        else if(ball.y+ball.h > pControl.rect.y+pControl.rect.h){
            pControl.moveDown(deltaT);
        }
    }
}
