Create Architecture Model
=========================

The `create-architecture-model` tool reads Kieker logs and creates
*Assembly Level Component Dependency Graphs* and *Assembly Level Operation Dependency Graphs*.
In addition the architecture model is serialized into a JSON file.
The tool is intended to be used in conjunctions with kieker-lang-pack-c Kieker logs, as their
`BeforeOperationEvent`s and `AfterOperationEvent`s do only contain pointer information for 
operation calls which must be resolved using `addr2line`. 

Parameters:
-i, --input (required) Input Kieker log directory or CSV file location
-o, --output (required) Output directory to store graphics and statistics
-M, --component-map Component, file and function map file
-a, --addrline Location of the addrline tool
-e, --executable Location of the executable
-m, --mode Different input read modes, default is kieker; other option is csv
-ia, --input-architecture-model Directory for an input architecture model
-oa, --output-architecture-model Directory for an output architecture model
-l, --source-label (required) Set source label for the read data
-c, --case-insensitive Handle function names case insensitive
    This is helpful with Fortran code as function names are handled
    case insensitive.
-H, --hostname Hostname to be used in CSV reconstruction, this can be
    used with static analysis data to simulate a deployment host
-E, --experiment-name (required) Name of the experiment
-g, --graphs Specify which output graphs must be generated (dot-op, dot-component, graphml)


Build
-----

`./gradlew build`

The build produces an tar and zip archive in its `build/distributions` directory.

Execution
---------

`create-architecture-model -i kieker-20210203-185229-781930961844639-UTC -o results -a /usr/local/bin/addr2line -e an-executable -p /absolute/path/prefix
