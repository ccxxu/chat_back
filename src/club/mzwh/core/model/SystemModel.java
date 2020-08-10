package club.mzwh.core.model;

import java.io.File;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import club.mzwh.common.util.Constant;
import club.mzwh.common.util.FileUtil;

/**  
 * @ClassName: SystemModel
 * @Description: 系统信息
 * @author ccxxu
 * @date 2020年3月14日 
 */
public class SystemModel {

	private double cpuIdle;
	
	private double cpuCombine;
	
	private long memTotal;
	
	private long memUsed;
	
	private double memCombine;
	
	private String cloudTotal = "5.0G";
	
	private String cloudUsed;
	
	private double cloudCombine;
	
	public SystemModel() {
		Sigar sigar = new Sigar();
        // 物理内存信息
        Mem mem;
		try {
			mem = sigar.getMem();
			// 内存总量 (M)
	        this.memTotal = mem.getTotal() / 1024L / 1024L;
	        // 当前内存使用量
	        this.memUsed = mem.getUsed() / 1024L / 1024L;
	        this.memCombine = (double)this.memUsed/this.memTotal;
	        //CPU的总量（单位：HZ）及CPU的相关信息
	        CpuInfo infos[] = sigar.getCpuInfoList();
	        CpuPerc cpuList[] = null;
	        cpuList = sigar.getCpuPercList();
	        if (infos.length>0) {// 不管是单块CPU还是多CPU都适用
	            //当前CPU的用户使用率、系统使用率、当前等待率、当前空闲率、总的使用率
	            CpuPerc cpu = cpuList[0];
	            this.cpuIdle = 1 - cpu.getCombined();// 当前空闲率
	            this.cpuCombine = cpu.getCombined();// 总的使用率
	        }
	        this.cloudUsed = FileUtil.formetFileSize(FileUtil.getFolderSize(new File(Constant.UPLOAD_PATH)));
	        this.cloudCombine = FileUtil.getCloudCombine();
		} catch (SigarException e) {
			e.printStackTrace();
			this.memTotal = 0;
			this.memUsed = 0;
			this.cpuIdle = 0.0;
			this.cpuCombine = 0.0;
		}
	}
	
	/**
	 * @return the cpuCombine
	 */
	public double getCpuCombine() {
		return cpuCombine;
	}
	
	/**
	 * @param cpuCombine the cpuCombine to set
	 */
	public void setCpuCombine(double cpuCombine) {
		this.cpuCombine = cpuCombine;
	}
	
	/**
	 * @return the cpuIdle
	 */
	public double getCpuIdle() {
		return cpuIdle;
	}
	
	/**
	 * @param cpuIdle the cpuIdle to set
	 */
	public void setCpuIdle(double cpuIdle) {
		this.cpuIdle = cpuIdle;
	}
	
	/**
	 * @return the memTotal
	 */
	public long getMemTotal() {
		return memTotal;
	}
	
	/**
	 * @param memTotal the memTotal to set
	 */
	public void setMemTotal(long memTotal) {
		this.memTotal = memTotal;
	}
	
	/**
	 * @return the memUsed
	 */
	public long getMemUsed() {
		return memUsed;
	}
	
	/**
	 * @param memUsed the memUsed to set
	 */
	public void setMemUsed(long memUsed) {
		this.memUsed = memUsed;
	}
	
	/**
	 * @return the memCombine
	 */
	public double getMemCombine() {
		return this.memCombine;
	}
	
	/**
	 * @param memCombine the memCombine to set
	 */
	public void setMemCombine(double memCombine) {
		this.memCombine = memCombine;
	}
	
	/**
	 * @return the cloudCombine
	 */
	public double getCloudCombine() {
		return cloudCombine;
	}
	
	/**
	 * @param cloudCombine the cloudCombine to set
	 */
	public void setCloudCombine(double cloudCombine) {
		this.cloudCombine = cloudCombine;
	}
	
	/**
	 * @return the cloudTotal
	 */
	public String getCloudTotal() {
		return this.cloudTotal;
	}
	
	/**
	 * @param cloudTotal the cloudTotal to set
	 */
	public void setCloudTotal(String cloudTotal) {
		this.cloudTotal = cloudTotal;
	}
	
	/**
	 * @return the cloudUsed
	 */
	public String getCloudUsed() {
		return cloudUsed;
	}
	
	/**
	 * @param cloudUsed the cloudUsed to set
	 */
	public void setCloudUsed(String cloudUsed) {
		this.cloudUsed = cloudUsed;
	}

}
