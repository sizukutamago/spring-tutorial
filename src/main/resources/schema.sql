drop table if exists meeting_room cascade ;
drop table if exists reservable_room cascade ;
drop table if exists reservation cascade ;
drop table if exists usr cascade ;

create table if not exists meeting_room (
    room_id     serial not null,
    room_name   varchar(255) not null,
    primary key (room_id)
);

create table if not exists reservable_room (
    reserved_date   date not null,
    room_id         int4 not null,
    primary key (reserved_date, room_id)
);

create table if not exists reservation (
    reservation_id  serial not null,
    end_time        time not null,
    start_time      time not null,
    reserved_date   date not null,
    room_id         int4 not null,
    user_id         int4 not null,
    primary key (reservation_id)
);

create table if not exists usr (
    user_id     serial not null,
    first_name  varchar (255) not null,
    last_name   varchar (255) not null,
    password    varchar (255) not null,
    role_name   varchar (255) not null,
    primary key (user_id)
);

alter table reservable_room drop constraint if exists FK_room;
alter table reservation drop if exists FK_reservable_room;
alter table reservation drop if exists FK_user;
alter table reservable_room add constraint FK_room foreign key (room_id) references meeting_room;
alter table reservation add constraint FK_reservable_room foreign key (reserved_date, room_id) references reservable_room;
alter table reservation add constraint FK_user foreign key (user_id) references usr;
