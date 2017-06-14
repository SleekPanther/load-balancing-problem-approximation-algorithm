import java.util.*;

public class LoadBalancing {
	public void balanceMachines(int machineCount, int[][] jobs){
		Arrays.sort(jobs, (a, b) -> -Integer.compare(a[1], b[1]));	//Sort by longest processing time 1st

		PriorityQueue<Machine> machineLoads = new PriorityQueue<Machine>();
		for(int i=1; i<=machineCount; i++){		//Initialize empty machines
			machineLoads.add(new Machine(i));
		}
		
		//Assign jobs to machines
		for(int j=0; j<jobs.length; j++){
			Machine smallestLoadMachine = machineLoads.remove();		//temporarily remove machine with smallest load
			int jobId=jobs[j][0];
			int jobProcessingTime = jobs[j][1];

			smallestLoadMachine.addJob(jobId, jobProcessingTime);
			smallestLoadMachine.setCurrentLoad(smallestLoadMachine.getCurrentLoad() + jobProcessingTime);	//Update machine's current load with new new job processing time

			machineLoads.add(smallestLoadMachine);		//Adding machine back does increaseKey
		}
		
		//Display Job assignments & find makespan
		int makespan = machineLoads.peek().getCurrentLoad();
		int makespanMachineId = machineLoads.peek().getId();
		System.out.println("Jobs Assignments:");
		while(!machineLoads.isEmpty()){
			Machine machine = machineLoads.remove();
			int machineLoad = machine.getCurrentLoad();
			System.out.println("Machine "+machine.getId()+"   (Load="+machineLoad+"):"  +"\nJobs in Order Assigned");
			if(machineLoad>makespan){	//Update makespan if a larger number is found
				makespan=machineLoad;
				makespanMachineId=machine.getId();
			}
			//Print the jobs assigned to the machine
			ArrayList<ArrayList<Integer>> assignedJobs = machine.getJobs();
			for(ArrayList<Integer> job : assignedJobs){
				System.out.println("Job "+job.get(0)+" (Processing Time="+job.get(1)+")");
			}
			System.out.println();
		}
		System.out.println("Makespan="+makespan+" (From Machine "+makespanMachineId+")\n\n");
		
	}

	class Machine implements Comparable<Machine>{
		private int id;
		private int currentLoad=0;
		private ArrayList<ArrayList<Integer>> jobs = new ArrayList<ArrayList<Integer>>();

		public Machine(int id){
			this.id=id;
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

		public void addJob(int id, int processingTime){
			jobs.add(new ArrayList<Integer>(Arrays.asList(id, processingTime)));
		}

		public ArrayList<ArrayList<Integer>> getJobs(){
			return jobs;
		}

		@Override			//Override to allow Priority Queue functionality
		public int compareTo(Machine otherMachine) {
			return getCurrentLoad() - otherMachine.getCurrentLoad();
		}

		@Override
		public String toString(){
			return "Machine "+getId()+": Current load="+getCurrentLoad()+"  Jobs="+jobs;
		}
	}


	public static void main(String[] args) {
		LoadBalancing loadBalancer = new LoadBalancing();
		int machineCount1 = 3;
		int[][] jobs1 = {
						{1, 2},
						{2, 2},
						{3, 2},
						{4, 2},
						{5, 5}
						};
		loadBalancer.balanceMachines(machineCount1, jobs1);

		int machineCount2 = 3;
		int[][] jobs2 = {
						{1, 21},
						{2, 3},
						{3, 38},
						{4, 42},
						{5, 19}
						};
		loadBalancer.balanceMachines(machineCount2, jobs2);

		int machineCount3 = 3;
		int[][] jobs3 = {
						{1, 2},
						{2, 2},
						{3, 6},
						{4, 3},
						{5, 4},
						{6, 2}
						};
		loadBalancer.balanceMachines(machineCount3, jobs3);
	}
}