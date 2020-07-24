package exp;

import java.util.Scanner;

public class Banker {
	
	int max[][];
	int available[] ;
	int allocation[][];
	int request[];
	int need[][];
	int work[] ;
	boolean finish[] ;
	int thread;
	Scanner sc = new Scanner(System.in);
	static int resNum;
	static int thrNum;
	public void start() {
		System.out.println("请输入有几种资源");
		resNum = sc.nextInt();
		System.out.println("请输入线程数量");
		thrNum = sc.nextInt();
		System.out.println(resNum +"     "+ thrNum);
		
	}
	public void ji() {
		System.out.println(resNum +"     " + thrNum);
	}
	public Banker() {
		
	}
	public Banker(int resNum,int thrNum) {
		max = new int[thrNum][resNum];
		available = new int[resNum];
		allocation = new int[thrNum][resNum];
		request = new int[resNum];
		need = new int [thrNum][resNum];
		work = new int[resNum];
		finish = new boolean[thrNum];
		
	}
	public void getData() {
		System.out.println("请输入"+resNum+"类资源的数目");
		for(int i=0;i<resNum;i++) {
			available[i] = sc.nextInt();
		}
		for(int i=0;i<thrNum;i++) {
			System.out.println("请输入进程"+i+"对"+resNum+"类资源所需要的最大资源数目");
			for(int j=0;j<resNum;j++) {
				max[i][j] = sc.nextInt();
			}
		}
		for(int i=0;i<thrNum;i++) {
			System.out.println("请输入进程"+i+"已经分配到的"+resNum+"类资源数");
			for(int j=0;j<resNum;j++) {
				allocation[i][j] = sc.nextInt();
			}
		}
		//计算进程所需的三类资源数
		for(int i=0;i<thrNum;i++) {
			for(int j=0;j<resNum;j++) {
				need[i][j] = max[i][j] - allocation[i][j];
			}
		}
		//重新计算available
		for(int i=0;i<resNum;i++) {
			for(int j=0;j<thrNum;j++) {
				available[i] -= allocation[j][i];
			}
		}
		
		
	}
	//用户输入要请求资源的线程，并判断
	public void getThread() {
		System.out.println("请输入要要申请的线程");
		int thread = sc.nextInt();
		if(thread < 0 || thread > 4) {
			System.out.println("不存在该线程，请重新输入");
			getThread();
		}else {
			this.thread = thread;
			System.out.println("请输入要请求的"+resNum+"种资源（若某种资源不请求，请填 0 ）");
			for(int i = 0;i<resNum;i++) {
				request[i] = sc.nextInt();
			}
			for(int i=0;i<resNum;i++) {
				if(request[i] > need[thread][i]) {
					System.out.println("所申请的资源超出其所需的资源，请重新输入");
					getThread();
				}
			}
			for(int i=0;i<resNum;i++) {
				if(request[i] > available[i]) {
					System.out.println("所申请的资源超出剩余的资源，请重新输入");
					getThread();
				}
			}	
			changData(thread);
			if(check(thread)){
				System.out.println();
				System.out.println("资源已经分配完成，是否继续分配？");
				System.out.println("Y 是的，继续                                    N 不需要");
				String isGo = sc.next();
				if(isGo.equals("Y") || isGo.equals("y")) {
					getThread();
				}else {
					System.out.println("谢谢，再见");
				}
				 
			}else{
				System.out.println("资源分配失败，请重新选择线程");
				recoverData(thread);
				getThread();
			}
		}
		
	}
	//尝试分配资源
	public void changData(int thread) {
		for(int i=0;i<resNum;i++) {
			//计算剩余资源
			available[i] -= request[i];
			//计算已分配资源
			allocation[thread][i] += request[i];
			//减少所需资源
			need[thread][i] -= request[i];
		}
	}
	//分配失败后，回溯
	public void recoverData(int thread) {
		for(int i=0;i<resNum;i++) {
			//计算剩余资源
			available[i] += request[i];
			//计算已分配资源
			allocation[thread][i] -= request[i];
			//计算所需资源
			need[thread][i] += request[i];
		}
	}
	
	public boolean check(int thread) {
		int queue[] = new int[thrNum];
		int j;
		int i;
		int k = 0;  //安全队列下标
		for(i=0;i<thrNum;i++) {
			finish[i] = false;
		}
		j = thread;
		for(i=0;i<resNum;i++) {
			work[i] = available[i];
		}
		while(j < thrNum) {
			for(i=0;i<resNum;i++) {
				if(finish[j]) {
					j++;
					break;
				}else if(need[j][i] > work[i]) {
					j++;
					break;
				}else if(i==2) {
					for(int a=0;a<resNum;a++) {
						work[a] += allocation[j][a];
					}
						finish[j] = true;
						queue[k] = j;
						k++;
						j=0;//开始判断
					
				}
			}
		}
		//判断是否都属于安全状态
		for(int a=0;a<thrNum;a++) {
			if(finish[a] = false) {
				System.out.println("系统不安全，资源申请失败");
				return false;
			}
		}
		//顺利通过
		System.out.println("申请资源成功，安全队列为");
		for(int a=0;a<thrNum;a++) {
			System.out.print(queue[a] + "     ");
		}
		return true;
		
	}
	//输出available和need
	public void showData() {
		System.out.println("need ");
		for(int i=0;i<thrNum;i++) {
			for(int j=0;j<resNum;j++) {
				System.out.print(need[i][j]+"   ");
			}
			System.out.println();
		}
		System.out.println("available  ");
		for(int i=0;i<resNum;i++) {
			System.out.println(available[i]+"  ");
		}
	}
	
	
	

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Banker asd = new Banker();
		asd.start();
		asd.ji();
		Banker t = new Banker(resNum,thrNum);
		t.getData();
		t.getThread();
		//asd.showData();

	}

}
