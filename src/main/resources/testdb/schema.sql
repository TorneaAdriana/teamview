 create table T_TEAM (ID bigint , NAME varchar(50));

commit;

create table T_MEMBER (ID bigint,FIRSTNAME varchar(50), LASTNAME varchar(50), EMAIL varchar(40),primary key (id));

commit ;

create table t_member_team_list(team_member_id bigint not null, team_list_id bigint not null);
