package exp;

import java.util.Random;
import java.util.Scanner;

public class Clock {
	public static void main(String[] args) {
	    // TODO Auto-generated method stub

	    int size = 3;//�ڴ��Ĵ�С�����Դ�ż���ҳ�棩
	    int pagesize = 10;
	    Scanner in = new Scanner(System.in);


	    System.out.println("�������ڴ��Ĵ�С��");
	    size = in.nextInt();
	    block[] arr = new block[size];//����һ������size��ҳ����ڴ��
	    for(int i = 0;i < size;i++){
	        arr[i] = new block();
	    }


	    System.out.println("���������ҳ�������");
	    pagesize = in.nextInt();
	    page[] pages = new page[pagesize];//�洢Ҫ���ʵ�ҳ�棬

	    System.out.println("��ѡ�����(������)��");
	    System.out.println("1��ҳ���������(0~7)���Ƿ��޸�(0,1)������Ĭ�ϵ�");
	    System.out.println("2����̬����ҳ��������У��Ƿ��޸Ĳ���Ĭ��ֵ");
	    System.out.println("3����̬����ҳ��������к��Ƿ��޸�");
	    int action = in.nextInt();

	    Random random = new Random();
	    if(action == 1){
	        for(int i = 0;i < pagesize;i++){
	            pages[i] = new page(random.nextInt(8),random.nextInt(10)%2);//ģ��ҳ��Ϊ1��ҳ�治�ᱻ�޸�
	        }
	    }else if(action == 2){

	        for(int i = 0;i < pagesize;i++){
	            System.out.println("�������" + i + "��ҳ��");
	            pages[i] = new page(in.nextInt(),random.nextInt(10)%2);//ģ��ҳ��Ϊ1��ҳ�治�ᱻ�޸�
	        }
	    }else if(action ==3){
	        for(int i = 0;i < pagesize;i++){
	            System.out.println("�������" + i + "��ҳ�ź��Ƿ񱻻ᱻ�޸�");
	            pages[i] = new page(in.nextInt(),in.nextInt());//ģ��ҳ��Ϊ1��ҳ�治�ᱻ�޸�
	        }
	    }

	    System.out.println("ҳ���\t������޸�");
	    for (page page : pages) {
	        System.out.println(page.page+"\t"+page.modify);
	    }

	    //�Ľ���ҳ���û��㷨
	    clock(arr,pages);


	}

	//�Ľ���clock�㷨
	public static void clock(block[] block,page[] page){

	    //���ڱ�ʾ��ǰҳ���Ƿ�װ���ڴ�ɹ�
	    boolean flag = false;

	    //ȱҳ��
	    int count = 0;

	    //ģ�����ҳ�������
	    for(int i = 0;i < page.length;i++){

	        for (int m = 0;m < block.length;m++) {
	            System.out.println("ҳ��ţ�" + block[m].page +";  ʹ��λ��" + block[m].access + ";  �޸�λ��"+block[m].modify);
	        }

	        System.out.println("��Ҫ���ʵ�ҳ��"+page[i].page);
	        if(contain(block,page[i])){
	            //�������ڴ���У��򲻻����ȱҳ���󣬼���ִ����㣬
	            System.out.println("���У�");
	            for (int m = 0;m < block.length;m++) {
	                System.out.println("ҳ��ţ�" + block[m].page +";  ʹ��λ��" + block[m].access + ";  �޸�λ��"+block[m].modify);
	            }
	            System.out.println("***********�ָ���************************");
	            continue;
	        }else{
	            System.out.println("ȱҳ...");
	            count++;//����ڴ���в��������ҳ�������һ��ȱҳ����Ѱ�Һ��ʵ��û�ҳ��
	            flag = false;
	        }

	        //ҳ��û�гɹ�װ���ڴ��ʱ��
	        while(!flag){

	            //��һ����Ѱ���ڴ���У�ʹ��λ���޸�λ��Ϊ1���ڴ�飬Ȼ������û�
	            for(int j = 0;j < block.length;j++){
	                if(block[j].access == 0 && block[j].modify == 0){
	                    //�ҵ��˿����û���ҳ�棬�����û��������޸�ʹ��λ
	                    block[j].page = page[i].page;
	                    block[j].access = 1;
	                    flag = true;
	                    break;
	                }
	            }

	            if(flag){
	                //�ɹ�װ���ڴ�,��ֱ������ѭ��������ִ�У�����ִ�еڶ���
	                break;
	            }

	            //�ڶ�����Ѱ��ʹ��λΪ0���޸�λΪ1��ҳ������û��������ڱ����ڴ��Ĺ����У��ѱ�������ҳ���ʹ��λ��Ϊ0��
	            for(int j = 0;j < block.length;j++){

	                if(block[j].access == 0 && block[j].modify == 1){
	                    //�ҵ��˿����û���ҳ�棬�����û��������޸�ʹ��λ
	                    block[j].page = page[i].page;
	                    block[j].access = 1;
	                    flag = true;
	                    break;
	                }else{
	                    block[j].access = 0;//�޸�ʹ��λΪ0
	                }
	            }
	        }

	        for (int m = 0;m < block.length;m++) {
	            System.out.println("ҳ��ţ�" + block[m].page +";  ʹ��λ��" + block[m].access + ";  �޸�λ��"+block[m].modify);
	        }
	        System.out.println("******************************************");
	    }

	    System.out.println("ȱҳ������" + count);
	    System.out.println("ȱҳ�ʣ�" + (count*1.0)/page.length);
	}


	//�ж�pageҳ���Ƿ������block�ڴ���У�������ڣ�����ʹ��λ��Ϊ1
	public static boolean contain(block[] block,page page){

	    for(int i = 0;i < block.length;i++){
	        //���ҳ��������ڴ���У����޸�ʹ��λ���޸�λ
	        if(block[i].page == page.page){
	            block[i].page = page.page;
	            block[i].access = 1;
	            block[i].modify = page.modify;
	            return true;
	        }
	    }
	    return false;
	}

	}

	//�ڴ�ռ��࣬���ڱ�ʾһ���ڴ�ռ䵥Ԫ
	class block{
	int page = -1;//ҳ��ţ���ʾ�洢�ڸ��ڴ�ҳ��ҳ�ţ�Ĭ��Ϊ-1

	int access = 0;//ʹ�ñ�־λ��Ĭ��ֵΪ0����ʾδʹ��
	int modify = 0;//�޸ı�־λ��Ĭ��ֵΪ0����ʾδ�޸�
	}

	//ҳ���࣬��ʾҪ���ʵ�ҳ��
	class page{
	public page(int page,int modify){
	    this.page = page;
	    this.modify = modify;
	}
	int page = -1;//��ʾ��ҳ�������ҳ��
	int modify = 0;//ģ���ҳ�汻װ���ڴ���ʱ���Ƿ��޸ģ�0��ʾ���޸ģ�1��ʾ�޸ģ�Ĭ��ֵΪ0

}
