--This sql is made using postgres syntax

-- USERS
CREATE TABLE users (
   id UUID PRIMARY KEY,
   username VARCHAR(30) NOT NULL,
   email VARCHAR(80) NOT NULL,
   password VARCHAR(100) NOT NULL
);

-- TO_DO_LIST
CREATE TABLE to_do_list (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(400),
    fk_user UUID NOT NULL REFERENCES users(id)
);

-- TASK
CREATE TABLE task (
    id UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description VARCHAR(400),
    completed BOOLEAN NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    completion_date DATE,
    state VARCHAR(20) NOT NULL,
    priority VARCHAR(20) NOT NULL,
    CONSTRAINT chk_state CHECK (state IN ('COMPLETED','TODO','LATE')),
    CONSTRAINT chk_priority CHECK (priority IN ('HIGH','MEDIUM', 'LOW')),
    fk_todolist UUID NOT NULL REFERENCES to_do_list(id)
);