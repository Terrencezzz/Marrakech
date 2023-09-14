package comp1110.ass2.gui;

import comp1110.ass2.*;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField boardTextField;


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     */
    void displayState(String state) {

        GameStatus gameString = new GameStatus(state);
        Board board = gameString.getBoard();
        List<Colour> rugColorList = board.getColourList(state);
        double side = 60;

        List<Square> squares = new ArrayList<>();
        double x = 140;
        double y = 240;
        for (int col = 0; col < 7; col++){
            for (int row = 0; row < 7; row++){
                Square square = new Square(x,y,side);
                Colour rugColour = rugColorList.get(col * 7 + row);
                Color javaFXColor = Colour.colourToJavaFXColor(rugColour);
                square.setFill(javaFXColor);
                square.setStroke(Color.BLACK);
                square.setStrokeWidth(2);
                squares.add(square);
                x += side + 11;
            }
            y += side + 62;
            x = 140;
        }
        root.getChildren().addAll(squares);


        Assam assam = gameString.getAssam();
        IntPair assamPosition = assam.getPosition();
        Direction assamDirection = assam.getDirection();
        int col = assamPosition.getCol();
        int row = assamPosition.getRow();

        double assamX = 140 + row * (side + 11);
        double assamY = 240 + col * (side + 62);

        Image assamImage = new Image("assam.png");
        ImageView imageView = new ImageView(assamImage);

        imageView.setX(assamX - assamImage.getWidth() / 2);
        imageView.setY(assamY - assamImage.getHeight() / 2);

        root.getChildren().add(imageView);
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

        URL imageUrl = getClass().getResource("BoardImage.png");
        if (imageUrl == null) {
            System.out.println("Failed to load background image.");
            System.exit(0);
        }
        Image backgroundImage = new Image(imageUrl.toString());
        ImageView backgroundImageView = new ImageView(backgroundImage);

        backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());

        root.getChildren().add(backgroundImageView);

        root.getChildren().add(controls);
        makeControls();

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
