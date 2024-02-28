.. _kieker-tools-allen-upper-limit:

Allen Upper Limit
=================

Calculate the complexity, coupling and cohesion of a selected topology graph
based on the operations of an architecture model. Used as a upper limit for
measurements.

===== =============== ======== ======================================================
Short Long            Required Description
===== =============== ======== ======================================================
-i    --input                  Input model directory
-n    --nodes                  Number of nodes in graph
-o    --output        yes      Output directory to store graphics and statistics
-M    --component-map          Component, file and function map file
-m    --creator-mode           Select topology generator modes: full, linear, star
===== =============== ======== ======================================================

Lets assume you have created a architecture model with the dynamic or static architecture recovery tool, :ref:`kieker-tools-dar` and :ref:`kieker-tools-sar` respectively.
And lets assume the model resides in the `input-model` directory. The output will be written to `output` and contain graphs and statistics.

The setup for architecture models as input is:
```bash
oceandsl-tools/bin/allen-upper-limit -i input-model -o output
```
This will read in the input-model, create a fully interconnected graph based on all operations in the model, keeping the module boundaries.

When option `-n` is used a graph with the specified number of nodes is generated. In case an input model is specified, it is ignored.
The generated graph will have the topollogy specified with the `-m` option:
- full = fully interconnected graph
- linear = linear chain of all nodes
- star = star pattern graph with one central node

The following entry produces a graph with 100 nodes which are fully interconnected.
```bash
oceandsl-tools/bin/allen-upper-limit -n 100 -m full -o output
```

The `-M` option is currently not implemented.

