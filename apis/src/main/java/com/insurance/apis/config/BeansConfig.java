package com.insurance.apis.config;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.insurance.apis.model.Insurance;
import com.insurance.apis.model.Policy;

@Configuration
public class BeansConfig {

    @Bean
    public RedisTemplate<String, Policy> policyRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Policy> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisTemplate<String, Insurance> insuranceRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Insurance> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory); // Define a conexão com o Redis

        // Serializador para as chaves do Redis (ex: "Insurance::idDoSeguro")
        template.setKeySerializer(new StringRedisSerializer());
        // Serializador para os valores do Redis (o objeto Insurance em si)
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // Serializador para as chaves internas de um Hash (se você usar HASH no Redis, ex: o ID do objeto)
        template.setHashKeySerializer(new RedisSerializer<UUID>() {
            @Override
            public byte[] serialize(UUID uuid) {
                if(uuid == null) {
                    return null;
                }
                return uuid.toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public UUID deserialize(byte[] bytes) {
                if (bytes == null) {
                    return null;
                }
                return UUID.fromString(new String(bytes, StandardCharsets.UTF_8));
            }
        });
        // Serializador para os valores internos de um Hash (o objeto Insurance dentro do Hash)
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet(); // Garante que o template seja inicializado corretamente
        return template;
    }

}
