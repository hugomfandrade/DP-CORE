package patterns;

import gui.MainWindow;
import org.junit.Before;
import org.junit.Test;
import parser.ProjectASTParser;

import java.io.File;
import java.util.stream.Stream;

import static patterns.PatternDetectionAlgorithmTestUtils.*;

@SuppressWarnings({"NewClassNamingConvention", "FieldCanBeLocal"})
public class PatternDetectionAlgorithmTest_Repos {

    // Patterns
    //
    private Pattern observerPattern;
    private Pattern visitorPattern;
    private Pattern commandPattern;
    private Pattern builderPattern;
    private Pattern bridgePattern;
    private Pattern bridgeV2Pattern;
    private Pattern abstractFactoryPattern;
    private Pattern abstractFactoryV2Pattern;
    private Pattern[] allPatterns;

    // Examples
    //
    private final File antProject = new File("examples/ant-rel-1.6.2/src/main");
    private final File awtProject = new File("examples/jdk6-master/jdk/src/share/classes/java/awt");
    private final File jHotDrawProject = new File("examples/jhotdraw60b1/src");

    private final File storeProject = new File("examples/store/store-java/src/main/java");
    private final File libPhoneNumberProject = new File("examples/libphonenumber-master/java/libphonenumber/src");

    private final File _01_quickUMLProject = new File("examples/1 - QuickUML 2001/src");
    private final File _02_lexiProject = new File("examples/2 - Lexi v0.1.1 alpha/src");
    private final File _03_jRefactoryProject = new File("examples/3 - jRefactory v2.6.24/src");
    private final File _04_netBeansProject = new File("examples/4 - Netbeans v1.0.x/src");
    private final File _05_jUnit_Project = new File("examples/5 - JUnit v3.7/src");
    private final File _06_jHotDrawProject = new File("examples/6 - JHotDraw v5.1/src");
    private final File _08_mapperXMLProject = new File("examples/8 - MapperXML v1.9.7/src/com");
    private final File _10_nutchProject = new File("examples/10 - Nutch v0.4/src");
    private final File _11_pmdProject = new File("examples/11 - PMD/src/net");

    @Before
    public void setup() {
        observerPattern = MainWindow.extractPattern(new File("patterns/Observer.pattern"));
        visitorPattern = MainWindow.extractPattern(new File("patterns/Visitor.pattern"));
        commandPattern = MainWindow.extractPattern(new File("patterns/Command.pattern"));
        builderPattern = MainWindow.extractPattern(new File("patterns/Builder.pattern"));
        bridgePattern = MainWindow.extractPattern(new File("patterns/Bridge.pattern"));
        bridgeV2Pattern = MainWindow.extractPattern(new File("patterns/Bridge_v2.pattern"));
        abstractFactoryPattern = MainWindow.extractPattern(new File("patterns/Abstract Factory.pattern"));
        abstractFactoryV2Pattern = MainWindow.extractPattern(new File("patterns/Abstract Factory_v2.pattern"));

        allPatterns = Stream.of(observerPattern, visitorPattern, commandPattern, builderPattern,
                        bridgePattern,
                        bridgeV2Pattern,
                        abstractFactoryPattern,
                        abstractFactoryV2Pattern)
                .toArray(Pattern[]::new);
    }

    @Test
    public void jHotDrawProject_Detection() {

        final File project = jHotDrawProject;

        System.out.println(project.getPath());
        ProjectASTParser.parse(project.getAbsolutePath());
        System.out.println("  -> " + ProjectASTParser.Classes.size() + " classes");

        for (Pattern pattern : allPatterns) {

            detect(pattern);
            System.out.println(stringifyResults(pattern));

            // printGenerateArray(pattern, getCandidates());

            // System.out.println(stringify(pattern, getCandidates()));
        }
    }

    @Test
    public void antProject_Detection() {

        final File project = antProject;

        System.out.println(project.getPath());
        ProjectASTParser.parse(project.getAbsolutePath());
        System.out.println("  -> " + ProjectASTParser.Classes.size() + " classes");

        for (Pattern pattern : allPatterns) {

            detect(pattern);

            System.out.println(stringifyResults(pattern));
        }
    }

    @Test
    public void awtProject_Detection() {

        final File project = awtProject;

        System.out.println(project.getPath());
        ProjectASTParser.parse(project.getAbsolutePath());
        System.out.println("  -> " + ProjectASTParser.Classes.size() + " classes");

        for (Pattern pattern : allPatterns) {

            detect(pattern);

            System.out.println(stringifyResults(pattern));
        }
    }

    @Test
    public void storeProject_Detection() {

        final File project = storeProject;

        System.out.println(project.getPath());
        ProjectASTParser.parse(project.getAbsolutePath());
        System.out.println("  -> " + ProjectASTParser.Classes.size() + " classes");

        for (Pattern pattern : allPatterns) {

            detect(pattern);

            System.out.println(stringifyResults(pattern));
        }
    }

    @Test
    public void libPhoneNumberProject_Detection() {

        final File project = libPhoneNumberProject;

        System.out.println(project.getPath());
        ProjectASTParser.parse(project.getAbsolutePath());
        System.out.println("  -> " + ProjectASTParser.Classes.size() + " classes");

        for (Pattern pattern : allPatterns) {

            detect(pattern);

            System.out.println(stringifyResults(pattern));
        }
    }

    @Test
    public void jUnitProject_Detection() {

        final File project = _05_jUnit_Project;

        System.out.println(project.getPath());
        ProjectASTParser.parse(project.getAbsolutePath());
        System.out.println("  -> " + ProjectASTParser.Classes.size() + " classes");

        for (Pattern pattern : allPatterns) {

            detect(pattern);

            System.out.println(stringifyResults(pattern));

            getCandidates().forEach(patternCandidate -> System.out.println("  p => " + stringify(patternCandidate)));
            // getSuperCandidates().forEach(patternCandidate -> System.out.println("sp => " + stringify(patternCandidate)));
            // getHyperCandidates().forEach(patternCandidate -> System.out.println("hp => " + stringify(patternCandidate)));
        }
    }

    @Test
    public void pmartRepos_Detection() {

        analyse(_01_quickUMLProject);
        analyse(_02_lexiProject);
        analyse(_03_jRefactoryProject);
        analyse(_04_netBeansProject);
        analyse(_05_jUnit_Project);
        analyse(_06_jHotDrawProject);
        analyse(_08_mapperXMLProject);
        analyse(_10_nutchProject);
        analyse(_11_pmdProject);
    }

    public void analyse(File project) {

        System.out.println();
        System.out.println(project.getPath());
        ProjectASTParser.parse(project.getAbsolutePath());
        System.out.println("  -> " + ProjectASTParser.Classes.size() + " classes");

        for (Pattern pattern : allPatterns) {

            detect(pattern);

            System.out.println(stringifyResults(pattern));
        }
    }
}