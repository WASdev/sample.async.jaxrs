# Java EE7: JAX-RS 2.0 Async Request processing [![Build Status](https://travis-ci.org/WASdev/sample.async.jaxrs.svg?branch=master)](https://travis-ci.org/WASdev/sample.async.jaxrs)

Java EE7 added support for asynchronous request processing of REST requests in JAX-RS 2.0.

This sample contains a few variations to illustrate how to use async request processing in JAX-RS 2.0 applications. It is organized around a generic `Item`, that is manipulated by an `ItemService` that happens to take a long time (it sleeps for a few seconds). Each variation copes with this slow service in a different way, to illustrate how JAX-RS Async requests work and how Concurrency Utilities and EJBs can be used to offload work to other threads while keeping the EE container happy.

* *Items*: [ItemsResource](/src/main/java/net/wasdev/jaxrs/async/ItemsResource.java) uses the `ItemService` to list, add, and fetch items, in the usual (synchronous) way.
* *Async Items*: [ItemsAsyncResource](/src/main/java/net/wasdev/jaxrs/async/ItemsAsyncResource.java) uses the `@Suspended` annotation with the `AsyncResponse` parameter, however, it is still essentially synchronous, as the work is not offloaded to a different thread.
* *Async EJB Items*: [ItemsEJBResource](/src/main/java/net/wasdev/jaxrs/async/ItemsEJBResource.java) defines a stateless EJB, which allows work offloading with the addition of the `@Asynchronous` annotation.
* *Async Items with Executor*: [ItemsExecutorResource](/src/main/java/net/wasdev/jaxrs/async/ItemsExecutorResource.java) looks up the `ManagedExecutorService` defined by EE7 Concurrency Utilities in JNDI. Work for the request is then submitted to the executor and resumed later.
* *Async Items with CDI-provided Executor*: [ItemsCDIExecutorResource](/src/main/java/net/wasdev/jaxrs/async/ItemsCDIExecutorResource.java) uses the `ManagedExecutorService` injected as an @Resource.

`AsyncResponse` objects can be configured to handle timeouts. There are two examples showing that:
* *Async with CDI-provided Executor and Timeout*: [ItemsCDIExecutorResourceTimeout](/src/main/java/net/wasdev/jaxrs/async/ItemsCDIExecutorResourceTimeout.java) sets a timeout, and registers a timeout handler, a connection callback, and a completion callback. Work is queued to a separate thread via a CDI-injected ManagedExecutorService.
* *Async EJB Items with Timeout*: [ItemsEJBResourceTimeout](/src/main/java/net/wasdev/jaxrs/async/ItemsEJBResourceTimeout.java) does the same as the previous example, but uses an asynchronous Stateless EJB instead.

## Getting Started

Browse the code to see what it does, or build and run it yourself:
* [Building and running on the command line](/docs/Using-cmd-line.md)
* [Building and running using Eclipse and WebSphere Development Tools (WDT)](/docs/Using-WDT.md)

In your browser, enter the URL for the application: [http://localhost:9080/jaxrs-async/](http://localhost:9080/async-jaxrs/) (where port 9080 assumes the httpEndpoint provided in the sample server.xml has not been modified).

## More on JAX-RS 2.0 and related technologies      
 -* [JSR 339: JAX-RS 2.0](https://jcp.org/en/jsr/detail?id=339)     
 -* [JSR 166: Concurrency Utilities](https://jcp.org/en/jsr/detail?id=166)      
 -* [JSR 345: Enterprise JavaBeansTM 3.2](https://jcp.org/en/jsr/detail?id=345)

## Notice

© Copyright IBM Corporation 2015, 2017.

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