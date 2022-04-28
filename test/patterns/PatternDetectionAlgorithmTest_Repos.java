package patterns;

import gui.MainWindow;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import parser.ProjectASTParser;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
    private final File jHotDrawProject = new File("examples/jhotdraw60b1/src/main");

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

    private static String stringify(Pattern pattern, List<PatternCandidate> patternCandidates) {
        final StringBuilder s = new StringBuilder("Candidates found for " + pattern.get_name() + ": " + patternCandidates.size());

        if (true) return s.toString();
        s.append("\n");
        for (PatternCandidate e : patternCandidates) {
            // s.append("\n");
            // s.append("Candidate of Pattern ").append(e.getPatternName()).append(":\n");

            StringBuilder s0 = new StringBuilder();
            for (int i = 0; i < e.getMemberCount(); i++) {
                s0// .append(e.getMemberNames().get(i))
                        // .append("(")
                        .append(e.getMemberAbilities().get(i))
                        // .append("): ")
                        .append(": ")
                        .append(e.getMembers().get(i).getName())
                        .append("\t");
            }

            s.append(s0).append("\n");
        }
        return s.toString();
    }

    /*
    @Test
    public void allPatterns_combinedProject() {

        final File project = combinedPatternsProject;

        // ProjectASTParser.parse(project.getAbsolutePath());
        // System.out.println(PatternDetectionAlgorithm.DetectPattern_Results(visitorPattern, false));

        Assertions.assertEquals(4, detect(abstractFactoryPattern, project).size());
        Assertions.assertEquals(0, detect(abstractFactoryV2Pattern, project).size());
        Assertions.assertEquals(4, detect(bridgePattern, project).size());
        Assertions.assertEquals(0, detect(bridgeV2Pattern, project).size());
        Assertions.assertEquals(2, detect(builderPattern, project).size());
        Assertions.assertEquals(6, detect(commandPattern, project).size());
        Assertions.assertEquals(4, detect(observerPattern, project).size());
        Assertions.assertEquals(2, detect(visitorPattern, project).size());

        // verify (only) the concrete names of the first members
        assertEquals(detect(abstractFactoryPattern, project), "ReptileFactory", "ReptileFactory", "MammalFactory", "MammalFactory");
        assertEquals(detect(abstractFactoryV2Pattern, project));
        assertEquals(detect(bridgePattern, project), "Triangle", "Triangle", "Pentagon", "Pentagon");
        assertEquals(detect(bridgeV2Pattern, project));
        assertEquals(detect(builderPattern, project), "MealDirector", "MealDirector");
        assertEquals(detect(commandPattern, project), "JapaneseMealBuilder", "ShoppingCartVisitorImpl", "ShoppingCartVisitorImpl", "LightOffCommand", "ItalianMealBuilder", "LightOnCommand");
        assertEquals(detect(observerPattern, project), "MyTopic", "ShoppingCartVisitorImpl", "ShoppingCartVisitorImpl", "MyTopicSubscriber");
        assertEquals(detect(visitorPattern, project), "Book", "Fruit");

    }


    @Test
    public void observerDetection_observerProject() {

        final Pattern pattern = observerPattern;
        final File project = observerProject;

        ArrayList<PatternCandidate> patternCandidates = detect(pattern, project);

        Assertions.assertEquals(2, patternCandidates.size());

        {
            PatternCandidate patternCandidate = patternCandidates.get(1);

            assertEquals(patternCandidate, 0, "A", "Concrete Observer", "MyTopicSubscriber");
            assertEquals(patternCandidate, 1, "B", "Observer", "Observer");
            assertEquals(patternCandidate, 2, "C", "Subject", "Subject");
        }

        {
            PatternCandidate patternCandidate = patternCandidates.get(0);

            assertEquals(patternCandidate, 0, "A", "Concrete Observer", "MyTopic");
            assertEquals(patternCandidate, 1, "B", "Observer", "Subject");
            assertEquals(patternCandidate, 2, "C", "Subject", "Observer");
        }
    }
    */

    /*
    @Test
    public void observerDetection_othersProject() {

        final Pattern pattern = observerPattern;

        Assertions.assertEquals(0, detect(pattern, abstractFactoryProject).size());
        Assertions.assertEquals(0, detect(pattern, bridgeProject).size());
        Assertions.assertEquals(0, detect(pattern, builderProject).size());
        Assertions.assertEquals(0, detect(pattern, commandProject).size());
        Assertions.assertEquals(2, detect(pattern, visitorProject).size());
    }
    */

    private static ArrayList<PatternCandidate> detect(Pattern pattern, File project) {

        ProjectASTParser.parse(project.getAbsolutePath());
        PatternDetectionAlgorithm.DetectPattern_Results(pattern, false);

        return Optional.ofNullable(getCandidates()).orElse(new ArrayList<>());
    }

    private static void assertEquals(PatternCandidate patternCandidate, int memberPos,
                                     String expectedMemberName,
                                     String expectedMemberAbility,
                                     String expectedMemberConcreteName) {

        Assertions.assertEquals(expectedMemberName, patternCandidate.getMemberNames().get(memberPos));
        Assertions.assertEquals(expectedMemberAbility, patternCandidate.getMemberAbilities().get(memberPos));
        Assertions.assertEquals(expectedMemberConcreteName, patternCandidate.getMembers().get(memberPos).getName());
    }

    private static void assertEquals(ArrayList<PatternCandidate> patternCandidates, String... expectedMemberConcreteNames) {

        Assertions.assertEquals(patternCandidates.size(), expectedMemberConcreteNames.length);

        for (int i = 0 ; i < patternCandidates.size() ; i++) {
            Assertions.assertEquals(expectedMemberConcreteNames[i], patternCandidates.get(i).getMembers().get(0).getName());
        }
    }

    private static ArrayList<PatternCandidate> getCandidates() {
        try {
            Field field = PatternDetectionAlgorithm.class.getDeclaredField("Candidates");
            field.setAccessible(true); // Suppress Java language access checking
            return (ArrayList<PatternCandidate>) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}