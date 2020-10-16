package chatProject.model.messages;

import chatProject.model.user.Status;
import chatProject.model.user.UserAccount;
import chatProject.model.user.UserInfo;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ChatRoomTest {
    @Test
    public void getName(){
        final Chatroom<String> chatroom = new Chatroom<>("Test",
                new UserInfo(
                        new UserAccount(0,"Username"),
                        Status.ACTIVE),
                new ArrayList<>());

        assertEquals("The chatroom's name should be the one written above", "Test", chatroom.getName());
    }

    @Test
    public void addMessage(){
        final UserInfo userInfo = new UserInfo(new UserAccount(0,"UserTest"),Status.ACTIVE);
        final Chatroom<String> chatroom = new Chatroom<>("Test", userInfo, new ArrayList<>());
        assertEquals("The messages should be the the one written above", new ArrayList<>(), chatroom.getCurrentMessages());

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(chatroom.addMessage(userInfo, "Test Message 1"));
        messages.add(chatroom.addMessage(new Message<>(1, userInfo, "Test add directly a message")));

        assertEquals("There are 2 messages in the list", 2, chatroom.getCurrentMessages().size());
        assertEquals("There are 2 messages in the list", messages, chatroom.getCurrentMessages());
    }

    @Test
    public void ToString_NullOwner() {
        final Chatroom<String> chatroom = new Chatroom<>("Test", null, new ArrayList<>());
        assertEquals("The toString() method of a Chatroom should print the name","Name", chatroom.toString());
    }

    @Test
    public void ToString_NotNullOwner() {
        final Chatroom<String> chatroom = new Chatroom<>("Test", new UserInfo(new UserAccount(0,"UserTest"), Status.ACTIVE), new ArrayList<>());
        assertEquals("The toString() method of a Chatroom should print the name","Test (UserTest)", chatroom.toString());
    }
}
