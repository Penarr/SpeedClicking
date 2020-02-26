/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedclicking;


import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * A simple game that randomly generates buttons on a screen that the user has to click
 * within 30 seconds. 
 * @author 000738570
 */
public class SpeedClicking extends Application {

    //DECLARATIONS
    private Pane root;
    private Button start;
    private Button click;
    private Label highLabel;
    private Timeline timer;
    private Label timerLabel;
    private Label finalScore;
    private Model model;
    private Label titleLabel;

    /*ACTION HANDLER for the start button. Will remove the start button,
    add a timer label and the new click button, move the click button somewhere
    random and track the amount of time that has passed. Will contain a KeyFrame
    handler*/
    private void startHandler(ActionEvent e) {
        model.setScore(0);
        //Click button will show the score plus one wiht initial background colour
        click.setText(Integer.toString(model.getScore() + 1));
        click.setStyle("-fx-background-color: #4bf2ba;");
        //set seconds for the clock
        model.setSeconds(30);
        //Timer label font size and color and position
        timerLabel.setFont(Font.font("Lucida Console", 24));
        timerLabel.setStyle("-fx-text-fill: white;");
        timerLabel.relocate(0, 0);
        
        
        //REMOVE nodes
        root.getChildren().removeAll(finalScore, start);
        //ADD timer label and click button
        root.getChildren().addAll(click, timerLabel);
        
        //CLICK button size and random location
        click.setPrefHeight(50);
        click.setPrefWidth(50);
        click.relocate(model.randNum(0, 550), model.randNum(20, 550));
        
        //NEW timeline
        timer = new Timeline();
        //Will count 30 seconds
        timer.setCycleCount(model.getSeconds());
        timer.getKeyFrames().add(
                //COUNT by 1 second and detect when it has counted.
                //ACTION handler will subtract 1 from the second value and update
                //the timer label
                new KeyFrame(Duration.seconds(1), (ActionEvent e1) -> {
                    model.oneSecond();
                    //UPDATE timer label
                    timerLabel.setText(Integer.toString(model.getSeconds()));
                    
                    
                    
                    //IF seconds reach zero the current nodes will be cleared,
                    //the final score will be highs and a button to play again
                    //will appear
                    
                   
                    if (model.getSeconds() == 0) {
                        //REMOVE nodes
                        root.getChildren().removeAll(click, timerLabel);
                        
                        
                        
                        //Start button after the game is done
                        start.setStyle("-fx-font: 56 Impact; -fx-base: #49B361;");
                        start.setPrefHeight(300);
                        start.setPrefWidth(600);
                        start.setText("Play Again?");
                        start.relocate(5, 310);
                        //Final score style and text
                        finalScore.setStyle("-fx-text-fill: white;");
                        finalScore.setText("Final Score: \n     " + Integer.toString(model.getScore()));
                        
                        //adds final score and start button
                        root.getChildren().addAll(finalScore, start);
                        
                        
                    }
                }));
        timer.play();
    }

    /*ACTION HANDLER. When the button is clicked the score is increased by one and
     the score is incrased by 1 point*/
    private void clickHandler(ActionEvent e) {
        //MOVE click button
        click.relocate(model.randNum(0, 550), model.randNum(20, 550));

        //ADD one to score and update score label
        model.addScore();
        click.setText(Integer.toString(model.getScore() + 1));
        
        //UPDATE high score
        if (model.getScore() > model.getHighScore()) {
            model.newHigh();
            highLabel.setText("High Score: " + Integer.toString(model.getHighScore()));
        }
        //CHANGES click button color based on score
        if (model.getScore() > 9) {
            click.setStyle("-fx-background-color: #C99BFF;  ");
    }
    
        if (model.getScore() > 19) {
            click.setStyle("-fx-background-color: #56ef56;");
    }
    
        if (model.getScore() > 29) {
            click.setStyle("-fx-background-color: #ffe43a;");
    }
        if (model.getScore() > 39) {
            click.setStyle("-fx-background-color: #ff8800;");
    }
        
        if (model.getScore() > 49) {
            click.setStyle("-fx-background-color: #ff0000;  -fx-text-fill: #ffffff;");
    }
        
        
    }
    
    
    /**
     * @param stage
     * @throws java.lang.Exception
     *
     */
    @Override
    public void start(Stage stage) throws Exception {
        //DECLARE nodes
        root = new Pane();
        stage.setTitle("Speed Clicking");
        stage.setResizable(false);
        Scene scene = new Scene(root, 600, 600);
        //Background color of black on node
        root.setStyle("-fx-background-color: #000000;");
        //Declarations
        model = new Model();
        titleLabel = new Label("Speed Clicking");
        click = new Button(Integer.toString(model.getScore() + 1));
        timer = new Timeline();
        timerLabel = new Label("30");
        start = new Button("Start");
        highLabel = new Label("High Score: 0");
       
        
        
        //Set stage
        
        stage.setScene(scene);
        //START button coordinates, size and font
        start.relocate(275, 275);
        start.setPrefWidth(50);
        start.setPrefHeight(50);
        start.setStyle("-fx-font: 14 Impact; -fx-base: #49B361;");

        //setting font for the click button
        click.setFont(Font.font("Impact", 14));
        
        

        //HIGH SCORE location and font size
        highLabel.relocate(465, 0);
        highLabel.setFont(Font.font("Lucida Console", 16));
        highLabel.setStyle("-fx-text-fill: white;");
        //titleLabel location and font
        titleLabel.relocate(200,0);
        titleLabel.setFont(Font.font("Lucida Console", 24));
        titleLabel.setStyle("-fx-text-fill: white;");
        
        //finalScore Label location and font
        finalScore = new Label();
        finalScore.setFont(Font.font("Lucida Console", 45));
        finalScore.relocate(150, 100);
        
        //ADD children start button and highsinitial score
        root.getChildren().addAll(start, titleLabel, highLabel);

        //LISTENERS to redirect button action to handlers
        start.setOnAction(this::startHandler);
        click.setOnAction(this::clickHandler);

        //SHOW stage
        stage.show();
    }

    //MAIN method to launch
    public static void main(String[] args) {
        launch(args);
    }

}
