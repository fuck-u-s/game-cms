package com.cms.model.sys;

/**
 * 方法描述:系统信息
 * <p>
 * author 小刘
 * version v1.0
 * date 2017/4/17 10:34
 */
public class SysInfo {

    // 基本信息

    private String ip;        //IP
    private String hostName;  // 主机名
    private String osName;    // 操作系统
    private String arch;      // 操作系统架构
    private String osVersion; // 操作系统版本
    private int processors;   // 处理器个数
    private String javaVersion; // java版本
    private String vendor;   // java运行环境供应商
    private String javaUrl;  // java供应商url
    private String javaHome; // java安装路径
    private String tmpdir;   // 默认的临时文件路径

    // 资源信息
    private String jvmTotal; // java总内存
    private String jvmUse;   // jvm使用内存
    private String jvmFree;  // jvm剩余内存
    private double jvmUsage; // jvm使用率

    private String ramTotal; // 内存总量
    private String ramUse;   // 当前内存使用量
    private String ramFree;  // 当前内存剩余量
    private double ramUsage; // 内存使用率

    private String swapTotal; // 交换区总量
    private String swapUse;   // 交换区使用量
    private String swapFree;  // 交换区剩余量
    private double swapUsage; // 交换区使用率

    private long cpuUserUse;  // CPU用户使用率
    private long cpuSysUse;   // CPU系统使用率
    private long cpuWait;     // CPU当前等待率
    private long cpuFree;     // CPU当前空闲率
    private long cpuTotal;    // CPU总使用率

    private String diskName;  // 系统盘名称
    private String diskType;  // 系统盘类型
    private String diskTotal; // 文件系统总大小
    private String diskFree;  // 文件系统剩余大小
    private long diskUse;     // 文件系统使用量
    private double diskUsage; // 文件系统利用率


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public int getProcessors() {
        return processors;
    }

    public void setProcessors(int processors) {
        this.processors = processors;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getJavaUrl() {
        return javaUrl;
    }

    public void setJavaUrl(String javaUrl) {
        this.javaUrl = javaUrl;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome;
    }

    public String getTmpdir() {
        return tmpdir;
    }

    public void setTmpdir(String tmpdir) {
        this.tmpdir = tmpdir;
    }

    public String getJvmTotal() {
        return jvmTotal;
    }

    public void setJvmTotal(String jvmTotal) {
        this.jvmTotal = jvmTotal;
    }

    public String getJvmUse() {
        return jvmUse;
    }

    public void setJvmUse(String jvmUse) {
        this.jvmUse = jvmUse;
    }

    public String getJvmFree() {
        return jvmFree;
    }

    public void setJvmFree(String jvmFree) {
        this.jvmFree = jvmFree;
    }

    public double getJvmUsage() {
        return jvmUsage;
    }

    public void setJvmUsage(double jvmUsage) {
        this.jvmUsage = jvmUsage;
    }

    public String getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(String ramTotal) {
        this.ramTotal = ramTotal;
    }

    public String getRamUse() {
        return ramUse;
    }

    public void setRamUse(String ramUse) {
        this.ramUse = ramUse;
    }

    public String getRamFree() {
        return ramFree;
    }

    public void setRamFree(String ramFree) {
        this.ramFree = ramFree;
    }

    public double getRamUsage() {
        return ramUsage;
    }

    public void setRamUsage(double ramUsage) {
        this.ramUsage = ramUsage;
    }

    public String getSwapTotal() {
        return swapTotal;
    }

    public void setSwapTotal(String swapTotal) {
        this.swapTotal = swapTotal;
    }

    public String getSwapUse() {
        return swapUse;
    }

    public void setSwapUse(String swapUse) {
        this.swapUse = swapUse;
    }

    public String getSwapFree() {
        return swapFree;
    }

    public void setSwapFree(String swapFree) {
        this.swapFree = swapFree;
    }

    public double getSwapUsage() {
        return swapUsage;
    }

    public void setSwapUsage(double swapUsage) {
        this.swapUsage = swapUsage;
    }

    public long getCpuUserUse() {
        return cpuUserUse;
    }

    public void setCpuUserUse(long cpuUserUse) {
        this.cpuUserUse = cpuUserUse;
    }

    public long getCpuSysUse() {
        return cpuSysUse;
    }

    public void setCpuSysUse(long cpuSysUse) {
        this.cpuSysUse = cpuSysUse;
    }

    public long getCpuWait() {
        return cpuWait;
    }

    public void setCpuWait(long cpuWait) {
        this.cpuWait = cpuWait;
    }

    public long getCpuFree() {
        return cpuFree;
    }

    public void setCpuFree(long cpuFree) {
        this.cpuFree = cpuFree;
    }

    public long getCpuTotal() {
        return cpuTotal;
    }

    public void setCpuTotal(long cpuTotal) {
        this.cpuTotal = cpuTotal;
    }

    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public String getDiskTotal() {
        return diskTotal;
    }

    public void setDiskTotal(String diskTotal) {
        this.diskTotal = diskTotal;
    }

    public String getDiskFree() {
        return diskFree;
    }

    public void setDiskFree(String diskFree) {
        this.diskFree = diskFree;
    }

    public long getDiskUse() {
        return diskUse;
    }

    public void setDiskUse(long diskUse) {
        this.diskUse = diskUse;
    }

    public double getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(double diskUsage) {
        this.diskUsage = diskUsage;
    }
}
