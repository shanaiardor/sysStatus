package com.shanai.sysstatus;

import com.shanai.sysstatus.dto.MonitorInfoBean;
import com.shanai.sysstatus.service.IMonitorService;
import com.shanai.sysstatus.service.MonitorServiceImpl;

public class Main {
    public static void main(String[] args) {
        IMonitorService monitorService = new MonitorServiceImpl();

        monitorService.listen(monitorInfoBean -> {
            System.out.println(monitorInfoBean);
            return true;
        },1000L);
    }
}
