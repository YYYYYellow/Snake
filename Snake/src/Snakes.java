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
	// ����ͷ����
	Dir dir = null;
	// �����1�ڵ㷽��
	Dir nDir = null;
	// װ�ߵĽڵ�����

	static ArrayList<Node> tailList = new ArrayList<Node>();
	// ArrayList<Node> headList = null;
	// ��ʼ����2���ڵ�
	Node node = new Node(Yards.rows / 2, Yards.cols / 2, Dir.L);

	public Snakes() {
		tailList.add(node);
	}

//	public Snakes(ArrayList<Node> tailL) {
//
//		this.tailList = tailL;
//		tailList.add(node);
//	}

	// �ߵĽڵ�
	class Node {
		// �ڵ�������
		int rows;
		// �ڵ�������
		int crows;
		// �ڵ㷽��
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

	// β�����ӽڵ�
	public void addToTail() {

		// Node tail = this.tailList.get(tailList.size()-1);
		int s = tailList.size();
		s -= s;
		System.out.println("s��ֵ-----��" + s);
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
		// ��ֵһ��ArrayList
		ArrayList<Node> tailClone = (ArrayList<Node>) tailList.clone();

		// ͷ���ı䷽��
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

		// ֮��Ľڵ�

		// ��������Ĵ�С
		int Size = tailList.size();
		if (Size == 1) {
			return;
		}

		Node nodeS = tailList.get(1);
		// �����2���ڵ㷽��
		nDir = nodeS.dir;
		// ��ͷ��㷽����ڶ��ڵ�
		nodeS.dir = dir;

		if (Size == 2) {
			return;
		}

		Node nodeSS = tailList.get(2);
		// ���ڶ��ڵ㷽��������ڵ�
		nodeSS.dir = nDir;

		// �ӵ��Ľڵ㿪ʼ��ͨ����¡�Ľڵ� ��ǰһ�ڵ㷽�򸳸��ֽڵ�
		for (int i = 3; i < Size; i++) {
			tailList.get(3).dir = tailClone.get(i - 1).dir;
		}

	}

	//
	public void eat(Eggs e) {

		if (this.getRec().intersects(e.getRec())) {
			System.out.println("������������������������������");
			e.occur();
			addToTail();
		}

	}

	// ��ײ����
	public Rectangle getRec() {
		Node head = tailList.get(0);

		return new Rectangle(head.rows * w, head.crows * h, head.w, head.h);
	}

	// ����
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			// ����ͷ���֮ǰ�ķ���
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
