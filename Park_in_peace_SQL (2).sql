use dbParkInPeace
--CREATE DATABASE dbParkInPeace

GO
ALTER TABLE [dbo].[tblBlackList] DROP CONSTRAINT [fk_BLA]
    GO
ALTER TABLE [dbo].[tblResevations] DROP CONSTRAINT [fk_RES]
    GO
ALTER TABLE [dbo].[tblResevations] DROP CONSTRAINT [fk_PLres]
    GO
ALTER TABLE [dbo].[tblUser] DROP CONSTRAINT [fk_ZIP]
    GO
ALTER TABLE [dbo].[tblSeason] DROP CONSTRAINT [fk_Psea]
    GO
ALTER TABLE [dbo].[tblPlot] DROP CONSTRAINT [fk_plot]
    GO
ALTER TABLE [dbo].[tblPlot] DROP CONSTRAINT [fk_PLsiz]
    GO
ALTER TABLE [dbo].[tblPlot] DROP CONSTRAINT [fk_PLuse]
    GO
ALTER TABLE [dbo].[tblParkingService] DROP CONSTRAINT [fk_Pser]
    GO
ALTER TABLE [dbo].[tblParkingService] DROP CONSTRAINT [fk_SERps]
    GO



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
    fldServiceValue bit,
    fldServiceType varchar(MAX),
    fldParkingServiceID int
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

ALTER TABLE tblParkingService
    add constraint fk_Pser
        foreign key(fldPlotID) references tblPlot (fldPlotID) ON DELETE CASCADE,
CONSTRAINT fk_SERps foreign key (fldServiceID) references tblService (fldServiceID) ON DELETE CASCADE

ALTER TABLE tblSeason
    add constraint fk_Psea
        foreign key (fldPlotID) references tblPlot(fldPlotID) ON DELETE CASCADE
