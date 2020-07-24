package exp;

import java.util.HashMap;
import java.util.Scanner;

public class LRU<k,v> {
	private int curentSize; //��ǰ�ĳ���
	private int capcity;	//������
	private HashMap<k,Node> caches;		//���нڵ�
	private Node last;
	private Node first;

	public LRU(int size) {
		curentSize =0;
		this.capcity = size;
		caches = new HashMap<k,Node>(size);
	}
	/*
	 * *���Ԫ��
	 */
	public void put(k key,v value) {
		Node node = caches.get(key);
        //�������Ԫ��
        if (node == null) {
            //�������Ԫ��������
            if (caches.size() >= capcity) {
                //�Ƴ����һ���ڵ�
                caches.remove(last.key);
                removeLast();
            }
            //�����½ڵ�
            node = new Node(key,value);
        }
        //�Ѿ����ڵ�Ԫ�ظ��Ǿ�ֵ
        node.value = value;
        //��Ԫ���ƶ����ײ�
        moveToHead(node);
        caches.put(key, node);
	}
	/**
     * �ѵ�ǰ�ڵ��ƶ����ײ�
     * @param node
     */
    private void moveToHead(Node node) {
    	//����ڵ������ײ����������ƶ�
        if (first == node) {
            return;
        }
        //�ڵ�Ҫ�ƶ���ͷ��������ԭ�����ڵ�λ��ȥ��
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        //���ڵ������һ������last����ǰһ���ڵ�
        if (node == last) {
            last = last.pre;
        }
        //��Ϊ�����ڽڵ�������
        if (first == null || last == null) { 
            first = last = node;
            return;
        }
        node.next = first;
        first.pre = node;
        first = node;
        first.pre = null;
    }
 
    /**
     * *�Ƴ����һ���ڵ�
     */
    private void removeLast() {
        if (last != null) {
            last = last.pre;
            //����ʱlastΪnull
            //��˵���Ѿ������ڽڵ�
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }
    /*
     * ͨ��key����ȡԪ��
     * ������LRU�㷨������·���Ԫ�ص�ʱ��
     */
    public Object get(k key) {
        Node node = caches.get(key);
        if (node == null) {
            return null;
        }
        //�ѷ��ʵĽڵ��ƶ����ײ�
        moveToHead(node);
        return node.value;
    }
    /**
     * ����key�Ƴ��ڵ�
     * @param key
     * @return
     */
    public Object remove(k key) {
        Node node = caches.get(key);
        if (node != null) {
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (node == first) {
                first = node.next;
            }
            if (node == last) {
                last = node.pre;
            }
        }
        return caches.remove(key);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = first;
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.next;
        }
        return sb.toString();
    }
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		int i;
		Scanner sc = new Scanner(System.in);
		System.out.println("����������󳤶�");
		i = sc.nextInt();
		LRU<Integer, String> lru = new LRU<Integer, String>(i);
		for(int a=0;a<99;a++) {
			System.out.println("������Ҫִ�еĲ�����");
			
		switch(sc.nextInt()) {
		case 1:
			System.out.println("����ִ���������");
			for(int j=0;j<i;j++) {
				System.out.println("������ڵ�"+j+"��ֵ");
				lru.put(j, sc.next());
			}
			System.out.println("����ɲ���");
			System.out.println("��ǰ����Ϊ��");
			System.out.println("ԭʼ����Ϊ:"+lru.toString());
			break;
		case 2:
			System.out.println("����ִ�л�ȡ����");
			System.out.println("������Ҫ��ȡԪ��ֵ��λ��");
			int o = sc.nextInt();
			lru.get(o);
			System.out.println("��ȡkeyΪ"+o+"��Ԫ��֮�������:"+lru.toString());
		case 3:
			System.out.println("����ִ��ɾ������");
			System.out.println("������Ҫɾ��Ԫ��ֵ��λ��");
			int ui = sc.nextInt();
			lru.remove(ui);
	        System.out.println("�Ƴ�key="+ui+"��֮�������:"+lru.toString());
			
		}
		}
		
	}
}
