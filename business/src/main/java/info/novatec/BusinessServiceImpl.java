package info.novatec;

import io.advantageous.qbit.annotation.PathVariable;
import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.reactive.Callback;

@RequestMapping("/business")
public class BusinessServiceImpl {

	@RequestMapping("/tx/{trace}")
	public void getBusinessTransaction(Callback<String> clientCallback, @PathVariable("trace") String trace) {
		String result = trace.substring(trace.length() / 2);
		clientCallback.accept(result);
	}
}
