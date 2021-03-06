Static Architecture Recovery
============================

Recover the architecture based on static code analysis done by the fparser tool.
The tool can currently recognize calls and data flow based on global variables,
e.g., common blocks in Fortran.

===== ========================== ======== ======================================================
Short Long                       Required Description
===== ========================== ======== ======================================================
-i    --call-input                        Operation call CSV file
-j    --dataflow-input                    Dataflow CSV file
-cs   --call-separation-char              Separation character for operation call CSV files,
                                          default is comma (,)
-ds   --dataflow-separation-char          Separation character for dataflow CSV files, default
                                          is comma (,)
-ns   --names-separation-char             Separation character for function name lists CSV 
                                          files, default is comma (,)
-f    --function-names                    Function file map CSV file
-m    --missing-functions                 Output file for the list of function without an associated file
-o    --output                   yes      Output directory to store graphics and statistics
-M    --component-map                     Component, file and function map file
-l    --source-label             yes      Set source label for the read data
-c    --case-insensitive                  Handle function names in CSV case insensitive
-H    --hostname                          Hostname to be used in CSV reconstruction
-E    --experiment-name          yes      Name of the experiment
-n    --missing-mappings                  Output file for files without a mapping in the 
                                          mapping file.
===== ========================== ======== ======================================================


The tool reads a list of operation calls, e.g., in Fortran, one subroutine
calling another one, and a data access list from CSV files.

These files are generated by the fparser-based tool that can be found in:
https://cau-git.rz.uni-kiel.de/ifi-ag-se/oceandsl/esm-coupling-analysis

The CSV files should use , as value separator. However, in case they
deviate as other tools to generate operation calls and dataflow are used,
the option allow to set a different character for operation calls -cs, dataflows
-ds, and names -ns from the function names CSV.

Call Input File
---------------

- source-path or component identifyer
- caller operation
- target-path or component identifyer
- callee operation (the called operation)

Dataflow Input File
-------------------

- module or component identifyer
- operation accessing the data or writing to the data
- direction read or write or both
- shared-data name of the data storage

Function Names File
-------------------

You can use multiple function names files.

- component or module name
- function name

These maps are used to rewrite the component identifiers in a component call
in case these are set to the empty string "".




