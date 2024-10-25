
-- создаем схему
-- create schema simplequestdb;
-- drop schema simplequestdb; -- cascade;


create table flight (
     id bigint primary key,
     flight_no varchar(16),
     departure_date timestamp,
     departure_airport_code char(3),
     arrival_date timestamp,
     arrival_airport_code char(3),
     aircraft_id int,
     status varchar(32)
);

insert into flight(id, flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id, status)
values (2,1,'2024-10-19 00:20:26','LED','2024-10-19 04:20:26','KZN',1,'CANCELLED');

SELECT f.id, f.flight_no, f.departure_date, f.departure_airport_code, f.arrival_date, f.arrival_airport_code, f.aircraft_id, f.status
FROM flight f;

-- drop table flight;


create table ticket (
    id bigint primary key,
    passport_no varchar(64),
    passenger_name varchar(256),
    flight_id bigint,
    seat_no char(4),
    cost numeric(8,2)
);


insert into ticket(id, passport_no, passenger_name, flight_id, seat_no, cost)
values (1,112233, 'Ivanov Ivan',1,'A1',200.00),
        (2,123456,'Petrov Petr',1,'B1',200.00),
        (3,654321,'Sidorov Max',1,'C1',175.00),
        (4,112233,'Ivanov Ivan',1,'A1',220.00);


-- drop table ticket;


-- таблица пользователей
create table user
(
    user_id  bigint primary key,
    login    varchar(128) unique,
    password varchar(128) not null,
    role     varchar(32)
);

insert into user(user_id, login, password, role)
values (1, 'ben', '123', 'ADMIN'),
       (2, 'user', '123', 'USER'),
       (3, 'quest', '123', 'GUEST'),
       (4, 'admin', '123', 'ADMIN');

-- drop table user;



create table questtype
(
    questtype_id    bigint primary key,
    name            varchar(32)
);

insert into questtype (questtype_id, name)
values  (1, 'QuestUFO'),
        (2, 'QuestTest')
;

-- drop table questtype;



-- таблица квестов
create table quest
(
    quest_id        bigint primary key,
    content         text,
    description     varchar(128),
    quest_type    varchar(32)
);

insert into quest(quest_id, content, description, quest_type)
values (1, 'JR Quest', 'Ты попал на корабль пришельцев. Попробуй вернуться домой живым!', 'QuestUFO'),
       (2, 'тест квест', 'тест квест', 'QuestTest')
;

-- drop table quest;


# -- таблица игр
# create table game
# (
#     game_id          bigint primary key,
#     player           bigint references user (user_id),
#     quest            bigint references quest (quest_id),
#     current_question bigint,
#     game_state       varchar(32),
#     date           date
# );
#
# insert into game(game_id, player, quest, current_question, game_state, date)
# values (1, 1, 1, null, 'WIN', '2024-10-21')
# ;
#
# -- drop table game;

create table questiontype
(
    questiontype_id bigint primary key,
    name            varchar(32)
);

insert into questiontype (questiontype_id, name)
values  (1, 'CONTINUE'),
        (2, 'WIN'),
        (3, 'LOST');

-- drop table questiontype;


-- таблица вопросов
create table question
(
    question_id     bigint primary key,
    content         text,
    quest           bigint references quest (quest_id),
    type            varchar(32)
);

insert into question(question_id, content, quest, type)
values  (1, 'Ты потерял память. Принять вызов НЛО?', 1, 'CONTINUE'),
        (2, 'Ты принял вызов. Поднимешься на мостик к их капитану?', 1, 'CONTINUE'),
        (3, 'Ты отклонил вызов. Смерть.', 1, 'LOST'),
        (4, 'Ты поднялся на мостик. Ты кто?', 1, 'CONTINUE'),
        (5, 'Ты не пошел на переговоры. Смерть.', 1, 'LOST'),
        (6, 'Тебя отвезли домой. Победа.', 1, 'WON'),
        (7, 'Твою ложь разоблачили. Смерть.', 1, 'LOST')
;

-- drop table question;


-- таблица ответов
create table answer
(
    answer_id       bigint primary key,
    content         text,
    quest           bigint references quest (quest_id),
    question_from   bigint references question (question_id),
    question_to     bigint references question (question_id)
);

insert into answer(answer_id, content, quest, question_from, question_to)
values  (1, 'Принять вызов.', 1, 1, 2),
        (2, 'Отклонить вызов.', 1, 1, 3),
        (3, 'Подняться на мостик.', 1, 2, 4),
        (4, 'Отказаться подниматься на мостик.', 1, 2, 5),
        (5, 'Рассказать правду о себе.', 1, 4, 6),
        (6, 'Солгать о себе.', 1, 4, 7)
;

-- drop table answer;
