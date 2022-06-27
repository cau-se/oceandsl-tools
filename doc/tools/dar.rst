Dynamic Architecture Analysis
=============================

Read a Kieker log and generate an architecture model from it.

===== ===================== ======== ======================================================
Short Long                  Required Description
===== ===================== ======== ======================================================
-i    --input               yes      Input Kieker log directory location
-o    --output              yes      Output directory to store the recovered model
-M    --component-map                Component, file and function map files
-a    --addrline                     Location of the addrline tool (necessary for Kieker4C)
-e    --executable                   Location of the executable
-l    --source-label        yes      Set source label for the read data
-c    --case-insensitive             Handle function names case insensitive
-E    --experiment-name     yes      Name of the experiment
-s    --signature-extractor yes      Type of extractor used for component and operation 
                                     signatures (elf, python, java)
===== ===================== ======== ======================================================

Examples
--------

```
dar -i kieker-log -o model-directory -l dynamic -E demo-project -s java
```

Component map file
------------------

The tool allows to group operations not only by file, but also by module
or component. As the module is not present with all kind of signatures, e.g.,
in ELF executable, information cannot be derived from the file name, we use a
module map file with the following format:

component name, file path, operation name

 
 

