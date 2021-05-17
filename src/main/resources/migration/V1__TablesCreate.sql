create table if not exists address
(
    Address_ID                                           int         not null
        primary key,
    Zip_code                                             int         not null,
    City                                                 varchar(80) not null,
    Region                                               varchar(80) not null,
    Street                                               varchar(80) not null,
    Building_number                                      int         not null,
    installing_possibilities_installing_possibilities_id int         null,
    installing_possibilities_installing_possibilitiy_id  int         null,
    installing_possibilities_installing_possibility_id   int         null,
    constraint Unique_address
        unique (Zip_code, City, Region, Street, Building_number)
);

create table if not exists balances
(
    Caller_ID                int                         not null,
    Long_distance_calls_debt decimal(15, 2) default 0.00 not null,
    Subscription_debt        decimal(15, 2) default 0.00 not null,
    Penalty_interest         decimal(15, 2) default 0.00 not null,
    LONG_DIST_DEBT_DATE      date                        null,
    SUBSCRIPTION_DEBT_DATE   date                        null,
    balance_id               int                         not null
        primary key,
    constraint Caller_ID
        unique (Caller_ID)
);

create table if not exists clients
(
    Client_ID   int           not null
        primary key,
    Second_name varchar(100)  not null,
    First_name  varchar(70)   not null,
    Middle_name varchar(80)   not null,
    Gender      char          not null,
    Birth_date  date          not null,
    Is_deadhead int default 0 not null
);

create table if not exists connection_prices
(
    Connection_price_ID int            not null
        primary key,
    Price_name          varchar(100)   not null,
    Price               decimal(15, 2) not null,
    constraint Price_name
        unique (Price_name)
);

create table if not exists hibernate_sequence
(
    next_val bigint null
);

create table if not exists installing_possibilities
(
    INSTALLING_POSSIBILITY_ID int not null
        primary key,
    ADDRESS_ID                int not null,
    TELEPHONE_EXCHANGE_ID     int not null,
    constraint Unique_INSTALLING_POSSIBILITY
        unique (ADDRESS_ID, TELEPHONE_EXCHANGE_ID),
    constraint FK72ramfxyft4xdfgrk064lr530
        foreign key (ADDRESS_ID) references address (Address_ID)
            on update cascade on delete cascade,
    constraint installing_possibilities_ibfk_1
        foreign key (ADDRESS_ID) references address (Address_ID)
            on update cascade on delete cascade
);

alter table address
    add constraint FKfxob2e0ynyadrbtxyg83ttw32
        foreign key (installing_possibilities_installing_possibility_id) references installing_possibilities (INSTALLING_POSSIBILITY_ID)
            on update cascade on delete cascade;

create table if not exists long_distance_call_prices
(
    Long_distance_call_price_ID int            not null
        primary key,
    Price_name                  varchar(100)   not null,
    Price_per_minute            decimal(15, 2) not null,
    constraint Price_name
        unique (Price_name)
);

create table if not exists long_distance_calls
(
    Long_distance_call_ID int            not null
        primary key,
    Source_phone_ID       int            not null,
    Destination_phone_ID  int            not null,
    Start_date            datetime       not null,
    End_date              datetime       not null,
    Call_price            decimal(15, 2) null,
    constraint Unique_long_distance_calls
        unique (Destination_phone_ID, Source_phone_ID, Start_date)
);

create table if not exists phone_types
(
    Phone_type_ID   int         not null
        primary key,
    Type_name       varchar(40) not null,
    phones_phone_id int         null,
    constraint Type_name
        unique (Type_name)
);

create table if not exists subscription_fees
(
    Subscription_fee_ID     int            not null
        primary key,
    Phone_number_type_ID    int            not null,
    Is_deadhead             int default 0  not null,
    Has_long_distance_calls int default 0  not null,
    Subscription_fee        decimal(15, 2) not null,
    constraint Unique_subscription_fee
        unique (Phone_number_type_ID, Is_deadhead, Has_long_distance_calls),
    constraint FKs1oy1kd3x010e6cj2v3lkr27y
        foreign key (Phone_number_type_ID) references phone_types (Phone_type_ID),
    constraint subscription_fees_ibfk_1
        foreign key (Phone_number_type_ID) references phone_types (Phone_type_ID)
);

create table if not exists telephone_exchanges
(
    Telephone_exchange_ID                                int          not null
        primary key,
    exchange_name                                        varchar(100) not null,
    exchange_type                                        varchar(50)  null,
    installing_possibilities_installing_possibilities_id int          null,
    installing_possibilities_installing_possibilitiy_id  int          null,
    installing_possibilities_installing_possibility_id   int          null,
    constraint FK8u4odl8bxyk28fetxtxoad0pe
        foreign key (installing_possibilities_installing_possibility_id) references installing_possibilities (INSTALLING_POSSIBILITY_ID)
);

create table if not exists callers
(
    Caller_ID               int           not null
        primary key,
    Telephone_exchange_ID   int           not null,
    Client_ID               int           not null,
    Is_blocked              int default 0 not null,
    Has_long_distance_calls int default 0 not null,
    balances_balance_id     int           null,
    constraint Unique_caller
        unique (Telephone_exchange_ID, Client_ID),
    constraint FKpyonhq7plg6su1v1bnlmxyw8f
        foreign key (balances_balance_id) references balances (balance_id),
    constraint callers_ibfk_1
        foreign key (Telephone_exchange_ID) references telephone_exchanges (Telephone_exchange_ID),
    constraint callers_ibfk_2
        foreign key (Client_ID) references clients (Client_ID)
);

alter table balances
    add constraint FK5e72ofj7wd5w3ch7th3uvsb0d
        foreign key (Caller_ID) references callers (Caller_ID);

alter table balances
    add constraint balances_ibfk_1
        foreign key (Caller_ID) references callers (Caller_ID);

create index Client_ID
    on callers (Client_ID);

create table if not exists connection_requests
(
    Connection_request_ID int  not null
        primary key,
    Telephone_exchange_ID int  not null,
    Address_ID            int  not null,
    Apartment_number      int  not null,
    Client_ID             int  not null,
    Request_date          date not null,
    constraint Unique_long_distance_call
        unique (Telephone_exchange_ID, Address_ID, Client_ID),
    constraint FK43w5j14fh8ryb9ks6vdp67hul
        foreign key (Telephone_exchange_ID) references telephone_exchanges (Telephone_exchange_ID),
    constraint connection_requests_ibfk_1
        foreign key (Telephone_exchange_ID) references telephone_exchanges (Telephone_exchange_ID),
    constraint connection_requests_ibfk_2
        foreign key (Address_ID) references address (Address_ID),
    constraint FKqc97guuyq7m9xqsfl06dghkp2
        foreign key (Address_ID) references address (Address_ID),
    constraint connection_requests_ibfk_3
        foreign key (Client_ID) references clients (Client_ID),
    constraint FK5pbf28esiy8k79kr8w8yidvq4
        foreign key (Client_ID) references clients (Client_ID)
);

alter table installing_possibilities
    add constraint installing_possibilities_ibfk_2
        foreign key (TELEPHONE_EXCHANGE_ID) references telephone_exchanges (Telephone_exchange_ID);

alter table installing_possibilities
    add constraint FKdd8kilsohlcbnc50918g8du50
        foreign key (TELEPHONE_EXCHANGE_ID) references telephone_exchanges (Telephone_exchange_ID);

create table if not exists phone_numbers
(
    Phone_number_ID       int         not null
        primary key,
    Phone_number          varchar(40) not null,
    TELEPHONE_EXCHANGE_ID int         not null,
    phones_phone_id       int         null,
    constraint Phone_number
        unique (Phone_number),
    constraint phone_numbers_ibfk_1
        foreign key (TELEPHONE_EXCHANGE_ID) references telephone_exchanges (Telephone_exchange_ID),
    constraint FKlrqdm2gu25s9atf9wtw69uqeb
        foreign key (TELEPHONE_EXCHANGE_ID) references telephone_exchanges (Telephone_exchange_ID)
);

create table if not exists phones
(
    Phone_ID                                  int not null
        primary key,
    Phone_number_ID                           int not null,
    Phone_type_ID                             int not null,
    Caller_ID                                 int not null,
    Address_ID                                int not null,
    Apartment_number                          int not null,
    long_distance_calls_long_distance_call_id int null,
    distance_calls_long_distance_call_id      int null,
    constraint FK3d9bpinrijqvj13hp7i31w5t2
        foreign key (long_distance_calls_long_distance_call_id) references long_distance_calls (Long_distance_call_ID),
    constraint FKe0g9i8a2hhtubgtj1vmegsoyx
        foreign key (distance_calls_long_distance_call_id) references long_distance_calls (Long_distance_call_ID),
    constraint phones_ibfk_1
        foreign key (Phone_number_ID) references phone_numbers (Phone_number_ID),
    constraint FKrlayhgrlebhi1c49vospqy1wt
        foreign key (Phone_number_ID) references phone_numbers (Phone_number_ID),
    constraint phones_ibfk_2
        foreign key (Phone_type_ID) references phone_types (Phone_type_ID),
    constraint FKe6r3ngrx8weqk99fqq6mtwx1f
        foreign key (Phone_type_ID) references phone_types (Phone_type_ID),
    constraint phones_ibfk_3
        foreign key (Caller_ID) references callers (Caller_ID),
    constraint FKf12thsllau36ix3en0g7a1hjq
        foreign key (Caller_ID) references callers (Caller_ID),
    constraint phones_ibfk_4
        foreign key (Address_ID) references address (Address_ID),
    constraint FKfv7f3dt64vhvbefpbjg7osce2
        foreign key (Address_ID) references address (Address_ID)
);

alter table long_distance_calls
    add constraint FK1ysy6qyeadthstgc264i21ukb
        foreign key (Destination_phone_ID) references phones (Phone_ID);

alter table long_distance_calls
    add constraint long_distance_calls_ibfk_1
        foreign key (Source_phone_ID) references phones (Phone_ID);

alter table long_distance_calls
    add constraint FKjj2ox3y66f3swiqwittiy4rd1
        foreign key (Source_phone_ID) references phones (Phone_ID);

alter table long_distance_calls
    add constraint long_distance_calls_ibfk_2
        foreign key (Destination_phone_ID) references phones (Phone_ID);

alter table phone_numbers
    add constraint FKpc1v4umfmgrb54rrk6fodyhyb
        foreign key (phones_phone_id) references phones (Phone_ID);

alter table phone_types
    add constraint FKgww1o3fucdl4ll6kw6sncvs9a
        foreign key (phones_phone_id) references phones (Phone_ID);

create table if not exists public_phones
(
    Public_phone_ID       int not null
        primary key,
    Address_ID            int not null,
    Telephone_exchange_ID int not null,
    constraint Unique_public_phone
        unique (Address_ID, Telephone_exchange_ID),
    constraint FKbui0xmqbellibyhhu4t0xps0o
        foreign key (Address_ID) references address (Address_ID),
    constraint public_phones_ibfk_1
        foreign key (Address_ID) references address (Address_ID),
    constraint public_phones_ibfk_2
        foreign key (Telephone_exchange_ID) references telephone_exchanges (Telephone_exchange_ID),
    constraint FK6xbjy0ywuaugvii602dyfedck
        foreign key (Telephone_exchange_ID) references telephone_exchanges (Telephone_exchange_ID)
);

