drop table if exists attached_photo_ids;
drop table if exists point_records;
drop table if exists reviews;
drop table if exists places;
drop table if exists users;

create table attached_photo_ids(
    review_id varchar(40) not null ,
    attached_photo_ids varchar(40)
) character set utf8mb4;

create table places (
        place_id   varchar(40) not null,
        place_name varchar(255) not null,
        primary key (place_id)
) character set utf8mb4;

create table point_records(
    record_id bigint not null auto_increment,
    created_date datetime(6),
    modify_date datetime(6),
    bonus_point integer not null default 0,
    content_point integer not null default 0,
    place_id varchar(40) not null ,
    user_id varchar(40) not null ,
    index place_user_record_idx (place_id,user_id),
    index record_created_idx (created_date),
    primary key (record_id)
) character set utf8mb4;

create table reviews(
    review_id varchar(40) not null ,
    created_date datetime(6),
    modify_date datetime(6),
    content text,
    review_type varchar(20) not null default 'NORMAL',
    place_id varchar(40) not null,
    user_id varchar(40) not null,
    index place_user_idx (place_id,user_id),
    primary key (review_id)
) character set utf8mb4;

create table users(
    user_id varchar(40) not null,
    nick_name varchar(20) not null ,
    primary key (user_id)
) character set utf8mb4;

alter table point_records
    add constraint foreign key (place_id)
    references places (place_id);

alter table point_records
    add constraint foreign key (user_id)
        references users (user_id);

alter table reviews
    add constraint foreign key (place_id)
    references places (place_id);

alter table reviews
    add constraint foreign key (user_id)
    references users (user_id);

alter table attached_photo_ids
    add constraint foreign key (review_id)
        references reviews (review_id);