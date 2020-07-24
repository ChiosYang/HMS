package exp;

public class Process {
	String name;			//进程名
	int arriveTime;			//到达时间
	int serveTime;			//服务时间
	int beginTime;			//开始时间
	int finishTime;		//结束时间
	int roundTime;			//轮转时间
	double aveRoundTime;	//带权周转时间
	double clock=0;					//在时间轮转调度算法中，记录该进程真实服务时间已经用时的时长
	int waitTime;					//记录每个进程到达后的等待时间，只用于最高响应比优先调度算法中
	boolean firstTimeTag=false;		//在RR算法中标识开始时间是否第一次计算

	
	public Process() {
		
	}
	public Process(String name,int arriveTime,int serveTime,double priority) {
		super();
		this.name = name;
		this.arriveTime = arriveTime;
		this.serveTime = serveTime;
		this.waitTime = 0;
	}
	public String toString() {
		String info = new String("进程名： P" + this.name);
		return info;
	}
}
