package ru.artemiyandstepan.bridge.controller;

import com.example.soap.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.artemiyandstepan.bridge.service.SoapClientService;

import java.util.List;

@RestController
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

    @PostMapping("/v1/oscar/screenwriters/get-loosers")
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
