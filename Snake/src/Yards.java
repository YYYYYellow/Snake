import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yards extends Frame {

	/**
	 * ���ڣ�2017/7/29
	 */
	private static final long serialVersionUID = 1L;

	// ��
	public static int rows = 50;
	// ��
	public static int crows = 50;
	// ���еļ��
	public static int Bolck_Size = 10;

	public static void main(String[] args) {
		// ����һ��yards
		new Yards().lauch();
	}

	public void lauch() {
		setBounds(0, 0, rows * Bolck_Size, crows * Bolck_Size);
		setBackground(Color.gray);
		setVisible(true);

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
			g.drawLine(i * Bolck_Size, 0, i * Bolck_Size, crows * Bolck_Size);
		}
		// ������
		for (int i = 1; i <= crows; i++) {
			g.drawLine(0, i * Bolck_Size, rows * Bolck_Size, i * Bolck_Size);
		}

	}

}
