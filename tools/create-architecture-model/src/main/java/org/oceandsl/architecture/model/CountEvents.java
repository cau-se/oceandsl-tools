/**
 *
 */
package org.oceandsl.architecture.model;

import teetime.stage.basic.AbstractTransformation;

/**
 * @author reiner
 *
 */
public class CountEvents<T> extends AbstractTransformation<T, T> {

    private long counter;
    private final long interval;

    public CountEvents(final long interval) {
        this.interval = interval;
    }

    @Override
    protected void execute(final T element) throws Exception {
        this.counter++;
        if ((this.counter % this.interval) == 0) {
            this.logger.info("Received {} events.", this.counter);
        }
        this.outputPort.send(element);
    }

    @Override
    protected void onTerminating() {
        this.logger.info("Received {} events.", this.counter);
        super.onTerminating();
    }
}
