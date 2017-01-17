package response;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.support.broadcast.BroadcastResponse;
import org.elasticsearch.action.ShardOperationFailedException;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.xcontent.StatusToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.RestStatus;

public class MahasiswaResponse extends BroadcastResponse implements StatusToXContent{

	@Override
	public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
		builder.field("id", id);
		builder.field("nama", nama);
		builder.field("jurusan", jurusan);
		builder.field("kelas", kelas);
		return builder;
	}

	@Override
	public RestStatus status() {
		// TODO Auto-generated method stub
		return RestStatus.OK;
	}
	
	private int id;
	private String nama;
	private String jurusan;
	private String kelas;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getJurusan() {
		return jurusan;
	}

	public void setJurusan(String jurusan) {
		this.jurusan = jurusan;
	}

	public String getKelas() {
		return kelas;
	}

	public void setKelas(String kelas) {
		this.kelas = kelas;
	}

	
	
	public MahasiswaResponse(int totalShards, int successfulShards, int failedShards,
            List<ShardOperationFailedException> shardFailures,
            int id, String nama, String jurusan, String kelas) {
		super(totalShards, successfulShards, failedShards, shardFailures);
		this.id = id;
		this.nama = nama;
		this.jurusan = jurusan;
		this.kelas = kelas;
	}

	 @Override
	    public void readFrom(StreamInput in) throws IOException {
	        super.readFrom(in);
	        id = in.readInt();
	        nama = in.readOptionalString();
	        jurusan = in.readOptionalString();
	        kelas = in.readOptionalString();
	    }

	 @Override
	    public void writeTo(StreamOutput out) throws IOException {
	        super.writeTo(out);
	        out.writeInt(id);
	        out.writeOptionalString(nama);
	        out.writeOptionalString(jurusan);
	        out.writeOptionalString(kelas);
	    }

	public MahasiswaResponse(){
		
	}
}
