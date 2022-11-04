import java.awt.*;
import java.awt.event.InputEvent;

public class matureButton {
    public Point topLeft;
    public Point topRight;
    public Point bottomLeft;
    public Point bottomRight;
    public Color base;
    public  Robot wallE;

    public matureButton(Point p1, Point p2, Point p3, Point p4, Color c1) throws AWTException {
        this.topLeft = p1;
        this.topRight = p2;
        this.bottomLeft = p3;
        this.bottomRight = p4;
        this.base = c1;
        wallE = new Robot();
    }

    public void manageButton() throws AWTException {
        if (checkForButton()){
            wallE.mouseMove(topLeft.x, topLeft.y);
            wallE.mousePress(InputEvent.BUTTON1_MASK);
            wallE.mouseRelease(InputEvent.BUTTON1_MASK);
            wallE.mouseMove(0, 0);
        }
    }

    public boolean checkForButton() throws AWTException {
        wallE = new Robot();
        if (!wallE.getPixelColor(topLeft.x, topLeft.y).equals(base)){
           return false;
        }
        if (!wallE.getPixelColor(topRight.x, topRight.y).equals(base)){
            return false;
        }
        if (!wallE.getPixelColor(bottomLeft.x, bottomLeft.y).equals(base)){
            return false;
        }
        if (!wallE.getPixelColor(bottomRight.x, bottomRight.y).equals(base)){
            return false;
        }
        return true;
    }

}
