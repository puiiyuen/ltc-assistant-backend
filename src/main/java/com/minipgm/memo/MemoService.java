package com.minipgm.memo;

import com.minipgm.utils.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoService {

    @Autowired
    private MemoMapper memoMapper;

    public Object memoList(int userId){
        try {
            return memoMapper.memoList(userId);
        } catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    public Object memoDetail(int userId,int memoId){
        try {
            return memoMapper.memoDetail(userId,memoId);
        } catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

}
