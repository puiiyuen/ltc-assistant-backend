/**
 * Staff Service
 *
 * @author Peiyuan
 * 2019-04-08
 */

package com.minipgm.staff;

import com.minipgm.config.configInfo;
import com.minipgm.enums.UserTypeEnum;
import com.minipgm.user.UserService;
import com.minipgm.util.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class StaffService {

    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private UserService userService;

    public Object getStaffList() {
        try {
            return staffMapper.getStaffList();
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    public Object getStaffDetail(int staffId) {
        try {
            return staffMapper.getStaffDetail(staffId);
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    public Object searchStaffs(int staffId,String name){
        try {
            return staffMapper.searchStaffs(staffId,name);
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int addStaff(Staff newStaff, int staffRegcode) {
        try {
            if (staffMapper.existStaff(newStaff.getGoverId()) == null) {
                userService.createAccount(newStaff.getStaffId(), "员工" + newStaff.getStaffId(),
                        UserTypeEnum.STAFF, staffRegcode, newStaff.getPhone(), newStaff.getEmail());
                staffMapper.addStaff(newStaff.getStaffId(), newStaff.getName(), newStaff.getSex(),
                        newStaff.getDob(), newStaff.getGoverId(), newStaff.getAddress(), newStaff.getPhotoUrl(),
                        newStaff.getMoveInDate());
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

    public Object uploadPhoto(byte[] photo, String photoName) {
        try {
            String UPLOAD_FOLDER = "/opt/photo/";
            String api = configInfo.getApi(0);

            Path path = Paths.get(UPLOAD_FOLDER + photoName);
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            Files.write(path, photo);
            String photoUrl = api + "/photo/" + photoName;
            Map<String, Object> wrapperUrl = new HashMap<String, Object>() {{
                put("link", photoUrl);
                put("operationStatus",operationStatus.SUCCESSFUL);
            }};
            return wrapperUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int modifyStaff(Staff staff) {
        try {
            if (staffMapper.modifyStaff(staff.getStaffId(), staff.getName(), staff.getSex(), staff.getDob(),
                    staff.getGoverId(), staff.getAddress(), staff.getPhotoUrl(), staff.getMoveInDate()) == 1 &&
                    userService.modifyContact(staff.getStaffId(), staff.getPhone(), staff.getEmail())
                            == operationStatus.SUCCESSFUL) {
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int deleteStaff(int staffId) {
        try {
            if (staffMapper.deleteStaff(staffId) == 1 &&
                    userService.destroyAccount(staffId) == operationStatus.SUCCESSFUL) {
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

}
