package info.novatec.service;

import static io.advantageous.qbit.annotation.QueueCallbackType.EMPTY;
import static io.advantageous.qbit.annotation.QueueCallbackType.IDLE;
import static io.advantageous.qbit.annotation.QueueCallbackType.LIMIT;

import info.novatec.client.BusinessServiceClient;
import io.advantageous.qbit.annotation.PathVariable;
import io.advantageous.qbit.annotation.QueueCallback;
import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.client.RemoteTCPClientProxy;
import io.advantageous.qbit.reactive.Callback;
import io.advantageous.qbit.service.ServiceProxyUtils;
import io.advantageous.reakt.promise.Promise;

@RequestMapping("/collect")
public class CollectiorServiceImpl {

	BusinessServiceClient bsClient;

	public CollectiorServiceImpl(BusinessServiceClient client) {
		this.bsClient = client;
		
		for (Class<?> in : client.getClass().getInterfaces()) {
			System.out.println(in.getName());
		}
	}

	@RequestMapping("/{trace}")
	public void collect(Callback<String> clientCallback, @PathVariable("trace") String trace) {
		if (!((RemoteTCPClientProxy) bsClient).connected()) {
			clientCallback.accept("No server.");
			return;
		}
		
		Promise<String> businessTransactionPromise = bsClient.getBusinessTransaction(trace);
		businessTransactionPromise.thenCallback(clientCallback).invoke();
	}
	
    @QueueCallback({EMPTY, IDLE, LIMIT})
    public void process() {
    	ServiceProxyUtils.flushServiceProxy(bsClient);
    }

}
