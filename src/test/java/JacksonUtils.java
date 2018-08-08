import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

class JacksonUtils {

    private static ObjectMapper objectMapper;

    static Object read(String source, Class<?> clazz) throws IOException {
        final InputStream in = JacksonUtils.class.getResourceAsStream(source);
        final ObjectMapper objectMapper = getObjectMapper();
        return objectMapper.readValue(in, clazz);
    }

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}
