package platform;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;




@Controller
public class ReactionController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    //private static String codeoutput = "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}";

    private CodeService service;

    @Autowired
    public ReactionController(CodeService service) {
        this.service = service;
    }

    /*
    @GetMapping(value = "code/new", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String codeform() {
        //return "<html>\n" + "<header><title>Welcome</title></header>\n" +
        //        "<body>\n" + "Hello world\n" + "</body>\n" + "</html>";
        return "<html>\n" +
                "<head><title>Create</title>" +
                "<script type=\"text/javascript\">function send() {\n" +
                "    let object = {\n" +
                "        \"code\": document.getElementById(\"code_snippet\").value\n" +
                "        \"time\": document.getElementById(\"time_restriction\").value\n" +
                "        \"views\": document.getElementById(\"views_restriction\").value\n" +
                "    };\n" +
                "    \n" +
                "    let json = JSON.stringify(object);\n" +
                "    \n" +
                "    let xhr = new XMLHttpRequest();\n" +
                "    xhr.open(\"POST\", '/api/code/new', false)\n" +
                "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "    xhr.send(json);\n" +
                "    \n" +
                "    if (xhr.status == 200) {\n" +
                "      alert(\"Success!\");\n" +
                "    }\n" +
                //"    else {\n" +
                //"      alert(\"Error!\");\n" +
                //"    }\n" +
                "}</script>\n" +
                "</head>\n" +
                "<body>\n"+ "<textarea id=\"code_snippet\">//Write your code here</textarea>\n" +
                "<br>\n" + "Time restriction:" + "<textarea id=\"time_restriction\" type=\"text\"/>" + "</br>\n" +
                "<br>\n" + "Views restriction:" + "<textarea id=\"view_restriction\" type=\"text\"/>" + "</br>\n" +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>\n" +
                "</body>\n" + "</html>";
    }
    */

    @GetMapping(value = "code/new", produces = MediaType.TEXT_HTML_VALUE)
    //@ResponseBody
    public String codeform() {
        return "newCode";
    }


    @GetMapping(value = "/code/latest")
    public String getLatest(Model model) {
        /*
        List<CodeInformation> temp = null;
        Repository rep = Repository.getInstance();
        temp.addAll(rep.getCodes());
        Collections.reverse(temp);
        List<CodeInformation> tail = temp.subList(Math.max(temp.size() - 3, 0), temp.size());
        */

        //Repository rep = Repository.getInstance();
        model.addAttribute("snippets", service.getTenLatestSnippets());
        return "latest";
    }

    @RequestMapping("/welcome")
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        System.out.println();

        return "welcome";
    }

    @GetMapping(path = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getHtmlCode(Model model, @PathVariable("id") String id) {
        CodeInformation code = service.getCodeFromStorage(id);
        if (code.isViewLimit()) {
            service.updateViewById(id);
            code = service.getCodeFromStorage(id);
        }
        if (code.isTimeLimit()) {
            //long currentSecond = System.currentTimeMillis();
            service.updateTimeById(id);
            code = service.getCodeFromStorage(id);
        }
        model.addAttribute("responseCode",code);
        return "codeTemplate2";
    }

}
