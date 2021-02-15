Create Architecture Model
=========================

The `create-architecture-model` tool reads Kieker logs and creates
*Assembly Level Component Dependency Graphs* and *Assembly Level Operation Dependency Graphs*.
In addition the architecture model is serialized into a JSON file.
The tool is intended to be used in conjunctions with kieker-lang-pack-c Kieker logs, as their
`BeforeOperationEvent`s and `AfterOperationEvent`s do only contain pointer information for 
operation calls which must be resolved using `addr2line`. 

This tool has 5 parameters:
-i input kieker log directory>
-o path where the output kieker log is placed
-a the location of the addr2line executable to resolve the names
-m the executable (model) to be analyzed by addr2line
-p prefix path to be removed from path information

Build
-----

`./gradlew build`

The build produces an tar and zip archive in its `build/distributions` directory.

Execution
---------

`create-architecture-model -i kieker-20210203-185229-781930961844639-UTC -o results -a /usr/local/bin/addr2line -e an-executable -p /absolute/path/prefix
