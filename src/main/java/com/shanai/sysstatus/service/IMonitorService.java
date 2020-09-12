package com.shanai.sysstatus.service;

/**
 * @author wct
 */
public interface IMonitorService {


    void listen(IMonitorServiceListener listener,Long interval);

} 