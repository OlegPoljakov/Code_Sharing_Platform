package platform;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@RestController
public class CodeSharingAPI {
    //public static int x = 0;
    //public static List<CodeInformation> codes = new ArrayList<>();


    private CodeService service;

    public CodeSharingAPI() {
    }

    @Autowired
    public CodeSharingAPI(CodeService service) {
        this.service = service;
    }

    @PostMapping(value = "api/code/new", consumes = "application/json")
    public String greet(@RequestBody CodeInformation code) {

        CodeInformation responseCode = new CodeInformation();
        responseCode.setCode(code.getCode());
        responseCode.setTitle("Code");
        responseCode.setTime(code.getTime());
        responseCode.setStartSeconds(System.currentTimeMillis());
        responseCode.setStartTime(LocalDateTime.now());
        responseCode.setViews(code.getViews());
        responseCode.setViewLimit(code.getViews() > 0);
        responseCode.setTimeLimit(code.getTime() > 0);
        service.addCodeToStorage(responseCode);
        String response = "{ \"id\" : \"" + responseCode.getId() + "\" }";
        //System.out.println(responseCode.getCode());
        //System.out.println(responseCode.getDate());
        return response;
        /* -- for stage 3
        String codetext = code.getContent();
        CodeInformation codeinfo = new CodeInformation(codetext);
        service.addCodeToStorage(codeinfo);
        */
        /* -- for stage 3
        Repository rep = Repository.getInstance();
        rep.addCode(codeinfo);
        JsonObject value = Json.createObjectBuilder()
                .add("id", String.valueOf(codeinfo.getId()))
                .build();

        String out = value.toString();
        return out;
        */
        /* -- for stage 4
        JsonObject value = Json.createObjectBuilder()
                .add("id", String.valueOf(service.lastIdRepository()))
                .build();

        String out = value.toString();
        return out;
        */
    }


    //@GetMapping("/api/code/{id}")
    //@GetMapping(path = "/api/code/{id}", consumes = "application/json", produces = "application/json;charset=UTF-8")
    @GetMapping(path = "/api/code/{id}")
    public CodeInformation getCodeById(@PathVariable String id){
        CodeInformation respcode = service.getCodeFromStorage(id);
        if (respcode.isViewLimit()) {
            service.updateViewById(id);
        }
        if (respcode.isTimeLimit()) {
            long currentSecond = System.currentTimeMillis();
            service.updateTimeById(id);
        }
        return service.getCodeFromStorage(id);

        /* -- for stage 3
        CodeInformation retval = null;
        Repository rep = Repository.getInstance();
        //for(CodeInformation code : codes){
        for(CodeInformation code : rep.getCodes()){
            if (code.getId() == id) {
                retval = code;
                break;
            }
        }
        return retval;
        */
        /* -- for stage 4
        CodeInformation retval = null;
        retval = service.getCodeFromStorage(id);
        return retval;
        */
    }


    @GetMapping("/api/code/latest")
    public List<CodeInformation> getLatest(){
        /* -- for stage 3
        Repository rep = Repository.getInstance();
        return rep.getTenLatestReversed();
        */
        // -- for stage 4 return service.getTenLatestSnippets();
        return service.getTenLatestSnippets();
    }

}