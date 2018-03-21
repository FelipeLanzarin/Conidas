package br.convidas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvidasUtils {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static String alphabetUpperCase[] = {"A","B","C","D","E","F","G"
												,"H","I","J","K","L","M","N"
												,"O","P","Q","R","S","T","U"
												,"V","W","X","Y","Z"};
	public static String alphabetLowerCase[] = {"a","b","c","d","e","f","g"
												,"h","i","j","k","l","m","n"
												,"o","p","q","r","s","t","u"
												,"v","w","x","y","z"};
	
	public static String getStringByDate(Date date){
		if(date != null){
			return sdf.format(date);
		}
		return "";
	}
}
