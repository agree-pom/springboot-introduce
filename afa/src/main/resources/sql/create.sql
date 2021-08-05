declare
    exist number;
begin
    select count(1) into exist from user_tables where table_name = 'USER_INFO';
    if exist < 1 then
        execute immediate 'create table user_info_2 (
                 user_id number(19,0) not null,
                 login_cnt number(10,0) default 0,
                 password varchar2(255 char) not null,
                 user_code varchar2(255 char) not null unique ,
                 user_name varchar2(255 char) not null unique ,
                 user_status varchar2(255 char), primary key (user_id)
            )
        ';
    end if;
    select count(1) into exist from user_tables where table_name = 'FLOW_BOOK';
    if exist < 1 then
        execute immediate 'create table flow_book_1 (
                 workdate varchar2(256) not null ,
                 agentserialno varchar2(256) not null ,
                 dealcode varchar2(256),
                 dealmsg varchar2(256),
                 remark varchar2(256),
                 constraint pk_flow_book_1 primary key(workdate, agentserialno)
            )
        ';
    end if;
end;||