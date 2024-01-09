package StepDefinitions;


import io.cucumber.java.Before;

import java.io.IOException;

public class cucmberHooks {
    @Before("@DeletePlace")
    public void beforeDelete() throws IOException {

        PlaceAPiStepDefinition api = new PlaceAPiStepDefinition();
        if(PlaceAPiStepDefinition.placeId==null) {
            api.add_place_payload_with("vissu", "Sansrikt", "India");
            api.user_calls_with_http_request("AddPlaceApi", "GET");
            api.the_api_call_is_success_with_status_code();
            api.verify_place_id_created_maps_to_using("vissu", "GetPlaceApi");
        }
    }
}
