use dbParkInPeace
--CREATE DATABASE dbParkInPeace

/*
GO
ALTER TABLE [dbo].[tblBlackList] DROP CONSTRAINT [fk_BLA]
    GO
ALTER TABLE [dbo].[tblResevations] DROP CONSTRAINT [fk_RES]
    GO
ALTER TABLE [dbo].[tblResevations] DROP CONSTRAINT [fk_PLres]
    GO
ALTER TABLE [dbo].[tblUser] DROP CONSTRAINT [fk_ZIP]
    GO
ALTER TABLE [dbo].[tblSeason] DROP CONSTRAINT [fk_PLsea]
    GO
ALTER TABLE [dbo].[tblPlot] DROP CONSTRAINT [fk_plot]
    GO
ALTER TABLE [dbo].[tblPlot] DROP CONSTRAINT [fk_PLsiz]
    GO
ALTER TABLE [dbo].[tblPlot] DROP CONSTRAINT [fk_PLuse]
    GO
ALTER TABLE [dbo].[tblParkingService] DROP CONSTRAINT [fk_Pser]
    GO
ALTER TABLE [dbo].[tblParkingService] DROP CONSTRAINT [fk_PSplo]
    GO
	*/


drop table if exists dbo.tblBlackList
drop table if exists dbo.tblService
drop table if exists dbo.tblParkingService

drop table if exists dbo.tblPlotSize
drop table if exists dbo.tblSeason

drop table if exists dbo.tblZipcodeCity

drop table if exists dbo.tblResevations
drop table if exists dbo.tblUser
drop table if exists dbo.tblPlot



    GO
CREATE TABLE tblUser
(
    fldUserID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
    fldName varchar(MAX),
	fldPhoneNumber varchar(8),
	fldPassword varchar(MAX),
	fldAddress varchar(MAX),
	fldAcountNumber int,
	fldEmail varchar(MAX),
	fldZipcode int,
)
CREATE TABLE tblZipcodeCity
(
    fldZipcode int PRIMARY KEY,
    fldCity nvarchar(MAX)
)
CREATE TABLE tblResevations
(
    fldreservationID int IDENTITY(1,1) PRIMARY KEY,
    fldStartDate date,
    fldEndDate date,
    fldUserID int,
    fldPlotID int
)
CREATE TABLE tblPlotSize
(
    fldPlotSizeID int IDENTITY(1,1) PRIMARY KEY,
    fldPlotSize varchar(MAX)
    )
CREATE TABLE tblPlot
(
    fldPlotID int IDENTITY(1,1) PRIMARY KEY,
    fldUserID int,
    fldLocation varchar(MAX),
fldDescription varchar(MAX),
fldImage varchar(MAX),
fldPlotSizeID int,
fldZipcode int
)

Create table tblService
(
    fldServiceID int not null IDENTITY(1,1) PRIMARY KEY,
    fldServiceType varchar(MAX)
    )
CREATE TABLE tblParkingService
(
    fldParkingServiceID int IDENTITY (1,1) PRIMARY KEY,
    fldPlotID int,
    fldServiceID int
)
CREATE TABLE tblBlackList
(
    fldBlackListID int IDENTITY(1,1) PRIMARY KEY,
    fldBlackList int,
    fldUserID int
)
CREATE TABLE tblSeason
(
    fldSeasonID int IDENTITY(1,1) PRIMARY KEY,
    fldPlotID int,
    fldLowSeasonPrice float,
    fldMediumSeasonPrice float,
    fldHighSeasonPrice float,
)

    GO

ALTER TABLE tblUser
    add constraint fk_ZIP
        foreign key (fldZipcode) references tblZipcodeCity(fldZipcode)


ALTER TABLE tblBlackList
    ADD constraint fk_BLA
        FOREIGN key (fldUserID) REFERENCES tblUser (fldUserID) ON DELETE CASCADE

ALTER TABLE tblResevations
    add constraint fk_RES foreign key (fldUserID) references tblUser (fldUserID),
CONSTRAINT fk_PLres foreign key (fldPlotID) references tblPlot (fldPlotID)

ALTER TABLE tblPlot
    add constraint fk_plot
        foreign key (fldZipcode) references tblZipcodeCity (fldZipcode),
CONSTRAINT fk_PLsiz foreign key (fldPlotSizeID) references tblPlotSize  (fldPlotSizeID) ,
CONSTRAINT fk_PLuse foreign key (fldUserID) references tblUser (fldUserID)

ALTER TABLE tblSeason
    add constraint fk_PLsea
        foreign key (fldPlotID) references tblPlot(fldPlotID) ON DELETE CASCADE

ALTER TABLE tblParkingService
    add constraint fk_Pser
        foreign key(fldPlotID) references tblPlot (fldPlotID) ON DELETE CASCADE,
CONSTRAINT fk_PSplo foreign key (fldServiceID) references tblService(fldServiceID)

--preload service types
INSERT INTO tblService (fldServiceType) VALUES ('NAN')
INSERT INTO tblService (fldServiceType) VALUES ('Toilet')
INSERT INTO tblService (fldServiceType) VALUES ('Water')
INSERT INTO tblService (fldServiceType) VALUES ('Electric')

INSERT INTO tblPlotSize (fldPlotSize) VALUES ('verysmall')
INSERT INTO tblPlotSize (fldPlotSize) VALUES ('small')
INSERT INTO tblPlotSize (fldPlotSize) VALUES ('mid')
INSERT INTO tblPlotSize (fldPlotSize) VALUES ('large')
INSERT INTO tblPlotSize (fldPlotSize) VALUES ('verylarge')

INSERT INTO tblZipcodeCity(fldCity,fldZipcode) VALUES ('Sønderborg',6400)
INSERT INTO tblZipcodeCity(fldCity,fldZipcode) VALUES ('Gråsten',6300)
INSERT INTO tblZipcodeCity(fldCity,fldZipcode) VALUES ('Aabenraa',6200)
INSERT INTO tblZipcodeCity(fldCity,fldZipcode) VALUES ('Haderslev',6100)

    INSERT INTO tblUser(fldName,fldPhoneNumber,fldPassword,fldAddress,fldAcountNumber,fldEmail,fldZipCode) VALUES ('Tobias Christensen', 74410239, 1,'B.S Ingmannsvej 2',10001000,'tob@mail.com',6400)
    INSERT INTO tblUser(fldName,fldPhoneNumber,fldPassword,fldAddress,fldAcountNumber,fldEmail,fldZipCode) VALUES ('Jesper Jepsen', 74459239, 'secretWord','Grundvigs Alle 37',10000320,'Jepsen@mail.com',6400)
    INSERT INTO tblUser(fldName,fldPhoneNumber,fldPassword,fldAddress,fldAcountNumber,fldEmail,fldZipCode) VALUES ('Nikolaj', 74890213, 'fettlovin','Perlegade 22',52301000,'fettman@mail.com',6400)
    INSERT INTO tblUser(fldName,fldPhoneNumber,fldPassword,fldAddress,fldAcountNumber,fldEmail,fldZipCode) VALUES ('Zhen Abudabi', 74950303, '12344231','Rådhusgade 23',12040000,'Zhen@mail.com',6300)
    INSERT INTO tblUser(fldName,fldPhoneNumber,fldPassword,fldAddress,fldAcountNumber,fldEmail,fldZipCode) VALUES ('Jakob Perrson', 21034089, 'Djblabblab','Lille Rådhusgade 19',10020943,'Perrson@mail.com',6400)
    INSERT INTO tblUser(fldName,fldPhoneNumber,fldPassword,fldAddress,fldAcountNumber,fldEmail,fldZipCode) VALUES ('Rasmus Hansen', 22304085, 'ralleralleman2003','Kolding vej 20',54391000,'ralleMail@mail.com',6100)

    INSERT INTO tblBlackList(fldUserID, fldBlackList) VALUES (1,0)
    INSERT INTO tblBlackList(fldUserID, fldBlackList) VALUES (2,0)
    INSERT INTO tblBlackList(fldUserID, fldBlackList) VALUES (3,0)
    INSERT INTO tblBlackList(fldUserID, fldBlackList) VALUES (4,0)
    INSERT INTO tblBlackList(fldUserID, fldBlackList) VALUES (5,0)
    INSERT INTO tblBlackList(fldUserID, fldBlackList) VALUES (6,0)

    INSERT INTO tblPlot(fldUserID, fldLocation, fldDescription,fldImage,fldPlotSizeID,fldZipCode) VALUES (1,'Grundtvigs Alle 2', 'DESCRIPTION PLACEHOLDER','IMAGEPATHPLACEHOLDER',1,6400)
    INSERT INTO tblPlot(fldUserID, fldLocation, fldDescription,fldImage,fldPlotSizeID,fldZipCode) VALUES (1,'Grundtvigs Alle 3', 'DESCRIPTION PLACEHOLDER','IMAGEPATHPLACEHOLDER',1,6400)
    INSERT INTO tblPlot(fldUserID, fldLocation, fldDescription,fldImage,fldPlotSizeID,fldZipCode) VALUES (1,'Grundtvigs Alle 4', 'DESCRIPTION PLACEHOLDER','IMAGEPATHPLACEHOLDER',1,6400)
    INSERT INTO tblPlot(fldUserID, fldLocation, fldDescription,fldImage,fldPlotSizeID,fldZipCode) VALUES (1,'Grundtvigs Alle 5', 'DESCRIPTION PLACEHOLDER','IMAGEPATHPLACEHOLDER',1,6400)
    INSERT INTO tblPlot(fldUserID, fldLocation, fldDescription,fldImage,fldPlotSizeID,fldZipCode) VALUES (2,'Perlegade 2', 'DESCRIPTION PLACEHOLDER','IMAGEPATHPLACEHOLDER',2,6400)
    INSERT INTO tblPlot(fldUserID, fldLocation, fldDescription,fldImage,fldPlotSizeID,fldZipCode) VALUES (2,'Perlegade  4', 'DESCRIPTION PLACEHOLDER','IMAGEPATHPLACEHOLDER',2,6400)

    INSERT INTO tblSeason (fldPlotID,fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice) VALUES (1, 30,50,100)
    INSERT INTO tblSeason (fldPlotID,fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice) VALUES (2, 30,50,100)
    INSERT INTO tblSeason (fldPlotID,fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice) VALUES (3, 30,50,120)
    INSERT INTO tblSeason (fldPlotID,fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice) VALUES (4, 30,50,120)
    INSERT INTO tblSeason (fldPlotID,fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice) VALUES (5, 60,100,200)
    INSERT INTO tblSeason (fldPlotID,fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice) VALUES (6, 60,100,200)


    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(1,2)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(1,1)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(1,4)

    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(2,2)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(2,3)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(2,4)

    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(3,4)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(3,3)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(3,1)

    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(4,3)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(4,4)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(4,1)

    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(5,1)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(5,1)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(5,1)

    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(6,2)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(6,3)
    INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(6,1)

    INSERT INTO tblResevations(fldStartDate,fldEndDate,fldUserID,fldPlotID) VALUES (CAST ('2023-06-01'AS Date), CAST ('2023-06-06' AS DATE), 3,1)
    INSERT INTO tblResevations(fldStartDate,fldEndDate,fldUserID,fldPlotID) VALUES (CAST ('2023-06-20'AS Date), CAST ('2023-06-21' AS DATE), 4,2)
    INSERT INTO tblResevations(fldStartDate,fldEndDate,fldUserID,fldPlotID) VALUES (CAST ('2023-06-08'AS Date), CAST ('2023-06-10' AS DATE), 5,3)
    INSERT INTO tblResevations(fldStartDate,fldEndDate,fldUserID,fldPlotID) VALUES (CAST ('2023-07-10'AS Date), CAST ('2023-07-11' AS DATE), 3,4)
    INSERT INTO tblResevations(fldStartDate,fldEndDate,fldUserID,fldPlotID) VALUES (CAST ('2023-06-13'AS Date), CAST ('2023-06-14' AS DATE), 5,5)
    INSERT INTO tblResevations(fldStartDate,fldEndDate,fldUserID,fldPlotID) VALUES (CAST ('2023-06-18'AS Date), CAST ('2023-06-19' AS DATE), 6,6)


