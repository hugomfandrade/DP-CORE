package patterns;

import gui.MainWindow;
import org.junit.Before;
import org.junit.Test;
import parser.ProjectASTParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static patterns.PatternDetectionAlgorithmTestUtils.*;

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
    private final File antProject = new File("examples/ant-rel-1.6.2/src");
    private final File awtProject = new File("examples/jdk6-master/jdk/src/share/classes/java/awt");
    private final File jHotDrawProject = new File("examples/jhotdraw60b1/src");

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
                        bridgePattern, bridgeV2Pattern,
                        abstractFactoryPattern, abstractFactoryV2Pattern)
                .toArray(Pattern[]::new);
    }

    @Test
    public void jHotDrawProject_Detection() {

        final File project = jHotDrawProject;

        System.out.println(project.getAbsolutePath());

        Stream.of(allPatterns).forEach(pattern -> {

            ProjectASTParser.parse(project.getPath());
            // System.out.println(PatternDetectionAlgorithm.DetectPattern_Results(pattern, false));

            ArrayList<PatternCandidate> candidates = detect(pattern, project);

            System.out.println(stringify(pattern, candidates));
        });
    }

    @Test
    public void antProject_Detection() {

        final File project = antProject;

        System.out.println(project.getPath());

        Stream.of(allPatterns).forEach(pattern -> {

            ProjectASTParser.parse(project.getAbsolutePath());
            // System.out.println(PatternDetectionAlgorithm.DetectPattern_Results(pattern, false));

            ArrayList<PatternCandidate> candidates = detect(pattern, project);

            System.out.println(stringify(pattern, candidates));
        });
    }

    @Test
    public void awtProject_Detection() {

        final File project = awtProject;

        System.out.println(project.getPath());

        Stream.of(allPatterns).forEach(pattern -> {

            ProjectASTParser.parse(project.getAbsolutePath());
            // System.out.println(PatternDetectionAlgorithm.DetectPattern_Results(pattern, false));

            ArrayList<PatternCandidate> candidates = detect(pattern, project);

            System.out.println(stringify(pattern, candidates));
        });
    }

    private static ArrayList<PatternCandidate> detect(Pattern pattern, File project) {

        ProjectASTParser.parse(project.getAbsolutePath());
        PatternDetectionAlgorithm.DetectPatternFast(new Pattern("name"));
        PatternDetectionAlgorithm.clear();
        PatternDetectionAlgorithm.DetectPattern_Results(pattern, true);

        // return Optional.ofNullable(getCandidates()).orElse(new ArrayList<>());
        // return Optional.ofNullable(getSuperCandidates()).orElse(new ArrayList<>());
        return Optional.ofNullable(getHyperCandidates()).orElse(new ArrayList<>());
    }
}