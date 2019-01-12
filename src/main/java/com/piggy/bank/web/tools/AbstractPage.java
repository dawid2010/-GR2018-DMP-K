package com.piggy.bank.web.tools;

import com.piggy.bank.web.exceptions.WrongArgumentException;

/**
 * Any class can extend this to provide pagination
 */
public abstract class AbstractPage {
    protected final int RESULTS_BY_PAGE = 15;

    /**
     * Check if pages is given and then parse it to integer
     *
     * @param page
     * @return integer representation of given string
     */
    protected Integer validateAndParsePageVariable(String page) {
        try {
            if (page != null && !page.isEmpty() && !page.equals("null")) {
                return Integer.parseInt(page);
            }
            return 0;
        } catch (Exception ex) {
            throw new WrongArgumentException();
        }
    }
}