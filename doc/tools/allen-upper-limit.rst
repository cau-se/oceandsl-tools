.. _kieker-tools-allen-upper-limit:

Allen Upper Limit
=================

Calculate the complexity, coupling and cohesion of a selected topology graph
based on the operations of an architecture model. Used as a upper limit for
measurements.[

Synopsis
--------
::
  
  allen-upper-limit [-i <path>] -o <path> [-n <number> -M <paths> -m <modes>]


Options
-------

===== ======================= ======== ======================================================
Short Long                    Required Description
===== ======================= ======== ======================================================
-i    --input <path>                   Input model directory
-o    --output <path>         yes      Output directory to store graphics and statistics
-n    --nodes <number>                 Number of nodes in graph
-M    --component-map <paths>          Component, file and function map file
-m    --creator-mode <modes>           Select topology generator modes: full, linear, star
===== ======================= ======== ======================================================

Description
-----------

The **allen-upper-limit** tool computes the complexity, coupling and cohesion metrics of a
undirected modular graph. It is based on the Edward B. Allen metrics founded on information
theory. The metrics aim to cover the cognitive load to comprehend a modular graph. The
complexity metric can be used on modular and non modular graphs, while coupling and cohesion
require a modular graph.

The graph can be based on an existing architecture model. In this case the operations of the
assembly model represent the nodes in the graph. The modules are derived from the component
structure of the graph. The edges of the graph are automatically generated to interconnect
all operations, i.e., all nodes with each other to create the most interconnected graph based
on the architecture. This is a helpful measure to compare it to the same metrics produced on
the actual model by the **maa** tool.

Beside an architecture modeĺ, the tool can also auto generate a graph based on the number
of nodes and a topology (creator-mode).

The `-M` option is currently not implemented.

Examples
--------

Lets assume you have created a architecture model with the dynamic or static
architecture recovery tool, `dar` and `sar`
respectively. And lets assume the model resides in the `input-model` directory.
The output will be written to `output` and contain graphs and statistics.

The setup for architecture models as input is:

::
  
  oceandsl-tools/bin/allen-upper-limit -i input-model -o output

This will read in the input-model, create a fully interconnected graph based
on all operations in the model, keeping the module boundaries.

When option `-n` is used a graph with the specified number of nodes is
generated. In case an input model is specified, it is ignored.
The generated graph will have the topollogy specified with the `-m` option:

- **full** = fully interconnected graph
- **linear** = linear chain of all nodes
- **star** = star pattern graph with one central node

The following entry produces a graph with 100 nodes which are fully interconnected.

::
  
  oceandsl-tools/bin/allen-upper-limit -n 100 -m full -o output

