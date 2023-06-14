package edu.wat.tim.lab1.service;

import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.wat.tim.lab1.repository.ArticleRepository;
import edu.wat.tim.lab1.repository.AuthorRepository;

@Service
@Slf4j
public class ScriptService {
    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ScriptService(ArticleRepository articleRepository, AuthorRepository authorRepository) {
        this.articleRepository = articleRepository;
        this.authorRepository = authorRepository;
    }

    public String exec(String script) {
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            var bindings = context.getBindings("js");
            bindings.putMember("articleRepository", articleRepository);
            bindings.putMember("authorRepository", authorRepository);
            return convertSmallToCapital(context.eval("js", script).toString());
        } catch (PolyglotException e) {
            log.error("Error executing", e);
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }
    }

    private String convertSmallToCapital(String text) {
        StringBuilder convertedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLowerCase(c)) {
                convertedText.append(Character.toUpperCase(c));
            } else {
                convertedText.append(c);
            }
        }
        return convertedText.toString();
    }
}