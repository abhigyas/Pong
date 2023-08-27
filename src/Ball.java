public class Ball {
    public Rect rect;
    public Rect lPaddle, rPaddle;
    public Text leftScoreText,rightScoreText;
    private double vy = 0.1;
    private double vx = -0.25;
    //velocity of x and y
    public Ball(Rect rect, Rect lPaddle, Rect rPaddle, Text leftScoreText, Text rightScoreText){
        this.rect = rect;
        this.lPaddle = lPaddle;
        this.rPaddle = rPaddle;
        this.leftScoreText=leftScoreText;
        this.rightScoreText=rightScoreText;
    }

    public double calculateVelocityAngle(Rect paddle){
        double relativeIntersectY = (paddle.y + (paddle.h / 2.0)) - (this.rect.y + (this.rect.h/2.0));
        double normalIntersectY = relativeIntersectY / (paddle.h/2.0);
        double theta = normalIntersectY*Constants.maxAngle;
        return Math.toRadians(theta);
    }
    
    public void update(double deltaT){
        if (vx < 0.0){
            if(this.rect.x <= this.lPaddle.x + this.lPaddle.w && this.rect.x +this.rect.w>= this.lPaddle.x && this.rect.y >= this.lPaddle.y && this.rect.y <= this.lPaddle.y +this.lPaddle.h){
                double theta = calculateVelocityAngle(lPaddle);
                double newVx = Math.abs(Math.cos(theta));
                double newVy = (-Math.sin(theta));
                double oldSignX = Math.signum(vx);
                double oldSignY = Math.signum(vy);
                this.vx = newVx * (-1.0*oldSignX);
                this.vy = newVy * (-1.0*oldSignY);
            }
        }
        else if(this.rect.x+this.rect.w <this.lPaddle.x){
            System.out.println("Player lost point");
        }
        else if(vx>=0.0){
                if(this.rect.x + this.rect.w >= this.rPaddle.x && this.rect.x <= this.rPaddle.x +this.rPaddle.w && this.rect.y >= this.rPaddle.y && this.rect.y <= this.rPaddle.y +this.rPaddle.h){
                double theta = calculateVelocityAngle(rPaddle);
                double newVx = Math.abs(Math.cos(theta));
                double newVy = (Math.sin(theta));
                double oldSignX = Math.signum(vx);
                double oldSignY = Math.signum(vy);
                this.vx = newVx * (-1.0*oldSignX);
                this.vy = newVy * (-1.0*oldSignY);
            }
        }
        else if(this.rect.x + this.rect.w > this.rPaddle.x+this.rPaddle.w){
            System.out.println("CPU lost point");
        }
        if(vy>0.0){
            if(this.rect.y +this.rect.h > Constants.sHeight){
                this.vy *= -1;
            }
        }
        else if (vy<0.0){
            if(this.rect.y<Constants.tHeight){
                this.vy *= -1;
            }
        }
        this.rect.x += vx* deltaT;
        this.rect.y += vy*deltaT;
        if(this.rect.x+this.rect.w<lPaddle.x){
            int rightScore = Integer.parseInt(rightScoreText.text);
            rightScore++;
            rightScoreText.text = "" + rightScore; 
            this.rect.x = Constants.sWidth /2.0;
            this.rect.y = Constants.sHeight /2.0;
            this.vy = 0.1;
            this.vx = -0.25;
            if(rightScore>=Constants.winScore){
                App.changeState(2);
            }
        }
        else if(this.rect.x>rPaddle.x+rPaddle.w){
            int leftScore = Integer.parseInt(leftScoreText.text);
            leftScore++;
            leftScoreText.text = "" + leftScore; 
            this.rect.x = Constants.sWidth /2.0;
            this.rect.y = Constants.sHeight /2.0;
            this.vy = 0.1;
            this.vx = -0.25;
             if(leftScore>Constants.winScore){
                App.changeState(2);
            }
        }

    }

}
