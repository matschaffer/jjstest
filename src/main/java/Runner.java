import jdk.nashorn.internal.runtime.ScriptFunction;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.script.*;
import javax.swing.text.View;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.function.Function;

public class Runner {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager(Runner.class.getClassLoader());
        ScriptEngine routerEngine = scriptEngineManager.getEngineByName("nashorn");
        ScriptContext routerContext = routerEngine.getContext()                          ;

        Router router = new Router();
        routerContext.setAttribute("router", router, ScriptContext.ENGINE_SCOPE);

        routerEngine.eval("load('src/main/javascript/routes.js')");

        ScriptEngine controllerEngine = scriptEngineManager.getEngineByName("nashorn");
        ScriptContext controllerContext = controllerEngine.getContext();


        Function<String, String> render = (String view) -> { return "render"; };

        controllerContext.setAttribute("render", render, ScriptContext.ENGINE_SCOPE);

        controllerEngine.eval("load('src/main/javascript/charts.js')");

        Invocable invocable = (Invocable) controllerEngine;
        HashMap<String, String> params = new HashMap<>();
        params.put("foo", "bar");

        HashMap<String, HashMap<String, String>> req = new HashMap<>();
        req.put("params", params);

        Object result = invocable.invokeFunction("index", req, new Response());

        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter buffer = new StringWriter();

        objectMapper.writeValue(buffer, result);
    }
}
