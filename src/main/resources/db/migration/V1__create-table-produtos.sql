create table produtos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    preco double not null,
    marca varchar(100) not null,
    fornecedor varchar(100) not null,
    email varchar(100) not null,
    pais varchar(100) not null,
    uf char(2) not null,

    primary key(id)

);