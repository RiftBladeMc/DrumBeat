package com.drumbeatbillings.drumbeat.datatypes;

import java.text.DateFormat;
import java.util.Date;

public class Slip {
    private long date;
    private int time;
    private final Client client;
    Slip(Client client, long date, int time) {
        this.time = time;
        this.client = client;
    }

    public String getDate() {
        return DateFormat.getDateInstance(DateFormat.LONG).format(new Date(date));
    }
}
