import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class Snakes {

	// public static int row;
	// public static int crow;
	// public static int Bolck_Size;

	// װ�ߵĽڵ�����

	ArrayList<Node> tailList = null;
	ArrayList<Node> headList = null;

	public Snakes(ArrayList<Node> tailL) {

		this.tailList = tailL;
		// ��ʼ����һ���ڵ�
		Node node = new Node((Yards.rows / 2) * Yards.Bolck_Size, (Yards.crows / 2) * Yards.Bolck_Size, Dir.L);
		tailList.add(node);
	}

	// �ߵĽڵ�
	class Node {
		// �ڵ�������
		int rows;
		// �ڵ�������
		int crows;
		// �ڵ㷽��
		Dir dir;

		public Node(int x, int y, Dir dir) {
			this.rows = x;
			this.crows = y;
			this.dir = dir;
		}

	}

	// β�����ӽڵ�
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

	// ͷ�����ӽڵ�
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

	// ����
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
