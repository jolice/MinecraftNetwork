alter table client_parent drop constraint if exists fk_client_parent_client;

alter table client_parent drop constraint if exists fk_client_parent_parent;

drop table if exists client;

drop table if exists client_parent;

drop table if exists parent;

