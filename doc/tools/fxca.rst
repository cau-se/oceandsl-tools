.. _kieker-tools-fxca:

FXTran Code Analysis
====================

Fxtran is a Fortran parser which outputs an XML based AST.
The parser can be found here https://github.com/pmarguinaud/fxtran (original)
and https://github.com/OceanDSL/fxtran (backup).
**fxca** processes the AST files from **fxtran** and outputs CSV files for calls, dataflows,
components and data storages.

===== ====================== ======== ======================================================
Short Long                   Required Description
===== ====================== ======== ======================================================
-d    --default-component    no       In case callees are identified that do not have an
                                      implementation in the code, assign the callee to this
                                      operation.
      --eol                  no       End of line symbol, Default is system dependent
-f    --flat                 no       Scan source directories flat, i.e. not in recusrive
                                      mode, default is scan recursively
-i    --input                yes      One or more paths to fxtran-generated XML files.
-l    --library-functions    no       Map files for built-in and other runtime functions.
-o    --output               yes      Path where the output files are placed.
===== ====================== ======== ======================================================

Inputs
------

- *.xml files for each Fortran file
- library-functions.csv map file
  - file = file and module name
  - name = operation name
  - parameter = number of parameters
  - mode = fixed or variable number of parameters

Outputs
-------

- operation-definitions.csv
  - path = path representing the module
  - operation = name of the operation (function, procedure, subroutine)
- calltable.csv contains operation calls with a full description of the caller and callee
  - caller-path = path representing the caller module
  - caller-module = module name of the caller
  - caller-operation = caller operation name
  - callee-path = path representing the callee module
  - callee-module = module name of the callee
  - callee-operation = callee operation name
- notfound.csv contains information on calls where the callee could not be resolved.
  - file-path = caller module path
  - module-name = caller module name
  - operation = caller operation
  - call = callee name
- dataflow-cc.csv describes dataflow between operations, source is the initiator of the dataflow
  - source-path = path representing the dataflow source
  - source-module = source module name
  - source-operation = source operation
  - target-path = path representing the dataflow target
  - target-module = target module name
  - target-operation = target operation
  - direction = flow direction (read, write, both)
- dataflow-cb.csv describes dataflow between common blocks and operations
  - common-block = name of the common block
  - file = file containing the operation
  - module = module name of the module for the involved operation
  - operation = operation name
  - direction = flow direction (read, write, both)
- common-blocks.csv describes common blocks
  - name = name of the common block
  - files = list of files containing the common block
  - modules = list of modules containing the common block
  - variables = list of variables in the common block


Example
-------

Basic setup reads in a set of XML files produced by **fxtran**. Lets assume all Fotran files
are located in ```code```.
```
fxtran code/*
fxca -i code/*.xml -o data
```
This produces a set of CSV files for calls and dataflows in the ```data``` directory.

However, code can contain invocations which call operations in libraries or language internal
functions where we do not have source code available. Here we can either ignore these calls
or assign them to a default component.

```
fxca -i code/*.xml -o data -d
```

In some projects this might be sufficient, but to be more flexible, we can use map files
to describe library and other auxiliary operations. The map files contain two columns for
module name and function name, respectively.

```
fxca -i code/*.xml -o data -l builtin-fortran-functions.csv special-library.csv
```

It is possible to specify multiple map files.

The CSV files are written based on the system encoding and line endings. This might not
always be helpful, i.e., when the tables must cross platforms. Therefore, you can specify
a different line ending with the ```--eol``` option.

```
fxca -i code/*.xml -o data --eol '\n'
```
