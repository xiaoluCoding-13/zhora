package com.zhora.common.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author zhehen.lu
 * @date 2025/8/13 10:02
 */
public class JacksonLongSerializer extends JsonSerializer<Long> {
    public JacksonLongSerializer() {
    }

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(aLong);
    }
}
