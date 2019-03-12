/**
 * Health Service
 *
 * @author Peiyuan
 * 2019-03-10
 */

package com.minipgm.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthService {

    @Autowired
    private HealthMapper healthMapper;

    public List<HealthDTO> getHealthReportList(){
        return healthMapper.getHealthReportList();
    }
}
