package comp1110.ass2.gui;

import comp1110.ass2.BasicClassess.Assam;
import comp1110.ass2.BasicClassess.Board;
import comp1110.ass2.BasicClassess.GameStatus;
import comp1110.ass2.BasicClassess.Player;
import comp1110.ass2.EnumClassess.Colour;
import comp1110.ass2.EnumClassess.Direction;
import comp1110.ass2.FunctionalClassess.IntPair;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    @Override
    public void start(Stage stage) throws Exception {

        StackPane startPane = new StackPane();
        Scene startScene = new Scene(startPane, WINDOW_WIDTH, WINDOW_HEIGHT);

        Button startButton = new Button("Game Start");
        startButton.setPrefWidth(300);
        startButton.setPrefHeight(100);
        startButton.setFont(new Font(24));
        startButton.setOnAction(e -> {
            Scene gameScene = playerChoice();
            stage.setScene(gameScene);
        });
        startPane.getChildren().add(startButton);

        stage.setScene(startScene);
        stage.show();
    }

    private Scene playerChoice() {

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

        Button twoPlayers = new Button("Two Players");
        setupPlayerButton(twoPlayers, 2);

        Button threePlayers = new Button("Three Players");
        setupPlayerButton(threePlayers, 3);

        Button fourPlayers = new Button("Four Players");
        setupPlayerButton(fourPlayers, 4);

        vbox.getChildren().addAll(twoPlayers, threePlayers, fourPlayers);

        return new Scene(vbox, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private GameStatus setupGameStatus(int playerCount) {
        List<Player> allPlayers = new ArrayList<>();

        if (playerCount == 2) {
            allPlayers.add(new Player(30,15,'i',Colour.CYAN));
            allPlayers.add(new Player(30,15,'i',Colour.YELLOW));
            allPlayers.add(new Player(0,0,'o',Colour.PURPLE));
            allPlayers.add(new Player(0,0,'o',Colour.RED));
        } else if (playerCount == 3) {
            allPlayers.add(new Player(30,15,'i',Colour.CYAN));
            allPlayers.add(new Player(30,15,'i',Colour.YELLOW));
            allPlayers.add(new Player(30,15,'i',Colour.PURPLE));
            allPlayers.add(new Player(0,0,'o',Colour.RED));
        } else {
            allPlayers.add(new Player(30,15,'i',Colour.CYAN));
            allPlayers.add(new Player(30,15,'i',Colour.YELLOW));
            allPlayers.add(new Player(30,15,'i',Colour.PURPLE));
            allPlayers.add(new Player(30,15,'i',Colour.RED));
        }

        IntPair position = new IntPair(3,3);
        Assam assam = new Assam(position, Direction.SOUTH);
        Board board = new Board("n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00");

        return new GameStatus(allPlayers, assam, board);
    }

    private void setupPlayerButton(Button button, int playerCount) {
        button.setPrefWidth(300);
        button.setPrefHeight(100);
        button.setFont(new Font(24));
        button.setOnAction(e -> {
            Scene gameScene = createGameScene();
            Stage currentStage = (Stage) button.getScene().getWindow();
            currentStage.setScene(gameScene);
        });
    }




    private Scene createGameScene() {
        int centerX1 = 400;
        int centerY1 = 70;
        int centerX2 = 320;
        int centerY2 = 630;
        int centerX3 = 880;
        int radius = 60;

        for (int i = 0; i < 4; i++) {
            Circle circle = new Circle(centerX1 + i * 160, centerY1, radius);
            circle.setFill(Color.BLUE);
            root.getChildren().add(circle);
        }

        for (int i = 0; i < 4; i++) {
            Circle circle = new Circle(centerX2 + i * 160, centerY2, radius);
            circle.setFill(Color.BLUE);
            root.getChildren().add(circle);
        }

        for (int i = 1; i < 4; i++) {
            Circle circle = new Circle(centerX2, centerY2 - i * 160, radius);
            circle.setFill(Color.BLUE);
            root.getChildren().add(circle);
        }

        for (int i = 1; i < 4; i++) {
            Circle circle = new Circle(centerX3, centerY1 + i * 160, radius);
            circle.setFill(Color.BLUE);
            root.getChildren().add(circle);
        }

        Rectangle greyBoard = new Rectangle();
        greyBoard.setWidth(560);
        greyBoard.setHeight(560);
        greyBoard.setFill(Color.GRAY);
        greyBoard.setX((double) (WINDOW_WIDTH - 560) / 2);
        greyBoard.setY((double) (WINDOW_HEIGHT - 560) / 2);

        root.getChildren().add(greyBoard);


        return new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
