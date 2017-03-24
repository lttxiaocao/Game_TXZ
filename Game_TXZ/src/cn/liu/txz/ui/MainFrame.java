package cn.liu.txz.ui;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

//用作显示的主窗体
//需要继承Frame类——继承
public class MainFrame extends Frame implements KeyListener {
	// 1、设置窗口最基本信息
	// 1.1、窗口是否显示
	// 构造方法
	public MainFrame() {
		// 由于添加图片组件的时候存在顺序，因此要根据需要调整
		// 添加图片组件的顺序
		// 背景图片由于是最大的图片，因此最后添加，其他要在背景图片之前添加
		// 做笼子（目标位置）
		targetInit();
		// 做人物
		wolfInit();
		// 做箱子
		sheepInit();
		// 做障碍
		treeInit();
		// 制作一个背景，并将背景添加到窗体中
		backgroundInit();
		// 设置整个窗体
		setMainFrameUI();
		// 使窗口能够监督用户是不是点了键盘
		this.addKeyListener(this);
	}

	// 当前操作的组件是JLabel，JLabel现在无 法判断到底在什么位置
	// 设定一个与数据存储对应着的JLabel存储数组
	JLabel[][] sheeps = new JLabel[12][16];
	// 当前数组创建出来后，JLabel数组中没有任何东西
	// 制作的箱子应该放到这个数组中
	// 需要在羊进行初始化的地方，将那三个箱子对应的组件放到这个数组中

	// 场景数据的模拟，使用二位数组模拟
	// 1代表障碍，0代表空地
	// 2代表人物
	// 4代表箱子
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
	// 代表人物横向的位置
	int wx;
	// 代表人物纵向的位置
	int wy;
	// 代表当前有多少个箱子移动到了目标
	int num = 0;
	// 代表箱子的总数
	int total = 3;

	// 推箱子障碍的初始化
	private void treeInit() {
		// 1、创建图片
		Icon ic = new ImageIcon("image\\tree.png");
		// 遍历二位数组
		for (int i = 0; i < datas.length; i++) {
			for (int j = 0; j < datas[i].length; j++) {
				// 判断一下原始数据里面的值如果是1，做障碍
				if (datas[i][j] == 1) {
					// 障碍的初始化
					// 2、创建JLabel
					JLabel lab_tree = new JLabel(ic);
					// 3、设置大小位置
					lab_tree.setBounds(10 + j * 50, 40 + i * 50, 50, 50);
					// 4、添加到窗体中
					this.add(lab_tree);
				}
			}
		}
	}

	// 推箱子目标的初始化
	private void targetInit() {
		// 制作目标
		// 1、制作图片
		Icon ic = new ImageIcon("image\\target.png");
		// 2、设位置
		// 遍历二位数组
		for (int i = 0; i < datas.length; i++) {
			for (int j = 0; j < datas[i].length; j++) {
				// 判断一下原始数据里面的值如果是8，做目标
				if (datas[i][j] == 8) {
					// 障碍的初始化
					// 2、创建JLabel
					JLabel lab_target = new JLabel(ic);
					// 3、设置大小位置
					lab_target.setBounds(10 + j * 50, 40 + i * 50, 50, 50);
					// 4、添加到窗体中
					this.add(lab_target);
				}
			}
		}
	}

	// 推箱子箱子的初始化
	private void sheepInit() {
		// 制作箱子的模型
		// 1、创建一张图片，箱子的图片
		Icon i = new ImageIcon("image\\sheep_out.png");
		// 2、使用JLabel组件模拟箱子
		JLabel lab_sheep1 = new JLabel(i);
		// 3、设置羊的位置
		lab_sheep1.setBounds(10 + 7 * 50, 40 + 3 * 50, 50, 50);
		// 4、把箱子添加到窗体中
		this.add(lab_sheep1);
		// 修改箱子对应位置上的数据为4
		datas[3][7] = 4;
		// 把这个JLabel组件放入到sheeps的数组中
		sheeps[3][7] = lab_sheep1;
		// 制作第二只羊，图片不需要制作
		// 第二只羊制作时，只需要修改对应的显示位置
		JLabel lab_sheep2 = new JLabel(i);
		lab_sheep2.setBounds(10 + 7 * 50, 40 + 5 * 50, 50, 50);
		this.add(lab_sheep2);
		datas[5][7] = 4;
		sheeps[5][7] = lab_sheep2;
		// 制作第三只羊，图片不需要制作
		// 第三只羊制作时，只需要修改对应的显示位置
		JLabel lab_sheep3 = new JLabel(i);
		lab_sheep3.setBounds(10 + 7 * 50, 40 + 7 * 50, 50, 50);
		this.add(lab_sheep3);
		datas[7][7] = 4;
		sheeps[7][7] = lab_sheep3;
	}

	// 推箱子人物的初始化
	JLabel lab_wolf;

	private void wolfInit() {
		// 人物最初的位置在哪里
		wx = 3;
		wy = 3;
		// 使用一张图片模拟人物
		// 1、创建一张图片，人物图片
		Icon i = new ImageIcon("image\\wolf_down.png");
		// 2、使用Jlabel组件模拟人物
		lab_wolf = new JLabel(i);
		// 3、设置人物在屏幕上的显示位置
		// 人物的显示位置放置在何处比较合算
		lab_wolf.setBounds(10 + wx * 50, 40 + wy * 50, 50, 50);
		// 4、把这个人物放到窗体里面
		this.add(lab_wolf);
		// 把人物数据添加到数组中
		// 将人物对应的位置上的数据改成2
		// datas[3][3] = 2 ;
	}

	// 背景初始化
	private void backgroundInit() {
		// 使用JLabel制作背景
		// 使用图片作为JLabel内部显示的内容
		// 创建一个图片对象
		Icon i = new ImageIcon("image\\bg.png");
		JLabel lab_bg = new JLabel(i);
		// 设置要添加的组件的位置和大小
		lab_bg.setBounds(10, 40, 800, 600);
		// 将这个东西添加到窗体里面
		this.add(lab_bg);
	}

	// 设置主窗体界面显示效果
	private void setMainFrameUI() {
		// 设置整个窗体的布局模式为自由布局
		this.setLayout(null);
		// 设置窗口的标题
		this.setTitle("Game");
		// 设置窗口的位置
		this.setLocation(200, 100);
		// 设置窗口尺寸
		this.setSize(820, 650);
		// 设置窗口不能最大化
		this.setResizable(false);
		// 让窗体关闭
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// 设置窗口显示出来
		this.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 如何分辨用户点击的是键盘上的某个按键
		// 通过键码值分辨是哪一个按键
		// 获取键码值得方式
		// key代表了你输入的是哪个按键
		// System.out.println(e.getKeyCode());
		// 左 37
		// 上 38
		// 右 39
		// 下 40
		int key = e.getKeyCode();
		// 按左键
		if (key == 37) {
			// 让人物向左移动
			// 换图片
			Icon i = new ImageIcon("image\\wolf_left.png");
			lab_wolf.setIcon(i);
			// 方向上第一位的坐标是 wy wx-1
			// 方向上第二位的坐标是 wy wx-2
			// ------什么都不做
			// 人物 树木
			if (datas[wy][wx - 1] == 1) {
				// 所谓什么事情都不做就是不让下面的代码执行
				return;
			}
			// 人物 箱子 树木
			if (datas[wy][wx - 1] == 4 && datas[wy][wx - 2] == 1) {
				return;
			}
			// 人物 箱子 箱子
			if (datas[wy][wx - 1] == 4 && datas[wy][wx - 2] == 4) {
				return;
			}
			// 人物 箱子 目标箱子
			if (datas[wy][wx - 1] == 4 && datas[wy][wx - 2] == 12) {
				return;
			}
			// 人物 目标箱子 树木
			if (datas[wy][wx - 1] == 12 && datas[wy][wx - 2] == 1) {
				return;
			}
			// 人物 目标箱子 箱子
			if (datas[wy][wx - 1] == 12 && datas[wy][wx - 2] == 4) {
				return;
			}
			// 人物 目标箱子 目标箱子
			if (datas[wy][wx - 1] == 12 && datas[wy][wx - 2] == 12) {
				return;
			}
			// ------人坐标 人图片 人显示位置
			// 人物 空地
			// 人物 空目标
			if (datas[wy][wx - 1] == 0 || datas[wy][wx - 1] == 8) {
				wx = wx - 1;
				// 首先要知道人物现在在什么位置
				int x = (int) lab_wolf.getLocation().getX();
				int y = (int) lab_wolf.getLocation().getY();
				// System.out.println(x+"__"+y);
				// 要知道移动一次步子多大
				// 一步移动50，向左移动，x变小，y不变
				lab_wolf.setLocation(x - 50, y);
				return;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 4-0 0-4
			// 人物 箱子 空地
			if (datas[wy][wx - 1] == 4 && datas[wy][wx - 2] == 0) {
				datas[wy][wx - 1] = 0;
				datas[wy][wx - 2] = 4;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 4-0 8-12
			// 人物 箱子 空目标
			if (datas[wy][wx - 1] == 4 && datas[wy][wx - 2] == 8) {
				datas[wy][wx - 1] = 0;
				datas[wy][wx - 2] = 12;
				num++;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 12-8 0-4
			// 人物 目标箱子 空地
			if (datas[wy][wx - 1] == 12 && datas[wy][wx - 2] == 0) {
				datas[wy][wx - 1] = 8;
				datas[wy][wx - 2] = 4;
				num--;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 12-8 8-12
			// 人物 目标箱子 空目标
			if (datas[wy][wx - 1] == 12 && datas[wy][wx - 2] == 8) {
				datas[wy][wx - 1] = 8;
				datas[wy][wx - 2] = 12;
			}
			// 1、获取箱子的对象
			// 2、计算移动的坐标
			sheeps[wy][wx - 1].setLocation(10 + wx * 50 - 100, 40 + wy * 50);
			// 上述代码仅仅修改了显示的效果，实际上组件中的位置并没有发生变化
			sheeps[wy][wx - 2] = sheeps[wy][wx - 1];
			// 将原始位置上的组件清除掉
			sheeps[wy][wx - 1] = null;
			wx = wx - 1;
			// 首先要知道人物现在在什么位置
			int x = (int) lab_wolf.getLocation().getX();
			int y = (int) lab_wolf.getLocation().getY();
			// System.out.println(x+"__"+y);
			// 要知道移动一次步子多大
			// 一步移动50，向左移动，x变小，y不变
			lab_wolf.setLocation(x - 50, y);
			victory();
/*
			// 一定要知道移动的位置前面有没有障碍
			// 如果没有障碍移动
			// 如果有障碍，什么事情都不做
			if (datas[wy][wx - 1] == 1) {
				// 换图片
				Icon i = new ImageIcon("image\\wolf_left.png");
				lab_wolf.setIcon(i);
				// 所谓什么事情都不做就是不让下面的代码执行
				return;
			}
			// 碰撞检测之人物遇到箱子
			if (datas[wy][wx - 1] == 4) {
				// 遇到了箱子
				// 如果遇到了箱子，并且箱子在方向上的后以为可以移动
				// 如果箱子后面的内容是数目或者是箱子，就不让移动了
				if (datas[wy][wx - 2] == 0) {
					// 人物在方向上移动一格，箱子在方向上移动一个
					// 箱子在方向上移动一格
					// 1、箱子的实际数据4的位置要发生变化
					// 当前箱子所在的位置值变成0
					datas[wy][wx - 1] = 0;
					// 移动以后箱子所在位置值变成4
					datas[wy][wx - 2] = 4;
					// 2、箱子的显示位置也要发生变化
					// 所谓箱子模型的移动其实和人物的移动是一样的
					// 就是改变箱子的坐标是使用箱子对象调用改变坐标的方法
					// 只要能够获取到箱子对象和计算出要移动的坐标就OK了
					// 箱子一定要使用要移动的那个箱子
					// 1、获取箱子的对象
					// 2、计算移动的坐标
					sheeps[wy][wx - 1].setLocation(10 + wx * 50 - 100,
							40 + wy * 50);
					// 上述代码仅仅修改了显示的效果，实际上组件中的位置并没有发生变化
					sheeps[wy][wx - 2] = sheeps[wy][wx - 1];
					// 将原始位置上的组件清除掉
					sheeps[wy][wx - 1] = null;
					// -------人物移动代码开始----------
					wx = wx - 1;
					// 首先要知道人物现在在什么位置
					int x = (int) lab_wolf.getLocation().getX();
					int y = (int) lab_wolf.getLocation().getY();
					// System.out.println(x+"__"+y);
					// 要知道移动一次步子多大
					// 一步移动50，向左移动，x变小，y不变
					lab_wolf.setLocation(x - 50, y);
					// 人物移动后进行图片更新
					// 首先要有图片
					Icon i = new ImageIcon("image\\wolf_left.png");
					// 设置显示图片为制定图片
					lab_wolf.setIcon(i);
					// -------人物移动代码结束----------
				} else {
					// 换图片
					Icon i = new ImageIcon("image\\wolf_left.png");
					lab_wolf.setIcon(i);
				}
			}
			// 如果碰到空地
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
		// 按上键
		if (key == 38) {
			// 让人物向上移动
			// 换图片
			Icon i = new ImageIcon("image\\wolf_up.png");
			lab_wolf.setIcon(i);
			// 方向上第一位的坐标是 wy-1 wx
			// 方向上第二位的坐标是 wy-2 wx
			// ------什么都不做
			// 人物 树木
			if (datas[wy - 1][wx] == 1) {
				// 所谓什么事情都不做就是不让下面的代码执行
				return;
			}
			// 人物 箱子 树木
			if (datas[wy - 1][wx] == 4 && datas[wy - 2][wx] == 1) {
				return;
			}
			// 人物 箱子 箱子
			if (datas[wy - 1][wx] == 4 && datas[wy - 2][wx] == 4) {
				return;
			}
			// 人物 箱子 目标箱子
			if (datas[wy - 1][wx] == 4 && datas[wy - 2][wx] == 12) {
				return;
			}
			// 人物 目标箱子 树木
			if (datas[wy - 1][wx] == 12 && datas[wy - 2][wx] == 1) {
				return;
			}
			// 人物 目标箱子 箱子
			if (datas[wy - 1][wx] == 12 && datas[wy - 2][wx] == 4) {
				return;
			}
			// 人物 目标箱子 目标箱子
			if (datas[wy - 1][wx] == 12 && datas[wy - 2][wx] == 12) {
				return;
			}
			// ------人坐标 人图片 人显示位置
			// 人物 空地
			// 人物 空目标
			if (datas[wy - 1][wx] == 0 || datas[wy - 1][wx] == 8) {
				wy = wy - 1;
				// 首先要知道人物现在在什么位置
				int x = (int) lab_wolf.getLocation().getX();
				int y = (int) lab_wolf.getLocation().getY();
				// System.out.println(x+"__"+y);
				// 要知道移动一次步子多大
				// 一步移动50，向右移动，x变大，y不变
				lab_wolf.setLocation(x, y - 50);
				return;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 4-0 0-4
			// 人物 箱子 空地
			if (datas[wy - 1][wx] == 4 && datas[wy - 2][wx] == 0) {
				datas[wy - 1][wx] = 0;
				datas[wy - 2][wx] = 4;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 4-0 8-12
			// 人物 箱子 空目标
			if (datas[wy - 1][wx] == 4 && datas[wy - 2][wx] == 8) {
				datas[wy - 1][wx] = 0;
				datas[wy - 2][wx] = 12;
				num++;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 12-8 0-4
			// 人物 目标箱子 空地
			if (datas[wy - 1][wx] == 12 && datas[wy - 2][wx] == 0) {
				datas[wy - 1][wx] = 8;
				datas[wy - 2][wx] = 4;
				num--;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 12-8 8-12
			// 人物 目标箱子 空目标
			if (datas[wy - 1][wx] == 12 && datas[wy - 2][wx] == 8) {
				datas[wy - 1][wx] = 8;
				datas[wy - 2][wx] = 12;
			}
			// 1、获取箱子的对象
			// 2、计算移动的坐标
			sheeps[wy - 1][wx].setLocation(10 + wx * 50, 40 + wy * 50 - 100);
			// 上述代码仅仅修改了显示的效果，实际上组件中的位置并没有发生变化
			sheeps[wy - 2][wx] = sheeps[wy - 1][wx];
			// 将原始位置上的组件清除掉
			sheeps[wy - 1][wx] = null;
			wy = wy - 1;
			// 首先要知道人物现在在什么位置
			int x = (int) lab_wolf.getLocation().getX();
			int y = (int) lab_wolf.getLocation().getY();
			// System.out.println(x+"__"+y);
			// 要知道移动一次步子多大
			// 一步移动50，向上移动，x不变，y变小
			lab_wolf.setLocation(x, y - 50);	
			victory();
		}
		// 按右键
		if (key == 39) {
			// 让人物向右移动
			// 换图片
			Icon i = new ImageIcon("image\\wolf_right.png");
			lab_wolf.setIcon(i);
			// 方向上第一位的坐标是 wy wx+1
			// 方向上第二位的坐标是 wy wx+2
			// ------什么都不做
			// 人物 树木
			if (datas[wy][wx + 1] == 1) {
				// 所谓什么事情都不做就是不让下面的代码执行
				return;
			}
			// 人物 箱子 树木
			if (datas[wy][wx + 1] == 4 && datas[wy][wx + 2] == 1) {
				return;
			}
			// 人物 箱子 箱子
			if (datas[wy][wx + 1] == 4 && datas[wy][wx + 2] == 4) {
				return;
			}
			// 人物 箱子 目标箱子
			if (datas[wy][wx + 1] == 4 && datas[wy][wx + 2] == 12) {
				return;
			}
			// 人物 目标箱子 树木
			if (datas[wy][wx + 1] == 12 && datas[wy][wx + 2] == 1) {
				return;
			}
			// 人物 目标箱子 箱子
			if (datas[wy][wx + 1] == 12 && datas[wy][wx + 2] == 4) {
				return;
			}
			// 人物 目标箱子 目标箱子
			if (datas[wy][wx + 1] == 12 && datas[wy][wx + 2] == 12) {
				return;
			}
			// ------人坐标 人图片 人显示位置
			// 人物 空地
			// 人物 空目标
			if (datas[wy][wx + 1] == 0 || datas[wy][wx + 1] == 8) {
				wx = wx + 1;
				// 首先要知道人物现在在什么位置
				int x = (int) lab_wolf.getLocation().getX();
				int y = (int) lab_wolf.getLocation().getY();
				// System.out.println(x+"__"+y);
				// 要知道移动一次步子多大
				// 一步移动50，向右移动，x变大，y不变
				lab_wolf.setLocation(x + 50, y);
				return;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 4-0 0-4
			// 人物 箱子 空地
			if (datas[wy][wx + 1] == 4 && datas[wy][wx + 2] == 0) {
				datas[wy][wx + 1] = 0;
				datas[wy][wx + 2] = 4;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 4-0 8-12
			// 人物 箱子 空目标
			if (datas[wy][wx + 1] == 4 && datas[wy][wx + 2] == 8) {
				datas[wy][wx + 1] = 0;
				datas[wy][wx + 2] = 12;
				num++;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 12-8 0-4
			// 人物 目标箱子 空地
			if (datas[wy][wx + 1] == 12 && datas[wy][wx + 2] == 0) {
				datas[wy][wx + 1] = 8;
				datas[wy][wx + 2] = 4;
				num--;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 12-8 8-12
			// 人物 目标箱子 空目标
			if (datas[wy][wx + 1] == 12 && datas[wy][wx + 2] == 8) {
				datas[wy][wx + 1] = 8;
				datas[wy][wx + 2] = 12;
			}
			// 1、获取箱子的对象
			// 2、计算移动的坐标
			sheeps[wy][wx + 1].setLocation(10 + wx * 50 + 100, 40 + wy * 50);
			// 上述代码仅仅修改了显示的效果，实际上组件中的位置并没有发生变化
			sheeps[wy][wx + 2] = sheeps[wy][wx + 1];
			// 将原始位置上的组件清除掉
			sheeps[wy][wx + 1] = null;
			wx = wx + 1;
			// 首先要知道人物现在在什么位置
			int x = (int) lab_wolf.getLocation().getX();
			int y = (int) lab_wolf.getLocation().getY();
			// System.out.println(x+"__"+y);
			// 要知道移动一次步子多大
			// 一步移动50，向右移动，x变大，y不变
			lab_wolf.setLocation(x + 50, y);		
			victory();
		}
		// 按下键
		if (key == 40) {
			// 让人物向下移动
			// 换图片
			Icon i = new ImageIcon("image\\wolf_down.png");
			lab_wolf.setIcon(i);
			// 方向上第一位的坐标是 wy+1 wx
			// 方向上第二位的坐标是 wy+2 wx
			// ------什么都不做
			// 人物 树木
			if (datas[wy + 1][wx] == 1) {
				// 所谓什么事情都不做就是不让下面的代码执行
				return;
			}
			// 人物 箱子 树木
			if (datas[wy + 1][wx] == 4 && datas[wy + 2][wx] == 1) {
				return;
			}
			// 人物 箱子 箱子
			if (datas[wy + 1][wx] == 4 && datas[wy + 2][wx] == 4) {
				return;
			}
			// 人物 箱子 目标箱子
			if (datas[wy + 1][wx] == 4 && datas[wy + 2][wx] == 12) {
				return;
			}
			// 人物 目标箱子 树木
			if (datas[wy + 1][wx] == 12 && datas[wy + 2][wx] == 1) {
				return;
			}
			// 人物 目标箱子 箱子
			if (datas[wy + 1][wx] == 12 && datas[wy + 2][wx] == 4) {
				return;
			}
			// 人物 目标箱子 目标箱子
			if (datas[wy + 1][wx] == 12 && datas[wy + 2][wx] == 12) {
				return;
			}
			// ------人坐标 人图片 人显示位置
			// 人物 空地
			// 人物 空目标
			if (datas[wy + 1][wx] == 0 || datas[wy + 1][wx] == 8) {
				wy = wy + 1;
				// 首先要知道人物现在在什么位置
				int x = (int) lab_wolf.getLocation().getX();
				int y = (int) lab_wolf.getLocation().getY();
				// System.out.println(x+"__"+y);
				// 要知道移动一次步子多大
				// 一步移动50，向右移动，x变大，y不变
				lab_wolf.setLocation(x, y + 50);
				return;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 4-0 0-4
			// 人物 箱子 空地
			if (datas[wy + 1][wx] == 4 && datas[wy + 2][wx] == 0) {
				datas[wy + 1][wx] = 0;
				datas[wy + 2][wx] = 4;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 4-0 8-12
			// 人物 箱子 空目标
			if (datas[wy + 1][wx] == 4 && datas[wy + 2][wx] == 8) {
				datas[wy + 1][wx] = 0;
				datas[wy + 2][wx] = 12;
				num++;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 12-8 0-4
			// 人物 目标箱子 空地
			if (datas[wy + 1][wx] == 12 && datas[wy + 2][wx] == 0) {
				datas[wy + 1][wx] = 8;
				datas[wy + 2][wx] = 4;
				num--;
			}
			// ------人坐标 人图片 人显示位置 箱子位置 箱子JLabel 12-8 8-12
			// 人物 目标箱子 空目标
			if (datas[wy + 1][wx] == 12 && datas[wy + 2][wx] == 8) {
				datas[wy + 1][wx] = 8;
				datas[wy + 2][wx] = 12;
			}
			// 1、获取箱子的对象
			// 2、计算移动的坐标
			sheeps[wy + 1][wx].setLocation(10 + wx * 50, 40 + wy * 50 + 100);
			// 上述代码仅仅修改了显示的效果，实际上组件中的位置并没有发生变化
			sheeps[wy + 2][wx] = sheeps[wy + 1][wx];
			// 将原始位置上的组件清除掉
			sheeps[wy + 1][wx] = null;
			wy = wy + 1;
			// 首先要知道人物现在在什么位置
			int x = (int) lab_wolf.getLocation().getX();
			int y = (int) lab_wolf.getLocation().getY();
			// System.out.println(x+"__"+y);
			// 要知道移动一次步子多大
			// 一步移动50，向上移动，x不变，y变小
			lab_wolf.setLocation(x, y + 50);			
			victory();
		}
	}

	private void victory() {
		if(num==3){
			Frame f =new Frame();
			// 设置窗口的标题
			f.setTitle("succeed");
			// 设置窗口的位置
			f.setLocation(600, 400);
			// 设置窗口尺寸
			f.setSize(400, 200);
			Icon i =new ImageIcon("image\\succeed.png");
			JLabel lab_ss = new JLabel(i);
			f.add(lab_ss);
			// 设置窗口不能最大化
			f.setResizable(false);
			// 让窗体关闭
			f.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			// 设置窗口显示出来
			f.setVisible(true);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
