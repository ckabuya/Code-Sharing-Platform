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
public class HtmlCodeController {
    @Autowired
    CodeService dbService;

    public HtmlCodeController() {
    }

    public HtmlCodeController(CodeService dbService) {
        this.dbService = dbService;
    }

    /**
     GET /code/N should return HTML that contains the N-th uploaded code snippet.
     */
    @GetMapping(path = "/code/{id}")
    public ModelAndView getHTMLCode(@PathVariable Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        Code code = dbService.getCode(id-1);
        params.put("date",code.getDate());
        params.put("code",code.getCode());
        return new ModelAndView("code", params);
    }

    @GetMapping(path = "/code/latest")
    public ModelAndView latestHTML() {
        // code.setCode(newCode.getCode());
        HashMap<String, Object> params = new HashMap<>();
        params.put("snippets",dbService.getCodeLatest());
        return new ModelAndView("snippets", params);
    }

    /**
     * GET /code/new should return HTML that contains:
     * Tags <textarea id="code_snippet"> ... </textarea> where you can paste a code snippet;
     * Title Create;
     * Button <button id="send_snippet" type="submit" onclick="send()">Submit</button>.
     * @return ModelAndView
     */
    @GetMapping(path = "/code/new")
    public ModelAndView index(Model model) {
        HashMap<String, Object> params = new HashMap<>();
        return new ModelAndView("index",params);
    }
}
