.. _kieker-tools-mop:

Model Operation
===============

Merges models together from different sources or select a portion of the model.
The tool supports multiple merge approaches:

- **merge** = merge components with the same name
- **nearest-merge** = merge components with have the most similar operations.
- **select** = select a subset of the model

Synopsis
--------
::
  
  mop -i <path> -o <path> -e <experiment name> merge
  mop -i <path> -o <path> -e <experiment name> -t <threshold> nearest-merge
  mop -i <path> -o <path> -e <experiment name> -s <path> select

Options
-------

===== =========================== ======== ======================================================
Short Long                        Required Description
===== =========================== ======== ======================================================
-i    --input <paths>             yes      Input architecture model directories
-o    --output <path>             yes      Output architecture model directory
-e    --experiment <name>         yes      Experiment name
-s    --selection-criteria <path>          Element selection criteria file
-t    --threshold <float>                  Threshold for accepted similarity in component names:
                                           1 = identical, 0 = nothing identical Default: 0.4
===== =========================== ======== ======================================================

Description
-----------

Two or more models are read and merged. The tool reads the first model and tries to add elements
of the subsequent models. In case elements have the same fully qualified name, it assumes that
these are the same thing.

The selection pattern file contains Java regular expressions to select
components that should exist in the output model.

Examples
--------

**Example merge**

Here two input models in the directories `static-model` and `dynamic-model` are read and **merged**
into a new model and stored in the `joined-model` directory.

::
  
  mop -i static-model dynamic-model -o joined-model -e UVic-2.9.2 merge


**Example selection**

The input model `model` is read and a portion of the model is selected and stored in the 
`selected-model` directory. The selection criteria is storedn in the `selection-pattern-file`.
The selection pattern file contains Java regular expressions to select
components that should exist in the output model.

::
  
  mop -i model -o selected-model -e UVic-2.9.2 -s selection-pattern-file select


