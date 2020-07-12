package ru.yakimovvn.tick_tack.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class SystemInUtilsTest {

    private SystemInUtils inUtils;

    private final int NUMB = 1;


    private final String TEST_MSG = "Test msg";

    private final ByteArrayInputStream BYTE_INT_IN = new ByteArrayInputStream(String.valueOf(NUMB).getBytes());

    private final String SMB_Y = "Y";
    private final String SMB_N = "N";

    private final ByteArrayInputStream BYTE_STR_IN_Y = new ByteArrayInputStream(SMB_Y.getBytes());
    private final ByteArrayInputStream BYTE_STR_IN_N = new ByteArrayInputStream(SMB_N.getBytes());



    @Before
    public void init(){
        inUtils = SystemInUtils.getInstance();
    }

    @Test
    public void askInt() {
        System.setIn(BYTE_INT_IN);
        assertEquals(NUMB, inUtils.askInt(TEST_MSG, NUMB -1  , NUMB + 1));
    }


    @Test
    public void askTwoSymbolsY() {
        System.setIn(BYTE_STR_IN_Y);
        assertTrue(inUtils.askTwoSymbols(TEST_MSG, SMB_Y, SMB_N));
    }

    @Test
    public void askTwoSymbolsN() {
        System.setIn(BYTE_STR_IN_N);
        assertFalse(inUtils.askTwoSymbols(TEST_MSG, SMB_Y, SMB_N));
    }

    @After
    public void closeSource(){
        inUtils.close();
    }
}