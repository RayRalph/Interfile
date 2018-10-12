/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.integration.utils;

import java.time.LocalDateTime;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author raymod
 */
public class HandlerUtilsIT {
    
    public HandlerUtilsIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of convertToXmlGregorianDateUTC method, of class HandlerUtils.
     */
    @Test
    public void testConvertToXmlGregorianDateUTC() throws Exception {
        System.out.println("convertToXmlGregorianDateUTC");
        LocalDateTime currentDateTime = LocalDateTime.now();
        HandlerUtils instance = new HandlerUtils();
        XMLGregorianCalendar expResult = null;
        XMLGregorianCalendar result = instance.convertToXmlGregorianDateUTC(currentDateTime);
        assert(expResult != result);
    }

    /**
     * Test of convertToXmlGregorianDate method, of class HandlerUtils.
     */
    @Test
    public void testConvertToXmlGregorianDate() throws Exception {
        System.out.println("convertToXmlGregorianDate");
        LocalDateTime currentDateTime = LocalDateTime.now();
        HandlerUtils instance = new HandlerUtils();
        XMLGregorianCalendar expResult = null;
        XMLGregorianCalendar result = instance.convertToXmlGregorianDate(currentDateTime);
        assert(expResult!= result);
    }

    /**
     * Test of convertDateToXmlGregorian method, of class HandlerUtils.
     */
    @Test
    public void testConvertDateToXmlGregorian() throws Exception {
        System.out.println("convertDateToXmlGregorian");
        Date aDate = new Date(System.currentTimeMillis());
        HandlerUtils instance = new HandlerUtils();
        XMLGregorianCalendar expResult = null;
        XMLGregorianCalendar result = instance.convertDateToXmlGregorian(aDate);
        assert(expResult!= result);
    }


    /**
     * Test of returnPlaceHolderIfEmptyOrNull method, of class HandlerUtils.
     */
    @Test
    public void testReturnPlaceHolderIfEmptyOrNull() {
        System.out.println("returnPlaceHolderIfEmptyOrNull");
        String str = null;
        String expResult = "";
        String result = HandlerUtils.returnPlaceHolderIfEmptyOrNull(str);
        assert(!expResult.equals(result));
    }
    
}
