create table ignore_data (
  id                            integer auto_increment not null,
  user_id                       uuid,
  target_id                     bigint not null,
  date                          timestamp not null,
  constraint uq_ignore_data_user_id_target_id unique (user_id,target_id),
  constraint pk_ignore_data primary key (id)
);

create table inventory_item_entity (
  id                            integer auto_increment not null,
  item_id                       integer not null,
  position                      integer not null,
  constraint uq_inventory_item_entity_item_id unique (item_id),
  constraint pk_inventory_item_entity primary key (id)
);

create table item_stack_entity (
  id                            integer auto_increment not null,
  material                      varchar(28) not null,
  material_data_id              integer,
  display_name                  varchar(255),
  constraint ck_item_stack_entity_material check ( material in ('AIR','STONE','GRASS','DIRT','COBBLESTONE','WOOD','SAPLING','BEDROCK','WATER','STATIONARY_WATER','LAVA','STATIONARY_LAVA','SAND','GRAVEL','GOLD_ORE','IRON_ORE','COAL_ORE','LOG','LEAVES','SPONGE','GLASS','LAPIS_ORE','LAPIS_BLOCK','DISPENSER','SANDSTONE','NOTE_BLOCK','BED_BLOCK','POWERED_RAIL','DETECTOR_RAIL','PISTON_STICKY_BASE','WEB','LONG_GRASS','DEAD_BUSH','PISTON_BASE','PISTON_EXTENSION','WOOL','PISTON_MOVING_PIECE','YELLOW_FLOWER','RED_ROSE','BROWN_MUSHROOM','RED_MUSHROOM','GOLD_BLOCK','IRON_BLOCK','DOUBLE_STEP','STEP','BRICK','TNT','BOOKSHELF','MOSSY_COBBLESTONE','OBSIDIAN','TORCH','FIRE','MOB_SPAWNER','WOOD_STAIRS','CHEST','REDSTONE_WIRE','DIAMOND_ORE','DIAMOND_BLOCK','WORKBENCH','CROPS','SOIL','FURNACE','BURNING_FURNACE','SIGN_POST','WOODEN_DOOR','LADDER','RAILS','COBBLESTONE_STAIRS','WALL_SIGN','LEVER','STONE_PLATE','IRON_DOOR_BLOCK','WOOD_PLATE','REDSTONE_ORE','GLOWING_REDSTONE_ORE','REDSTONE_TORCH_OFF','REDSTONE_TORCH_ON','STONE_BUTTON','SNOW','ICE','SNOW_BLOCK','CACTUS','CLAY','SUGAR_CANE_BLOCK','JUKEBOX','FENCE','PUMPKIN','NETHERRACK','SOUL_SAND','GLOWSTONE','PORTAL','JACK_O_LANTERN','CAKE_BLOCK','DIODE_BLOCK_OFF','DIODE_BLOCK_ON','STAINED_GLASS','TRAP_DOOR','MONSTER_EGGS','SMOOTH_BRICK','HUGE_MUSHROOM_1','HUGE_MUSHROOM_2','IRON_FENCE','THIN_GLASS','MELON_BLOCK','PUMPKIN_STEM','MELON_STEM','VINE','FENCE_GATE','BRICK_STAIRS','SMOOTH_STAIRS','MYCEL','WATER_LILY','NETHER_BRICK','NETHER_FENCE','NETHER_BRICK_STAIRS','NETHER_WARTS','ENCHANTMENT_TABLE','BREWING_STAND','CAULDRON','ENDER_PORTAL','ENDER_PORTAL_FRAME','ENDER_STONE','DRAGON_EGG','REDSTONE_LAMP_OFF','REDSTONE_LAMP_ON','WOOD_DOUBLE_STEP','WOOD_STEP','COCOA','SANDSTONE_STAIRS','EMERALD_ORE','ENDER_CHEST','TRIPWIRE_HOOK','TRIPWIRE','EMERALD_BLOCK','SPRUCE_WOOD_STAIRS','BIRCH_WOOD_STAIRS','JUNGLE_WOOD_STAIRS','COMMAND','BEACON','COBBLE_WALL','FLOWER_POT','CARROT','POTATO','WOOD_BUTTON','SKULL','ANVIL','TRAPPED_CHEST','GOLD_PLATE','IRON_PLATE','REDSTONE_COMPARATOR_OFF','REDSTONE_COMPARATOR_ON','DAYLIGHT_DETECTOR','REDSTONE_BLOCK','QUARTZ_ORE','HOPPER','QUARTZ_BLOCK','QUARTZ_STAIRS','ACTIVATOR_RAIL','DROPPER','STAINED_CLAY','STAINED_GLASS_PANE','LEAVES_2','LOG_2','ACACIA_STAIRS','DARK_OAK_STAIRS','SLIME_BLOCK','BARRIER','IRON_TRAPDOOR','PRISMARINE','SEA_LANTERN','HAY_BLOCK','CARPET','HARD_CLAY','COAL_BLOCK','PACKED_ICE','DOUBLE_PLANT','STANDING_BANNER','WALL_BANNER','DAYLIGHT_DETECTOR_INVERTED','RED_SANDSTONE','RED_SANDSTONE_STAIRS','DOUBLE_STONE_SLAB2','STONE_SLAB2','SPRUCE_FENCE_GATE','BIRCH_FENCE_GATE','JUNGLE_FENCE_GATE','DARK_OAK_FENCE_GATE','ACACIA_FENCE_GATE','SPRUCE_FENCE','BIRCH_FENCE','JUNGLE_FENCE','DARK_OAK_FENCE','ACACIA_FENCE','SPRUCE_DOOR','BIRCH_DOOR','JUNGLE_DOOR','ACACIA_DOOR','DARK_OAK_DOOR','END_ROD','CHORUS_PLANT','CHORUS_FLOWER','PURPUR_BLOCK','PURPUR_PILLAR','PURPUR_STAIRS','PURPUR_DOUBLE_SLAB','PURPUR_SLAB','END_BRICKS','BEETROOT_BLOCK','GRASS_PATH','END_GATEWAY','COMMAND_REPEATING','COMMAND_CHAIN','FROSTED_ICE','MAGMA','NETHER_WART_BLOCK','RED_NETHER_BRICK','BONE_BLOCK','STRUCTURE_VOID','OBSERVER','WHITE_SHULKER_BOX','ORANGE_SHULKER_BOX','MAGENTA_SHULKER_BOX','LIGHT_BLUE_SHULKER_BOX','YELLOW_SHULKER_BOX','LIME_SHULKER_BOX','PINK_SHULKER_BOX','GRAY_SHULKER_BOX','SILVER_SHULKER_BOX','CYAN_SHULKER_BOX','PURPLE_SHULKER_BOX','BLUE_SHULKER_BOX','BROWN_SHULKER_BOX','GREEN_SHULKER_BOX','RED_SHULKER_BOX','BLACK_SHULKER_BOX','WHITE_GLAZED_TERRACOTTA','ORANGE_GLAZED_TERRACOTTA','MAGENTA_GLAZED_TERRACOTTA','LIGHT_BLUE_GLAZED_TERRACOTTA','YELLOW_GLAZED_TERRACOTTA','LIME_GLAZED_TERRACOTTA','PINK_GLAZED_TERRACOTTA','GRAY_GLAZED_TERRACOTTA','SILVER_GLAZED_TERRACOTTA','CYAN_GLAZED_TERRACOTTA','PURPLE_GLAZED_TERRACOTTA','BLUE_GLAZED_TERRACOTTA','BROWN_GLAZED_TERRACOTTA','GREEN_GLAZED_TERRACOTTA','RED_GLAZED_TERRACOTTA','BLACK_GLAZED_TERRACOTTA','CONCRETE','CONCRETE_POWDER','STRUCTURE_BLOCK','IRON_SPADE','IRON_PICKAXE','IRON_AXE','FLINT_AND_STEEL','APPLE','BOW','ARROW','COAL','DIAMOND','IRON_INGOT','GOLD_INGOT','IRON_SWORD','WOOD_SWORD','WOOD_SPADE','WOOD_PICKAXE','WOOD_AXE','STONE_SWORD','STONE_SPADE','STONE_PICKAXE','STONE_AXE','DIAMOND_SWORD','DIAMOND_SPADE','DIAMOND_PICKAXE','DIAMOND_AXE','STICK','BOWL','MUSHROOM_SOUP','GOLD_SWORD','GOLD_SPADE','GOLD_PICKAXE','GOLD_AXE','STRING','FEATHER','SULPHUR','WOOD_HOE','STONE_HOE','IRON_HOE','DIAMOND_HOE','GOLD_HOE','SEEDS','WHEAT','BREAD','LEATHER_HELMET','LEATHER_CHESTPLATE','LEATHER_LEGGINGS','LEATHER_BOOTS','CHAINMAIL_HELMET','CHAINMAIL_CHESTPLATE','CHAINMAIL_LEGGINGS','CHAINMAIL_BOOTS','IRON_HELMET','IRON_CHESTPLATE','IRON_LEGGINGS','IRON_BOOTS','DIAMOND_HELMET','DIAMOND_CHESTPLATE','DIAMOND_LEGGINGS','DIAMOND_BOOTS','GOLD_HELMET','GOLD_CHESTPLATE','GOLD_LEGGINGS','GOLD_BOOTS','FLINT','PORK','GRILLED_PORK','PAINTING','GOLDEN_APPLE','SIGN','WOOD_DOOR','BUCKET','WATER_BUCKET','LAVA_BUCKET','MINECART','SADDLE','IRON_DOOR','REDSTONE','SNOW_BALL','BOAT','LEATHER','MILK_BUCKET','CLAY_BRICK','CLAY_BALL','SUGAR_CANE','PAPER','BOOK','SLIME_BALL','STORAGE_MINECART','POWERED_MINECART','EGG','COMPASS','FISHING_ROD','WATCH','GLOWSTONE_DUST','RAW_FISH','COOKED_FISH','INK_SACK','BONE','SUGAR','CAKE','BED','DIODE','COOKIE','MAP','SHEARS','MELON','PUMPKIN_SEEDS','MELON_SEEDS','RAW_BEEF','COOKED_BEEF','RAW_CHICKEN','COOKED_CHICKEN','ROTTEN_FLESH','ENDER_PEARL','BLAZE_ROD','GHAST_TEAR','GOLD_NUGGET','NETHER_STALK','POTION','GLASS_BOTTLE','SPIDER_EYE','FERMENTED_SPIDER_EYE','BLAZE_POWDER','MAGMA_CREAM','BREWING_STAND_ITEM','CAULDRON_ITEM','EYE_OF_ENDER','SPECKLED_MELON','MONSTER_EGG','EXP_BOTTLE','FIREBALL','BOOK_AND_QUILL','WRITTEN_BOOK','EMERALD','ITEM_FRAME','FLOWER_POT_ITEM','CARROT_ITEM','POTATO_ITEM','BAKED_POTATO','POISONOUS_POTATO','EMPTY_MAP','GOLDEN_CARROT','SKULL_ITEM','CARROT_STICK','NETHER_STAR','PUMPKIN_PIE','FIREWORK','FIREWORK_CHARGE','ENCHANTED_BOOK','REDSTONE_COMPARATOR','NETHER_BRICK_ITEM','QUARTZ','EXPLOSIVE_MINECART','HOPPER_MINECART','PRISMARINE_SHARD','PRISMARINE_CRYSTALS','RABBIT','COOKED_RABBIT','RABBIT_STEW','RABBIT_FOOT','RABBIT_HIDE','ARMOR_STAND','IRON_BARDING','GOLD_BARDING','DIAMOND_BARDING','LEASH','NAME_TAG','COMMAND_MINECART','MUTTON','COOKED_MUTTON','BANNER','END_CRYSTAL','SPRUCE_DOOR_ITEM','BIRCH_DOOR_ITEM','JUNGLE_DOOR_ITEM','ACACIA_DOOR_ITEM','DARK_OAK_DOOR_ITEM','CHORUS_FRUIT','CHORUS_FRUIT_POPPED','BEETROOT','BEETROOT_SEEDS','BEETROOT_SOUP','DRAGONS_BREATH','SPLASH_POTION','SPECTRAL_ARROW','TIPPED_ARROW','LINGERING_POTION','SHIELD','ELYTRA','BOAT_SPRUCE','BOAT_BIRCH','BOAT_JUNGLE','BOAT_ACACIA','BOAT_DARK_OAK','TOTEM','SHULKER_SHELL','IRON_NUGGET','KNOWLEDGE_BOOK','GOLD_RECORD','GREEN_RECORD','RECORD_3','RECORD_4','RECORD_5','RECORD_6','RECORD_7','RECORD_8','RECORD_9','RECORD_10','RECORD_11','RECORD_12')),
  constraint uq_item_stack_entity_material_data_id unique (material_data_id),
  constraint pk_item_stack_entity primary key (id)
);

create table item_stack_entity_lore (
  item_stack_entity_id          integer not null,
  value                         varchar(255) not null
);

create table material_data_entity (
  dtype                         varchar(31) not null,
  id                            integer auto_increment not null,
  dye_color                     varchar(10),
  constraint ck_material_data_entity_dye_color check ( dye_color in ('WHITE','ORANGE','MAGENTA','LIGHT_BLUE','YELLOW','LIME','PINK','GRAY','SILVER','CYAN','PURPLE','BLUE','BROWN','GREEN','RED','BLACK')),
  constraint pk_material_data_entity primary key (id)
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

alter table inventory_item_entity add constraint fk_inventory_item_entity_item_id foreign key (item_id) references item_stack_entity (id) on delete restrict on update restrict;

alter table item_stack_entity add constraint fk_item_stack_entity_material_data_id foreign key (material_data_id) references material_data_entity (id) on delete restrict on update restrict;

create index ix_item_stack_entity_lore_item_stack_entity_id on item_stack_entity_lore (item_stack_entity_id);
alter table item_stack_entity_lore add constraint fk_item_stack_entity_lore_item_stack_entity_id foreign key (item_stack_entity_id) references item_stack_entity (id) on delete restrict on update restrict;

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

