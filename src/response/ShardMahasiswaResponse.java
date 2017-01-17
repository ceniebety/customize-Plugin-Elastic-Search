package response;

import java.io.IOException;

import org.elasticsearch.action.support.broadcast.BroadcastShardResponse;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.index.shard.ShardId;

public class ShardMahasiswaResponse extends BroadcastShardResponse{

	private String index;
	private int id;
	private String nama;
	private String jurusan;
	private String kelas;
	
	public ShardMahasiswaResponse() {
	 }
	
	public ShardMahasiswaResponse(String index, ShardId shardId, int id, String nama, String jurusan, String kelas) {
        super(shardId);
        this.index = index;
        this.id=id;
        this.nama=nama;
        this.jurusan = jurusan;
        this.kelas = kelas;
    }

    public String getIndex() {
        return index;
    }
    
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

	public void setIndex(String index) {
		this.index = index;
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
}
