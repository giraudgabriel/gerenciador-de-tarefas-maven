-- drop schema task_manager;
-- drop user 'user'@'localhost';

create schema task_manager;

use task_manager;

create user 'user'@'localhost' identified by 'tnc';

grant select, insert, delete, update on task_manager.* to user@'localhost';

create table usuario(
    id bigint unsigned primary key auto_increment,
    nome varchar(150) not null,
    nome_usuario varchar(50) not null,
    senha varchar(50) not null,
    constraint usu_nome_usuario_uk unique (nome_usuario) 
);

create table gerente (
  id bigint unsigned primary key auto_increment,
  titulo varchar(10),
  constraint ger_usu_fk foreign key (id) references usuario(id)
);

create table desenvolvedor (
  id bigint unsigned primary key auto_increment,
  constraint dev_usu_fk foreign key (id) references usuario(id)
);

create table desenvolvedor_funcao (
  id bigint unsigned primary key auto_increment,
  dev_id bigint unsigned,
  funcao varchar(50) not null,
  constraint dev_dev_fk foreign key (dev_id) references desenvolvedor(id)
);

create table tarefa (
  id bigint unsigned primary key auto_increment,
  titulo varchar(50) not null,
  descricao varchar(500) not null,
  data_hora_criacao datetime not null,
  data_hora_entrega datetime null,
  gerente_id bigint unsigned,
  constraint tar_ger_fk foreign key (gerente_id)
    references gerente (id)
);

create table desenvolvedor_tarefa (
  dev_id bigint unsigned,
  tar_id bigint unsigned,
  primary key (dev_id, tar_id),
  constraint dev_fk foreign key (dev_id)
    references desenvolvedor (id),
  constraint dev_tar_fk foreign key (tar_id)
    references tarefa (id)
);