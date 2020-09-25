drop schema task_manager;
drop user 'user'@'localhost';

create schema task_manager;

use task_manager;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on task_manager.* to user@'localhost';

create table usu_usuario(
    usu_id bigint unsigned primary key auto_increment,
    usu_nome varchar(150) not null,
    usu_nome_usuario varchar(50) not null,
    usu_senha varchar(50) not null,
    constraint usu_nome_usuario_uk unique (usu_nome_usuario) 
);

create table ger_gerente (
  ger_id bigint unsigned primary key,
  ger_titulo varchar(10),
  constraint ger_usu_fk foreign key (ger_id) references usu_usuario(usu_id)
);

create table fun_funcionario (
  fun_id bigint unsigned primary key,
  constraint fun_usu_fk foreign key (fun_id) references usu_usuario(usu_id)
);

create table tar_tarefa (
  tar_id bigint unsigned primary key auto_increment,
  tar_titulo varchar(50) not null,
  tar_descricao varchar(500) not null,
  tar_data_hora_criacao datetime not null,
  tar_data_hora_entrega datetime null,
  tar_gerente_id bigint unsigned,
  constraint tar_ger_fk foreign key (tar_gerente_id)
    references ger_gerente (ger_id)
);

create table fut_funcionario_tarefa (
  fun_id bigint unsigned,
  tar_id bigint unsigned,
  primary key (fun_id, tar_id),
  constraint fut_fun_fk foreign key (fun_id)
    references fun_funcionario (fun_id),
  constraint fut_tar_fk foreign key (tar_id)
    references tar_tarefa (tar_id)
);