package comp1110.ass2.gui;

import comp1110.ass2.BasicClassess.Assam;
import comp1110.ass2.BasicClassess.Board;
import comp1110.ass2.BasicClassess.GameStatus;
import comp1110.ass2.BasicClassess.Player;
import comp1110.ass2.EnumClassess.Colour;
import comp1110.ass2.EnumClassess.Direction;
import comp1110.ass2.FunctionalClassess.IntPair;
import comp1110.ass2.FunctionalClassess.Rotation;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;
    int playerCount;
    private double offsetX = 0;
    private double offsetY = 0;

    int row = 0;
    int col = 0;

    IntPair position1;
    IntPair position2;


    void displayGame(String state) {
        //These are for the squares
        GameStatus gameString = new GameStatus(state);
        List<Colour> rugColorList = Board.getColourList(state);
        double side = 80;

        List<Square> squares = new ArrayList<>();
        double x = 110;
        double y = 360;
        for (int col = 0; col < 7; col++){
            for (int row = 0; row < 7; row++){
                Square square = new Square(x,y,side);
                Colour rugColour = rugColorList.get(col * 7 + row);
                if (rugColour != Colour.GREY) {
                    Color javaFXColor = Colour.colourToJavaFXColor(rugColour);
                    square.setFill(javaFXColor);
                    square.setStroke(Color.BLACK);
                    square.setStrokeWidth(2);
                    squares.add(square);
                }
                x += side;
            }
            y += side;
            x = 110;
        }
        root.getChildren().addAll(squares);



        //These are for assam.png
        Assam assam = gameString.getAssam();
        IntPair assamPosition = assam.getPosition();
        Direction assamDirection = assam.getDirection();
        int col = assamPosition.getCol();
        int row = assamPosition.getRow();

        URL assamImageUrl = Viewer.class.getResource("assam.png");
        Image assamImage = new Image(assamImageUrl.toString());
        ImageView imageView = new ImageView(assamImage);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);

        double assamX = 335 + col * 80;
        double assamY = 70 + row * 80;
        imageView.setX(assamX);
        imageView.setY(assamY);

        double rotationAngle = Rotation.directionToInt(assamDirection);
        imageView.setRotate(rotationAngle);

        root.getChildren().add(imageView);



        //These are for the players' information
        List<Player> players = gameString.getPlayers();
        HBox leftPlayerBox = new HBox(20);
        HBox rightPlayerBox = new HBox(20);

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            String playerData = "Color: " + p.getPlayerColour() + "\n" +
                    "Dirhams: " + p.getDirhams() + "\n" +
                    "Rugs: " + p.getRugNumber() + "\n" +
                    "Status: " + p.getStatus();

            Label playerLabel = new Label(playerData);
            playerLabel.setStyle("-fx-background-color: rgba(255,255,255,0.7); " +
                    "-fx-padding: 10; " +
                    "-fx-border-width: 2; " +
                    "-fx-border-color: black; " +
                    "-fx-font-size: 14px;");


            if (i < 2) {
                leftPlayerBox.getChildren().add(playerLabel);
            } else {
                rightPlayerBox.getChildren().add(playerLabel);
            }
        }

        leftPlayerBox.setLayoutX(10);
        leftPlayerBox.setLayoutY(10);

        rightPlayerBox.setLayoutX(WINDOW_WIDTH - 240);
        rightPlayerBox.setLayoutY(10);

        root.getChildren().addAll(leftPlayerBox, rightPlayerBox);
    }


    private static class Square extends Polygon {
        Square(double col, double row, double side){
            double shift = side / 2;
            this.getPoints().addAll(shift,shift,shift,-shift,-shift,-shift,-shift,shift);
            setLayoutX(row);
            setLayoutY(col);
        }
    }


    private Rectangle createDraggableRectangle(double x, double y, double width, double height, boolean vertical) {
        Rectangle rect = new Rectangle(x, y, width, height);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);

        rect.setOnMousePressed(event -> {
            offsetX = event.getSceneX() - rect.getX();
            offsetY = event.getSceneY() - rect.getY();
        });

        rect.setOnMouseDragged(event -> {
            rect.setX(event.getSceneX() - offsetX);
            rect.setY(event.getSceneY() - offsetY);
        });

        rect.setOnMouseReleased(event -> {

            double side = 80;
            rect.setX(Math.round(rect.getX() / side) * side);
            rect.setY(Math.round(rect.getY() / side) * side - 10);

            if (vertical) {
                col = (int) ((rect.getX() - 320) / side);
                row = (int) ((rect.getY() - 70) / side);
                position1 = new IntPair(row, col);
                position2 = new IntPair(row+1, col);
            }
            else {
                col = (int) ((rect.getX() - 320) / side);
                row = (int) ((rect.getY() - 70) / side);

                position1 = new IntPair(row,col);
                position2 = new IntPair(row,col+1);
            }
        });

        return rect;
    }


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
        setupPlayerButton(twoPlayers,2);

        Button threePlayers = new Button("Three Players");
        setupPlayerButton(threePlayers,3);

        Button fourPlayers = new Button("Four Players");
        setupPlayerButton(fourPlayers,4);

        vbox.getChildren().addAll(twoPlayers, threePlayers, fourPlayers);

        return new Scene(vbox, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private GameStatus setupInitialGameStatus(int playerCount) {
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

        Assam assam = new Assam("A33N");
        Board board = new Board("n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00");
        return new GameStatus(allPlayers, assam, board);
    }

    private void setupPlayerButton(Button button, int playerCount) {
        button.setPrefWidth(300);
        button.setPrefHeight(100);
        button.setFont(new Font(24));
        button.setOnAction(e -> {
            setPlayerCount(playerCount);
            Scene gameScene = createGameScene();
            Stage currentStage = (Stage) button.getScene().getWindow();
            currentStage.setScene(gameScene);
        });
    }


    private void setPlayerCount(int count) {
        this.playerCount = count;
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
        displayGame(setupInitialGameStatus(playerCount).toString());


        Colour[] rectangleColors = {Colour.YELLOW, Colour.PURPLE, Colour.CYAN, Colour.RED};
        double rectWidth = 160;
        double rectHeight = 80;
        for (int i = 0; i < 4; i++) {
            Rectangle draggableRect = createDraggableRectangle(50, 150 + i * (rectHeight + 20), rectWidth, rectHeight, false);
            draggableRect.setFill(Colour.colourToJavaFXColor(rectangleColors[i]));  // Set the rectangle color
            root.getChildren().add(draggableRect);
        }

        double rectWidthV = 80;
        double rectHeightV = 160;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Rectangle draggableRect = createDraggableRectangle(1000 + i * (rectWidthV + 20), 150 + j * (rectHeightV + 10), rectWidthV, rectHeightV, true);
                draggableRect.setFill(Colour.colourToJavaFXColor(rectangleColors[i * 2 + j]));
                root.getChildren().add(draggableRect);
            }
        }

        return new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
