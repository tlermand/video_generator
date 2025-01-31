# README #

### DESCRIPTION ###
Automatic generation of explanatory videos of algorithms from their execution traces.
Directory distribution:
* [example execution traces](https://github.com/tlermand/video_generator/tree/main/example%20execution%20traces "example execution traces") contains execution traces to test the project.
* [grammar model](https://github.com/tlermand/video_generator/tree/main/grammar%20model "grammar model") contains the grammar used in the project.
* [static tracing clases](https://github.com/tlermand/video_generator/tree/main/static%20tracing%20clases "static tracing clases") contains the classes used for static tracing.
* [vGenerator](https://github.com/tlermand/video_generator/tree/main/vGenerator "vGenerator") contains the Apache Netbeans project including all the source code.

### BUILD PROJECT ###
To build the project, Apache Netbeans is required.
* Download and install [Apache NetBeans](https://netbeans.apache.org/front/main/index.html),
* Open the project folder ([vGenerator](https://github.com/tlermand/video_generator/tree/main/vGenerator "vGenerator")) using Apache Netbeans.
* Once open with Apache Netbeans, click build project. (All necessary dependencies will be downloaded in this step)
* After building the project, you can click run project to test the application.

### USING THE APPLICATION ###
Once open:
* Click on Browse to find and select an execution trace file (you can try any of the files in "[example execution traces](https://github.com/tlermand/video_generator/tree/main/example%20execution%20traces "example execution traces")" folder)
* Then, you can click on the dropdown selection to select one the 4 available graphical representations for the nodes.
* finally, you can click en Generate video to generate and save the generated video.
