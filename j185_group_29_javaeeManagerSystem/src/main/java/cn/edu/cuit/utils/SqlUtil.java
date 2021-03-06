package cn.edu.cuit.utils;

public class SqlUtil {

public static String getPageSql(int page,int recordOfPage) {

		return " limit "+((page-1)*recordOfPage)+" , "+recordOfPage;
	}
}
