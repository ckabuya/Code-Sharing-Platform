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
public class HtmlCodeController {
    @Autowired
    CodeService dbService;

    public HtmlCodeController() {
    }

    public HtmlCodeController(CodeService dbService) {
        this.dbService = dbService;
    }

    /**
     * GET /code/N should return HTML that contains the N-th uploaded code snippet.
     */
    @GetMapping(path = "/code/{id}")
    public ModelAndView getHTMLCode(@PathVariable String id) {
        HashMap<String, Object> params = new HashMap<>();
        System.out.println("Processing Get Web:" + id);
        Code code = dbService.getCode(id);
        System.out.println("Code Processed via Web:" + code);
        if (code != null) {
            params.put("date", code.getDate());
            params.put("code", code.getCode());
            params.put("time", code.getTime());
            params.put("views", code.getViews());

            if(code.isSecret() && code.getViews()==0 && code.getTime()==0) //this is a Not ideal
               params.put("isSecret", true);
            else
            {
                params.put("isSecret", false);
            }
            //check restrictions
           // if (code.getViews() <= 0 && code.getTime() <= 0) {
               // System.out.println("Code Processed Not Secret:" + code);
              //  return new ModelAndView("code", params);//normal code
           // } else {
             //   System.out.println("Code Processed is Secret:" + code);
                return new ModelAndView("secret", params); //secret code.
           // }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Not found");
        }
    }

    @GetMapping(path = "/code/latest")
    public ModelAndView latestHTML() {
        // code.setCode(newCode.getCode());
        HashMap<String, Object> params = new HashMap<>();
        params.put("snippets", dbService.getCodeLatest());
        return new ModelAndView("snippets", params);
    }

    /**
     * GET /code/new should return HTML that contains:
     * Tags <textarea id="code_snippet"> ... </textarea> where you can paste a code snippet;
     * Title Create;
     * Button <button id="send_snippet" type="submit" onclick="send()">Submit</button>.
     *
     * @return ModelAndView
     */
    @GetMapping(path = "/code/new")
    public ModelAndView index(Model model) {
        HashMap<String, Object> params = new HashMap<>();
        return new ModelAndView("index", params);
    }
}
