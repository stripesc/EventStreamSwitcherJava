import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.KeyEvent;
import org.json.*;

public class switcher {
    public static ArrayList<String> streamers;
    public static ArrayList<String> streamerNames;
    public static int timesLooped = 0;

    public static void main(String[] args) throws InterruptedException, AWTException, IOException {
        AccessToken token = getToken();
        if (!token.isGood){
            System.out.println("Bad token, exiting program");
            System.exit(400);
        }
        Robot wallE = new Robot();
        matureButton mCheckr = new matureButton(new Point(-1063, 604), new Point(-959, 606), new Point(-1063, 624), new Point(-958, 622), new Color(157, 38, 249));
        while (true){
            streamers = getStreamerArray("streamers.txt");
            streamerNames = getStreamerArrayNoURL("streamers.txt");
            for (int i = 0; i < streamers.size(); i++){
                if (isLive(streamerNames.get(i), token)){
                    getStream(streamers.get(i), wallE);
                    Thread.sleep(5000);
                    mCheckr.manageButton();
                    Thread.sleep(10000);
                }
            }
            timesLooped++;
            System.out.println(timesLooped);
        }
    }

    public static void getStream(String name, Robot wallE) throws InterruptedException{
        try{
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
        } catch(IllegalArgumentException e){
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

    public static ArrayList<String> getStreamerArrayNoURL(String fileName) {
        ArrayList<String> streamers = new ArrayList<String>();
        try {
            File streamersFile = new File(fileName);
            Scanner fileReader = new Scanner(streamersFile);
            while(fileReader.hasNextLine()){
                streamers.add(fileReader.nextLine());
            }
        }
        catch(FileNotFoundException error){
            System.out.println("Error: " + error + " during file read");
        }
        return streamers;
    }

// ------------------------ CURL STUFF ---------------------------
    public static AccessToken getToken() throws IOException {
        return (new AccessToken());
//        String clientID = "YOUR_BOT_ID_HERE";
//        String clientSec = "YOUR_BOT_SECRET_HERE";
//        ArrayList<Character> stringHolder = new ArrayList<>();
//        String command = "curl -X POST \"https://id.twitch.tv/oauth2/token\" -H \"Content-Type: application/x-www-form-urlencoded\" -d \"client_id="+clientID+"&client_secret="+clientSec+"&grant_type=client_credentials\" --ssl-no-revoke";
//        Process process = Runtime.getRuntime().exec(command);
//        InputStreamReader out = new InputStreamReader((process.getInputStream()));
//        while (!out.ready()){
////            Hacky wait to wait until it's ready to read
//        }
//        while (out.ready()){
//            stringHolder.add((char)(out.read()));
//        }
//        String output = "";
//        for (int i = 0; i < stringHolder.size(); i++){
//            output += stringHolder.get(i);
//        }
//        JSONObject response = new JSONObject(output);
//
//        try{
//            response.get("status").equals(400);
//            return null;
//        }
//        catch(Exception e){
////            System.out.println("Good request");
//            return new AccessToken(response, clientID);
//        }
    }

    public static boolean isLive(String name, AccessToken token) throws IOException, InterruptedException {
        JSONObject stream = getStreamData(name, token);
//        System.out.println(!stream.get("data").toString().equals("[]"));
        return !stream.get("data").toString().equals("[]");
    }

    public static JSONObject getStreamData(String streamName, AccessToken token) throws IOException {
        ArrayList<Character> stringHolder = new ArrayList<>();
        String command = "curl -X GET \"https://api.twitch.tv/helix/streams?user_login="+streamName+"\" -H \"Authorization: "+token.toString()+"\" -H \"Client-Id: "+token.clientID+"\" --ssl-no-revoke";
//        System.out.println(command);
        Process process = Runtime.getRuntime().exec(command);
        InputStreamReader  out = new InputStreamReader((process.getInputStream()));
        while (!out.ready()){
//            Hacky wait to wait until it's ready to read
        }
        while (out.ready()){
            stringHolder.add((char)(out.read()));
        }
        String output = "";
        for (int i = 0; i < stringHolder.size(); i++){
            output += stringHolder.get(i);
        }
//        System.out.println(new JSONObject(output));
        return new JSONObject(output);
    }

    public static void checkMature(Robot wallE){

    }

}
