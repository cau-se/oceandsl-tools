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
-k    --keep-metadata                Keep the metadata info in memory regardless a trace
                                     is considered complete
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

 
Using Addrline
--------------
 
Addrline is a command line tool under Linux and other Unices which is able to
exctract ELF information on functions in ELF based executable. That are all
native executable under Linux and similar operating systems. It might work with
other binaries as well.
 
To be able to work the executable must be linked with debugging symbols, i.e.,
with gcc this can be achieved with the option -g.

Executable
----------

When analyzing Kieker logs from Kieker4C the log only contains function
pointer references. This is suboptimal to understand what is going on.
Therefore, you can extract names and other information on function with dar
utilizing addrline automatically.

The executable must be compiled with -g to contain debugging symbols.

Special Trace Cases
-------------------

Usually a trace is a sequenceo of before and after operation events. Each
before operation increases the stack and every after operation decreases the
call stack. Thus, when the call stack is empty again, the **dar** tool removes
the trace metadata from memory, as the trace is considered complete. However,
in some contexts this is not the case and the trace continues afterwards.
Therefore, you can use the option -k. This will keep the trace metadata.
Regardless the trace seems to be complete. In case you have many small traces
this migh lead to a memory leak, as all trace metadata is kept until termination
of the tool.
 
