.. _kieker-tools-mktable:

Make Table
==========

Processes output from the delta tool and generates two LaTeX files
compact.tex and full.tex which show an aggregated and complete mapping.
respectively.

Synopsis
--------
::
  
  mktable -i <path> -o <path>

Options
-------

===== ====================== ======== ======================================================
Short Long                   Required Description
===== ====================== ======== ======================================================
-i    --input <path>         yes      Input CSV restructure file produced by the delta tool
-o    --output <path>        yes      Output path for the two LaTeX file
===== ====================== ======== ======================================================

Description
-----------

This tool only helps with producing large LaTeX tables based on the CSV files from
the restructuring tool.
