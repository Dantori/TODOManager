CREATE TABLE t_user
(
    id       BIGSERIAL NOT NULL,
    password VARCHAR(255),
    username VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE t_role
(
    id   BIGSERIAL NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE t_todo
(
    id          BIGSERIAL NOT NULL,
    completed   BOOLEAN   NOT NULL,
    date        TIMESTAMP,
    description VARCHAR(255),
    user_id     BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES t_user (id)
);

CREATE TABLE t_user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, roles_id),
    CONSTRAINT FK_user_id_roles FOREIGN KEY (user_id) REFERENCES t_user (id),
    CONSTRAINT FK_roles_id FOREIGN KEY (roles_id) REFERENCES t_role (id)
);
