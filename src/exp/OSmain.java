package exp;

import java.util.Scanner;

public class OSmain {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		System.out.println("请输入您要执行的操作：");
		Scanner sc = new Scanner(System.in);
		switch(sc.nextInt()) {
		case 1:
			new SSTF();
		case 2:
			new SCAN();
		}
		
			
	}

}
