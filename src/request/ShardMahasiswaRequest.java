package request;

import java.io.IOException;

import org.elasticsearch.action.support.broadcast.BroadcastShardRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.index.shard.ShardId;

public class ShardMahasiswaRequest extends BroadcastShardRequest{
	private int index;

    private MahasiswaRequest request;

    ShardMahasiswaRequest() {
    }

    public ShardMahasiswaRequest(int index, ShardId shardId, MahasiswaRequest request) {
        super(shardId, request);
        this.index = index;
        this.request = request;
    }

    public int getIndex() {
        return index;
    }

    public MahasiswaRequest getRequest() {
        return request;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        index = in.readInt();
        request = MahasiswaRequest.from(in);
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeInt(index);
        request.writeTo(out);
    }
}
