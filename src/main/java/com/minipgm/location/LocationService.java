/**
 * Location Service
 *
 * @author Peiyuan
 * 2019-03-26
 */

package com.minipgm.location;

import com.minipgm.location.utils.LocationConverter;
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

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int collectLocations(List<Location> locations) {
        try {
            for (Location location : locations) {
                Double[] wgs84ToGcj02 = LocationConverter.WGS84ToGCJ02(location.getLongitude(), location.getLatitude());
                locationMapper.addLocation(location.getUserId(), wgs84ToGcj02[0],
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
    public int createGeoFence(int userId,String gid,List<List<Double>> points) {
        try {
            for (List<Double> point : points) {
                locationMapper.addFencePoint(userId,gid,point.get(0),point.get(1));
            }
            return operationStatus.SUCCESSFUL;
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int updateGeoFence(int userId,String gid,List<List<Double>> points) {
        try {
            locationMapper.deleteFencePoint(gid);
            for (List<Double> point : points) {
                locationMapper.addFencePoint(userId,gid,point.get(0),point.get(1));
            }
            return operationStatus.SUCCESSFUL;
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
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


}
