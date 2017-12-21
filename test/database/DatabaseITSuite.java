/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author arnel
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({database.RitDBIT.class, database.LidDBIT.class, database.FietsDBIT.class, database.connect.ConnectITSuite.class})
public class DatabaseITSuite {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
