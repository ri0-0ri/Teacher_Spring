use gb;
create table test_user(
	userid varchar(300) primary key,
    userpw varchar(300),
    username varchar(300)
);
select * from test_user;

create table test_board(
	boardnum int primary key auto_increment,
    boardtitle varchar(300),
    boardcontents varchar(3000),
	regdate datetime default now(),
    userid varchar(300)
);
select * from test_board;