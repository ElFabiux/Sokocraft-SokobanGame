prompt PL/SQL Developer Export Tables for user FARGUEDAS@XE
prompt Created by fabia on Monday, June 26, 2023
set feedback off
set define off

prompt Dropping TBL_PLAYERS...
drop table TBL_PLAYERS cascade constraints;
prompt Creating TBL_PLAYERS...
create table TBL_PLAYERS
(
  pla_id    NUMBER(5) not null,
  pla_name  VARCHAR2(15),
  pla_level VARCHAR2(1),
  pla_game  VARCHAR2(200),
  pla_steps VARCHAR2(5)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_PLAYERS
  add constraint PK_PLAYERS primary key (PLA_ID)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for TBL_PLAYERS...
alter table TBL_PLAYERS disable all triggers;
prompt Loading TBL_PLAYERS...
insert into TBL_PLAYERS (pla_id, pla_name, pla_level, pla_game, pla_steps)
values (0, 'Fabiux', '4', ',,,,,,,,,,,,,,,,,,,,,,,...#..#.#....,..,,,....#.o........+.,,...#+#@...#..####.,,....#....#......#.,,...#..#.#.......#.,,...#......#.##.#..,,.#.....0##..#...o.,,#....#.......#..,,,,,,,,,,,,,,,,,,,,,,,', '86');
insert into TBL_PLAYERS (pla_id, pla_name, pla_level, pla_game, pla_steps)
values (2, 'Mauri', '1', ',,,,,,,,#1##,,,,,,,,,,,,,,,,#..#,,,,,22,,,#####,#..#,,,,,22,,,#6..###..####,,,,,,,#.o.#+#....0#,,,,,,,#...#.#5.#.####,,,,,#.....##.#.#70#,,,,,#4......@#.#..#,3,,,##########....#,,,,,,,,,,,,,,######,,,', '47');
insert into TBL_PLAYERS (pla_id, pla_name, pla_level, pla_game, pla_steps)
values (1, 'AkameNat', '2', '######,,####,,,,,,,,#....#,,#.##,,,,,,,,#.##.####.######,,,,#+...o.........#,,,,#.#.#.######.#.#,,,,#.#.#.....#..#.#,,,,#.#.#####.#.##.#,,,,#......0#.o....#,,,,#0#######+@.####,,,,###,,,,,#####,,,,,,,', '118');
commit;
prompt 3 records loaded
prompt Enabling triggers for TBL_PLAYERS...
alter table TBL_PLAYERS enable all triggers;

set feedback on
set define on
prompt Done
