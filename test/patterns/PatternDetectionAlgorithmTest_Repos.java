package patterns;

import gui.MainWindow;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.stream.Stream;

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

    // Projects
    //
    private final File antProject = new File("projects/Article/ant-rel-1.6.2/src/main");
    private final File awtProject = new File("projects/Article/awt-jdk6");
    private final File jHotDrawProject = new File("projects/Article/jhotdraw60b1/src");

    private final File _01_quickUMLProject = new File("projects/P-MARt/1 - QuickUML 2001/src");
    private final File _02_lexiProject = new File("projects/P-MARt/2 - Lexi v0.1.1 alpha/src");
    private final File _03_jRefactoryProject = new File("projects/P-MARt/3 - jRefactory v2.6.24/src");
    private final File _04_netBeansProject = new File("projects/P-MARt/4 - Netbeans v1.0.x/src");
    private final File _05_jUnit_Project = new File("projects/P-MARt/5 - JUnit v3.7/src/junit");
    private final File _06_jHotDrawProject = new File("projects/P-MARt/6 - JHotDraw v5.1/src");
    private final File _08_mapperXMLProject = new File("projects/P-MARt/8 - MapperXML v1.9.7/src/com");
    private final File _10_nutchProject = new File("projects/P-MARt/10 - Nutch v0.4/src");
    private final File _11_pmdProject = new File("projects/P-MARt/11 - PMD/src/net");

    private final File jUnit34Project = new File("projects/others/junit3.4");

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

        allPatterns = Stream.of(abstractFactoryPattern, builderPattern, bridgePattern, commandPattern, observerPattern, visitorPattern)
                .toArray(Pattern[]::new);
    }

    @Test
    public void articleProjects_Detection() {

        analyse(jHotDrawProject);
        analyse(antProject);
        analyse(awtProject);
    }

    @Test
    public void pmartRepos_Detection() {

        analyse(_01_quickUMLProject);
        analyse(_02_lexiProject);
        analyse(_03_jRefactoryProject);
        // analyse(_04_netBeansProject);
        analyse(_05_jUnit_Project);
        // analyse(_06_jHotDrawProject);
        analyse(_08_mapperXMLProject);
        analyse(_10_nutchProject);
        analyse(_11_pmdProject);
    }

    @Test
    public void otherProjects_Detection() {

        analyse(jUnit34Project);
    }

    public void analyse(File project) {
        this.analyse(project, false);
    }

    public void analyse(File project, boolean printify) {
        PatternDetectionAlgorithmTestUtils.analyse(project, allPatterns, printify);
    }
}