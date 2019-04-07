/**
 * Notice Mapper
 *
 * @author Peiyuan
 * 2019-04-03
 */

package com.minipgm.message.notice;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {


    @Results({
            @Result(property = "noticeId", column = "notice_id"),
            @Result(property = "noticeTitle", column = "notice_title"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("SELECT notice_id,notice_title,create_date,update_date FROM notice WHERE notice_status=#{status}")
    List<NoticeToList> getNoticeList(int status);

    @Insert("INSERT INTO notice (user_id,notice_id,notice_title,notice_content,notice_status) " +
            "VALUES (#{userId},#{noticeId},#{noticeTitle},#{noticeContent},#{noticeStatus})")
    int addNewNotice(int userId, String noticeId, String noticeTitle, String noticeContent, int noticeStatus);

    @Update("UPDATE notice SET user_id=#{userId},notice_title=#{noticeTitle},notice_content=#{noticeContent}," +
            "update_date=CURRENT_TIMESTAMP WHERE notice_id=#{noticeId}")
    int modifyNotice(int userId,String noticeTitle,String noticeContent,String noticeId);

    @Delete("DELETE FROM notice WHERE notice_id=#{noticeId}")
    int deleteNotice(String noticeId);

    @Update("UPDATE notice SET notice_status=#{noticeStatus} WHERE notice_id=#{noticeId}")
    int changeStatus(String noticeId,int noticeStatus);

    @Select("SELECT MAX(notice_id) FROM notice WHERE notice_id LIKE #{todayId}")
    String maxNoticeId(String todayId);

}
