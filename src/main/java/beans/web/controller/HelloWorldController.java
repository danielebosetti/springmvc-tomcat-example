package beans.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import beans.domain.User;

@RestController
public class HelloWorldController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello world";
    }

    @RequestMapping("/go1")
    public void go1() {
        System.out.println("go1:called");
    }

    @RequestMapping("/go2")
    public void go2(InputStream is) {
        System.out.println("go1:called");
        try {
            System.out.println("is=" + IOUtils.toString(is, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    @GetMapping(path = "/go3", produces = MediaType.TEXT_HTML_VALUE)
    public void go3(InputStream is, Writer out, HttpMethod method) {
        System.out.println("go3: method=" + method);
        PrintWriter pw = new PrintWriter(out);
        pw.printf("<b>value=%s</b>", 123L * 123);
        pw.println("hellooo");
        pw.close();
    }

    @GetMapping(path = "/go4", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User go3put(HttpMethod method) {
        System.out.println("go3put: method=" + method);
        return new User();
    }

    @GetMapping(path = "/go4/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User go4param(HttpMethod method, @PathVariable int userId) {
        if (userId > 10000)
            throw new NotFoundException();
        User res = new User();
        res.setV(userId);
        return res;
    }

    @GetMapping(path = "/go5", produces = MediaType.APPLICATION_XML_VALUE)
    public User go5(HttpMethod method) {
        System.out.println("go5: method=" + method);
        return new User();
    }

    @RequestMapping(value = "/myfiles/{file_name}", method = RequestMethod.GET)
    // http://localhost:8080/app/files/c%3A%2Fbin%2Fkey%2Etxt
    public void getFile(
            @PathVariable("file_name") String fileName,
            HttpServletResponse response) {
        try {
            response.setContentType("application/force-download");
            fileName = "c:\\bin\\key1.txt";
            response.setHeader("Content-Disposition", "attachment; filename=\"key1.txt\"");
            
            // get your file as InputStream
            InputStream is = new FileInputStream(fileName);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace(System.out);
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    static class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
    
}
