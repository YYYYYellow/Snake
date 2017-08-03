import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Snakes {

	// ͷ��㣬β�ڵ㣬��С
	private Node head = null;
	private Node tail = null;
	private int size = 0;

	int rows;
	int cols;
	int w = Yards.Bolck_Size;
	int h = Yards.Bolck_Size;
	// װ�ߵĽڵ�����

	private Yards y;
	// ��ʼ����2���ڵ�
	Node node = new Node(Yards.rows / 2, Yards.cols / 2, Dir.L);

	public Snakes(Yards y) {
		this.y = y;
		//
		this.head = node;
		this.tail = node;
		tail.next = null;
		this.size = 1;
	}

	// �ߵĽڵ�
	class Node {
		// �ڵ�������
		int rows;
		// �ڵ�������
		int cols;
		// �ڵ㷽��
		Dir dir;

		Node next = null;
		Node pre = null;

		int w = Yards.Bolck_Size;
		int h = Yards.Bolck_Size;

		public Node(int x, int y, Dir dir) {
			this.rows = x;
			this.cols = y;
			this.dir = dir;
		}

		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.red);
			g.fillRect(this.rows * w, this.cols * h, Yards.Bolck_Size, Yards.Bolck_Size);
			g.setColor(c);
		}

	}

	// β�����ӽڵ�
	public void addToTail() {
		Node node = null;
		// ���Ͻ���(0,0)
		// ������rows(x)���
		// ������cols(y)���
		switch (tail.dir) {
		case L:
			node = new Node(tail.rows + 1, tail.cols, tail.dir);
			break;
		case R:
			node = new Node(tail.rows - 1, tail.cols, tail.dir);
			break;
		case U:
			node = new Node(tail.rows, tail.cols - 1, tail.dir);
			break;
		case D:
			node = new Node(tail.rows, tail.cols + 1, tail.dir);
			break;
		}
		tail.next = node;
		node.pre = tail;
		tail = node;
		// tail.next = null;
		size++;
	}

	// β�����ӽڵ�
	public void addToHead() {
		Node node = null;
		switch (head.dir) {

		case L:
			node = new Node(head.rows - 1, head.cols, head.dir);
			break;
		case R:
			node = new Node(head.rows + 1, head.cols, head.dir);
			break;
		case U:
			node = new Node(head.rows, head.cols - 1, head.dir);
			break;
		case D:
			node = new Node(head.rows, head.cols + 1, head.dir);
			break;
		}
		head.pre = node;
		node.next = head;
		head = node;
		size++;
	}

	// ����
	public void draw(Graphics g) {
		move();
		Node n = head;
		while (n != null) {
			n.draw(g);
			n = n.next;
		}
	}

	// �ƶ�
	private void move() {
		addToHead();
		deleteLast();
		checkout();
	}

	// ɾ�����һ��Ԫ��
	public void deleteLast() {

		Node n = tail;
		tail = tail.pre;
		tail.next = null;
		size++;
	}

	// ��ײ
	public void eat(Eggs e) {

		if (this.getRec().intersects(e.getRec())) {
			System.out.println("������������������������������");
			e.occur();
			addToTail();
			y.setScore(y.getScore() + 5);
		}

	}

	// ��ײ����
	public Rectangle getRec() {

		return new Rectangle(head.rows * w, head.cols * h, head.w, head.h);
	}

	// �жϽ�����û
	public void checkout() {
		// �ж��Ƿ������߽�
		if (head.rows < 2 || head.cols < 4 || head.rows > 48 || head.cols > 47) {
			y.gameOver();
		}
		// �ж��Ƿ�Ե��Լ�
		// 4�������ܳԵ��Լ�
		if (size <= 4) {
			return;
		}
		Node n = head.next;
		while (n != null) {
			if (n.rows == head.rows && n.cols == head.cols) {
				y.gameOver();
			}
			n = n.next;
		}

	}

	// ����
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			if (head.dir == Dir.R)
				return;
			head.dir = Dir.L;
			break;
		case KeyEvent.VK_RIGHT:
			if (head.dir == Dir.L)
				return;
			head.dir = Dir.R;
			break;
		case KeyEvent.VK_UP:
			if (head.dir == Dir.D)
				return;
			head.dir = Dir.U;
			break;
		case KeyEvent.VK_DOWN:
			if (head.dir == Dir.U)
				return;
			head.dir = Dir.D;
			break;
		}
	}
}
