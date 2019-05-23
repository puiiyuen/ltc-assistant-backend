package com.minipgm.memo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemoMapper {

    @Select("SELECT * FROM memo WHERE user_id=#{userId}")
    List<Memo>memoList(int userId);

    @Select("SELECT * FROM memo " +
            "WHERE user_id=#{userId} AND memo_id=#{memoId}")
    Memo memoDetail(int userId,int memoId);

}

