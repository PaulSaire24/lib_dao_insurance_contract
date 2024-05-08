package com.bbva.pisd.lib.r226.util;

public enum Properties {

    QUERY_SELECT_INSRC_CONTRACT_DETAIL("PISD.SELECT_INSURANCE_CONTRACT_DETAIL"),

    QUERY_SELECT_INSRC_RECEIPTS_DETAIL("PISD.SELECT_INSURANCE_RECEIPTS_DETAIL");

    private final String value;

    public String getValue() {
        return this.value;
    }

    private Properties(String value) { this.value = value; }

}
