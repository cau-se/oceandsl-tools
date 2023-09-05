#!/bin/bash

BASE_DIR=$(cd "$(dirname "$0")"; pwd)

VERSION="1.3.0-SNAPSHOT"

rm -rf $BASE_DIR/build/oceandsl-tools

mkdir -p $BASE_DIR/build/oceandsl-tools
cd $BASE_DIR/build/oceandsl-tools

mkdir bin
mkdir lib

for I in allen-upper-limit cmi dar delta fxca maa mktable mop mt mvis sar relabel restructuring ; do
	unzip $BASE_DIR/tools/$I/build/distributions/$I-$VERSION.zip
	mv $I-$VERSION/lib/* lib/
	mv $I-$VERSION/bin/* bin/
	rm -rf $I-$VERSION
done

cd ..
tar -cvzpf oceandsl-tools.tgz oceandsl-tools

# end

