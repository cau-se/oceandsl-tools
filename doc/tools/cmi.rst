.. _kieker-tools-cmi:

Check Model Integrity
=====================

This is more or less a debugging tool to check whether a model is without
technical issues, i.e., containment, all references intact and other properties.
See below for a complete list of checks.

Synopsis
--------

::
  
  cmi -i <model path> [-c <checks>]`

Options
-------

===== ====================== ======== ======================================================
Short Long                   Required Description
===== ====================== ======== ======================================================
-i    --input <path>         yes      Input architecture model directory
-c    --checks <checks>               Checks: type, assembly, deployment, execution, source,
                                      statistics
===== ====================== ======== ======================================================

Description
-----------

List of detailed checks:

- type (Type model)
  
  - Check component type signarure integrity including all operations and storages
  - Check signature attributes in all objects
  - Check name attributes in all objects
  - Check package attributes in all objects
  - Check all references in all objects for integrity
  
- assembly (Assembly model)
  
  - Check signature attributes in all objects
  - Check name attributes in all objects
  - Check package attributes in all objects
  - Check all references in all objects for integrity
  
- deployment (Deployment model)
  
  - Check for duplicate deployed operations
  - Check signature attributes in all objects
  - Check all references in all objects for integrity
  
- execution (Execution model)
  
  - Check signature attributes in all objects
  - Check all references in all objects for integrity
  - Check execution invocation integrity
  - Check execution storage dataflow integrity
  - Check execution operation dataflow integrity
  - Check for duplicate invocations
  
- source
  
  - Check source element for missing labels
  - Check source element without a model element
  
- statistics
  
  - Check all references in all objects for integrity

Examples
--------

Check the integrity of the type model in the model directory `input-model` with:

::
  
  oceandsl-tools/bin/cmi -i input-model -c type

It is possible to check multiple models at once. For example type and deployment with:

::
  
  oceandsl-tools/bin/cmi -i input-model -c type deployment

