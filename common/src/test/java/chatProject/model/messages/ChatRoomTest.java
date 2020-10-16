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
}
