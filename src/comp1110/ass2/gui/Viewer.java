package comp1110.ass2.gui;

import comp1110.ass2.BasicClasses.*;
import comp1110.ass2.EnumClasses.Colour;
import comp1110.ass2.EnumClasses.Direction;
import comp1110.ass2.FunctionalClasses.IntPair;
import comp1110.ass2.FunctionalClasses.Rotation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private static final Group root = new Group();
    private final Group controls = new Group();
    private TextField boardTextField;


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     */
    void displayState(String state) {

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

        rightPlayerBox.setLayoutX(VIEWER_WIDTH - 240);
        rightPlayerBox.setLayoutY(10);

        root.getChildren().addAll(leftPlayerBox, rightPlayerBox);
    }

    private static class Square extends Polygon{
        Square(double col, double row, double side){
            double shift = side / 2;
            this.getPoints().addAll(shift,shift,shift,-shift,-shift,-shift,-shift,shift);
            setLayoutX(row);
            setLayoutY(col);
        }
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label boardLabel = new Label("Game State:");
        boardTextField = new TextField();
        boardTextField.prefWidthProperty().bind(root.sceneProperty().get().widthProperty().subtract(250));


        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayState(boardTextField.getText());
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(boardLabel,
                boardTextField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);


        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Marrakech Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

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
        greyBoard.setX((double) (VIEWER_WIDTH - 560) / 2);
        greyBoard.setY((double) (VIEWER_HEIGHT - 560) / 2);

        root.getChildren().add(greyBoard);

        root.getChildren().add(controls);
        makeControls();

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
