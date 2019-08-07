create table client (
  id                            integer auto_increment not null,
  constraint pk_client primary key (id)
);

create table client_parent (
  client_id                     integer not null,
  parent_id                     integer not null,
  constraint uq_client_parent_id unique (parent_id),
  constraint pk_client_parent primary key (client_id,parent_id)
);

create table parent (
  dtype                         varchar(31) not null,
  id                            integer auto_increment not null,
  constraint pk_parent primary key (id)
);

alter table client_parent add constraint fk_client_parent_client foreign key (client_id) references client (id) on delete restrict on update restrict;

alter table client_parent add constraint fk_client_parent_parent foreign key (parent_id) references parent (id) on delete restrict on update restrict;

