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
go
Create PROCEDURE [dbo].[insertUser] (@fldName varchar(MAX), @fldPhoneNumber varchar(MAX),@fldPassword VARCHAR(max), @fldAddress varchar(MAX), @fldAcountNumber int,
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
CREATE PROCEDURE insertBlacklist (@fldBlacklist int, @fldUserID int)
as
begin
insert into tblBlackList (fldBlackList, fldUserID) Values(@fldBlacklist,@fldUserID)
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
select * from fldBlackList where fldBlackListID = @fldBlackListID
end


GO
CREATE PROCEDURE insertResevation (@fldStartDate date,@fldEndDate date,@fldUserID int,@fldPlotID int )
as
begin
SET NOCOUNT ON
INSERT INTO tblResevations(fldStartDate,fldEndDate,fldUserID,fldPlotID) VALUES (@fldStartDate,@fldEndDate,@fldUserID,@fldPlotID);
SELECT SCOPE_IDENTITY()
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
SELECT fldUserID,fldPlotID,fldLocation,fldDescription, fldImage, fldPlotSize,fldZipcode from tblPlot
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

GO
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

GO
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

GO
CREATE PROCEDURE insertServicePlot ( @ID int, @value int)
    as
begin
DECLARE @SQL NVARCHAR(MAX)
DECLARE @value2 NVARCHAR(MAX)
DECLARE @ID2 NVARCHAR(MAX)
set @ID2 = @ID
set @value2 =@value
set @SQL = N'INSERT INTO tblParkingServices (fldPlotID,fldServiceID) VALUES('+@ID2+','+@value2+')'
exec sp_executesql @SQL
end

GO
CREATE PROCEDURE deleteServicePlot (@ID int, @value int)
    as
begin
DELETE tblParkingService WHERE fldPlotID = @ID AND fldServiceID = @value
end

GO
CREATE PROCEDURE updatePlotPricing (@fieldname varchar(MAX), @value float, @ID int)
    as
begin
DECLARE @SQL NVARCHAR(MAX)
DECLARE @value2 NVARCHAR(MAX)
DECLARE @ID2 NVARCHAR(MAX)
set @ID2 = @ID
set @value2 =@value
set @SQL = N'UPDATE tblSeason SET '+ @fieldname +' = ' + @value2 + ' WHERE fldPlotID =' + @ID2
exec sp_executesql @SQL
end

GO
CREATE PROCEDURE createPlotNoPlotSize (@Location VARCHAR(MAX), @Description VARCHAR(MAX), @Image VARCHAR(MAX), @ZipCode int, @UserID int)
    as
begin
INSERT INTO tblPlot (fldLocation,fldDescription,fldImage,fldZipcode,fldUserID)
VALUES (@Location,@Description,@Image,@ZipCode,@UserID)
SELECT SCOPE_IDENTITY()
end


GO
CREATE PROCEDURE insertSeasonServiceSize(
    @fldToiletID int, @fldElectricID int , @fldWaterID int, @fldLowSeasonPrice float,@fldMediumSeasonPrice float,@fldHighSeasonPrice float,
    @fldPlotSize NVARCHAR(MAX),@fldPlotID int,@fldZip int)
    as
begin
DECLARE @plotsizeID int
INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(@fldPlotID,@fldToiletID)  IF(@fldToiletID >0)
INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(@fldPlotID,@fldWaterID)  IF(@fldWaterID >0)
INSERT INTO tblParkingService (fldPlotID,fldServiceID) VALUES(@fldPlotID,@fldElectricID)  IF(@fldElectricID >0)
INSERT INTO tblSeason (fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice, fldPlotID) VALUES (@fldLowSeasonPrice,@fldMediumSeasonPrice,@fldHighSeasonPrice,@fldPlotID);
INSERT INTO tblPlotSize (fldPlotSize) VALUES (@fldPlotSize);
SET @plotsizeID = (SELECT fldPlotSizeID FROM tblPlotSize WHERE fldPlotSize =@fldPlotSize)
UPDATE tblPlot SET fldPlotSizeID = @plotsizeID WHERE fldPlotID = @fldPlotID
UPDATE tblPlot SET fldZipcode = @fldZip WHERE fldPlotID = @fldPlotID

end

go
CREATE PROCEDURE getPlotServices (@plotID int)
    as
begin
SELECT fldServiceID FROM tblParkingService WHERE fldPlotID =@plotID
end

GO
CREATE PROCEDURE getPlotPrices (@plotID int)
    as
begin
SELECT fldLowSeasonPrice,fldMediumSeasonPrice,fldHighSeasonPrice FROM tblSeason WHERE fldPlotID =@plotID
end



GO
CREATE PROCEDURE userLoginCheck (@password VARCHAR(MAX), @username VARCHAR(MAX))
    as
begin
SELECT * FROM tblUser WHERE fldPassword =@password AND fldName =@username
end

GO
CREATE PROCEDURE removeBlackList (@fldUserID int, @fldBlackList int)
    as
begin
DELETE tblBlackList WHERE fldUserID = @fldUserID AND fldBlackList = @fldBlackList
end

GO
CREATE PROCEDURE addBlackList (@fldUserID int, @fldBlackList int)
    as
begin
IF NOT EXISTS (SELECT fldBlackList FROM tblBlackList WHERE fldUserID = @fldUserID AND fldBlackList = @fldBlackList)
	INSERT INTO tblBlackList (fldBlackList,fldUserID) VALUES (@fldBlackList,@fldUserID)

end

GO
CREATE PROCEDURE getBlackListedBy (@fldUserID int)
    as
begin
SELECT fldUserID FROM tblBlackList WHERE fldBlackList = @fldUserID
end
