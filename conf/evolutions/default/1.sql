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


create table course_student (
  course_id                      bigint not null,
  student_id                     bigint not null,
  constraint pk_course_student primary key (course_id, student_id))
;

create table student_course (
  student_id                     bigint not null,
  course_id                      bigint not null,
  constraint pk_student_course primary key (student_id, course_id))
;
create sequence course_seq;

create sequence student_seq;




alter table course_student add constraint fk_course_student_course_01 foreign key (course_id) references course (id) on delete restrict on update restrict;

alter table course_student add constraint fk_course_student_student_02 foreign key (student_id) references student (id) on delete restrict on update restrict;

alter table student_course add constraint fk_student_course_student_01 foreign key (student_id) references student (id) on delete restrict on update restrict;

alter table student_course add constraint fk_student_course_course_02 foreign key (course_id) references course (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists course;

drop table if exists course_student;

drop table if exists student;

drop table if exists student_course;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists course_seq;

drop sequence if exists student_seq;

