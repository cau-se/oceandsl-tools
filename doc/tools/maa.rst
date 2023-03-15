Model Architecture Analysis
===========================

Analyze an existing architecture model and identify interfaces and create
component hierarchy. It reads in an architectural model and performs up to
two operations on the model:
(a) Identifying interfaces
(b) generate hierarchical components.
The latter requires a map file which maps operations to components.

===== ====================== ======== ======================================================
Short Long                   Required Description
===== ====================== ======== ======================================================
-i    --input                yes      Input architecture model directory
-o    --output               yes      Output architecture model directory
-I    --compute-interfaces            Compute interfaces based on aggregated invocations
-g    --hierarchy-grouping            Generate a component hierarchy based on a map file
-c    --operation-calls               Output the list of calls
-s    --component-statistics          Output numerous component statistics
-gs   --separator                     Sparator string for CSV inputs
===== ====================== ======== ======================================================

For the option **hierarchy-grouping** you need to specify at least one map file
containing the function module mapping.



