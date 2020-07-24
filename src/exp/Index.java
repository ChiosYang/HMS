package exp;

import java.util.Scanner;

public class Index {
	public static void main(String[] args) {
		System.out.println("****************************************");
		int f_size;//文件的大小
		int b_size;//每个盘块的大小
		int bnumber_size;//每个盘快号所占空间的大小
		int p;//偏移量
		int b_number;//文件所占块数
		int index0,index1,index2,index3;//直接索引，一级索引，二级索引，三级索引
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入文件的大小以字节为单位：");
		f_size=sc.nextInt();
		System.out.println("请输入每个盘块的大小以字节为单位：");
		b_size=sc.nextInt();
		System.out.println("请输入每个盘块号的大小以字节为单位：");
		bnumber_size=sc.nextInt();
		index0=10*b_size;
		index1=10*b_size+b_size*(b_size/bnumber_size);
		index2=10*b_size+b_size*(b_size/bnumber_size)+b_size*(b_size/bnumber_size)*(b_size/bnumber_size);
		index3=10*b_size+b_size*(b_size/bnumber_size)+b_size*(b_size/bnumber_size)*(b_size/bnumber_size)+b_size*(b_size/bnumber_size)*(b_size/bnumber_size)*(b_size/bnumber_size);
		
		if(f_size<=0) {
			System.out.println("所输入的文件的大小不合法！！！！！！");
		}
		
		else if(f_size>0&&f_size<=index0) {
			b_number=f_size/b_size;
			p=f_size%b_size;
			if(p==0) {
				System.out.println("存储该文件使用了直接索引，文件占用了直接索引的前"+b_number+"块，偏移量为0");
				System.out.println("文件占用了"+b_number+"个盘块");
			}
			else if(p>0) {
				System.out.println("存储该文件使用了直接索引，文件占用了直接索引的前"+(b_number+1)+"块，偏移量为"+p);
				System.out.println("文件占用了"+(b_number+1)+"个盘块");
			}
			
		}
		else if(f_size>index0&&f_size<=index1) {
			
			b_number=(f_size-index0)/b_size;
			p=(f_size-index0)%b_size;
			if(p==0) {
				System.out.println("存储该文件使用了一级索引，文件占用了一级直接索引的前"+b_number+"块，偏移量为0");
				System.out.println("文件占用了"+(b_number+10)+"个盘块");
			}
			
			else if(p>0) {
				System.out.println("存储该文件使用了一级索引，文件占用了一级直接索引的前"+(b_number+1)+"块，偏移量为"+p);
				System.out.println("文件占用了"+(b_number+1+10)+"个盘块");
			}
			
			
		}
		
		else if(f_size>index1&&f_size<=index2) {
			b_number=(f_size-index1)/b_size;
			p=(f_size-index1)%b_size;
			
			if(p==0) {
				System.out.println("存储该文件使用了二级索引，文件占用了二级直接索引的前"+b_number+"块，偏移量为0");
				System.out.println("文件占用了"+(b_number+10+(b_size/bnumber_size))+"个盘块");
			}
			else if(p>0) {
				System.out.println("存储该文件使用了二级索引，文件占用了二级直接索引的前"+(b_number+1)+"块，偏移量为"+p);
				System.out.println("文件占用了"+(b_number+1+10+(b_size/bnumber_size))+"个盘块");
			}
			
		}
		
		
		else if(f_size>index2&&f_size<=index3) {
			b_number=(f_size-index2)/b_size;
			p=(f_size-index2)%b_size;
			if(p==0) {
				System.out.println("存储该文件使用了三级索引，文件占用了三级直接索引的前"+b_number+"块，偏移量为0");
				System.out.println("文件占用了"+(b_number+10+(b_size/bnumber_size)+(b_size/bnumber_size)*(b_size/bnumber_size))+"个盘块");
			}
			else if(p>0) {
				System.out.println("存储该文件使用了三级索引，文件占用了三级直接索引的前"+(b_number+1)+"块，偏移量为"+p);
				System.out.println("文件占用了"+(b_number+1+10+(b_size/bnumber_size)+(b_size/bnumber_size)*(b_size/bnumber_size))+"个盘块");
			}
		}
		
	}


}
