package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    public Code getCodeAsJSON(@PathVariable String id) {
        System.out.println("Processing Get API:" +id);
        Code code = dbService.getCode(id);
        System.out.println("Code Processed via API:" +code);
        if(code !=null){
            return code;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND," Not found");
        }
    }


    /*
     * POST /api/code/new should take a JSON object with a single field code, use it as the current code
     * snippet, and return JSON with a single field id. ID is the unique number of the snippet that helps you can
     *
     * */
    @PostMapping(path = "/api/code/new")
    public String postCode(@RequestBody Code newCode) {
        if(newCode.getTime()>0 || newCode.getViews() > 0 ){
            newCode.setSecret(true);
        }
        String uid=dbService.addCode(newCode);
        System.out.println("ID: "+uid+ " Code :" +newCode);
        return "{\"id\":\""+uid +"\"}";
    }
    /*
    GET /api/code/latest should return a JSON array with 10 most recently uploaded code snippets sorted from the newest to the oldest.
     */
    @GetMapping(path = "/api/code/latest")
    public Collection<Code> latestAPI() {
        return dbService.getCodeLatest();
    }
}