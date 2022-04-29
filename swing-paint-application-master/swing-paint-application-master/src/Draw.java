import Factory.ButtonFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Draw {

	Canvas canvas;
	Color color = Color.WHITE;
	Dimension size = new Dimension(40,40);
	ButtonFactory buttonFactory = new ButtonFactory();
	JButton clearButton, blackButton, blueButton, greenButton, redButton,
			colorPicker, magentaButton, grayButton, orangeButton, yellowButton,
			pinkButton, cyanButton, lightGrayButton, saveButton, loadButton,
			saveAsButton, rectangle, pencil, undoButton, redoButton;
	private JFileChooser fileChooser;

	private File file;
	private Icon save = new ImageIcon(getClass().getResource("save.png"));
	private Icon undo = new ImageIcon(getClass().getResource("undo.png"));
	private Icon redo = new ImageIcon(getClass().getResource("redo.png"));
	private Icon pencilIcon = new ImageIcon(getClass()
			.getResource("pencil.png"));
	private Icon rect = new ImageIcon(getClass().getResource("rect.png"));
	private int saveCounter = 0;
	private JLabel filenameBar, thicknessStat;
	private JSlider thicknessSlider;
	private int width, height;
	ChangeListener thick = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			thicknessStat.setText(String.format("%s",
					thicknessSlider.getValue()));
			canvas.setThickness(thicknessSlider.getValue());
		}
	};


	public void setWH(int width,int height){
		this.width = width;
		this.height = height;
	}
	public void openPaint() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(info.getName())) {
	            try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            break;
	        }
	    }
		JFrame frame = new JFrame("Paint ("+ width +"X" + height +")");
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		canvas = new Canvas();

		container.add(canvas, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		Box box = Box.createVerticalBox();
		Box box1 = Box.createHorizontalBox();

		panel1.setLayout(new FlowLayout());
		new ButtonDecorator(clearButton = new JButton("clear"));
		new ButtonDecorator(colorPicker = new JButton("Color Picker"));
		new ButtonDecorator(loadButton = new JButton("Load"));
		new ButtonDecorator(saveAsButton = new JButton("Save As"));

		pencil = new JButton(pencilIcon);
		pencil.setPreferredSize(new Dimension(40, 40));

		rectangle = new JButton(rect);
		rectangle.setPreferredSize(new Dimension(40, 40));

		thicknessSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 1);
		thicknessSlider.setMajorTickSpacing(25);
		thicknessSlider.setPaintTicks(true);
		thicknessSlider.setPreferredSize(new Dimension(40, 40));
		thicknessSlider.addChangeListener(thick);
		undoButton = new JButton(undo);
		undoButton.setPreferredSize(new Dimension(20, 20));

		redoButton = new JButton(redo);
		redoButton.setPreferredSize(new Dimension(20, 20));

		blackButton = buttonFactory.getButton("ColorButton",size, Color.BLACK);

		blueButton = buttonFactory.getButton("ColorButton",size, Color.BLUE);

		greenButton = buttonFactory.getButton("ColorButton", size, Color.GREEN);

		redButton = buttonFactory.getButton("ColorButton", size, Color.RED);

		grayButton = buttonFactory.getButton("ColorButton", size, Color.GRAY);

		magentaButton = buttonFactory.getButton("ColorButton", size, Color.MAGENTA);

		orangeButton = buttonFactory.getButton("ColorButton", size, Color.ORANGE);

		yellowButton = buttonFactory.getButton("ColorButton", size, Color.YELLOW);

		pinkButton = buttonFactory.getButton("ColorButton", size, Color.PINK);

		cyanButton = buttonFactory.getButton("ColorButton", size, Color.CYAN);

		lightGrayButton = buttonFactory.getButton("ColorButton", size, Color.lightGray);


		saveButton = new JButton(save);



	//new ButtonDecorator (clearButton = new JButton("Clear "));


		filenameBar = new JLabel("No file");
		thicknessStat = new JLabel("1");

		box.add(Box.createVerticalStrut(40));
		box1.add(thicknessSlider, BorderLayout.NORTH);
		box1.add(thicknessStat, BorderLayout.NORTH);
		box.add(box1, BorderLayout.NORTH);
		panel1.add(filenameBar, BorderLayout.SOUTH);
		box.add(Box.createVerticalStrut(20));
		box.add(undoButton, BorderLayout.NORTH);
		box.add(Box.createVerticalStrut(5));
		box.add(redoButton, BorderLayout.NORTH);
		box.add(Box.createVerticalStrut(5));
		box.add(pencil, BorderLayout.NORTH);
		box.add(Box.createVerticalStrut(5));
		box.add(rectangle, BorderLayout.NORTH);

		blackButton.addActionListener(e -> executeCommand(new ColorCommand(Color.BLACK, canvas)));
		blueButton.addActionListener(e -> executeCommand(new ColorCommand(Color.blue, canvas)));
		greenButton.addActionListener(e -> executeCommand(new ColorCommand(Color.green, canvas)));
		redButton.addActionListener(e -> executeCommand(new ColorCommand(Color.red, canvas)));
		magentaButton.addActionListener(e -> executeCommand(new ColorCommand(Color.magenta, canvas)));
		grayButton.addActionListener(e -> executeCommand(new ColorCommand(Color.gray, canvas)));
		orangeButton.addActionListener(e -> executeCommand(new ColorCommand(Color.orange, canvas)));
		yellowButton.addActionListener(e -> executeCommand(new ColorCommand(Color.yellow, canvas)));
		pinkButton.addActionListener(e -> executeCommand(new ColorCommand(Color.pink, canvas)));
		cyanButton.addActionListener(e -> executeCommand(new ColorCommand(Color.cyan, canvas)));
		lightGrayButton.addActionListener(e -> executeCommand(new ColorCommand(Color.lightGray, canvas)));

		clearButton.addActionListener(e-> executeCommand(new ClearCommand(canvas)));
		undoButton.addActionListener(e-> executeCommand(new UndoCommand(canvas)));
		redoButton.addActionListener(e-> executeCommand(new RedoCommand(canvas)));
		rectangle.addActionListener(e-> executeCommand(new RectangleCommand(canvas)));
		pencil.addActionListener(e-> executeCommand(new PencilCommand(canvas)));
		loadButton.addActionListener(e->executeCommand(new LoadCommand(canvas, fileChooser, loadButton, file, filenameBar)));
		saveButton.addActionListener(e->executeCommand(new SaveCommand(canvas, fileChooser, file, filenameBar, saveCounter, saveButton)));
		saveAsButton.addActionListener(e->executeCommand(new SaveAsCommand(canvas, saveCounter, fileChooser, file, filenameBar, saveAsButton)));
		colorPicker.addActionListener(e->executeCommand(new ColorPickCommand(canvas, color)));

		panel.add(greenButton);
		panel.add(blueButton);
		panel.add(blackButton);
		panel.add(redButton);
		panel.add(magentaButton);
		panel.add(grayButton);
		panel.add(orangeButton);
		panel.add(yellowButton);
		panel.add(pinkButton);
		panel.add(cyanButton);
		panel.add(lightGrayButton);
		panel.add(saveButton);
		panel.add(saveAsButton);
		panel.add(loadButton);
		panel.add(colorPicker);
		panel.add(clearButton);

		container.add(panel, BorderLayout.NORTH);
		container.add(panel1, BorderLayout.SOUTH);
		container.add(box, BorderLayout.WEST);

		frame.setVisible(true);

		frame.setSize(width+79,height+11);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	private void executeCommand(Command command){

		command.Execute();
	}

}

class ColorCommand implements Command{
	private Color color;
	private Canvas canvas;
	ColorCommand(Color color, Canvas canvas){this.color = color;this.canvas = canvas;}

	public void Execute() {
		canvas.color(color);
	}
}
class RectangleCommand implements Command{
	private Canvas canvas;
	RectangleCommand(Canvas canvas){this.canvas = canvas;}

	@Override
	public void Execute() {
	canvas.rect();
	}
}
class ClearCommand implements Command{
	private Canvas canvas;
	ClearCommand(Canvas canvas){this.canvas = canvas;}

	@Override
	public void Execute() {
		canvas.clear();
	}
}
class UndoCommand implements Command{
	private Canvas canvas;
	UndoCommand(Canvas canvas){this.canvas = canvas;}

	@Override
	public void Execute() {
		canvas.undo();
	}
}
class RedoCommand implements Command{
	private Canvas canvas;
	RedoCommand(Canvas canvas){this.canvas = canvas;}

	@Override
	public void Execute() {
		canvas.redo();
	}
}
class PencilCommand implements Command{
	private Canvas canvas;
	PencilCommand(Canvas canvas){this.canvas = canvas;}

	@Override
	public void Execute() {
		canvas.pencil();
	}
}
class LoadCommand implements Command{
	private Canvas canvas;
	private JFileChooser fileChooser;
	private JButton loadButton;
	private File file;
	private JLabel filenameBar;
	LoadCommand(Canvas canvas, JFileChooser fileChooser, JButton loadButton, File file, JLabel filenameBar){this.canvas = canvas; this.fileChooser = fileChooser; this.loadButton = loadButton; this.file = file; this.filenameBar = filenameBar;}

	public void Execute(){
		fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(loadButton) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			filenameBar.setText(file.toString());
			canvas.load(file);
		}
	}
}
class SaveCommand implements Command {
	private Canvas canvas;
	private JFileChooser fileChooser;
	private File file;
	private JLabel filenameBar;
	private int saveCounter;
	private JButton saveButton;
	SaveCommand(Canvas canvas, JFileChooser filechooser, File file, JLabel fileNameBar, int saveCounter, JButton saveButton) {this.canvas = canvas; this.fileChooser = filechooser; this.filenameBar = fileNameBar; this.saveCounter = saveCounter; this.saveButton = saveButton;}

	public void Execute() {
		if (saveCounter == 0) {
			fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(saveButton) == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				saveCounter = 1;
				filenameBar.setText(file.toString());
				canvas.save(file);
			}
		} else {
			filenameBar.setText(file.toString());
			canvas.save(file);
		}
	}
}
class SaveAsCommand implements Command{
	private Canvas canvas;
	private int saveCounter;
	private JFileChooser fileChooser;
	private File file;
	private JLabel filenameBar;
	private JButton saveAsButton;
	SaveAsCommand(Canvas canvas, int saveCounter, JFileChooser fileChooser, File file, JLabel filenameBar, JButton saveAsButton){this.canvas = canvas;this.saveCounter = saveCounter; this.fileChooser = fileChooser;this.file = file; this.filenameBar = filenameBar;this.saveAsButton = saveAsButton;}

	@Override
	public void Execute() {
		saveCounter = 1;
		fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(saveAsButton) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			filenameBar.setText(file.toString());
			canvas.save(file);
		}

	}
}
class ColorPickCommand implements Command{
	private Canvas canvas;
	private Color color;
	ColorPickCommand(Canvas canvas , Color color){this.canvas = canvas;this.color = color;}

	@Override
	public void Execute() {
		color = JColorChooser.showDialog(null, "Pick your color!",
				color);
		if (color == null)
			color = (Color.WHITE);
		canvas.picker(color);
	}
}