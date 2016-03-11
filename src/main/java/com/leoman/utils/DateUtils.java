package com.leoman.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Java中日期的经常有一下五个方面： 1、创建日期 2、日期格式化显示 3、日期的转换（主要是和字符串之间的相互转换）
 * 4、日期中年、月、日、时、分、秒、星期、月份等获取。 5、日期的大小比较、日期的加减。
 * 
 * @author Administrator
 * 
 */
public class DateUtils {
	private DateUtils() {

	}

	public static void main(String[] args) throws ParseException {
		// System.out.println(DateTools.dateToDateWithFormat(new Date(),
		// "yyyy-MM-01"));
		//		
		// System.out.println(DateTools.dateWithFormat(new Date(),
		// "yyyy-MM-1"));

		// System.out.println(DateTools.dateCompare(DateTools.stringToDateWithFormat("2008-09-12",
		// "yyyy-MM-dd")));

		// System.out.println(DateTools.getDays(new Date()));
		// System.out.println(DateTools.getRemainDays(new Date()));
//		System.out.println(new Date());
//        int year =  getYear(new Date());
//        int month = getMonth(new Date());
//        System.out.println("year:"+year+",month:"+month+1);

//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = format.parse("2015-06-15 22:30:00");
//
//        System.out.println(minuteCompare(date,15));

		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dateAddDay(new Date(),3)));

    }

	/**
	 *  获得上m月的第一天
	 * @param nowdate
	 * @param m 月数,0是本月的第一天,-1上月的第一天
	 * @return
	 * @throws ParseException
	 */
	public static Date getStartDate(Date nowdate,Integer m) throws ParseException{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(nowdate);
		gc.add(GregorianCalendar.MONTH, m);
		Calendar now = Calendar.getInstance();
		now.setTime(gc.getTime());
		String mm=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1);
		return DateUtils.stringToDateWithFormat(mm+"-"+"01 00:00:00","yyyy-MM-dd HH:mm:ss");
	}


    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    public static Integer getYear(Date date){

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.YEAR);
    }

    public static Integer getMonth(Date date){

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.MONTH);
    }



    /**
	 * 当月天数
	 *
	 * @return
	 */
	public static Integer getDays(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 当月剩余天数
	 *
	 * @return
	 */
	public static Integer getRemainDays(Date date) {
		Calendar curcal = Calendar.getInstance();
		curcal.setTime(date);
		Integer remain = curcal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1
				- curcal.get(Calendar.DATE);
		return remain;
	}

	/* 日期转中文 */
	/**
	 * 中文显示周几
	 *
	 * @param date
	 */
	public static String chinaDayOfWeek(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		String dayOfWeek[] = { "", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六" };
		return dayOfWeek[now.get(Calendar.DAY_OF_WEEK)];
	}

	/**
	 * 中文显示月份
	 *
	 * @param date
	 * @return
	 */
	public static String chinaMonth(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		String months[] = { "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月",
				"九月", "十月", "十一月", "十二月" };
		return months[now.get(Calendar.MONTH)];
	}

	/* 日期格式转换 */

	/**
	 * 获得format格式的日期 此方法内部用到了dateWithCalendar(Date date)方法 format notice:
	 * yyyy-MM-01 exception yyyy-MM-1 ok
	 *
	 * @param date
	 * @param format
	 *
	 * @return
	 * @throws ParseException
	 */
	public static Date dateWithFormat(Date date, String format)
			throws ParseException {
		String str = DateUtils.dateWithCalendar(date);
		return DateUtils.stringToDateWithFormat(str, format);
	}

	/**
	 * 用Calendar获得精确到毫秒的日期字符串
	 *
	 * @param date
	 * @return
	 */
	public static String dateWithCalendar(Date date) {
		StringBuffer sb = new StringBuffer();
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		sb.append(now.get(Calendar.YEAR));
		sb.append("-");
		sb.append(now.get(Calendar.MONTH) + 1);
		sb.append("-");
		sb.append(now.get(Calendar.DATE));
		sb.append(" ");
		sb.append(now.get(Calendar.HOUR));
		sb.append(":");
		sb.append(now.get(Calendar.MINUTE));
		sb.append(":");
		sb.append(now.get(Calendar.SECOND));
		sb.append(":");
		sb.append(now.get(Calendar.MILLISECOND));
		return sb.toString();
	}

	/**
	 * 将日转换成,format格式的日期字符串
	 *
	 * @param date
	 *            new Date()
	 * @param format
	 *            yyyy-MM-dd
	 * @return
	 */
	public static String dateToStringWithFormat(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 将日期字符串转换成,format格式的日期
	 *
	 * @param date
	 *            日期字符串
	 * @param format
	 *            日期格式:yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDateWithFormat(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}

	/**
	 * 将日期转换成format格式的日期
	 *
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式:yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static Date dateToDateWithFormat(Date date, String format)
			throws ParseException {
		String str = DateUtils.dateToStringWithFormat(date, format);
		date = DateUtils.stringToDateWithFormat(str, format);
		return date;
	}

	/* 日期比较 */

	/**
	 * 当前日期与参数日期的比较, 参数日期大于当前日期时返回true
	 * 
	 * @param date
	 * @return
	 */
	public static boolean dateCompare(Date date) {
		if (new Date().before(date)) {
			return true;
		}
		return false;
	}

	public static Date dateAddDay(Date date, int day) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.DATE, day);
		return gc.getTime();
	}

	public static Date dateAddMonth(Date date, int m) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.MONTH, m);
		return gc.getTime();
	}



	/**
	 * 下列方法是判断当前时间与旧的时间是否相隔second秒，是返回真，否返回假 如果当前日期与参数日期间隔大于second秒时返回真，
	 * 
	 * @param date
	 * @param second
	 * @return
	 */
	public static boolean secondCompare(Date date, int second) {
		GregorianCalendar gc = new GregorianCalendar();
		Date d = new Date();
		gc.setTime(d);
		gc.add(GregorianCalendar.SECOND, -second);
		// 当前时间减去second秒之后是否还在旧时间前面
		// 旧时间的前面 olddate.before
		if (date.before(gc.getTime())) {
			return true;
		}
		return false;
	}

	/**
	 * 如果当前日期与旧日期间隔大于minute分时返回真，
	 * @param olddate
	 * @param minute
	 * @return
	 */
	public static boolean minuteCompare(Date olddate, int minute) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.MINUTE, -minute);
		if (olddate.before(gc.getTime())) {
			return true;
		}
		return false;
	}

	/**
	 * 如果当前日期与旧日期间隔大于hour小时,返回真，
	 * 
	 * @param olddate
	 * @param hour
	 * @return
	 */
	public static boolean hourCompare(Date olddate, int hour) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.HOUR, -hour);
		if (olddate.before(gc.getTime())) {
			return true;
		}
		return false;
	}

	/**
	 * 如果当前日期与旧日期间隔大于day天时返回真，
	 * 
	 * @param olddate
	 * @param day
	 * @return
	 */
	public static boolean dayCompare(Date olddate, int day) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.DATE, -day);
		if (olddate.before(gc.getTime())) {
			return true;
		}
		return false;
	}

	/**
	 * 如果当前日期与旧日期间隔大于month月,返回真
	 * 
	 * @param olddate
	 * @param month
	 * @return
	 */
	public static boolean monthCompare(Date olddate, int month) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.MONTH, -month);
		if (olddate.before(gc.getTime())) {
			return true;
		}
		return false;
	}

	/**
	 * 如果当前日期与旧日期间隔大于year年,返回真，
	 * 
	 * @param olddate
	 * @param year
	 * @return
	 */
	public static boolean yearCompare(Date olddate, int year) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.YEAR, -year);
		if (olddate.before(gc.getTime())) {
			return true;
		}
		return false;
	}


	// date类型转换为String类型
	// formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
	// data Date类型的时间
	public static String dateToString(Date data, String formatType) {
		return new SimpleDateFormat(formatType).format(data);
	}

	// long类型转换为String类型
	// currentTime要转换的long类型的时间
	// formatType要转换的string类型的时间格式
	public static String longToString(long currentTime, String formatType)
			throws ParseException {
		Date date = longToDate(currentTime, formatType); // long类型转成Date类型
		String strTime = dateToString(date, formatType); // date类型转成String
		return strTime;
	}

	// string类型转换为date类型
	// strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
	// HH时mm分ss秒，
	// strTime的时间格式必须要与formatType的时间格式相同
	public static Date stringToDate(String strTime, String formatType)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		Date date = null;
		date = formatter.parse(strTime);
		return date;
	}

	// long转换为Date类型
	// currentTime要转换的long类型的时间
	// formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
	public static Date longToDate(long currentTime, String formatType)
			throws ParseException {
		Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
		String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
		Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
		return date;
	}

	// string类型转换为long类型
	// strTime要转换的String类型的时间
	// formatType时间格式
	// strTime的时间格式和formatType的时间格式必须相同
	public static long stringToLong(String strTime, String formatType)
			throws ParseException {
		Date date = stringToDate(strTime, formatType); // String类型转成date类型
		if (date == null) {
			return 0;
		} else {
			long currentTime = dateToLong(date); // date类型转成long类型
			return currentTime;
		}
	}

	// date类型转换为long类型
	// date要转换的date类型的时间
	public static long dateToLong(Date date) {
		return date.getTime();
	}

}
