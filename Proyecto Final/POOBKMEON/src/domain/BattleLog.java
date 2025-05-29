package domain;

import java.io.Serializable;
import java.util.*;

public class BattleLog implements Serializable{
    private static BattleLog instance;
    private final ArrayList<String> messages = new ArrayList<>();
    private final ArrayList<Integer> damage = new ArrayList<>();

    private BattleLog(){}

    public static BattleLog getInstance() {
        if (instance == null) instance = new BattleLog();
        return instance;
    }

    public void addMessage(String msg) {
        messages.add(msg);
    }
    public void addDamage(int damageValue) {
        damage.add(damageValue);
    }

    public int getLastDamage() {
        if (messages.isEmpty()) {
        return 0; 
    }
        return damage.getLast();
    }

    public String getLastMessage() {
        if (messages.isEmpty()) {
        return "Elige tu acción"; 
    }
        return messages.getLast();
    }


    public void clear() {
        messages.clear();
        damage.clear();
    }
}