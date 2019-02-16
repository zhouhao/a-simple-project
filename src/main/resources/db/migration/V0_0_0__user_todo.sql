create table user
(
  id           int auto_increment,
  name         varchar(256)                        not null,
  phone        varchar(20)                         not null,
  created_time timestamp default current_timestamp null,
  constraint user_pk primary key (id)
);

create table todo
(
  id           int auto_increment,
  content      varchar(256)                         not null,
  user_id      int                                  not null,
  is_completed tinyint(1) default 0                 null,
  created_time timestamp  default current_timestamp null,
  remind_time  timestamp                            not null,
  constraint todo_pk primary key (id)
);

alter table todo
  add constraint todo_user_id_fk
    foreign key (user_id) references user (id);