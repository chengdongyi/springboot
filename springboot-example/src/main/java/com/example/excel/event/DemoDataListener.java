package com.example.excel.event;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.example.excel.data.DemoData;

import java.util.ArrayList;
import java.util.List;

public class DemoDataListener extends AnalysisEventListener<DemoData> {

    /**
     * 每隔5条存储数据库, 实际使用中可以3000条, 然后清理list, 方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    List<DemoData> list = new ArrayList<>();

    // private DemoDao demoDao;

    public DemoDataListener() {

    }

//    public DemoDataListener(DemoDao demoDao) {
//        this.demoDao = demoDao;
//    }

    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {

        System.out.println("解析到一条数据: " + JSON.toJSONString(demoData));
        list.add(demoData);
        // 达到 BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        System.out.println("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println(list.size() + "条数据，开始存储数据库！");
        // demoDao.save(list);
        System.out.println("存储数据库成功！");
    }

}
