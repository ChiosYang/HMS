package exp;

import java.util.HashMap;
import java.util.Scanner;

public class LRU<k,v> {
	private int curentSize; //当前的长度
	private int capcity;	//总容量
	private HashMap<k,Node> caches;		//所有节点
	private Node last;
	private Node first;

	public LRU(int size) {
		curentSize =0;
		this.capcity = size;
		caches = new HashMap<k,Node>(size);
	}
	/*
	 * *添加元素
	 */
	public void put(k key,v value) {
		Node node = caches.get(key);
        //如果是新元素
        if (node == null) {
            //如果超过元素容纳量
            if (caches.size() >= capcity) {
                //移除最后一个节点
                caches.remove(last.key);
                removeLast();
            }
            //创建新节点
            node = new Node(key,value);
        }
        //已经存在的元素覆盖旧值
        node.value = value;
        //把元素移动到首部
        moveToHead(node);
        caches.put(key, node);
	}
	/**
     * 把当前节点移动到首部
     * @param node
     */
    private void moveToHead(Node node) {
    	//如果节点已在首部，则无需移动
        if (first == node) {
            return;
        }
        //节点要移动到头部，则将其原来所在的位置去除
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        //若节点是最后一个，将last交给前一个节点
        if (node == last) {
            last = last.pre;
        }
        //此为不存在节点的情况，
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
     * *移除最后一个节点
     */
    private void removeLast() {
        if (last != null) {
            last = last.pre;
            //若此时last为null
            //则说明已经不存在节点
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }
    /*
     * 通过key来获取元素
     * 用于在LRU算法的情况下访问元素的时候
     */
    public Object get(k key) {
        Node node = caches.get(key);
        if (node == null) {
            return null;
        }
        //把访问的节点移动到首部
        moveToHead(node);
        return node.value;
    }
    /**
     * 根据key移除节点
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
		// TODO 自动生成的方法存根
		int i;
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入表的最大长度");
		i = sc.nextInt();
		LRU<Integer, String> lru = new LRU<Integer, String>(i);
		for(int a=0;a<99;a++) {
			System.out.println("请输入要执行的操作：");
			
		switch(sc.nextInt()) {
		case 1:
			System.out.println("正在执行输入操作");
			for(int j=0;j<i;j++) {
				System.out.println("请输入节点"+j+"的值");
				lru.put(j, sc.next());
			}
			System.out.println("已完成操作");
			System.out.println("当前链表为：");
			System.out.println("原始链表为:"+lru.toString());
			break;
		case 2:
			System.out.println("正在执行获取操作");
			System.out.println("请输入要获取元素值的位置");
			int o = sc.nextInt();
			lru.get(o);
			System.out.println("获取key为"+o+"的元素之后的链表:"+lru.toString());
		case 3:
			System.out.println("正在执行删除操作");
			System.out.println("请输入要删除元素值的位置");
			int ui = sc.nextInt();
			lru.remove(ui);
	        System.out.println("移除key="+ui+"的之后的链表:"+lru.toString());
			
		}
		}
		
	}
}
