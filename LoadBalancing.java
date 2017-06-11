import java.util.*;

public class LoadBalancing {
	public void balanceMachines(int machineCount, int[][] jobs){
		Arrays.sort(jobs, (a, b) -> -Integer.compare(a[1], b[1]));	//Sort by  longest procession time 1st

		ArrayList<ArrayList<Integer>> jobsForMachine = new ArrayList<ArrayList<Integer>>();
		PriorityQueue<Machine> loads = new PriorityQueue<Machine>();
		for(int i=0; i<machineCount; i++){
			loads.add(new Machine(i));
			jobsForMachine.add(new ArrayList<Integer>());
		}
		
		for(int j=0; j<jobs.length; j++){
			Machine smallestLoadMachine = loads.remove();
			int i=smallestLoadMachine.getId();
			jobsForMachine.get(i).add(jobs[j][0]);
			smallestLoadMachine.setCurrentLoad(smallestLoadMachine.getCurrentLoad() + jobs[j][1]);
			loads.add(smallestLoadMachine);
		}
		
		System.out.println("Jobs Assignments:");
		for(int i=0;  !loads.isEmpty();  i++){
			Machine machine = loads.remove();
			System.out.print("Machine "+machine.getId()+": ");
			ArrayList<Integer> assignedJobs = jobsForMachine.get(i);
			for(int k=0; k<assignedJobs.size(); k++){
				System.out.print("\nJob "+assignedJobs.get(k)+": (Processing time="+jobs[k][1]+")");
			}
			System.out.println("\n");
		}
		
	}

	class Machine implements Comparable<Machine>{
		private int id;
		private int currentLoad;

		public Machine(int id){
			this.id=id;
			currentLoad=0;
		}
		
		public int getId(){
			return id;
		}
		
		public int getCurrentLoad(){
			return currentLoad;
		}		
		public void setCurrentLoad(int load){
			currentLoad = load;
		}

		@Override
		public int compareTo(Machine otherMachine) {
			return getCurrentLoad() - otherMachine.getCurrentLoad();
		}
		
		@Override
		public String toString(){
			return "Machine "+id+": Current Load="+currentLoad;
		}
	}


	public static void main(String[] args) {
		LoadBalancing loadBalancer = new LoadBalancing();
		int machineCount1 = 4;
		int[][] jobs1 = {
						{1, 2},
						{2, 2},
						{3, 2},
						{4, 2},
						{5, 3},
						{6, 1},
						{7, 4}
						};
		loadBalancer.balanceMachines(machineCount1, jobs1);
	}

}