--liquibase formatted sql

--changeset mittal:1

CREATE TABLE  user_master
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    contact_no varchar(255) ,
    created_at DATETIME,
    deleted_at DATETIME ,
    dial_code varchar(255),
    email_id varchar(255) ,
    first_name varchar(255) ,
    is_active boolean,
    last_name varchar(255) ,
    password varchar(255) ,
    updated_at DATETIME ,
    CONSTRAINT user_master_pkey PRIMARY KEY (id)
);
--changeset mittal:2


CREATE TABLE user_sessions
( id BIGINT NOT NULL AUTO_INCREMENT,
    active boolean,
    last_access_date DATETIME ,
    logout_date DATETIME,
    login_date DATETIME,
    session_id varchar(255) ,
    session_timeout BIGINT,
    user_id BIGINT,
    CONSTRAINT user_sessions_pkey PRIMARY KEY (id),
    CONSTRAINT uk_bjoac5vd2jt3pnrfrdeb49014 UNIQUE (session_id)
);