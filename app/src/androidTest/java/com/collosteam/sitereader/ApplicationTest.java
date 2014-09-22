package com.collosteam.sitereader;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.collosteam.sitereader.data.ResponseItem;
import com.google.gson.Gson;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testParseResponseJson() throws Exception {

        Gson gson = new Gson();

        String json = "{\"response\":101}";

        ResponseItem responseItem = gson.fromJson(json, ResponseItem.class);

        assertEquals(101, responseItem.getId());


        String json2 = "{\"response\":0}";

        ResponseItem responseItem2 = gson.fromJson(json2, ResponseItem.class);

        assertNotNull(responseItem2);
    }
}