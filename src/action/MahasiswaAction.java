package action;

import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

import request.MahasiswaRequest;
import response.MahasiswaResponse;

public class MahasiswaAction extends Action<MahasiswaRequest, MahasiswaResponse, MahasiswaRequestBuilder>{
	public static final String NAME = "mahasiswa";

    public static final MahasiswaAction INSTANCE = new MahasiswaAction();
    
	protected MahasiswaAction() {
		super(NAME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MahasiswaRequestBuilder newRequestBuilder(ElasticsearchClient client) {
		return new MahasiswaRequestBuilder(client);
	}

	@Override
	public MahasiswaResponse newResponse() {
		// TODO Auto-generated method stub
		return new MahasiswaResponse();
	}

}
