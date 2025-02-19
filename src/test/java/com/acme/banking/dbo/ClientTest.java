package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {
    @Test @Disabled("temporary disabled")
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(clientId)),
                hasProperty("name", is(clientName))
        ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }
    @Test
    public void shouldReturnErrorWhenIdNegative() {
        //region given
        final int clientId = -1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        try {
            Client sut = new Client(clientId, clientName);
        }
        //endregion

        //region then
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Id is negtive");
        }
    }
    @Test
    public void shouldReturnErrorWhenNameEmpty() {
        //region given
        final int clientId = 1;
        final String clientName = "";
        //endregion

        //region when
        try {
            Client sut = new Client(clientId, clientName);
        }
        //endregion

        //region then
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Name is empty");
        }
    }

}
