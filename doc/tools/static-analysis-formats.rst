File Formats
============

The static analysis uses a set of input files to read call graph and data
flow traces. All CSV files use commas for value separation. In detail they
are:


Function call file
------------------

Containing functions calls.

There are two supported formats with 3 and 4 column CSV files.
- 3 columns: file, caller, callee: In this format the callee lacks the
  information where the operation is located. Thus, it must be inferred
  at a later point.
- 4 columns: caller-file, caller, callee-file, callee

Function lookup file
--------------------

The lookup file has 2 columns containing the operation name followed by
the filename, i.e., operation, file

Component map file
------------------

The tool allows to group operations not only by file, but also by module
or component. As the module information cannot be derived from the file
name, we use a module map file with the following format:
component name, file path, operation name

Dataflow file
-------------

Dataflow is stored in a file with 4 columns:
caller file, caller, direction, common block




