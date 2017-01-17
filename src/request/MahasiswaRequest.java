package request;

import java.io.IOException;

import org.elasticsearch.action.support.broadcast.BroadcastRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

public class MahasiswaRequest extends BroadcastRequest<MahasiswaRequest> {

	private String nama;

	public MahasiswaRequest() {

	}

	public MahasiswaRequest(String... indices) {
		super(indices);
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Override
	public void readFrom(StreamInput in) throws IOException {
		super.readFrom(in);
		nama = in.readOptionalString();
	}

	@Override
	public void writeTo(StreamOutput out) throws IOException {
		super.writeTo(out);
		out.writeOptionalString(nama);

	}

	static MahasiswaRequest from(StreamInput in) throws IOException {
		MahasiswaRequest request = new MahasiswaRequest();
		request.readFrom(in);
		return request;
	}

	// @Override
	// public ActionRequestValidationException validate() {
	// ActionRequestValidationException validationException = null;
	// if (term == null || term.isEmpty()) {
	// validationException = addValidationError("text is missing", null);
	// }
	// return validationException;
	// }
}
