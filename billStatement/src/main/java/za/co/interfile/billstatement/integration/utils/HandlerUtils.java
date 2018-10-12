/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.integration.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author raymond
 */
public class HandlerUtils {

    protected static final String STRING_PLACEHOLDER = "NONE";

    protected static final String PHONE_NUMBER_PLACEHOLDER = "+111-1111111111";

    protected int utcOffset = 0;

    protected XMLGregorianCalendar convertToXmlGregorianDateUTC(LocalDateTime currentDateTime)
            throws DatatypeConfigurationException {
        GregorianCalendar gcal = GregorianCalendar.from(currentDateTime.atZone(ZoneId.of("Z")));
        XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        xmlCal.setTimezone(0);
        return xmlCal;

    }

    protected XMLGregorianCalendar convertToXmlGregorianDate(LocalDateTime currentDateTime)
            throws DatatypeConfigurationException {
        GregorianCalendar gcal = GregorianCalendar.from(currentDateTime.atZone(ZoneId.systemDefault()));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

    }

    protected XMLGregorianCalendar convertDateToXmlGregorian(Date aDate) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(aDate);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }
    
    public static Date convertXmlGregorianToDate(XMLGregorianCalendar xDate){
        GregorianCalendar c = xDate.toGregorianCalendar();
        return c.getTime();
    }


    private String removePrologTag(String xmlString) {
        return xmlString.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
    }

    private static boolean isNeitherNullNorEmptyString(String str) {

        return str != null && !str.isEmpty();
    }
    public static String returnPlaceHolderIfEmptyOrNull(String str) {
        return isNeitherNullNorEmptyString(str) ? str : STRING_PLACEHOLDER;
    }

}
