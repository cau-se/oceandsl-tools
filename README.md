# OceanDSL Tools

Project containing all Java-based tools developed in OceanDSL.

## Build

All java tools can be build with
`./gradlew build`

## Execute

Each tool has its own set of parameters and can be found in
`doc/tools/$TOOL_NAME`.

Current tools:
- rewrite-log-entries
- dar (dynamic architecture reconstruction) read Kieker log data and reconstruct the architecture
- sar (static architecutre reconstruction) read calls and data access files to reconstruct the architecture
- mop (model operation) merge, diff, diff-tag, subtract, subtract-tag models
- mvis (model visualization) visualize and compute metrics for a given architecture model
- pp-static-log





