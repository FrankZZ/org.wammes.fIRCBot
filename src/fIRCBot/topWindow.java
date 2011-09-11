package fIRCBot;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class topWindow extends JFrame implements ActionListener 
{

	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField textField;
	private fIRCBot _bot;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public topWindow( fIRCBot bot ) {
		_bot = bot;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		textField.addActionListener(this);
	}
	public void append( String message )
	{
		textArea.append( message );
		textArea.setCaretPosition( textArea.getDocument( ).getLength( ) );
	}
	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		String text = textField.getText( );
		if( text.length( ) > 0 )
		{
			_bot.rawLine( text );
			textField.selectAll();
		}
	}
}
