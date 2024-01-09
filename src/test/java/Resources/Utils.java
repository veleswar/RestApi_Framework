package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.*;
import java.util.Properties;

public class Utils {

     public static RequestSpecification req;
    public static String getGlobalValue(String Key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/Resources/global.properties") ;
        prop.load(fis);
        return prop.getProperty(Key);

    }
    public RequestSpecification requestSpecification() throws IOException {

       if (req==null) {
             PrintStream log = new PrintStream(new FileOutputStream("logging.txt")) ;
               req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123").
                       addFilter(RequestLoggingFilter.logRequestTo(log)).
                       addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();


           return req;
       }
       return req;
    }

    public static String getJsonPath(Response apiResponse,String Key)
    {
        String resp = apiResponse.asString();
        JsonPath apResponse = new JsonPath(resp);
        return apResponse.get(Key);

    }
}
