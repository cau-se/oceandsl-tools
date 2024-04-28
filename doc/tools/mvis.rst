.. _kieker-tools-mvis:

Model Visualization and Statistics Tool
=======================================

mvis allows to generate graphics and apply metrics to architecture models.

Synopsis
--------
::
  
  mvis -i <path> -o <path> [-M <path>] [-s <selector>] [-g <graphs>] [-m <mode>] [-c] [--eol <character>]

Options
-------

===== ===================== ======== ======================================================
Short Long                  Required Description
===== ===================== ======== ======================================================
-i    --input               yes      Input model directory
-o    --output              yes      Output directory to store graphics and statistics
-M    --component-map                Component, file and function map file
-s    --selector                     Set architecture graph selector
                                     all, diff, subtract, intersect
-g    --graphs                       Specify which output graphs must be generated
                                     dot-op, graphml, dot-component
-m    --mode                         Mode deciding whether an edge is added when its nodes
                                     are not selected add-nodes, only
-c    --compute-statistics           Generate the listed statistics
      --eol                          Set end of line character for CSV files
                                     Default: system's standard symbol
===== ===================== ======== ======================================================

Description
-----------

mvis is used to generate visualizations and create statistics on a model. It can select
different elements of the model (nodes) and use labels assigned to model elements.
See `sar` and `dar`documentation how to set labels and `relabel` to change labels.

Selectors
~~~~~~~~~

- **all** selects all nodes regardless of the node and edge labels
- **diff** diff:label1,label2 
- **subtract** subtract:label1,label2
- **interstect** intersect:label1,label2

 
Outputs
~~~~~~~
 
**Counting Metrics**
%s/%s-%s output-directory, file-prefix, operation-calls.csv
%s/%s-%s output-directory, file-prefix, distinct-operation-degree.csv
%s/%s-%s output-directory, file-prefix, distinct-module-degree.csv

**Allen Metric**
%s/%s output-directory, model-complexity.csv

Examples
--------

Generate a component based graphic with all nodes of a model and compute statistics.

::
  
  mvis -i model -o output -s all -g dot-component -c

Generate a component based graphic with two levels of components, where the second level
of components is provided by the `component-map.csv` file. Display only elements which have
both labels `dynamic` and `static`.

::
  
  mvis -i model -o output -s intersect:dynamic,static -g dot-component -c -M component-map.csv
