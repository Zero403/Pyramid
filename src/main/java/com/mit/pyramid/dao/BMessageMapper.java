package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mit.pyramid.entity.BMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
public interface BMessageMapper extends BaseMapper<BMessage> {

    @Select("select * from b_message where orderId = #{uid} and type != 0 ORDER BY createtime DESC")
    List<BMessage> myMessage(int uid);//我的所有消息

    @Select("select * from b_message where orderId = #{uid} and type = #{type} ORDER BY createTime DESC")
    List<BMessage> myMessageByType(@Param("uid") int uid, @Param("type") int type);

    @Select("select * from b_message where sendId = #{uid} and type != 0 ORDER BY createTime DESC")
    List<BMessage> sendedMessage(int uid);

    @Insert("INSERT INTO b_message(sendId, orderId, title, discription, createTime) values(#{sendId}, #{orderId}, #{title}, #{discription}, #{createTime})")
    int insertMessage(BMessage message);


}