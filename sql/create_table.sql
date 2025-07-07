-- auto-generated definition
create table tag
(
    id         bigint auto_increment comment 'id'
        primary key,
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete   tinyint  default 0                 null,
    userId     bigint                             null comment '创建用户id',
    tagName    varchar(256)                       null comment '标签名',
    isParent   tinyint  default 0                 null comment '是否父标签',
    tagLayer   int      default 0                 null comment '标签层级',
    constraint tag_tagName_uindex
        unique (tagName),
    constraint tag_userId_uindex
        unique (userId)
);
-- auto-generated definition
create table user
(
    id           bigint auto_increment
        primary key,
    userAccount  varchar(256)                       null comment '账号',
    username     varchar(256)                       null comment '用户昵称',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(256)                       null comment '电话号码',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '用户状态0正常',
    createTime   datetime default CURRENT_TIMESTAMP null invisible comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null comment '数据更新时间',
    isDelete     tinyint  default 0                 null comment '是否删除',
    userRole     int      default 0                 not null comment '0用户，1管理员',
    planetCode   varchar(512)                       null comment '星球编号',
    tags         varchar(1024)                      null comment '标签列表'
);
create table team
(
    id     bigint auto_increment comment 'id' primary key,
    name   varchar(256) not null comment '队伍名称',
    description varchar(1024) null comment '描述',
    maxNum int default 1 not null comment '最大人数',
    expireTime datetime null comment '过期时间',
    userld bigint comment '用户id (队长 id) ',
    status int default 0 not null comment '0 -公开,1 -私有, 2- 加密',
    password varchar(512) null comment '密码',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete tinyint default 0 not null comment '是否删除'
)comment '队伍';
create table user_team
(
    id bigint auto_increment comment 'id' primary key,
    userld bigint comment '用户id',
    teamld bigint comment '队伍id',
    joinTime datetime null comment '加入时间',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete tinyint default 0 not null comment '是否删除'
)comment'用户队伍关系';

