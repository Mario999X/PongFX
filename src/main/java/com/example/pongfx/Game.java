package com.example.pongfx;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Game extends BorderPane {

    private Rectangle borderLeft, borderRight, borderUp, borderDown,
            bar1, bar2;
    private Circle ball;
    private GameController controller;
    private StackPane panelGame;
    private HBox panelScore;
    private Label labelOrgScore1, labelOrgScore2;


    public Game(){
        //Instance
        this.borderLeft=new Rectangle();
        this.borderRight=new Rectangle();
        this.borderUp=new Rectangle();
        this.borderDown=new Rectangle();
        this.bar1 =new Rectangle();
        this.bar2 = new Rectangle();
        this.ball = new Circle();
        this.panelScore = new HBox();
        this.labelOrgScore1 = new Label("Player1: " + 0);
        this.labelOrgScore2 = new Label("Player2: " + 0);
        panelScore.setSpacing(610);
        this.panelGame =new StackPane();
        this.panelGame.setMinSize(0,0);
        this.panelGame.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        controller=new GameController(borderLeft,borderRight,borderUp,borderDown, bar1,
                bar2, ball, panelGame, panelScore, labelOrgScore1, labelOrgScore2);

        //Inicialice

            // --- WALLS ---
        borderLeft.setFill(Color.TRANSPARENT);
        borderLeft.heightProperty().bind(panelGame.heightProperty());
        borderLeft.widthProperty().bind(panelGame.widthProperty().divide(20));

        borderRight.setFill(Color.TRANSPARENT);
        borderRight.heightProperty().bind(panelGame.heightProperty());
        borderRight.widthProperty().bind(panelGame.widthProperty().divide(20));

        borderDown.setFill(Color.RED);
        borderDown.heightProperty().bind(panelGame.heightProperty().divide(20));
        borderDown.widthProperty().bind(panelGame.widthProperty());

        borderUp.setFill(Color.RED);
        borderUp.heightProperty().bind(panelGame.heightProperty().divide(20));
        borderUp.widthProperty().bind(panelGame.widthProperty());

            // --- OBJECTS ---
        bar1.setFill(Color.WHITE);
        bar1.translateXProperty().bind(borderLeft.widthProperty().multiply(2.10));
        bar1.heightProperty().bind(borderLeft.widthProperty().multiply(3.15));
        bar1.widthProperty().bind(borderLeft.widthProperty().divide(1.85));

        bar2.setFill(Color.WHITE);
        bar2.translateXProperty().bind(borderRight.widthProperty().multiply(-2.10));
        bar2.heightProperty().bind(borderLeft.widthProperty().multiply(3.15));
        bar2.widthProperty().bind(borderLeft.widthProperty().divide(1.85));

        ball.setFill(Color.WHITE);
        ball.radiusProperty().bind(borderLeft.widthProperty().divide(3));

        //Sets and Adds
        panelGame.getChildren().addAll(borderLeft,borderRight, borderDown, borderUp, bar1, bar2, ball);
        panelGame.setAlignment(borderLeft, Pos.CENTER_LEFT);
        panelGame.setAlignment(borderRight, Pos.CENTER_RIGHT);
        panelGame.setAlignment(borderDown, Pos.BOTTOM_CENTER);
        panelGame.setAlignment(borderUp, Pos.TOP_CENTER);
        panelGame.setAlignment(bar1, Pos.CENTER_LEFT);
        panelGame.setAlignment(bar2, Pos.CENTER_RIGHT);
        panelGame.setAlignment(ball, Pos.CENTER);

        panelScore.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        panelScore.getChildren().addAll(labelOrgScore1, labelOrgScore2);


        this.setCenter(panelGame);
        this.setBottom(panelScore);

    }

}

