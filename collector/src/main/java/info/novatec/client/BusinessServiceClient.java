package info.novatec.client;

import io.advantageous.reakt.promise.Promise;

public interface BusinessServiceClient {
	
	Promise<String>  getBusinessTransaction(String trace);
}
