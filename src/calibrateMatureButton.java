import java.awt.*;

class calibrateMatureButton {
    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot wallE = new Robot();
        Point[] corners = calibrate();
        System.out.println("move off button");
        Thread.sleep(2000);
        Color base = wallE.getPixelColor(corners[0].x,corners[0].y);
        System.out.println("matureButton mCheckr = new matureButton(" +
                        "new Point("+corners[0].x+", "+corners[0].y+"), " +
                        "new Point("+corners[1].x+", "+corners[1].y+"), " +
                        "new Point("+corners[2].x+", "+corners[2].y+"), " +
                        "new Point("+corners[3].x+", "+corners[3].y+"), " +
                        "new Color("+base.getRed()+", "+base.getGreen()+", "+base.getBlue()+"));"
        );
    }

    public static Point[] calibrate() throws InterruptedException {
        Point p1,p2,p3,p4;
        System.out.println("Go to top left corner");
        Thread.sleep(3000);
        p1 = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Go to top right corner");
        Thread.sleep(3000);
        p2 = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Go to bottom left corner");
        Thread.sleep(3000);
        p3 = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Go to bottom right corner");
        Thread.sleep(3000);
        p4 = MouseInfo.getPointerInfo().getLocation();
        return new Point[]{p1,p2,p3,p4};
    }
}
