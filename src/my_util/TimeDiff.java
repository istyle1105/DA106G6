package my_util;

import java.sql.*;
import java.util.Calendar;


public class TimeDiff {
	public static int getTimeDiffToDays(Timestamp start,Timestamp end ) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTimeInMillis(start.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(end.getTime());
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		// 獲得這一天在是這一年的第多少天
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		if (year1 != year2) // 不同一年
		{
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
					timeDistance += 366; // 閏年
				} else {
					timeDistance += 365; // 不是閏年
				}
			}
			return timeDistance + (day2 - day1)+1;
		} else {
//			System.out.println("判斷day2 - day1 : " + (day2 - day1));
			return day2 - day1+1; // 同年
		}
	}
		
	
	
	public static void main(String[]args) {
		int test = getTimeDiffToDays(new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()+1000*60*60*5));
		System.out.println(test);
	}
	
}
