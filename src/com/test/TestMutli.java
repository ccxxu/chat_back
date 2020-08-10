package com.test;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

public class TestMutli {
	public static void main(String[] args) {        
		File source = new File("D:\\5.mp4");        
		Encoder encoder = new Encoder();        
		try {            
			MultimediaInfo m = encoder.getInfo(source);            
			long ls = m.getDuration();            
			System.out.println("此视频时长为:" + ls / 60000 + "分" + ls / 1000 + "秒！");        
			} catch (Exception e) {            
				e.printStackTrace();        
				}    
		}

}
