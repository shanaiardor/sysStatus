package com.shanai.sysstatus.service;

import com.shanai.sysstatus.dto.MonitorInfoBean;

import java.io.IOException;

/**
 * @author wct
 */
public interface IMonitorServiceListener {
    boolean refresh(MonitorInfoBean monitorInfoBean);
}
