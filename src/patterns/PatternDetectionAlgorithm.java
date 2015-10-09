package patterns;

import java.util.ArrayList;

import parser.ClassObject;
import parser.Connection;
import parser.ProjectASTParser;
import parser.ClassObject.Abstraction;

/**
 * Basic algorithm class. Its function DetectPattern(Pattern p) is responsible for
 * detecting patterns over the given code.
 * IMPORTANT: Need to parse the code and call finduses() etc for each ClassObject first.
 */
public class PatternDetectionAlgorithm {

	// //////////////////// VARIABLES ///////////////////////////////

	private static ArrayList<PatternCandidate> Candidates = new ArrayList<PatternCandidate>();
	private static ArrayList<PatternCandidate> TotalSuperCandidates = new ArrayList<PatternCandidate>();
	private static ArrayList<PatternCandidate> TotalHyperCandidates = new ArrayList<PatternCandidate>();
	private static Pattern p;

	// /////////////////// METHODS //////////////////////////////

	public PatternDetectionAlgorithm() {
	};

	/**
	 * Detects PatternCandidates and returns the results in a String.
	 * Used mainly for gui purposes.
	 * 
	 * @param p the pattern to be exported in a string
	 * @param grouping boolean indicating if grouping procedure will be applied on the results
	 * @return Returns a string which represents the pattern's details. Can be written into a file as it is.
	 */
	public static String DetectPattern_Results(Pattern p, Boolean grouping) {
		DetectPatternFast(p);
		if (grouping) {
			findSuperCandidates();
			findHyperCandidates();
			return HyperCandidates_inaString();
		} else {
			return PatternCandidates_inaString();
		}
	}

	/**
	 * Detects the Pattern candidates depending on the pattern rules and fills the arraylist Candidates.
	 * This fast version reduces the completion time by a large margin compared to its predecessor
	 * DetectPattern(Pattern p) by cutting away branches of the detection tree closer to its root.
	 * 
	 * @param pattern Defines the pattern to be detected.
	 */
	public static void DetectPatternFast(Pattern pattern) {
		p = pattern;
		// Clearing Candidates array of previous calls
		Candidates.clear();

		ArrayList<ClassObject> ClassObjects = new ArrayList<ClassObject>(ProjectASTParser.Classes.values());
		ArrayList<ClassObject> Candidate = new ArrayList<ClassObject>();
		Recursive(ClassObjects, Candidate, 0);
	}

	/**
	 * Recursive function implementing moving deeper into the detection tree.
	 * 
	 * @param ClassObjects The ClassObject items not being used already at an higher node
	 * @param CurrentCandidate The ClassObject items that have already been chosen for the pattern candidate
	 * @param depth A number indicating the depth of the current node of the detection tree. Root is number 0.
	 */
	private static void Recursive(ArrayList<ClassObject> ClassObjects, ArrayList<ClassObject> CurrentCandidate,
			int depth) {
		if (depth < p.get_memb_num()) {
			for (ClassObject o : ClassObjects) {
				if (AbstractionCheck(p.get_Members().get(depth), o) && ConnectionsCheck(CurrentCandidate, o, depth)) {
					ArrayList<ClassObject> NextClassObjects = new ArrayList<ClassObject>(ClassObjects);
					NextClassObjects.remove(o);
					ArrayList<ClassObject> NextCandidate = new ArrayList<ClassObject>(CurrentCandidate);
					NextCandidate.add(o);
					Recursive(NextClassObjects, NextCandidate, depth + 1);
				}
			}
		} else {
			PatternCandidate temp = new PatternCandidate(p.get_name());
			for (int i = 0; i < p.get_memb_num(); i++) {
				temp.addMember(CurrentCandidate.get(i), p.get_Members().get(i).getName(), p.get_Members().get(i)
						.getAbility());
			}
			Candidates.add(temp);
		}
	}

	/**
	 * Function that checks if ClassObject o2's abstraction agrees with o1's abstraction.
	 * 
	 * @param o1 ClassObject that defines the necessary abstraction
	 * @param o2 ClassObject whose abstraction will be checked
	 * @return Returns true if ClassObject o2's abstraction agrees with o1's.
	 */
	private static Boolean AbstractionCheck(ClassObject o1, ClassObject o2) {
		Abstraction abstraction1 = o1.get_abstraction();
		Abstraction abstraction2 = o2.get_abstraction();
		if (abstraction1 == abstraction2)
			return true;
		else if (abstraction1 == Abstraction.Abstracted
				&& (abstraction2 == Abstraction.Abstract || abstraction2 == Abstraction.Interface))
			return true;
		else if (abstraction1 == Abstraction.Any)
			return true;
		return false;
	}

	/**
	 * Checks if the given ClassObject satisfies the connections defined by the pattern,
	 * to the ClassObjects given.
	 * 
	 * @param co ClassObject ArrayList with the ClassObject items already chosen as candidate parts
	 * @param o The ClassObject to be checked
	 * @param depth A number indicating the depth of the current node of the detection tree. Root is number 0.
	 * @return Returns true if the input ClassObject of the specific depth contains
	 *         the required connections to the previous ClassObjects in the ArrayList given.
	 */
	private static Boolean ConnectionsCheck(ArrayList<ClassObject> co, ClassObject o, int depth) {
		for (Connection ac : p.get_Connections()) {
			Boolean flag = true;
			if (ac.get_From().getName().equalsIgnoreCase(Character.toString((char) (65 + depth)))
					&& ((int) (ac.get_To().getName().toCharArray()[0]) - 65) < depth) {
				switch (ac.get_type()) {
				case has:
					if (!(o.has(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))) {
						flag = false;
					}
					break;
				case use:
					if (!(o.uses(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))) {
						flag = false;
					}
					break;
				case inh:
					if (!(o.inherits(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))) {
						flag = false;
					}
					break;
				case ref:
					if (!(o.references(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))) {
						flag = false;
					}
					break;
				case create:
					if (!(o.creates(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))) {
						flag = false;
					}
					break;
				case call:
					if (!(o.calls(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))) {
						flag = false;
					}
					break;
				case wildcard:
					if (!(o.has(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))
							&& !(o.uses(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))
							&& !(o.inherits(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))
							&& !(o.references(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))
							&& !(o.creates(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))
							&& !(o.calls(co.get((int) (ac.get_To().getName()).toCharArray()[0] - 65)))) {
						flag = false;
					}
					break;
				default:
					System.out.println("ERROR!");
					System.exit(0);
					break;
				}
			} else if (ac.get_To().getName().equalsIgnoreCase(Character.toString((char) (65 + depth)))
					&& ((int) (ac.get_From().getName().toCharArray()[0]) - 65) < depth) {
				switch (ac.get_type()) {
				case has:
					if (!(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).has(o)) {
						flag = false;
					}
					break;
				case use:
					if (!(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).uses(o)) {
						flag = false;
					}
					break;
				case inh:
					if (!(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).inherits(o)) {
						flag = false;
					}
					break;
				case ref:
					if (!(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).references(o)) {
						flag = false;
					}
					break;
				case create:
					if (!(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).creates(o)) {
						flag = false;
					}
					break;
				case call:
					if (!(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).calls(o)) {
						flag = false;
					}
					break;
				case wildcard:
					if (!(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).has(o)
							&& !(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).uses(o)
							&& !(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).inherits(o)
							&& !(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).references(o)
							&& !(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).creates(o)
							&& !(co.get((int) (ac.get_From().getName()).toCharArray()[0] - 65)).calls(o)) {
						flag = false;
					}
					break;
				default:
					System.out.println("ERROR!");
					System.exit(0);
					break;
				}
			}
			if (flag == false)
				return false;
		}
		return true;
	}

	/**
	 * Finds all the SuperCandidates out of the PatternCandidates already detected.
	 * This is the first step of the grouping procedure.
	 * 
	 */
	public static void findSuperCandidates() {
		ArrayList<SuperCandidate> SuperCandidates = new ArrayList<SuperCandidate>();
		for (PatternCandidate c1 : Candidates) {
			for (PatternCandidate c2 : Candidates) {
				if (c1 != c2) {
					int count = 0;
					int dissimilarity = 0;
					for (int i = 0; i < p.get_memb_num(); i++) {
						if (c1.getMembers().get(i) == c2.getMembers().get(i))
							count++;
						else
							dissimilarity = i;
					}
					if (count != (p.get_memb_num() - 1))
						continue;
					// Checking for duplicates
					boolean flag = true;
					boolean flag2 = true;
					if (!SuperCandidates.isEmpty()) {
						for (SuperCandidate s : SuperCandidates) {
							if (s.getMergers().contains(c1) && s.getMergers().contains(c2))
								flag = false;
						}

					}
					if (flag) {
						// Create new SuperCandidate or fill an existing one
						if (!SuperCandidates.isEmpty()) {
							for (SuperCandidate s : SuperCandidates) {
								if (s.getDissimilarity() == dissimilarity
										&& (s.getMergers().contains(c1) || s.getMergers().contains(c2))) {
									// Fill an existing SuperCandidate
									if (s.getMergers().contains(c1)) {
										s.addDissimilarMember(c2);
										flag2 = false;
									} else {
										s.addDissimilarMember(c1);
										flag2 = false;
									}
								}
							}
						}
						if (flag2) {
							// Create new SuperCandidate
							SuperCandidate temp = new SuperCandidate(c1, dissimilarity);
							temp.addDissimilarMember(c2);
							SuperCandidates.add(temp);
						}

					}
				}
			}
		}
		TotalSuperCandidates.addAll(SuperCandidates);
		// Now check for Candidates left behind without a merge and add them in the SuperCandidates ArrayList
		for (PatternCandidate c : Candidates) {
			boolean flag = false;
			for (SuperCandidate s : SuperCandidates) {
				if (s.getMergers().contains(c)) {
					flag = true;
					break;
				}
			}
			// if false, that means this candidate is not part of a SuperCandidate
			if (flag == false) {
				TotalSuperCandidates.add(c);
			}

		}
	}

	/**
	 * Finds all the HyperCandidates out of the SuperCandidates and PatternCandidates already detected.
	 * This is the final step of the grouping procedure.
	 */
	public static void findHyperCandidates() {
		ArrayList<HyperCandidate> HyperCandidates = new ArrayList<HyperCandidate>();
		ArrayList<Integer> dissimilarity = new ArrayList<Integer>();
		for (PatternCandidate c1 : TotalSuperCandidates) {
			for (PatternCandidate c2 : TotalSuperCandidates) {
				if (c1 != c2) {
					dissimilarity.clear();
					if (c1 instanceof SuperCandidate && c2 instanceof SuperCandidate) {
						for (int i = 0; i < p.get_memb_num(); i++) {
							if (((SuperCandidate) c1).getDissimilarity() != i
									&& ((SuperCandidate) c2).getDissimilarity() != i) {
								if (c1.getMembers().get(i) != c2.getMembers().get(i))
									dissimilarity.add(i);
							} else {
								dissimilarity.add((((SuperCandidate) c1).getDissimilarity()));
								if (((SuperCandidate) c1).getDissimilarity() != ((SuperCandidate) c2)
										.getDissimilarity())
									dissimilarity.add(((SuperCandidate) c2).getDissimilarity());
							}
						}
					} else if (c1 instanceof SuperCandidate) {
						for (int i = 0; i < p.get_memb_num(); i++) {
							if (((SuperCandidate) c1).getDissimilarity() != i) {
								if (c1.getMembers().get(i) != c2.getMembers().get(i))
									dissimilarity.add(i);
							} else {
								dissimilarity.add((((SuperCandidate) c1).getDissimilarity()));
							}
						}
					} else if (c2 instanceof SuperCandidate) {
						for (int i = 0; i < p.get_memb_num(); i++) {
							if (((SuperCandidate) c2).getDissimilarity() != i) {
								if (c1.getMembers().get(i) != c2.getMembers().get(i))
									dissimilarity.add(i);
							} else {
								dissimilarity.add((((SuperCandidate) c2).getDissimilarity()));
							}
						}
					} else {
						// Neither c1 nor c2 are SuperCandidates
						for (int i = 0; i < p.get_memb_num(); i++) {
							if (c1.getMembers().get(i) != c2.getMembers().get(i))
								dissimilarity.add(i);
						}
					}
					if (dissimilarity.size() != 2)
						continue;
					// Checking for duplicates
					boolean flag = true;
					boolean flag2 = true;
					if (!HyperCandidates.isEmpty()) {
						for (HyperCandidate s : HyperCandidates) {
							if (s.getMergers().contains(c1) && s.getMergers().contains(c2))
								flag = false;
						}
					}
					if (flag) {
						// Create new HyperCandidate or fill an existing one
						if (!HyperCandidates.isEmpty()) {
							for (HyperCandidate h : HyperCandidates) {
								if (h.getDissimilarity1() == dissimilarity.get(0)
										&& h.getDissimilarity2() == dissimilarity.get(1)
										&& (h.getMergers().contains(c1) || h.getMergers().contains(c2))) {
									// Fill an existing SuperCandidate
									if (h.getMergers().contains(c1)) {
										h.addHyperMember(c2);
										;
										flag2 = false;
									} else {
										h.addHyperMember(c1);
										flag2 = false;
									}
								}
							}
						}
						if (flag2) {
							// Create new HyperCandidate
							HyperCandidate temp = new HyperCandidate(c1, dissimilarity);
							temp.addHyperMember(c2);
							HyperCandidates.add(temp);
						}
					}
				}
			}
		}
		TotalHyperCandidates.addAll(HyperCandidates);
		// Now check for Candidates left behind without a merge and add them in the TotalHyperCandidates ArrayList
		for (PatternCandidate c : TotalSuperCandidates) {
			boolean flag = false;
			for (HyperCandidate s : HyperCandidates) {
				if (s.getMergers().contains(c)) {
					flag = true;
					break;
				}
			}
			// if false, that means this candidate is not part of a HyperCandidate
			if (flag == false) {
				TotalHyperCandidates.add(c);
			}

		}
	}

	/**
	 * Prints the number of the SuperCandidates detected.
	 * 
	 */
	public void PrintSuperCount() {
		System.out.println("SuperCandidate count: " + TotalSuperCandidates.size());
	}

	/**
	 * Prints the SuperCandidates detected.
	 * 
	 */
	public void PrintSuperCandidates() {
		for (PatternCandidate e : TotalSuperCandidates) {
			System.out.println("");
			System.out.println("Candidate of Pattern " + e.getPatternName() + ":");
			e.printcandidate();
		}
	}

	/**
	 * Prints the number of the HyperCandidates detected.
	 * 
	 */
	public void PrintHyperCount() {
		System.out.println("HyperCandidate count: " + TotalHyperCandidates.size());
	}

	/**
	 * Prints the HyperCandidates detected.
	 * 
	 */
	public void PrintHyperCandidates() {
		for (PatternCandidate e : TotalHyperCandidates) {
			System.out.println("");
			System.out.println("Candidate of Pattern " + e.getPatternName() + ":");
			e.printcandidate();
		}
	}

	/**
	 * Prints the candidates found by the latest DetectPattern(Pattern p) function call.
	 */
	public void PrintResults() {
		for (PatternCandidate e : Candidates) {
			System.out.println("");
			System.out.println("Candidate of Pattern " + e.getPatternName() + ":");
			e.printcandidate();
		}
	}

	/**
	 * Prints the PatternCandidates found by the latest DetectPattern(Pattern p) (or Fast)
	 * function into a String which is returned.
	 * 
	 * @return s: A string of the printed PatternCandidates detected.
	 */
	private static String PatternCandidates_inaString() {
		String s;
		s = "Amount of Candidates found: " + Candidates.size() + "\n";
		for (PatternCandidate e : Candidates) {
			s += "\n";
			s += "Candidate of Pattern " + e.getPatternName() + ":\n";
			s += e.candidatetoString();
		}
		return s;
	}

	/**
	 * Prints the HyperCandidates, found by the grouping procedure,
	 * into a String which is returned.
	 * 
	 * @return s: A string of the printed PatternCandidates detected.
	 */
	private static String HyperCandidates_inaString() {
		String s;
		s = "Amount of HyperCandidates found: " + TotalHyperCandidates.size() + "\n";
		for (PatternCandidate e : TotalHyperCandidates) {
			s += "\n";
			s += "HyperCandidate of Pattern " + e.getPatternName() + ":\n";
			s += e.candidatetoString();
		}
		return s;
	}

	/**
	 * Method to clear this PatternDetectionAlgorithm instance.
	 * 
	 */
	public static void clear() {
		Candidates.clear();
		TotalSuperCandidates.clear();
		TotalHyperCandidates.clear();
		p.clear();
	}
}
