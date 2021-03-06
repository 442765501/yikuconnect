package com.yiku.common.util;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类DateEditor.java的实现描述：SPRINGMVC 绑定DATE参数
 * @author ssl 2015年8月20日 下午2:10:51
 */
public class DateEditor extends PropertyEditorSupport {

    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DateFormat dateFormat;
    private boolean allowEmpty = true;

    public DateEditor() {
    }

    public DateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            // Treat empty String as null title.
            setValue(null);
        } else {
            try {
                if(this.dateFormat != null) {
                    setValue(this.dateFormat.parse(text));
                } else {
                    if(text.contains(":")) {
                        setValue(TIMEFORMAT.parse(text));
                    } else {
                        setValue(DATEFORMAT.parse(text));
                    }
                }
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        DateFormat dateFormat = this.dateFormat;
        if(dateFormat == null) {
            dateFormat = TIMEFORMAT;
        }
        return (value != null ? dateFormat.format(value) : "");
    }


}
