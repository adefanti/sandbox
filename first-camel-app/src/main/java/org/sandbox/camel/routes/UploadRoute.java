package org.sandbox.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.sandbox.upload.bean.FileContainer;
import org.sandbox.upload.service.UploadWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

public class UploadRoute extends RouteBuilder {

    private final static Logger LOG = LoggerFactory
            .getLogger(UploadRoute.class);

    @Override
    public void configure() throws Exception {
        String cxfEndpoint = "cxf:/uploadService?serviceClass="
                + UploadWebService.class.getName();

        from(cxfEndpoint)
                .to("log:input")
                .process(new UploadProcessor())
                .log("Creating ${file:name} to disk")
                .to("file:target/messages/inputdir");
    }

    private class UploadProcessor implements Processor {

        @Override
        public void process(Exchange exchange) throws Exception {
            FileContainer fileContainer = exchange.getIn().getBody(
                    FileContainer.class);
            if (fileContainer.getData() != null) {
                // convert DataHandler to String
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                fileContainer.getData().writeTo(output);
                String body = new String(output.toByteArray());

                // set data (as String) to the file
                exchange.getOut().setBody(body);
                // set output filename
                exchange.getOut().setHeader(
                        Exchange.FILE_NAME,
                        fileContainer.getFileName() + "."
                        + fileContainer.getFileExtension());
            } else {
                LOG.warn("No data found");
            }
        }
    }
}
