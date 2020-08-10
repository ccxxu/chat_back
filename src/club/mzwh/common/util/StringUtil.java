package club.mzwh.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static String dealArtcleId(String articleId){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date now = new Date();
		
		if(!"0".equals(articleId)){
			articleId = articleId.substring(6);
		}
		long id = Long.parseLong(articleId) + 1;
		
		int len = articleId.length();
		String str = "";
		if(len<8){
			for(int i=0;i<8-len;i++){
				str += "0";
			}
			str += id;
		}else{
			str = "" + id;
		}
		return sdf.format(now) + str;
	}
	
	/**
	 * 生成  yyyy/MM 串
	 * @return
	 */
	public static String dealStrYearMM(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date now = new Date();
		String str = sdf.format(now);
		return str;
	}
	
	/**
	 * 生成  yyyy/MM 串
	 * @return
	 */
	public static String dealStrYearMM(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String str = sdf.format(date);
		return str.substring(0, 4) + "/" + str.substring(4);
	}
	
	/**日期转化为指定格式的字符串
	 * @param Object 要转化的日期(应是一个有效的date)
	 * @param format 格式字符串，yyyy-MM-dd HH:mm:ss.SSS（详见SimpleDateFormat类）
	 * @return
	 */
	public static String dateToString(Object date,String format){
		if(date == null || date.equals("")) return "";
		else{
			try{
				return new SimpleDateFormat(format,Locale.CHINA).format((Date)date);
			}catch(Exception e){
				return date.toString();
			}		
		}
	}
	
	public static String replaceXSS(String str){
		if(str==null) str="";
/*		str=IgnoreCaseReplace(str,"net user", "");
		str=IgnoreCaseReplace(str,"xp_cmdshell", "");
		str=IgnoreCaseReplace(str,"exec master.dbo.xp_cmdshell", "");
		str=IgnoreCaseReplace(str,"net localgroup administrators", "");
		str=IgnoreCaseReplace(str,"insert into", "");
		str=IgnoreCaseReplace(str,"delete from", "");
		str=IgnoreCaseReplace(str,"drop table", "");
		str=IgnoreCaseReplace(str,"update", "");
		str=IgnoreCaseReplace(str,"truncate ", "");*/
		str=IgnoreCaseReplace(str,"'", "‘");
/*		str=IgnoreCaseReplace(str,"add", "");
		str=IgnoreCaseReplace(str,"select", "");    
		str=IgnoreCaseReplace(str,"from", "");
		str=IgnoreCaseReplace(str,"count", "");
		str=IgnoreCaseReplace(str,"asp", "");
		str=IgnoreCaseReplace(str,"char", "");
		str=IgnoreCaseReplace(str,"mid", "");
		str=IgnoreCaseReplace(str,":", "");*/
		
//		str=IgnoreCaseReplace(str,"file=", "");
//		str=IgnoreCaseReplace(str,"src=", "");
//		
//		str=IgnoreCaseReplace(str,"&", "&amp;");
//		str=IgnoreCaseReplace(str,"\"", "&quot;");
//		str=IgnoreCaseReplace(str,"%", "&#37");
//		str=IgnoreCaseReplace(str,">", "&gt;");
//		str=IgnoreCaseReplace(str,"<", "&lt;");
//		//str=IgnoreCaseReplace(str,"_", "&nbsp;_");
//		str=IgnoreCaseReplace(str,"=", "=??");
//		str=IgnoreCaseReplace(str,"\\|", "");
//		str=IgnoreCaseReplace(str,"/\\*\\*/", "");
//		str=IgnoreCaseReplace(str,"\\+","");
//		
//		str=IgnoreCaseReplace(str,"javascript:", "javascript：");
//		str=IgnoreCaseReplace(str,"jscript:", "jscript：");
//		str=IgnoreCaseReplace(str,"vbscript:", "vbscript：");
//		//str=IgnoreCaseReplace(str,"asc", "");
//		
//		str=IgnoreCaseReplace(str,"alert\\(", "");
//		str=IgnoreCaseReplace(str,"<script", "&lt;script");
		
		//str=IgnoreCaseReplace(str,"#include", "");
		if(str==null) str="";
		return str;
	}
	
	public static String IgnoreCaseReplace(String source, String oldstring, 
			String newstring){ 
			Pattern p = Pattern.compile(oldstring, Pattern.CASE_INSENSITIVE); 
			Matcher m = p.matcher(source); 
			String ret=m.replaceAll(newstring); 
			return ret; 
	}
	
	public static boolean isEmpty(String str){
		if(str != null && !"".equals(str)){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean isEmpty(Object obj){
		if(obj != null && !"".equals(obj.toString())){
			return false;
		}else{
			return true;
		}
	}
	
	public static String getUUID(){
		 UUID uuid = UUID.randomUUID();
		 return uuid.toString().replaceAll("-", "");
	}
	/**尝试将字符串转化为整型
	 * @param s 要转化的字符串
	 * @param defaultValue 转化失败的默认值
	 * @return
	 */
	public static int tryToInt(String s,int defaultValue){
		int r=defaultValue;
		try{
			r=Integer.parseInt(s);
		}catch(Exception e){
			r=defaultValue;
		}
		return r;
	}
	/**
	* <p>方法名称: stringToDate|描述: 字符串转化为日期</p>
	* @param str 要转化的字符串
	* @param format 格式字符串，yyyy-MM-dd HH:mm:ss.SSS（详见SimpleDateFormat类）
	* @return 转化失败返回null
	*/
	public static Date stringToDate(String str,String format){
		if(str == null || str.equals("")) return null;
		else{
			try{
				if(str.length() != format.length()) return null;
				return new SimpleDateFormat(format).parse(str);
			}catch(Exception e){
				return null;
			}		
		}
	}
	/**如果是null则转化为空
	 * @param o
	 * @return
	 */
	public static String nullToEmpty(Object o) {
		if (o == null)	return "";
		else			return o.toString();
	}
	
	/**
	 * 判断对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isNotNullOrEmpty(Object obj){
		if ("".equals(obj) || obj == null) {
			return false;
		}
		return true;
	}
	
	/**
	* <p>方法名称: getRandomNumString|描述: 生成指定长度的随机字符串</p>
	* @param len
	* @return 包含大小写字母和数字
	*/
	public static String getRandomString(int len){
		Random random = new Random();
		String s = "";
		for (int i=0;i<len;i++) {
			int rType = random.nextInt(3);
			int rChar=48;
			if(rType == 0){
				rChar = random.nextInt(10) + 48;
			}else if(rType == 1){
				rChar = random.nextInt(26) + 65;
			}else{//rType=2
				rChar = random.nextInt(26) + 97;
			}
			char ctmp = (char)rChar;
			s += String.valueOf(ctmp);
		}
		return s;
	}
	
	/**
	* <p>方法名称: isNullOrEmpty|描述: 判断字符串是否为null或者空</p>
	* @param s
	* @return
	*/
	public static boolean isNullOrEmpty(String s){
		if(null == s || "".equals(s))return true;
		return false;
	}
	
	 public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
