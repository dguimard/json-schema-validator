package com.networknt.schema.spi.providers.draftv4;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.ValidatorTypeCode;
import com.networknt.schema.spi.BaseJsonValidatorNode;
import com.networknt.schema.spi.JsonSchemaValidatorNode;
import com.networknt.schema.spi.ValidatorNode;
import com.networknt.schema.spi.ValidatorNodeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class MinPropertiesValidatorNode extends BaseJsonValidatorNode {

    public static final String PROPERTY_NAME_MINPROPERTIES = "minProperties";

    private static final Logger logger = LoggerFactory.getLogger(MinPropertiesValidatorNode.class);

    private final int min;

    private MinPropertiesValidatorNode(String schemaPath, JsonNode jsonNode, ValidatorNode parent, ValidatorNode root) {
        super(ValidatorTypeCode.MIN_PROPERTIES, schemaPath, jsonNode, parent, root);
        min = jsonNode.isIntegralNumber() ? jsonNode.intValue() : 0;
    }

    @Override
    public List<ValidationMessage> validate(JsonNode node, JsonNode rootNode, String at) {
        debug(logger, node, rootNode, at);

        List<ValidationMessage> errors = new LinkedList<>();
        if (node.isObject()) {
            if (node.size() < min) {
                errors.add(buildValidationMessage(at, "" + min));
            }
        }

        return errors;
    }

    public static final class Factory implements ValidatorNodeFactory<MinPropertiesValidatorNode> {
        @Override
        public MinPropertiesValidatorNode newInstance(String schemaPath, JsonNode jsonNode, ValidatorNode parent, ValidatorNode root) {
            return null;
        }
    }
}
