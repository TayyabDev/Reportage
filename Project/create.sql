#create schema cscc43s18_linjun9;
use cscc43s18_linjun9;

create table Account(
	accountId int auto_increment primary key,
    password varchar(20) not null,
    userName varchar(20) not null unique,
    accountType  enum('T','A') default null,
    registered bool default false);

create table User(
	userId Int auto_increment primary key,
    firstName varchar(50) not null,
    lastName varchar(50) not null,
    dateOfBirth date,
    userType enum('T','A'),
    accountId int default null,
    constraint userfk
		foreign key(accountId)
        references Account(accountId)
        on delete set null
        on update cascade);

create table TEQStaff(
	teqStaffId int primary key,
    constraint teqfk
		foreign key (teqStaffId)
        references User(userId)
        on delete cascade
        on update cascade);

create table Agency(
	agencyId int auto_increment primary key,
    name varchar(50) not null);

create table Officer(
	officerId int primary key,
    agencyId int,
    constraint officerFk1
		foreign key(OfficerId)
        references User(userId)
        on delete cascade
        on update cascade,
	constraint officerFk2
		foreign key(agencyId)
        references Agency(agencyId)
        on delete set null
        on update cascade);

create table Template(
	templateId int auto_increment primary key,
    updateTime timestamp default now(),
    teqStaffId int,
    constraint Templatefk1
		foreign key (teqStaffId)
        references TEQStaff(teqStaffId),
    templateName varchar(255),
    tableName varchar(255));

create table ClientDataForm(
	clientDataFormId int auto_increment primary key,
    templateId int,
    constraint CDFFk1
		foreign key(templateId)
        references Template(templateId),
    uploadTime timestamp default now(),
    reviewerId int default null,
    constraint CDFFk2
		foreign key(reviewerId)
        references TEQStaff(teqStaffId),
    agencyId int not null,
    constraint CDFFk3
		foreign key(agencyId)
        references Agency(agencyId),
    year year(4),
    month int(2) check (month > 0 and month < 13),
    numOfClients int not null default 0);

create table VariableName(
	variableNameId int auto_increment primary key,
	variableName varchar(255),
    realName varchar(255) not null,
    templateName varchar(255),
    unique(variableName, realName, templateName));

create table Report(
	id int auto_increment primary key,
	reportName varchar(255) not null unique,
	reportQuery text not null);

insert into Account (userName, password, accountType, registered) values ('root', 'root', 'T', true);
insert into User(firstName, lastName, dateOfBirth, userType, accountId) values ('root', 'root', '1900-01-01', 'T', 1);
insert into Agency(name) values('TEQ');