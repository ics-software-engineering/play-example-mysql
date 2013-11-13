# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table page_retrieval (
  primary_key               bigint auto_increment not null,
  timestamp                 bigint,
  constraint pk_page_retrieval primary key (primary_key))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table page_retrieval;

SET FOREIGN_KEY_CHECKS=1;

