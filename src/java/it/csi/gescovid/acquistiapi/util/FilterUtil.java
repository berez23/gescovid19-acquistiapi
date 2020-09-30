package it.csi.gescovid.acquistiapi.util;

import it.csi.gescovid.acquistiapi.dto.test.StringCrit;

public class FilterUtil {
	
	/**
	 * valuta un generico criterio stringa
	 * @param crit il criterio
	 * @param val il valore da valutare
	 * @return
	 */
	public static boolean evalStringCrit(StringCrit crit, String val) {
		if (crit.getC()!=null) {
			return evalStringCritC(crit.getC(), val, false);
		}
		else if (crit.getCi()!=null) {
			return evalStringCritC(crit.getCi(), val, true);
		}
		else if (crit.getE()!=null) {
			return evalStringCritE(crit.getE(), val, false);
		}
		else if (crit.getEq()!=null) {
			return evalStringCritEq(crit.getEq(), val);
		}
		else if (crit.getGt()!=null) {
			throw new UnsupportedOperationException("string_crit.Gt() not supported");
		}
		else if (crit.getGte()!=null) {
			throw new UnsupportedOperationException("string_crit.Gte() not supported");
		}
		else if (crit.getIn()!=null && !crit.getIn().isEmpty()) {
			throw new UnsupportedOperationException("string_crit.In() not supported");
		}
		else if (crit.getLt()!=null) {
			throw new UnsupportedOperationException("string_crit.Lt() not supported");
		}
		else if (crit.getLte()!=null) {
			throw new UnsupportedOperationException("string_crit.Lte() not supported");
		}
		else if (crit.getNe()!=null) {
			return !evalStringCritEq(crit.getEq(), val);
		}
		else if (crit.getNin()!=null && !crit.getNin().isEmpty()) {
			throw new UnsupportedOperationException("string_crit.Nin() not supported");
		}
		else if (crit.getS()!=null) {
			return evalStringCritS(crit.getS(), val, false);
		}
		else if (crit.getSi()!=null) {
			return evalStringCritS(crit.getSi(), val, true);
		}
		else throw new UnsupportedOperationException("string_crit.??() not supported");
	}

	private static boolean evalStringCritS(String crit, String val, boolean ignoreCase) {
		if (!ignoreCase) {
			if (val == null) {
				return false;
			}
			else {
				return val.startsWith(crit);
			}
		}
		else {
			if (val == null) {
				return false;
			}
			else {
				return val.toUpperCase().startsWith(crit.toUpperCase());
			}
		}
	}

	private static boolean evalStringCritE(String crit, String val, boolean ignoreCase) {
		if (!ignoreCase) {
			if (val == null) {
				return false;
			}
			else {
				return val.endsWith(crit);
			}
		}
		else {
			if (val == null) {
				return false;
			}
			else {
				return val.toUpperCase().endsWith(crit.toUpperCase());
			}
		}
	}

	private static boolean evalStringCritC(String crit, String val, boolean ignoreCase) {
		if (!ignoreCase) {
			return crit.indexOf(val)>-1;
		}
		else {
			if (val == null) {
				return false;
			}
			else {
				return crit.toUpperCase().indexOf(val.toUpperCase())>-1;
			}
		}
	}

	private static boolean evalStringCritEq(String eq, String val) {
		return val != null && val.equals(eq);
	}
}
