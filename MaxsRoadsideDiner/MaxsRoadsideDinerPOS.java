
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

//import Calculator.NumericListener;

@SuppressWarnings("serial")
public class MaxsRoadsideDinerPOS extends JFrame implements ActionListener {
	Border loginBdr = BorderFactory.createEmptyBorder(25, 15, 25, 15);
	Color vividTangelo = new Color(255, 84, 0);
	String[] employeeType = { " ", "Admin", "Server" };
	CardLayout cl = new CardLayout();
//	CardLayout scl = new CardLayout();
	int currentCard = 1;

	JLabel breakfastTitle;

	private JPanel serverPanel;
	private JPanel receiptPanel;
	// private JPanel selectionPanels;

	protected String itemDescription;
	protected Component receiptBox;

	DecimalFormat df = new DecimalFormat("$#0.00");

	protected Component sandwichSelectionBox;
	private Object[] categorySelectionButton;
	JLabel sandwichTitle;
	JPanel itemSelectionPanel;
	JLabel categoryTitle;
	private AbstractButton receiptTestText;
	

//	private ArrayList<Food> orderedFoods = new ArrayList<Food>();

	public MaxsRoadsideDinerPOS() {
		super("Max's Roadside Diner");  // GUI Name

		// JFrame Characteristics
		setSize(1370, 728);  			// Sets JFrame Size
		setLayout(null);				// Sets JFrame Layout
		setResizable(false);			// Sets JFrame not resizable

		// Background Image on Layered Pane
		ImageIcon roadsideDinerBackground = new ImageIcon("1950s-Diner-2.jpg");					// Creates ImageIcon for Graphic Background 
		JLabel roadsideDinerBackgroundLabel = new JLabel(roadsideDinerBackground);				// Creates Graphic Background as JLabel
		getLayeredPane().add(roadsideDinerBackgroundLabel, new Integer(Integer.MIN_VALUE));		// Adds Graphic Background JLabel to JFrame's LayeredPane
		roadsideDinerBackgroundLabel.setBounds(0, 0, roadsideDinerBackground.getIconWidth(), 
				roadsideDinerBackground.getIconHeight());										// Sets the bounds of Graphic JLabel

		/**
		 *	SCREEN #1 - Login Screen Panel
		 */

		final RoundedPanel loginScreenPanel = whiteRoundedPanel();  	// Login Screen White Rounded Background Panel
		loginScreenPanel.setSize(315, 235);								// Sets the size of Login Screen White Background Panel
		loginScreenPanel.setLocation(525, 200);							// Sets the location of Login Screen White Background Panel on JFrame

		final JPanel loginUserPasswordPanel = new JPanel();  			// Card Panel for Login User Password Panel
		loginUserPasswordPanel.setLayout(cl);							// Sets the Login User Password Panel to CardLayout
		loginUserPasswordPanel.setOpaque(false);						// Sets the LoginUserPasswordPanel's background invisible	

		JLabel titleLabel = new JLabel("MAX's Roadside Diner", JLabel.CENTER);	// Creates and centers JLabel for Title of Diner
		titleLabel.setForeground(vividTangelo);									// Sets the color of the Title
		titleLabel.setFont(new Font("Serif", Font.BOLD, 25));					// Sets the font for Title

		JLabel loginUserLabel = new JLabel("User:  ", JLabel.RIGHT);		// Creates and right justifies User JLabel 

		final JComboBox comboEmployeeType = new JComboBox(employeeType);	// Creates User JComboBox (Login drop down box)
																			// using employeeType (an Array)
		
		comboEmployeeType.addActionListener(new ActionListener() {			// JComboBox ActionListener
			public void actionPerformed(ActionEvent e) {					// Stores action performed in ActionEvent
				comboEmployeeType.setSelectedItem(e);						// Sets the selected item of JComboBox
			}
		});

		JLabel loginPasswordLabel = new JLabel("Password:  ", JLabel.RIGHT);	// Creates and right justifies Password JLabel

		final JTextField loginPassword = new JTextField(8);						// Creates JTextField for Password; accepts first 8 characters
		loginPassword.setHorizontalAlignment(JTextField.CENTER);				// Centers the text user enters for Password

		JPanel userPasswordPanel = new JPanel(new GridLayout(2, 2, 3, 3));		// Creates JPanel with GridLayout to hold 
																				// JLabels and JTextfields for User ID and
																				// Password
		
		userPasswordPanel.setOpaque(false);										// Sets userPasswordPanel to invisible								
		userPasswordPanel.add(loginUserLabel);									// Adds User Label to userPasswordPanel
		userPasswordPanel.add(comboEmployeeType);
		userPasswordPanel.add(loginPasswordLabel);
		userPasswordPanel.add(loginPassword);

		JButton loginButton = new JButton("Login");								// Creates Login JButton
		loginButton.setFont(new Font("Serif", Font.BOLD, 14));

		loginButton.addActionListener(new ActionListener() {					// ActionListener for Login Button
			public void actionPerformed(ActionEvent e) {

				boolean validUser = false;										// Initializes validUser (User Not Valid)

				String userId = (String) comboEmployeeType.getSelectedItem();	// Gets UserID from JComboBox
				System.out.println(userId);
				String userPassword = loginPassword.getText();					// Gets Password from JTextField
				System.out.println(userPassword);

				try {
					
					validUser = MSAccessDatabase.authenticateUser(userId, userPassword);	//Checks Database for valid user	
					System.out.println(validUser);

					if (validUser) {											//Check Database return of valid user	
						
						if (userId == "Server") {								
							System.out.println("Did we make it here?");

							remove(loginScreenPanel);							//Removes login screen panel from JFrame

							JPanel serverPanel = new JPanel();
							serverPanel.setLayout(new BorderLayout(20, 20));
							serverPanel.setSize(600, 275);
							serverPanel.setLocation(325, 200);
							serverPanel.setOpaque(false);

							JTabbedPane itemSelectionPanel = new JTabbedPane();		// Creates JTabbedPane for Item Selections

							ArrayList<String> categorySelection = new ArrayList<String>();	//Creates ArrayList for categories
							categorySelection = MSAccessDatabase.getCategorySelections();	//Gets Categories from Database

							for (int i = 0; i < categorySelection.size(); i++) {		//For Loop to create JTabbedPane
								
								String cat = categorySelection.get(i);					//Gets Category from ArrayList

								JPanel[] catPanel = new JPanel[categorySelection.size()];	//Creates Array of JPanels to hold
																							//item selections
								catPanel[i] = getItemSelection(cat);						//
								itemSelectionPanel.addTab(categorySelection.get(i), catPanel[i]);
							}

							final RoundedPanel receiptPanel = whiteRoundedPanel();
							receiptPanel.setPreferredSize(new Dimension(250, 300));
							JLabel receiptTestText = new JLabel(
									"            RECEIPT            ");

							final Box receiptBox = Box.createVerticalBox();
							receiptTestText.setAlignmentX(Box.CENTER_ALIGNMENT);
							receiptBox.add(receiptTestText);
							receiptBox.add(Box.createRigidArea(new Dimension(0,
									10)));

							receiptPanel.add(receiptBox);

							serverPanel.add(itemSelectionPanel, BorderLayout.CENTER);
							serverPanel.add(receiptPanel, BorderLayout.EAST);
							add(serverPanel);
							serverPanel.setVisible(true);
							validate();
							repaint();
						
						} else if (userId == "Admin") {
							// display Server Panel

							remove(loginScreenPanel);

							// RoundedPanel adminPanel = whiteRoundedPanel();
							JPanel itemCatSelectionPanel = new JPanel();
							itemCatSelectionPanel.setLayout(new BorderLayout(
									20, 20));
							itemCatSelectionPanel.setSize(600, 275);
							itemCatSelectionPanel.setLocation(325, 200);
							itemCatSelectionPanel.setOpaque(false);

							JTabbedPane serverPanel = new JTabbedPane();

							JPanel addEmployeePanel = createEmployeePanel();
							serverPanel
									.addTab("Add Employee", addEmployeePanel);

							JPanel addInventoryPanel = createInventoryPanel();
							serverPanel.addTab("Inventory", addInventoryPanel);

							final RoundedPanel receiptPanel = whiteRoundedPanel();
							receiptPanel.setPreferredSize(new Dimension(250,
									300));
							JLabel receiptTestText = new JLabel(
									"            RECEIPT            ");

							final Box receiptBox = Box.createVerticalBox();
							receiptTestText.setAlignmentX(Box.CENTER_ALIGNMENT);
							receiptBox.add(receiptTestText);
							receiptBox.add(Box.createRigidArea(new Dimension(0,
									10)));

							receiptPanel.add(receiptBox);

							itemCatSelectionPanel.add(serverPanel,
									BorderLayout.CENTER);
							// administrationPanel.add(adminPanel,
							// BorderLayout.CENTER);
							// adminPanel.add(receiptPanel, BorderLayout.EAST);
							add(itemCatSelectionPanel);
							itemCatSelectionPanel.setVisible(true);
							validate();
							repaint();
							// display Admin Panel

						}
					} else {
						// display error message
						currentCard = 2;
						cl.show(loginUserPasswordPanel, "" + (currentCard));

						javax.swing.Timer timer = new javax.swing.Timer(1000,
								new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										currentCard = 1;
										cl.show(loginUserPasswordPanel, ""
												+ (currentCard));
										comboEmployeeType.setSelectedItem(" ");
										loginPassword.setText("");
									}
								});

						timer.setRepeats(false);
						timer.setInitialDelay(2500);
						timer.start();
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				}

				
				
			}

			private JPanel createInventoryPanel() {
				// TODO Auto-generated method stub

				JPanel createInvPanel = new JPanel();

				JPanel inventoryPanel = new JPanel(new BorderLayout());

				JPanel newInventoryPanel = new JPanel(
						new GridLayout(5, 2, 7, 7));

				JLabel itemNumberLabel = new JLabel("Item Number:  ");

				final JTextField itemNumber = new JTextField(6);

				JLabel categoryLabel = new JLabel("Menu Category:  ");

				final JTextField category = new JTextField(10);

				JLabel itemNameLabel = new JLabel("Item Name:  ");
				final JTextField itemName = new JTextField(15);

				JLabel itemPriceLabel = new JLabel("Item Price:  ");
				final JTextField itemPrice = new JTextField(15);

				JLabel itemQauntityLabel = new JLabel("Item Quantity:  ");
				final JTextField itemQauntity = new JTextField(15);

				/*
				 * JButton submitButton = new JButton("Submit");
				 * 
				 * submitButton.addActionListener(new ActionListener() { private
				 * String addUserID; private String addPassword;
				 * 
				 * public void actionPerformed(ActionEvent ex) {
				 * 
				 * Employee addEmployee = new Employee();
				 * 
				 * addUserID = userID.getText(); addPassword =
				 * password.getText();
				 * 
				 * addEmployee.setEmployeeUserID(addUserID);
				 * addEmployee.setEmployeePassword(addPassword);
				 * 
				 * MSAccessDatabase.addEmployee();
				 * 
				 * }});
				 */

				newInventoryPanel.add(itemNumberLabel);
				newInventoryPanel.add(itemNumber);
				newInventoryPanel.add(categoryLabel);
				newInventoryPanel.add(category);
				newInventoryPanel.add(itemNameLabel);
				newInventoryPanel.add(itemName);
				newInventoryPanel.add(itemPriceLabel);
				newInventoryPanel.add(itemPrice);
				newInventoryPanel.add(itemQauntityLabel);
				newInventoryPanel.add(itemQauntity);

				inventoryPanel.add(newInventoryPanel, BorderLayout.CENTER);

				createInvPanel.add(inventoryPanel);

				return createInvPanel;
			}

			private JPanel createEmployeePanel() {
				// TODO Auto-generated method stub

				JPanel createEmpPanel = new JPanel();

				JPanel newEmployeePanel = new JPanel(new BorderLayout());

				JPanel employeePanel = new JPanel(new GridLayout(2, 2, 7, 7));

				JLabel employeeUserID = new JLabel("Employee UserId:  ");

				final JTextField userID = new JTextField(6);

				JLabel employeePassword = new JLabel("Employee Password:  ");

				final JTextField password = new JTextField(8);

				JButton submitButton = new JButton("Submit");

				submitButton.addActionListener(new ActionListener() {
					private String addUserID;
					private String addPassword;

					public void actionPerformed(ActionEvent ex) {

						Employee addEmployee = new Employee();

						addUserID = userID.getText();
						addPassword = password.getText();

						addEmployee.setEmployeeUserID(addUserID);
						addEmployee.setEmployeePassword(addPassword);

						MSAccessDatabase.addEmployee();

					}
				});

				employeePanel.add(employeeUserID);
				employeePanel.add(userID);
				employeePanel.add(employeePassword);
				employeePanel.add(password);

				newEmployeePanel.add(employeePanel, BorderLayout.CENTER);
				newEmployeePanel.add(submitButton, BorderLayout.SOUTH);

				createEmpPanel.add(newEmployeePanel);

				return createEmpPanel;
			}
		});

		Box userPasswordBox = Box.createHorizontalBox();
		userPasswordPanel.setAlignmentY(Box.CENTER_ALIGNMENT);
		userPasswordBox.add(userPasswordPanel);
		userPasswordBox.add(Box.createRigidArea(new Dimension(50, 0)));

		Box loginBox = Box.createVerticalBox();
		titleLabel.setAlignmentX(Box.CENTER_ALIGNMENT);
		userPasswordBox.setAlignmentX(Box.CENTER_ALIGNMENT);
		loginButton.setAlignmentX(Box.CENTER_ALIGNMENT);
		loginBox.add(Box.createRigidArea(new Dimension(0, 10)));
		loginBox.add(titleLabel);
		loginBox.add(Box.createRigidArea(new Dimension(0, 20)));
		loginBox.add(userPasswordBox);
		loginBox.add(Box.createRigidArea(new Dimension(0, 20)));
		loginBox.add(loginButton);

		/**
		 * SCREEN #2 - Invalid UserId/Password
		 */
		JPanel invalidUserPanel = new JPanel();
		JLabel invalidUserMsg = new JLabel("INVALID USER/PASSWORD",
				JLabel.CENTER);
		invalidUserMsg.setFont(new Font("Serif", Font.BOLD, 16));
		invalidUserMsg.setForeground(vividTangelo);
		JLabel invalidUserMsg2 = new JLabel("Please Try Again", JLabel.CENTER);
		invalidUserMsg2.setFont(new Font("Serif", Font.BOLD, 14));

		Box invalidUserBox = Box.createVerticalBox();
		invalidUserMsg.setAlignmentX(Box.CENTER_ALIGNMENT);
		invalidUserMsg2.setAlignmentX(Box.CENTER_ALIGNMENT);
		invalidUserBox.add(Box.createRigidArea(new Dimension(0, 50)));
		invalidUserBox.add(invalidUserMsg);
		invalidUserBox.add(invalidUserMsg2);

		invalidUserPanel.add(invalidUserBox);

		/**
		 * RECEIPT PANEL
		 */
/*
		final RoundedPanel receiptPanel = whiteRoundedPanel();
		receiptPanel.setPreferredSize(new Dimension(250, 300));
		JLabel receiptTestText = new JLabel("            RECEIPT            ");

		final Box receiptBox = Box.createVerticalBox();
		receiptTestText.setAlignmentX(Box.CENTER_ALIGNMENT);
		receiptBox.add(receiptTestText);
		receiptBox.add(Box.createRigidArea(new Dimension(0, 10)));

		receiptPanel.add(receiptBox);
*/
		/**
		 * SCREEN #3 - Server Menu Order Panel
		 */
		// serverPanel = new JPanel(new BorderLayout(10, 10));
		// serverPanel.setSize(1000, 525);
		// serverPanel.setLocation(185, 50);
		// serverPanel.setOpaque(false);

		/**
		 * SCREEN #3a - Server Menu Title Panel (NORTH)
		 */
		RoundedPanel serverTitlePanel = whiteRoundedPanel();

		JLabel serverTitleText = new JLabel("MAC's Roadside Diner",
				JLabel.CENTER);
		serverTitleText.setForeground(vividTangelo);
		serverTitleText.setFont(new Font("Serif", Font.BOLD, 25));

		Box titleBox = Box.createVerticalBox();
		serverTitleText.setAlignmentX(Box.CENTER_ALIGNMENT);
		titleBox.add(serverTitleText);
		titleBox.add(Box.createRigidArea(new Dimension(0, 10)));

		serverTitlePanel.add(titleBox);
		// serverPanel.add(serverTitlePanel, BorderLayout.NORTH);

		final JLabel categoryTitle = new JLabel(" ");
		// System.out.println("SandwichTitle = " +
		// ItemInventory.getItemCategory());
		categoryTitle.setForeground(vividTangelo);
		categoryTitle.setFont(new Font("Serif", Font.BOLD, 23));

		/**
		 * SCREEN 3b - CATEGORY SELECTION PANEL (WEST)
		 */
		// final JPanel selectionPanels = new JPanel();
		// selectionPanels.setLayout(scl);
		// selectionPanels.setOpaque(false);

		RoundedPanel categorySelectionPanel = whiteRoundedPanel();
		categorySelectionPanel.setPreferredSize(new Dimension(175, 300));
		JLabel menuSelectionTitle = new JLabel("MENU SELECTION");

		JPanel menuSelectBtns = new JPanel();
		// menuSelectBtns = getCategorySelection();

		Box categorySelectionBox = Box.createVerticalBox();
		menuSelectionTitle.setAlignmentX(Box.CENTER_ALIGNMENT);
		menuSelectBtns.setAlignmentX(Box.CENTER_ALIGNMENT);
		categorySelectionBox.add(Box.createRigidArea(new Dimension(0, 10)));
		categorySelectionBox.add(menuSelectionTitle);
		categorySelectionBox.add(Box.createRigidArea(new Dimension(0, 20)));
		categorySelectionBox.add(menuSelectBtns);

		categorySelectionPanel.add(categorySelectionBox);

		// serverPanel.add(categorySelectionPanel, BorderLayout.WEST);

		RoundedPanel itemSelectionPanel = whiteRoundedPanel();
		itemSelectionPanel.setPreferredSize(new Dimension(175, 300));

		JPanel itemSelectBtns = new JPanel();
		String category = ItemInventory.getItemCategory();

		// Build Login Screen

		loginUserPasswordPanel.add(loginBox, "1");

		loginUserPasswordPanel.add(invalidUserBox, "2");
		loginScreenPanel.add(loginUserPasswordPanel, BorderLayout.CENTER);

		add(loginScreenPanel);

		((JComponent) getContentPane()).setOpaque(false);
		setVisible(true);

	}

	private RoundedPanel whiteRoundedPanel() {

		Border emptyBdr = BorderFactory.createEmptyBorder(15, 0, 10, 0);

		// Rounded Panel
		RoundedPanel whiteBackgroundPanel = new RoundedPanel(new BorderLayout());
		whiteBackgroundPanel.setBorder(emptyBdr);
		whiteBackgroundPanel.setBackground(Color.white);
		whiteBackgroundPanel.setOpaque(false);

		return whiteBackgroundPanel;

	}

	/*
	 * private JPanel createInnerPanel(String text) { JPanel jplPanel = new
	 * JPanel(); // jplPanel.setSize(300, 220); JLabel jlbDisplay = new
	 * JLabel(text); jlbDisplay.setHorizontalAlignment(JLabel.CENTER);
	 * jplPanel.setLayout(new GridLayout(1, 1)); jplPanel.add(jlbDisplay);
	 * return jplPanel; } /* private JPanel getCategorySelection() { String
	 * catIndex = null;
	 * 
	 * ArrayList<String> categorySelection = new ArrayList<String>();
	 * categorySelection = MSAccessDatabase.getItemSelections("Breakfast");
	 * 
	 * final JButton categorySelectionButton[] = new
	 * JButton[categorySelection.size()];
	 * 
	 * // JPanel menuSelectionButtons = new JPanel(new
	 * GridLayout(categorySelection.size(), 1, 7, 7)); JPanel
	 * menuSelectionButtons = new JPanel(new GridLayout(5, 2, 7, 7));
	 * menuSelectionButtons.setOpaque(false);
	 * 
	 * for(int i = 0; i < categorySelection.size(); i++ ) {
	 * categorySelectionButton[i] = new JButton(categorySelection.get(i));
	 * 
	 * categorySelectionButton[i].addActionListener(new ActionListener() {
	 * 
	 * 
	 * // private String itemCategory;
	 * 
	 * public void actionPerformed(ActionEvent e) { int index = -1;
	 * 
	 * for(int i = 0; i < categorySelectionButton.length; i++) { if
	 * (e.getSource() == categorySelectionButton[i]) { index = i;
	 * ItemInventory.setItemCategory(categorySelectionButton[index].getText());
	 * System.out.println("Index = " + index + "  After Button Pressed = " +
	 * ItemInventory.getItemCategory()); break; } }
	 * 
	 * if(index != -1) { // RoundedPanel itemSelectionPanel =
	 * whiteRoundedPanel(); System.out.println("Title = " +
	 * categorySelectionButton[index].getText()); String itemCategory =
	 * categorySelectionButton[index].getText(); JLabel itemCategoryTitle = new
	 * JLabel(itemCategory);
	 * ItemInventory.setItemCategory(categorySelectionButton[index].getText());
	 * System.out.println("After Button Text = " +
	 * ItemInventory.getItemCategory());
	 * 
	 * getItemSelection(itemCategory);
	 * 
	 * ArrayList<String> itemSelection = new ArrayList<String>(); itemSelection
	 * = MSAccessDatabase.getItemSelections(itemCategory);
	 * System.out.println("Category = " + itemCategory + "  Item Selections = "
	 * + itemSelection);
	 * 
	 * JButton itemSelectionButton[] = new JButton[itemSelection.size()];
	 * 
	 * JPanel itemSelectionButtons = new JPanel(new GridLayout(5, 2, 7, 7));
	 * itemSelectionButtons.setOpaque(false);
	 * 
	 * for(int i = 0; i < itemSelection.size(); i++ ) { itemSelectionButton[i] =
	 * new JButton(itemSelection.get(i));
	 * itemSelectionButtons.add(itemSelectionButton[i]); }
	 * 
	 * // Box itemSelectionBox = Box.createVerticalBox(); //
	 * itemCategoryTitle.setAlignmentX(Box.CENTER_ALIGNMENT); //
	 * itemSelectionButtons.setAlignmentX(Box.CENTER_ALIGNMENT); //
	 * itemSelectionBox.add(Box.createRigidArea(new Dimension(0,10))); //
	 * itemSelectionBox.add(itemCategoryTitle); //
	 * itemSelectionBox.add(Box.createRigidArea(new Dimension(0,20))); //
	 * itemSelectionBox.add(itemSelectionButtons);
	 * 
	 * // itemSelectionPanel.add(itemSelectionBox); // index = index + 2; //
	 * String catIndex = "" + index; // selectionPanels.add(itemSelectionBox);
	 * // serverPanel.add(selectionPanels, BorderLayout.CENTER); //
	 * selectionPanels.validate(); // selectionPanels.repaint();
	 * 
	 * // itemSelectionPanel.add(itemSelectionButtons); //
	 * selectionPanels.add(itemSelectionButtons, BorderLayout.CENTER); //
	 * serverPanel.validate(); // serverPanel.repaint(); } //
	 * selectionPanels.add(itemSelectionPanel, catIndex); //
	 * scl.show(selectionPanels, catIndex); } });
	 * 
	 * // scl.show(selectionPanels, catIndex); //
	 * System.out.println("After Button Text = " +
	 * ItemInventory.getItemCategory()); //
	 * menuSelectionButtons.add(categorySelectionButton[i]); } return
	 * menuSelectionButtons; }
	 */
	protected void displaySelectionPanel(String itemCategory) {

		System.out.println("Made it to getItemSelection");
		System.out.println("Item Category = " + itemCategory);

		JLabel itemCategoryLabel = new JLabel(itemCategory);

		ArrayList<String> itemSelection = new ArrayList<String>();
		itemSelection = MSAccessDatabase.getItemSelections(itemCategory);

		final JButton itemSelectionButton[] = new JButton[itemSelection.size()];

		final JPanel itemSelectionButtons = new JPanel(new GridLayout(5, 2, 7,
				7));
		itemSelectionButtons.setOpaque(false);

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

						Boolean itemOrderedFound = MSAccessDatabase
								.getItem("Sau Biscuit");

						if (itemOrderedFound) {
							receiptPanel.add(displayOrderedItem());
						}

						receiptPanel.validate();
						repaint();

					}

				}
			});

			itemSelectionButtons.add(itemSelectionButton[i]);
		}

		Box itemSelectionBox = Box.createVerticalBox();
		itemCategoryLabel.setAlignmentX(Box.CENTER_ALIGNMENT);
		itemSelectionBox.add(Box.createRigidArea(new Dimension(0, 10)));
		itemSelectionBox.add(itemCategoryLabel);
		itemSelectionBox.add(Box.createRigidArea(new Dimension(0, 20)));
		itemSelectionBox.add(itemSelectionButtons);

		// selectionPanels.add(itemSelectionBox);
		itemSelectionPanel.add(itemSelectionBox);
		serverPanel.add(itemSelectionPanel, BorderLayout.CENTER);

		serverPanel.validate();
		serverPanel.repaint();
	}

	public JPanel getItemSelection(String itemCategory) {
		// receiptPanel = new JPanel();
		System.out.println("Made it to getItemSelection");
		System.out.println("Item Category = " + itemCategory);
		// System.out.println("Item Category = " + index);
		ArrayList<String> itemSelection = new ArrayList<String>();
		itemSelection = MSAccessDatabase.getItemSelections(itemCategory);

		final JButton itemSelectionButton[] = new JButton[itemSelection.size()];

		JPanel itemSelectPanel = new JPanel();
		itemSelectPanel.setSize(500, 500);

		final JPanel itemSelectionButtons = new JPanel(new GridLayout(5, 3, 7, 7));
		itemSelectionButtons.setOpaque(false);

		for (int i = 0; i < itemSelection.size(); i++) {
			itemSelectionButton[i] = new JButton(itemSelection.get(i));

			itemSelectionButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					System.out.println(e.getSource());
					
					int index = -1;

					System.out.println(itemSelectionButton.length);
					
					for (int i = 0; i < itemSelectionButton.length; i++) {
						if (e.getSource() == itemSelectionButton[i]) {
							index = i;

							break;
						}
					}

					if (index != -1) {

						Boolean itemOrderedFound = MSAccessDatabase.getItem(itemSelectionButton[index].getText());

						if (itemOrderedFound) 
						{
							JPanel testPanel = new JPanel();
					//		receiptPanel.add(displayOrderedItem());
							testPanel = displayOrderedItem();
						//	receiptPanel.add(testPanel);
							testPanel.setVisible(true);
							add(testPanel);
							
						}

						validate();
						repaint();

					}

				}
			});

			// System.out.println("After Button Text = " +
			// ItemInventory.getItemCategory());
			itemSelectionButtons.add(itemSelectionButton[i]);
			itemSelectPanel.add(itemSelectionButtons);

		}
		System.out.println("After Button Text = "
				+ ItemInventory.getItemCategory());

		return itemSelectPanel;

	}

	public void actionPerformed(ActionEvent e) { // the ActionListener
													// monitoring 3 buttons.
		for (int i = 0; i < categorySelectionButton.length; i++) // using for
																	// loop to
																	// scan
																	// buttons
		{
			System.out.println("Button Length = "
					+ categorySelectionButton.length);
			if (categorySelectionButton == e.getSource()) { // the button[i] is
															// being pressed
				// container.setBackground(color[i]); // re-set background color
				// accordingly
				System.out.println("Did we make it here?");
				// break;
			}
		}

	}

	public JPanel displayOrderedItem() {

		JLabel qtySold = new JLabel("1 - ", JLabel.LEFT);
		JLabel itemOrderedDescription = new JLabel(ItemInventory.getItemDescription(), JLabel.LEFT);
		String itemPrice = "" + df.format(ItemInventory.getPrice());
		JLabel itemOrderedPrice = new JLabel(itemPrice, JLabel.RIGHT);

		System.out.println(ItemInventory.getItemDescription());
		
		
		JPanel displayItemOrdered = new JPanel(new BorderLayout(5, 5));
	//	displayItemOrdered.setOpaque(false);
		displayItemOrdered.setPreferredSize(new Dimension(175, 17));
		displayItemOrdered.add(qtySold, BorderLayout.WEST);
		displayItemOrdered.add(itemOrderedDescription, BorderLayout.CENTER);
		displayItemOrdered.add(itemOrderedPrice, BorderLayout.EAST);

	//	receiptPanel.add(displayItemOrdered);
		
		final RoundedPanel receiptPanel = whiteRoundedPanel();
		receiptPanel.setPreferredSize(new Dimension(250, 300));
		JLabel receiptTestText = new JLabel(
				"            RECEIPT            ");

		final Box receiptBox = Box.createVerticalBox();
		receiptTestText.setAlignmentX(Box.CENTER_ALIGNMENT);
		receiptBox.add(receiptTestText);
		receiptBox.add(Box.createRigidArea(new Dimension(0, 10)));
		receiptBox.add(displayItemOrdered);

		receiptPanel.add(receiptBox);
	//	receiptPanel.validate();
	//	receiptPanel.repaint();

		return receiptPanel;
	}

	protected void getItem(String itemDescription) {

		DecimalFormat df = new DecimalFormat("##.##");

		Boolean itemOrderedFound = MSAccessDatabase.getItem(itemDescription);
		System.out.println(itemOrderedFound);

		if (itemOrderedFound) {
			JLabel qtySold = new JLabel("1 - ", JLabel.LEFT);
			JLabel itemOrderedDescription = new JLabel(ItemInventory.getItemDescription(), JLabel.LEFT);
			String itemPrice = "" + ItemInventory.getPrice();
			JLabel itemOrderedPrice = new JLabel(itemPrice, JLabel.RIGHT);

			JPanel displayItemOrdered = new JPanel(new BorderLayout(5, 5));
			displayItemOrdered.setPreferredSize(new Dimension(150, 17));
			displayItemOrdered.add(qtySold, BorderLayout.WEST);
			displayItemOrdered.add(itemOrderedDescription, BorderLayout.CENTER);
			displayItemOrdered.add(itemOrderedPrice, BorderLayout.EAST);

		}
	}

	public static void main(String[] args) {
		new MaxsRoadsideDinerPOS();
	}
}
