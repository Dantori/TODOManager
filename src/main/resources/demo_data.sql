insert into s_todo.t_user(id, password, username)
values (1, '$2a$10$IjIizGFoWbl79qpy/oDMcu9XvmiFKCL18yJLwYQ8zcg9zr7K3wWCy', 'admin'),
       (2, '$2a$10$n4qVmzTnNKlLApWsde1eR.EAJ.ZQZ1Svk5a2u2D/W.poaQkF7tKSu', 'Ilya');

insert into s_todo.t_role(id, name)
values (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

insert into s_todo.t_user_roles(user_id, roles_id)
values (1, 2),
       (2, 1);

insert into s_todo.t_todo(id, completed, description, user_id)
values (1, false, 'do homework', 2);