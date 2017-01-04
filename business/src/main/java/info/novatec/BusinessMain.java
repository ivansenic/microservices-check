package info.novatec;

import io.advantageous.qbit.admin.ManagedServiceBuilder;
import io.advantageous.qbit.server.EndpointServerBuilder;

public class BusinessMain {

	public static void main(String[] args) {
		final ManagedServiceBuilder managedServiceBuilder = ManagedServiceBuilder.managedServiceBuilder()
				.setRootURI("/").setPort(8001);

		EndpointServerBuilder endpointServerBuilder = managedServiceBuilder.getEndpointServerBuilder();
		endpointServerBuilder.addService("business", new BusinessServiceImpl());
		endpointServerBuilder.build().start();;

		managedServiceBuilder.getAdminBuilder().build().startServer();
	}
}
