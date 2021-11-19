package com.example.pongfx;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameController {
    private Rectangle borderLeft, borderRight, borderUp, borderDown, palitroque1, palitroque2;
    private Circle ball;
    private Double speedBallX, speedBallY, speedPalito1, speedPalito2;
    private Timeline animation;
    private StackPane panelGame;

    public GameController(Rectangle borderLeft, Rectangle borderRight, Rectangle borderUp, Rectangle borderDown, Rectangle palitroque1, Rectangle palitroque2, Circle ball, StackPane panelGame) {
        this.borderLeft = borderLeft;
        this.borderRight = borderRight;
        this.borderUp = borderUp;
        this.borderDown = borderDown;
        this.palitroque1 = palitroque1;
        this.palitroque2 = palitroque2;
        this.ball = ball;
        this.speedBallX = 1.0;
        this.speedBallY = 1.0;
        this.panelGame=panelGame;
        initGame();
        initControls();
    }

    private void initGame() {
        animation = new Timeline(new KeyFrame(Duration.millis(17),t->{
            movePalitoLeft();
            movePalitoRight();
            ballMove();
            colisionBallVertical();
            colisionSides();
        }));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        colisionPalitos();
        colisionPalitosMuros();
    }

    private void ballMove(){
        ball.setTranslateY(ball.getTranslateY()+ speedBallY);
        ball.setTranslateX(ball.getTranslateX()+ speedBallX);
    }

    private void movePalitoLeft() {
        controls();
        palitroque1.setTranslateY(palitroque1.getTranslateY()+ speedPalito1);
    }

    private void movePalitoRight() {
        controls();
        palitroque2.setTranslateY(palitroque2.getTranslateY()+ speedPalito2);

    }

    private void colisionPalitos(){
        if (ball.getBoundsInParent().intersects(palitroque1.getBoundsInParent())
        | ball.getBoundsInParent().intersects(palitroque2.getBoundsInParent())){
            if (speedBallX<20 & speedBallX >-20){
                speedBallX =- speedBallX *1.1;
            }else{
                speedBallX =- speedBallX;
            }
        }
    }

    private void colisionPalitosMuros(){
        if (palitroque1.getBoundsInParent().intersects(borderUp.getBoundsInParent())
        | palitroque1.getBoundsInParent().intersects(borderDown.getBoundsInParent())){
            speedPalito1 = 0.0;
        }
        if (palitroque2.getBoundsInParent().intersects(borderUp.getBoundsInParent())
                | palitroque2.getBoundsInParent().intersects(borderDown.getBoundsInParent())){
            speedPalito2 = 0.0;
        }

    }

    private void colisionSides(){
        if (ball.getBoundsInParent().intersects(borderRight.getBoundsInParent())
        | ball.getBoundsInParent().intersects(borderLeft.getBoundsInParent())){
            ball.setTranslateX(0);
            ball.setTranslateY(0);
            palitroque1.setTranslateY(0);
            palitroque2.setTranslateY(0);
            speedBallX=1.5;
        }
    }
    private void colisionBallVertical(){
        if (ball.getBoundsInParent().intersects(borderUp.getBoundsInParent())
        | ball.getBoundsInParent().intersects(borderDown.getBoundsInParent())){
            speedBallY = -speedBallY*1.1;
        }
    }

    private void initControls(){
        panelGame.setOnKeyPressed(e->animation.play());
        panelGame.setFocusTraversable(true);

    }

    private void controls(){
        panelGame.setOnKeyPressed(e ->{
        switch (e.getCode()){
            case UP: speedPalito2 -=1;
            break;
            case W: speedPalito1 -=1;
            break;
            case DOWN: speedPalito2 +=1;
            case S: speedPalito1 +=1;
            break;
        }
        });
        panelGame.setOnKeyReleased(e ->{
            switch (e.getCode()){
                case W: speedPalito1 =0.0;
                break;
                case S: speedPalito1 = 0.0;
                break;
                case UP: speedPalito2 = 0.0;
                break;
                case DOWN: speedPalito2 = 0.0;
                break;
            }
        });
    }
}
