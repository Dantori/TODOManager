insert into s_todo.t_user(password, username)
values ('$2a$10$ASpZ82vnvoQ6D09cKcAmWeolIKkIMiJDNEUjC/7rGztE5nd5kQ1ii', 'admin'),
       ('$2a$10$ASpZ82vnvoQ6D09cKcAmWeolIKkIMiJDNEUjC/7rGztE5nd5kQ1ii', 'Ilya');

insert into s_todo.t_role(name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into s_todo.t_user_roles(user_id, roles_id)
values (1, 2),
       (2, 1);

insert into s_todo.t_todo(completed, description, user_id)
values (false, 'do homework', 2);