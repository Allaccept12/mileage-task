
insert into users(user_id, nick_name) values ('3ede0ef2-92b7-4817-a5f3-0c575361f741','사용자1');
insert into users(user_id, nick_name) values ('3ede0ef2-92b7-4817-a5f3-0c575361f742','사용자2');
insert into users(user_id, nick_name) values ('3ede0ef2-92b7-4817-a5f3-0c575361f743','사용자3');

insert into places(place_id, place_name) values ('2e4baf1c-5acb-4efb-a1af-eddada31b001','서울');
insert into places(place_id, place_name) values ('2e4baf1c-5acb-4efb-a1af-eddada31b002','부산');
insert into places(place_id, place_name) values ('2e4baf1c-5acb-4efb-a1af-eddada31b003','진주');
insert into places(place_id, place_name) values ('2e4baf1c-5acb-4efb-a1af-eddada31b004','제주도');

insert into reviews(review_id, created_date, modify_date, content,review_type, place_id, user_id)
values (
        '240a0658-dc5f-4878-9381-ebb7b2667771','2022-06-22 12:00:00','2022-06-22 12:00:00','좋은 장소','FIRST',
        '2e4baf1c-5acb-4efb-a1af-eddada31b001','3ede0ef2-92b7-4817-a5f3-0c575361f741');

insert into attached_photo_ids(review_id, attached_photo_ids) values('240a0658-dc5f-4878-9381-ebb7b2667771',
                                                                     'e4d1a64e-a531-46de-88d0-ff0ed70c0bb1');
insert into point_records(created_date, modify_date, bonus_point, content_point, place_id, user_id)
values ('2022-06-22 12:00:00', '2022-06-22 12:00:00',1,2,
        '2e4baf1c-5acb-4efb-a1af-eddada31b001','3ede0ef2-92b7-4817-a5f3-0c575361f741');

insert into reviews(review_id, created_date, modify_date, content,review_type, place_id, user_id)
values (
           '240a0658-dc5f-4878-9381-ebb7b2667772','2022-06-22 14:00:00','2022-06-22 14:00:00','','FIRST',
           '2e4baf1c-5acb-4efb-a1af-eddada31b002','3ede0ef2-92b7-4817-a5f3-0c575361f741');

insert into attached_photo_ids(review_id, attached_photo_ids) values('240a0658-dc5f-4878-9381-ebb7b2667772',
                                                                     'e4d1a64e-a531-46de-88d0-ff0ed70c0bb2');
insert into attached_photo_ids(review_id, attached_photo_ids) values('240a0658-dc5f-4878-9381-ebb7b2667772',
                                                                     'e4d1a64e-a531-46de-88d0-ff0ed70c0bb3');

insert into point_records(created_date, modify_date, bonus_point, content_point, place_id, user_id)
values ('2022-06-22 14:00:00', '2022-06-22 14:00:00',1,1,
        '2e4baf1c-5acb-4efb-a1af-eddada31b002','3ede0ef2-92b7-4817-a5f3-0c575361f741');


