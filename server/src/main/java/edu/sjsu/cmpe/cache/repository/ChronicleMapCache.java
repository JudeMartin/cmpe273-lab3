package edu.sjsu.cmpe.cache.repository;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;


import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.util.List;



import net.openhft.chronicle.map.ChronicleMapBuilder;
import net.openhft.chronicle.map.ChronicleMap;
import edu.sjsu.cmpe.cache.domain.Entry;


public class ChronicleMapCache implements CacheInterface {
	ChronicleMap<Long, Entry> chronicleMap;

	public ChronicleMapCache(String suff) throws IOException {
		chronicleMap = ChronicleMapBuilder.of(Long.class, Entry.class)
				.createPersistedTo(new File("/tmp/chronicleMap.txt" + suff));
	}

	@Override
	public Entry save(Entry newEntry) {
		checkNotNull(newEntry, "The instance cannot be a null");
		chronicleMap.putIfAbsent(newEntry.getKey(), newEntry);

		return newEntry;
	}

	@Override
	public Entry get(Long key) {
		checkArgument(key > 0,
				"Key was %s but expected greater than zero value", key);
		return chronicleMap.get(key);
	}

	@Override
	public List<Entry> getAll() {
		return new ArrayList<Entry>(chronicleMap.values());
	}

}
