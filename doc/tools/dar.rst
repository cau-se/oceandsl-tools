.. _kieker-tools-dar:

Dynamic Architecture Analysis
=============================

Read Kieker monitoring logs and generate an architecture model from it.

Synopsis
--------
::
  
  dar -i <log-directory> ... <log-directory> -o <model-path>
      [-M <path> ... <path>] [-a <addrline>] [-e <executable>]
      -l <label> -E <experiment-name> -s <extractor>
      [-k] [-ms <separator>] -m <modes>

Options
-------

===== ================================= ======== ======================================================
Short Long                              Required Description
===== ================================= ======== ======================================================
-i    --input <paths>                   yes      Input Kieker log directory location
-o    --output <path>                   yes      Output directory to store the recovered model
-M    --component-maps <paths>                   Component, file and function map files
-a    --addrline <path>                          Location of the addrline tool (necessary for Kieker4C)
-e    --executable <path>                        Location of the executable
-l    --source-label <label>            yes      Set source label for the read data
-c    --case-insensitive                         Handle function names case insensitive
-E    --experiment-name <name>          yes      Name of the experiment
-s    --signature-extractor <extractor> yes      Type of extractor used for component and operation
                                                 signatures (elf, python, java)
-k    --keep-metadata                            Keep the metadata info in memory regardless a trace
                                                 is considered complete
-ms   --map-file-separator <seperator>           Separator character for the mapping file
-m    --module-modes <modes>            yes      Module converter strategies: file-mode, map-mode,
                                                 module-mode, java-class-mode, java-class-long-mode
===== ================================= ======== ======================================================

Description
-----------

The basic function is to read Kieker monitoring logs containing call traces and
generate one Kieker architecture model from this input. It supports various modes
to identify components.

Module Modes
~~~~~~~~~~~~

Currently **dar** supports five different modes to modularize the architecture.
Each module is seen as a component of the architecture.

- **file-mode** all functions within a file are put in the same component
- **map-mode**  a separate map file sorts functions into a component
- **module-mode** Fortran module definitions are used to place a function into a
  component
- **java-class-mode** The simple class name is used for component names and to
  group functions/methods
- **java-class-long-mode** The full qualified name of classes is used for
  component names and to group functions/methods

In principle, it is possible to specify multiple modes. This is helpful when
for example not all parts of a program use, e.g., Fortran modules, then the
functions outside of modules can be grouped based on another strategy.

Component map file
~~~~~~~~~~~~~~~~~~

The tool allows to group operations not only by file, but also by module
or component. As the module is not present with all kind of signatures, e.g.,
in ELF executable, information cannot be derived from the file name, we use a
module map file with the following format:

component name, file path, operation name

 
Using Addrline
~~~~~~~~~~~~~~
 
Addrline is a command line tool under Linux and other Unices which is able to
exctract ELF information on functions in ELF based executable. That are all
native executable under Linux and similar operating systems. It might work with
other binaries as well.
 
To be able to work the executable must be linked with debugging symbols, i.e.,
with gcc this can be achieved with the option `-g`.

Executable
~~~~~~~~~~

When analyzing Kieker logs from Kieker4C the log only contains function
pointer references. This is suboptimal to understand what is going on.
Therefore, you can extract names and other information on function with dar
utilizing addrline automatically.

The executable must be compiled with option `-g` to contain debugging symbols when using **gcc**.

For ELF binaries which have been compiled with debug symbols can be used to resolve
logged function pointers in the Kieker log. Therefore, `dar` relies on an executable called
`addrline` which must be installed. The location can be provided with `-a`.

As the debugging symbols are included in the executable, the executable must also be
provided to `dar` with the option `-e`.

Special Trace Cases
~~~~~~~~~~~~~~~~~~~

Usually a trace is a sequenceo of before and after operation events. Each
before operation increases the stack and every after operation decreases the
call stack. Thus, when the call stack is empty again, the **dar** tool removes
the trace metadata from memory, as the trace is considered complete. However,
in some contexts this is not the case and the trace continues afterwards.
Therefore, you can use the option `-k`. This will keep the trace metadata.
Regardless the trace seems to be complete. In case you have many small traces
this migh lead to a memory leak, as all trace metadata is kept until termination
of the tool.


Examples
--------

The following example imports a Kieker log from `kieker-log` and outputs the resulting
model in `model-directory`. All elements will be labeled `dynamic` as specified by the
`-l` option. The whole project will be labeled as `demo-project` and signatures will be
interpreted based on Java signature parser. For Fortran and C programs, the option must
be `elf`. The used modularization strategy is java-class-mode.

::
  
  dar -i kieker-log -o model-directory -l dynamic -E demo-project -s java -m java-class-mode

The second exmaple addresses the use with ELF binaries. Therefore, the signature parser is
`elf` and the module mode is `module-mode` which uses the module information
in signatures in Fortran.

::
  
  dar -i kieker-log -o model-directory -l dynamic -E demo-project -s elf -m module-mode -a /usr/bin/addr2line -e /home/user/my-model/UVicECSM

