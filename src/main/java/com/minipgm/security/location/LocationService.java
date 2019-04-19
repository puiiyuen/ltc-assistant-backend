/**
 * Location Service
 *
 * @author Peiyuan
 * 2019-03-26
 */

package com.minipgm.security.location;

import com.minipgm.security.location.utils.*;
import com.minipgm.utils.idGenerator;
import com.minipgm.utils.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private idGenerator idGenerator;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int collectLocations(List<Location> locations) {
        try {
            for (Location location : locations) {
                Double[] wgs84ToGcj02 = LocationConverter.WGS84ToGCJ02(location.getLongitude(), location.getLatitude());
                locationMapper.addUserLocation(location.getUserId(), wgs84ToGcj02[0],
                        wgs84ToGcj02[1], location.getUnixTs());
            }
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int createGeoFence(int userId, String gid, List<List<Double>> points) {
        try {
            for (List<Double> point : points) {
                locationMapper.addFencePoint(userId, gid, point.get(0), point.get(1));
            }
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int updateGeoFence(int userId, String gid, List<List<Double>> points) {
        try {
            locationMapper.deleteFencePoint(gid);
            for (List<Double> point : points) {
                locationMapper.addFencePoint(userId, gid, point.get(0), point.get(1));
            }
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int deleteGeoFence(String gid) {
        try {
            locationMapper.deleteFencePoint(gid);
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void fenceCheck(String gid) {
        try {
            List<Point> fence = locationMapper.getFence(gid);
            List<Location> userLocations = locationMapper.getUserLocation();
            for (Location userLocation : userLocations) {
                Point point = new Point(userLocation.getLongitude(), userLocation.getLatitude());
                if (!GeoFence.isPolygonContainsPoint(fence, point)) {
                    locationMapper.addSecurityRecord(idGenerator.incidentId(), userLocation.getUserId(),
                            0, userLocation.getLongitude(), userLocation.getLatitude(),
                            userLocation.getRecordDate());
                } else if (GeoFence.isPointInPolygonBoundary(fence, point)) {
                    locationMapper.addSecurityRecord(idGenerator.incidentId(), userLocation.getUserId(),
                            1, userLocation.getLongitude(), userLocation.getLatitude(),
                            userLocation.getRecordDate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Object helpRequest(Location location) {
        try {
            locationMapper.addSecurityRecord(idGenerator.incidentId(), location.getUserId(), 2,
                    location.getLongitude(), location.getLatitude(),location.getRecordDate());
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }


}
