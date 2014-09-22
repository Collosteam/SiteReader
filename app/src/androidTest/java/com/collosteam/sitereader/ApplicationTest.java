package com.collosteam.sitereader;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.collosteam.sitereader.data.Responceitem;
import com.google.gson.Gson;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    
    public ApplicationTest() {
        super(Application.class);
                    }


    public void testParceResponceJson() throws Exception {

        Gson gson = new Gson();
        String json = "{\"responce\":101}";
        Responceitem responceitem = gson.fromJson(json, Responceitem.class);
        assertEquals(101, responceitem.getId());

        String json2 = "{\"responce\":0}";
        Responceitem responceitem2 = gson.fromJson(json2, Responceitem.class);
        assertNotNull(responceitem2);




    }
}