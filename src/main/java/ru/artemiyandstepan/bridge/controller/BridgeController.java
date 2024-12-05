package ru.artemiyandstepan.bridge.controller;

import com.example.soap.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.artemiyandstepan.bridge.service.SoapClientService;

import java.util.List;

@RestController
public class BridgeController {
    private final SoapClientService soapClientService;

    public BridgeController(SoapClientService soapClientService) {
        this.soapClientService = soapClientService;
    }

    @GetMapping("/sleep/{time}")
    public String sleep(@PathVariable("time") long time) {
        SleepRequest soapRequest = new SleepRequest();
        soapRequest.setTime(time);

        SleepResponse soapResponse = (SleepResponse) soapClientService.sendSoapRequest(soapRequest);
        return soapResponse.getMessage();
    }

    @GetMapping("/v1/oscar/screenwriters/get-loosers")
    public ResponseEntity<List<Movie>> getLoosers() {
        GetLoosersRequest soapRequest = new GetLoosersRequest();

        GetLoosersResponse soapResponse = (GetLoosersResponse) soapClientService.sendSoapRequest(soapRequest);

        List<Movie> responseDto = soapResponse.getMovies().getMovie();

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/v1/oscar/directors/humiliate-by-genre/{genre}")
    public ResponseEntity<String> humiliateByGenre(
            @PathVariable String genre
    ) {
        HumiliateByGenreRequest soapRequest = new HumiliateByGenreRequest();
        MovieGenre movieGenre = MovieGenre.valueOf(genre);
        soapRequest.setGenre(movieGenre);

        HumiliateByGenreResponse soapResponse = (HumiliateByGenreResponse) soapClientService.sendSoapRequest(soapRequest);

        return ResponseEntity.ok(soapResponse.getResult());
    }
}
