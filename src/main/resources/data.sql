insert into meeting_room (room_id, room_name) values (1, '新木場');
insert into meeting_room (room_id, room_name) values (2, '辰巳');
insert into meeting_room (room_id, room_name) values (3, '豊洲');
insert into meeting_room (room_id, room_name) values (4, '月島');
insert into meeting_room (room_id, room_name) values (5, '新富町');
insert into meeting_room (room_id, room_name) values (6, '銀座一丁目');
insert into meeting_room (room_id, room_name) values (7, '有楽町');

insert into reservable_room (reserved_date, room_id)values (CURRENT_DATE, 1);

insert into reservable_room (reserved_date, room_id)values (CURRENT_DATE, 7);

insert into usr (user_id, first_name, last_name, password, role_name) values (1, '太郎', '山田', 'test', 'USER');
