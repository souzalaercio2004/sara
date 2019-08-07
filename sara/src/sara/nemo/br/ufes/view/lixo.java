package sara.nemo.br.ufes.view;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class lixo  {


	public static void main(String[] args) {
		String datax= "10/05/2011";
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dtDada= converteStringToLocalDateNoFormatoDDMMAAAA(datax, "dd/MM/yyyy");
		LocalDate data = LocalDate.parse(datax, formato);// Converte a String datax para data no formato "dd/MM/yyyy"
		System.out.println(dtDada);
		String mostrarData= converteLocalDateToString(data);// Converte LocalDate para String
		System.out.println(mostrarData);
		java.util.Date novaData;
		try {
			novaData = converteStringEmDate("dd/MM/yyyy", "15/05/1970");
			System.out.println("Nova data= "+ novaData);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dado= "10/02/2019";
		int dia = Integer.parseInt(dado.substring(0, 2));
		int mes= Integer.parseInt(dado.substring(3, 5));
		int ano= Integer.parseInt(dado.substring(6, 10));
		System.out.println("dia= "+ dia);
		System.out.println("mes= "+ mes);
		System.out.println("ano= "+ ano);

	}
	
 public static LocalDate converteStringToLocalDateNoFormatoDDMMAAAA(String data, String formato) {
	 DateTimeFormatter format = DateTimeFormatter.ofPattern(formato);
	 LocalDate dt = LocalDate.parse(data, format);
	 return (dt);
 }
 
 public static String converteLocalDateToString(LocalDate data) {
	 DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 
	 return(formato.format(data));
 }
 
 public static java.util.Date converteStringEmDate(final String formato, final String data) throws ParseException {

	    return new SimpleDateFormat(formato).parse(data);
	}

}
