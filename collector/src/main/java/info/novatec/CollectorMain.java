package info.novatec;

import info.novatec.client.BusinessServiceClient;
import info.novatec.service.CollectiorServiceImpl;
import io.advantageous.qbit.admin.ManagedServiceBuilder;
import io.advantageous.qbit.client.Client;
import io.advantageous.qbit.client.ClientBuilder;
import io.advantageous.qbit.server.EndpointServerBuilder;

public class CollectorMain {

	public static void main(String[] args) {
		final ManagedServiceBuilder managedServiceBuilder = ManagedServiceBuilder.managedServiceBuilder()
				.setRootURI("/").setPort(8002);

		final ClientBuilder clientBuilder = ClientBuilder.clientBuilder();
		Client websoketClient = clientBuilder.setHost("localhost").setPort(8001).setProtocolBatchSize(1).build();
		BusinessServiceClient proxy = websoketClient.createProxy(BusinessServiceClient.class, "business");
		websoketClient.start();


		EndpointServerBuilder endpointServerBuilder = managedServiceBuilder.getEndpointServerBuilder();
		endpointServerBuilder.addService("collector", new CollectiorServiceImpl(proxy));
		endpointServerBuilder.build().start();

		managedServiceBuilder.getAdminBuilder().setPort(7778).build().startServer();
	}

}
