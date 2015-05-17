package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;

public class Client {
	private enum StringFunnel implements Funnel<String> {
		INSTANCE;

		public void funnel(String from, PrimitiveSink into) {
			into.putString(from);
		}

		@Override
		public String toString() {
			return "Funnels.stringFunnel()";
		}
	}

	public static void main(String[] args) throws Exception {
		
		String[] shardedInstances = new String[3];
		shardedInstances[0] = "http://localhost:3000";
		shardedInstances[1] = "http://localhost:3001";
		shardedInstances[2] = "http://localhost:3002";

		String[] input = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		List<String> serverlist = new ArrayList<String>();
		Map<String, CacheServiceInterface> caches = new HashMap<String, CacheServiceInterface>();
		for (int i = 0; i < 3; i++) {
			serverlist.add(shardedInstances[i]);
			caches.put(shardedInstances[i], new DistributedCacheService(shardedInstances[i]));
		}

		

		RendezvousHash<Integer, String> rh = new RendezvousHash<Integer, String>(
				Hashing.murmur3_128(), Funnels.integerFunnel(), StringFunnel.INSTANCE,
				serverlist);
		System.out.println("Initiating the Rendezvous Cache Client...");
		for (int i = 0; i < input.length; i++) {
			String cachename = rh.get(i + 1);
			caches.get(cachename).put(i + 1, input[i]);
			System.out.println("put(" + (i + 1) + " => " + input[i] + ")");
		}

		for (int i = 0; i < input.length; i++) {
			String cachename = rh.get(i + 1);
			String value = caches.get(cachename).get(i + 1);
			System.out.println("get(" + (i + 1) + " => " + value + ")");
		}


	}
}
