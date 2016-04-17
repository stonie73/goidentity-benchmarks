# goidentity-benchmarks

go:Identity benchmark suite

### Getting Started

This benchmark is using gatling as a load-testing tool.
To reduce setup time, everything you need is wrapped within a gradle build.

### System Requirements

- JDK 8
- git (if you want to collaborate)

### Running the gatling recorder 

To open the gatling recorder UI: 
- open a console
- go to the base dir (where you find build.gradle)
- run "./gradlew gatlingRecorder"
 
The gatling recorder window should open and you are ready to go.

The recordings will be stored in "build/gatling/recordings/" as a Scala source file.
Extract everything you need from here and migrate it to a better readable and structured
gatling simulation class under "src/gatling/scala/".

### Running the benchmarks

To start the benchmark: 
- open a console
- go to the base dir (where you find build.gradle)
- run "./gradlew gatling"

You will find the sweet gatling html report for your simulations under "build/reports/gatling/"
