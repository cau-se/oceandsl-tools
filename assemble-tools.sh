#!/bin/bash

BASE_DIR=$(cd "$(dirname "$0")"; pwd)

rm -rf $BASE_DIR/build/oceandsl-tools

mkdir -p $BASE_DIR/build/oceandsl-tools
cd $BASE_DIR/build/oceandsl-tools

mkdir bin
mkdir lib

for I in cmi dar maa mop mvis sar relabel ; do
	unzip $BASE_DIR/tools/$I/build/distributions/$I.zip
	mv $I/lib/* lib/
	mv $I/bin/* bin/
	rm -rf $I
done

cd ..
tar -cvzpf oceandsl-tools.tgz oceandsl-tools

# end

