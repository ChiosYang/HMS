package exp;

import java.util.Scanner;

public class Index {
	public static void main(String[] args) {
		System.out.println("****************************************");
		int f_size;//�ļ��Ĵ�С
		int b_size;//ÿ���̿�Ĵ�С
		int bnumber_size;//ÿ���̿����ռ�ռ�Ĵ�С
		int p;//ƫ����
		int b_number;//�ļ���ռ����
		int index0,index1,index2,index3;//ֱ��������һ��������������������������
		Scanner sc=new Scanner(System.in);
		System.out.println("�������ļ��Ĵ�С���ֽ�Ϊ��λ��");
		f_size=sc.nextInt();
		System.out.println("������ÿ���̿�Ĵ�С���ֽ�Ϊ��λ��");
		b_size=sc.nextInt();
		System.out.println("������ÿ���̿�ŵĴ�С���ֽ�Ϊ��λ��");
		bnumber_size=sc.nextInt();
		index0=10*b_size;
		index1=10*b_size+b_size*(b_size/bnumber_size);
		index2=10*b_size+b_size*(b_size/bnumber_size)+b_size*(b_size/bnumber_size)*(b_size/bnumber_size);
		index3=10*b_size+b_size*(b_size/bnumber_size)+b_size*(b_size/bnumber_size)*(b_size/bnumber_size)+b_size*(b_size/bnumber_size)*(b_size/bnumber_size)*(b_size/bnumber_size);
		
		if(f_size<=0) {
			System.out.println("��������ļ��Ĵ�С���Ϸ�������������");
		}
		
		else if(f_size>0&&f_size<=index0) {
			b_number=f_size/b_size;
			p=f_size%b_size;
			if(p==0) {
				System.out.println("�洢���ļ�ʹ����ֱ���������ļ�ռ����ֱ��������ǰ"+b_number+"�飬ƫ����Ϊ0");
				System.out.println("�ļ�ռ����"+b_number+"���̿�");
			}
			else if(p>0) {
				System.out.println("�洢���ļ�ʹ����ֱ���������ļ�ռ����ֱ��������ǰ"+(b_number+1)+"�飬ƫ����Ϊ"+p);
				System.out.println("�ļ�ռ����"+(b_number+1)+"���̿�");
			}
			
		}
		else if(f_size>index0&&f_size<=index1) {
			
			b_number=(f_size-index0)/b_size;
			p=(f_size-index0)%b_size;
			if(p==0) {
				System.out.println("�洢���ļ�ʹ����һ���������ļ�ռ����һ��ֱ��������ǰ"+b_number+"�飬ƫ����Ϊ0");
				System.out.println("�ļ�ռ����"+(b_number+10)+"���̿�");
			}
			
			else if(p>0) {
				System.out.println("�洢���ļ�ʹ����һ���������ļ�ռ����һ��ֱ��������ǰ"+(b_number+1)+"�飬ƫ����Ϊ"+p);
				System.out.println("�ļ�ռ����"+(b_number+1+10)+"���̿�");
			}
			
			
		}
		
		else if(f_size>index1&&f_size<=index2) {
			b_number=(f_size-index1)/b_size;
			p=(f_size-index1)%b_size;
			
			if(p==0) {
				System.out.println("�洢���ļ�ʹ���˶����������ļ�ռ���˶���ֱ��������ǰ"+b_number+"�飬ƫ����Ϊ0");
				System.out.println("�ļ�ռ����"+(b_number+10+(b_size/bnumber_size))+"���̿�");
			}
			else if(p>0) {
				System.out.println("�洢���ļ�ʹ���˶����������ļ�ռ���˶���ֱ��������ǰ"+(b_number+1)+"�飬ƫ����Ϊ"+p);
				System.out.println("�ļ�ռ����"+(b_number+1+10+(b_size/bnumber_size))+"���̿�");
			}
			
		}
		
		
		else if(f_size>index2&&f_size<=index3) {
			b_number=(f_size-index2)/b_size;
			p=(f_size-index2)%b_size;
			if(p==0) {
				System.out.println("�洢���ļ�ʹ���������������ļ�ռ��������ֱ��������ǰ"+b_number+"�飬ƫ����Ϊ0");
				System.out.println("�ļ�ռ����"+(b_number+10+(b_size/bnumber_size)+(b_size/bnumber_size)*(b_size/bnumber_size))+"���̿�");
			}
			else if(p>0) {
				System.out.println("�洢���ļ�ʹ���������������ļ�ռ��������ֱ��������ǰ"+(b_number+1)+"�飬ƫ����Ϊ"+p);
				System.out.println("�ļ�ռ����"+(b_number+1+10+(b_size/bnumber_size)+(b_size/bnumber_size)*(b_size/bnumber_size))+"���̿�");
			}
		}
		
	}


}
