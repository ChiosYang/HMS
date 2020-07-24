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
		System.out.println("�������м�����Դ");
		resNum = sc.nextInt();
		System.out.println("�������߳�����");
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
		System.out.println("������"+resNum+"����Դ����Ŀ");
		for(int i=0;i<resNum;i++) {
			available[i] = sc.nextInt();
		}
		for(int i=0;i<thrNum;i++) {
			System.out.println("���������"+i+"��"+resNum+"����Դ����Ҫ�������Դ��Ŀ");
			for(int j=0;j<resNum;j++) {
				max[i][j] = sc.nextInt();
			}
		}
		for(int i=0;i<thrNum;i++) {
			System.out.println("���������"+i+"�Ѿ����䵽��"+resNum+"����Դ��");
			for(int j=0;j<resNum;j++) {
				allocation[i][j] = sc.nextInt();
			}
		}
		//������������������Դ��
		for(int i=0;i<thrNum;i++) {
			for(int j=0;j<resNum;j++) {
				need[i][j] = max[i][j] - allocation[i][j];
			}
		}
		//���¼���available
		for(int i=0;i<resNum;i++) {
			for(int j=0;j<thrNum;j++) {
				available[i] -= allocation[j][i];
			}
		}
		
		
	}
	//�û�����Ҫ������Դ���̣߳����ж�
	public void getThread() {
		System.out.println("������ҪҪ������߳�");
		int thread = sc.nextInt();
		if(thread < 0 || thread > 4) {
			System.out.println("�����ڸ��̣߳�����������");
			getThread();
		}else {
			this.thread = thread;
			System.out.println("������Ҫ�����"+resNum+"����Դ����ĳ����Դ���������� 0 ��");
			for(int i = 0;i<resNum;i++) {
				request[i] = sc.nextInt();
			}
			for(int i=0;i<resNum;i++) {
				if(request[i] > need[thread][i]) {
					System.out.println("���������Դ�������������Դ������������");
					getThread();
				}
			}
			for(int i=0;i<resNum;i++) {
				if(request[i] > available[i]) {
					System.out.println("���������Դ����ʣ�����Դ������������");
					getThread();
				}
			}	
			changData(thread);
			if(check(thread)){
				System.out.println();
				System.out.println("��Դ�Ѿ�������ɣ��Ƿ�������䣿");
				System.out.println("Y �ǵģ�����                                    N ����Ҫ");
				String isGo = sc.next();
				if(isGo.equals("Y") || isGo.equals("y")) {
					getThread();
				}else {
					System.out.println("лл���ټ�");
				}
				 
			}else{
				System.out.println("��Դ����ʧ�ܣ�������ѡ���߳�");
				recoverData(thread);
				getThread();
			}
		}
		
	}
	//���Է�����Դ
	public void changData(int thread) {
		for(int i=0;i<resNum;i++) {
			//����ʣ����Դ
			available[i] -= request[i];
			//�����ѷ�����Դ
			allocation[thread][i] += request[i];
			//����������Դ
			need[thread][i] -= request[i];
		}
	}
	//����ʧ�ܺ󣬻���
	public void recoverData(int thread) {
		for(int i=0;i<resNum;i++) {
			//����ʣ����Դ
			available[i] += request[i];
			//�����ѷ�����Դ
			allocation[thread][i] -= request[i];
			//����������Դ
			need[thread][i] += request[i];
		}
	}
	
	public boolean check(int thread) {
		int queue[] = new int[thrNum];
		int j;
		int i;
		int k = 0;  //��ȫ�����±�
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
						j=0;//��ʼ�ж�
					
				}
			}
		}
		//�ж��Ƿ����ڰ�ȫ״̬
		for(int a=0;a<thrNum;a++) {
			if(finish[a] = false) {
				System.out.println("ϵͳ����ȫ����Դ����ʧ��");
				return false;
			}
		}
		//˳��ͨ��
		System.out.println("������Դ�ɹ�����ȫ����Ϊ");
		for(int a=0;a<thrNum;a++) {
			System.out.print(queue[a] + "     ");
		}
		return true;
		
	}
	//���available��need
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
		// TODO �Զ����ɵķ������
		Banker asd = new Banker();
		asd.start();
		asd.ji();
		Banker t = new Banker(resNum,thrNum);
		t.getData();
		t.getThread();
		//asd.showData();

	}

}
