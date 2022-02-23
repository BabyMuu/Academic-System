/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2005                    */
/* Created on:     2021-08-16 22:54:14                          */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('classInfo') and o.name = 'FK_CLASSINF_REFERENCE_INSTRUCT')
alter table classInfo
   drop constraint FK_CLASSINF_REFERENCE_INSTRUCT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('classInfo')
            and   type = 'U')
   drop table classInfo
go

if exists (select 1
            from  sysobjects
           where  id = object_id('instructor')
            and   type = 'U')
   drop table instructor
go

/*==============================================================*/
/* Table: classInfo                                             */
/*==============================================================*/
create table classInfo (
   clsId                int                  identity,
   insId                int                  null,
   clsName              char(10)             null,
   grade                char(4)              null,
   constraint PK_CLASSINFO primary key (clsId)
)
go

/*==============================================================*/
/* Table: instructor                                            */
/*==============================================================*/
create table instructor (
   insId                int                  identity,
   insName              varchar(20)          not null,
   insNum               varchar(20)          not null,
   tel                  char(11)             not null,
   collegeId            int                  not null default 0
      constraint CKC_COLLEGEID_INSTRUCT check (collegeId >= 0),
   constraint PK_INSTRUCTOR primary key (insId)
)
go

alter table classInfo
   add constraint FK_CLASSINF_REFERENCE_INSTRUCT foreign key (insId)
      references instructor (insId)
go

