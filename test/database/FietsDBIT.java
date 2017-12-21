/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import databag.Fiets;
import datatype.Standplaats;
import datatype.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arnel
 */
public class FietsDBIT {

    private Fiets fiets;

    public FietsDBIT() {
    }

    @Before
    public void setUp() {

        fiets = new Fiets();

        fiets.setStatus(Status.actief);
        fiets.setStandplaats(Standplaats.Tielt);
        fiets.setOpmerking("Dit is de allereerste fiets, die straks verwijdert zal worden.");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toevoegenFiets method, of class FietsDB.
     */
    @Test
    public void testToevoegenFiets() throws Exception {

        try {
            FietsDB fCon=new FietsDB();
            
            

        } finally {

        }

    }

    /**
     * Test of wijzigenToestandFiets method, of class FietsDB.
     */
    @Test
    public void testWijzigenToestandFiets() throws Exception {
    }

    /**
     * Test of wijzigenOpmerkingFiets method, of class FietsDB.
     */
    @Test
    public void testWijzigenOpmerkingFiets() throws Exception {
    }

    /**
     * Test of zoekFiets method, of class FietsDB.
     */
    @Test
    public void testZoekFiets() throws Exception {
    }

    /**
     * Test of zoekAlleFietsen method, of class FietsDB.
     */
    @Test
    public void testZoekAlleFietsen() throws Exception {
    }

}
