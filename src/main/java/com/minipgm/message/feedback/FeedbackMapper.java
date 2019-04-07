/**
 * Feedback Mapper
 *
 * @author Peiyuan
 * 2019-04-07
 */

package com.minipgm.message.feedback;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FeedbackMapper {

    @Results({
            @Result(property = "feedbackId",column = "feedback_id"),
            @Result(property = "feedbackTitle", column = "feedback_title"),
            @Result(property = "createDate", column = "record_date")
    })
    @Select("SELECT feedback_id,feedback_title,record_date FROM feedback " +
            "ORDER BY record_date DESC")
    List<FeedbackToList> getFeedbackList();

    @Results({
            @Result(property = "feedbackId",column = "feedback_id"),
            @Result(property = "feedbackTitle", column = "feedback_title"),
            @Result(property = "feedbackContent",column = "feedback_content"),
            @Result(property = "createDate", column = "record_date")
    })
    @Select("SELECT * FROM feedback WHERE feedback_id=#{feedbackId}")
    Feedback getFeedbackDetail(String feedbackId);

    @Delete("DELETE FROM feedback WHERE feedback_id=#{feedbackId}")
    int deleteFeedback(String feedbackId);

    @Select("SELECT MAX(feedback_id) FROM feedback WHERE feedback_id LIKE #{todayId}")
    String maxFeedbackId(String todayId);

}
