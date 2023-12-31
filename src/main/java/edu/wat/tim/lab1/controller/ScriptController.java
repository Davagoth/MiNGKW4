package edu.wat.tim.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.wat.tim.lab1.service.ScriptService;


@RestController
@CrossOrigin
@RequestMapping("/api/script")
public class ScriptController {
    private final ScriptService scriptService;

    @Autowired
    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PutMapping()
    public ResponseEntity<String> execScript(@RequestBody String script) {
        return new ResponseEntity<>(convertSmallToCapital(script), HttpStatus.OK);
    }

    private String convertSmallToCapital(String script) {
        StringBuilder convertedScript = new StringBuilder();
        for (char c : script.toCharArray()) {
            if (Character.isLowerCase(c)) {
                convertedScript.append(Character.toUpperCase(c));
            } else {
                convertedScript.append(c);
            }
        }
        return convertedScript.toString();
    }
}