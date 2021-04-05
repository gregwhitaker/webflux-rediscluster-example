package example.config;

import example.data.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis Spring Data configuration
 */
@Configuration
public class RedisConfiguration {

  @Bean
  public ReactiveRedisOperations<String, Product> redisOperations(ReactiveRedisConnectionFactory factory) {
    final Jackson2JsonRedisSerializer<Product> serializer = new Jackson2JsonRedisSerializer<>(Product.class);
    final RedisSerializationContext.RedisSerializationContextBuilder<String, Product> builder = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
    final RedisSerializationContext<String, Product> context = builder.value(serializer).build();

    return new ReactiveRedisTemplate<>(factory, context);
  }
}
