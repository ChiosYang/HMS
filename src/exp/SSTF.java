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
		System.out.print("�������У�");
		for(int i=0;i<visit.length;i++){
			System.out.print(visit[i]+" ");
			sum=Math.abs(visit[i]-start)+sum;
			start=visit[i];
		}
		System.out.println();
		System.out.println("�����Ĵŵ�������"+sum);
		System.out.println("ƽ��Ѱ�����ȣ�"+sum/visit.length);
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
		System.out.println("����������������г��ȣ�");
		int a=sc.nextInt();
		System.out.println("�������������������У�");
		int[] queue=new int[a];
		for(int i=0;i<a;i++){
			queue[i]=sc.nextInt();
		}
		SSTF sstf=new SSTF();
		System.out.println("�������дͷ��ʼλ�ã�");
		int start=sc.nextInt();
		sstf.print(sstf.sstf(queue, start),start);
	}


}
