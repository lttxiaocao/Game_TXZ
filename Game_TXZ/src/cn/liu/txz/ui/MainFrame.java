package cn.liu.txz.ui;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

//������ʾ��������
//��Ҫ�̳�Frame�ࡪ���̳�
public class MainFrame extends Frame implements KeyListener {
	// 1�����ô����������Ϣ
	// 1.1�������Ƿ���ʾ
	// ���췽��
	public MainFrame() {
		// �������ͼƬ�����ʱ�����˳�����Ҫ������Ҫ����
		// ���ͼƬ�����˳��
		// ����ͼƬ����������ͼƬ����������ӣ�����Ҫ�ڱ���ͼƬ֮ǰ���
		// �����ӣ�Ŀ��λ�ã�
		targetInit();
		// ������
		wolfInit();
		// ������
		sheepInit();
		// ���ϰ�
		treeInit();
		// ����һ������������������ӵ�������
		backgroundInit();
		// ������������
		setMainFrameUI();
		// ʹ�����ܹ��ල�û��ǲ��ǵ��˼���
		this.addKeyListener(this);
	}

	// ��ǰ�����������JLabel��JLabel������ ���жϵ�����ʲôλ��
	// �趨һ�������ݴ洢��Ӧ�ŵ�JLabel�洢����
	JLabel[][] sheeps = new JLabel[12][16];
	// ��ǰ���鴴��������JLabel������û���κζ���
	// ����������Ӧ�÷ŵ����������
	// ��Ҫ������г�ʼ���ĵط��������������Ӷ�Ӧ������ŵ����������

	// �������ݵ�ģ�⣬ʹ�ö�λ����ģ��
	// 1�����ϰ���0����յ�
	// 2��������
	// 4��������
	int[][] datas = { 
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 8, 1 },
			{ 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 8, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 8, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
	};
	// ������������λ��
	int wx;
	// �������������λ��
	int wy;
	// ����ǰ�ж��ٸ������ƶ�����Ŀ��
	int num = 0;
	// �������ӵ�����
	int total = 3;

	// �������ϰ��ĳ�ʼ��
	private void treeInit() {
		// 1������ͼƬ
		Icon ic = new ImageIcon("image\\tree.png");
		// ������λ����
		for (int i = 0; i < datas.length; i++) {
			for (int j = 0; j < datas[i].length; j++) {
				// �ж�һ��ԭʼ���������ֵ�����1�����ϰ�
				if (datas[i][j] == 1) {
					// �ϰ��ĳ�ʼ��
					// 2������JLabel
					JLabel lab_tree = new JLabel(ic);
					// 3�����ô�Сλ��
					lab_tree.setBounds(10 + j * 50, 40 + i * 50, 50, 50);
					// 4����ӵ�������
					this.add(lab_tree);
				}
			}
		}
	}

	// ������Ŀ��ĳ�ʼ��
	private void targetInit() {
		// ����Ŀ��
		// 1������ͼƬ
		Icon ic = new ImageIcon("image\\target.png");
		// 2����λ��
		// ������λ����
		for (int i = 0; i < datas.length; i++) {
			for (int j = 0; j < datas[i].length; j++) {
				// �ж�һ��ԭʼ���������ֵ�����8����Ŀ��
				if (datas[i][j] == 8) {
					// �ϰ��ĳ�ʼ��
					// 2������JLabel
					JLabel lab_target = new JLabel(ic);
					// 3�����ô�Сλ��
					lab_target.setBounds(10 + j * 50, 40 + i * 50, 50, 50);
					// 4����ӵ�������
					this.add(lab_target);
				}
			}
		}
	}

	// ���������ӵĳ�ʼ��
	private void sheepInit() {
		// �������ӵ�ģ��
		// 1������һ��ͼƬ�����ӵ�ͼƬ
		Icon i = new ImageIcon("image\\sheep_out.png");
		// 2��ʹ��JLabel���ģ������
		JLabel lab_sheep1 = new JLabel(i);
		// 3���������λ��
		lab_sheep1.setBounds(10 + 7 * 50, 40 + 3 * 50, 50, 50);
		// 4����������ӵ�������
		this.add(lab_sheep1);
		// �޸����Ӷ�Ӧλ���ϵ�����Ϊ4
		datas[3][7] = 4;
		// �����JLabel������뵽sheeps��������
		sheeps[3][7] = lab_sheep1;
		// �����ڶ�ֻ��ͼƬ����Ҫ����
		// �ڶ�ֻ������ʱ��ֻ��Ҫ�޸Ķ�Ӧ����ʾλ��
		JLabel lab_sheep2 = new JLabel(i);
		lab_sheep2.setBounds(10 + 7 * 50, 40 + 5 * 50, 50, 50);
		this.add(lab_sheep2);
		datas[5][7] = 4;
		sheeps[5][7] = lab_sheep2;
		// ��������ֻ��ͼƬ����Ҫ����
		// ����ֻ������ʱ��ֻ��Ҫ�޸Ķ�Ӧ����ʾλ��
		JLabel lab_sheep3 = new JLabel(i);
		lab_sheep3.setBounds(10 + 7 * 50, 40 + 7 * 50, 50, 50);
		this.add(lab_sheep3);
		datas[7][7] = 4;
		sheeps[7][7] = lab_sheep3;
	}

	// ����������ĳ�ʼ��
	JLabel lab_wolf;

	private void wolfInit() {
		// ���������λ��������
		wx = 3;
		wy = 3;
		// ʹ��һ��ͼƬģ������
		// 1������һ��ͼƬ������ͼƬ
		Icon i = new ImageIcon("image\\wolf_down.png");
		// 2��ʹ��Jlabel���ģ������
		lab_wolf = new JLabel(i);
		// 3��������������Ļ�ϵ���ʾλ��
		// �������ʾλ�÷����ںδ��ȽϺ���
		lab_wolf.setBounds(10 + wx * 50, 40 + wy * 50, 50, 50);
		// 4�����������ŵ���������
		this.add(lab_wolf);
		// ������������ӵ�������
		// �������Ӧ��λ���ϵ����ݸĳ�2
		// datas[3][3] = 2 ;
	}

	// ������ʼ��
	private void backgroundInit() {
		// ʹ��JLabel��������
		// ʹ��ͼƬ��ΪJLabel�ڲ���ʾ������
		// ����һ��ͼƬ����
		Icon i = new ImageIcon("image\\bg.png");
		JLabel lab_bg = new JLabel(i);
		// ����Ҫ��ӵ������λ�úʹ�С
		lab_bg.setBounds(10, 40, 800, 600);
		// �����������ӵ���������
		this.add(lab_bg);
	}

	// ���������������ʾЧ��
	private void setMainFrameUI() {
		// ������������Ĳ���ģʽΪ���ɲ���
		this.setLayout(null);
		// ���ô��ڵı���
		this.setTitle("Game");
		// ���ô��ڵ�λ��
		this.setLocation(200, 100);
		// ���ô��ڳߴ�
		this.setSize(820, 650);
		// ���ô��ڲ������
		this.setResizable(false);
		// �ô���ر�
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// ���ô�����ʾ����
		this.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// ��ηֱ��û�������Ǽ����ϵ�ĳ������
		// ͨ������ֵ�ֱ�����һ������
		// ��ȡ����ֵ�÷�ʽ
		// key����������������ĸ�����
		// System.out.println(e.getKeyCode());
		// �� 37
		// �� 38
		// �� 39
		// �� 40
		int key = e.getKeyCode();
		// �����
		if (key == 37) {
			// �����������ƶ�
			// ��ͼƬ
			Icon i = new ImageIcon("image\\wolf_left.png");
			lab_wolf.setIcon(i);
			// �����ϵ�һλ�������� wy wx-1
			// �����ϵڶ�λ�������� wy wx-2
			// ------ʲô������
			// ���� ��ľ
			if (datas[wy][wx - 1] == 1) {
				// ��νʲô���鶼�������ǲ�������Ĵ���ִ��
				return;
			}
			// ���� ���� ��ľ
			if (datas[wy][wx - 1] == 4 && datas[wy][wx - 2] == 1) {
				return;
			}
			// ���� ���� ����
			if (datas[wy][wx - 1] == 4 && datas[wy][wx - 2] == 4) {
				return;
			}
			// ���� ���� Ŀ������
			if (datas[wy][wx - 1] == 4 && datas[wy][wx - 2] == 12) {
				return;
			}
			// ���� Ŀ������ ��ľ
			if (datas[wy][wx - 1] == 12 && datas[wy][wx - 2] == 1) {
				return;
			}
			// ���� Ŀ������ ����
			if (datas[wy][wx - 1] == 12 && datas[wy][wx - 2] == 4) {
				return;
			}
			// ���� Ŀ������ Ŀ������
			if (datas[wy][wx - 1] == 12 && datas[wy][wx - 2] == 12) {
				return;
			}
			// ------������ ��ͼƬ ����ʾλ��
			// ���� �յ�
			// ���� ��Ŀ��
			if (datas[wy][wx - 1] == 0 || datas[wy][wx - 1] == 8) {
				wx = wx - 1;
				// ����Ҫ֪������������ʲôλ��
				int x = (int) lab_wolf.getLocation().getX();
				int y = (int) lab_wolf.getLocation().getY();
				// System.out.println(x+"__"+y);
				// Ҫ֪���ƶ�һ�β��Ӷ��
				// һ���ƶ�50�������ƶ���x��С��y����
				lab_wolf.setLocation(x - 50, y);
				return;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 4-0 0-4
			// ���� ���� �յ�
			if (datas[wy][wx - 1] == 4 && datas[wy][wx - 2] == 0) {
				datas[wy][wx - 1] = 0;
				datas[wy][wx - 2] = 4;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 4-0 8-12
			// ���� ���� ��Ŀ��
			if (datas[wy][wx - 1] == 4 && datas[wy][wx - 2] == 8) {
				datas[wy][wx - 1] = 0;
				datas[wy][wx - 2] = 12;
				num++;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 12-8 0-4
			// ���� Ŀ������ �յ�
			if (datas[wy][wx - 1] == 12 && datas[wy][wx - 2] == 0) {
				datas[wy][wx - 1] = 8;
				datas[wy][wx - 2] = 4;
				num--;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 12-8 8-12
			// ���� Ŀ������ ��Ŀ��
			if (datas[wy][wx - 1] == 12 && datas[wy][wx - 2] == 8) {
				datas[wy][wx - 1] = 8;
				datas[wy][wx - 2] = 12;
			}
			// 1����ȡ���ӵĶ���
			// 2�������ƶ�������
			sheeps[wy][wx - 1].setLocation(10 + wx * 50 - 100, 40 + wy * 50);
			// ������������޸�����ʾ��Ч����ʵ��������е�λ�ò�û�з����仯
			sheeps[wy][wx - 2] = sheeps[wy][wx - 1];
			// ��ԭʼλ���ϵ���������
			sheeps[wy][wx - 1] = null;
			wx = wx - 1;
			// ����Ҫ֪������������ʲôλ��
			int x = (int) lab_wolf.getLocation().getX();
			int y = (int) lab_wolf.getLocation().getY();
			// System.out.println(x+"__"+y);
			// Ҫ֪���ƶ�һ�β��Ӷ��
			// һ���ƶ�50�������ƶ���x��С��y����
			lab_wolf.setLocation(x - 50, y);
			victory();
/*
			// һ��Ҫ֪���ƶ���λ��ǰ����û���ϰ�
			// ���û���ϰ��ƶ�
			// ������ϰ���ʲô���鶼����
			if (datas[wy][wx - 1] == 1) {
				// ��ͼƬ
				Icon i = new ImageIcon("image\\wolf_left.png");
				lab_wolf.setIcon(i);
				// ��νʲô���鶼�������ǲ�������Ĵ���ִ��
				return;
			}
			// ��ײ���֮������������
			if (datas[wy][wx - 1] == 4) {
				// ����������
				// ������������ӣ����������ڷ����ϵĺ���Ϊ�����ƶ�
				// ������Ӻ������������Ŀ���������ӣ��Ͳ����ƶ���
				if (datas[wy][wx - 2] == 0) {
					// �����ڷ������ƶ�һ�������ڷ������ƶ�һ��
					// �����ڷ������ƶ�һ��
					// 1�����ӵ�ʵ������4��λ��Ҫ�����仯
					// ��ǰ�������ڵ�λ��ֵ���0
					datas[wy][wx - 1] = 0;
					// �ƶ��Ժ���������λ��ֵ���4
					datas[wy][wx - 2] = 4;
					// 2�����ӵ���ʾλ��ҲҪ�����仯
					// ��ν����ģ�͵��ƶ���ʵ��������ƶ���һ����
					// ���Ǹı����ӵ�������ʹ�����Ӷ�����øı�����ķ���
					// ֻҪ�ܹ���ȡ�����Ӷ���ͼ����Ҫ�ƶ��������OK��
					// ����һ��Ҫʹ��Ҫ�ƶ����Ǹ�����
					// 1����ȡ���ӵĶ���
					// 2�������ƶ�������
					sheeps[wy][wx - 1].setLocation(10 + wx * 50 - 100,
							40 + wy * 50);
					// ������������޸�����ʾ��Ч����ʵ��������е�λ�ò�û�з����仯
					sheeps[wy][wx - 2] = sheeps[wy][wx - 1];
					// ��ԭʼλ���ϵ���������
					sheeps[wy][wx - 1] = null;
					// -------�����ƶ����뿪ʼ----------
					wx = wx - 1;
					// ����Ҫ֪������������ʲôλ��
					int x = (int) lab_wolf.getLocation().getX();
					int y = (int) lab_wolf.getLocation().getY();
					// System.out.println(x+"__"+y);
					// Ҫ֪���ƶ�һ�β��Ӷ��
					// һ���ƶ�50�������ƶ���x��С��y����
					lab_wolf.setLocation(x - 50, y);
					// �����ƶ������ͼƬ����
					// ����Ҫ��ͼƬ
					Icon i = new ImageIcon("image\\wolf_left.png");
					// ������ʾͼƬΪ�ƶ�ͼƬ
					lab_wolf.setIcon(i);
					// -------�����ƶ��������----------
				} else {
					// ��ͼƬ
					Icon i = new ImageIcon("image\\wolf_left.png");
					lab_wolf.setIcon(i);
				}
			}
			// ��������յ�
			if (datas[wy][wx - 1] == 0) {
				wx = wx - 1;
				int x = (int) lab_wolf.getLocation().getX();
				int y = (int) lab_wolf.getLocation().getY();
				lab_wolf.setLocation(x - 50, y);
				Icon i = new ImageIcon("image\\wolf_left.png");
				lab_wolf.setIcon(i);
			}
*/
		}
		// ���ϼ�
		if (key == 38) {
			// �����������ƶ�
			// ��ͼƬ
			Icon i = new ImageIcon("image\\wolf_up.png");
			lab_wolf.setIcon(i);
			// �����ϵ�һλ�������� wy-1 wx
			// �����ϵڶ�λ�������� wy-2 wx
			// ------ʲô������
			// ���� ��ľ
			if (datas[wy - 1][wx] == 1) {
				// ��νʲô���鶼�������ǲ�������Ĵ���ִ��
				return;
			}
			// ���� ���� ��ľ
			if (datas[wy - 1][wx] == 4 && datas[wy - 2][wx] == 1) {
				return;
			}
			// ���� ���� ����
			if (datas[wy - 1][wx] == 4 && datas[wy - 2][wx] == 4) {
				return;
			}
			// ���� ���� Ŀ������
			if (datas[wy - 1][wx] == 4 && datas[wy - 2][wx] == 12) {
				return;
			}
			// ���� Ŀ������ ��ľ
			if (datas[wy - 1][wx] == 12 && datas[wy - 2][wx] == 1) {
				return;
			}
			// ���� Ŀ������ ����
			if (datas[wy - 1][wx] == 12 && datas[wy - 2][wx] == 4) {
				return;
			}
			// ���� Ŀ������ Ŀ������
			if (datas[wy - 1][wx] == 12 && datas[wy - 2][wx] == 12) {
				return;
			}
			// ------������ ��ͼƬ ����ʾλ��
			// ���� �յ�
			// ���� ��Ŀ��
			if (datas[wy - 1][wx] == 0 || datas[wy - 1][wx] == 8) {
				wy = wy - 1;
				// ����Ҫ֪������������ʲôλ��
				int x = (int) lab_wolf.getLocation().getX();
				int y = (int) lab_wolf.getLocation().getY();
				// System.out.println(x+"__"+y);
				// Ҫ֪���ƶ�һ�β��Ӷ��
				// һ���ƶ�50�������ƶ���x���y����
				lab_wolf.setLocation(x, y - 50);
				return;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 4-0 0-4
			// ���� ���� �յ�
			if (datas[wy - 1][wx] == 4 && datas[wy - 2][wx] == 0) {
				datas[wy - 1][wx] = 0;
				datas[wy - 2][wx] = 4;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 4-0 8-12
			// ���� ���� ��Ŀ��
			if (datas[wy - 1][wx] == 4 && datas[wy - 2][wx] == 8) {
				datas[wy - 1][wx] = 0;
				datas[wy - 2][wx] = 12;
				num++;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 12-8 0-4
			// ���� Ŀ������ �յ�
			if (datas[wy - 1][wx] == 12 && datas[wy - 2][wx] == 0) {
				datas[wy - 1][wx] = 8;
				datas[wy - 2][wx] = 4;
				num--;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 12-8 8-12
			// ���� Ŀ������ ��Ŀ��
			if (datas[wy - 1][wx] == 12 && datas[wy - 2][wx] == 8) {
				datas[wy - 1][wx] = 8;
				datas[wy - 2][wx] = 12;
			}
			// 1����ȡ���ӵĶ���
			// 2�������ƶ�������
			sheeps[wy - 1][wx].setLocation(10 + wx * 50, 40 + wy * 50 - 100);
			// ������������޸�����ʾ��Ч����ʵ��������е�λ�ò�û�з����仯
			sheeps[wy - 2][wx] = sheeps[wy - 1][wx];
			// ��ԭʼλ���ϵ���������
			sheeps[wy - 1][wx] = null;
			wy = wy - 1;
			// ����Ҫ֪������������ʲôλ��
			int x = (int) lab_wolf.getLocation().getX();
			int y = (int) lab_wolf.getLocation().getY();
			// System.out.println(x+"__"+y);
			// Ҫ֪���ƶ�һ�β��Ӷ��
			// һ���ƶ�50�������ƶ���x���䣬y��С
			lab_wolf.setLocation(x, y - 50);	
			victory();
		}
		// ���Ҽ�
		if (key == 39) {
			// �����������ƶ�
			// ��ͼƬ
			Icon i = new ImageIcon("image\\wolf_right.png");
			lab_wolf.setIcon(i);
			// �����ϵ�һλ�������� wy wx+1
			// �����ϵڶ�λ�������� wy wx+2
			// ------ʲô������
			// ���� ��ľ
			if (datas[wy][wx + 1] == 1) {
				// ��νʲô���鶼�������ǲ�������Ĵ���ִ��
				return;
			}
			// ���� ���� ��ľ
			if (datas[wy][wx + 1] == 4 && datas[wy][wx + 2] == 1) {
				return;
			}
			// ���� ���� ����
			if (datas[wy][wx + 1] == 4 && datas[wy][wx + 2] == 4) {
				return;
			}
			// ���� ���� Ŀ������
			if (datas[wy][wx + 1] == 4 && datas[wy][wx + 2] == 12) {
				return;
			}
			// ���� Ŀ������ ��ľ
			if (datas[wy][wx + 1] == 12 && datas[wy][wx + 2] == 1) {
				return;
			}
			// ���� Ŀ������ ����
			if (datas[wy][wx + 1] == 12 && datas[wy][wx + 2] == 4) {
				return;
			}
			// ���� Ŀ������ Ŀ������
			if (datas[wy][wx + 1] == 12 && datas[wy][wx + 2] == 12) {
				return;
			}
			// ------������ ��ͼƬ ����ʾλ��
			// ���� �յ�
			// ���� ��Ŀ��
			if (datas[wy][wx + 1] == 0 || datas[wy][wx + 1] == 8) {
				wx = wx + 1;
				// ����Ҫ֪������������ʲôλ��
				int x = (int) lab_wolf.getLocation().getX();
				int y = (int) lab_wolf.getLocation().getY();
				// System.out.println(x+"__"+y);
				// Ҫ֪���ƶ�һ�β��Ӷ��
				// һ���ƶ�50�������ƶ���x���y����
				lab_wolf.setLocation(x + 50, y);
				return;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 4-0 0-4
			// ���� ���� �յ�
			if (datas[wy][wx + 1] == 4 && datas[wy][wx + 2] == 0) {
				datas[wy][wx + 1] = 0;
				datas[wy][wx + 2] = 4;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 4-0 8-12
			// ���� ���� ��Ŀ��
			if (datas[wy][wx + 1] == 4 && datas[wy][wx + 2] == 8) {
				datas[wy][wx + 1] = 0;
				datas[wy][wx + 2] = 12;
				num++;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 12-8 0-4
			// ���� Ŀ������ �յ�
			if (datas[wy][wx + 1] == 12 && datas[wy][wx + 2] == 0) {
				datas[wy][wx + 1] = 8;
				datas[wy][wx + 2] = 4;
				num--;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 12-8 8-12
			// ���� Ŀ������ ��Ŀ��
			if (datas[wy][wx + 1] == 12 && datas[wy][wx + 2] == 8) {
				datas[wy][wx + 1] = 8;
				datas[wy][wx + 2] = 12;
			}
			// 1����ȡ���ӵĶ���
			// 2�������ƶ�������
			sheeps[wy][wx + 1].setLocation(10 + wx * 50 + 100, 40 + wy * 50);
			// ������������޸�����ʾ��Ч����ʵ��������е�λ�ò�û�з����仯
			sheeps[wy][wx + 2] = sheeps[wy][wx + 1];
			// ��ԭʼλ���ϵ���������
			sheeps[wy][wx + 1] = null;
			wx = wx + 1;
			// ����Ҫ֪������������ʲôλ��
			int x = (int) lab_wolf.getLocation().getX();
			int y = (int) lab_wolf.getLocation().getY();
			// System.out.println(x+"__"+y);
			// Ҫ֪���ƶ�һ�β��Ӷ��
			// һ���ƶ�50�������ƶ���x���y����
			lab_wolf.setLocation(x + 50, y);		
			victory();
		}
		// ���¼�
		if (key == 40) {
			// �����������ƶ�
			// ��ͼƬ
			Icon i = new ImageIcon("image\\wolf_down.png");
			lab_wolf.setIcon(i);
			// �����ϵ�һλ�������� wy+1 wx
			// �����ϵڶ�λ�������� wy+2 wx
			// ------ʲô������
			// ���� ��ľ
			if (datas[wy + 1][wx] == 1) {
				// ��νʲô���鶼�������ǲ�������Ĵ���ִ��
				return;
			}
			// ���� ���� ��ľ
			if (datas[wy + 1][wx] == 4 && datas[wy + 2][wx] == 1) {
				return;
			}
			// ���� ���� ����
			if (datas[wy + 1][wx] == 4 && datas[wy + 2][wx] == 4) {
				return;
			}
			// ���� ���� Ŀ������
			if (datas[wy + 1][wx] == 4 && datas[wy + 2][wx] == 12) {
				return;
			}
			// ���� Ŀ������ ��ľ
			if (datas[wy + 1][wx] == 12 && datas[wy + 2][wx] == 1) {
				return;
			}
			// ���� Ŀ������ ����
			if (datas[wy + 1][wx] == 12 && datas[wy + 2][wx] == 4) {
				return;
			}
			// ���� Ŀ������ Ŀ������
			if (datas[wy + 1][wx] == 12 && datas[wy + 2][wx] == 12) {
				return;
			}
			// ------������ ��ͼƬ ����ʾλ��
			// ���� �յ�
			// ���� ��Ŀ��
			if (datas[wy + 1][wx] == 0 || datas[wy + 1][wx] == 8) {
				wy = wy + 1;
				// ����Ҫ֪������������ʲôλ��
				int x = (int) lab_wolf.getLocation().getX();
				int y = (int) lab_wolf.getLocation().getY();
				// System.out.println(x+"__"+y);
				// Ҫ֪���ƶ�һ�β��Ӷ��
				// һ���ƶ�50�������ƶ���x���y����
				lab_wolf.setLocation(x, y + 50);
				return;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 4-0 0-4
			// ���� ���� �յ�
			if (datas[wy + 1][wx] == 4 && datas[wy + 2][wx] == 0) {
				datas[wy + 1][wx] = 0;
				datas[wy + 2][wx] = 4;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 4-0 8-12
			// ���� ���� ��Ŀ��
			if (datas[wy + 1][wx] == 4 && datas[wy + 2][wx] == 8) {
				datas[wy + 1][wx] = 0;
				datas[wy + 2][wx] = 12;
				num++;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 12-8 0-4
			// ���� Ŀ������ �յ�
			if (datas[wy + 1][wx] == 12 && datas[wy + 2][wx] == 0) {
				datas[wy + 1][wx] = 8;
				datas[wy + 2][wx] = 4;
				num--;
			}
			// ------������ ��ͼƬ ����ʾλ�� ����λ�� ����JLabel 12-8 8-12
			// ���� Ŀ������ ��Ŀ��
			if (datas[wy + 1][wx] == 12 && datas[wy + 2][wx] == 8) {
				datas[wy + 1][wx] = 8;
				datas[wy + 2][wx] = 12;
			}
			// 1����ȡ���ӵĶ���
			// 2�������ƶ�������
			sheeps[wy + 1][wx].setLocation(10 + wx * 50, 40 + wy * 50 + 100);
			// ������������޸�����ʾ��Ч����ʵ��������е�λ�ò�û�з����仯
			sheeps[wy + 2][wx] = sheeps[wy + 1][wx];
			// ��ԭʼλ���ϵ���������
			sheeps[wy + 1][wx] = null;
			wy = wy + 1;
			// ����Ҫ֪������������ʲôλ��
			int x = (int) lab_wolf.getLocation().getX();
			int y = (int) lab_wolf.getLocation().getY();
			// System.out.println(x+"__"+y);
			// Ҫ֪���ƶ�һ�β��Ӷ��
			// һ���ƶ�50�������ƶ���x���䣬y��С
			lab_wolf.setLocation(x, y + 50);			
			victory();
		}
	}

	private void victory() {
		if(num==3){
			Frame f =new Frame();
			// ���ô��ڵı���
			f.setTitle("succeed");
			// ���ô��ڵ�λ��
			f.setLocation(600, 400);
			// ���ô��ڳߴ�
			f.setSize(400, 200);
			Icon i =new ImageIcon("image\\succeed.png");
			JLabel lab_ss = new JLabel(i);
			f.add(lab_ss);
			// ���ô��ڲ������
			f.setResizable(false);
			// �ô���ر�
			f.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			// ���ô�����ʾ����
			f.setVisible(true);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
