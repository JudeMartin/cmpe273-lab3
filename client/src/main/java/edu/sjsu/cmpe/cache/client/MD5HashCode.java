package edu.sjsu.cmpe.cache.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

public class MD5HashCode implements HashFunction {

	@Override
	public Integer hash(String string) {
		
		
		com.google.common.hash.HashFunction hashingFucn = Hashing.md5();
		HashCode hashCode = hashingFucn.newHasher()
		       .putString(string, Charsets.UTF_8)
		       .hash();
		return hashCode.asInt();
    }
}
