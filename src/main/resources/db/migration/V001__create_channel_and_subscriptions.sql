create table channels (
    id uuid not null,
    channel_id character varying(255) not null,
    name character varying(255) not null,
    url character varying(255) not null,
    constraint channel_pk primary key (id)
);

create table subscriptions (
    id uuid not null,
    channel_id uuid not null,
    guild_id character varying(255) not null,
    text_channel_id character varying(255) not null,
    message_template text not null,
    constraint subscription_pk primary key (id)
);

alter table subscriptions
    add constraint subscription_channel_fk
        foreign key (channel_id)
        references channels (id);