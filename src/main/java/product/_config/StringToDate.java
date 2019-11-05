package product._config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDate implements Converter<String, Date> {
	private final static SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat date2 = new SimpleDateFormat("yyyy/MM/dd");
	private final static SimpleDateFormat time1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private final static SimpleDateFormat time2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

	@Override
	public Date convert(String source) {
		System.out.println(source);
		if (null == source || source.isEmpty()) {
			return null;
		}
		boolean has_ = source.contains("-");
		boolean hasMH = source.contains(":");
		try {
			if (has_ && hasMH) {
				return time1.parse(source); // "yyyy-MM-dd hh:mm:ss
			} else if (has_) {
				return date1.parse(source); // yyyy-MM-dd
			} else if (source.contains("/") && hasMH) {
				return time2.parse(source); // yyyy/MM/dd hh:mm:ss
			} else if (source.contains("/")) {
				return date2.parse(source); // yyyy/MM/dd
			} else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
