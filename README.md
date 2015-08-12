# Java EE7: JAX-RS 2.0 Async Request processing

Java EE7 added support for asynchronous request processing of REST requests in JAX-RS 2.0.


This sample contains a few variations to illustrate how to use async request processing in JAX-RS 2.0 applications. It is organized around a generic `Item`, that is manipulated by an `ItemService` that happens to take a long time (it sleeps for a few seconds). Each variation copes with this slow service in a different way, to illustrate how JAX-RS Async requests work and how Concurrency Utilities and EJBs can be used to offload work to other threads while keeping the EE container happy.

* ItemsResource uses the `ItemService` to list, add, and fetch items, in the usual (synchronous) way.
* ItemsAsyncResource uses the `@Suspended` annotation with the `AsyncResponse` parameter, however, it is still essentially synchronous, as the work is not offloaded to a different thread.
* ItemsEJBResource defines a stateless EJB, which allows work offloading with the addition of the `@Asynchronous` annotation.
* ItemsExecutorResource looks up the `ManagedExecutorService` defined by EE7 Concurrency Utilities in JNDI. Work for the request is then submitted to the executor and resumed later.
* ItemsCDIExecutorResource uses the `ManagedExecutorService` injected as an @Resource.

`AsyncResponse` objects can be configured to handle timeouts. There are two examples showing that:
* ItemsCDIExecutorResourceTimeout sets a timeout, and registers a timeout handler, a connection callback, and a completion callback. Work is queued to a separate thread via a CDI-injected ManagedExecutorService.
* ItemsEJBResourceTimeout does the same as the previous example, but uses an asynchronous Stateless EJB instead.


Browse the code to see what it does, or build and run it yourself:
* [Building with Gradle](/docs/Building-the-sample.md#building-with-gradle)
* [Building with maven](/docs/Building-the-sample.md#building-with-maven)
* [Downloading WAS Liberty](/docs/Downloading-WAS-Liberty.md)
* [Start the server using the command line, or maven/gradle plugins](/docs/Starting-the-server.md)
* [Using Eclipse and WebSphere Development Tools (WDT)](/docs/Using-WDT.md)

Once the server has been started, go to [http://localhost:9081/jaxrs-async/](http://localhost:9081/jaxrs-async/) to interact with the sample.


## More on JAX-RS 2.0 and related technologies
* [JSR 339: JAX-RS 2.0](https://jcp.org/en/jsr/detail?id=339)
* [JSR 166: Concurrency Utilities](https://jcp.org/en/jsr/detail?id=166)
* [JSR 345: Enterprise JavaBeansTM 3.2](https://jcp.org/en/jsr/detail?id=345)

## Notice

© Copyright IBM Corporation 2015.

## License

```text
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
````
