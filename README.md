# EventStreamSwitcherJava

## setup
1. in `src/switcher.java` place your twitch bots client ID and client secret in their repsective lines shown below
```
String clientID = "YOUR_BOT_ID_HERE";
String clientSec = "YOUR_BOT_SECRET_HERE";
```
2. To have the bot check for the "Mature stream" popup, follow the bellow steps. If not skip this step
 1. Open a twitch stream with a "Mature stream"
 2. Complie `src/calibrateMatureButton.java`
 3. Run the compiled output and hover the corners indicated by the programs console output
 4. Take the results final outputed line and replace line 19 in `src/switcher.java` with the contents. It should look somthing liek this with different numbers
 ```
 matureButton mCheckr = new matureButton(new Point(-1063, 604), new Point(-959, 606), new Point(-1063, 624), new Point(-958, 622), new Color(157, 38, 249));
 ```
