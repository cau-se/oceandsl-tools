Overview - OceanDSL Tools and Analysis
======================================

The OceanDSL project aims to create DSLs to support the separation of concerns
in scientific models, including parameter management, configuration, and
parameter selection. Therefore, we need a good understanding of the code of
scientific models and therefore, tools to recover architecture and behavior
of these models.

Thus, we create a growing set of tools to perform architecture recovery or
discovery when there never was an architecture planned. Analyzes of the
architecture models and support for optimizing the architecture.

To change the logging setup you can either change that file or define
additional options with the JAVA_OPTS environment variable, e.g.,

``export JAVA_OPTS="-Dlogback.configurationFile=/full/path/to/logger/config/logback-trace.groovy"``
or use the tool specific ``_OPTS`` variable, e.g.,
``CMI_OPTS`` for the ``cmi`` tool.

Furthermore, you can use both variables to pass additional JVM
parameters and options to a tool.

-  :ref:`kieker-tools-allen-upper-limit`
-  :ref:`kieker-tools-cmi`
-  :ref:`kieker-tools-dar`
-  :ref:`kieker-tools-delta`
-  :ref:`kieker-tools-fxca`
-  :ref:`kieker-tools-maa`
-  :ref:`kieker-tools-mktable`
-  :ref:`kieker-tools-mop`
-  :ref:`kieker-tools-mt`
-  :ref:`kieker-tools-mvis`
-  :ref:`kieker-tools-relabel`
-  :ref:`kieker-tools-rewrite-log-entries`
-  :ref:`kieker-tools-sar`
-  :ref:`kieker-tools-static-analysis-formats`

Please note these tools are also integrated into the Kieker monitoring and architecture analysis framework. Thus, newer versions of the tools can be found there.

https://kieker-monitoring.readthedocs.io/en/latest/
