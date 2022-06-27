Preprocessor for Static Logs
============================

Logs based on FParser produce a CSV file with three column refering
to caller file name, caller function name, callee function name.
The callee file name is missing. However, this can be inferred by other
means from the source code and stored in lookup tables. The
**pp-static-log** will read an 3 column CSV file use one or more
lookup tables and generate a 4 column output CSV file.


===== ===================== ======== ======================================================
Short Long                  Required Description
===== ===================== ======== ======================================================
-i    --input               yes      Input 3 column static log file
-m    --map                 yes      Function to map files. One of more files separated by
                                     a space.
-o    --output              yes      Output file for 4 column log
===== ===================== ======== ======================================================

