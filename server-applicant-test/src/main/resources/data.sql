/**
 * CREATE Script for init of DB
 */

-- Create 5 Cars
insert into car (id, date_created, last_modified, manufacturer, model, color, license_plate, seat_count, convertible, rating, engine_type) 
values (1001, now(), now(), 'Ford', 'Ka', 'White', 'XPT-2023', 5, false, 3.5, 'GAS');

insert into car (id, date_created, last_modified, manufacturer, model, color, license_plate, seat_count, convertible, rating, engine_type) 
values (1002, now(), now(), 'Mercedes', 'SLK 200', 'Grey', 'XYZ-2032', 5, true, 4.5, 'ELECTRIC');

insert into car (id, date_created, last_modified, manufacturer, model, color, license_plate, seat_count, convertible, rating, engine_type) 
values (1003, now(), now(), 'BMW', 'X6', 'Black', 'BZH-4232', 6, false, 4.8, 'HYBRID');

insert into car (id, date_created, last_modified, manufacturer, model, color, license_plate, seat_count, convertible, rating, engine_type) 
values (1004, now(), now(), 'BMW', 'i36', 'Black', 'BZX-6372', 6, false, 4.9, 'HYBRID');

insert into car (id, date_created, last_modified, manufacturer, model, color, license_plate, seat_count, convertible, rating, engine_type) 
values (1005, now(), now(), 'BMW', 'X8', 'Black', 'TZS-7361', 6, false, 5.0, 'HYBRID');



-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1001, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (1002, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (1003, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1004, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (1005, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (1006, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (1007,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (1008,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

-- Select car 1 for driver 4
update driver set car = 1001 where id = 1004;

-- Select car 2 for driver 5
update driver set car = 1002 where id = 1005;

-- Select car 3 for driver 6
update driver set car = 1003 where id = 1006;