package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Supplier;

import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.broadcast.TransportBroadcastAction;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ShardOperationFailedException;
import org.elasticsearch.action.support.DefaultShardOperationFailedException;
import org.elasticsearch.action.support.broadcast.BroadcastShardOperationFailedException;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.block.ClusterBlockLevel;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.routing.GroupShardsIterator;
import org.elasticsearch.cluster.routing.ShardRouting;
import org.elasticsearch.cluster.service.ClusterService;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLoggerFactory;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.indices.IndicesService;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import request.MahasiswaRequest;
import request.ShardMahasiswaRequest;
import response.MahasiswaResponse;
import response.ShardMahasiswaResponse;

public class TransportMahasiswaAction extends
		TransportBroadcastAction<MahasiswaRequest, MahasiswaResponse, ShardMahasiswaRequest, ShardMahasiswaResponse> {



	// private final static ESLogger logger =
	// ESLoggerFactory.getLogger(TransportMahasiswaAction.class.getName());

	private final IndicesService indicesService;
	
	@Inject
	protected TransportMahasiswaAction(Settings settings, String actionName, ThreadPool threadPool,
			ClusterService clusterService, TransportService transportService, ActionFilters actionFilters,
			IndexNameExpressionResolver indexNameExpressionResolver, Supplier<MahasiswaRequest> request,
			Supplier<ShardMahasiswaRequest> shardRequest, String shardExecutor,IndicesService indicesService) {
		super(settings, MahasiswaAction.NAME, threadPool, clusterService, transportService, actionFilters, indexNameExpressionResolver,
				request, shardRequest, ThreadPool.Names.GENERIC); //error
		this.indicesService = indicesService;
	}

//	@Inject
//	public TransportMahasiswaAction(Settings settings, ThreadPool threadPool, ClusterService clusterService,
//			TransportService transportService, IndicesService indicesService, ActionFilters actionFilters,
//			IndexNameExpressionResolver indexNameExpressionResolver) {
//		super(settings, MahasiswaAction.NAME, threadPool, clusterService, transportService, actionFilters,
//				indexNameExpressionResolver, MahasiswaRequest.class, ShardMahasiswaRequest.class,
//				ThreadPool.Names.GENERIC);
//		this.indicesService = indicesService;
//	}

	@Override
	protected ClusterBlockException checkGlobalBlock(ClusterState state, MahasiswaRequest req) {
		 return state.blocks().globalBlockedException(ClusterBlockLevel.METADATA_READ);
	}

	@Override
	protected ClusterBlockException checkRequestBlock(ClusterState state, MahasiswaRequest request, String[] concreteIndices) {
		return state.blocks().indicesBlockedException(ClusterBlockLevel.METADATA_READ, concreteIndices);
	}

	
	@Override
	protected ShardMahasiswaRequest newShardRequest(int numShards, ShardRouting shard, MahasiswaRequest request) {
		// TODO Auto-generated method stub
		return new ShardMahasiswaRequest(shard.getId(), shard.shardId(), request);
	}

	@Override
	protected ShardMahasiswaResponse newShardResponse() {
		// TODO Auto-generated method stub
		return new ShardMahasiswaResponse();
	}

	@Override
	protected ShardMahasiswaResponse shardOperation(ShardMahasiswaRequest arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GroupShardsIterator shards(ClusterState clusterState, MahasiswaRequest arg1, String[] concreteIndices) {
		// TODO Auto-generated method stub
		return clusterState.routingTable().activePrimaryShardsGrouped(concreteIndices, true);
	}
	
	@Override
    protected MahasiswaResponse newResponse(MahasiswaRequest request, AtomicReferenceArray shardsResponses, ClusterState clusterState) {
        int successfulShards = 0;
        int failedShards = 0;
        List<ShardOperationFailedException> shardFailures = null;
        int id = 0;
        String nama = null;
        String jurusan = null;
        String kelas = null;
        for (int i = 0; i < shardsResponses.length(); i++) {
            Object shardResponse = shardsResponses.get(i);
            if (shardResponse instanceof BroadcastShardOperationFailedException) {
                BroadcastShardOperationFailedException e = (BroadcastShardOperationFailedException) shardResponse;
//                logger.error(e.getMessage(), e);
                failedShards++;
                if (shardFailures == null) {
                    shardFailures = new ArrayList<ShardOperationFailedException>();
                }
                shardFailures.add(new DefaultShardOperationFailedException(e));
            } else {
                if (shardResponse instanceof ShardMahasiswaResponse) {
                    successfulShards++;
                    ShardMahasiswaResponse resp = (ShardMahasiswaResponse) shardResponse;
                    id = resp.getId();
                    nama = resp.getNama();
                    jurusan = resp.getJurusan();
                    kelas = resp.getKelas();
                }
            }
        }

        return new MahasiswaResponse(shardsResponses.length(), successfulShards, failedShards, shardFailures, id, nama, jurusan, kelas); 
    }

}
