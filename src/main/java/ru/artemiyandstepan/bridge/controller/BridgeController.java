package ru.artemiyandstepan.bridge.controller;

import ru.artemiyandstepan.bridge.service.SoapClientService;
import com.example.soap.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BridgeController {
    private final SoapClientService soapClientService;

    @PostMapping("/sleep/{time}")
    public String sleep(@PathVariable("time") long time) {
        SleepRequest soapRequest = new SleepRequest();
        soapRequest.setTime(time);

        SleepResponse soapResponse = (SleepResponse) soapClientService.sendSoapRequest(soapRequest);
        return soapResponse.getMessage();
    }

    @GetMapping("/loosers")
    public Object getLoosers() {
        Object soapRequest = new Object(); // SOAP-запрос для loosers
        return soapClientService.sendSoapRequest(soapRequest);
    }

    // Другие эндпоинты можно добавить аналогично
}
