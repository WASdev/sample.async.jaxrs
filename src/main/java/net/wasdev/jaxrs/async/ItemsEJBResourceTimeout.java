/*******************************************************************************
 * Copyright (c) 2015 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package net.wasdev.jaxrs.async;

import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ejbitemsTimeout")
public class ItemsEJBResourceTimeout implements TimeoutHandler{
    @EJB
    AsyncEJB asyncEJB;

    /**
     * For this example. we've moved the EJB with the @Asynchronous
     * annotation to a class that we call. In order to set a timeout on
     * the AsyncResponse, we need to be able to register the timeout
     * handler before the processing we care about (retrieval of items)
     * begins.
     *
     * @param ar
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getItems(@Suspended final AsyncResponse ar) {
        System.out.println("ejbitemsTimeout");
        ar.setTimeout(500, TimeUnit.MILLISECONDS);
        ar.setTimeoutHandler(this);

        ar.register(new CompletionCallback() {
            @Override
            public void onComplete(Throwable t) {
                System.out.println("DONE! ");
                if ( t != null ) {
                    t.printStackTrace();
                }
            }
        });

        ar.register(new ConnectionCallback() {
            @Override
            public void onDisconnect(AsyncResponse ar) {
                System.out.println("Disconnected: " + ar);
            }
        });

        asyncEJB.getItems(ar);
    }

    public void handleTimeout(AsyncResponse ar) {
        ar.resume(Response.ok("Backup plan! " + new Item().getId()).build());
    }
}
