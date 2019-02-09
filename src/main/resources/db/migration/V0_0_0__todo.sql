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

