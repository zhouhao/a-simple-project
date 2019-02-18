create table reminder_history
(
  id           int auto_increment,
  user_id      int                                 not null,
  todo_id      int                                 not null,
  created_time timestamp default current_timestamp null,
  constraint reminder_history_pk primary key (id)
);