create table ASTERISK_INCOME_MESSAGE (
    ID bigint,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    --
    TYPE_ varchar(50),
    CHANNEL_ID varchar(255),
    INCOME_JSON text,
    --
    primary key (ID)
);