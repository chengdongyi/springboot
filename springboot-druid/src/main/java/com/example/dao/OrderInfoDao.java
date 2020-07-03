package com.example.dao;

import com.example.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderInfoDao {

    @Select("select order_id, mobile, product_id, product_name, price, status, order_time, update_time " +
            "from t_order_info where order_id = #{orderId}")
    OrderInfo findByOrderId(@Param("orderId") String orderId);

    @Insert({
            "insert into t_order_info ",
            "   (order_id, mobile, product_id, product_name, price, status, order_time, update_time) ",
            "values ",
            "   (#{orderId, jdbcType=VARCHAR}, #{mobile, jdbcType=VARCHAR}, #{productId, jdbcType=VARCHAR}, ",
            "    #{productName, jdbcType=VARCHAR}, #{price, jdbcType=VARCHAR}, #{status, jdbcType=VARCHAR}, ",
            "    #{orderTime, jdbcType=TIMESTAMP}, #{updateTime, jdbcType=TIMESTAMP})"
    })
    void insertRecord(OrderInfo orderInfo);

    @Update({
            "<script>",
            "update t_order_info ",
            "<set> ",
            "<if test=\"status != null and status != ''\">status = #{status,jdbcType=VARCHAR}, </if> ",
            "<if test=\"updateTime != null\">update_time = #{updateTime,jdbcType=TIMESTAMP}</if> ",
            "</set> ",
            "where order_id = #{orderId, jdbcType=VARCHAR} ",
            "</script>"
    })
    void updateRecord(OrderInfo orderInfo);

    @Delete("delete from t_order_info where order_id = #{orderId, jdbcType=VARCHAR}")
    void deleteOneRecord(@Param("orderId") String orderId);

    @Select("select order_id, mobile, product_id, product_name, price, status, order_time, update_time " +
            "from t_order_info where mobile = #{mobile}")
    @Results(id = "order_results", value = {
            @Result(property="orderId",   column="order_id"),
            @Result(property="mobile",   column="mobile"),
            @Result(property="productId",   column="product_id"),
            @Result(property="productName",   column="product_name"),
            @Result(property="price",   column="price"),
            @Result(property="status",   column="status"),
            @Result(property="orderTime",   column="order_time"),
            @Result(property="updateTime",   column="update_time")
    })
    OrderInfo findByMobile(@Param("mobile") String mobile);

    @Select("select order_id, mobile, product_id, product_name, price, status, order_time, update_time " +
            "from t_order_info where product_id = #{productId}")
    @ResultMap("order_results")
    List<OrderInfo> findByProductId(@Param("productId") String productId);

}
