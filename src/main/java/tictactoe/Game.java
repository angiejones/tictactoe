package tictactoe;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Game {

    private ChromeDriver driver;
    private Board board;

    private int playerScore, tieScore, computerScore;
    private By restartIndicator = By.className("restart");

    public Game() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://playtictactoe.org/");

        board = new Board(driver);
    }

    public Board getBoard(){
        return board;
    }

    public boolean isOver(){
        return driver.findElement(restartIndicator).isDisplayed();
    }

    public boolean isThereAWinner(int winningScore){
        updateScores();
        return playerScore >= winningScore || computerScore >= winningScore;
    }

    /**
     * Gets scores from the browser
     */
    public void updateScores(){
        var scores = driver.findElementsByClassName("score")
                .stream()
                .map(WebElement::getText)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        playerScore = scores.get(0);
        tieScore = scores.get(1);
        computerScore = scores.get(2);
    }

    public Board restart() {
        driver.findElement(restartIndicator).click();
        board = new Board(driver);
        return board;
    }

    public void printResults(){
        if(playerScore > computerScore){
            System.out.println(format("Yayyy, you won! 🎉 Score: %d:%d", playerScore, computerScore));
        }
        else if(computerScore > playerScore){
            System.out.println(format("Awww, you lose. 😩 Score: %d:%d", computerScore, playerScore));
        }
    }

    public void end(){
        printResults();
        driver.quit();
        System.exit(0);
    }
}
