package org.sevenhills.liueri19;

public class Table extends JPanel implements ActionListener {
	private final Timer timer = new Timer(17, this);	//approximately 60 fps
	public final int width, height;
	public double velocity = 1;	//in pixels/millisecond? IDK yet...
	//private List<Ball> balls = new ArrayList<Ball>();	may add multiple balls for difficulty (just a thought)
	private Ball ball;
	private int playerLScore = 0;
	private int playerRScore = 0;
	
	public Table() {
		this(800, 600);
		//balls.add(Ball.construct(this, velocity));
		ball = Ball.construct(this, velocity);
		//setup a 800*600 table with a "random" heading ball in the center.
	}
	
	public Table(int width, int height) {
		super(true);
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public static void main(String[] args) {
		Table table = new Table();
		JFrame frame = new JFrame("Pong Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	f.add(table);
        	f.pack();
		f.setVisisble(true);
		timer.start();
	}
	
	public void resetBall() {
		//set to center
		ball.setCoordinate(width / 2, height / 2);
	}
	
	//invoked on every repaint()
	@Override
	public void paintComponent(Graphics g) {
		//paint table
		
		//paint ball
		
		//paint paddles
		
		//paint scores
		
	}
	
	//will be invoked by the timer with 20 milliseconds intervals
	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
	}
	
	//accessors & mutators
	public void playerLScore() {
		playerLScore++;
		resetBall();
	}
	
	public int getPlayerLScore() {
		return playerLScore;
	}
	
	public void playerRScore() {
		playerRScore++;
		resetBall();
	}
	
	public int getPlayerRScore() {
		return playerRScore;
	}
}
