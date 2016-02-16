package net.wasdev.jaxrs.async.fvt.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This tests that two items are returned with an ID from various endpoints.
 */
@RunWith(Parameterized.class)
public class TestItems extends EndpointTest {

    /**
     * @return The endpoints that are expected to return a pair of items
     */
    @Parameters(name="{0}")
    public static Collection<String> endpoints() {
        List<String> endpoints = new ArrayList<>();
        endpoints.add("items");
        endpoints.add("asyncitems");
        endpoints.add("ejbitems");
        endpoints.add("execitems");
        endpoints.add("cdiexecitems");
        return endpoints;
    }

    public TestItems(String endpoint) {
        super(endpoint);
    }

    @Override
    protected void assertResponseStringCorrect(String responseString) {
        JsonReader reader = Json.createReader(new StringReader(responseString));
        JsonStructure jsonStructure = reader.read();
        assertTrue(jsonStructure instanceof JsonArray);
        JsonArray jsonArray = (JsonArray) jsonStructure;
        assertEquals(2, jsonArray.size());
        for (JsonValue jsonValue : jsonArray) {
            assertTrue(jsonValue instanceof JsonObject);
            JsonObject jsonObject = (JsonObject) jsonValue;
            assertTrue(jsonObject.containsKey("id"));
        }
    }
    
}
