create table book
(
  id           varchar(36)                          not null primary key,
  title        varchar(200)                         not null comment '标题',
  description  text                                 null comment '描述',
  author       varchar(100)                         not null comment '作者',
  asin         varchar(40)                          not null comment 'ASIN编号',
  price        decimal(6, 2)                        not null comment '价格',
  published_at date                                 not null comment '出版日期',
  created_at   datetime default current_timestamp() null comment '创建时间',
  updated_at   datetime default current_timestamp() null comment '更新时间'
) comment '图书';
