package exp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Collections;

public class ProcessMenu {
	ArrayList<Process> jcb;// ������н���
	LinkedList<Process> link;// ����Ѿ�������еĽ���
	ArrayList<Process> new_jcb;// ��Ű�ָ�������㷨�����Ľ���
	Process nowProcess;// ��ǰӦִ�н���
	public void init() {//��ʼ��
		jcb = new ArrayList<Process>();
		link = new LinkedList<Process>();
		new_jcb = new ArrayList<Process>();
		Process p1 = new Process("1", 0, 4,3);
		Process p2 = new Process("2", 1, 3,2);
		Process p3 = new Process("3", 2, 5,3);
		Process p4 = new Process("4", 3, 2,1);
		Process p5 = new Process("5", 4, 4,5);
		jcb.add(p1);jcb.add(p2);jcb.add(p3);jcb.add(p4);jcb.add(p5);
		//�Ƚ�Process���򣬱���������㷨ʵ�֣��Ͳ���Ҫ�ٶ���һ����ʶ�����Ƿ��ѵ����boolean,������ÿ�ζ���ͷ��ʼɨ��Process������
		//������һ��K��¼�µ�ǰ�Ѿ�ɨ�赽��λ�ã�һ�α������ɣ�������㷨Ч�ʡ�
		Collections.sort(jcb, new compareAt_St());
	}
	/*
	 * �����ȷ���
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
	 * ����ҵ����
	 */
	public void SJF() {
		ProcessQueue pq=new ProcessQueue();
		pq.EnqueueLast();
		System.out.println("*****************************************************");
		while(!link.isEmpty()) {
			pq.DisplayQueue();//��ӡ��ǰ�����еĽ���
			pq.Dequeue();//���ӣ�һ��һ��
			pq.EnqueueLast();//�ѵ���Ľ������
			Collections.sort(link, new compareSt());//�����еĽ��̻��谴����ʱ�䳤�Ƚ�������
		}

	}
	/*
	 * 
	 */
	public static void main(String[] args) {
		ProcessMenu pm=new ProcessMenu();
		pm.init();//��ʼ������
		pm.FCFS();pm.printProcess();
		pm.SJF();pm.printProcess();
	//	pm.RR();pm.printProcess();
	//	pm.HRN();pm.printProcess();
	}


	class ProcessQueue{
		int k=0;			//�±�
		int nowTime = 0;   //��ǰʱ��
		double sliceTime;	//��תʱ��Ƭ
		int i=0; //��¼��ǰ������еĴ���
		public void EnqueueLast() { //���̴Ӷ�β����
			while (k < jcb.size()) {//��������jcb�е����н���ʱ����
				if (jcb.get(k).arriveTime <= nowTime) {//�Ѿ�����Ľ��̰�����ʱ���Ⱥ�������
					link.addLast(jcb.get(k));
					k++;
				} else {
					break;//����ý��̻�δ��ӣ����Ƚ���������������ǰ�±�kֵ��ע�⣺�˴���Ҫk--��
				}
			}
			
		}
		public void EnqeueFirst() {	//���̴Ӷ��׽���
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
			nowProcess.beginTime = nowTime;//���㿪ʼʱ�䣬��Ϊ��һ�����̵Ľ���ʱ��
			nowProcess.finishTime = nowProcess.beginTime + nowProcess.serveTime;//�������ʱ�䣬�ý��̿�ʼʱ��+����ʱ��
			nowProcess.roundTime = nowProcess.finishTime - nowProcess.arriveTime;//������תʱ��
			nowProcess.aveRoundTime = (double) nowProcess.roundTime / nowProcess.serveTime;//����ƽ����תʱ��
			nowTime = nowProcess.finishTime;//��ý���ʱ�䣬����ǰʱ�䣬�����ж�ʣ�µĽ����Ƿ��ѵ���
			new_jcb.add(nowProcess);//����������ݺ����new_jcb����
			for(int i=0;i<link.size();++i) {
				link.get(i).waitTime++;//���н���ȴ����еĽ��̵ȴ�ʱ��+1,�˴�ֻΪ�����Ӧ���㷨����
			}
		}
		public void Dequeue(double sliceTime) {
			
		}
		public void DisplayQueue() {
			i++;
			System.out.println("��"+i+"�ζ������ŶӵĽ��̣�"+link);
		}
}	
	public void printProcess() {
		System.out.println("������ ����ʱ��  ����ʱ��   ��ʼʱ��  ���ʱ��  ��תʱ��  ��Ȩ��תʱ��");
		for (int i = 0; i < new_jcb.size(); ++i) {
			System.out.println("P"+new_jcb.get(i).name + "   " + new_jcb.get(i).arriveTime + "      " +
		new_jcb.get(i).serveTime+ "     " + new_jcb.get(i).beginTime + "     " + new_jcb.get(i).finishTime +
		"     "+ new_jcb.get(i).roundTime + "    " + new_jcb.get(i).aveRoundTime);
		}
		new_jcb.clear();//���new_jcb�����ڵ����ݣ�����洢�����㷨�Ľ����չʾ

	}
}
class compareSt implements Comparator<Process> {// ������ʱ������
	public int compare(Process arg0, Process  arg1) {
		return arg0.serveTime - arg1.serveTime;
	}
}
 
class compareAt_St implements Comparator<Process > {// ������ʱ������������ʱ����ͬ��������ʱ������
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
class comparePriority implements Comparator<Process>{//����Ӧ����������
 
	public int compare(Process o1, Process o2) {
		double r1=(double)o1.waitTime/o1.serveTime;
		double r2=(double)o2.waitTime/o2.serveTime;
		return r1>r2?1:-1;
	}
	
}

