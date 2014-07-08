import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class Response {
    public void send(ScriptObjectMirror data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter buffer = new StringWriter();
        objectMapper.writeValue(buffer, data);
        System.out.println("Sending: "+ buffer.toString());
    }
}
