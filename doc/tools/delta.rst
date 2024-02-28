.. _kieker-tools-delta:

Architecture Delta
==================

Process restructuring information to compute deltas between architecture
models and store the result in different output formats

===== ====================== ======== ======================================================
Short Long                   Required Description
===== ====================== ======== ======================================================
-i    --input                yes      input restructure XMI file path
-o    --output               yes      output restructure information path and filename
                                      without extension
      --eol                  no       specify which kind of end of line character should be
                                      used in CSV files. Default is system's default EOL
===== ====================== ======== ======================================================

The `delta` tool reads an restructuring file and outputs restructure information in YAML and CSV
format. The `--eol` option allows to specify which line ending is used for the CSV files.
Both outputs are written to the output directory.

The output CSV file has three columns for:
- source component
- target component
- operation

Each triple describes that the given operation is moved from one component (source) to 
another component (target).

The YAML model comprises of component representing the target component and operation entries
with operation names and the source component.

Examples
--------

Lets assume we have a model `recovered` and a derived model `optimized`. Then we can
generate restructure files with **restructuring** and process the result as follows:

```bash
oceandsl-tools/bin/restructuring -s kuhn -i recovered optimized -o restructured
oceandsl-tools/bin/delta -i restructured/1.xmi -o outputs
```

The **restructuring** tool will produce for each optimized model a restructuring file.
It assumes the first file is the baseline.
