package io.toolisticon.jigsawbyap.processor;

import com.sun.source.util.Trees;
import io.toolisticon.annotationprocessortoolkit.AbstractAnnotationProcessor;
import io.toolisticon.jigsawbyap.api.JigsawModule;
import io.toolisticon.spiap.api.Service;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * Annotation Processor for {@link JigsawModule}.
 */
@Service(Processor.class)
public class JigsawByApProcessor extends AbstractAnnotationProcessor {

    private final static Set<String> SUPPORTED_ANNOTATIONS = createSupportedAnnotationSet(JigsawModule.class);

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return SUPPORTED_ANNOTATIONS;
    }

    @Override
    public boolean processAnnotations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        // process Services annotation
        for (Element element : roundEnv.getElementsAnnotatedWith(JigsawModule.class)) {

            PackageElement packageElement = (PackageElement) element;

            // get annotation
            JigsawModule jigsawModuleAnnotation = element.getAnnotation(JigsawModule.class);



            // ---------------------------------------------------------------
            // -- get module  (use APTK tools => first parent of kind MODULE)
            // ---------------------------------------------------------------

            // ---------------------------------------------------------------
            // -- check if module is unnamed (default) module => abort
            // ---------------------------------------------------------------

            // ---------------------------------------------------------------
            // -- get all existing packages in module (enclosing elements of kind PACKAGE)
            // ---------------------------------------------------------------

            // ---------------------------------------------------------------
            // -- get package-info.java files and all classes and store it
            // -- Source code can be grabbed via Trees class
            // -- it might be that package-info source files have to be created via template mechanism (APTK)
            // ---------------------------------------------------------------


            // ---------------------------------------------------------------
            // -- get all existing packages in module (enclosing elements of kind PACKAGE)
            // ---------------------------------------------------------------


            // ---------------------------------------------------------------
            // -- Prepare the module-info.java source file unfortunately this cannot be generated in a regular way.
            // -- It won't be picked up and compiled correctly after source file is created
            // -- so we have to compile it on our own
            // -- need a forwarding file manager to be able to access the compiled class from memory
            // -- module-info.java files aren't not just compiled, there are some checks if exported packages exist and at least contain one class
            // -- need to add all source codes of all classes and package-info files for compilation
            // -- if we compile everything we will get feedback of module violations in all classes too ==> forward compile errors
            // ---------------------------------------------------------------

            // ---------------------------------------------------------------
            // -- funny thing : the compiled class then can copied to SOURCE_OUTPUT
            // ---------------------------------------------------------------


            // Processors can be loaded at ap execution time
            ServiceLoader<Processor> processors = ServiceLoader.load(Processor.class, JigsawByApProcessor.class.getClassLoader());
            System.out.println("READ PROCESSORS");
            int i = 0;
            for (Processor processor : processors) {
                System.out.println("[" + (i++) + "] := " + processor.getClass().getCanonicalName());
            }

            // check if all java files that can be processed by an annotation processor can be loaded. This includes all Classes and package-info files


            // get all packages

            // get root package
            System.out.println("FILE:\n" + usingBufferedReader(Trees.instance(processingEnv).getPath(packageElement).getCompilationUnit().getSourceFile()));

            /*-

            PackageElement root = packageElement;

            String moduleName = packageElement.getEnclosingElement() != null && !packageElement.getEnclosingElement().getSimpleName().toString().isEmpty() ? packageElement.getEnclosingElement().getSimpleName() + "/" : "";
            try {
                String moduleAndPkg = moduleName + root.getQualifiedName().toString();
                FileObject fileObject = getFiler().getResource(StandardLocation.SOURCE_PATH, moduleAndPkg, "package-info.java");
                System.out.println("SOURCE_PATH (" + moduleAndPkg + ".package-info.java) := " + fileObject != null ? fileObject.toUri().toString() : "NULL");

                File file = new File(fileObject.toUri());
                System.out.println("FILE:\n" + usingBufferedReader(file));


            } catch (IOException e) {
                e.printStackTrace();
            }
*/


/*-
            System.out.println("WRITE MODULE-INFO.CLASS !!!!!!!!!!!!!");
            // Now copy file
            String filePath = "module-info.class";
            try {
                InputStream source = new FileInputStream(
                        "/Users/tobiasstamann/Projects/Opensource/compile-testing/compile-testing/target/compileTesting_failingUnitTests/asmtotnjft/CLASS_OUTPUT/module-info.class");
                FileObject target = ProcessingEnvironmentUtils.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", filePath);

                copyClassFile(source, target);


            } catch (IOException e) {
                System.out.println("!!!! ERROR !!!!");
                e.printStackTrace();
                MessagerUtils.error(packageElement, JigsawByApProcessorMessages.ERROR_COULD_NOT_FIND_MODULE_FILE.getMessage(), filePath);
            }

            System.out.println("DONE : WRITE MODULE-INFO.CLASS !!!!!!!!!!!!!");

*/
            /*-

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();


            try {
                JavaCompiler.CompilationTask compilationTask = compiler.getTask(null, compiler.getStandardFileManager(diagnostics, null, null), diagnostics, null, null, new SimpleJavaFileObject(new URI("str://module-info.java"), JavaFileObject.Kind.SOURCE) {

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            */

/*-
            // Now copy file
            String filePath = "module-info.java";
            try {
                FileObject target = ProcessingEnvironmentUtils.getFiler().createSourceFile(filePath);
//SimpleResourceWriter javaWriter = FilerUtils.createResource(filePath);

                InputStream source = this.getClass().getResourceAsStream(jigsawModuleAnnotation.value());


                copyClassFile(source, target);

            } catch (IOException e) {
                e.printStackTrace();
                MessagerUtils.error(packageElement, JigsawByApProcessorMessages.ERROR_COULD_NOT_FIND_MODULE_FILE.getMessage(), filePath);
            }
            */

        }

        return false;

    }

    private void copyClassFile(InputStream source, FileObject target1) {

        OutputStream fos = null;
        InputStream is = source;

        try {


            // open input and output stream for file copy
            fos = target1.openOutputStream();

            byte[] buffer = new byte[20000];

            int ch = is.read(buffer);
            while (-1 != ch) {

                fos.write(buffer, 0, ch);
                ch = is.read(buffer);

            }

            fos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {

                }
            }

            if (fos != null) {
                try {
                    is.close();
                } catch (IOException e) {

                }
            }
        }


    }


    private static String usingBufferedReader(File file) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    private static String usingBufferedReader(JavaFileObject file) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(file.openReader(true))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_9;
    }
}
