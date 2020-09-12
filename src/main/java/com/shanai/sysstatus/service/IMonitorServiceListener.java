package com.shanai.sysstatus.service;

import com.shanai.sysstatus.dto.MonitorInfoBean;

/**
 * @author wct
 */
public interface IMonitorServiceListener {
    boolean refresh(MonitorInfoBean monitorInfoBean);
}
