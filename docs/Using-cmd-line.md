### Running with Maven command-line

This project can be built with [Apache Maven]. The project uses [Liberty Maven Plug-in] to automatically download and install the Liberty Java EE 7 Full Platform runtime from [Maven Central]. Liberty Maven Plug-in is also used to create, configure, and run the application on the Liberty server. 

Use the following steps to run the application with Maven:

1. Execute the full Maven build. The Liberty Maven Plug-in will download and install the Liberty runtime and create the server.
    ```bash
    $ mvn clean install
    ```
2. To run the server with the Spring application execute:
    ```bash
    $ mvn liberty:run-server
    ```

### Running with Gradle command-line

This project can be built with [Gradle]. The project uses [Liberty Gradle Plug-in] to automatically download and install Liberty with Java EE 7 Full Platform runtime from [Maven Central]. Liberty Gradle Plug-in is also used to create, configure, and run the application on the Liberty server. 

Use the following steps to run the application with Gradle:

1. Execute full Gradle build. This will cause Liberty Gradle Plug-in to download and install Liberty profile server.
    ```bash
    $ gradle clean build
    ```
    
2. To run the server with the Spring application execute:
    ```bash
    $ gradle libertyStart
    ```
        
3. To stop the server with the Spring application execute:
    ```bash
    $ gradle libertyStop
    ```

[Liberty Maven Plug-in]: https://github.com/WASdev/ci.maven
[Liberty Gradle Plug-in]: https://github.com/WASdev/ci.gradle

[Apache Maven]: http://maven.apache.org
[Gradle]: https://gradle.org/

[Maven Central]: https://search.maven.org/