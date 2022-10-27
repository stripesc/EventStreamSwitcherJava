import org.json.JSONObject;

public class AccessToken {
    public String accessToken;
    public String clientID;
    public String tokenType;

    public AccessToken(String accessToken, String tokenType, String clientID){
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.clientID = clientID;
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
