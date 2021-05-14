package de.wolfgang_bongartz.cellular_automaton.gui;

import de.wolfgang_bongartz.cellular_automaton.automaton.CellularAutomaton;
import de.wolfgang_bongartz.cellular_automaton.automaton.Habitat;
import de.wolfgang_bongartz.cellular_automaton.automaton.PaintHabitatEvent;
import de.wolfgang_bongartz.cellular_automaton.automaton.PaintHabitatEventObserver;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import de.wolfgang_bongartz.cellular_automaton.parser.Parser;


import javax.swing.JLabel;

/**
 * Main-window
 * Containts the main()-method.
 * @author Wolfgang Bongartz
 *
 */
public class MainWindow extends JFrame implements PaintHabitatEventObserver {

	private static final long serialVersionUID = 526846725798144760L;
	private DrawPanel _drawPanel;
	private JTextArea _editorPane;
	private JLabel _lblGenerationCounter;
	private String _filename = "TheTimeMachine.cad";
	protected CellularAutomaton _cellularAutomaton;
	private boolean _keepOnRunning;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		_cellularAutomaton = null;
		_keepOnRunning = false;

		PaintHabitatEvent.AddObserver(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		getContentPane().setLayout(new BorderLayout());

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(380);
		getContentPane().add(splitPane);

		_drawPanel = new DrawPanel();
		splitPane.setRightComponent(_drawPanel);

		JPanel leftPane = new JPanel(new BorderLayout());
		splitPane.setLeftComponent(leftPane);

		_editorPane = new JTextArea();
		_editorPane.setLineWrap(false);
		JScrollPane scrollPane = new JScrollPane(_editorPane);
		leftPane.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		JButton btnCompile = new JButton("Compile");
		btnCompile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					_lblGenerationCounter.setText("Generation 0");
					Parser parser;
					parser = new Parser(_editorPane.getText());
					_cellularAutomaton = parser.ca_definition();
					if(_cellularAutomaton !=null) {
						_drawPanel.setHabitat(_cellularAutomaton.getHabitat());
					} else {
						_drawPanel.setHabitat(null);
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,ex.getMessage(),"Parse Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonPane.add(btnCompile);

		JButton button = new JButton("Start");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!_keepOnRunning) {
					_keepOnRunning = true;
					createNextGeneration();
				}
			}
		});
		buttonPane.add(button);

		JButton button_1 = new JButton("Stop");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_keepOnRunning=false;
			}
		});
		buttonPane.add(button_1);

		JButton button_2 = new JButton("Step");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_keepOnRunning=false;
				createNextGeneration();
			}
		});
		buttonPane.add(button_2);

		_lblGenerationCounter = new JLabel("Generation 0");
		buttonPane.add(_lblGenerationCounter);

		leftPane.add(buttonPane, BorderLayout.SOUTH);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmLoad = new JMenuItem("Load definition...");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadDefinitionFile();
			}
		});
		mnFile.add(mntmLoad);

		JMenuItem mntmSave = new JMenuItem("Save definition...");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveDefinitionFile();
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);

		String s = loadTextFile(_filename);
		if(s!=null) {
			_editorPane.setText(s);
		}
	}

	private void saveDefinitionFile() {
		FileDialog fd = new FileDialog(this, "Save definition file",
				FileDialog.SAVE);
		fd.setDirectory(".");
		fd.setFile(_filename);
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename != null) {
			_filename = filename;
			String s = _editorPane.getText();
			writeTestFile(_filename, s);
		}
	}

	private void writeTestFile(String filename, String content) {
		BufferedWriter writer = null;
		try {
			File logFile = new File(filename);
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write(content);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage(),"Could not write file", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				// Close the writer no matter what...
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	private void loadDefinitionFile() {
		FileDialog fd = new FileDialog(this, "Load definition file",
				FileDialog.LOAD);
		fd.setDirectory(".");
		fd.setFile("*.cad");
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename != null) {
			_filename = filename;
			String s = loadTextFile(_filename);
			_editorPane.setText(s);
		}
	}

	private String loadTextFile(String filename) {
		BufferedReader br = null;
		String retVal = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line + "\n");
				line = br.readLine();
			}
			retVal = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage(),"Could not load file", JOptionPane.ERROR_MESSAGE);
		} finally {
			if (br != null) {
				try {
					// Close the reader no matter what...
					br.close();
				} catch (Exception e) {
				}
			}
		}
		return retVal;
	}

	private void createNextGeneration() {
		if(_cellularAutomaton !=null) {
			Thread t = new Thread(_cellularAutomaton);
			t.start();
		}
	}

	@Override
	/*
	 * 
	 * @see automat.PaintHabitatEventObserver#handleEvent(automat.Habitat)
	 */
	public void handleEvent(Habitat _habitat, int generation) {
		_lblGenerationCounter.setText("Generation " + generation);
		_drawPanel.setHabitat(_cellularAutomaton.getHabitat());
		if(_keepOnRunning) createNextGeneration();
	}	
}
