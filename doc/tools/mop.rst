Model Operation
===============

Merges models together from different sources or select a portion of the model.

===== ===================== ======== ======================================================
Short Long                  Required Description
===== ===================== ======== ======================================================
-i    --input               yes      Input architecture model directories
-o    --output              yes      Ouput architecture model directory
-e    --experiment          yes      Experiment name
-s    --selection-criteria           Element selection criteria file
===== ===================== ======== ======================================================

Example merge:
```
mop -i static-model dynamic-model -o joined-model -e UVic-2.9.2 merge
```

Example selection:
```
mop -i model -o selected-model -e UVic-2.9.2 -s selection-pattern-file select
```

The selection pattern file contains Java regular expressions to select
components that should exist in the output model.


