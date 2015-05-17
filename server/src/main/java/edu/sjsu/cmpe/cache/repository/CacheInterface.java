package edu.sjsu.cmpe.cache.repository;

import edu.sjsu.cmpe.cache.domain.Entry;
import java.util.List;



public interface CacheInterface {
    Entry save(Entry newEntry);
    Entry get(Long key);
    List<Entry> getAll();

}
