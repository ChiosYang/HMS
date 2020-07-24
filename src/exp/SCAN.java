package exp;

import java.util.Scanner;

public class SCAN {
	private int visit[];
	private int nearIndex=0;
	public int[] scan(int queue[],int start,int direction){
		int nearNum=9999;
		int index=0;
		visit=new int[queue.length];
		for(int i=0;i<queue.length;i++){
			index=-1;
			for(int j=0;j<queue.length;j++){
				if(queue[j]!=-1){
					if((direction==1)&&(queue[j]>start)&&(Math.abs(nearNum-start)>Math.abs(queue[j]-start))){
						nearNum=queue[j];
						nearIndex=j;
						index=0;
					}
					else if((direction==0)&&(queue[j]<start)&&(Math.abs(nearNum-start)>Math.abs(queue[j]-start))){
						nearNum=queue[j];
						nearIndex=j;
						index=0;
					}
				}
			}
			if((direction==1)&&(index==-1)){
				direction=0;
				i=i-1;
			}
			else if((direction==0)&&(index==-1)){
				direction=1;
				i=i-1;
			}
			if(index==0){
				visit[i]=nearNum;
				queue[nearIndex]=-1;
				start=nearNum;
				nearNum=9999;
			}
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
		System.out.println("***************************************************");
		System.out.println("******      *****       *******  ******    ****  **");
		System.out.println("******  *********  ********** ***  ****  **  **  **");
		System.out.println("******      *****  ********  *****  ***  **  **  **");
		System.out.println("**********  *****  ********         ***  ***  *  **");
		System.out.println("******      *****       ***  *****  ***  ****    **");
		System.out.println("***************************************************");
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入磁盘请求序列长度：");
		int a=sc.nextInt();
		System.out.println("请输入磁盘请求访问序列：");
		int[] queue=new int[a];
		for(int i=0;i<a;i++){
			queue[i]=sc.nextInt();
		}
		SCAN scan=new SCAN();
		System.out.println("请输入读写头起始位置：");
		int start=sc.nextInt();
		System.out.println("磁道增加的方向：（0向磁道号减少的方向移动,1向磁道号增加的方向移动）");
		int direction=sc.nextInt();
		scan.print(scan.scan(queue, start,direction),start);
	}

}
