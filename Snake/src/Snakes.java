import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Snakes {

	// public static int row;
	// public static int crow;
	// public static int Bolck_Size;

	// ����ͷ����
	Dir dir = null;
	// �����1�ڵ㷽��
	Dir nDir = null;
	// װ�ߵĽڵ�����

	ArrayList<Node> tailList = null;
	ArrayList<Node> headList = null;
	// ��ʼ����2���ڵ�
	Node node = new Node((Yards.rows / 2) * Yards.Bolck_Size, (Yards.crows / 2) * Yards.Bolck_Size, Dir.L);

	public Snakes(ArrayList<Node> tailL) {

		this.tailList = tailL;
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
	public void addToTail() {

		Node tail = tailList.get(tailList.size() - 1);
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

		// ���ƶ�
		move();
		Iterator<Node> it = tailList.iterator();
		// �ڻ���
		while (it.hasNext()) {
			Node node = (Node) it.next();

			g.fillRect(node.rows, node.crows, Yards.Bolck_Size, Yards.Bolck_Size);
			// System.out.println("???????");
		}
		// �ߵ��ƶ�
		// move();
		g.setColor(c);

	}

	// ****
	private void move() {

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
