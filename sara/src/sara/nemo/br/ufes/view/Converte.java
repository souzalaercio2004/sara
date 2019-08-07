package sara.nemo.br.ufes.view;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import sara.nemo.br.ufes.inf.domain.Frequencia;

public class Converte {
	public static LocalDate converteStringToLocalDateNoFormatoDDMMAAAA(String data, String formato) {
		 DateTimeFormatter format = DateTimeFormatter.ofPattern(formato);
		 LocalDate dt = LocalDate.parse(data, format);
		 return (dt);
	 }
	
	 public static String converteLocalDateToString(LocalDate data) {
		 DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 
		 return(formato.format(data));
	 }
	 
	public static Date converterStringToJavaSqlDate(String data) {
		java.sql.Date dt = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date parsed= null;
		try {
			parsed = format.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dt = new java.sql.Date(parsed.getTime());
		return (dt);
	}
	 public static java.util.Date converteStringEmDate(final String formato, final String data) throws ParseException {

	    return new SimpleDateFormat(formato).parse(data);
	}
	public static Date converterLocalDateToJavaSqlDate(LocalDate data) {
		java.sql.Date dt= java.sql.Date.valueOf(data);
		return (dt);
	}
	public static LocalDate converterJavaSqlDateToLocalDate(java.sql.Date date) {
		return (date.toLocalDate());
	}
	public static java.sql.Time converterLocalTimeToJavaSqlTime(LocalTime time) {
		return java.sql.Time.valueOf(time);
	}
	
	public static LocalTime converterJavaSqlTimeToLocalTime(java.sql.Time time) {
		return time.toLocalTime();
	}
	
	public static Frequencia frequenciaModelada(String []frequen) {
		Frequencia frequencia= new Frequencia();
		
		if (frequen[0]== "D") {
			frequencia.setDomingo(true);
		} else frequencia.setDomingo(false);
		
		if (frequen[1]== "S") {
			frequencia.setSegunda(true);
		} else frequencia.setSegunda(false);
		
		if (frequen[2]== "T") {
			frequencia.setTerca(true);
		} else frequencia.setTerca(false);
		
		if (frequen[3]== "Q") {
			frequencia.setQuarta(true);
		} else frequencia.setQuarta(false);
		
		if (frequen[4]== "Q") {
			frequencia.setSegunda(true);
		} else frequencia.setSegunda(false);
		
		if (frequen[5]== "S") {
			frequencia.setTerca(true);
		} else frequencia.setTerca(false);
		
		if (frequen[6]== "S") {
			frequencia.setQuarta(true);
		} else frequencia.setQuarta(false);
		
		return (frequencia);
	}
}
