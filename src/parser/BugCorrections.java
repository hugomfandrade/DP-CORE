package parser;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.TreePath;
import com.sun.tools.javac.tree.JCTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface BugCorrections {

    boolean ENABLED = false;

    static Object innerClassesNamingFix(Object key) {
        if (ENABLED && key instanceof String) {
            String keyString = (String) key;
            key = keyString.substring(!keyString.contains(".") ? 0 : keyString.lastIndexOf(".") + 1);
        }
        return key;
    }

    static String innerClassesNamingFix(String key) {
        if (ENABLED && key != null) {
            key = key.substring(!key.contains(".") ? 0 : key.lastIndexOf(".") + 1);
        }
        return key;
    }

    static String getClassScope(TreePath treePath, ClassTree aclass) {
        if (true || !ENABLED) return aclass.getSimpleName().toString();
        return getClassScope(treePath);
    }

    static String getClassScope(TreePath node) {

        TreePath originalNode = node;

        List<String> fullName = new ArrayList<>();

        while (node.getParentPath() != null) {
            if (node.getLeaf() instanceof ClassTree) {

                fullName.add(((ClassTree) node.getLeaf()).getSimpleName().toString());
            }
            node = node.getParentPath();
        }

        // names may be null/empty. tells class is anonymous

        return // originalNode.getCompilationUnit().getPackageName() + "." +
                IntStream.range(0, fullName.size())
                .mapToObj(i -> fullName.get(fullName.size() - i - 1))
                .collect(Collectors.joining("."));
    }

    static ClassObject getClassObject(TreePath node) {
        if (!ENABLED) return ProjectASTParser.thisclass;

        String className = getClassScope(node);
        if (className != null && ProjectASTParser.Classes.containsKey(className)) {
            return ProjectASTParser.Classes.get(className);
        }
        return null;
    }

    static String getClassName(Tree tree) {
        if (!ENABLED) return tree.toString();
        if (tree.getKind() == Tree.Kind.MEMBER_SELECT) {
            JCTree.JCFieldAccess access = (JCTree.JCFieldAccess) tree;
            return access.name.toString();
        }
        return tree.toString();
    }

    static boolean isStatic(ArrayList<String> modifiers) {
        if (!ENABLED) return false;
        return modifiers.contains("static");
    }
}
