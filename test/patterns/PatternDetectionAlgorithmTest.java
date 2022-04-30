package patterns;

import gui.MainWindow;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import parser.ProjectASTParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import static patterns.PatternDetectionAlgorithmTestUtils.getCandidates;

public class PatternDetectionAlgorithmTest {

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

    // Examples
    //
    private final File abstractFactoryProject = new File("examples/Abstract Factory Example");
    private final File bridgeProject = new File("examples/Bridge Example");
    private final File builderProject = new File("examples/Builder Example");
    private final File combinedPatternsProject = new File("examples/Combined Patterns Example");
    private final File commandProject = new File("examples/Command Example");
    private final File observerProject = new File("examples/Observer Example");
    private final File visitorProject = new File("examples/Visitor Example");

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

    @Test
    public void observerDetection_othersProject() {

        final Pattern pattern = observerPattern;

        Assertions.assertEquals(0, detect(pattern, abstractFactoryProject).size());
        Assertions.assertEquals(0, detect(pattern, bridgeProject).size());
        Assertions.assertEquals(0, detect(pattern, builderProject).size());
        Assertions.assertEquals(0, detect(pattern, commandProject).size());
        Assertions.assertEquals(2, detect(pattern, visitorProject).size());
    }

    @Test
    public void abstractFactoryDetection_abstractFactoryProject() {

        final Pattern pattern = abstractFactoryPattern;
        final File project = abstractFactoryProject;

        ArrayList<PatternCandidate> patternCandidates = detect(pattern, project);

        Assertions.assertEquals(4, patternCandidates.size());

        {
            PatternCandidate patternCandidate = patternCandidates.get(0);

            assertEquals(patternCandidate, 0, "A", "Concrete Factory", "ReptileFactory");
            assertEquals(patternCandidate, 1, "B", "Abstract Factory", "SpeciesFactory");
            assertEquals(patternCandidate, 2, "C", "Product", "Snake");
            assertEquals(patternCandidate, 3, "D", "Abstract Product", "Animal");
        }

        {
            PatternCandidate patternCandidate = patternCandidates.get(1);

            assertEquals(patternCandidate, 0, "A", "Concrete Factory", "ReptileFactory");
            assertEquals(patternCandidate, 1, "B", "Abstract Factory", "SpeciesFactory");
            assertEquals(patternCandidate, 2, "C", "Product", "Tyrannosaurus");
            assertEquals(patternCandidate, 3, "D", "Abstract Product", "Animal");
        }

        {
            PatternCandidate patternCandidate = patternCandidates.get(2);

            assertEquals(patternCandidate, 0, "A", "Concrete Factory", "MammalFactory");
            assertEquals(patternCandidate, 1, "B", "Abstract Factory", "SpeciesFactory");
            assertEquals(patternCandidate, 2, "C", "Product", "Cat");
            assertEquals(patternCandidate, 3, "D", "Abstract Product", "Animal");
        }

        {
            PatternCandidate patternCandidate = patternCandidates.get(3);

            assertEquals(patternCandidate, 0, "A", "Concrete Factory", "MammalFactory");
            assertEquals(patternCandidate, 1, "B", "Abstract Factory", "SpeciesFactory");
            assertEquals(patternCandidate, 2, "C", "Product", "Dog");
            assertEquals(patternCandidate, 3, "D", "Abstract Product", "Animal");
        }
    }

    @Test
    public void abstractFactoryDetection_othersProject() {

        final Pattern pattern = abstractFactoryPattern;

        Assertions.assertEquals(0, detect(pattern, bridgeProject).size());
        Assertions.assertEquals(0, detect(pattern, builderProject).size());
        Assertions.assertEquals(0, detect(pattern, commandProject).size());
        Assertions.assertEquals(0, detect(pattern, observerProject).size());
        Assertions.assertEquals(0, detect(pattern, visitorProject).size());
    }

    @Test
    public void abstractFactoryDetectionV2_abstractFactoryProject() {

        final Pattern pattern = abstractFactoryV2Pattern;
        final File project = abstractFactoryProject;

        ArrayList<PatternCandidate> patternCandidates = detect(pattern, project);

        Assertions.assertEquals(0, patternCandidates.size());
    }

    @Test
    public void abstractFactoryV2Detection_othersProject() {

        final Pattern pattern = abstractFactoryV2Pattern;

        Assertions.assertEquals(0, detect(pattern, bridgeProject).size());
        Assertions.assertEquals(0, detect(pattern, builderProject).size());
        Assertions.assertEquals(0, detect(pattern, commandProject).size());
        Assertions.assertEquals(0, detect(pattern, observerProject).size());
        Assertions.assertEquals(0, detect(pattern, visitorProject).size());
    }

    @Test
    public void bridgePattern_bridgeProject() {

        final Pattern pattern = bridgePattern;
        final File project = bridgeProject;

        ArrayList<PatternCandidate> patternCandidates = detect(pattern, project);

        Assertions.assertEquals(2, patternCandidates.size());

        {
            PatternCandidate patternCandidate = patternCandidates.get(0);

            assertEquals(patternCandidate, 0, "A", "Refined Abstraction", "LogitechRemoteControl");
            assertEquals(patternCandidate, 1, "B", "Abstraction", "AbstractRemoteControl");
            assertEquals(patternCandidate, 2, "C", "Concrete Implementor", "SamsungTV");
            assertEquals(patternCandidate, 3, "D", "Implementor", "ITV");
        }

        {
            PatternCandidate patternCandidate = patternCandidates.get(1);

            assertEquals(patternCandidate, 0, "A", "Refined Abstraction", "LogitechRemoteControl");
            assertEquals(patternCandidate, 1, "B", "Abstraction", "AbstractRemoteControl");
            assertEquals(patternCandidate, 2, "C", "Concrete Implementor", "SonyTV");
            assertEquals(patternCandidate, 3, "D", "Implementor", "ITV");
        }
    }

    @Test
    public void bridgeDetection_othersProject() {

        final Pattern pattern = bridgePattern;

        Assertions.assertEquals(0, detect(pattern, abstractFactoryProject).size());
        Assertions.assertEquals(0, detect(pattern, builderProject).size());
        Assertions.assertEquals(0, detect(pattern, commandProject).size());
        Assertions.assertEquals(0, detect(pattern, observerProject).size());
        Assertions.assertEquals(0, detect(pattern, visitorProject).size());
    }

    @Test
    public void bridgeV2Pattern_bridgeProject() {

        final Pattern pattern = bridgeV2Pattern;
        final File project = bridgeProject;

        ArrayList<PatternCandidate> patternCandidates = detect(pattern, project);

        Assertions.assertEquals(0, patternCandidates.size());
    }

    @Test
    public void bridgeV2Detection_othersProject() {

        final Pattern pattern = bridgeV2Pattern;

        Assertions.assertEquals(0, detect(pattern, abstractFactoryProject).size());
        Assertions.assertEquals(0, detect(pattern, builderProject).size());
        Assertions.assertEquals(0, detect(pattern, commandProject).size());
        Assertions.assertEquals(0, detect(pattern, observerProject).size());
        Assertions.assertEquals(0, detect(pattern, visitorProject).size());
    }

    @Test
    public void builderPattern_builderProject() {

        final Pattern pattern = builderPattern;
        final File project = builderProject;

        ArrayList<PatternCandidate> patternCandidates = detect(pattern, project);

        Assertions.assertEquals(2, patternCandidates.size());

        {
            PatternCandidate patternCandidate = patternCandidates.get(0);

            assertEquals(patternCandidate, 0, "A", "Director", "MealDirector");
            assertEquals(patternCandidate, 1, "B", "Builder", "MealBuilder");
            assertEquals(patternCandidate, 2, "C", "Concrete Builder", "JapaneseMealBuilder");
            assertEquals(patternCandidate, 3, "D", "Product", "Meal");
        }

        {
            PatternCandidate patternCandidate = patternCandidates.get(1);

            assertEquals(patternCandidate, 0, "A", "Director", "MealDirector");
            assertEquals(patternCandidate, 1, "B", "Builder", "MealBuilder");
            assertEquals(patternCandidate, 2, "C", "Concrete Builder", "ItalianMealBuilder");
            assertEquals(patternCandidate, 3, "D", "Product", "Meal");
        }
    }

    @Test
    public void builderDetection_othersProject() {

        final Pattern pattern = builderPattern;

        Assertions.assertEquals(0, detect(pattern, abstractFactoryProject).size());
        Assertions.assertEquals(0, detect(pattern, bridgeProject).size());
        Assertions.assertEquals(0, detect(pattern, commandProject).size());
        Assertions.assertEquals(0, detect(pattern, observerProject).size());
        Assertions.assertEquals(0, detect(pattern, visitorProject).size());
    }

    @Test
    public void commandPattern_CommandProject() {

        final Pattern pattern = commandPattern;
        final File project = commandProject;

        ArrayList<PatternCandidate> patternCandidates = detect(pattern, project);

        Assertions.assertEquals(2, patternCandidates.size());

        {
            PatternCandidate patternCandidate = patternCandidates.get(0);

            assertEquals(patternCandidate, 0, "A", "Concrete Command", "LightOffCommand");
            assertEquals(patternCandidate, 1, "B", "Command", "Command");
            assertEquals(patternCandidate, 2, "C", "Receiver", "Light");
            assertEquals(patternCandidate, 3, "D", "Invoker", "RemoteControl");
        }

        {
            PatternCandidate patternCandidate = patternCandidates.get(1);

            assertEquals(patternCandidate, 0, "A", "Concrete Command", "LightOnCommand");
            assertEquals(patternCandidate, 1, "B", "Command", "Command");
            assertEquals(patternCandidate, 2, "C", "Receiver", "Light");
            assertEquals(patternCandidate, 3, "D", "Invoker", "RemoteControl");
        }
    }

    @Test
    public void commandDetection_othersProject() {

        final Pattern pattern = commandPattern;

        Assertions.assertEquals(0, detect(pattern, abstractFactoryProject).size());
        Assertions.assertEquals(0, detect(pattern, bridgeProject).size());
        Assertions.assertEquals(2, detect(pattern, builderProject).size());
        Assertions.assertEquals(0, detect(pattern, observerProject).size());
        Assertions.assertEquals(0, detect(pattern, visitorProject).size());
    }

    @Test
    public void visitorPattern_visitorProject() {

        final Pattern pattern = visitorPattern;
        final File project = visitorProject;

        ArrayList<PatternCandidate> patternCandidates = detect(pattern, project);

        Assertions.assertEquals(2, patternCandidates.size());

        {
            PatternCandidate patternCandidate = patternCandidates.get(1);

            assertEquals(patternCandidate, 0, "A", "Concrete Element", "Fruit");
            assertEquals(patternCandidate, 1, "B", "Element", "ItemElement");
            assertEquals(patternCandidate, 2, "C", "Concrete Visitor", "ShoppingCartVisitorImpl");
            assertEquals(patternCandidate, 3, "D", "Visitor", "ShoppingCartVisitor");
        }

        {
            PatternCandidate patternCandidate = patternCandidates.get(0);

            assertEquals(patternCandidate, 0, "A", "Concrete Element", "Book");
            assertEquals(patternCandidate, 1, "B", "Element", "ItemElement");
            assertEquals(patternCandidate, 2, "C", "Concrete Visitor", "ShoppingCartVisitorImpl");
            assertEquals(patternCandidate, 3, "D", "Visitor", "ShoppingCartVisitor");
        }
    }

    @Test
    public void visitorDetection_othersProject() {

        final Pattern pattern = visitorPattern;

        Assertions.assertEquals(0, detect(pattern, abstractFactoryProject).size());
        Assertions.assertEquals(0, detect(pattern, bridgeProject).size());
        Assertions.assertEquals(0, detect(pattern, builderProject).size());
        Assertions.assertEquals(0, detect(pattern, commandProject).size());
        Assertions.assertEquals(0, detect(pattern, observerProject).size());
    }

    @Test
    public void Pattern_Project() {

        final Pattern pattern = observerPattern;
        final File project = observerProject;

        ProjectASTParser.parse(project.getAbsolutePath());
        System.out.println(PatternDetectionAlgorithm.DetectPattern_Results(pattern, false));

        if (true) return;
        ArrayList<PatternCandidate> patternCandidates = detect(pattern, project);

        Assertions.assertEquals(2, patternCandidates.size());

        {
            PatternCandidate patternCandidate = patternCandidates.get(0);

            assertEquals(patternCandidate, 0, "A", "Concrete Observer", "MyTopicSubscriber");
            assertEquals(patternCandidate, 1, "B", "Observer", "Observer");
            assertEquals(patternCandidate, 2, "C", "Subject", "Subject");
        }
    }

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
        Assertions.assertEquals(4, detect(commandPattern, project).size());
        Assertions.assertEquals(4, detect(observerPattern, project).size());
        Assertions.assertEquals(2, detect(visitorPattern, project).size());

        // verify (only) the concrete names of the first members
        assertEquals(detect(abstractFactoryPattern, project), "ReptileFactory", "ReptileFactory", "MammalFactory", "MammalFactory");
        assertEquals(detect(abstractFactoryV2Pattern, project));
        assertEquals(detect(bridgePattern, project), "Triangle", "Triangle", "Pentagon", "Pentagon");
        assertEquals(detect(bridgeV2Pattern, project));
        assertEquals(detect(builderPattern, project), "MealDirector", "MealDirector");
        assertEquals(detect(commandPattern, project), "JapaneseMealBuilder", "LightOffCommand", "ItalianMealBuilder", "LightOnCommand");
        assertEquals(detect(observerPattern, project), "MyTopic", "ShoppingCartVisitorImpl", "ShoppingCartVisitorImpl", "MyTopicSubscriber");
        assertEquals(detect(visitorPattern, project), "Book", "Fruit");

    }

    private static ArrayList<PatternCandidate> detect(Pattern pattern, File project) {

        ProjectASTParser.parse(project.getAbsolutePath());
        PatternDetectionAlgorithm.DetectPatternFast(new Pattern("name"));
        PatternDetectionAlgorithm.clear();
        PatternDetectionAlgorithm.DetectPattern_Results(pattern, true);

        /*
        System.out.println(pattern.get_name() + " " +
                getCandidates().size() + " " +
                getSuperCandidates().size() + " " +
                getHyperCandidates().size());

        getCandidates().forEach(patternCandidate -> System.out.println("  p => " + stringify(patternCandidate)));
        getSuperCandidates().forEach(patternCandidate -> System.out.println("sp => " + stringify(patternCandidate)));
        getHyperCandidates().forEach(patternCandidate -> System.out.println("hp => " + stringify(patternCandidate)));
        */

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
}