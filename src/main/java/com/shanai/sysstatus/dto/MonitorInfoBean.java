package com.shanai.sysstatus.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 监视信息的JavaBean类.
 * @author wct
 */

@Data
@ToString
public class MonitorInfoBean {   
    /** *//** 可使用内存. */  
    private long totalMemory;   
       
    /** *//** 剩余内存. */  
    private long freeMemory;   
       
    /** *//** 最大可使用内存. */  
    private long maxMemory;   
       
    /** *//** 操作系统. */  
    private String osName;   
       
    /** *//** 总的物理内存. */  
    private long totalMemorySize;   
       
    /** *//** 剩余的物理内存. */  
    private long freePhysicalMemorySize;   
       
    /** *//** 已使用的物理内存. */  
    private long usedMemory;   
       
    /** *//** 总负载. */
    private double systemLoadAverage;
       
    /** *//** cpu使用率. */  
    private double cpuRatio;   

    private List<Space> spaces = new ArrayList<>();

    private List<SpaceInfo> spaceInfos = new ArrayList<>();


    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    public static class Space {
        String path;
        // 空闲
        long freeSpace;
        // 已使用
        long usableSpace;
        // 总空间
        long totalSpace;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    public static class SpaceInfo {
        String path;
        long reads;
        long writes;
        long transferTime;
    }
}  

