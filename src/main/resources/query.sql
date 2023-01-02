CREATE DATABASE kpp_labs;

CREATE TABLE discipline
(
    id                BIGINT AUTO_INCREMENT,
    name              VARCHAR(100) NOT NULL,
    hour_per_semester INT          NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY (id)
);

CREATE TABLE teacher
(
    id            BIGINT AUTO_INCREMENT,
    surname       VARCHAR(100) NOT NULL,
    name          VARCHAR(100) NOT NULL,
    father_name   VARCHAR(100),
    phone_number  VARCHAR(20)  NOT NULL,
    email         VARCHAR(255) NOT NULL,
    discipline_id BIGINT,
    CONSTRAINT pk_id PRIMARY KEY (id),
    CONSTRAINT fk_teacher_discipline FOREIGN KEY (discipline_id) REFERENCES discipline (id)
);


CREATE TABLE schedule
(
    id             BIGINT AUTO_INCREMENT,
    start_time     TIMESTAMP   NOT NULL,
    end_time       TIMESTAMP   NOT NULL,
    cabinet_number VARCHAR(10) NOT NULL,
    discipline_id  BIGINT      NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY (id),
    CONSTRAINT fk_schedule_discipline FOREIGN KEY (discipline_id) REFERENCES discipline (id)
);