package exp;

public class Process {
	String name;			//������
	int arriveTime;			//����ʱ��
	int serveTime;			//����ʱ��
	int beginTime;			//��ʼʱ��
	int finishTime;		//����ʱ��
	int roundTime;			//��תʱ��
	double aveRoundTime;	//��Ȩ��תʱ��
	double clock=0;					//��ʱ����ת�����㷨�У���¼�ý�����ʵ����ʱ���Ѿ���ʱ��ʱ��
	int waitTime;					//��¼ÿ�����̵����ĵȴ�ʱ�䣬ֻ���������Ӧ�����ȵ����㷨��
	boolean firstTimeTag=false;		//��RR�㷨�б�ʶ��ʼʱ���Ƿ��һ�μ���

	
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
		String info = new String("�������� P" + this.name);
		return info;
	}
}
