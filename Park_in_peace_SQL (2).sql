use dbParkInPeace
--CREATE DATABASE dbParkInPeace
drop table if exists dbo.tblBlackList
drop table if exists dbo.tblParkingService
drop table if exists dbo.tblResevations
drop table if exists dbo.tblSeasonPlot
drop table if exists dbo.tblPlot
drop table if exists dbo.tblPlotSize
drop table if exists dbo.tblSeason
drop table if exists dbo.tblService
drop table if exists dbo.tblUser
drop table if exists dbo.tblZipcodeCity


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
	fldToilet bit,
	fldElectric bit,
	fldWater bit
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
    fldSeasonName varchar(MAX),
	fldLowSeasonPrice float,
    fldMediumSeasonPrice float,
    fldHighSeasonPrice float,
)
CREATE TABLE tblSeasonPlot
(
    fldSeasonPlotID int IDENTITY(1,1) PRIMARY KEY,
    fldSeasonID int,
    fldPlotID int,
  
)

GO

ALTER TABLE tblUser
add constraint fk_ZIP
foreign key (fldZipcode) references tblZipcodeCity(fldZipcode)


ALTER TABLE tblBlackList
ADD constraint fk_BLA
FOREIGN key (fldUserID) REFERENCES tblUser (fldUserID)

ALTER TABLE tblResevations
add constraint fk_RES
foreign key (fldUserID) references tblUser (fldUserID),
foreign key (fldPlotID) references tblPlot (fldPlotID)

ALTER TABLE tblPlot
add constraint fk_plot
foreign key (fldZipcode) references tblZipcodeCity (fldZipcode),
foreign key (fldPlotSizeID) references tblPlotSize  (fldPlotSizeID),
foreign key (fldUserID) references tblUser (fldUserID)

ALTER TABLE tblParkingService
add constraint fk_Pser
foreign key(fldPlotID) references tblPlot (fldPlotID),
foreign key (fldServiceID) references tblService (fldServiceID)

ALTER TABLE tblSeasonPlot
add constraint fk_splot
FOREIGN KEY (fldSeasonID) REFERENCES tblSeason (fldSeasonID),
FOREIGN KEY (fldPlotID) REFERENCES tblPlot (fldPlotID)