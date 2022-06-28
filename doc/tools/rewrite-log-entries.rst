Replace Log Entries
===================

(obsolete)

This tool has 4 parameters and can be used to replace function pointer
references by function signatures produced by kieker-lang-pack-c
instrumentations.

The parameters:
-i input kieker log directory>
-o path where the output kieker log is placed
-a the location of the addr2line executable to resolve the names
-m the executable (model) to be analyzed by addr2line

