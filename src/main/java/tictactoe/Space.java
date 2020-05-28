package tictactoe;

import org.openqa.selenium.By;
import static java.lang.String.format;

public enum Space {

    TOP_LEFT("square top left"),
    TOP_CENTER("square top"),
    TOP_RIGHT("square top right"),
    CENTER_LEFT("square left"),
    CENTER_CENTER("square"),
    CENTER_RIGHT("square right"),
    BOTTOM_LEFT("square bottom left"),
    BOTTOM_CENTER("square bottom"),
    BOTTOM_RIGHT("square bottom right");

    private String className;
    private By locator;

    Space(String className){
        this.className = className;
        locator = By.xpath(format("//div[@class='%s']", className));
    }

    public String getClassName(){
        return className;
    }

    public By getLocator(){
        return locator;
    }
}