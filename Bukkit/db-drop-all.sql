alter table ignore_data drop constraint if exists fk_ignore_data_target_id;
drop index if exists ix_ignore_data_target_id;

alter table inventory_item_entity drop constraint if exists fk_inventory_item_entity_item_id;

alter table item_stack_entity drop constraint if exists fk_item_stack_entity_material_data_id;

alter table item_stack_entity_lore drop constraint if exists fk_item_stack_entity_lore_item_stack_entity_id;
drop index if exists ix_item_stack_entity_lore_item_stack_entity_id;

alter table player_preferences drop constraint if exists fk_player_preferences_profile_id;

alter table players drop constraint if exists fk_players_rank_id;
drop index if exists ix_players_rank_id;

alter table players drop constraint if exists fk_players_purchases_id;

alter table player_purchases_purchase drop constraint if exists fk_player_purchases_purchase_player_purchases;

alter table player_purchases_purchase drop constraint if exists fk_player_purchases_purchase_purchase;

alter table private_message_record drop constraint if exists fk_private_message_record_from_id;
drop index if exists ix_private_message_record_from_id;

alter table private_message_record drop constraint if exists fk_private_message_record_to_id;
drop index if exists ix_private_message_record_to_id;

alter table punishment_data_punishment_record drop constraint if exists fk_punishment_data_punishment_record_punishment_data;

alter table punishment_data_punishment_record drop constraint if exists fk_punishment_data_punishment_record_punishment_record;

alter table punishment_record drop constraint if exists fk_punishment_record_target_id;
drop index if exists ix_punishment_record_target_id;

alter table purchase drop constraint if exists fk_purchase_owner_id;
drop index if exists ix_purchase_owner_id;

alter table rank drop constraint if exists fk_rank_child_id;

alter table rank_permissions drop constraint if exists fk_rank_permissions_rank_id;
drop index if exists ix_rank_permissions_rank_id;

drop table if exists ignore_data;

drop table if exists inventory_item_entity;

drop table if exists item_stack_entity;

drop table if exists item_stack_entity_lore;

drop table if exists material_data_entity;

drop table if exists player_preferences;

drop table if exists players;

drop table if exists player_purchases;

drop table if exists player_purchases_purchase;

drop table if exists private_message_record;

drop table if exists punishment_data;

drop table if exists punishment_data_punishment_record;

drop table if exists punishment_record;

drop table if exists purchase;

drop table if exists rank;

drop table if exists rank_permissions;

