package ru.r5am.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import static freemarker.template.Configuration.VERSION_2_3_26;

public class PageGenerator {
    private static final Configuration CFG = new Configuration(VERSION_2_3_26);
    private static final String HTML_DIR = "src" + File.separator +
                                           "main" + File.separator +
                                           "resources" + File.separator +
                                           "templates";

    public static String getPage(String filename, Map<String, Object> data) {

        // Посмотрим путь, откуда мы стартуем
        String currentDir = new File("").getAbsolutePath();
//        System.out.println(currentDir);



        Writer stream = new StringWriter();
        String templateFullPath = currentDir + File.separator + HTML_DIR;
        try {
            CFG.setDirectoryForTemplateLoading(new File(templateFullPath));
            Template template = CFG.getTemplate(filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            System.err.println("Couldn't find template file: " + filename);
            e.printStackTrace();
        }
        return stream.toString();
    }
}
