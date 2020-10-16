package chatProject.model.messages;

import chatProject.model.user.Status;
import chatProject.model.user.UserAccount;
import chatProject.model.user.UserInfo;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ChatInstanceTest {
    @Test
    public void getCurentChatrooms(){
        UserInfo userInfo = new UserInfo(new UserAccount(0, "Test"), Status.ACTIVE);

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(new Message<>(0, userInfo, "toto"));

        final ArrayList<Chatroom<String>> chatrooms = new ArrayList<>();
        chatrooms.add(new Chatroom<>("ChatroomTest", userInfo, messages));

        final HashMap<UserInfo, LocalTime> users = new HashMap<>();

        final ChatInstance<String> chatInstance = new ChatInstance<>(chatrooms, users);

        assertEquals("The chatrooms should be the same that we created just before", chatrooms, chatInstance.getCurentChatrooms());
    }

    @Test
    public void getUsers(){
        UserInfo userInfo = new UserInfo(new UserAccount(0, "UserTest"), Status.ACTIVE);

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(new Message<>(0, userInfo, "toto"));

        final ArrayList<Chatroom<String>> chatrooms = new ArrayList<>();
        chatrooms.add(new Chatroom<>("ChatroomTest", userInfo, messages));

        final HashMap<UserInfo, LocalTime> users = new HashMap<>();
        users.put(userInfo, LocalTime.now());
        users.put(new UserInfo(new UserAccount(1, "UserTest2"), Status.ACTIVE), LocalTime.now());

        final ChatInstance<String> chatInstance = new ChatInstance<>(chatrooms, users);

        assertEquals("The users should be the same that we created just before", users, chatInstance.getUsers());
    }

    @Test
    public void addChatroom(){
        UserInfo userInfo = new UserInfo(new UserAccount(0, "UserTest"), Status.ACTIVE);

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(new Message<>(0, userInfo, "toto"));

        final ArrayList<Chatroom<String>> chatrooms = new ArrayList<>();

        final HashMap<UserInfo, LocalTime> users = new HashMap<>();
        users.put(userInfo, LocalTime.now());
        users.put(new UserInfo(new UserAccount(1, "UserTest2"), Status.ACTIVE), LocalTime.now());

        final ChatInstance<String> chatInstance = new ChatInstance<>(chatrooms, users);
        assertEquals("The chatrooms should be the same that we created just before", chatrooms, chatInstance.getCurentChatrooms());

        int idChatroom1 = chatInstance.addChatroom(new Chatroom<>("Chatroom1", userInfo, messages));
        int idChatroom2 = chatInstance.addChatroom(new Chatroom<>("Chatroom2", null, new ArrayList<>()));

        assertEquals("Two chatrooms have been added to the list", 2, chatInstance.getCurentChatrooms().size());
        assertEquals("The first chatroom is find into the list", "Chatroom1", chatInstance.getCurentChatrooms().get(idChatroom1).getName());
        assertEquals("The second chatroom is find into the list", "Chatroom2", chatInstance.getCurentChatrooms().get(idChatroom2).getName());

    }

    @Test
    public void addUser(){
        UserAccount userAccount = new UserAccount(0, "UserTest");
        UserInfo userInfo = new UserInfo(userAccount, Status.ACTIVE);

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(new Message<>(0, userInfo, "toto"));

        final ArrayList<Chatroom<String>> chatrooms = new ArrayList<>();
        chatrooms.add(new Chatroom<>("Chatroom1", userInfo, messages));

        final HashMap<UserInfo, LocalTime> users = new HashMap<>();

        final ChatInstance<String> chatInstance = new ChatInstance<>(chatrooms, users);

        assertEquals("The users should be the same that we created just before", users, chatInstance.getUsers());

        boolean isUser1 = chatInstance.addUser(userInfo);
        boolean isUser2 = chatInstance.addUser(userInfo);
        boolean isUser3 = chatInstance.addUser(new UserInfo(new UserAccount(1, "UserTest2"), Status.ACTIVE));

        assertEquals("We added 2 users into the list", 2, chatInstance.getUsers().size());
        assertFalse("User2 is already in the list", isUser2);
        assertTrue("We added this user", isUser1);
        assertTrue("We added this user", isUser3);
    }

    @Test
    public void initEmptyChat(){
        ChatInstance<String> chatInstance = ChatInstance.initEmptyChat();
        assertEquals(new ArrayList<>(), chatInstance.getCurentChatrooms());
        assertEquals(new HashMap<>(), chatInstance.getUsers());
    }
}
