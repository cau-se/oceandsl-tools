# Overview

This package is a java side implementation of architecture discovery of dataflow analysis of Earth System Models (ESM).
In the global package settings as well as creation of a Teetime config is added and called by arg:

``-bsc-j`` or ``--bsc-dataflow-input``

To successfully read and convert test data please use CSV files with line inputs like:

filename, block, read/write, target
"componentX;xsub1;READ;ysub1"

and

filename, blockname, type
"componentX;xcom1;COMMON;
 componentX;xsub2;SUBROUTINE;
 componentX;ximportComp;IMPORT;
 componentY;ysub1;SUBROUTINE;
 componentY;ysub2;"SUBROUTINE;

If you want to start a full dataflow analysis of an ESM please start an analysis run of the tool
[ESM Dataflow Analysis] (https://cau-git.rz.uni-kiel.de/ifi-ag-se/oceandsl/esm-dataflow-analysis)
Afterwards use the oceandsl-sar tool to handle the provided data.

Command:
```-bsc-j ".\\test.csv" -bsc-c ".\\testComponents.csv" -o "./output/" -E "testrun A" -l "ich bin ein Label"```

# Visualization:
todo

# Design

In order to fit in the given structure of oceandsl-sar, the bsc.dataflow uses the TeeTime-framework to fulfill
data transformation. The entry point is a separate configuration class, connected to the main class of oceandsl-sar.
With entry flags defined in Settings.java it can be accessed with two flags for a successful reconstruction.

1)  -bsc-j "dataflow.csv"   -- generated file containing all dataflow found in an ESM
2)  -bsc-c "component.csv"  -- generated file containing all subroutines and common blocks

(...)

## TeeTimeBscDataflowConfiguration.java

This class starts the data extraction by calling multiple defined Stages and connecting them via TeeTime-Framework ports.
In the beginning of the constructor a lookupTable for routines and storage boxes is created, which will be used later on
to configure target components. Secondly the first stage is created: "CSVBscDataflowReaderStage". This stage is used to
retrieve dataflow results given by the '-bsc-j' command. It is stored in a "DataTransferObject"(DTO) to pass upcoming Stages.
In the following stage named "PreConfigurationStage" a passed DTO is added by the target component of the dataflow stored.