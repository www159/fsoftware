use nt_oa;

create table if not exists `user` (
    `id` bigint(0) not null auto_increment primary key comment '用户ID',
    `dept` varchar(255) character set utf8mb4 not null comment '部门',
    `company` varchar(255) character set utf8mb4 not null comment '公司',
    `account` varchar(255) character set utf8mb4 not null comment '账户信息',
    `name` varchar(255) character set utf8mb4 not null comment '用户名',
    `password` varchar(255) not null comment '密码',
    `head_img` varchar(255) default null comment '图像url',
    `gender` boolean not null comment '性别',
    `state` boolean not null comment '用户状态',
    `mobile` varchar(20) not null comment '手机号码',
    `email` varchar(20) not null comment '邮箱',
    `birthday` datetime(0) not null comment '生日',
    `memo` varchar(255) comment '备注'
);

create table if not exists `complaint` (
    `complaint_id` bigint(0) not null auto_increment primary key comment '投诉ID',
    `complaint_company` varchar(255) not null comment '投诉公司名称',
    `complaint_name` varchar(255) not null comment '投诉用户名字',
    `mobile` varchar(20) not null comment '投诉人手机号码',
    `anonymous` boolean not null comment '开启匿名',
    `time` datetime not null comment '投诉日期',
    `title` varchar(255) not null comment '投诉标题',
    `to_complaint_name` varchar(255) not null comment '被投诉用户名字',
    `to_complaint_dept` varchar(255) not null comment '被投诉人部门名称',
    `content` varchar(1023) not null comment '内容',
    `state` int default 0
);

create table if not exists `complaint_reply` (
    `complaint_reply_id` bigint(0) not null auto_increment primary key comment '回复ID',
    `complaint_id` bigint not null comment '投诉ID',
    `replyer_id` bigint not null comment '回复人ID',
    `time` datetime not null comment '回复时间',
    `content` varchar(1023) not null comment '内容'
);

create table if not exists `info` (
    `info_id` bigint(0) not null auto_increment primary key comment '公告ID',
    `type` int default 0,
    `source` varchar(1023) comment '链接',
    `title` varchar(255) not null comment '标题',
    `content` varchar(1023) not null comment '内容',
    `memo` varchar(255) not null comment '备注',
    `creator_id` varchar(255) not null comment '创建人名字',
    `time` datetime not null comment '创建时间',
    `state` int default 1
);

create table if not exists `consultation` (
    `consultation_id` bigint(0) not null auto_increment primary key comment '咨询ID',
    `consult_id` bigint not null comment '咨询发起者ID',
    `time` datetime not null comment '咨询时间',
    `title` varchar(255) not null comment '咨询标题',
    `type` int not null comment '咨询类型',
    `name` varchar(255) not null comment '咨询人姓名',
    `mobile` varchar(20) not null comment '咨询人电话号码',
    `company` varchar(255) not null comment '咨询人单位',
    `state` int default 0
);

create table if not exists `consultation_reply` (
    `consultation_reply_id` bigint(0) not null auto_increment primary key comment '咨询回复ID',
    `consultation_id` bigint not null comment '咨询ID',
    `replayer_id` bigint not null comment '回复者ID',
    `content` varchar(1023) not null comment '内容'
);

create table if not exists `reservation_item` (
    `reservation_item_id` bigint(0) not null auto_increment primary key comment '预约事项ID',
    `creator_id` bigint not null comment '创建人ID',
    `name` varchar(255) not null comment '事项名称',
    `department` varchar(255) not null comment '部门'
);

create table if not exists `reservation` (
    `reservation_id` bigint(0) not null auto_increment primary key comment '预约ID',
    `reservation_item_id` bigint not null comment '预约事项ID',
    `reservation_time` date not null comment '预约时间',
    `submitter_id` bigint not null comment '提交人ID',
    `state` int default 0 comment '状态',
    `memo` varchar(255) comment '备注',
    `reserve_name` varchar(255) not null comment '预约人姓名',
    `reserve_company` varchar(255) not null comment '预约人单位',
    `reserve_phone_number` varchar(255) not null comment '预约人电话'
);

create table if not exists `reservation_handler` (
    `handler_id` bigint(0) not null auto_increment primary key comment '投诉受理人ID',
    `content` varchar(1023) not null comment '回复信息',
    `reservation_result` int default 0 comment '预约结果',
    `reservation_id` bigint not null comment '预约ID'
);

create table if not exists nt_oa.role (
    role_id bigint auto_increment primary key,
    role_name varchar(255) not null,
    state int not null,
    constraint table_name_role_id_uindex unique (role_id)
);

create table if not exists nt_oa.privilege (
    privilege_id int not null primary key,
    name varchar(255) not null,
    constraint privilege_privilege_id_uindex unique (privilege_id)
);

create table if not exists nt_oa.privilege (
    privilege_id int not null primary key,
    name varchar(255) not null,
    constraint privilege_privilege_id_uindex unique (privilege_id)
);

create table if not exists nt_oa.user_role (
    user_id bigint not null,
    role_id bigint not null,
    id bigint not null primary key,
    constraint user_role_id_uindex unique (id)
);

create table if not exists nt_oa.user_phone_number_list (
    id int auto_increment primary key,
    user_id int not null,
    excel_file varchar(255) not null,
    created_time datetime null
);

create table if not exists nt_oa.easy_notify (
    id bigint auto_increment primary key,
    title varchar(255) not null,
    type varchar(20) not null,
    status varchar(20) not null,
    creator varchar(50) not null,
    created_time datetime not null
);

create table if not exists nt_oa.easy_notify_phone_numbers (
    id bigint auto_increment primary key,
    notify_id bigint not null,
    phone_number varchar(255) not null,
    constraint easy_notify_phone_numbers_id_uindex unique (id),
    constraint easy_notify_phone_numbers_easy_notify_id_fk foreign key (notify_id) references nt_oa.easy_notify (id)
);

create table if not exists nt_oa.easy_notify_record (
    id int auto_increment primary key,
    title varchar(255) not null,
    type varchar(20) not null,
    status varchar(20) not null,
    creator varchar(50) not null,
    created_time datetime null
);

create table if not exists nt_oa.easy_notify_task (
    id int auto_increment primary key,
    record_id int not null,
    phone_number_list varchar(1000) not null,
    sent_records varchar(1000) null,
    constraint easy_notify_task_ibfk_1 foreign key (record_id) references nt_oa.easy_notify (id)
);

create index record_id on nt_oa.easy_notify_task (record_id);

create table if not exists nt_oa.sent_record (
    id int auto_increment primary key,
    task_id int not null,
    phone_number varchar(20) not null,
    sms_content varchar(500) null,
    email_content varchar(500) null,
    status varchar(20) not null,
    constraint sent_record_ibfk_1 foreign key (task_id) references nt_oa.easy_notify_task (id)
);

create table if not exists nt_oa.notify_mail
(
    id        bigint auto_increment
        primary key,
    notify_id bigint       not null,
    mail      varchar(255) not null
);



create index task_id on nt_oa.sent_record (task_id);