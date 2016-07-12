# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table course (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_course primary key (id))
;

create table student (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_student primary key (id))
;


create table Student_Enroll (
  cid                            bigint not null,
  sid                            bigint not null,
  constraint pk_Student_Enroll primary key (cid, sid))
;
create sequence course_seq;

create sequence student_seq;




alter table Student_Enroll add constraint fk_Student_Enroll_course_01 foreign key (cid) references course (id) on delete restrict on update restrict;

alter table Student_Enroll add constraint fk_Student_Enroll_student_02 foreign key (sid) references student (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists course;

drop table if exists Student_Enroll;

drop table if exists student;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists course_seq;

drop sequence if exists student_seq;

