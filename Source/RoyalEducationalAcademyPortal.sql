CREATE DATABASE RoyalEducationalAcademyPortal
GO
USE RoyalEducationalAcademyPortal
GO
-- CREATE TABLE MEMBERPERMISSION, this table most use for understanding or interpretation - MP*
CREATE TABLE MEMBERPERMISSION(
MPID int identity primary key,
MPNAME nvarchar(50)
)
GO
-- INSERT MEMBERPERMISSION Table data
INSERT INTO MEMBERPERMISSION (MPNAME) VALUES (N'Administrator')
INSERT INTO MEMBERPERMISSION (MPNAME) VALUES (N'Stuff')
INSERT INTO MEMBERPERMISSION (MPNAME) VALUES (N'Student')
GO
SELECT * FROM MEMBERPERMISSION
GO
-- CREATE TABLE Members - M*
CREATE TABLE MEMBER(
MID int identity primary key,
MUSERNAME nvarchar(32),
MPASSWORD nvarchar(32),
MFULLNAME nvarchar(80),
MADRESS nvarchar(80),
MEMAIL nvarchar(80),
MBIRTHDATE smalldatetime,
MAVARTA image,
MPERMISSION int references MEMBERPERMISSION(MPID)
)
GO
-- INSERT MEMBER
INSERT INTO MEMBER(MUSERNAME,MPASSWORD,MFULLNAME,MADRESS,MEMAIL,MBIRTHDATE,MAVARTA,MPERMISSION)
VALUES (N'admin',N'21232f297a57a5a743894a0e4a801fc3',N'Phạm Huy Đức',N'Long Biên - Hà Nội - Việt Nam',N'ducphgc00103@fpt.edu.vn','08/09/1990',NULL,1)
GO
SELECT * FROM MEMBER
GO
-- Creating table about Bath, Sem, Course, Class ...
CREATE TABLE CLASS(
CID int identity primary key,
CNAME nvarchar(80)
)
GO
INSERT INTO CLASS(CNAME) VALUES (N'GC0501')
INSERT INTO CLASS(CNAME) VALUES (N'GC0502')
INSERT INTO CLASS(CNAME) VALUES (N'GC0503')
GO
SELECT * FROM CLASS
GO
CREATE TABLE CLASSMEMBER(
CMID int identity primary key,
CID int references CLASS(CID),
MID int references MEMBER(MID)
)
GO
INSERT INTO CLASSMEMBER(CID,MID) VALUES (1,7)
GO
SELECT * FROM CLASSMEMBER
GO
-- Creating Bath
CREATE TABLE BATH(
BID int identity primary key,
BNAME nvarchar(80)
)
GO
INSERT INTO BATH(BNAME) VALUES (N'GreenWich')
GO
SELECT * FROM BATH
GO
-- Creating Sem
-- DROP TABLE SEM
CREATE TABLE SEM(
SID int identity primary key,
SNAME nvarchar(80),
BID int references BATH(BID)
)
GO
INSERT INTO SEM(SNAME,BID) VALUES (N'Semester IV',1)
GO
SELECT * FROM SEM
GO

-- Creating Course
-- DROP TABLE COURSE
CREATE TABLE COURSE(
CID int identity primary key,
CNAME nvarchar(80),
SID int references SEM(SID)
)
GO
INSERT INTO COURSE(CNAME,SID) VALUES (N'Advandture Programming in C#',1)
GO
SELECT * FROM COURSE
GO
-- Creating Class in course
CREATE TABLE CLASSINCOURSE(
CCID int identity primary key,
CLASS int references CLASS(CID),
COURSE int references COURSE(CID)
)
GO
INSERT INTO CLASSINCOURSE(CLASS,COURSE) VALUES (1,1)
GO
SELECT * FROM CLASSINCOURSE
GO
