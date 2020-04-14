package unity_aanp;

import freemarker.template.Configuration;
import freemarker.template.Template;
import javassist.ClassPool;
import javassist.CtClass;
import unity_aanp.datamodel.ClassInfo;

import java.io.BufferedWriter;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class App {
    public static void main(String[] args) throws Exception {
        String classpath = args[0];
        Path outFile = Path.of(args[1]);

        // list class names from jar
        String[] classNames;
        try (ZipFile zip = new ZipFile(classpath)) {
            classNames = zip.stream().map(ZipEntry::getName).filter(e -> e.endsWith(".class"))
                    .map(e -> e.substring(0, e.length() - 6).replace('/', '.')).toArray(String[]::new);
        }

        // load classes using javassist
        ClassPool cp = new ClassPool();
        cp.appendClassPath(classpath);
        List<CtClass> classes = new ArrayList<>(classNames.length);
        for (String className : classNames) {
            CtClass clazz = cp.get(className);

            // skip local or anonymous classes
            if (clazz.getEnclosingBehavior() != null)
                continue;

            // skip non-visible classes
            if (!Modifier.isPublic(clazz.getModifiers()))
                continue;

            classes.add(clazz);
        }

        // C# code generation using FreeMaker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "");
        Template template = cfg.getTemplate("cs-class.ftl");
        Files.createDirectories(outFile.getParent());
        try (BufferedWriter fw = Files.newBufferedWriter(outFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (CtClass ctClass : classes) {
                ClassInfo dataModel = ClassInfo.fromCtClass(ctClass);
                template.process(dataModel, fw);
            }
        }
    }
}

