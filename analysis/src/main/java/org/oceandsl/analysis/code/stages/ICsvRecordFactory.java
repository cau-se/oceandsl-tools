package org.oceandsl.analysis.code.stages;

public interface ICsvRecordFactory<T extends ICsvRecord> {

    T createRecord(String[] headerLabels, String[] values);
}
