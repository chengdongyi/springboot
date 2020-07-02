package com.example.dao;

import com.example.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    String SELECT = "select id, username, password, status from user ";

    @Select(SELECT + "where username = #{username}")
    User findByUserName(@Param("username") String username);


//    @Select({"<script>",
//            "SELECT count, decode(successNum, NULL, '0', round(successNum * 100 / count, 2)) AS successRate",
//            "FROM (",
//            "   SELECT count(1) AS count,  sum(decode(orderflag, '1', 1, 0)) AS successNum",
//            "   FROM T_DFP_ORDERINFO_TRA",
//            "   <if test=\"partitionId == 'P202002'\">partition(P202002)</if>",
//            "   <if test=\"partitionId == 'P202003'\">partition(P202003)</if>",
//            "   <if test=\"partitionId == 'P202004'\">partition(P202004)</if>",
//            "   <if test=\"partitionId == 'P202005'\">partition(P202005)</if>",
//            "   <if test=\"partitionId == 'P202006'\">partition(P202006)</if>",
//            "   <if test=\"partitionId == 'P202007'\">partition(P202007)</if>",
//            "   <if test=\"partitionId == 'P202008'\">partition(P202008)</if>",
//            "   <if test=\"partitionId == 'P202009'\">partition(P202009)</if>",
//            "   <if test=\"partitionId == 'P202010'\">partition(P202010)</if>",
//            "   <if test=\"partitionId == 'P202011'\">partition(P202011)</if>",
//            "   <if test=\"partitionId == 'P202012'\">partition(P202012)</if>",
//            "   <if test=\"partitionId == 'P202101'\">partition(P202101)</if>",
//            "   <if test=\"partitionId == 'P202102'\">partition(P202102)</if>",
//            "   <if test=\"partitionId == 'P202103'\">partition(P202103)</if>",
//            "   <if test=\"partitionId == 'P202104'\">partition(P202104)</if>",
//            "   <if test=\"partitionId == 'P202105'\">partition(P202105)</if>",
//            "   <if test=\"partitionId == 'P202106'\">partition(P202106)</if>",
//            "   <if test=\"partitionId == 'P202107'\">partition(P202107)</if>",
//            "   <if test=\"partitionId == 'P202108'\">partition(P202108)</if>",
//            "   <if test=\"partitionId == 'P202109'\">partition(P202109)</if>",
//            "   <if test=\"partitionId == 'P202110'\">partition(P202110)</if>",
//            "   <if test=\"partitionId == 'P202111'\">partition(P202111)</if>",
//            "   <if test=\"partitionId == 'P202112'\">partition(P202112)</if>",
//            "   WHERE to_char (ordertime,'yyyymmddhh24miss') >= #{startTime}",
//            "   AND to_char (ordertime,'yyyymmddhh24miss') <![CDATA[<]]> #{endTime}",
//            "   )",
//            "</script>"})
//    OrderMorDataResult findAllByHour(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("partitionId") String partitionId);

}
