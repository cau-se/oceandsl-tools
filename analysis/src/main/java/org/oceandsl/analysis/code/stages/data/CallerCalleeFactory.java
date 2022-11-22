package org.oceandsl.analysis.code.stages.data;

import org.oceandsl.analysis.code.stages.ICsvRecordFactory;

public class CallerCalleeFactory implements ICsvRecordFactory<CallerCallee> {

    @Override
    public CallerCallee createRecord(final String[] headerLabels, final String[] values) {
        return new CallerCallee(values[0], values[1], values[2], values[3], values[4], values[5]);
    }

}
