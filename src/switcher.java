import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.KeyEvent;

public class switcher {
    public static ArrayList<String> streamers = getStreamerArray("streamers.txt");
    public static int timesLooped = 0;

    public static void main(String[] args) throws InterruptedException, AWTException {
//        Robot wallE = new Robot();
//        wallE.mouseMove(500,200);
//        System.out.println(wallE.getPixelColor(500,200));
        while (true){
            for (int i = 0; i < streamers.size(); i++){
                getStream(streamers.get(i));
                Thread.sleep(10000);
            }
            timesLooped++;
            System.out.println(timesLooped);
        }
    }

    public static void getStream(String name) throws InterruptedException{
        try{
            Robot wallE = new Robot();
            wallE.keyPress(17);
            wallE.keyPress('L');
            wallE.keyRelease('L');
            wallE.keyRelease(17);
            Thread.sleep(100);
            for (int i = 0; i < name.length(); i++){
                if (Character.isUpperCase(name.charAt(i))){
                    wallE.keyPress(16);
                    wallE.keyPress(Character.toUpperCase(name.charAt(i)));
                    wallE.keyRelease(Character.toUpperCase(name.charAt(i)));
                    wallE.keyRelease(16);
                }
                else {
                    wallE.keyPress(Character.toUpperCase(name.charAt(i)));
                    wallE.keyRelease(Character.toUpperCase(name.charAt(i)));
                }
            }
            Thread.sleep(100);
            wallE.keyPress(KeyEvent.VK_ENTER);
            wallE.keyRelease(KeyEvent.VK_ENTER);
        }
        catch(AWTException e){
            System.out.println("Not enough access");
            System.out.println(e.getMessage());
        }
        catch(IllegalArgumentException e){
            System.out.println("Bad character code");
        }
    }

    public static ArrayList<String> getStreamerArray(String fileName) {
        ArrayList<String> streamers = new ArrayList<String>();
        try {
            File streamersFile = new File(fileName);
            Scanner fileReader = new Scanner(streamersFile);
            while(fileReader.hasNextLine()){
                streamers.add("twitch.tv/"+fileReader.nextLine());
            }
        }
        catch(FileNotFoundException error){
            System.out.println("Error: " + error + " during file read");
        }
        return streamers;
    }
}
