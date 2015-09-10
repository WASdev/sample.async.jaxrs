## Building and running the sample using the command line

### Clone Git Repo
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#clone-git-repo)

```bash

$ git clone https://github.com/WASdev/sample.async.jaxrs.git

```

### Building the sample
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#building-the-sample-in-eclipse)

This sample can be built using either [Gradle](#gradle-commands) or [Maven](#apache-maven-commands).

###### [Gradle](http://gradle.org/) commands

```bash
$ gradle build publishToMavenLocal
```

If you want to also run the functional tests then you need to [Download WAS Liberty](/docs/Downloading-WAS-Liberty.md) and set the libertyRoot property in the gradle.properties file to point to your Liberty install.

###### [Apache Maven](http://maven.apache.org/) commands

```bash
$ mvn install
```

If you want to also run the functional tests then you need to [Download WAS Liberty](/docs/Downloading-WAS-Liberty.md) and pass in the location of your install as the system property libertyRoot:

```bash
$ mvn -DlibertyRoot=<LibertyInstallLocation> install
```

In addition to publishing the war to the local maven repository, the built war file is copied into the apps directory of the server configuration located in the async-jaxrs-wlpcfg directory:

```text
async-jaxrs-wlpcfg
 +- servers
     +- jaxrsSample                            <-- specific server configuration
        +- server.xml                          <-- server configuration
        +- apps                                <- directory for applications
           +- async-jaxrs-application.war      <- sample application
        +- logs                                <- created by running the server locally
        +- workarea                            <- created by running the server locally
```

### Running the application locally
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#running-the-application-locally)

Pre-requisite: [Download WAS Liberty](/docs/Downloading-WAS-Liberty.md)

Use the following to start the server and run the application:

```bash
$ export WLP_USER_DIR=/path/to/sample.async.jaxrs/async-jaxrs-wlpcfg
$ /path/to/wlp/bin/server run jaxrsSample
```

* `run` runs the server in the foreground.
* `start` runs the server in the background. Look in the logs directory for console.log to see what's going on, e.g.

```bash
$ tail -f ${WLP_USER_DIR}/servers/jaxrsSample/logs/console.log
```

