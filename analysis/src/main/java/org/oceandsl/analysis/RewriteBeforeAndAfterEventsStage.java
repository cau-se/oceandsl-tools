/**
 *
 */
package org.oceandsl.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.flow.trace.operation.AfterOperationEvent;
import kieker.common.record.flow.trace.operation.BeforeOperationEvent;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

/**
 * @author reiner
 *
 */
public class RewriteBeforeAndAfterEventsStage extends AbstractConsumerStage<IMonitoringRecord> {

    private final OutputPort<IMonitoringRecord> outputPort = this.createOutputPort(IMonitoringRecord.class);

    private final File addrlineExecutable;
    private final Map<String, AddrOutput> addressMap = new HashMap<>();
    private final File modelExecutable;

    public RewriteBeforeAndAfterEventsStage(final File addrLineExecutable, final File modelExecutable) {
        this.addrlineExecutable = addrLineExecutable;
        this.modelExecutable = modelExecutable;
    }

    @Override
    protected void execute(final IMonitoringRecord element) throws Exception {
        if (element instanceof BeforeOperationEvent) {
            final BeforeOperationEvent before = (BeforeOperationEvent) element;
            final AddrOutput rewriteInfo = this.findRewriteInfo(before.getOperationSignature());
            this.outputPort.send(new BeforeOperationEvent(before.getTimestamp(), before.getTraceId(),
                    before.getOrderIndex(), rewriteInfo.name, rewriteInfo.getFilename()));
        } else if (element instanceof AfterOperationEvent) {
            final AfterOperationEvent before = (AfterOperationEvent) element;
            final AddrOutput rewriteInfo = this.findRewriteInfo(before.getOperationSignature());
            this.outputPort.send(new AfterOperationEvent(before.getTimestamp(), before.getTraceId(),
                    before.getOrderIndex(), rewriteInfo.name, rewriteInfo.getFilename()));
        } else {
            this.outputPort.send(element);
        }
    }

    private AddrOutput findRewriteInfo(final String address) throws IOException, InterruptedException {
        final AddrOutput addrOutput = this.addressMap.get(address);
        if (addrOutput == null) {
            final Process process = Runtime.getRuntime().exec(String.format("%s -e %s -p -C -f %s",
                    this.addrlineExecutable.getCanonicalPath(), this.modelExecutable, address));
            process.waitFor();
            new BufferedReader(new InputStreamReader(process.getErrorStream())).lines().forEach(new Consumer<String>() {
                @Override
                public void accept(final String arg0) {
                    System.err.println(arg0);
                }
            });

            new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(new Consumer<String>() {

                final Pattern pattern = Pattern.compile("^(\\w+) at ([\\w\\?/\\.\\-]+):([\\d\\?]*)$");

                @Override
                public void accept(final String string) {
                    final Matcher matcher = this.pattern.matcher(string);
                    if (matcher.find()) {
                        final Integer linenumber = matcher.group(3).equals("?") ? null
                                : Integer.parseInt(matcher.group(3));
                        RewriteBeforeAndAfterEventsStage.this.addressMap.put(address,
                                new AddrOutput(matcher.group(1), matcher.group(2), linenumber));
                    }
                }

            });
            return this.addressMap.get(address);
        } else {
            return addrOutput;
        }
    }

    class AddrOutput {

        private final String name;
        private final String filename;
        private final Integer linenumber;

        public AddrOutput(final String name, final String filename, final Integer linenumber) {
            this.name = name;
            this.filename = filename;
            this.linenumber = linenumber;
        }

        public String getName() {
            return this.name;
        }

        public String getFilename() {
            return this.filename;
        }

        public Integer getLinenumber() {
            return this.linenumber;
        }
    }

    public OutputPort<IMonitoringRecord> getOutputPort() {
        return this.outputPort;
    }

}
