.. _kieker-tools-allen-upper-limit:

Allen Upper Limit
=================

Calculate the complexity, coupling and cohesion of a selected topology graph
based on the operations of an architecture model. Used as a upper limit for
measurements.

===== =============== ======== ======================================================
Short Long            Required Description
===== =============== ======== ======================================================
-i    --input         yes      Input model directory
-n    --nodes                  Number of nodes in graph
-o    --output        yes      Output directory to store graphics and statistics
-M    --component-map          Component, file and function map file
-m    --creator-mode           Select topology generator modes: full, linear, start
===== =============== ======== ======================================================

Lets assume you have created a architecture model with the dynamic or static architecture recovery tool, :ref:`kieker-tools-dar` and :ref:`kieker-tools-sar` respectively.
And lets assume the model resides in the `input-model` directory. The output will be written to `output` and contain graphs and statistics.

The basic setup is
```bash
oceandsl-tools/bin/allen-upper-limit -i input-model -o output
```
and will read in the input-model, create a fully interconnected graph of all operations in the model.

Explain different creator modes and which one is default
Explain what -n does
Explain what -M does

