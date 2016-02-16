package net.wasdev.jaxrs.async.fvt.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This tests that the correct timeout message is returned from various endpoints.
 */
@RunWith(Parameterized.class)
public class TestTimeouts extends EndpointTest {

    /**
     * @return The endpoints that are expected to return a timeout message
     */
    @Parameters(name="{0}")
    public static Collection<String> endpoints() {
        List<String> endpoints = new ArrayList<>();
        endpoints.add("ejbitemsTimeout");
        endpoints.add("cdiexecitemsTimeout");
        return endpoints;
    }

    public TestTimeouts(String endpoint) {
        super(endpoint);
    }

    @Override
    protected void assertResponseStringCorrect(String responseString) {
        assertTrue(responseString.startsWith("Backup plan!"));
    }
}
