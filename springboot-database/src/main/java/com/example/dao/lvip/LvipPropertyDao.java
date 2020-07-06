package com.example.dao.lvip;

import com.example.domain.JobsProperty;
import com.example.domain.lvip.LvipProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LvipPropertyDao {

    @Select("SELECT proName, proValue, startTime, endTime, proComment, proType "
            + " FROM lvip_property "
            + " WHERE proName = #{proName}")
    LvipProperty findByProName(@Param("proName") String proName);

    @Update("UPDATE lvip_property SET proValue = #{proValue} "
            + "WHERE proName = #{proName}")
    void updateRecord(LvipProperty lvipProperty);

}
