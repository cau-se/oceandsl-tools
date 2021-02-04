/**
 * 
 */
package org.oceandsl.log.rewriter;

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
	
	private OutputPort<IMonitoringRecord> outputPort = this.createOutputPort(IMonitoringRecord.class);
	
	private File addrlineExecutable;
	private Map<String,AddrOutput> addressMap = new HashMap<String,AddrOutput>();
	private File modelExecutable;
	
	public RewriteBeforeAndAfterEventsStage(File addrLineExecutable, File modelExecutable) {
		this.addrlineExecutable = addrLineExecutable;
		this.modelExecutable = modelExecutable;
	}
	
	@Override
	protected void execute(IMonitoringRecord element) throws Exception {
		if (element instanceof BeforeOperationEvent) {
			BeforeOperationEvent before = (BeforeOperationEvent)element;
			AddrOutput rewriteInfo = findRewriteInfo(before.getOperationSignature());
			this.outputPort.send(new BeforeOperationEvent(before.getTimestamp(), before.getTraceId(), before.getOrderIndex(),
					rewriteInfo.name,"COMPONENT"));
		} else if (element instanceof AfterOperationEvent) {
			AfterOperationEvent before = (AfterOperationEvent)element;
			AddrOutput rewriteInfo = findRewriteInfo(before.getOperationSignature());
			this.outputPort.send(new AfterOperationEvent(before.getTimestamp(), before.getTraceId(), before.getOrderIndex(),
					rewriteInfo.name,"COMPONENT"));
		} else
			this.outputPort.send(element);
	}
	
	private AddrOutput findRewriteInfo(String address) throws IOException, InterruptedException {
		AddrOutput addrOutput = addressMap.get(address);
		if (addrOutput == null) {
			Process process = Runtime.getRuntime().exec(String.format("%s -e %s -p -C -f %s",
					this.addrlineExecutable.getCanonicalPath(),
					this.modelExecutable, address));
			process.waitFor();
			new BufferedReader(new InputStreamReader(process.getErrorStream())).lines().forEach(new Consumer<String>() {
				@Override
				public void accept(String arg0) {
					System.err.println(arg0);		
				}
			});
			
			new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(new Consumer<String>() {

				final Pattern pattern = Pattern.compile("^(\\w+) at ([\\w\\?/\\.\\-]+):([\\d\\?]*)$");
				
				@Override
				public void accept(String string) {
					Matcher matcher = this.pattern.matcher(string);
					if (matcher.find()) {
						Integer linenumber = matcher.group(3).equals("?")?null:Integer.parseInt(matcher.group(3));
						addressMap.put(address,new AddrOutput(matcher.group(1), matcher.group(2), linenumber));
					}
				}
				
			});
			return addressMap.get(address);
		} else
			return addrOutput;
	}

	class AddrOutput {

		private final String name;
		private final String filename;
		private final Integer linenumber;
		
		public AddrOutput(String name, String filename, Integer linenumber) {
			this.name = name;
			this.filename = filename;
			this.linenumber = linenumber;
		}

		public String getName() {
			return name;
		}
		
		public String getFilename() {
			return filename;
		}
		
		public Integer getLinenumber() {
			return linenumber;
		}
	}

	public OutputPort getOutputPort() {
		return this.outputPort;
	}

}
