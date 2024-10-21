package com.javarush.balyuke.simplequest.entities;

public class Answer extends AbstractEntity {
    private Quest quest;
    private Question from;

    private Question to;

    public Answer(Integer id, String content, Quest quest, Question from, Question to) {
        super(id, content);
        this.quest = quest;
        this.from = from;
        this.to = to;
    }

    public Question getFrom() {
        return from;
    }

    public void setFrom(Question from) {
        this.from = from;
    }

    public Question getTo() {
        return to;
    }

    public void setTo(Question to) {
        this.to = to;
    }
}
