package club.mzwh.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-11 下午3:00:01 
 * 类说明
 */
public class DateFormatUtil {
	/**
	 * date to string
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String DateToString(Date date, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(date);
	}

	/**
	 * string to date
	 * 
	 * @param strDate
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date StringToDate(String strDate, String format)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = simpleDateFormat.parse(strDate);
		return date;
	}
}
