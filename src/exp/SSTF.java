package exp;

import java.util.Scanner;

public class SSTF {
	
	private int visit[];
	private int nearIndex=0;
	public int[] sstf(int queue[],int start){
		int nearNum=9999;
		visit=new int[queue.length];
		for(int i=0;i<queue.length;i++){
			for(int j=0;j<queue.length;j++){
				if(queue[j]!=-1){
					if(Math.abs(nearNum-start)>Math.abs(queue[j]-start)){
						nearNum=queue[j];
						nearIndex=j;
					}
				}
			}
			visit[i]=nearNum;
			queue[nearIndex]=-1;
			start=nearNum;
			nearNum=9999;
		}
	return visit;
	}	
	public void print(int visit[],int start){
		double sum=0;
		System.out.print("访问序列：");
		for(int i=0;i<visit.length;i++){
			System.out.print(visit[i]+" ");
			sum=Math.abs(visit[i]-start)+sum;
			start=visit[i];
		}
		System.out.println();
		System.out.println("经过的磁道总数："+sum);
		System.out.println("平均寻道长度："+sum/visit.length);
	}
 
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.println("***************************************************");
		System.out.println("******      *****       ****        ***       *****");
		System.out.println("******  *********  ************  ******  **********");
		System.out.println("******      *****       *******  ******       *****");
		System.out.println("**********  **********  *******  ******  **********");
		System.out.println("******      *****       *******  ******  **********");
		System.out.println("***************************************************");
		System.out.println("请输入磁盘请求序列长度：");
		int a=sc.nextInt();
		System.out.println("请输入磁盘请求访问序列：");
		int[] queue=new int[a];
		for(int i=0;i<a;i++){
			queue[i]=sc.nextInt();
		}
		SSTF sstf=new SSTF();
		System.out.println("请输入读写头起始位置：");
		int start=sc.nextInt();
		sstf.print(sstf.sstf(queue, start),start);
	}


}
