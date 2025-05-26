package domain;

import java.io.Serializable;
import java.util.*;

public class BattleLog implements Serializable{
    private static BattleLog instance;
    private final List<String> messages = new ArrayList<>();

    private BattleLog() {}

    public static BattleLog getInstance() {
        if (instance == null) instance = new BattleLog();
        return instance;
    }

    public void addMessage(String msg) {
        messages.add(msg);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }
    public String getLastMessage() {
        if (messages.isEmpty()) {
        return "Elige tu acción"; 
    }
        return messages.getLast();
    }

    public void clear() {
        messages.clear();
    }
}