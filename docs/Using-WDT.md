## Eclipse / WDT

The WebSphere Development Tools (WDT) for Eclipse can be used to control the server (start/stop/dump/etc.), it also supports incremental publishing with minimal restarts, working with a debugger to step through your applications, etc.

WDT also provides:

* content-assist for server configuration (a nice to have: server configuration is minimal, but the tools can help you find what you need and identify finger-checks, etc.)
* automatic incremental publish of applications so that you can write and test your changes locally without having to go through a build/publish cycle or restart the server (which is not that big a deal given the server restarts lickety-split, but less is more!).

Installing WDT on Eclipse is as simple as a drag-and-drop, but the process is explained [on wasdev.net] [wasdev-wdt].

[wasdev-wdt]: https://developer.ibm.com/wasdev/downloads/liberty-profile-using-eclipse/

### Clone Git Repo
:pushpin: [Switch to cmd line example](/docs/Using-cmd-line.md/#clone-git-repo)

If the sample git repository hasn't been cloned yet, WDT has git tools integrated into the IDE:

1.  Open the Git repositories view
    * *Window -> Show View -> Other*
    * Type "git" in the filter box, and select *Git Repositories*
2.  Copy Git repo url by finding the textbox under "HTTPS clone URL" at the top of this page, and select *Copy to clipboard*
3.  In the Git repositories view, select the hyperlink `Clone a Git repository`
4.  The git repo url should already be filled in.  Select *Next -> Next -> Finish*
5.  The "sample.async.jaxrs [master]" repo should appear in the view

### Building the sample in Eclipse
:pushpin: [Switch to cmd line example](/docs/Using-cmd-line.md/#building-the-sample)

This sample can be built using [Maven](#building-with-maven).

#### Building with [Maven](http://maven.apache.org/)

###### Import Maven projects into WDT

1.  In the Git Repository view, expand the jaxrs repo to see the "Working Directory" folder
2.  Right-click on this folder, and select *Copy path to Clipboard*
3.  Select menu *File -> Import -> Maven -> Existing Maven Projects*
4.  In the Root Directory textbox, Paste in the repository directory.
5.  Select *Browse...* button and select *Finish* 
6.  This will create a project in Eclipse: async-jaxrs

:star: *Note:* If you did not use Eclipse/WDT to clone the git repository, follow from step 3, but navigate to the cloned repository directory rather than pasting its name in step 4.

###### Running in Eclipse with Maven

1. Right-click the project and select **Run As > Run on Server**.
2. You should see the following message in the console: `Application async-jaxrs started in XX.XX seconds.`

:star: *Note:* Some versions of WDT incorrectly map the cdi-1.2 dependency to the CDI 1.0 Facet, which prevents the *Run As ...* operation in step 1 from succeeding. If this happens, Right-click on the `async-jaxrs` project, and select *Properties*, then select *Project Facets* in the left-hand pane. Change the the "Context and dependency injection (CDI)" facet to use version 1.2, at which point, step 1 (above) should work.

