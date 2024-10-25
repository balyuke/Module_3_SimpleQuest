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

public class RepositoryQuiz {

    private JsonObject jsonObject;

    private static final Map<Integer, Quest> QUESTS = new HashMap<>();
    private static final Map<Integer, Question> QUESTIONS = new HashMap<>();
    private static final Map<Integer, Answer> ANSWERS = new HashMap<>();

    public RepositoryQuiz() {

        Quest quest1 = new Quest(101
                , "НЛО"
                , Quest.QuestType.QuestUFO);

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
                "    - Здраствуйте, командир! Я Зинаида - ваша помощница. Видите? Там в углу пьет кофе\n" +
                "    наш штурман - сержант Перегарный Шлейф, под штурвалом спит наш бортмеханик - Чёрный Богдан,\n" +
                "    а фотографирует его Сергей Стальная Пятка - наш навигатор.\n" +
                "    А как обращаться к вам?");

        Question q101 = new Question(101, "Ты потерял память. Принять вызов НЛО?", quest1, Question.Type.CONTINUE);
        Question q102 = new Question(102, "Ты принял вызов. Поднимешься на мостик к их капитану?", quest1, Question.Type.CONTINUE);
        Question q103 = new Question(103, "Ты отклонил вызов. Смерть.", quest1, Question.Type.LOST);
        Question q104 = new Question(104, "Ты поднялся на мостик. Ты кто?", quest1, Question.Type.CONTINUE);
        Question q105 = new Question(105, "Ты не пошел на переговоры. Смерть.", quest1, Question.Type.LOST);
        Question q106 = new Question(106, "Тебя отвезли домой. Победа.", quest1, Question.Type.WON);
        Question q107 = new Question(107, "Твою ложь разоблачили. Смерть.", quest1, Question.Type.LOST);

        Answer a101 = new Answer(101, "Принять вызов.", quest1, q101, q102);
        Answer a102 = new Answer(102, "Отклонить вызов.", quest1, q101, q103);
        Answer a103 = new Answer(103, "Подняться на мостик.", quest1, q102, q104);
        Answer a104 = new Answer(104, "Отказаться подниматься на мостик.", quest1, q102, q105);
        Answer a105 = new Answer(105, "Рассказать правду о себе.", quest1, q104, q106);
        Answer a106 = new Answer(106, "Солгать о себе.", quest1, q104, q107);

        QUESTS.put(quest1.getId(), quest1);

        QUESTIONS.put(q101.getId(), q101);
        QUESTIONS.put(q102.getId(), q102);
        QUESTIONS.put(q103.getId(), q103);
        QUESTIONS.put(q104.getId(), q104);
        QUESTIONS.put(q105.getId(), q105);
        QUESTIONS.put(q106.getId(), q106);
        QUESTIONS.put(q107.getId(), q107);

        ANSWERS.put(a101.getId(), a101);
        ANSWERS.put(a102.getId(), a102);
        ANSWERS.put(a103.getId(), a103);
        ANSWERS.put(a104.getId(), a104);
        ANSWERS.put(a105.getId(), a105);
        ANSWERS.put(a106.getId(), a106);

        /*********************/

        Quest quest2 = new Quest(201
                , "Викторина"
                , Quest.QuestType.QuestTest);

        quest2.setProlog("Пролог",
                "    Ты с отличием закончил школу с углубленным знанием Географии\n" +
                "    - Здравствуйте, как вас зовут! Я Пелагея - ведущая нашей викторины. Видите? Слева от вас стоит\n" +
                "    сыграть решающую игру с такими же умникам как и ты на уровне города и в случае победы\n" +
                "    поехать на Российскую олимпиаду.\n" +
                "    Разве не об этом ты мечтал?");
        quest2.setProlog("Знакомство с участниками",
                "    Когда ты вышел в студию со зрителями, тебя поприветствовала девушка с черной папкой в руках:\n" +
                "    - Здравствуйте, как вас зовут! Я Пелагея - ведущая нашей викторины. Видите? Слева от вас стоит\n" +
                "    Антонио - он является победителй прошлогодней викторины. Вы готовы бросить ему вызов?\n" +
                "    Как к вам можно обращаться?");

        Question q201 = new Question(201, "Вопрос №1. Какова длина экватора Земли в км?", quest2, Question.Type.CONTINUE);
        Question q202 = new Question(202, "Вы успешно ответили на первый вопрос. <br> Длина экватора 40 075 км.  <br> Вопрос №2. Площадь какой из стран больше?", quest2, Question.Type.CONTINUE);
        Question q203 = new Question(203, "Вы ответили более 50000 и не угадали. <br> Длина экватора 40 075.", quest2, Question.Type.LOST);
        Question q204 = new Question(204, "Вы успешно ответили на Вопрос2. <br> Площадь Канады почти в 5 раз больше площади Мексики и составляет около 10 млн.км. <br> Вопрос №3. Какая река длиннее?", quest2, Question.Type.CONTINUE);
        Question q205 = new Question(205, "Вы ответили Мексика и не угадали.<br> Площадь Канады почти в 5 раз больше площади Мексики и составляет около 10 млн.км. <br> Поражение", quest2, Question.Type.LOST);
        Question q206 = new Question(206, "Вы успешно ответили на 3 вопроса и переходите в следующий раунд. <br> Победа.", quest2, Question.Type.WON);
        Question q207 = new Question(207, "Вы неверно ответили на Вопрос3. <br> Длина Амазонки 7 100 км, а длина Нила всего 6 650 км. <br> Поражение.", quest2, Question.Type.LOST);

        Answer a201 = new Answer(201, "Менее 50000 км", quest2, q201, q202);  // 40 075 км
        Answer a202 = new Answer(202, "Более 50000 км", quest2, q201, q203);
        Answer a203 = new Answer(203, "Мексика", quest2, q202, q204);         // Площадь Мексики 1 972 550 км
        Answer a204 = new Answer(204, "Канада", quest2, q202, q205);          // Площадь Канады 9 984 670 км
        Answer a205 = new Answer(205, "Амазонка", quest2, q204, q206);        // 7100
        Answer a206 = new Answer(206, "Нил", quest2, q204, q207);             // 6650


        QUESTS.put(quest2.getId(), quest2);

        QUESTIONS.put(q201.getId(), q201);
        QUESTIONS.put(q202.getId(), q202);
        QUESTIONS.put(q203.getId(), q203);
        QUESTIONS.put(q204.getId(), q204);
        QUESTIONS.put(q205.getId(), q205);
        QUESTIONS.put(q206.getId(), q206);
        QUESTIONS.put(q207.getId(), q207);

        ANSWERS.put(a201.getId(), a201);
        ANSWERS.put(a202.getId(), a202);
        ANSWERS.put(a203.getId(), a203);
        ANSWERS.put(a204.getId(), a204);
        ANSWERS.put(a205.getId(), a205);
        ANSWERS.put(a206.getId(), a206);
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
