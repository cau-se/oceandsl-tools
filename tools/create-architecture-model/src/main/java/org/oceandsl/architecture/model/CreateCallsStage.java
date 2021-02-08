/**
 * 
 */
package org.oceandsl.architecture.model;

import java.util.HashMap;
import java.util.Map;

import kieker.common.record.flow.IFlowRecord;
import kieker.common.record.flow.trace.operation.BeforeOperationEvent;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

/**
 * @author reiner
 *
 */
public class CreateCallsStage extends AbstractConsumerStage<IFlowRecord> {
	
	private final Map<Long,BeforeOperationEvent> cachedTraces = new HashMap<>();
	private OutputPort<Call> outputPort = this.createOutputPort(Call.class);

	@Override
	protected void execute(IFlowRecord element) throws Exception {
		if (element instanceof BeforeOperationEvent) {
			BeforeOperationEvent currentEvent = (BeforeOperationEvent)element;
			BeforeOperationEvent previousEvent = cachedTraces.get(currentEvent.getTraceId());
			if (previousEvent != null) {
				this.outputPort.send(new Call(ECallType.BEFORE, 
						previousEvent.getClassSignature(),
						previousEvent.getOperationSignature(),
						currentEvent.getClassSignature(),
						currentEvent.getOperationSignature()));
				
			}
			cachedTraces.put(currentEvent.getTraceId(), currentEvent);
		}
	}
	

	public OutputPort<Call> getOutputPort() {
		return outputPort;
	}

}
