package com.igt.technical.task.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.igt.technical.task.model.CustomerInfoDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.request;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.igt.technical.task.controller.TestUtils.getContentFromFile;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CustomerInfoResourceIT extends AbstractIntegrationTest {

    private static final String URI = "/customers";
    private static WireMockServer wireMockServer = new WireMockServer(options().port(8120));

    @Test
    void createNewCustomerInformation() throws IOException {
        String request = getContentFromFile("/input/newcustomerdata.json");

        setUpPostResponseWireMock();

        CustomerInfoDTO response = makePostRequest(URI, request)
                .expectStatus().isCreated()
                .expectBody(CustomerInfoDTO.class)
                .returnResult()
                .getResponseBody();

        assertThat(response).isNotNull();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getId()).isEqualTo(1L);
            softly.assertThat(response.getSurname()).isEqualTo("Json");
            softly.assertThat(response.getSurname()).isEqualTo("Beetle");
            softly.assertThat(response.getPassword()).isEqualTo("!2Qwerty20");
            softly.assertThat(response.getEmail()).isEqualTo("email@somefortest.com");
            softly.assertThat(response.getCountry()).isEqualTo("Canada");
        });
    }

    @Test
    void createNewCustomerInformationWithException() {
        makePostRequest(URI, "null")
                .expectStatus().isBadRequest();
    }

    @Test
    void getCustomerInformationById() throws IOException {
        String expectedResponse = getContentFromFile("/output/customerdataResponse.json");

        String actualResponse = makeGetRequest(URI + "?id=" + 1)
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertThat(actualResponse).isNotNull().isEqualTo(expectedResponse);
    }

    @Test
    void getCustomerInformationWithException() {
        makeGetRequest(URI + "?id=" + 10)
                .expectStatus()
                .isNotFound()
                .expectBody(CustomerInfoDTO.class);
    }

    @Test
    void updateCustomerInformation() throws IOException {
        String request = getContentFromFile("/input/updatedcustomerdata.json");

        setUpPutResponseWireMock();

        CustomerInfoDTO response = makePutRequest(URI + "?id=" + 1, request)
                .expectStatus().isOk()
                .expectBody(CustomerInfoDTO.class)
                .returnResult()
                .getResponseBody();

        assertThat(response).isNotNull();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getId()).isEqualTo(1L);
            softly.assertThat(response.getSurname()).isEqualTo("Mason");
            softly.assertThat(response.getSurname()).isEqualTo("Geele");
            softly.assertThat(response.getPassword()).isEqualTo("!2Qwerty20");
            softly.assertThat(response.getEmail()).isEqualTo("email@somefortest.com");
            softly.assertThat(response.getCountry()).isEqualTo("Nepal");
        });
    }

    @Test
    void updateCustomerInformationWithException() {
        makePutRequest(URI + "?id=" + 10, null)
                .expectStatus().isBadRequest();
    }

    @Test
    void deleteCustomerInformationById() {
        setUpDeleteResponseWireMock();

        makeDeleteRequest(URI + "?id=" + 1)
                .expectStatus().isNoContent()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }

    @Test
    void deleteCustomerInformationWithException() {
        makeDeleteRequest(URI + "?id=" + 10)
                .expectStatus()
                .isNotFound()
                .expectBody(CustomerInfoDTO.class);
    }

    private static void setUpPostResponseWireMock() {
        wireMockServer.stubFor(
                request("POST", urlEqualTo(URI))
                        .willReturn(
                                aResponse()
                                        .withHeader("Content-Type", "text/json")
                                        .withStatus(201)
                                        .withBodyFile("newcustomerResponse.json"))
        );
    }

    private static void setUpPutResponseWireMock() {
        wireMockServer.stubFor(
                request("PUT", urlEqualTo(URI))
                        .willReturn(
                                aResponse()
                                        .withHeader("Content-Type", "text/json")
                                        .withStatus(200)
                                        .withBodyFile("updatedcustomerResponse.json"))
        );
    }

    private static void setUpDeleteResponseWireMock() {
        wireMockServer.stubFor(
                request("DELETE", urlEqualTo(URI))
                        .willReturn(
                                aResponse()
                                        .withHeader("Content-Type", "text/json")
                                        .withStatus(204)
                                        .withBody(String.valueOf(HttpStatus.NO_CONTENT)))
        );
    }
}
