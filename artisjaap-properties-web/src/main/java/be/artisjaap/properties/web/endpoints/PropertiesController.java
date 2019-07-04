package be.artisjaap.properties.web.endpoints;

import be.artisjaap.properties.action.GetProperty;
import be.artisjaap.properties.web.endpoints.response.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/properties")
public class PropertiesController {

    @Autowired
    private GetProperty getProperty;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<PropertyResponse>> listAllProperties() {
        return ResponseEntity.ok(PropertyResponse.from(getProperty.allProperties()));
    }
}
