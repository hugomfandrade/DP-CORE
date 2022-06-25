package patterns;

import parser.ClassObject;
import parser.ProjectASTParser;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class PatternDetectionAlgorithmTestUtils {

    private PatternDetectionAlgorithmTestUtils() {
        throw new RuntimeException("this is a utility class");
    }


    public static void analyse(File project, Pattern[] patterns) {
        analyse(project, patterns, false);
    }

    public static void analyse(File project, Pattern[] patterns, boolean printify) {

        System.out.println();
        System.out.print(project.getPath());
        ProjectASTParser.parse(project.getAbsolutePath());
        System.out.println("  -> " + ProjectASTParser.Classes.size() + " classes");

        for (Pattern pattern : patterns) {

            detect(pattern);

            System.out.println(stringifyResults(pattern));
            if (printify) System.out.print(stringifyResults(pattern, getCandidates()));
        }
    }

    public static List<PatternCandidate> detect(Pattern pattern, File project) {

        ProjectASTParser.parse(project.getAbsolutePath());
        return detect(pattern);
    }

    public static List<PatternCandidate> detect(Pattern pattern) {

        PatternDetectionAlgorithm.DetectPatternFast(new Pattern("name"));
        PatternDetectionAlgorithm.clear();
        PatternDetectionAlgorithm.DetectPattern_Results(pattern, true);

        /*
        getCandidates().forEach(patternCandidate -> System.out.println("  p => " + stringify(patternCandidate)));
        getSuperCandidates().forEach(patternCandidate -> System.out.println("sp => " + stringify(patternCandidate)));
        getHyperCandidates().forEach(patternCandidate -> System.out.println("hp => " + stringify(patternCandidate)));
        */

        return sort(Optional.ofNullable(getCandidates()).orElse(new ArrayList<>()));
    }

    public static List<PatternCandidate> sort(List<PatternCandidate> patternCandidates) {
        patternCandidates.sort((o1, o2) -> {
            for (int i = 0 ; i < o1.getMembers().size() && i < o2.getMembers().size() ; i++) {
                int compare = o1.getMembers().get(i).getName().compareTo(o2.getMembers().get(i).getName());
                if (compare != 0) return compare;
            }
            return 0;
        });
        return patternCandidates;
    }

    public static String stringifyResults(Pattern pattern) {

        return pattern.get_name() + " => " +
                getCandidates().size() + " " +
                getSuperCandidates().size() + " " +
                getHyperCandidates().size();
    }

    public static String stringify(PatternCandidate patternCandidate) {
        final StringBuilder s = new StringBuilder();
        patternCandidate.getMembers().stream().forEach(o -> s.append(o.getName()).append(";"));
        return s.toString();
    }

    public static String stringify(Pattern pattern, List<PatternCandidate> patternCandidates) {

        final StringBuilder s = new StringBuilder("Candidates found for " + pattern.get_name() + ": " + patternCandidates.size());

        if (false) return s.toString();
        s.append("\n");

        patternCandidates.sort(Comparator.comparing(o -> o.getMembers().get(0).getName()));
        patternCandidates.sort((o1, o2) -> {
            for (int i = 0 ; i < o1.getMembers().size() && i < o2.getMembers().size() ; i++) {
                int compare = o1.getMembers().get(i).getName().compareTo(o2.getMembers().get(i).getName());
                if (compare != 0) return compare;
            }
            return 0;
        });
        for (PatternCandidate e : patternCandidates) {
            // s.append("\n");
            // s.append("Candidate of Pattern ").append(e.getPatternName()).append(":\n");

            StringBuilder s0 = new StringBuilder();
            s0.append(" -");
            for (int i = 0; i < e.getMemberCount(); i++) {
                s0// .append(e.getMemberNames().get(i))
                        // .append("(")
                        // .append(e.getMemberAbilities().get(i))
                        // .append("): ")
                        // .append(": ")
                        .append(e.getMembers().get(i).getName())
                        .append(", ")
                        .append(" ");
            }

            s.append(s0).append("\n");
        }
        return s.toString();
    }

    public static String stringifyResults(Pattern pattern, List<PatternCandidate> patternCandidates) {

        final StringBuilder s = new StringBuilder();

        Function<String, String> simplify = s1 -> s1.substring(s1.lastIndexOf('.') + 1);

        patternCandidates
                .forEach(patternCandidate -> patternCandidate
                        .getMembers()
                        .forEach(c -> c.setName(simplify.apply(c.getName())))
                );

        sort(patternCandidates);

        for (PatternCandidate e : patternCandidates) {
            // s.append("\n");
            // s.append("Candidate of Pattern ").append(e.getPatternName()).append(":\n");

            StringBuilder s0 = new StringBuilder();
            s0.append(" -");
            for (int i = 0; i < e.getMemberCount(); i++) {
                s0// .append(e.getMemberNames().get(i))
                        // .append("(")
                        // .append(e.getMemberAbilities().get(i))
                        // .append("): ")
                        // .append(": ")
                        .append(e.getMembers().get(i).getName())
                        .append(", ")
                        .append(" ");
            }

            s.append(s0).append("\n");
        }
        return s.toString();
    }

    public static List<PatternCandidate> getCandidates() {
        try {
            Field field = PatternDetectionAlgorithm.class.getDeclaredField("Candidates");
            field.setAccessible(true); // Suppress Java language access checking
            return sort((ArrayList<PatternCandidate>) field.get(null));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<PatternCandidate> getHyperCandidates() {
        try {
            Field field = PatternDetectionAlgorithm.class.getDeclaredField("TotalHyperCandidates");
            field.setAccessible(true); // Suppress Java language access checking
            return sort((ArrayList<PatternCandidate>) field.get(null));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<PatternCandidate> getSuperCandidates() {
        try {
            Field field = PatternDetectionAlgorithm.class.getDeclaredField("TotalSuperCandidates");
            field.setAccessible(true); // Suppress Java language access checking
            return sort((ArrayList<PatternCandidate>) field.get(null));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printGenerateArray(Pattern pattern, List<PatternCandidate> candidates) {

        System.out.print("var " + pattern.get_name() + "Results = [");

        boolean first = true;
        candidates.sort((o1, o2) -> {
            for (int i = 0 ; i < o1.getMembers().size() && i < o2.getMembers().size() ; i++) {
                int compare = o1.getMembers().get(i).getName().compareTo(o2.getMembers().get(i).getName());
                if (compare != 0) return compare;
            }
            return 0;
        });
        for (PatternCandidate patternCandidate : candidates) {
            if (first) {
                first = false;
                System.out.println();
            }
            else {
                System.out.println(",");
            }
            System.out.print("[");
            boolean firstOfAll = true;
            for (ClassObject object : patternCandidate.getMembers()) {
                if (firstOfAll) {
                    firstOfAll = false;
                }
                else {
                    System.out.print(",");
                }
                System.out.print("\"" + object.getName() + "\"");
            }
            System.out.print("]");
        }

        System.out.println("];");
    }
}