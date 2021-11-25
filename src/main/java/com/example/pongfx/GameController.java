package com.example.pongfx;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameController {
    private Rectangle borderLeft, borderRight, borderUp, borderDown, palitroque1, palitroque2;
    private Circle ball;
    private double movBallX, movBallY, movPalito1, movPalito2;
    private Timeline animation;
    private StackPane panelGame;
    private HBox panelScore;
    private int score1, score2;

    public GameController(Rectangle borderLeft, Rectangle borderRight, Rectangle borderUp, Rectangle borderDown,
                          Rectangle palitroque1, Rectangle palitroque2, Circle ball, StackPane panelGame, HBox panelScore) {
        this.borderLeft = borderLeft;
        this.borderRight = borderRight;
        this.borderUp = borderUp;
        this.borderDown = borderDown;
        this.palitroque1 = palitroque1;
        this.palitroque2 = palitroque2;
        this.ball = ball;
        this.movBallX = 3;
        this.movBallY = 3;
        this.panelGame= panelGame;
        this.score1 = 0;
        this.score2 = 0;
        this.panelScore = panelScore;

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
            colisionPalitos();
            colisionPalitosMuros();
        }));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

    }

    // ------ MOVIMIENTOS ------

    private void movePalitoLeft() {
        controls();
        palitroque1.setTranslateY(palitroque1.getTranslateY()+ movPalito1);
    }

    private void movePalitoRight() {
        controls();
        palitroque2.setTranslateY(palitroque2.getTranslateY() + movPalito2);
    }

    private void ballMove(){
        ball.setTranslateY(ball.getTranslateY()+ movBallY);
        ball.setTranslateX(ball.getTranslateX()+ movBallX);
    }

    // ------ COLISIONES ------

    private void colisionPalitos(){
        if (ball.getBoundsInParent().intersects(palitroque1.getBoundsInParent())
        | ball.getBoundsInParent().intersects(palitroque2.getBoundsInParent())){
            if (movBallX <15 & movBallX >-15){
                movBallX =-movBallX *1.1;
            }else{
                movBallX =-movBallX;
            }
        }
    }

    private void colisionPalitosMuros(){

        if (palitroque1.getBoundsInParent().intersects(borderUp.getBoundsInParent())){
            palitroque1.setTranslateY(palitroque1.getTranslateY()+4.5);
        }
        if (palitroque1.getBoundsInParent().intersects(borderDown.getBoundsInParent())){
            palitroque1.setTranslateY(palitroque1.getTranslateY()-4.5);
        }
        if (palitroque2.getBoundsInParent().intersects(borderUp.getBoundsInParent())){
            palitroque2.setTranslateY(palitroque2.getTranslateY()+4.5);
        }
        if (palitroque2.getBoundsInParent().intersects(borderDown.getBoundsInParent())){
            palitroque2.setTranslateY(palitroque2.getTranslateY()-4.5);
        }

    }

    private void colisionSides(){

        if (ball.getBoundsInParent().intersects(borderRight.getBoundsInParent())){
            ball.setTranslateX(0);
            ball.setTranslateY(0);
            palitroque1.setTranslateY(0);
            palitroque2.setTranslateY(0);
            movBallX =3;
            movBallY = 3;
            score1 = score1+1;
        }
        if (ball.getBoundsInParent().intersects(borderLeft.getBoundsInParent())){
            ball.setTranslateX(0);
            ball.setTranslateY(0);
            palitroque1.setTranslateY(0);
            palitroque2.setTranslateY(0);
            movBallX =3;
            movBallY = 3;
            score2 = score2+1;
        }

    }

    private void colisionBallVertical(){
        if (ball.getBoundsInParent().intersects(borderUp.getBoundsInParent())
        | ball.getBoundsInParent().intersects(borderDown.getBoundsInParent())){
            if (movBallY <15 & movBallY >-15){
                movBallY =-movBallY *1.1;
            }else{
                movBallY =-movBallY;
            }
        }
    }

    // ------ CONTROLES ------

    private void initControls(){
        panelGame.setOnKeyPressed(e->animation.play());
        panelGame.setFocusTraversable(true);

    }

    private void controls(){
        panelGame.setOnKeyPressed(e ->{
        switch (e.getCode()){
            case W: movPalito1 =-4.5;
                break;
            case S: movPalito1 =4.5;
                break;
            case UP: movPalito2 =-4.5;
                break;
            case DOWN: movPalito2 =4.5;
                break;
        }
        });

        panelGame.setOnKeyReleased(e ->{
            switch (e.getCode()){
                case W: movPalito1 =0.0;
                case S: movPalito1 = 0.0;
                    break;
                case UP: movPalito2 = 0.0;
                case DOWN: movPalito2 = 0.0;
                    break;
            }
        });
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }
}
