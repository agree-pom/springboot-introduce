create table user_info (
   user_id number(19,0) not null,
   login_cnt number(10,0) default 0,
   password varchar2(255 char) not null,
   user_code varchar2(255 char) not null unique ,
   user_name varchar2(255 char) not null unique ,
   user_status varchar2(255 char), primary key (user_id)
);

create table flow_book  (
    workdate varchar2(256) not null ,
    agentserialno varchar2(256) not null ,
    dealcode varchar2(256),
    dealmsg varchar2(256),
    remark varchar2(256),
    constraint pk_flow_book primary key(workdate, agentserialno)
);