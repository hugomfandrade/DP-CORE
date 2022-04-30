package patterns;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class PatternDetectionAlgorithmTestUtils {

    private PatternDetectionAlgorithmTestUtils() {
        throw new RuntimeException("this is a utility class");
    }

    public static String stringify(PatternCandidate patternCandidate) {
        final StringBuilder s = new StringBuilder();
        patternCandidate.getMembers().stream().forEach(o -> s.append(o.getName()).append(";"));
        return s.toString();
    }

    public static String stringify(Pattern pattern, List<PatternCandidate> patternCandidates) {

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

    public static ArrayList<PatternCandidate> getCandidates() {
        try {
            Field field = PatternDetectionAlgorithm.class.getDeclaredField("Candidates");
            field.setAccessible(true); // Suppress Java language access checking
            return (ArrayList<PatternCandidate>) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<PatternCandidate> getHyperCandidates() {
        try {
            Field field = PatternDetectionAlgorithm.class.getDeclaredField("TotalHyperCandidates");
            field.setAccessible(true); // Suppress Java language access checking
            return (ArrayList<PatternCandidate>) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<PatternCandidate> getSuperCandidates() {
        try {
            Field field = PatternDetectionAlgorithm.class.getDeclaredField("TotalSuperCandidates");
            field.setAccessible(true); // Suppress Java language access checking
            return (ArrayList<PatternCandidate>) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}