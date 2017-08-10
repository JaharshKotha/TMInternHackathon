DELIMITER ;;
CREATE DEFINER=`blackbird`@`%` PROCEDURE `DaySummaryComputation`()
BEGIN			
			insert into `DAY_SUMMARY_THRESHOLD` (venueId , offlineScanThreshold, rejectThreshold)
			select t1.venueId,t2.rejectThreshold,t1.offlineScanThreshold from 			
 	 		(select fres.eid,fres.`venueId`,avg(fres.totalOfflineScan) as `offlineScanThreshold` from (	 		
 	 		select res.eid,e.`venueId`,res.totalOfflineScan from (
 	 		select eti.eventId as eid, ifnull(COUNT(eti.eventTokenId),0) as totalOfflineScan
			from EVENT_TOKEN_INFO eti, SCAN_RESULT_LOOKUP srl
			WHERE eti.scanResult = srl.scanResult
			AND eti.eventId in (SELECT eventId from EVENTS  where venueId is not null and `localDate` BETWEEN CURDATE() - INTERVAL 10 DAY AND CURDATE()) 
			AND srl.`online` = 'OFFLINE'
			group by eventId ) AS res
			Inner Join EVENTS e on e.`eventId` = res.eid 
			) AS fres group by fres.`venueId` ) t1 
			
			left outer join
			
			(select fres.eid,fres.`venueId`,avg(fres.totalBadScan) as `rejectThreshold` from (	 		
 	 		select res.eid,e.`venueId`,res.totalBadScan from (
 	 		select eti.eventId as eid, ifnull(COUNT(eti.eventTokenId),0) as totalBadScan
			from EVENT_TOKEN_INFO eti, SCAN_RESULT_LOOKUP srl
			WHERE eti.scanResult = srl.scanResult
			AND eti.eventId in (SELECT eventId from EVENTS  where venueId is not null and `localDate` BETWEEN CURDATE() - INTERVAL 10 DAY AND CURDATE()) 
			AND srl.disposition = 'BAD'
			AND eti.horizonId is not null
			group by eventId) AS res
			Inner Join EVENTS e on e.`eventId` = res.eid 
			) AS fres group by fres.`venueId` )t2 
			on t1.`venueId` = t2.`venueId`
			 
			union
			
			select t2.venueId,t2.rejectThreshold,t1.offlineScanThreshold from 			
 	 		(select fres.eid,fres.`venueId`,avg(fres.totalOfflineScan) as `offlineScanThreshold` from (	 		
 	 		select res.eid,e.`venueId`,res.totalOfflineScan from (
 	 		select eti.eventId as eid, ifnull(COUNT(eti.eventTokenId),0) as totalOfflineScan
			from EVENT_TOKEN_INFO eti, SCAN_RESULT_LOOKUP srl
			WHERE eti.scanResult = srl.scanResult
			AND eti.eventId in (SELECT eventId from EVENTS  where venueId is not null and `localDate` BETWEEN CURDATE() - INTERVAL 10 DAY AND CURDATE()) 
			AND srl.`online` = 'OFFLINE'
			group by eventId ) AS res
			Inner Join EVENTS e on e.`eventId` = res.eid 
			) AS fres group by fres.`venueId` ) t1 
			
			right outer join
			
			(select fres.eid,fres.`venueId`,avg(fres.totalBadScan) as `rejectThreshold` from (	 		
 	 		select res.eid,e.`venueId`,res.totalBadScan from (
 	 		select eti.eventId as eid, ifnull(COUNT(eti.eventTokenId),0) as totalBadScan
			from EVENT_TOKEN_INFO eti, SCAN_RESULT_LOOKUP srl
			WHERE eti.scanResult = srl.scanResult
			AND eti.eventId in (SELECT eventId from EVENTS  where venueId is not null and `localDate` BETWEEN CURDATE() - INTERVAL 10 DAY AND CURDATE()) 
			AND srl.disposition = 'BAD'
			AND eti.horizonId is not null
			group by eventId) AS res
			Inner Join EVENTS e on e.`eventId` = res.eid 
			) AS fres group by fres.`venueId` )t2 
			on t1.`venueId` = t2.`venueId`; 
			
			
				
END;;
DELIMITER ;
