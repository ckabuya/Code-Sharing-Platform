package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.model.Code;
import platform.service.CodeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
public class ApiCodeController {
    @Autowired
    CodeService dbService;

    public ApiCodeController() {
    }

    public ApiCodeController(CodeService dbService) {
        this.dbService = dbService;
    }


    /**
     * GET /api/code/N should return JSON with the N-th uploaded code snippet.
     */
    @GetMapping(path = "/api/code/{id}")
    public Code getCodeAsJSON(@PathVariable Integer id) {
        return  dbService.getCode(id-1);
    }


    /*
     * POST /api/code/new should take a JSON object with a single field code, use it as the current code
     * snippet, and return JSON with a single field id. ID is the unique number of the snippet that helps you can
     *
     * */
    @PostMapping(path = "/api/code/new")
    public String postCode(@RequestBody Code newCode) {
        return "{\"id\":\""+ dbService.addCode(newCode)+"\"}";
    }
    /*
    GET /api/code/latest should return a JSON array with 10 most recently uploaded code snippets sorted from the newest to the oldest.
     */
    @GetMapping(path = "/api/code/latest")
    public Collection<Code> latestAPI() {
        return dbService.getCodeLatest();
    }
}
