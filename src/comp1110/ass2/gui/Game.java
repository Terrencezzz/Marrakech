package comp1110.ass2.gui;

import comp1110.ass2.BasicClasses.*;
import comp1110.ass2.EnumClasses.Colour;
import comp1110.ass2.EnumClasses.Direction;
import comp1110.ass2.FunctionalClasses.Die;
import comp1110.ass2.FunctionalClasses.IntPair;
import comp1110.ass2.FunctionalClasses.Rotation;
import comp1110.ass2.Marrakech;
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
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Terrnece Wu
 */

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;
    int playerCount;
    private double offsetX = 0;
    private double offsetY = 0;

    int row = 0;
    int col = 0;
    int index = 0;
    double rotationAngle;
    int rotateTime = 1;
    int totalRound = 15;
    List<String> turn = new ArrayList<>();
    List<Player> allPlayers = new ArrayList<>();
    Assam assam = new Assam("A33N");
    String assamString = assam.toString();
    Board board = new Board("n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00");
    GameStatus g = new GameStatus(allPlayers,assam,board);
    String gameString = g.toString();
    IntPair position1;
    IntPair position2;
    String gameStatus = "Game Start";
    String assanStatus = "Not Yet";


    void displayGame(String state) {

        root.getChildren().removeIf(child -> !"background".equals(child.getId()));

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

        rotationAngle = Rotation.directionToInt(assamDirection);
        imageView.setRotate(rotationAngle);

        imageView.setOnMouseClicked(e -> {
            if (gameStatus.equals("Game Start")) {
                rotationAngle += 90;
                rotateAssam();
                if (rotationAngle == 360) {
                    rotationAngle = 0;
                }
                Assam newAssam = new Assam(assamString);
                GameStatus g = new GameStatus(allPlayers,newAssam,board);
                updateGameString(g);
                displayGame(g.toString());
            }
            else if (gameStatus.equals("Game Process") && assanStatus.equals("Not Yet")) {
                rotationAngle += 90;
                if (rotationAngle == 360) {
                    rotationAngle = 0;
                }

                if (rotateTime == 2) {
                    rotateAssam();
                    updateRotateTime();
                }

                rotateAssam();
                updateRotateTime();
                Assam newAssam = new Assam(assamString);
                GameStatus g = new GameStatus(allPlayers,newAssam,board);
                updateGameString(g);
                displayGame(g.toString());
            }
        });

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

        leftPlayerBox.setId("background");
        rightPlayerBox.setId("background");

        root.getChildren().addAll(leftPlayerBox, rightPlayerBox);


        Text text = new Text("Turn: " + turn.get(index));
        text.setX(1000);
        text.setY(600);
        text.setFont(new Font(30));
        root.getChildren().add(text);

        Colour[] rectangleColors = {Colour.YELLOW, Colour.PURPLE, Colour.CYAN, Colour.RED};
        double rectWidth = 160;
        double rectHeight = 80;
        for (int i = 0; i < 4; i++) {
            Rectangle draggableRect = createDraggableRectangle(50, 150 + i * (rectHeight + 20), rectWidth, rectHeight, false, rectangleColors[i]);
            draggableRect.setFill(Colour.colourToJavaFXColor(rectangleColors[i]));
            root.getChildren().add(draggableRect);
        }

        double rectWidthV = 80;
        double rectHeightV = 160;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Rectangle draggableRect = createDraggableRectangle(1000 + i * (rectWidthV + 20), 150 + j * (rectHeightV + 10), rectWidthV, rectHeightV, true, rectangleColors[i * 2 + j]);
                draggableRect.setFill(Colour.colourToJavaFXColor(rectangleColors[i * 2 + j]));
                root.getChildren().add(draggableRect);
            }
        }
    }

    void updateRotateTime() {
        if (rotateTime < 4) {
            rotateTime += 1;
        }
        else {
            rotateTime = 1;
        }
    }

    private static class Square extends Polygon {
        Square(double col, double row, double side){
            double shift = side / 2;
            this.getPoints().addAll(shift,shift,shift,-shift,-shift,-shift,-shift,shift);
            setLayoutX(row);
            setLayoutY(col);
        }
    }


    private Rectangle createDraggableRectangle(double x, double y, double width, double height, boolean vertical, Colour rectangleColour) {
        Rectangle rect = new Rectangle(x, y, width, height);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);

        if (turn.get(index).equals(rectangleColour.toString()) && assanStatus.equals("Moved")) {
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

                    updatePosition1(row,col);
                    updatePosition2(row+1,col);
                    List<IntPair> positions = new ArrayList<>();
                    positions.add(position1);
                    positions.add(position2);
                    if (checkRug(positions)) {
                        makePlacement(positions);
                        updateIndex();
                        changeAssam();
                        displayGame(gameString);
                    }
                }
                else {
                    col = (int) ((rect.getX() - 320) / side);
                    row = (int) ((rect.getY() - 70) / side);

                    updatePosition1(row,col);
                    updatePosition2(row,col+1);
                    List<IntPair> positions = new ArrayList<>();
                    positions.add(position1);
                    positions.add(position2);
                    if (checkRug(positions)) {
                        makePlacement(positions);
                        updateIndex();
                        changeAssam();
                        displayGame(gameString);
                    }
                }
            });
        }

        return rect;
    }

    private boolean checkRug(List<IntPair> positions) {
        Player p = allPlayers.get(index);
        char colour = Colour.colourToChar(p.getPlayerColour());
        int id = 15 - totalRound;
        Rug r = new Rug(colour,id,positions);
        return Marrakech.isPlacementValid(gameString,r.toString());
    }

    void updateIndex() {
        if (playerCount == 2) {
            if (index == 1) {
                index = 0;
                totalRound -= 1;
            }
            else {
                index += 1;
            }
        }
        else if (playerCount == 3) {
            if (index == 2) {
                index = 0;
                totalRound -= 1;
            }
            else {
                index += 1;
            }
        }
        else {
            if (index == 3) {
                index = 0;
                totalRound -= 1;
            }
            else {
                index += 1;
            }
        }
    }

    void changeAssam() {
        assanStatus = "Not Yet";
    }

    void updatePosition1(int row, int col) {
        position1 = new IntPair(row,col);

    }

    void updatePosition2(int row, int col) {
        position2 = new IntPair(row,col);
    }

    void winner() {
        char winner = Marrakech.getWinner(gameString);
        if (winner != 'n') {
            openWinnerScreen(winner);
        }
    }

    void openWinnerScreen(char winner) {
        Stage stage = new Stage();
        stage.setTitle("Winner");

        StackPane root = new StackPane();
        Label label = new Label("Winner: " + winner);
        root.getChildren().add(label);

        Scene scene = new Scene(root, 200, 200);
        stage.setScene(scene);

        stage.show();
    }

    void makePlacement(List<IntPair> positions) {
        if (turn.get(index).equals("CYAN")) {
            for (Player Player : allPlayers) {
                if (Colour.colourToChar(Player.getPlayerColour()) == 'c') {
                    int id = 15 - totalRound;
                    Rug r = new Rug('c',id,positions);
                    gameString = Marrakech.makePlacement(gameString,r.toString());
                    GameStatus game = new GameStatus(gameString);
                    board = game.getBoard();
                    allPlayers = game.getPlayers();
                    winner();
                }
            }
        }
        else if (turn.get(index).equals("YELLOW")) {
            for (Player Player : allPlayers) {
                if (Colour.colourToChar(Player.getPlayerColour()) == 'y') {
                    int id = 15 - totalRound;
                    Rug r = new Rug('y',id,positions);
                    gameString = Marrakech.makePlacement(gameString,r.toString());
                    GameStatus game = new GameStatus(gameString);
                    board = game.getBoard();
                    allPlayers = game.getPlayers();
                    winner();
                }
            }
        }
        else if (turn.get(index).equals("PURPLE")) {
            for (Player Player : allPlayers) {
                if (Colour.colourToChar(Player.getPlayerColour()) == 'p') {
                    int id = 15 - totalRound;
                    Rug r = new Rug('p',id,positions);
                    gameString = Marrakech.makePlacement(gameString,r.toString());
                    GameStatus game = new GameStatus(gameString);
                    board = game.getBoard();
                    allPlayers = game.getPlayers();
                    winner();
                }
            }
        }
        else if (turn.get(index).equals("RED")) {
            for (Player Player : allPlayers) {
                if (Colour.colourToChar(Player.getPlayerColour()) == 'r') {
                    int id = 15 - totalRound;
                    Rug r = new Rug('r',id,positions);
                    gameString = Marrakech.makePlacement(gameString,r.toString());
                    GameStatus game = new GameStatus(gameString);
                    board = game.getBoard();
                    allPlayers = game.getPlayers();
                    winner();
                }
            }
        }
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

        return new GameStatus(allPlayers, assam, board);
    }


    private void setupPlayerButton(Button button, int playerCount) {
        button.setPrefWidth(300);
        button.setPrefHeight(100);
        button.setFont(new Font(24));
        button.setOnAction(e -> {
            setPlayerCount(playerCount);
            setTurn(playerCount);
            Scene gameScene = createGameScene();
            Stage currentStage = (Stage) button.getScene().getWindow();
            currentStage.setScene(gameScene);
        });
    }

    void rotateAssam() {
        assamString = Marrakech.rotateAssam(assamString,90);
    }


    private void setPlayerCount(int count) {
        this.playerCount = count;
    }

    private void setTurn(int count) {
        if (count == 2) {
            turn.add("CYAN");
            turn.add("YELLOW");
        }
        else if (count == 3) {
            turn.add("CYAN");
            turn.add("YELLOW");
            turn.add("PURPLE");
        }
        else {
            turn.add("CYAN");
            turn.add("YELLOW");
            turn.add("PURPLE");
            turn.add("RED");
        }
    }

    void updateGameString(GameStatus g) {
        gameString = g.toString();
    }

    private void applyTransform() {
        assamString = Marrakech.moveAssam(assamString, Die.getDieNumber());
        gameStatus = "Game Process";
        assanStatus = "Moved";
    }

    void payment() {
        GameStatus game = new GameStatus(gameString);
        Assam a = game.getAssam();

        IntPair p = a.getPosition();
        Colour c = Board.getColour(Board.getColourList(gameString),p); //Assam stand
        
        char sta = 0;
        Player pg = null;
        for (Player pc : allPlayers) {
            if (pc.getPlayerColour().equals(c)) {
                pg = pc;
                sta = pc.getStatus();
            }
        }
        
        if (!allPlayers.get(index).getPlayerColour().equals(c) && sta == 'i') {
            int pay = Marrakech.getPaymentAmount(gameString);
            int money = allPlayers.get(index).getDirhams();

            int moneyG = pg.getDirhams();
            int indexG = allPlayers.indexOf(pg);
            
            if (money > pay) {
                Player player = new Player(money-pay,allPlayers.get(index).getRugNumber(),allPlayers.get(index).getStatus(),allPlayers.get(index).getPlayerColour());
                allPlayers.set(index,player);

                Player playerG = new Player(moneyG+pay,pg.getRugNumber(),pg.getStatus(),pg.getPlayerColour());
                allPlayers.set(indexG,playerG);
                winner();
            }
            else {
                String str = turn.get(index);
                Player player = null;
                for (Player pla : allPlayers) {
                    if (pla.getPlayerColour().toString().equals(str)) {
                        player = pla;
                    }
                }

                int j = allPlayers.indexOf(player);
                player = new Player(0,allPlayers.get(j).getRugNumber(),'o',allPlayers.get(j).getPlayerColour());
                allPlayers.set(j,player);

                Player playerG = new Player(moneyG+money,pg.getRugNumber(),pg.getStatus(),pg.getPlayerColour());
                allPlayers.set(indexG,playerG);

                playerCount -= 1;
                turn.remove(index);

                if (index == playerCount) {
                    index = 0;
                    totalRound -= 1;
                }

                assanStatus = "Not Yet";

                game = new GameStatus(allPlayers,assam,board);
                updateGameString(game);
                displayGame(gameString);
                winner();
            }
            
        }
    }
    private Scene createGameScene() {

        Button goButton = new Button("GO");
        goButton.setId("background");
        goButton.setPrefWidth(100);
        goButton.setPrefHeight(50);
        goButton.setLayoutX(50);
        goButton.setLayoutY(WINDOW_HEIGHT - 100);
        goButton.setOnAction(e -> {
            if (assanStatus.equals("Not Yet")) {
                applyTransform();
                Assam newAssam = new Assam(assamString);
                GameStatus g = new GameStatus(allPlayers,newAssam,board);
                updateGameString(g);
                payment();
                g = new GameStatus(allPlayers,newAssam,board);
                updateGameString(g);
                displayGame(g.toString());
                winner();
            }
        });

        root.getChildren().add(goButton);


        int centerX1 = 400;
        int centerY1 = 70;
        int centerX2 = 320;
        int centerY2 = 630;
        int centerX3 = 880;
        int radius = 60;

        for (int i = 0; i < 4; i++) {
            Circle circle = new Circle(centerX1 + i * 160, centerY1, radius);
            circle.setFill(Color.BLUE);
            circle.setId("background");
            root.getChildren().add(circle);
        }

        for (int i = 0; i < 4; i++) {
            Circle circle = new Circle(centerX2 + i * 160, centerY2, radius);
            circle.setFill(Color.BLUE);
            circle.setId("background");
            root.getChildren().add(circle);
        }

        for (int i = 1; i < 4; i++) {
            Circle circle = new Circle(centerX2, centerY2 - i * 160, radius);
            circle.setFill(Color.BLUE);
            circle.setId("background");
            root.getChildren().add(circle);
        }

        for (int i = 1; i < 4; i++) {
            Circle circle = new Circle(centerX3, centerY1 + i * 160, radius);
            circle.setFill(Color.BLUE);
            circle.setId("background");
            root.getChildren().add(circle);
        }

        Rectangle greyBoard = new Rectangle();
        greyBoard.setId("background");
        greyBoard.setWidth(560);
        greyBoard.setHeight(560);
        greyBoard.setFill(Color.GRAY);
        greyBoard.setX((double) (WINDOW_WIDTH - 560) / 2);
        greyBoard.setY((double) (WINDOW_HEIGHT - 560) / 2);

        double x = greyBoard.getX();
        double y = greyBoard.getY();
        double side = 80;

        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                Rectangle square = new Rectangle(x + col * side, y + row * side, side, side);
                square.setFill(Color.GREY);
                square.setStroke(Color.BLACK);
                square.setStrokeWidth(1);
                square.setId("background");
                root.getChildren().add(square);
            }
        }
        displayGame(setupInitialGameStatus(playerCount).toString());

        return new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public static void main(String[] args) {
        launch(args);
    }
}