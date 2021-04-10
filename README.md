# Helix-Raid-Tool
## Discord raid tool for both user and bot raids.

download jar at https://github.com/Supergamer5465/Helix-Raid-Tool/releases

Please make sure to follow this guide so that you do not use the raid tool improperly

<details>
<summary>Guide for Bot Raiding</summary>
  
### A step-by-step guide
  
  1. Have admin privileges, or know another administrator that can add the bot for you
  2. Create a discord application at https://discord.com/developers
  3. In the application, go to the tab labeled "bot"
  4. create your bot
  5. Scroll down. Under "Privileged Gateway Intents", enable "server members intent."
  6. Scroll up, copy the bot token, and paste it into the "bot token" area in the raid tool window.
  7. Open the OAuth2 tab
  8. In the first checkbox which is labeled "scopes", select "bot."
  9. In the second checkbox which is labeled "bot permissions", select "administrator."
  10. When both options are selected, copy the url which appears under the first checkbox.
  11. Either open the URL and select the server (if you have admin), or send the URL to the admin who will add the bot to the server for you.
  12. Now, open the raid tool window
  13. Paste your proxies into the designated area
  14. Type your message into the designated area (must be less than 2000 characters)
  15. Paste the ID of the target server into the area which says "server id". You can find the ID by having developer mode enabled in discord settings, right clicking the server icon, and clicking "copy ID."
  16. Paste user ID's into the mass ban exemption area (only if you/your friends don't want to get banned). You can obtain the user ID's by right clicking your icon similarly to how one would obtain the server ID.
  17. Once the bot is added to the correct server, start the raid.
  
</details>

<details>
<summary>GUI information</summary>

![image](https://user-images.githubusercontent.com/64598162/114188992-52582680-990f-11eb-8e3d-39c50a8107ba.png)

Enter your bot token here like in the above image

![image](https://user-images.githubusercontent.com/64598162/114189232-9814ef00-990f-11eb-8331-67ed3f2b857d.png)

Enter your bot tokens on each line with `proxy:port`, do not leave an empty line at the bottom.

![image](https://user-images.githubusercontent.com/64598162/114189596-f9d55900-990f-11eb-8430-7f29bcf5dce7.png)

Type anything in the message content box. It must be within 2000 characters, which is discord's character limit for sending messages.

![image](https://user-images.githubusercontent.com/64598162/114189744-25f0da00-9910-11eb-889a-17ea1481e503.png)

![image](https://user-images.githubusercontent.com/64598162/114190136-939d0600-9910-11eb-8ed9-1fac1808ce26.png)

Enter the server ID. It can be obtained by turning on Settings > Advanced > Developer Mode in the discord client, right clicking the target server, and clicking "copy ID"

![image](https://user-images.githubusercontent.com/64598162/114276382-fe6f3f80-99eb-11eb-9db1-d213beac54a5.png)
![image](https://user-images.githubusercontent.com/64598162/114190218-b16a6b00-9910-11eb-9f5c-cee169a72ed8.png)
![image](https://user-images.githubusercontent.com/64598162/114190675-2d64b300-9911-11eb-89dd-65650cad20cb.png)

The nuke bot includes a mass banning feature, the mass ban exemption box can be left empty if you are willing to let those who are not admin become banned by the bot or "Enable Mass Ban" is not selected.

![image](https://user-images.githubusercontent.com/64598162/114191281-dd3a2080-9911-11eb-9811-90e497324204.png)

You do not need to do anything with the output box, it is just the console output.

![image](https://user-images.githubusercontent.com/64598162/114191400-fc38b280-9911-11eb-994b-67e37774e18e.png)

Quite self-explanatory here. Start raid: starts the raid, Stop raid: abruptly stops the raid.

</details>
