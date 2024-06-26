.. _kieker-tools-sar:

Static Architecture Recovery
============================

Recover the architecture based on static code analysis done by the fparser tool.
The tool can currently recognize calls and data flow based on global variables,
e.g., common blocks in Fortran.

Synopsis
--------
::
  
  sar -i <path> -o <path> [-E <experiment-name>] [-g <call|dataflow|both>] [-m <module-mode|file-mode|map-mode>] [-l <label>]


Options
-------

===== ========================== ======== ======================================================
Short Long                       Required Description
===== ========================== ======== ======================================================
-i    --input                    yes      Input directory
-o    --output                   yes      Output directory to store graphics and statistics
-M    --component-maps                    Component, file and function map files
-E    --experiment-name                   Name of the experiment
-H    --hostname                          Hostname to be used in CSV reconstruction
-g    --input-mode                        Input mode to be used for static analysis
                                          Possible Values: [CALL, DATAFLOW, BOTH]
-a    --missing-functions                 Output file for the list of functions without an
                                          associated file
-n    --missing-mappings-file             Output file for the list of files with a missing
                                          mapping in the mapping file. 
-m    --module-modes                      Module converter strategies (at lease one of):
                                          module-mode, file-mode, map-mode 
-sc   --separation-character              Separation character for CSV files, default is
                                          comma (,)
-l    --source-label                      Set source label for the read data
===== ========================== ======== ======================================================

Description
-----------

The tool reads a list of operation calls and dataflows from CSV files.
For Fortran programs **fxtran** can be used to generate these files.

The CSV files should use, as value separator. However, in case they
deviate as other tools to generate operation calls and dataflow are used,
the option allow to set a different character for operation calls -cs, dataflows
-ds, and names -ns from the function names CSV.

Call Input File
~~~~~~~~~~~~~~~

- source-path or component identifier
- source-module
- caller operation
- target-path or component identifier
- target-module
- callee operation (the called operation)

Dataflow Input File
~~~~~~~~~~~~~~~~~~~

- module or component identifyer
- operation accessing the data or writing to the data
- direction read or write or both
- shared-data name of the data storage

Function Names File
~~~~~~~~~~~~~~~~~~~

You can use multiple function names files.

- component or module name
- function name

These maps are used to rewrite the component identifiers in a component call
in case these are set to the empty string "".

Examples
--------

In this example a previously generated set of input files from **fxtran**
are read and a model is produced in the `output-model` directory. Each
element of the model is marked with a label `example`. Note this label does
not appear visually in the models, but can be used to control coloring and
other operation on a model later when mixing models with other models.

The example uses `module-mode` which means modules are derived from Fortran
module information. If your model does not contain such information, it
is better to use `file-mode` or `map-mode`.

::
  
  sar -i input-data -o output-model -l example -m module-mode -g both
