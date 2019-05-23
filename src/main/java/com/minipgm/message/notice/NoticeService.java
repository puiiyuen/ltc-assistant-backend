/**
 * Notice Service
 *
 * @author Peiyuan
 * 2019-04-03
 */

package com.minipgm.message.notice;

import com.minipgm.config.configInfo;
import com.minipgm.utils.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.nio.file.*;
import java.util.*;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public List<NoticeToList> getCurrentList() {
        try {
            return noticeMapper.getNoticeList(1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NoticeToList> getOldList() {
        try {
            return noticeMapper.getNoticeList(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getNoticeDetail(String noticeId){
        try {
            return noticeMapper.getNoticeDetail(noticeId);
        } catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    public Object displayNotice(int position){
        try {
            List<Notice> notices = noticeMapper.displayNotice();
            return notices.get(position-1);
        } catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int addNewNotice(Notice newNotice) {
        try {
            noticeMapper.addNewNotice(newNotice.getUserId(), newNotice.getNoticeId(), newNotice.getNoticeTitle(),
                    newNotice.getNoticeContent(), newNotice.getNoticeStatus());
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }


    public Object uploadPicture(byte[] picture, String pictureName) {
        try {
            String UPLOAD_FOLDER = "/opt/notices/";
            String api = configInfo.getApi(0);

           Path path = Paths.get(UPLOAD_FOLDER + pictureName);
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            Files.write(path, picture);
            String photoUrl = api + "/notices/pic/" + pictureName;
            Map<String,String> wrapperUrl = new HashMap<String,String>(){{
                put("link",photoUrl);
            }};
            return wrapperUrl;
        } catch (Exception e) {
            e.printStackTrace();
            //缺少删除图片
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int modifyNotice(Notice modifyNotice){
        try {
            if (noticeMapper.modifyNotice(modifyNotice.getUserId(),modifyNotice.getNoticeTitle(),
                    modifyNotice.getNoticeContent(),modifyNotice.getNoticeId())==1){
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
                return operationStatus.FAILED;
            }
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int deleteNotice(String noticeId){
        try{
            if (noticeMapper.deleteNotice(noticeId) == 1){
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
                return operationStatus.FAILED;
            }
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int changeNoticeStatus(String noticeId,int noticeStatus){
        try {
            if (noticeMapper.changeStatus(noticeId,noticeStatus) == 1){
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return operationStatus.FAILED;
            }
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

}
