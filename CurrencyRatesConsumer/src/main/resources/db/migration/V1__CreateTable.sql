drop table if exists currency_rates cascade;

create table currency_rates (
    currency_rates_id int8 not null generated always as identity unique,
    buy float4 not null,
    create_time timestamp not null,
    currency_type int4 not null,
    provider_type int4 not null,
    sale float4 not null,
    primary key (currency_rates_id)
);