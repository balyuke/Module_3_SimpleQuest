package com.javarush.balyuke.simplequest.entities;

public class Prolog extends AbstractEntity{

    private Quest quest;

    public Prolog(Integer id, String content, Quest quest) {

        super(id, content);
        this.quest = quest;
    }

    public Quest getQuest() { return quest; }
    public void setQuest(Quest quest) {
        this.quest = quest;
    }

}
