import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ListTest2 {	

	protected static class Movie implements Comparable<Movie> {
		String name="";
		double rank=0.0;
		
		List<Movie> neighbors = new ArrayList<Movie>();
		
		public Movie (String name, double rank) {
			this.name=name;
			this.rank=rank;
		}
		
		public String getName() {
			return name;
		}	
		public double getRank() {
			return rank;
		}
		
		public void setNeighbors(Movie movie) {
			neighbors.add( movie);
		}
		
		public List<Movie> getNeighbors() {
			return neighbors;
		}
		private void addNeighbor(Movie m) {
			// TODO Auto-generated method stub
			neighbors.add(m);
		}

		@Override
		public int compareTo(Movie o) {
			// TODO Auto-generated method stub
			double compareRank = ((double) o.rank);
			
			return (int) (compareRank - this.rank);
		}
		
		public final static Comparator<Movie> movieRank_comparator = new Comparator<Movie>() {
			public int compare(Movie mr1, Movie mr2) {
				return mr1.compareTo(mr2);  
			}
		};
		
	}
	public static void main(String[] args) {
		System.out.println("Add Movie dataset.. ");
		Movie m1 = new Movie("Bamby", 4.5);
		Movie m2 = new Movie("Batman", 5.0);
		Movie m3 = new Movie("Jurasic Park", 4.5);
		Movie m4 = new Movie("X-man", 3.4);
		System.out.println(m1.name+"\t" + m2.name +"\t"+ m3.name +"\t"+ m4.name );
		m1.addNeighbor(m2);
		m1.addNeighbor(m3);
		m2.addNeighbor(m1);
		m2.addNeighbor(m4);
		m3.addNeighbor(m1);
		m3.addNeighbor(m4);
		m4.addNeighbor(m2);
		m4.addNeighbor(m3);
		
		List<Movie> lm1= m1.getNeighbors();
		System.out.println("\tMovie: "+m1.name+"\t");
		for (Movie m: lm1) {
			System.out.print(m.name+"\t");
		}
		System.out.println();
		List<Movie> lm2= m2.getNeighbors();
		System.out.println("\tMovie: "+m2.name+"\t");
		for (Movie m: lm2) {
			System.out.print(m.name+"\t");
		}
		System.out.println();
		List<Movie> lm3= m3.getNeighbors();
		System.out.println("\tMovie: "+m3.name+"\t");
		for (Movie m: lm3) {
			System.out.print(m.name+"\t");
		}
		System.out.println();
		List<Movie> lm4= m4.getNeighbors();
		System.out.println("\tMovie: "+m4.name+"\t");
		for (Movie m: lm4) {
			System.out.print(m.name+"\t");
		}
		
		// find unique movies 
		Set<Movie> temp = new HashSet<Movie>();
		temp.addAll(m1.getNeighbors());
		temp.addAll(m2.getNeighbors());
		temp.addAll(m3.getNeighbors());
		temp.addAll(m4.getNeighbors());
		
		System.out.println("unique movies..");
		for (Movie m: temp) {
			System.out.println("("+m.name+"   "+m.rank+")");
		}
		List<Movie> sl = ListTest2.getHighRanks(temp, 2);
		
		System.out.println("ranked movies..");
		for (Movie m: sl) {
			System.out.println("("+m.name+"   "+m.rank+")");
		}
		
	}
	
	// assume connected graph
	// find unique movies with name and rank
	static List<Movie> getHighRanks(Set<Movie> mv, int n) {
		List<Movie> lm = new ArrayList<Movie>(mv);
		lm.sort(Movie.movieRank_comparator);
		return lm.subList(0, n);
	}
	
	// and then sort them in descending order
	

	
}
