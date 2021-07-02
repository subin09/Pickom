package edu.kh.semi.crawler.model.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String[] args) {
		
		String sDate = "20001010";
		
		
		SimpleDateFormat format= new SimpleDateFormat("yyyyMMdd");
		try {
			Date uDate = format.parse(sDate);
			
			java.sql.Date qlDate = new java.sql.Date(uDate.getTime());
			
			
			System.out.println(sDate);
			System.out.println(uDate);
			System.out.println(qlDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
}
