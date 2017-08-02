import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Snakes {

	int rows;
	int cols;
	int w = Yards.Bolck_Size;
	int h = Yards.Bolck_Size;
	// 保存头方向
	Dir dir = null;
	// 保存第1节点方向
	Dir nDir = null;
	// 装蛇的节点容器

	static ArrayList<Node> tailList = new ArrayList<Node>();
	// ArrayList<Node> headList = null;
	// 初始化第2个节点
	Node node = new Node(Yards.rows / 2, Yards.cols / 2, Dir.L);

	public Snakes() {
		tailList.add(node);
	}

//	public Snakes(ArrayList<Node> tailL) {
//
//		this.tailList = tailL;
//		tailList.add(node);
//	}

	// 蛇的节点
	class Node {
		// 节点所在行
		int rows;
		// 节点所在列
		int crows;
		// 节点方向
		Dir dir;

		int w = Yards.Bolck_Size;
		int h = Yards.Bolck_Size;

		public Node(int x, int y, Dir dir) {
			this.rows = x;
			this.crows = y;
			this.dir = dir;
		}

		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.red);
			g.fillRect(node.rows * w, node.crows * h, Yards.Bolck_Size, Yards.Bolck_Size);
			g.setColor(c);
		}

	}

	// 尾部增加节点
	public void addToTail() {

		// Node tail = this.tailList.get(tailList.size()-1);
		int s = tailList.size();
		s -= s;
		System.out.println("s的值-----：" + s);
		Node tail = tailList.get(s);
		switch (tail.dir) {
		case L:
			tailList.add(new Node(tail.rows, tail.crows + 1, tail.dir));
			break;
		case R:
			tailList.add(new Node(tail.rows, tail.crows - 1, tail.dir));
			break;
		case U:
			tailList.add(new Node(tail.rows + 1, tail.crows, tail.dir));
			break;
		case D:
			tailList.add(new Node(tail.rows - 1, tail.crows, tail.dir));
			break;

		}
	}

	// 头部增加节点
	// public void addToHead(Node head) {
	//
	// switch (head.dir) {
	// case L:
	// headList.add(new Node(head.rows, head.crows - 1, head.dir));
	// break;
	// case R:
	// headList.add(new Node(head.rows, head.crows + 1, head.dir));
	// break;
	// case U:
	// headList.add(new Node(head.rows - 1, head.crows, head.dir));
	// break;
	// case D:
	// headList.add(new Node(head.rows + 1, head.crows, head.dir));
	// break;
	//
	// }
	// }

	// 画蛇
	public void draw(Graphics g) {
		Iterator<Node> it = tailList.iterator();
		while (it.hasNext()) {
			Node node = (Node) it.next();
			node.draw(g);
		}
		move();

	}

	// ****
	private void move() {

//		addToTail();
		// 赋值一个ArrayList
		ArrayList<Node> tailClone = (ArrayList<Node>) tailList.clone();

		// 头结点改变方向
		switch (tailList.get(0).dir) {
		case L:
			tailList.get(0).rows = tailList.get(0).rows - 1;
			break;
		case R:
			tailList.get(0).rows = tailList.get(0).rows + 1;
			break;
		case U:
			tailList.get(0).crows = tailList.get(0).crows - 1;
			break;
		case D:
			tailList.get(0).crows = tailList.get(0).crows + 1;
			break;
		}

		// 之后的节点

		// 计算链表的大小
		int Size = tailList.size();
		if (Size == 1) {
			return;
		}

		Node nodeS = tailList.get(1);
		// 保存第2个节点方向
		nDir = nodeS.dir;
		// 将头结点方向赋予第二节点
		nodeS.dir = dir;

		if (Size == 2) {
			return;
		}

		Node nodeSS = tailList.get(2);
		// 将第二节点方向赋予第三节点
		nodeSS.dir = nDir;

		// 从第四节点开始，通过克隆的节点 将前一节点方向赋给现节点
		for (int i = 3; i < Size; i++) {
			tailList.get(3).dir = tailClone.get(i - 1).dir;
		}

	}

	//
	public void eat(Eggs e) {

		if (this.getRec().intersects(e.getRec())) {
			System.out.println("碰到了碰到了碰到了碰到了碰到了");
			e.occur();
			addToTail();
		}

	}

	// 碰撞计算
	public Rectangle getRec() {
		Node head = tailList.get(0);

		return new Rectangle(head.rows * w, head.crows * h, head.w, head.h);
	}

	// 键盘
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			// 保存头结点之前的方向
			dir = node.dir;
			node.dir = Dir.L;
			System.out.println("VK_LEFT");
			break;
		case KeyEvent.VK_RIGHT:
			dir = node.dir;
			node.dir = Dir.R;
			System.out.println("VK_RIGHT");

			break;
		case KeyEvent.VK_UP:
			dir = node.dir;
			node.dir = Dir.U;
			System.out.println("VK_UP");
			break;
		case KeyEvent.VK_DOWN:
			dir = node.dir;
			node.dir = Dir.D;
			System.out.println("VK_DOWN");
			break;
		}
	}
}
