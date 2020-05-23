# Bungee-Tools

Bungee Tools is a plugin that allows you to communicate between Nukkit and Bungeecord. You can retrieve or send various things to get the number of players or to transfer players. 

# API
Call the request class
```java
Request request = new Request();
```

Now set the handler class, where the response should come from the bungeecord server, should be handled
```java
request.setHandler(new ThisIsMyHandlerClass());
```

At least one player on the network is required to send a request#
```java
request.setPlayer(player.getName());
```

Now tell the type what you want to retrieve
```java
request.setType("PlayerCount");
```

Now enter the appropriate data, to specify the number of players number of a server, you need the name of the server
```java
request.setData(new String[]{"lobby"});
```

To send the request now, enter the following
```java
request.build();
```

# Handler Class
As in the example above we would like to have the number of players from the lobby server here
```java
public class ThisIsMyHandlerClass extends RequestResponseAction {

    @Override
    public void handle(Player player, String type, Object data) {
        if(type.equals("PlayerCount")) {
            String[] ex = data.toString().split(":");
            System.out.println("Player Count (" + ex[0] + "): " + ex[1]);
        }
    }
}
```

# Request Types
- "PlayerCount"
- "PlayerList"
- "GetServers"
- "GetServer"
- "ServerIP"
- "IP"

----------------

If problems arise, create an issue or write us on Discord.

| Discord |
| :---: |
[![Discord](https://img.shields.io/discord/639130989708181535.svg?style=flat-square&label=discord&colorB=7289da)](https://discord.gg/5tYC5dJ) |
