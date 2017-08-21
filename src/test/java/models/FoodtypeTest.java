package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodtypeTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNameReturnsCorrectName() throws Exception {
        Foodtype testFoodtype = setupFoodtype();
        assertEquals("Chinese", testFoodtype.getName());
    }

    // helpers

    public Foodtype setupFoodtype() {
        return new Foodtype("Chinese");
    }

    public Foodtype setupAltFoodtype() {
        return new Foodtype("Scottish");
    }

}