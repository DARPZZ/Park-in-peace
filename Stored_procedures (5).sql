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
select * from tblPlot
end

GO
CREATE PROCEDURE getPlot (@fldPlotID int)
as
begin
select * from tblPlot where fldPlotID = @fldPlotID
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
alter PROCEDURE combine
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

