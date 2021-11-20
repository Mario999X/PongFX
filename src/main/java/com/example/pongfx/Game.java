package com.example.pongfx;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Game extends BorderPane {

    private Rectangle borderLeft, borderRight, borderUp, borderDown,
            palitroque1, palitroque2;
    private Circle ball;
    private GameController controller;
    private StackPane panelGame;


    public Game(){
        //Instance
        this.borderLeft=new Rectangle();
        this.borderRight=new Rectangle();
        this.borderUp=new Rectangle();
        this.borderDown=new Rectangle();
        this.palitroque1 =new Rectangle();
        this.palitroque2 = new Rectangle();
        this.ball = new Circle();
        this.panelGame =new StackPane();
        this.panelGame.setMinSize(0,0);
        this.panelGame.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        controller=new GameController(borderLeft,borderRight,borderUp,borderDown, palitroque1,
                palitroque2, ball, panelGame);

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
        palitroque1.setFill(Color.WHITE);
        palitroque1.translateXProperty().bind(borderLeft.widthProperty());
        palitroque1.heightProperty().bind(borderLeft.widthProperty().multiply(3.25));
        palitroque1.widthProperty().bind(borderLeft.widthProperty().divide(1.75));

        palitroque2.setFill(Color.WHITE);
        palitroque2.translateXProperty().bind(borderRight.widthProperty().multiply(-1));
        palitroque2.heightProperty().bind(borderLeft.widthProperty().multiply(3.25));
        palitroque2.widthProperty().bind(borderLeft.widthProperty().divide(1.75));

        ball.setFill(Color.WHITE);
        ball.radiusProperty().bind(borderLeft.widthProperty().divide(3));

        //Sets and Adds
        panelGame.getChildren().addAll(borderLeft,borderRight, borderDown, borderUp, palitroque1, palitroque2, ball);
        panelGame.setAlignment(borderLeft, Pos.CENTER_LEFT);
        panelGame.setAlignment(borderRight, Pos.CENTER_RIGHT);
        panelGame.setAlignment(borderDown, Pos.BOTTOM_CENTER);
        panelGame.setAlignment(borderUp, Pos.TOP_CENTER);
        panelGame.setAlignment(palitroque1, Pos.CENTER_LEFT);
        panelGame.setAlignment(palitroque2, Pos.CENTER_RIGHT);
        panelGame.setAlignment(ball, Pos.CENTER);

        this.setCenter(panelGame);


    }

}

