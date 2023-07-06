package br.ufrn.imd.reservagomvc.checkout.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {
//	@Bean
//	public CacheManager cacheManager(RedissonClient redissonClient) {
//		return new RedissonSpringCacheManager(redissonClient);
//	}

	@Bean
	public RedissonClient getClient() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		return Redisson.create(config);
	}
}
