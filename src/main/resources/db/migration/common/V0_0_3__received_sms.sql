create table received_sms
(
  id           int auto_increment,
  from_phone   varchar(20)                         not null,
  to_phone     varchar(20)                         not null,
  message      varchar(1024)                       not null,
  created_time timestamp default current_timestamp null,
  constraint received_sms_pk primary key (id)
);