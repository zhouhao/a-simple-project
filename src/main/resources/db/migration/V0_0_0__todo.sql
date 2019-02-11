create table todo
(
  id           int auto_increment,
  content      varchar(256)                         not null,
  phone        varchar(20)                          not null,
  is_completed tinyint(1) default 0                 null,
  created_time timestamp  default current_timestamp null,
  remind_time  timestamp                            not null,
  constraint todo_pk primary key (id)
);

insert into todo (content, phone, is_completed, remind_time)
values ('todo 1', '11111111', false, '2019-01-01 00:00:01'),
       ('todo 2', '22222222', false, '2019-02-11 00:00:01'),
       ('todo 3', '33333333', false, '2019-02-15 00:00:01');