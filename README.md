# TopicBasedWebSocket
## Configuration
```java
@Configuration
@EnableWebSocketMessageBroker
public class SocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/cp").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry
                .setApplicationDestinationPrefixes("/app")
                .enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");

    }
}
```
> The  `.setApplicationDestinationPrefixes("/app")` prefix is used to navigate the request to the controller
```java
@MessageMapping("/message")
    @SendTo("/topic/message")
    public UserResponse publicMessage(@Payload UserResponse userResponse) {
        userResponse.setMessageType(UserResponse.MessageType.CHAT);
        userResponse.setGroupType(UserResponse.GroupType.PUBLIC);
        userResponse.setTime(new SimpleDateFormat("HH:mm").format(new Date()));
        return userResponse;
    }
    
 @MessageMapping("{groupId}/message")
    @SendTo("/topic/{groupId}/message")
    public UserResponse privateMessage(@DestinationVariable String groupId, @Payload UserResponse userResponse) {
        userResponse.setMessageType(UserResponse.MessageType.CHAT);
        userResponse.setGroupType(UserResponse.GroupType.PRIVATE);
        userResponse.setTime(new SimpleDateFormat("HH:mm").format(new Date()));
        return userResponse;
    }
```

## Each session has its own even which can be tracked by eventlistener
>SessionConnectEvent,SessionDisconnectEvent,SessionSubscribeEvent,UnsubscribeEvent

```java
@EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
       System.out.println("Session connnected");
    }
```

## Front end
```javascript
var socket = new SockJS('/cp');
let stompClient = Stomp.over(socket);
```

> `var socket = new SockJS('/cp');` has /cp which is the endpoint in `registry.addEndpoint("/cp").withSockJS();`
> Using Stomp `let stompClient = Stomp.over(socket);`

> `stompClient.subscribe('/topic/message', onMessageReceived);` fetchs all the messages that are send to ` @SendTo("/topic/message")`

> Message is sent to controller with `stompClient.send("/app/message", {}, JSON.stringify(response));` which is converted to `/message` and received `@MessageMapping("/message")`. 

> Here `/app` is the prefix we set in configuration `.setApplicationDestinationPrefixes("/app")`.

