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
    private Double speedX, speedY, speedPalito1Y, speedPalito2Y;
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
        this.speedX = 1.0;
        this.speedY = 1.0;
        this.speedPalito1Y = 0.0;
        this.speedPalito2Y = 0.0;
        this.panelGame=panelGame;
        initGame();
        initControls();
    }

    private void initGame() {
        animation = new Timeline(new KeyFrame(Duration.millis(17),t->{
            moveTank1();
            moveTank2();
            ballMove();
        }));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        detectarColision();
    }

    private void ballMove(){
        ball.setTranslateY(ball.getTranslateY()-1);
        detectarColisionVertical();
    }

    private void moveTank1() {
        palitroque1.setTranslateY(palitroque1.getTranslateY()-1);
    }

    private void moveTank2() {
        palitroque2.setTranslateY(palitroque2.getTranslateY()+1);

    }


    private void detectarColision(){
        if (ball.getBoundsInParent().intersects(borderLeft.getBoundsInParent())){
            System.out.println("Colision");
        }
    }
    private void detectarColisionVertical(){
        if (ball.getBoundsInParent().intersects(borderUp.getBoundsInParent())
        | ball.getBoundsInParent().intersects(borderDown.getBoundsInParent())){
            speedY = -speedY*1.1;
        }
    }

    private void initControls(){
        panelGame.setOnKeyPressed(e->animation.play());
        panelGame.setFocusTraversable(true);
        controls();

    }

    private void controls(){
        panelGame.setOnKeyPressed(e ->{
        switch (e.getCode()){
            case NUMPAD8: speedPalito2Y -=1;
            break;
            case W: speedPalito1Y -=1;
            break;
            case NUMPAD2: speedPalito2Y +=1;
            case S: speedPalito1Y +=1;
            break;
        }
        });
    }
}
