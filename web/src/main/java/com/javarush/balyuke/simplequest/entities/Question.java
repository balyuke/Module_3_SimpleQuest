package com.javarush.balyuke.simplequest.entities;

public class Question extends AbstractEntity {
    private Quest quest;
    private Type type;

    public Question(Integer id, String content, Quest quest, Type type) {
        super(id, content);
        this.quest = quest;
        this.type = type;
    }

    public Quest getQuest() { return quest; }
    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        CONTINUE,
        WON,
        LOST
    }
}
