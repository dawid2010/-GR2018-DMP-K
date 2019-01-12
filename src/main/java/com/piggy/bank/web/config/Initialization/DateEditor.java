package com.piggy.bank.web.config.Initialization;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Help for date problems
 */
public class DateEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        try {
            setValue(new SimpleDateFormat("yyyy-MM-dd").parse(text));
        } catch (ParseException e) {
            setValue(null);
        }
    }

    @Override
    public String getAsText() {
        String s = "";
        if (getValue() != null) {
            s = new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
        }
        return s;
    }
}
