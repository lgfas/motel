package com.luisguilherme.motel.fixture;

import com.luisguilherme.motel.request.QuartosRequest;

public class QuartosRequestFixture {

    public static QuartosRequest quartosRequest() {
        return new QuartosRequest(
                "Quarto com 1 cama de casal e frigobar",
                2L
        );
    }
}
