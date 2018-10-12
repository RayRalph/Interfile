/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author raymod
 */
public class FileUploadControllerIT {
    
    public FileUploadControllerIT() {
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
     * Test of getFile method, of class FileUploadController.
     */
    @Test
    public void testGetFile() {
        System.out.println("getFile");
        FileUploadController instance = new FileUploadController();
        UploadedFile expResult = null;
        UploadedFile result = instance.getFile();
        assert(expResult == result);
    }


    /**
     * Test of isFileSelected method, of class FileUploadController.
     */
    @Test
    public void testIsFileSelected() {
        System.out.println("isFileSelected");
        FileUploadController instance = new FileUploadController();
        boolean expResult = false;
        boolean result = instance.isFileSelected();
        assert(expResult ==result);
    }
    
}
