import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


@SuppressWarnings("serial")
public class categorySelection extends JPanel 
{
	protected AbstractButton categoryTitle;
	public categorySelection()
	{
//	final RoundedPanel categorySelectionPanel = whiteRoundedPanel();
		JPanel categorySelectionPanel = new JPanel();
		categorySelectionPanel.setPreferredSize(new Dimension(175, 300));
		JLabel testText = new JLabel("MENU SELECTION");
		
		ArrayList<String> categorySelection = new ArrayList<String>();
	categorySelection = MSAccessDatabase.getCategorySelections();
   	    	    	    		 
	final JButton categorySelectionButton[] = new JButton[categorySelection.size()];
		 
	JPanel menuSelectionButtons = new JPanel(new GridLayout(categorySelection.size(), 1, 7, 7));
    menuSelectionButtons.setOpaque(false);
    
    for(int i = 0; i < categorySelection.size(); i++ )
    {
    	categorySelectionButton[i] = new JButton(categorySelection.get(i));

    	categorySelectionButton[i].addActionListener(new ActionListener() 
    	{
    
			private String itemCategory;

			public void actionPerformed(ActionEvent e) 
    		{
    	   		int index = -1;
    	   		
    	   		for(int i = 0; i < categorySelectionButton.length; i++)
    	   		{
    	   			if (e.getSource() == categorySelectionButton[i]) { 
    	   	            index = i; 
    	   	            
    	   	            break; 
    	   	        } 
    	   		}
    	   		
    	   		if(index != -1)
    	   		{
    	   			System.out.println("Title = " + categorySelectionButton[index].getText());
    	   			String itemCategory = categorySelectionButton[index].getText();
    	   		//	displaySelectionPanel(itemCategory);
    	   			categoryTitle.setText(itemCategory);
    	   			ItemInventory.setItemCategory(categorySelectionButton[index].getText());
    	   //			getItemSelection(itemCategory, index);
    	   		//	JPanel itemSelections = new JPanel();
    	   			System.out.println("After Button Text = " + ItemInventory.getItemCategory());
    	          //  JPanel itemSelections = getItemSelection(itemCategory);
    	            
    	  //         itemSelectionButtons.add(itemSelections);
    	 //           itemSelectionPanel.add(itemSelections);
    	    //        serverPanel.add(itemSelectionButtons, BorderLayout.CENTER);
    	   		}
    	   
    	  // 		sandwichSelectionBox.add(getItemSelection(itemCategory));
//    	   	 itemSelectionButtons.add(itemSelections);
	//            itemSelectionPanel.add(itemSelection);
	   //         serverPanel.add(itemSelectionButtons, BorderLayout.CENTER);
   // 	    currentCard = 4;     			
	//			scl.show(selectionPanels, "" + (currentCard));
				
    		}


				
			
    	});
       System.out.println("After Button Text = " + ItemInventory.getItemCategory());
       menuSelectionButtons.add(categorySelectionButton[i]);

    }	    	        	
    
	    Box categorySelectionBox = Box.createVerticalBox();
	    testText.setAlignmentX(Box.CENTER_ALIGNMENT);
	    menuSelectionButtons.setAlignmentX(Box.CENTER_ALIGNMENT);
	    categorySelectionBox.add(Box.createRigidArea(new Dimension(0,10)));
	    categorySelectionBox.add(testText);
	    categorySelectionBox.add(Box.createRigidArea(new Dimension(0,20)));
	    categorySelectionBox.add(menuSelectionButtons);
	    
		categorySelectionPanel.add(categorySelectionBox);
	//	serverPanel.add(categorySelectionPanel, BorderLayout.WEST);
	}


}
