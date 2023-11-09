import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AccessToken {
    public String accessToken;
    public String clientID = "YOUR_BOT_ID_HERE";
    public String clientSec = "YOUR_BOT_SECRET_HERE";
    public String tokenType;
    public boolean isGood;

    public AccessToken() throws IOException {
        ArrayList<Character> stringHolder = new ArrayList<>();
        String command = "curl -X POST \"https://id.twitch.tv/oauth2/token\" -H \"Content-Type: application/x-www-form-urlencoded\" -d \"client_id="+this.clientID+"&client_secret="+this.clientSec+"&grant_type=client_credentials\" --ssl-no-revoke";
        Process process = Runtime.getRuntime().exec(command);
        InputStreamReader out = new InputStreamReader((process.getInputStream()));
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
        JSONObject response = new JSONObject(output);

        try{
            response.get("status").equals(400);
            isGood = false;
        }
        catch(Exception e){
//            System.out.println("Good request");
            isGood = true;
        }
    }
    public AccessToken(JSONObject response, String clientID){
        this.accessToken = (String)response.get("access_token");
        this.tokenType = (String)response.get("token_type");
        this.clientID = clientID;
    }
    public String toString() {
        return tokenType.substring(0, 1).toUpperCase() + tokenType.substring(1) + " " + accessToken;
    }
}
