package com.example.dao.jobs;

import com.example.domain.JobsProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JobsPropertyDao {

    @Select("SELECT proName, proValue, startTime, endTime, proComment, proType "
            + "FROM jobs_property "
            + "WHERE proName = #{proName}")
    JobsProperty findByProName(@Param("proName") String proName);

    @Update("UPDATE jobs_property SET proValue = #{proValue} "
            + "WHERE proName = #{proName}")
    void updateRecord(JobsProperty jobsProperty);

}
