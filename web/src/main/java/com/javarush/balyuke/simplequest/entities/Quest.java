package com.javarush.balyuke.simplequest.entities;

import java.util.HashMap;
import java.util.Map;

public class Quest extends AbstractEntity {
    private QuestType questType;
    private Map<String, String> prologs = new HashMap<>();

    public Quest(Integer id, String content, QuestType questType) {
        super(id, content);
        this.questType = questType;
    }


    public void setProlog(String key, String value) { prologs.put(key, value); }

    public QuestType getType() {
        return questType;
    }

    public void setType(QuestType questType) {
        this.questType = questType;
    }

    public enum QuestType {
        QuestUFO,
        QuestTest
    }
}
