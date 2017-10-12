package net.wasdev.jaxrs.async.it.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runners.Parameterized;

/**
 * <p>This abstract base class will use the {@link Parameterized} JUnit runner to test various endpoints. Subclasses must:</p>
 * <ul>
 * <li>Provide endpoints relative to <code>http://localhost:{port}/jaxrs-async/test/</code> via a <code>@Parameters</code> annotated method</li>
 * <li>Implement the {@link #assertResponseStringCorrect(String)} template method to check the response string from a given endpoint</li>
 * </ul>
 */
public abstract class EndpointTest {

    private final String endpoint;
    
    public EndpointTest(String endpoint) {
        this.endpoint = endpoint;
    }
    
    @Test
    public void testEndpoint() {
        Client client = ClientBuilder.newClient();
        String port = System.getProperty("liberty.test.port");
        String url = "http://localhost:" + port + "/jaxrs-async/test/" + endpoint;
        System.out.println("Testing " + url);
        
        Response getResponse = client.target(url).request().accept(MediaType.APPLICATION_JSON).get();
        String responseString = getResponse.readEntity(String.class);
        System.out.println("Returned " + responseString);
        assertResponseStringCorrect(responseString);
    }
    
    /**
     * Template method to check the response string from an endpoint is correct.
     * 
     * @param responseString
     */
    protected abstract void assertResponseStringCorrect(String responseString);

}
