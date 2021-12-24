CREATE TABLE `z_reimbursement` (
`id`  varchar(64) NOT NULL ,
`pid`  varchar(64) NULL ,
`userId`  varchar(64) NULL ,
`fee`  decimal(10,2) NULL ,
`note`  varchar(255) NULL ,
`feedate`  date NULL ,
`type`  varchar(255) NULL ,
`bmyj`  varchar(255) NULL ,
`refee`  decimal(10,2)  NULL ,
`bzhu`  varchar(255) NULL ,
`createdatetime`  datetime NULL ,
PRIMARY KEY (`id`)
)
;

CREATE TABLE `zz_user` (
`user_id`  varchar(64) NOT NULL ,
`department_id`  varchar(64),
`role_id`  varchar(64) ,
`username`  varchar(64) NULL ,
`psd`  varchar(64) NULL ,
PRIMARY KEY (`user_id`)
)
;

CREATE TABLE `zz_department` (
`department_id`  varchar(64) NOT NULL ,
`departmentname`  varchar(64) NULL ,
PRIMARY KEY (`department_id`)
)
;

CREATE TABLE `zz_role` (
`role_id`  varchar(64) NOT NULL ,
`rolename`  varchar(64) NULL ,
PRIMARY KEY (`role_id`)
)
;

insert into `zz_department` (department_id,departmentname) values ('001','经理部');
insert into `zz_department` (department_id,departmentname) values ('002','财务办');
insert into `zz_department` (department_id,departmentname) values ('003','车管办');
insert into `zz_department` (department_id,departmentname) values ('004','开发办');
insert into `zz_department` (department_id,departmentname) values ('005','业务办');

insert into `zz_role` (role_id,rolename) values ('001','部门领导');
insert into `zz_role` (role_id,rolename) values ('002','职员');

insert into `zz_user` (user_id,department_id,role_id,username,psd) values ('tigger','001','001','tigger','a');
insert into `zz_user` (user_id,department_id,role_id,username,psd) values ('kitty','001','002','kitty','a');
insert into `zz_user` (user_id,department_id,role_id,username,psd) values ('bear','002','001','bear','a');
insert into `zz_user` (user_id,department_id,role_id,username,psd) values ('lucy','002','002','lucy','a');
insert into `zz_user` (user_id,department_id,role_id,username,psd) values ('monkey','003','001','monkey','a');
insert into `zz_user` (user_id,department_id,role_id,username,psd) values ('apple','003','002','apple','a');
insert into `zz_user` (user_id,department_id,role_id,username,psd) values ('giraffe','004','001','giraffe','a');
insert into `zz_user` (user_id,department_id,role_id,username,psd) values ('dave','004','002','dave','a');
insert into `zz_user` (user_id,department_id,role_id,username,psd) values ('rat','005','001','rat','a');
insert into `zz_user` (user_id,department_id,role_id,username,psd) values ('tom','005','002','tom','a');


CREATE TABLE `z_paiche` (
	`id` VARCHAR (64) NOT NULL,
	`pid` VARCHAR (64),
	`user_id` VARCHAR (64),
	`startdatetime` datetime,
	`persons` INT,
	`phone` VARCHAR (25) NULL,
	`startposition` VARCHAR (255) NULL,
	`endposition` VARCHAR (255) NULL,
	`driver` VARCHAR (25) NULL,
	`car` VARCHAR (25) NULL,
	`bzhu` VARCHAR (255) NULL,
	`createdatetime` datetime NULL,
	PRIMARY KEY (`id`)
);


CREATE TABLE `z_log` (
	`id` VARCHAR (64) NOT NULL,
	`task` VARCHAR (128),
	`task_id` VARCHAR (64),
	`user_id` VARCHAR (64),
	`isagreed` VARCHAR (10) NULL,
	`log` VARCHAR (500) NULL,
	`createdatetime` datetime NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `zz_code` (
	`id` VARCHAR (10) NOT NULL,
	`codename` VARCHAR (128),
	PRIMARY KEY (`id`)
);

INSERT INTO `zz_code` (`id`, `codename`) VALUES ('0', '不同意');
INSERT INTO `zz_code` (`id`, `codename`) VALUES ('1', '同意');
INSERT INTO `zz_code` (`id`, `codename`) VALUES ('2', '重新申请');
INSERT INTO `zz_code` (`id`, `codename`) VALUES ('3', '取消申请');
INSERT INTO `zz_code` (`id`, `codename`) VALUES ('4', '正常派车');
INSERT INTO `zz_code` (`id`, `codename`) VALUES ('5', '确认操作');
INSERT INTO `zz_code` (`id`, `codename`) VALUES ('6', '休假');
INSERT INTO `zz_code` (`id`, `codename`) VALUES ('7', '出差');

CREATE TABLE `z_beaway` (
`id` VARCHAR (64) NOT NULL,
`pid` VARCHAR (64),
`paiche_id` VARCHAR (64),
`user_id` VARCHAR (64),
`sort` VARCHAR (10) ,
`startdatetime` datetime,
`enddatetime` datetime,
`phone` VARCHAR (25),
`onposition` VARCHAR (255) ,
`borrowmoney` DECIMAL(10,2),
`bzhu` VARCHAR (255) NULL,
`createdatetime` datetime ,
PRIMARY KEY (`id`)
);
