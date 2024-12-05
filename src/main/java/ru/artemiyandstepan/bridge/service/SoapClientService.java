package ru.artemiyandstepan.bridge.service;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SoapClientService extends WebServiceGatewaySupport {

    public Object sendSoapRequest(Object request) {
        return getWebServiceTemplate().marshalSendAndReceive(request);
    }
}

