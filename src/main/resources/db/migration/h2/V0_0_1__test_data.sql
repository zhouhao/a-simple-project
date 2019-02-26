insert into user(name, phone)
values ('test_1', '12345678'),
       ('test_2', '87654321');

insert into todo(content, user_id, remind_time)
VALUES ('hello world 1_1', '1', DATEADD('DAY', 2, CURRENT_DATE)),
       ('hello world 1_2', '1', DATEADD('DAY', 1, CURRENT_DATE)),
       ('hello world 2_1', '2', DATEADD('DAY', -2, CURRENT_DATE))