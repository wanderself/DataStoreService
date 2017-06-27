package com.gree.grih.datstore.jsonsBean;

import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * JsonUtils
 * Created by wander on 19th.Jun.2017
 */
public class JsonUtils {

    private final static JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static boolean isValidJson(String jsonStr) {
        try {
            JsonSchema jsonSchema = jsonSchemaFactory.getJsonSchema(JsonLoader.fromResource("/schema.json"));
            ProcessingReport report = jsonSchema.validate(JsonLoader.fromString(jsonStr));
            if (report.isSuccess()) {
                return true;
            }
        } catch (ProcessingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
        return false;
    }
}
