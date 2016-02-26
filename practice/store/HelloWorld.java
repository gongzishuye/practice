package com.jd.si.venus.monitor.store.storeImpl;

import com.jd.si.monitor.kpi.MonitorItemInfo;
import com.jd.si.venus.monitor.store.MonitorKpiStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by chenlu11 on 2016/1/26.
 */

public class MonitorKpiStoreMysql implements MonitorKpiStore {
    private final static Logger logger = LoggerFactory.getLogger(MonitorKpiStoreMysql.class);

    private JdbcTemplate jdbcTemplate;

    public MonitorKpiStoreMysql(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
    }

    @Override
    public void storeMonitorInfo(MonitorItemInfo monitorItemInfo) {
        if(monitorItemInfo == null || !monitorItemInfo.isSetMonitorItem() || !monitorItemInfo.isSetLogtype() ||
                !monitorItemInfo.isSetDatetime() || !monitorItemInfo.isSetCount()){
            return ;
        }
        String sql = "insert into si_monitor_kpi values(?,?,?,?,?,?);";

        this.jdbcTemplate.update(sql,monitorItemInfo.getMonitorItem(), monitorItemInfo.getLogtype(), monitorItemInfo.getCount(),
                monitorItemInfo.getDatetime(), monitorItemInfo.getExpid(), monitorItemInfo.getCustom() == null ? "": monitorItemInfo.getCustom());
    }


}
