package com.piggy.bank.web.tools;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class that provides help for creating json
 */
@Component
public class JSONHelper {
    /**
     * @return new JSON
     */
    public JSON createJson() {
        return new JSON(new StringBuffer());
    }

    /**
     * Class that is representation for json with one string buffer field
     */
    public class JSON {
        private StringBuffer json;

        private JSON(StringBuffer json) {
            this.json = json;
        }

        /**
         * @param key
         * @param value
         * @return json with new field
         */
        public JSON addField(String key, String value) {
            if (!isFirstItem()) {
                json.append(",");
            }
            value = replaceSpecialCharacters(value);
            json.append("\"").append(key).append("\"")
                    .append(": ")
                    .append("\"").append(value).append("\"");
            return this;
        }

        /**
         * @param key
         * @param values
         * @return json with new table
         */
        public JSON addTable(String key, List<String> values) {
            if (values == null || values.isEmpty()) {
                return this;
            }
            if (!isFirstItem()) {
                json.append(",");
            }
            json.append("\"").append(key).append("\"")
                    .append(": ")
                    .append("[");
            int currentIndex = 0;
            for (String value : values) {
                value = replaceSpecialCharacters(value);
                json.append("\"").append(value).append("\"");
                if (currentIndex < values.size() - 1) {
                    json.append(",");
                    currentIndex++;
                } else {
                    json.append("]");
                }
            }
            return this;
        }

        @Override
        public String toString() {
            return new StringBuffer().append("{").append(json).append("}").toString();
        }

        private boolean isFirstItem() {
            return json.toString().equals("");
        }

        private String replaceSpecialCharacters(String value){
            return StringEscapeUtils.escapeJava(value);
        }
    }
}
