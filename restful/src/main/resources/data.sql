insert into user values(90001, sysdate(), 'user1', 'test1', '111111-1111111');
insert into user values(90002, sysdate(), 'user2', 'test2', '211111-1111111');
insert into user values(90003, sysdate(), 'user3', 'test3', '311111-1111111');

insert into post values(10001, 'My first post', 90001);
insert into post values(10002, 'My second post', 90001);