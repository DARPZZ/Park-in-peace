-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile

use dbParkInPeace
/*
GO
CREATE PROCEDURE getAllUser
as
begin
select * from tblUser
end


GO
CREATE PROCEDURE getUser (@fldUserID int)
as
begin
select * from tblUser where fldUserID = @fldUserID
end


GO
CREATE PROCEDURE deleteUser (@fldUserID int)
as
begin
delete from tblUser where fldUserID = @fldUserID
end


GO
create PROCEDURE updateUser(@fldUserID int, @fieldname varchar(MAX), @value varchar(MAX))
as
begin
DECLARE @sql varchar(MAX)
SET @sql ='UPDATE  tblUser set ' + @fieldname +' = ''' + @value + ''' where fldUserID = ' + CAST( @fldUserID as varchar)
print @sql
execute (@SQL)
end

*/
/*
GO
CREATE PROCEDURE insertUser (@fldName varchar(MAX), @fldPhoneNumber varchar(MAX),@fldPassword VARCHAR(max), @fldAddress varchar(MAX), @fldAcountNumber int, 
	@fldEmail varchar(max), @fldZipcode int)
as
begin
DECLARE @sql varchar(MAX)
SET @sql = 'insert into [dbo].[tblUser]
           ([fldName]
           ,[fldPhoneNumber]
           ,[fldPassword]
           ,[fldAddress]
           ,[fldAcountNumber]
           ,[fldEmail]
           ,[fldZipcode])
		   values(''' + @fldName + ''' ,''' + @fldPhoneNumber + ''' , ''' + @fldPassword + ''' , ''' + @fldAddress +''', ' + CAST( @fldAcountNumber as varchar) + 
				', ''' + @fldEmail + ''' , ' + CAST(@fldZipcode as varchar) +')'
					print (@sql)
					execute (@sql)
end
*/

/*
GO
CREATE PROCEDURE getAllResevations
as
begin
select * from tblResevations
end
*/
/*
Go
create PROCEDURE getResevastion (@fldreservationID int)
as
begin
select * from tblResevations where fldreservationID = @fldreservationID
end
*/

/*

GO
CREATE PROCEDURE delteResevations (@fldreservationID int)
as
begin
delete from tblResevations where fldreservationID = @fldreservationID
end


GO
create PROCEDURE updateResevations(@fldreservationID int, @fieldname varchar(MAX), @value varchar(MAX))
as
begin
DECLARE @sql varchar(MAX)
SET @sql ='UPDATE tblResevations set ' + @fieldname +' = ''' + @value + ''' where fldreservationID = ' + CAST( @fldreservationID as varchar)
print @sql
execute (@SQL)
end


GO

CREATE PROCEDURE getAllBlackList
as
begin
select fldBlackList from tblBlackList
end


GO
CREATE PROCEDURE getBlacklist (@fldBlackListID int)
as
begin
select * from tblUser where fldBlackListID = @fldBlackListID
end


GO
CREATE PROCEDURE insertResevation (@fldStartDate date,@fldEndDate date,@fldUserID int,@fldPlotID int )
as
begin
DECLARE @sql varchar(MAX)
SET @sql = '
INSERT INTO [dbo].[tblResevations]
           ([fldStartDate]
           ,[fldEndDate]
           ,[fldUserID]
           ,[fldPlotID])
		 values(''' +CAST( @fldStartDate as varchar(max)) + ''' ,''' + CAST( @fldEndDate as varchar(max)) +''', ' + CAST( @fldUserID as varchar) + ', ' +
		 CAST (@fldPlotID as varchar) +')'
					print (@sql)
					execute (@sql)
end



GO
CREATE PROCEDURE getAllZipcode
as
begin
select * from tblZipcodeCity
end

GO
CREATE PROCEDURE getZipcode (@fldZipcode int)
as
begin
select * from tblZipcodeCity where fldZipcode = @fldZipcode
end

GO
CREATE PROCEDURE deletezipCode (@fldZipcode int)
as
begin
delete from tblZipcodeCity where fldZipcode = @fldZipcode
end





GO
CREATE PROCEDURE getAllServices
as
begin
select * from tblService
end

GO
CREATE PROCEDURE getService (@fldServiceID int)
as
begin
select * from tblService where fldServiceID = @fldServiceID
end

GO
CREATE PROCEDURE delteService (@fldServiceID int)
as
begin
delete from tblService where fldServiceID = @fldServiceID
end




GO
CREATE PROCEDURE getPlotPricingServices (@plotID int)
as
begin
select fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice FROM tblSeasonPlot JOIN
tblSeason ON tblSeasonPlot.fldSeasonID = tblSeason.fldSeasonID WHERE tblSeasonPlot.fldPlotID = @plotID
UNION
SELECT fldToilet,fldWater,fldElectric FROM tblParkingService join
tblService ON tblParkingService.fldServiceID = tblService.fldServiceID WHERE tblParkingService.fldPlotID =@plotID
end

    GO
CREATE PROCEDURE getallPlotPricingServices
as
begin
select fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice FROM tblSeasonPlot JOIN
tblSeason ON tblSeasonPlot.fldSeasonID = tblSeason.fldSeasonID
UNION
SELECT fldToilet,fldWater,fldElectric FROM tblParkingService join
tblService ON tblParkingService.fldServiceID = tblService.fldServiceID
end

GO
CREATE PROCEDURE getAllSeasonPlot
as
begin
select * from tblSeasonPlot
end

GO
CREATE PROCEDURE getSeasonPlot (@fldSeasonPlotID int)
as
begin
select * from tblSeasonPlot where fldSeasonPlotID = @fldSeasonPlotID
end

GO
CREATE PROCEDURE deleteSeasonPlot (@fldSeasonPlotID int)
as
begin
delete from tblSeasonPlot where fldSeasonPlotID = @fldSeasonPlotID
end




GO
CREATE PROCEDURE getAllPlotSize
as
begin
select * from tblPlotSize
end

GO
CREATE PROCEDURE getPlotSize (@fldPlotSizeID int)
as
begin
select * from tblPlotSize where fldPlotSizeID = @fldPlotSizeID
end

GO
CREATE PROCEDURE deleteplotSize (@fldPlotSizeID int)
as
begin
delete from tblPlotSize where fldPlotSizeID = @fldPlotSizeID
end



GO
CREATE PROCEDURE getAlltblParkingService
as
begin
select * from tblParkingService
end

GO
CREATE PROCEDURE getParkingService (@fldParkingServiceID int)
as
begin
select * from tblParkingService where fldParkingServiceID = @fldParkingServiceID
end

GO
CREATE PROCEDURE deleteParkingService (@fldParkingServiceID int)
as
begin
delete from tblParkingService where fldParkingServiceID = @fldParkingServiceID
end
*/

/*
GO
CREATE PROCEDURE getAllPlots
as
begin
SELECT fldUserID,fldPlotID,fldLocation, fldImage, fldPlotSize,fldZipcode from tblPlot
LEFT JOIN
tblPlotSize ON tblPlot.fldPlotSizeID = tblPlotSize.fldPlotSizeID
end

GO
CREATE PROCEDURE getPlot (@fldPlotID int)
as
begin
SELECT fldUserID,fldPlotID,fldLocation, fldImage, fldPlotSize,fldZipcode from tblPlot
LEFT JOIN
tblPlotSize ON tblPlot.fldPlotSizeID = tblPlotSize.fldPlotSizeID WHERE fldPlotID =@fldPlotID
end

GO
CREATE PROCEDURE deletePlot (@fldPlotID int)
as
begin
delete from tblPlot where fldPlotID = @fldPlotID
end
*/
/*
go
Create PROCEDURE combine
AS
BEGIN
  SELECT
    tblplot.fldPlotID,
    tblPlot.fldLocation,
    tblPlot.fldDescription,
    tblPlot.fldImage,
    tblService.fldElectric,
    tblService.fldToilet,
    tblService.fldWater,
	tblResevations.fldreservationID,
    tblResevations.fldStartDate,
    tblResevations.fldEndDate,
    tblResevations.flduserID,
    tblPlotSize.fldPlotSize,
    tblSeason.fldHighSeasonPrice,
    tblSeason.fldMediumSeasonPrice,
    tblSeason.fldLowSeasonPrice,
    tblSeason.fldSeasonName,
    tblZipcodeCity.fldZipcode
  FROM
    tblPlot
    LEFT JOIN tblParkingService ON tblPlot.fldPlotID = tblParkingService.fldPlotID
    LEFT JOIN tblService ON tblParkingService.fldServiceID = tblService.fldServiceID
    LEFT JOIN tblResevations ON tblResevations.fldPlotID = tblPlot.fldPlotID
    LEFT JOIN tblPlotSize ON tblPlotSize.fldPlotSizeID = tblPlot.fldPlotSizeID
    LEFT JOIN tblSeasonPlot ON tblSeasonPlot.fldPlotID = tblPlot.fldPlotID
    LEFT JOIN tblSeason ON tblSeason.fldSeasonID = tblSeasonPlot.fldSeasonID
    LEFT JOIN tblZipcodeCity ON tblZipcodeCity.fldZipcode = tblPlot.fldZipcode
	
END
*/
GO
create PROCEDURE getOwner
as
begin
select 
tblResevations.fldStartDate, tblResevations.fldEndDate,
tblPlot.fldLocation, tblPlot.fldPlotID, tblPlot.fldUserID,
tblZipcodeCity.fldZipcode

from tblPlot
inner join tblResevations
on tblPlot.fldPlotID = tblResevations.fldPlotID
inner join tblZipcodeCity
on tblZipcodeCity.fldZipcode = tblPlot.fldZipcode
end

GO
CREATE PROCEDURE getPlotSizeIDFromPlotID (@fldPlotID int)
    as
begin
SELECT fldPlotSize FROM  tblPlot JOIN
tblPlotSize ON tblPlot.fldPlotSizeID = tblPlotSize.fldPlotSizeID WHERE tblPlot.fldPlotID = @fldPlotID
end

CREATE PROCEDURE updatePlotSize (@fldPlotSize varchar(MAX), @fldPlotSizeID int)
    as
begin
UPDATE  tblPlotSize SET fldPlotSize = @fldPlotSize WHERE fldPlotSizeID =@fldPlotSizeID
end
CREATE PROCEDURE updatePlotStrings (@fieldname varchar(MAX), @value VARCHAR(MAX), @ID int)
    as
begin
DECLARE @SQL NVARCHAR(MAX)
DECLARE @value2 NVARCHAR(MAX)
DECLARE @ID2 NVARCHAR(MAX)
set @ID2 = @ID
set @value2 =@value
set @SQL = N'UPDATE tblPlot SET '+ @fieldname +' = '+CHAR(39) + @value2 +CHAR(39) + ' WHERE fldPlotID =' + @ID2
exec sp_executesql @SQL
end
CREATE PROCEDURE updatePlotIntegers (@fieldname varchar(MAX), @value int, @ID int)
    as
begin
DECLARE @SQL NVARCHAR(MAX)
DECLARE @value2 NVARCHAR(MAX)
DECLARE @ID2 NVARCHAR(MAX)
set @ID2 = @ID
set @value2 =@value
set @SQL = N'UPDATE tblPlot SET '+ @fieldname +' = ' + @value2 + ' WHERE fldPlotID =' + @ID2
exec sp_executesql @SQL
end

CREATE PROCEDURE updatePlotServices (@fieldname varchar(MAX), @value VARCHAR(MAX), @ID int)
    as
begin
DECLARE @SQL NVARCHAR(MAX)
DECLARE @value2 NVARCHAR(MAX)
DECLARE @ID2 NVARCHAR(MAX)
set @ID2 = @ID
set @value2 =@value
set @SQL = N'UPDATE tblService SET '+ @fieldname +' = ' + @value2 + ' WHERE fldServiceID =' + @ID2
exec sp_executesql @SQL
end

CREATE PROCEDURE getPlotServiceID (@ID int)
    as
begin
SELECT tblService.fldServiceID FROM tblParkingService JOIN
                                    tblService ON tblService.fldServiceID = tblParkingService.fldServiceID WHERE tblParkingService.fldPlotID =2
end

CREATE PROCEDURE updatePlotPricing (@fieldname varchar(MAX), @value float, @ID int)
    as
begin
DECLARE @SQL NVARCHAR(MAX)
DECLARE @value2 NVARCHAR(MAX)
DECLARE @ID2 NVARCHAR(MAX)
set @ID2 = @ID
set @value2 =@value
set @SQL = N'UPDATE tblSeason SET '+ @fieldname +' = ' + @value2 + ' WHERE fldSeasonID =' + @ID2
exec sp_executesql @SQL
end

CREATE PROCEDURE getPlotSeasonID(@ID int)
    as
begin
SELECT tblSeason.fldSeasonID FROM tblSeasonPlot JOIN
                                  tblSeason ON tblSeason.fldSeasonID = tblSeasonPlot.fldSeasonID WHERE tblSeasonPlot.fldPlotID =2
end

CREATE PROCEDURE createPlotNoPlotSize (@Location VARCHAR(MAX), @Description VARCHAR(MAX), @Image VARCHAR(MAX), @ZipCode int, @UserID int)
    as
begin
INSERT INTO tblPlot (fldLocation,fldDescription,fldImage,fldZipcode,fldUserID)
VALUES (@Location,@Description,@Image,@ZipCode,@UserID)
SELECT SCOPE_IDENTITY()
end

CREATE PROCEDURE getPlotID (@userID int, @location VARCHAR(MAX))
    as
begin
SELECT MAX(1) fldPlotID FROM tblPlot WHERE fldUserID =@userID AND fldLocation =@location
end

CREATE PROCEDURE insertSeasonServiceSizeFirstPass (
    @fldToilet int, @fldElectric int , @fldWater int, @fldLowSeasonPrice float,@fldMediumSeasonPrice float,@fldHighSeasonPrice float,
    @fldPlotSize NVARCHAR(MAX))
    as
begin
DECLARE @serviceID int
DECLARE @seasonID int
DECLARE @zipID int
INSERT INTO tblService (fldToilet,fldElectric,fldWater) VALUES (@fldToilet,@fldElectric,@fldWater)
SET @serviceID = SCOPE_IDENTITY()
INSERT INTO tblSeason (fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice) VALUES (@fldLowSeasonPrice,@fldMediumSeasonPrice,@fldHighSeasonPrice)
SET @seasonID = SCOPE_IDENTITY()
INSERT INTO tblPlotSize (fldPlotSize) VALUES (@fldPlotSize)
SET @zipID =SCOPE_IDENTITY()
SELECT @serviceID, @seasonID, @zipID
end

CREATE PROCEDURE insertPlotLastPass (@plotID int, @serviceID int,@seasonID int,@sizeID int)
    as
begin
INSERT INTO tblParkingService (fldPlotID, fldServiceID) VALUES (@plotID,@serviceID)
    INSERT INTO tblSeasonPlot (fldPlotID,fldSeasonID) VALUES (@plotID,@seasonID)
UPDATE tblPlot SET fldPlotSizeID = @sizeID WHERE fldPlotID = @plotID
end

CREATE PROCEDURE userLoginCheck (@password VARCHAR(MAX), @username VARCHAR(MAX))
    as
begin
SELECT * FROM tblUser WHERE fldPassword =@password AND fldName =@username
end