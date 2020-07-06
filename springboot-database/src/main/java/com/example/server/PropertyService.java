package com.example.server;

import com.example.dao.jobs.JobsPropertyDao;
import com.example.dao.lvip.LvipPropertyDao;
import com.example.domain.JobsProperty;
import com.example.domain.lvip.LvipProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PropertyService {

    @Resource
    private JobsPropertyDao jobsPropertyDao;

    @Resource
    private LvipPropertyDao lvipPropertyDao;

    public LvipProperty getLvipProperty(String proName) {
        return lvipPropertyDao.findByProName(proName);
    }

    public JobsProperty getJobsProperty(String proName) {
        return jobsPropertyDao.findByProName(proName);
    }

    /**
     * 只能指定 Manager 事务回滚
     *
     * 分布式事务
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-jta-atomikos</artifactId>
     * </dependency>
     */
    @Transactional("jobsTransactionManager")
    public void updateProperty(String proName, String vlaue) {
        JobsProperty jobs = new JobsProperty();
        jobs.setProName(proName);
        jobs.setProValue(vlaue);
        jobsPropertyDao.updateRecord(jobs);

        LvipProperty lvip = new LvipProperty();
        lvip.setProName(proName);
        lvip.setProValue(vlaue);
        lvipPropertyDao.updateRecord(lvip);
        int a = 10 / 0;
    }

}
