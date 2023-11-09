# EventStreamSwitcherJava

## setup
1. in `src/AccessToken.java` place your twitch bots client ID and client secret in their repsective lines shown below
```
String clientID = "YOUR_BOT_ID_HERE";
String clientSec = "YOUR_BOT_SECRET_HERE";
```
2. To have the bot check for the "Mature stream" popup, follow the bellow steps. If not skip this step
    + Open a twitch stream with a "Mature stream"
    + Compile `src/calibrateMatureButton.java`
    + Run the compiled program and hover the corners indicated by the programs console output
    + Take the results outputted by the previous program and replace line 19 in `src/switcher.java` with the contents. It should look something like this, but with different numbers
 ```
 matureButton mCheckr = new matureButton(
  new Point(-1063, 604), new Point(-959, 606), new Point(-1063, 624),
  new Point(-958, 622), new Color(157, 38, 249)
 );
 ```
 *The code will be on one line in the output, but for readability its split into multiple lines*
 
3. Compile all the files in the `src/` folder
4. Open the browser you want to use (confirmed to work on chrome, but it works on any browser that has `Ctrl + L` jump to the search bar
5. If you want to try and avoid ads you can install an ad blocker on your browser, however this program will not block ads itself
6. Run the program and click into the browser window within 5 seconds
7. To stop the program you can terminate the program though either the task manager or via the console window that the program is running in.
