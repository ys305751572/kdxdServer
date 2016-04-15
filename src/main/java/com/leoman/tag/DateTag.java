package com.leoman.tag;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 时间格式化标签
 * @author yesong
 *
 */
public class DateTag extends TagSupport {

	private static final long serialVersionUID = 7616105715060229557L;

	private String value;

	private String format;

	@Override
	public int doStartTag() throws JspException {
		String vv = ""+ value ;
		if(!StringUtils.isNotBlank(vv)) {
			return super.doStartTag();
		}
		long time = Long.valueOf(vv);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		if(StringUtils.isBlank(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		String s = dateformat.format(c.getTime());
		try {
			pageContext.getOut().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
