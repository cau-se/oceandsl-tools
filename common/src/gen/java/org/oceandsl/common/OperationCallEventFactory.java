package org.oceandsl.common;

import kieker.common.exception.RecordInstantiationException;
import kieker.common.record.factory.IRecordFactory;
import kieker.common.record.io.IValueDeserializer;

/**
 * @author Reiner Jung
 * 
 * @since 1.0
 */
public final class OperationCallEventFactory implements IRecordFactory<OperationCallEvent> {

    @Override
    public OperationCallEvent create(final IValueDeserializer deserializer) throws RecordInstantiationException {
        return new OperationCallEvent(deserializer);
    }

    @Override
    public String[] getValueNames() {
        return OperationCallEvent.VALUE_NAMES; // NOPMD
    }

    @Override
    public Class<?>[] getValueTypes() {
        return OperationCallEvent.TYPES; // NOPMD
    }

    public int getRecordSizeInBytes() {
        return OperationCallEvent.SIZE;
    }
}
