import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class Snakes {

	// public static int row;
	// public static int crow;
	// public static int Bolck_Size;

	// 装蛇的节点容器

	ArrayList<Node> tailList = null;
	ArrayList<Node> headList = null;

	public Snakes(ArrayList<Node> tailL) {

		this.tailList = tailL;
		// 初始化第一个节点
		Node node = new Node((Yards.rows / 2) * Yards.Bolck_Size, (Yards.crows / 2) * Yards.Bolck_Size, Dir.L);
		tailList.add(node);
	}

	// 蛇的节点
	class Node {
		// 节点所在行
		int rows;
		// 节点所在列
		int crows;
		// 节点方向
		Dir dir;

		public Node(int x, int y, Dir dir) {
			this.rows = x;
			this.crows = y;
			this.dir = dir;
		}

	}

	// 尾部增加节点
	public void addToTail(Node tail) {

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
		Color c = g.getColor();
		g.setColor(Color.red);
		Iterator<Node> it = tailList.iterator();
		while (it.hasNext()) {
			Node node = (Node) it.next();
			g.fillRect(node.rows, node.crows, Yards.Bolck_Size, Yards.Bolck_Size);
		}
		g.setColor(c);
	}
}
