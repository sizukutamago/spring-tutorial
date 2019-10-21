-- 会議室
insert into meeting_room (room_name) values ('新木場');
insert into meeting_room (room_name) values ('辰巳');
insert into meeting_room (room_name) values ('豊洲');
insert into meeting_room (room_name) values ('月島');
insert into meeting_room (room_name) values ('新富町');
insert into meeting_room (room_name) values ('銀座一丁目');
insert into meeting_room (room_name) values ('有楽町');

insert into reservable_room (reserved_date, room_id)values (CURRENT_DATE, 1);
insert into reservable_room (reserved_date, room_id)values (CURRENT_DATE + 1, 1);
insert into reservable_room (reserved_date, room_id)values (CURRENT_DATE - 1, 1);

insert into reservable_room (reserved_date, room_id)values (CURRENT_DATE, 7);
insert into reservable_room (reserved_date, room_id)values (CURRENT_DATE + 1, 7);
insert into reservable_room (reserved_date, room_id)values (CURRENT_DATE - 1, 7);

insert into usr (user_id, first_name, last_name, password, role_name) values ('toro-yamada', '太郎', '山田', 'test', 'USER');
