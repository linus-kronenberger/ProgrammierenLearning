package de.dhbwka.java.musterklausurenselbsterstellt.SnatChat;

import de.dhbwka.java.musterklausurenselbsterstellt.SnatChat.Message;
import de.dhbwka.java.musterklausurenselbsterstellt.SnatChat.SnatChatFrontend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SnatChatRoom {
    private String roomName;
    private List<SnatChatFrontend> frontends = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public SnatChatRoom(String roomName) {
        this.roomName = roomName;
    }

    public List<SnatChatFrontend> getFrontends() {
        return frontends;
    }

    public void setFrontends(List<SnatChatFrontend> frontends) {
        this.frontends = frontends;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return this.roomName;
    }
    public void register(SnatChatFrontend s) {
        List<String> lines = new ArrayList<>();
        List<String> lastLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("de/dhbwka/java/musterklausurenselbsterstellt/SnatChat/"+roomName+".txt"))) {
            while (br.ready()) {
                String line = Message.rot13(br.readLine());
                lines.add(line);
                System.out.println("Line Read: " + line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(lines.size() >= 10) {
            for (int i = lines.size() - 10; i < lines.size(); i++) {
                lastLines.add(lines.get(i));
            }
        } else {
            lastLines = lines;
        }

        System.out.println(lastLines);
        frontends.add(s);
        for (SnatChatFrontend frontend : frontends) {
            for (String line : lastLines) {
                frontend.receiveMessage(line);
            }
        }
    }
    public void unregister(SnatChatFrontend s) {
        frontends.remove(s);
    }
    public void sendMessage(Message msg) {
        frontends.forEach(f->{f.receiveMessage(msg);});
        write(msg.getSender().getName() + ": " + msg.getText(), roomName);
        messages.add(msg);
    }
    public void sendMessage(String text) {
        frontends.forEach(f->{f.receiveMessage(text);});
        write(text, roomName);
    }
    public void write(String text, String roomName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("de/dhbwka/java/musterklausurenselbsterstellt/SnatChat/" + roomName + ".txt", true))) {
            bw.write(Message.rot13(text));
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
