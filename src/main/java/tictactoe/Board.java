package tictactoe;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private ChromeDriver driver;
    private Map<Space, Boolean> spaces = new HashMap();
    private By emptySpacesSelector = By.xpath("//div[@class='board']/div/div[@class='']/..");

    public Board(ChromeDriver driver) {
        this.driver = driver;
        Arrays.stream(Space.values()).forEach(space -> spaces.put(space, false));
    }

    public void play(Space space){
        driver.findElement(space.getLocator()).click();
        spaces.put(space, true);
        try{ Thread.sleep(500); } catch(Exception e){}
    }

    /**
     * Updates Spaces map to be in sync with the game on the browser
     */
    private void updateSpaceOccupancy(){
        var emptyElements = driver.findElements(emptySpacesSelector)
                .stream()
                .map(e->e.getAttribute("class"))
                .collect(Collectors.toList());

        Arrays.stream(Space.values()).forEach(space -> {
            if(!emptyElements.contains(space.getClassName())){
                spaces.put(space, true);
            }
        });
    }

    public List<Space> getOpenSpaces(){
        updateSpaceOccupancy();
        return spaces.entrySet()
                .stream()
                .filter(occupied -> !occupied.getValue())
                .map(space->space.getKey())
                .collect(Collectors.toList());
    }
}
