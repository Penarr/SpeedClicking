/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedclicking;

import java.util.Random;

/**
 * Model that tracks seconds, score and the high score and generates
 * random coordinates within a specified range for the game.
 * @author 000738570
 */
public class Model {
    private int score;
    private int seconds;
    private int highScore;
    private Random rand = new Random();
    
    //Adds one to score
    public void addScore() {
        this.score += 1;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    //Random number method
    public int randNum(int min, int max) {
        return (rand.nextInt((max - min) + 1) + min);
    }
    //Method to update high score
    public void newHigh() {
        if (score > highScore) {
            highScore = score;
        }
    }
    //GET for highScore
    public int getHighScore() {
        return highScore;
    }
    //GET for score
    public int getScore() {
        return score;
    }
    //GET for seconds
    public int getSeconds() {
        return seconds;
    }
    //SUBTRACT one from seconds for timer
    public void oneSecond() {
        seconds--;
    }
    
    //SET seconds 
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
    
}



