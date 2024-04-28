.. _kieker-tools-restructuring:

Restructuring
=============

Identify the number of operations to transform an architecture model
into another one. Best strategy to use is kuhn

Synopsis
--------
::
  
  restructuring -i <input-models> -o <output-model> [-e <experiment-name>] [--eol <symbol>] [-s <strategy>]


Options
-------

===== ====================== ======== ======================================================
Short Long                   Required Description
===== ====================== ======== ======================================================
-i    --input                yes      Input architecture model directories
-o    --output               yes      Output architecture model directory
      --eol                  no       End of line symbol
-e    --experiment           no       Experiment name
-s    --strategy             yes      Strategy identifier
                                      Possible Values: [NORMAL, EMPTY, RANDOM, KUHN]
===== ====================== ======== ======================================================

Description
-----------

The tool reads a multiple number of models and calculates the edit distance between the first
and every other model. The tool outputs information on the relevant operations and a table
containing all changes.

Examples
--------

::
  
  restructuring -i input/base input/optimization-1 input/optimization-2 -e example -s kuhn

