package com.example.nazir.homework4;

import java.util.Date;

/**
 * Created by nazir on 10.08.2017.
 */

enum cPolisTypes {
    polisOsago,
    polisKasko,
    polisFlat,
    polisAid
}

public class PolisEntity {
    private cPolisTypes polisType;
    private String      polisNumber;
    private String      polisObject;
    private Date        polisStart;
    private Date        polisEnd;

    public PolisEntity(cPolisTypes type, String number, String object, Date date_start, Date date_stop) {
        polisType   = type;
        polisNumber = number;
        polisObject = object;
        polisStart  = date_start;
        polisEnd    = date_stop;
    }

    public cPolisTypes getPolisType() {
        return polisType;
    }

    public void setPolisType(cPolisTypes polis_Type) {
        polisType = polis_Type;
    }

    public String getPolisNumber() {
        return polisNumber;
    }

    public void setPolisNumber(String polis_Number) {
        polisNumber = polis_Number;
    }

    public String getPolisObject() {
        return polisObject;
    }

    public void setPolisObject(String polis_Object) {
        polisObject = polis_Object;
    }

    public Date getPolisStart() {
        return polisStart;
    }

    public void setPolisStart(Date polis_Start) {
        polisStart = polis_Start;
    }

    public Date getPolisEnd() {
        return polisEnd;
    }

    public void setPolisEnd(Date polis_End) {
        polisEnd = polis_End;
    }

    public int countDaysBetween(Date D1, Date D2) {
        if ((D1 == null) || (D2 == null))   return 0;

        long diff_ost = D2.getTime() - D1.getTime();
        return (int) (diff_ost / (3600000 * 24));
    }
}
