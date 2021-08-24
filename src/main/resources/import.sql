--the file specifying the initial data that will be written into database
insert into user(name,password) values('User1','user1');
insert into user(name,password) values('User2','user2');
insert into user(name, password) values('User3','user3');

insert into theatre(theatre_name) values('PVR');
insert into movie(movie_name) values('GodFather');
update movie set theatre_fid = (select theatre_id from theatre where theatre_name = 'PVR') where movie_name = 'GodFather';

insert into seat_user_association(seat_number) values(1);
insert into seat_user_association(seat_number) values(2);
insert into seat_user_association(seat_number) values(3);
insert into seat_user_association(seat_number) values(4);
insert into seat_user_association(seat_number) values(5);
insert into seat_user_association(seat_number) values(6);
insert into seat_user_association(seat_number) values(7);
insert into seat_user_association(seat_number) values(8);
insert into seat_user_association(seat_number) values(9);
insert into seat_user_association(seat_number) values(10);