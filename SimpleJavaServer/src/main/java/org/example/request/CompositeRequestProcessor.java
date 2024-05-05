package org.example.request;

import java.io.IOException;
import java.util.Map;

import org.example.HttpRequest;

public class CompositeRequestProcessor implements ReqProcessor {
    Map<RequestRule, ReqProcessor> processingRules;

    public CompositeRequestProcessor(Map<RequestRule, ReqProcessor> processingRules) {
        this.processingRules = processingRules;
    }

    @Override
    public void process(HttpRequest httpRequest) throws IOException {
        ReqProcessor processor = processingRules.entrySet().stream()
                .filter(r -> r.getKey().matches(httpRequest))
                .findFirst()
                .orElseThrow()
                .getValue();

        processor.process(httpRequest);
    }

}
