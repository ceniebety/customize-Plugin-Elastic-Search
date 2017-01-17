package action;

import org.elasticsearch.action.Action;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

import request.MahasiswaRequest;
import response.MahasiswaResponse;

public class MahasiswaRequestBuilder
		extends ActionRequestBuilder<MahasiswaRequest, MahasiswaResponse, MahasiswaRequestBuilder> {

	protected MahasiswaRequestBuilder(ElasticsearchClient client) {
		super(client, MahasiswaAction.INSTANCE, new MahasiswaRequest());
	}

	 public MahasiswaRequestBuilder setNama(String nama) {
	        request.setNama(nama);
	        return this;
	    }
}
