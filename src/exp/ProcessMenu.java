package exp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Collections;

public class ProcessMenu {
	ArrayList<Process> jcb;// 存放所有进程
	LinkedList<Process> link;// 存放已经进入队列的进程
	ArrayList<Process> new_jcb;// 存放按指定调度算法排序后的进程
	Process nowProcess;// 当前应执行进程
	public void init() {//初始化
		jcb = new ArrayList<Process>();
		link = new LinkedList<Process>();
		new_jcb = new ArrayList<Process>();
		Process p1 = new Process("1", 0, 4,3);
		Process p2 = new Process("2", 1, 3,2);
		Process p3 = new Process("3", 2, 5,3);
		Process p4 = new Process("4", 3, 2,1);
		Process p5 = new Process("5", 4, 4,5);
		jcb.add(p1);jcb.add(p2);jcb.add(p3);jcb.add(p4);jcb.add(p5);
		//先将Process排序，便于下面的算法实现，就不需要再定义一个标识进程是否已到达的boolean,即无需每次都从头开始扫描Process容器，
		//而是用一个K记录下当前已经扫描到的位置，一次遍历即可，提高了算法效率。
		Collections.sort(jcb, new compareAt_St());
	}
	/*
	 * 先来先服务
	 */

	public void FCFS() {
		ProcessQueue pq = new ProcessQueue();
		pq.EnqueueLast();
		System.out.println("***************************************************************************");
		while(!link.isEmpty()) {
			pq.DisplayQueue();
			pq.Dequeue();
			pq.EnqueueLast();
			
		}
	}
	/*
	 * 短作业优先
	 */
	public void SJF() {
		ProcessQueue pq=new ProcessQueue();
		pq.EnqueueLast();
		System.out.println("*****************************************************");
		while(!link.isEmpty()) {
			pq.DisplayQueue();//打印当前队列中的进程
			pq.Dequeue();//出队，一次一个
			pq.EnqueueLast();//已到达的进程入队
			Collections.sort(link, new compareSt());//队列中的进程还需按服务时间长度进行排序
		}

	}
	/*
	 * 
	 */
	public static void main(String[] args) {
		ProcessMenu pm=new ProcessMenu();
		pm.init();//初始化容器
		pm.FCFS();pm.printProcess();
		pm.SJF();pm.printProcess();
	//	pm.RR();pm.printProcess();
	//	pm.HRN();pm.printProcess();
	}


	class ProcessQueue{
		int k=0;			//下标
		int nowTime = 0;   //当前时间
		double sliceTime;	//轮转时间片
		int i=0; //记录当前出入队列的次数
		public void EnqueueLast() { //进程从队尾进入
			while (k < jcb.size()) {//当遍历完jcb中的所有进程时结束
				if (jcb.get(k).arriveTime <= nowTime) {//已经到达的进程按到达时间先后进入队列
					link.addLast(jcb.get(k));
					k++;
				} else {
					break;//如果该进程还未入队，即先结束遍历，保留当前下标k值，注意：此处不要k--；
				}
			}
			
		}
		public void EnqeueFirst() {	//进程从队首进入
			while( k < jcb.size()) {
				if(jcb.get(k).arriveTime < nowTime) {
					link.addFirst(jcb.get(k));
					k++;
				}else {
					break;
				}
			}
		}
		public void Dequeue() {
			nowProcess = link.removeFirst();
			nowProcess.beginTime = nowTime;//计算开始时间，即为上一个进程的结束时间
			nowProcess.finishTime = nowProcess.beginTime + nowProcess.serveTime;//计算结束时间，该进程开始时间+服务时间
			nowProcess.roundTime = nowProcess.finishTime - nowProcess.arriveTime;//计算周转时间
			nowProcess.aveRoundTime = (double) nowProcess.roundTime / nowProcess.serveTime;//计算平均周转时间
			nowTime = nowProcess.finishTime;//获得结束时间，即当前时间，方便判断剩下的进程是否已到达
			new_jcb.add(nowProcess);//经处理过数据后加入new_jcb容器
			for(int i=0;i<link.size();++i) {
				link.get(i).waitTime++;//所有进入等待队列的进程等待时间+1,此处只为最高响应比算法所用
			}
		}
		public void Dequeue(double sliceTime) {
			
		}
		public void DisplayQueue() {
			i++;
			System.out.println("第"+i+"次队列中排队的进程："+link);
		}
}	
	public void printProcess() {
		System.out.println("进程名 到达时间  服务时间   开始时间  完成时间  周转时间  带权周转时间");
		for (int i = 0; i < new_jcb.size(); ++i) {
			System.out.println("P"+new_jcb.get(i).name + "   " + new_jcb.get(i).arriveTime + "      " +
		new_jcb.get(i).serveTime+ "     " + new_jcb.get(i).beginTime + "     " + new_jcb.get(i).finishTime +
		"     "+ new_jcb.get(i).roundTime + "    " + new_jcb.get(i).aveRoundTime);
		}
		new_jcb.clear();//清空new_jcb容器内的内容，方便存储各种算法的结果并展示

	}
}
class compareSt implements Comparator<Process> {// 按服务时间升序
	public int compare(Process arg0, Process  arg1) {
		return arg0.serveTime - arg1.serveTime;
	}
}
 
class compareAt_St implements Comparator<Process > {// 按到达时间升序，若到达时间相同，按服务时间升序
	public int compare(Process o1, Process  o2) {
		int a = o1.arriveTime - o2.arriveTime;
		if (a > 0)
			return 1;
		else if (a == 0) {
			return o1.serveTime > o2.serveTime ? 1 : -1;
		} else
			return -1;
	}
}
class comparePriority implements Comparator<Process>{//按响应比升序排序
 
	public int compare(Process o1, Process o2) {
		double r1=(double)o1.waitTime/o1.serveTime;
		double r2=(double)o2.waitTime/o2.serveTime;
		return r1>r2?1:-1;
	}
	
}

