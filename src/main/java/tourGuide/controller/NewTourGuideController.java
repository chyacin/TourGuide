package tourGuide.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewTourGuideController {

    @Autowired
    NewTourGuideService tourGuideService;


    @GetMapping("/names")
    public List<String> names(){
        return tourGuideService.fetchNames();
    }
}
