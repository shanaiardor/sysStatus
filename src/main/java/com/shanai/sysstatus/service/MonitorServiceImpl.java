package com.shanai.sysstatus.service;

import com.shanai.sysstatus.dto.MonitorInfoBean;
import lombok.SneakyThrows;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;


import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author wct
 */
public class MonitorServiceImpl extends Timer implements IMonitorService {


    /**
     * 获得当前的监控对象.
     * @return 返回构造好的监控对象
     */
    @SneakyThrows
    private MonitorInfoBean getMonitorInfoBean() {


        MonitorInfoBean monitorInfoBean = new MonitorInfoBean();

        //============================ 内存 CPU ====================/
        int Mb = 1024 * 1024;
        int Gb = 1024 * 1024 * 1024;
        // JVM 使用内存
        long totalMemory = Runtime.getRuntime().totalMemory() / Mb;
        // JVM 空闲内存
        long freeMemory = Runtime.getRuntime().freeMemory() / Mb;
        // JVM 最大内存
        long maxMemory = Runtime.getRuntime().maxMemory() / Mb;

        // osmxb
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        // 系统平台
        String os_name = System.getProperty("os.name");

        com.sun.management.OperatingSystemMXBean osBean = ManagementFactory
                .getPlatformMXBean(com.sun.management.OperatingSystemMXBean.class);

        double os_processCpuLoad = osBean.getProcessCpuLoad();
        double os_systemCpuLoad = osBean.getSystemCpuLoad();
        long os_totalMemory = osBean.getTotalPhysicalMemorySize() / Mb;
        long os_freePhysicalMemorySize = osBean.getFreePhysicalMemorySize() / Mb;
        double systemLoadAverage = osBean.getSystemLoadAverage();

        //=================================================================/

        //================== 磁盘=====================/
        File[] files = File.listRoots();




        for (File file : files) {
            // 信息
            String path = file.getPath();
            // 空闲
            long freeSpace = file.getFreeSpace() / Gb;
            // 已使用
            long usableSpace = file.getUsableSpace() / Gb;
            // 总空间
            long totalSpace = file.getTotalSpace() /Gb;

            monitorInfoBean.getSpaces().add(new MonitorInfoBean.Space(path,freeSpace,usableSpace,totalSpace));
        }


        monitorInfoBean.setTotalMemory(totalMemory);
        monitorInfoBean.setFreeMemory(freeMemory);
        monitorInfoBean.setMaxMemory(maxMemory);
        monitorInfoBean.setOsName(os_name);
        monitorInfoBean.setTotalMemorySize(os_totalMemory);
        monitorInfoBean.setFreePhysicalMemorySize(os_freePhysicalMemorySize);
        monitorInfoBean.setUsedMemory(os_totalMemory - os_freePhysicalMemorySize);
        monitorInfoBean.setSystemLoadAverage(systemLoadAverage);
        monitorInfoBean.setCpuRatio(os_systemCpuLoad);


        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        List<HWDiskStore> diskStores = hardware.getDiskStores();
        for (HWDiskStore diskStore : diskStores) {
            long reads = diskStore.getReads();
            long writes = diskStore.getWrites();
            long transferTime = diskStore.getTransferTime();
            String name = diskStore.getName();

            monitorInfoBean.getSpaceInfos().add(new MonitorInfoBean.SpaceInfo(name,reads,writes,transferTime));
        }


        return monitorInfoBean;
    }

    @Override
    public void listen(IMonitorServiceListener listener, Long interval) {
        schedule(new TimerTask() {
            @Override
            public void run() {
                boolean refresh = listener.refresh(getMonitorInfoBean());
                if (!refresh) {
                    this.cancel();
                }
            }
        },new Date(),interval);
    }
}
