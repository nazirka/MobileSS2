package com.example.nazir.homework4;

import java.util.Date;

/**
 * Created by nazir on 10.08.2017.
 */

public class PolisEntity {
    private int     PolisType;
    private String  PolisNumber;
    private String  PolisObject;
    private Date    PolisStart;
    private Date    PolisEnd;

    public PolisEntity(int type, String number, String object, Date date_start, Date date_stop) {
        PolisType   = type;
        PolisNumber = number;
        PolisObject = object;
        PolisStart  = date_start;
        PolisEnd    = date_stop;
    }

    public int getPolisType() {
        return PolisType;
    }

    public void setPolisType(int polisType) {
        PolisType = polisType;
    }

    public String getPolisNumber() {
        return PolisNumber;
    }

    public void setPolisNumber(String polisNumber) {
        PolisNumber = polisNumber;
    }

    public String getPolisObject() {
        return PolisObject;
    }

    public void setPolisObject(String polisObject) {
        PolisObject = polisObject;
    }

    public Date getPolisStart() {
        return PolisStart;
    }

    public void setPolisStart(Date polisStart) {
        PolisStart = polisStart;
    }

    public Date getPolisEnd() {
        return PolisEnd;
    }

    public void setPolisEnd(Date polisEnd) {
        PolisEnd = polisEnd;
    }
}
