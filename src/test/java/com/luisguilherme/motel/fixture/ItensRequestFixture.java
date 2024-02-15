package com.luisguilherme.motel.fixture;

import com.luisguilherme.motel.request.ItensRequest;

public class ItensRequestFixture {

    public static ItensRequest itensRequest() {
        return new ItensRequest(
                "Água",
                2F
        );
    }
}
