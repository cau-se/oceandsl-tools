# OceanDSL Tools

This repository contains all the JAva-based tools developed for
OceanDSL. Detailed information on how to use these tools, file formats and
related information can be found in the `doc` directory. 

Below be describe how to build, package and execute the tools.

## Build

All java tools can be build with
`./gradlew build`

## Package

Call `./assemble-tools.sh` to produce one tar bundle containing all tools and its
libraries. This archive uses way less space than the individual tools, as they
share many of the libraries.

## Execute

Each tool has its own set of parameters and can be found in
`doc/tools/$TOOL_NAME.rst`.
You may find the complete documentation under `doc/index.rst`

**Current tools:**
- allen-upper-limit compute the maximal upper complexity limit for a given architecture
- cmi check the integrity of a model
- dar (dynamic architecture reconstruction) read Kieker log data and reconstruct the architecture
- delta produce architecture model deltas in different formats based on *restructuring* output
- fxca process fxtran output and generate CSV files with calls, dataflows and storage info
- maa model architecture analysis tool
- mktable generate LaTeX tables representing architecture changes based on results from delta
- mop (model operation) merge, diff, diff-tag, subtract, subtract-tag models
- mvis (model visualization) visualize and compute metrics for a given architecture model
- pp-static-log preprocess log files from static analysis (deprecated)
- relabel allows to change labels assigned to architecture model elements
- restructuring compute the necessary steps to change one architecture into a new one
- rewrite-log-entries preprocess kieker log files from Kieker4C monitoring to resolve function references
- sar (static architecutre reconstruction) read calls and data access files to reconstruct the architecture
