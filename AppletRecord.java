package capture2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class AppletRecord {
	private JFrame window = null ;
	private JButton button1 ;
	private String button1Str = "record" ;

	public AppletRecord() throws LineUnavailableException{
		window = new JFrame("Recorder") ;

		JPanel BuPanel = new JPanel() ;
		button1 = new JButton( button1Str ) ;
		button1.addActionListener( new BuListener1() ) ;
		BuPanel.add( button1 ) ;
		BuPanel.setSize( 300, 50 ) ;
		window.add( BuPanel, BorderLayout.CENTER) ;
		window.addWindowListener(
	 			new WindowAdapter(){
	 				@Override
	 				public void windowClosing(WindowEvent we){
	 					System.out.println("complete.") ;

	 					System.exit(77) ;
	 				}
	 			}
	 	);
	 	window.pack() ;
	 	window.setSize(200,60);
	 	window.setVisible(true) ;
	}

	public JFrame getWindow(){
		return this.window;
	}

	static class BuListener1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			try {
	            /* Register jNativeHook */
	            GlobalScreen.registerNativeHook();
	        } catch (NativeHookException ex) {
	            /* Its error */
	            System.err.println("There was a problem registering the native hook.");
	            System.err.println(ex.getMessage());
	            System.exit(1);
	        }

	        /* Construct the example object and initialze native hook. */
	        GlobalScreen.addNativeKeyListener(new JNativeHookCapture());
	        Component c = (Component)e.getSource();
	        Window w = SwingUtilities.getWindowAncestor(c);
	        w.dispose();
	        JFrame frame = new JFrame("Recording");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setSize(400, 50);
	        frame.setVisible(true);

	        JLabel label = new JLabel("Recording.If you want to end recording, press the ESC key.");
	        //frame.getContentPane().add(label);

	        Container frame2 = frame.getContentPane();
	        frame2.add(label);

	        frame.addWindowListener(
		 			new WindowAdapter(){
		 				public void windowClosing(WindowEvent we){
		 					System.out.println("complete.") ;
		 					System.exit(77) ;
		 				}
		 			}
		 	);
		}

	}
	public static void main(String[] args) throws LineUnavailableException{
		new AppletRecord() ;
	}
}
