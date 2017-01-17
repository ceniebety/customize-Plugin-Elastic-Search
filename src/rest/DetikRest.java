package rest;

import static org.elasticsearch.rest.RestRequest.Method.GET;

import java.io.IOException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;

import request.MahasiswaRequest;

public class DetikRest extends BaseRestHandler{

	protected DetikRest(Settings settings, Client client, RestController controller) {
		super(settings);
		controller.registerHandler(GET, "/_detik", this);
        controller.registerHandler(GET, "/{index}/_detik", this);
	}

	@Override
	protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	 public void handleRequest(final RestRequest request, final RestChannel channel, final Client client) {
	        try {
	        	MahasiswaRequest mahasiswaRequest = new MahasiswaRequest(Strings.splitStringByCommaToArray(request.param("index")));
	        	mahasiswaRequest.setNama(request.param("nama"));
	        }catch (Exception e) {
				// TODO: handle exception
			}
	    }
}