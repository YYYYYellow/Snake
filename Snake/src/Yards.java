import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Yards extends Frame {

	/**
	 * ���ڣ�2017/7/29
	 */
	private static final long serialVersionUID = 1L;

	// װ�ߵĽڵ�����
//	static ArrayList<Snakes.Node> tailList = new ArrayList<Snakes.Node>();

	// ��
	public static int rows = 50;
	// ��
	public static int cols = 50;
	// ���еļ��
	public static int Bolck_Size = 10;

	static Snakes snakes;
	Eggs eggs = new Eggs();
	Image offScreenImage = null;

	public static void main(String[] args) {
		// ����һ��yards

		new Yards().lauch();
//		snakes = new Snakes(tailList);
		snakes = new Snakes();

		// ����ˢ���߳�

		// Thread thread = new Thread(yards);
		// thread.start();
	}

	public void lauch() {
		setBounds(0, 0, rows * Bolck_Size, cols * Bolck_Size);
		setBackground(Color.gray);
		setVisible(true);
		new Thread(new RepaintRunnable()).start();

		addKeyListener(new KeyMonitor());
		// �رռ�����
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Frame f = (Frame) e.getSource();
				f.setVisible(false);
				System.exit(0);
			}
		});
	}

	// ���ػ�������
	public void paint(Graphics g) {
		g.setColor(Color.darkGray);
		// ������
		for (int i = 1; i <= rows; i++) {
			g.drawLine(i * Bolck_Size, 0, i * Bolck_Size, cols * Bolck_Size);
		}
		// ������
		for (int i = 1; i <= cols; i++) {
			g.drawLine(0, i * Bolck_Size, rows * Bolck_Size, i * Bolck_Size);
		}
		snakes.eat(eggs);
		eggs.draw(g);
		snakes.draw(g);

	}

	private class RepaintRunnable implements Runnable {

		@Override
		public void run() {
			while (true) {
				repaint();
				// System.out.println("--------");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			snakes.keyPressed(e);
		}

	}

}
