package Resources;

public enum ApiEndPoints {

    AddPlaceApi("/maps/api/place/add/json"),
    GetPlaceApi("/maps/api/place/get/json"),
    DeletePlaceApi("/maps/api/place/delete/json");
    private final String resource;

    ApiEndPoints(String resource)
    {
        this.resource = resource;

    }

    public String getResource()
    {
        return resource;
    }


}
