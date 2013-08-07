import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TestPanelArray extends JFrame
{
	public TestPanelArray()
	{
		super("TestArray");
		
		
		// JFrame Characteristics
		setSize(500, 500); 			// Sets JFrame Size
	//	setLayout(null);				// Sets JFrame Layout
		setResizable(false);
		
		String itemCategory = "Breakfast";
		
	final JPanel mainPanel = new JPanel(new BorderLayout());
		
		
		
		JPanel testPanel = new JPanel();

		ArrayList<String> itemSelection = new ArrayList<String>();
		itemSelection = MSAccessDatabase.getItemSelections(itemCategory);

		final JButton itemSelectionButton[] = new JButton[itemSelection.size()];

		for (int i = 0; i < itemSelection.size(); i++) {
			itemSelectionButton[i] = new JButton(itemSelection.get(i));
			
			itemSelectionButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = -1;

					for (int i = 0; i < itemSelectionButton.length; i++) {
						if (e.getSource() == itemSelectionButton[i]) {
							index = i;

							break;
						}
					}

					if (index != -1) {

						Boolean itemOrderedFound = MSAccessDatabase.getItem("Sau Biscuit");

						if (itemOrderedFound) {
							JLabel itemFound = new JLabel("I T E M    F O U N D");
							mainPanel.add(itemFound, BorderLayout.EAST);
						}

					//	receiptPanel.validate();
						repaint();

					}

				}
			});
			testPanel.add(itemSelectionButton[i]);
		}
		
		
		JLabel testText = new JLabel("Receipt");
		mainPanel.add(testText, BorderLayout.EAST);
		
		mainPanel.add(testPanel, BorderLayout.CENTER);
		add(mainPanel);
		((JComponent) getContentPane()).setOpaque(false);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new TestPanelArray();
	}
}