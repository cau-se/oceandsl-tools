/**
 *
 */
package org.oceandsl.architecture.model;

import java.util.ArrayList;
import java.util.List;

import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

/**
 * @author reiner
 *
 */
public class CountUniqueCalls extends AbstractConsumerStage<Call> {

    private final List<StoredCall> storedCalls = new ArrayList<>();
    private final OutputPort<List<StoredCall>> outputPort = this.createOutputPort();

    @Override
    protected void execute(final Call element) throws Exception {
        for (final StoredCall storedCall : this.storedCalls) {
            if (storedCall.getCall().equals(element)) {
                storedCall.increment();
                return;
            }
        }
        this.storedCalls.add(new StoredCall(1, element));
    }

    private void printStore() {
        for (final StoredCall storedCall : this.storedCalls) {
            final Call call = storedCall.getCall();
            this.logger.info(
                    String.format("%s:%s -> %s:%s (%d)", call.getFromClassSignature(), call.getFromOperationSignature(),
                            call.getToClassSignature(), call.getToOperationSignature(), storedCall.getCount()));
        }
    }

    @Override
    protected void onTerminating() {
        this.printStore();
        this.outputPort.send(this.storedCalls);
        super.onTerminating();
    }

    private class StoredCall {
        private long count;
        private final Call call;

        public StoredCall(final long count, final Call call) {
            this.count = count;
            this.call = call;
        }

        public Call getCall() {
            return this.call;
        }

        public void increment() {
            this.count++;
        }

        public long getCount() {
            return this.count;
        }
    }

    public OutputPort<List<StoredCall>> getOutputPort() {
        return this.outputPort;
    }

}
