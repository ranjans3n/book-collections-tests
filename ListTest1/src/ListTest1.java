import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ListTest1 {
	
	// truck location
	static List<Integer> tl = Arrays.asList(4,0);    // this is location of truck 
	

	static class Id_dist implements Comparable<Id_dist> {
		Integer id = new Integer(0);
		Double dist = new Double(0.0);

		@Override
		public int compareTo(Id_dist o) {
			// TODO Auto-generated method stub
			Double compareDist = ((Double) o.dist);
			// descending order
			// return (int) (compareDist - this.dist); // for descending
			return (int) (this.dist - compareDist); // for ascending
		}

		public final static Comparator<Id_dist> Id_dist_Comparator = new Comparator<Id_dist>() {
			public int compare(Id_dist fd1, Id_dist fd2) {
				return fd1.compareTo(fd2); // for descending

			}
		};
	}

	private static final Comparator<? super Id_dist> Id_dist_Comparator = null;

	static List<List<Integer>> nearestM(Integer numpackets,
			List<List<Integer>> allLoc, Integer selected) {

		// System.out.println("nearestM: ");

		List<Id_dist> result = new ArrayList<Id_dist>();
//		List<Integer> tl = new ArrayList<Integer>();
//		tl.add(0);
//		tl.add(0);
		Integer c = new Integer(0);
		for (List<Integer> e : allLoc) {
			Id_dist i_d = new Id_dist();
			i_d.id = c++;
			i_d.dist = distance(e, tl);
			result.add(i_d);
		}
		// System.out.println("result list: ");
		// for (Id_dist idd: result) {
		// System.out.println("("+idd.id+","+idd.dist+")");
		// }

		List<List<Integer>> temp = new ArrayList<List<Integer>>();

		// System.out.println("allLoc list: ");
		// for (List<Integer> l : allLoc) {
		// System.out.println("("+l.get(0)+","+l.get(1)+")");
		// }
		//
		// sort the result by i_d
		result.sort(Id_dist_Comparator);

//		 System.out.println("sorted result list: ");
//		 for (Id_dist idd: result) {
//		 System.out.println("("+idd.id+","+idd.dist+")");
//		 }

		for (int i = 0; i < selected; i++) {
			Id_dist t = result.get(i);
			temp.add(allLoc.get(t.id));
		}

		// System.out.println("temp list: ");
		// for (List<Integer> l : temp) {
		// System.out.println("("+l.get(0)+","+l.get(1)+")");
		// }
		return temp;
	}

	private static double distance(List<Integer> e, List<Integer> tl) {
		// TODO Auto-generated method stub
		double result = 0.0;
		result += Math.sqrt(Math.pow(e.get(0) - tl.get(0), 2)
				+ Math.pow(e.get(1) - tl.get(1), 2));
		return result;
	}

	public static void main(String[] args) {

		List<List<Integer>> locs = new ArrayList<List<Integer>>(3);
		List<Integer> t = Arrays.asList(4, 7);
		locs.add(t);
		t = Arrays.asList(3, 9);
		locs.add(t);
		t = Arrays.asList(4, 1);
		locs.add(t);
		t = Arrays.asList(2, 1);
		locs.add(t);
		t = Arrays.asList(4, 10);
		locs.add(t);

		// you cannot use index on a empty list or a list with a non-zero
		// capacity.
		// You need to add first and then can use get and set
		System.out.println("Locations of " + locs.size() + " packages: ");
		for (List<Integer> l : locs) {
			System.out.println(l.get(0) + " " + l.get(1));
		}

		List<List<Integer>> sol = new ArrayList<List<Integer>>();

		sol = nearestM(3, locs, 3); // getting the solution

		System.out.println();
		System.out.println("Selected " + sol.size() + " packages for truck");
		for (List<Integer> l : sol) {
			System.out.println(l.get(0) + " " + l.get(1));
		}
	}

}