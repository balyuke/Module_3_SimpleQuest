package com.javarush.balyuke.simplequest.repositories;

import com.javarush.balyuke.simplequest.entities.Answer;
import com.javarush.balyuke.simplequest.entities.Quest;
import com.javarush.balyuke.simplequest.entities.Question;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Repository {

    private JsonObject jsonObject;

    private static final Map<Integer, Quest> QUESTS = new HashMap<>();
    private static final Map<Integer, Question> QUESTIONS = new HashMap<>();
    private static final Map<Integer, Answer> ANSWERS = new HashMap<>();

    public Repository() {

        Quest quest1 = new Quest(1
                , "НЛО"
                , Quest.QuestType.QuestUFO);
        Quest quest2 = new Quest(2
                , "Тестовый квест"
                , Quest.QuestType.QuestTest);

//        String storyIntro = loadStoryIntroFromJsonFile();
//        String storyAcquaintanceText = loadStoryAcquaintanceTextFromJson();

//        quest1.setProlog("Пролог",
//                storyIntro);
//        quest1.setProlog("Знакомство с экипажем",
//                storyAcquaintanceText);

        quest1.setProlog("Пролог",
                "    Ты стоишь в космическом порту и готов подняться на борт\n" +
                "    своего корабля. Разве ты не об этом мечтал? Стать капитаном\n" +
                "    галактического судна с экипажем, который будет совершать\n" +
                "    подвиги под твоим командованием.\n" +
                "    Так что вперед!");
        quest1.setProlog("Знакомство с экипажем",
                "    Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной папкой в руках:\n" +
                "    — Здраствуйте, командир! Я Зинаида — ваша помощница. Видите? Там в углу пьет кофе\n" +
                "    наш штурман — сержант Перегарный Шлейф, под штурвалом спит наш бортмеханик — Чёрный Богдан,\n" +
                "    а фотографирует его Сергей Стальная Пятка — наш навигатор.\n" +
                "    А как обращаться к вам?");

        Question q1 = new Question(1, "Ты потерял память. Принять вызов НЛО?", quest1, Question.Type.USUAL);
        Question q2 = new Question(2, "Ты принял вызов. Поднимешся на мостик к их капитану?", quest1, Question.Type.USUAL);
        Question q3 = new Question(3, "Ты отклонил вызов. Смерть.", quest1, Question.Type.LOST);
        Question q4 = new Question(4, "Ты поднялся на мостик. Ты кто?", quest1, Question.Type.USUAL);
        Question q5 = new Question(5, "Ты не пошел на переговоры. Смерть.", quest1, Question.Type.LOST);
        Question q6 = new Question(6, "Тебя отвезли домой. Победа.", quest1, Question.Type.WON);
        Question q7 = new Question(7, "Твою ложь разоблачили. Смерть.", quest1, Question.Type.LOST);

        Answer a1 = new Answer(1, "Принять вызов.", quest1, q1, q2);
        Answer a2 = new Answer(2, "Отклонить вызов.", quest1, q1, q3);
        Answer a3 = new Answer(3, "Поднятся на мостик.", quest1, q2, q4);
        Answer a4 = new Answer(4, "Отказаться подниматься на мостик.", quest1, q2, q5);
        Answer a5 = new Answer(5, "Рассказать правду о себе.", quest1, q4, q6);
        Answer a6 = new Answer(6, "Солгать о себе.", quest1, q4, q7);

        QUESTS.put(quest1.getId(), quest1);
        QUESTS.put(quest2.getId(), quest2);

        QUESTIONS.put(q1.getId(), q1);
        QUESTIONS.put(q2.getId(), q2);
        QUESTIONS.put(q3.getId(), q3);
        QUESTIONS.put(q4.getId(), q4);
        QUESTIONS.put(q5.getId(), q5);
        QUESTIONS.put(q6.getId(), q6);
        QUESTIONS.put(q7.getId(), q7);

        ANSWERS.put(a1.getId(), a1);
        ANSWERS.put(a2.getId(), a2);
        ANSWERS.put(a3.getId(), a3);
        ANSWERS.put(a4.getId(), a4);
        ANSWERS.put(a5.getId(), a5);
        ANSWERS.put(a6.getId(), a6);
    }

    public Quest getQuestById(int id) { return QUESTS.get(id); }

    public Question getQuestionById(Integer id) {
        return QUESTIONS.get(id);
    }

    public Answer getAnswerById(Integer id) {
        return ANSWERS.get(id);
    }

    public Collection<Answer> getAnswersByFromQuestionId(Integer id) {
        return ANSWERS.values().stream()
                .filter(a -> a.getFrom().getId().equals(id))
                .collect(Collectors.toSet());
    }

    private JsonObject loadJsonObjectFromJsonFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("story.json");
        JsonReader jsonReader = Json.createReader(inputStream);

        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        return jsonObject;
    }

    private String loadStoryIntroFromJsonFile() {
        return jsonObject.getJsonObject("prologs").getString("prolog1");
    }

    private String loadStoryAcquaintanceTextFromJson() {
        return jsonObject.getJsonObject("prologs").getString("prolog2");
    }
}
