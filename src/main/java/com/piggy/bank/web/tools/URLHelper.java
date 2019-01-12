package com.piggy.bank.web.tools;

import org.springframework.stereotype.Component;

/**
 * Class that helps out with URLs
 */
@Component
public class URLHelper {

    /**
     * Split given url by slash and remove last 'x' items of it and then construct new ulr without this last items
     *
     * @param url
     * @param numberOfItems
     * @return new url without last items
     */
    public String removeLastItemsSplittedBySlash(String url, int numberOfItems) {
        String[] baseUrlSplitted = url.split("/");
        StringBuffer newUrl = new StringBuffer(url);
        for (int i = 0; i < baseUrlSplitted.length - numberOfItems; i++) {
            newUrl.append(baseUrlSplitted[i]);
            if (i < baseUrlSplitted.length - (numberOfItems + 1)) {
                newUrl.append("/");
            }
        }
        return newUrl.toString();
    }
}
