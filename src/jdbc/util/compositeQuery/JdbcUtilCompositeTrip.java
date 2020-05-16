package jdbc.util.compositeQuery;

import java.util.*;


public class JdbcUtilCompositeTrip {
	public static String get_aCondition_For_Oracle(String columnName,String value) {
		String aCondition = null;
		if("trip_price".equals(columnName)||"mem_amount".equals(columnName)||"mem_limited".equals(columnName)||"current_mem".equals(columnName))
			aCondition = columnName+"<="+value;// 改為多少以內
		else if ("tour_name".equals(columnName)||"tour_detail".equals(columnName))
			aCondition = columnName+" like '%"+value+"%' or tour_detail like '%"+value+"%'";// 用於varchar 同時查詢行程名稱與行程細節的兩個欄位
		else if ("last_date".equals(columnName)||"reg_deadline".equals(columnName))
			aCondition = "to_char("+columnName+",'yyyy-mm-dd')<'"+value+"'";// 用於Oracle的date 小於查詢日期 用於結束日
		else if ("first_date".equals(columnName)||"reg_start".equals(columnName))
			aCondition = "to_char("+columnName+",'yyyy-mm-dd')>'"+value+"'";// 用於Oracle的date 大於查詢日期 用於開始日
		else if ("days".equals(columnName))
			switch (value) {
			case "0":
				aCondition = columnName + " < 4 ";// 用於天數1~3天
				break;
			case "1":
				aCondition = columnName + " > 3 and "+ columnName + " < 8 ";// 用於天數4~7天
				break;
			case "2":
				aCondition = columnName + " > 6 and "+ columnName + " < 15 ";// 用於天數7~14天
				break;
			case "3":
				aCondition = columnName + " > 14 ";// 用於天數15天以上
				break;
			}
			
		return aCondition+" ";
	}
	
	public static String get_WhereCondition(Map<String,String[]>map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		whereCondition.append(" where tour_status = 0 and current_mem < mem_limited and SYSDATE between reg_start and reg_deadline "); //先篩出上架及現在人數小於人數上限的,最後再去掉報名期間不含今天的團
		for(String key:keys) {
			String value = map.get(key)[0];
			if(value!=null&&value.trim().length()!=0&&!"action".equals(key)) {
				
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				
					whereCondition.append(" and "+aCondition);
				
				System.out.println("送出欄位數為:"+count);
			}
		}
		return whereCondition.toString();
	}
	

	public static void main (String[] args) {
		Map<String,String[]>map = new TreeMap<>();
//		map.put("trip_price", new String[] { "30000" });
//		map.put("days", new String[] { "5" });
//		map.put("first_date", new String[] { "2020-02-15" });
//		map.put("last_date", new String[] { "2020-05-30" });
//		map.put("tour_name", new String[] { "環球影城" });
		
		String finalSQL = "select * from trip_view "
		          + JdbcUtilCompositeTrip.get_WhereCondition(map)
		          + " order by first_date";
		System.out.println("●●finalSQL = " + finalSQL);
	}
}
