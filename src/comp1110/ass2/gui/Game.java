package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    @Override
    public void start(Stage stage) throws Exception {
        // FIXME Task 7 and 15
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);

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

        stage.setScene(scene);
        stage.show();
    }
}
