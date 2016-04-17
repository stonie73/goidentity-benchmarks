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

### Running the benchmarks

To start the benchmark: 
- open a console
- go to the base dir (where you find build.gradle)
- run "./gradlew gatling"

