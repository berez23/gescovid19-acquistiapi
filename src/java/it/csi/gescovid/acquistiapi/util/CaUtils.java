package it.csi.gescovid.acquistiapi.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;

public class CaUtils {

	public static boolean isEmpty(String valore) {
		if (valore == null) {
			return true;
		}

		if (valore.trim().length() == 0) {
			return true;
		}

		return false;
	}

	public static boolean isNotEmpty(String valore) {
		return !isEmpty(valore);
	}

	public static BigDecimal toBigDecimal(Integer numero) {
		if (numero == null) {
			return null;
		}

		return new BigDecimal(numero.intValue());
	}

	public static BigDecimal toBigDecimal(Boolean numero) {
		if (numero == null) {
			return null;
		}

		return new BigDecimal(numero ? 1 : 0);
	}

	public static BigDecimal toBigDecimal(boolean numero) {

		return new BigDecimal(numero ? 1 : 0);
	}

	public static BigDecimal toBigDecimal(String numero) {
		if (CaUtils.isEmpty(numero)) {
			return null;
		}

		return new BigDecimal(numero);
	}

	public static Integer toInteger(BigDecimal numero) {
		if (numero == null) {
			return null;
		}

		return new Integer(numero.intValue());
	}

	public static Integer toInteger(Long numero) {
		if (numero == null) {
			return null;
		}

		return new Integer(numero.intValue());
	}

	public static Integer toInteger(String numero) {
		if (CaUtils.isEmpty(numero)) {
			return null;
		}

		return new Integer(numero);
	}

	public static boolean toBoolean(BigDecimal numero) {
		if (numero == null) {
			return false;
		}

		return (numero.intValue() == 0) ? false : true;
	}

	public static boolean isEqual(String s1, String s2) {
		if (isEmpty(s1) && isEmpty(s2)) {
			return true;
		}
		if (isEmpty(s1) && isNotEmpty(s2)) {
			return false;
		}
		if (isNotEmpty(s1) && isEmpty(s2)) {
			return false;
		}
		if (isNotEmpty(s1) && isNotEmpty(s2)) {
			if (s1.equalsIgnoreCase(s2)) {
				return true;
			}
			return false;
		}

		return true;
	}

	public static boolean isNotEqual(String s1, String s2) {
		return !isEqual(s1, s2);
	}

	public static boolean isEqual(BigDecimal s1, BigDecimal s2) {
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1 == null && s2 != null) {
			return false;
		}
		if (s1 != null && s2 == null) {
			return false;
		}
		if (s1 != null && s2 != null) {
			if (s1.equals(s2)) {
				return true;
			}
			return false;
		}

		return true;
	}

	public static boolean isNotEqual(BigDecimal s1, BigDecimal s2) {
		return !isEqual(s1, s2);
	}

	public static String toString(BigDecimal number) {
		if (number == null) {
			return null;
		}

		return number.toString();
	}

	public static String toString(Integer number) {
		if (number == null) {
			return null;
		}

		return number.toString();
	}

	public static String toString(Long number) {
		if (number == null) {
			return null;
		}

		return number.toString();
	}

	public static Timestamp timestamp(String data, Integer ora, Integer minuti) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DAY_OF_MONTH, new Integer(data.substring(0, 2)));
		cal.set(Calendar.MONTH, new Integer(data.substring(3, 5)) - 1);
		cal.set(Calendar.YEAR, new Integer(data.substring(6, 10)));
		cal.set(Calendar.HOUR_OF_DAY, ora);
		cal.set(Calendar.MINUTE, minuti);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new Timestamp(cal.getTimeInMillis());
	}

	public static String toString(Timestamp ts) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (ts != null) {
			return sdf.format(new Date(ts.getTime()));
		}
		return "";
	}

	public static String toString(java.sql.Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.format(data);
		return sdf.format(data);
	}

	public static java.sql.Date toSqlDate(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date parse = sdf.parse(data);
			return new java.sql.Date(parse.getTime());
		} catch (ParseException e) {
			throw e;
		}
	}

	public static java.sql.Date toSqlDatePasswordExpirationFormat(String output) throws ParseException {
		try {

			StringBuilder sb = new StringBuilder();

			sb.append(output.substring(0, 4));
			sb.append("-");
			sb.append(output.substring(4, 6));
			sb.append("-");
			sb.append(output.substring(6, 8));
			sb.append("T");
			sb.append(output.substring(8, 10));
			sb.append(":");
			sb.append(output.substring(10, 12));
			sb.append(":");
			sb.append(output.substring(12, 14));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ITALY);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date parse = sdf.parse(sb.toString());
			return new java.sql.Date(parse.getTime());
		} catch (ParseException e) {
			throw e;
		}
	}

	public static String toStringTrattino(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if (date != null) {
			return sdf.format(date);
		}
		return "";
	}

	public static String toDateyyyymmdd(String data) {
		try {
			if (data != null) {
				String giorno = data.substring(0, 2);
				String mese = data.substring(3, 5);
				String anno = data.substring(6, 10);

				StringBuilder sb = new StringBuilder();
				sb.append(anno);
				sb.append("-");
				sb.append(mese);
				sb.append("-");
				sb.append(giorno);

				return sb.toString();
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static Integer differenza(Integer minuendo, Integer sottraendo) {
		if (minuendo == null) {
			minuendo = new Integer(0);
		}

		if (sottraendo == null) {
			sottraendo = new Integer(0);
		}

		return new Integer(minuendo.intValue() - sottraendo.intValue());
	}

	public static Integer somma(Integer addendo1, Integer addendo2) {
		if (addendo1 == null) {
			addendo1 = new Integer(0);
		}

		if (addendo2 == null) {
			addendo2 = new Integer(0);
		}

		return new Integer(addendo1.intValue() + addendo2.intValue());
	}

	public static Timestamp converti(Date date) {

		return new Timestamp(date.getTime());
	}

	public static String convertiData(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	public static String convertiData(Date date, String formato) {

		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(date);
	}

	public static void main(String[] args) {

		System.out.println(convertiData(new Date(), "yyyy-MM-dd"));
	}

}
