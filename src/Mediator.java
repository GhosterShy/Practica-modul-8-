import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

interface IMediator {
    void sendMessage(String message, IUser user);
    void addUser(IUser user);
    void removeUser(IUser user);
}




class ChatMediator implements IMediator {
    private List<IUser> users;

    public ChatMediator() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, IUser user) {
        for (IUser u : users) {
            if (u != user) {
                u.receiveMessage(message);
            }
        }
    }

    @Override
    public void addUser(IUser user) {
        this.users.add(user);
        notifyUsers(user.getName() + " вошел в чат.");
    }

    @Override
    public void removeUser(IUser user) {
        this.users.remove(user);
        notifyUsers(user.getName() + " покинул чат.");
    }

    private void notifyUsers(String notification) {
        for (IUser u : users) {
            u.receiveMessage(notification);
        }
    }
}

interface IUser {
    void sendMessage(String message);
    void receiveMessage(String message);
    String getName();
}



class User implements IUser {
    private String name;
    private IMediator mediator;

    public User(String name, IMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(this.name + ": Отправка сообщения => " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(this.name + ": Получено сообщение => " + message);
    }

    @Override
    public String getName() {
        return this.name;
    }
}



class ChannelMediator {
    private Map<String, ChatMediator> channels;

    public ChannelMediator() {
        this.channels = new HashMap<>();
    }

    public void createChannel(String channelName) {
        channels.put(channelName, new ChatMediator());
        System.out.println("Канал " + channelName + " создан.");
    }

    public ChatMediator getChannel(String channelName) {
        return channels.get(channelName);
    }
}






public class Mediator {
    public static void main(String[] args) {
        ChannelMediator channelMediator = new ChannelMediator();
        channelMediator.createChannel("Общий");
        channelMediator.createChannel("Игры");


        ChatMediator generalChat = channelMediator.getChannel("Общий");
        IUser user1 = new User("Alice", generalChat);
        IUser user2 = new User("Bob", generalChat);

        generalChat.addUser(user1);
        generalChat.addUser(user2);

        user1.sendMessage("Привет всем!");
        user2.sendMessage("Привет, Alice!");


        ChatMediator gamesChat = channelMediator.getChannel("Игры");
        IUser user3 = new User("Charlie", gamesChat);
        gamesChat.addUser(user3);

        user3.sendMessage("Есть кто на игру?");
        gamesChat.removeUser(user3);
    }
}
