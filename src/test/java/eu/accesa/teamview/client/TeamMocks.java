package eu.accesa.teamview.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.util.StreamUtils.copyToString;

public class TeamMocks {

    public static void setupMockTeamResponse(WireMockServer mockServer) throws IOException {
        mockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/teams"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                TeamMocks.class.getClassLoader().getResourceAsStream("payload/get-books-response.json")
                                                , Charset.defaultCharset()
                                        )
                                )
                ));
    }
}
