package Resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayLoad(String name, String language, String address)
    {
        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setAddress(address);
        ap.setLanguage(language);
        ap.setPhone_number("+91234567755");
        ap.setWebsite("testing.com");
        ap.setName(name);
        List<String> typeList = new ArrayList<String>();
        typeList.add("shoe");
        typeList.add("shop");
        ap.setType(typeList);
        Location loc = new Location();
        loc.setLat(-38.3846);
        loc.setLng(-40.4536);
        ap.setLocation(loc);

        return  ap ;
    }
    public String deletePlacePayLoad(String placeId)
    {
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
    }

}
