package icu.yunke.framework.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig  {

    /**
     * 定义Jackson2ObjectMapperBuilder的Bean，用于定制JSON序列化和反序列化行为。
     * @return 配置好的Jackson2ObjectMapperBuilder实例。
     */
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder()
                // 通过服务加载器发现所有的Jackson模块，包括第三方提供的模块。
                .findModulesViaServiceLoader(true)
                // 禁用将日期序列化为时间戳的形式，确保日期被序列化为ISO格式的字符串。
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                // 注册JavaTimeModule模块，提供对Java 8日期时间类型的序列化和反序列化支持。
                .modules(new JavaTimeModule())
                // 启用默认类型，注意这可能会带来安全风险
                .defaultTyping(StdTypeResolverBuilder.noTypeInfoBuilder());
    }

    @Primary
    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.build();
    }

}