create table ignore_data (
  id                            integer auto_increment not null,
  user_id                       uuid,
  target_id                     bigint not null,
  date                          timestamp not null,
  constraint uq_ignore_data_user_id_target_id unique (user_id,target_id),
  constraint pk_ignore_data primary key (id)
);

create table player_preferences (
  id                            integer auto_increment not null,
  profile_id                    bigint not null,
  private_messages              boolean default false not null,
  show_chat                     boolean default false not null,
  party_requests                boolean default false not null,
  constraint uq_player_preferences_profile_id unique (profile_id),
  constraint pk_player_preferences primary key (id)
);

create table players (
  id                            bigint auto_increment not null,
  rank_id                       integer,
  uuid                          uuid not null,
  name                          varchar(255) not null,
  coins                         bigint not null,
  last_login                    timestamp,
  play_time                     bigint not null,
  locale                        varchar(20),
  purchases_id                  uuid not null,
  registration_date             timestamp not null,
  version                       integer not null,
  constraint uq_players_uuid unique (uuid),
  constraint uq_players_name unique (name),
  constraint uq_players_purchases_id unique (purchases_id),
  constraint pk_players primary key (id)
);

create table player_purchases (
  id                            uuid not null,
  constraint pk_player_purchases primary key (id)
);

create table player_purchases_purchase (
  player_purchases_id           uuid not null,
  purchase_id                   integer not null,
  constraint uq_player_purchases_purchase_id unique (purchase_id),
  constraint pk_player_purchases_purchase primary key (player_purchases_id,purchase_id)
);

create table private_message_record (
  from_id                       bigint not null,
  to_id                         bigint not null,
  text                          varchar(250),
  date                          timestamp not null
);

create table punishment_data (
  id                            uuid not null,
  warns                         integer not null,
  constraint pk_punishment_data primary key (id)
);

create table punishment_data_punishment_record (
  punishment_data_id            uuid not null,
  punishment_record_id          bigint not null,
  constraint uq_punishment_data_punishment_record_id unique (punishment_record_id),
  constraint pk_punishment_data_punishment_record primary key (punishment_data_id,punishment_record_id)
);

create table punishment_record (
  dtype                         varchar(31) not null,
  id                            bigint auto_increment not null,
  date                          timestamp,
  punisher                      varchar(255),
  target_id                     bigint not null,
  reason                        varchar(255),
  punishment_type               varchar(4),
  expiration                    timestamp,
  active_type                   varchar(4),
  constraint ck_punishment_record_punishment_type check ( punishment_type in ('BAN','KICK','MUTE','WARN')),
  constraint ck_punishment_record_active_type check ( active_type in ('BAN','MUTE')),
  constraint pk_punishment_record primary key (id)
);

create table purchase (
  id                            integer auto_increment not null,
  owner_id                      bigint,
  purchase_id                   integer not null,
  price                         bigint not null,
  purchase_description          varchar(255) not null,
  date_time                     timestamp,
  constraint pk_purchase primary key (id)
);

create table rank (
  id                            integer auto_increment not null,
  operator                      boolean default false not null,
  name                          varchar(255) not null,
  prefix                        varchar(255) not null,
  child_id                      integer,
  constraint uq_rank_name unique (name),
  constraint uq_rank_prefix unique (prefix),
  constraint uq_rank_child_id unique (child_id),
  constraint pk_rank primary key (id)
);

create table rank_permissions (
  rank_id                       integer not null,
  value                         varchar(255) not null
);

create index ix_ignore_data_target_id on ignore_data (target_id);
alter table ignore_data add constraint fk_ignore_data_target_id foreign key (target_id) references players (id) on delete restrict on update restrict;

alter table player_preferences add constraint fk_player_preferences_profile_id foreign key (profile_id) references players (id) on delete restrict on update restrict;

create index ix_players_rank_id on players (rank_id);
alter table players add constraint fk_players_rank_id foreign key (rank_id) references rank (id) on delete restrict on update restrict;

alter table players add constraint fk_players_purchases_id foreign key (purchases_id) references player_purchases (id) on delete restrict on update restrict;

alter table player_purchases_purchase add constraint fk_player_purchases_purchase_player_purchases foreign key (player_purchases_id) references player_purchases (id) on delete restrict on update restrict;

alter table player_purchases_purchase add constraint fk_player_purchases_purchase_purchase foreign key (purchase_id) references purchase (id) on delete restrict on update restrict;

create index ix_private_message_record_from_id on private_message_record (from_id);
alter table private_message_record add constraint fk_private_message_record_from_id foreign key (from_id) references players (id) on delete restrict on update restrict;

create index ix_private_message_record_to_id on private_message_record (to_id);
alter table private_message_record add constraint fk_private_message_record_to_id foreign key (to_id) references players (id) on delete restrict on update restrict;

alter table punishment_data_punishment_record add constraint fk_punishment_data_punishment_record_punishment_data foreign key (punishment_data_id) references punishment_data (id) on delete restrict on update restrict;

alter table punishment_data_punishment_record add constraint fk_punishment_data_punishment_record_punishment_record foreign key (punishment_record_id) references punishment_record (id) on delete restrict on update restrict;

create index ix_punishment_record_target_id on punishment_record (target_id);
alter table punishment_record add constraint fk_punishment_record_target_id foreign key (target_id) references players (id) on delete restrict on update restrict;

create index ix_purchase_owner_id on purchase (owner_id);
alter table purchase add constraint fk_purchase_owner_id foreign key (owner_id) references players (id) on delete restrict on update restrict;

alter table rank add constraint fk_rank_child_id foreign key (child_id) references rank (id) on delete restrict on update restrict;

create index ix_rank_permissions_rank_id on rank_permissions (rank_id);
alter table rank_permissions add constraint fk_rank_permissions_rank_id foreign key (rank_id) references rank (id) on delete restrict on update restrict;

