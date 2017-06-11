import java.util.*;

public class LoadBalancing {
	
	
	public void balanceMachines(int machineCount, int[][] jobs){
		System.out.println(1);
	}

	public static void main(String[] args) {
		LoadBalancing loadBalancer = new LoadBalancing();
		int[][] jobs1 = {
						{1, 2},
						{2, 2},
						{3, 2},
						{4, 2},
						{5, 3},
						{6, 1},
						{7, 4}
						};
		loadBalancer.balanceMachines(4, jobs1);
		
		ArrayList arrlist = new ArrayList();
		Collections.addAll(arrlist, "1","2","3");
		System.out.println("Final collection value: "+arrlist);
	}

}