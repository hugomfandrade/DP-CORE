/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is Forte for Java, Community Edition. The Initial
 * Developer of the Original Code is Sun Microsystems, Inc. Portions
 * Copyright 1997-2000 Sun Microsystems, Inc. All Rights Reserved.
 */

package  org.netbeans.modules.web.wizards.beanjsp.ui;

import org.netbeans.modules.web.wizards.beanjsp.model.*;
import org.netbeans.modules.web.wizards.wizardfw.*;
import org.netbeans.modules.web.wizards.beanjsp.ide.netbeans.*;

import org.netbeans.modules.web.util.*;
import org.netbeans.modules.web.wizards.beanjsp.util.*;

import org.openide.util.*;
import org.openide.loaders.DataFolder;
import org.openide.filesystems.*;


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class JSPBeansSelectionPanel extends StandardWizardPanel {

    public JSPBeansSelectionPanel() {
        this(JSPPage.INPUT_PAGE);
    }

    public JSPBeansSelectionPanel(int pageType) {
        super();
        this.pageType = pageType;
        initComponents ();
        setButtonsEnabled();
        addSelectionListeners();
        addDoubleClickListeners();
    }

    /*public HelpCtx getHelp () {
      return new HelpCtx (JSPBeansSelectionPanel.class);
}*/

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents () {//GEN-BEGIN:initComponents

        java.util.ResourceBundle resBundle = NbBundle.getBundle(JSPPageWizard.i18nBundle);
        //// create components

        this.setTopMessage(resBundle.getString("JBW_JSPBeansSelectionMsg"));						 // NOI18N
        beanPackagePanel = new javax.swing.JPanel();
        beanPackageMsg = new MultiLineLabel(resBundle.getString("JBW_BeanPackageMsg"));			 // NOI18N
        beanPackageLabel = new JLabel(resBundle.getString("JBW_BeanPackageLabel"));				 // NOI18N
        beanPackageTF = new javax.swing.JTextField (30);
        // beanPackageTF.setEnabled(false);
        beanPackageTF.setEditable(false);
        beanPackageTF.setBackground (java.awt.Color.lightGray);
        beanPackageBrowseB = new JButton (resBundle.getString("JBW_BeanPackageBrowseBLabel"));	 // NOI18N
        // netbeans convention
        beanPackageBrowseB.setFont (new java.awt.Font ("SansSerif", 0, 11));					 // NOI18N
        beanPackageBrowseB.setMinimumSize (new java.awt.Dimension(85, 15));
        beanPackageBrowseB.setMaximumSize (new java.awt.Dimension(85, 27));
        beanPackageBrowseB.setPreferredSize (new java.awt.Dimension(85, 25));


        beansLabel = new JLabel(resBundle.getString("JBW_JSPBeansListName"));						 // NOI18N
        beansList = new JList();
        beansSPane = new JScrollPane(beansList);

        beansSPane.setPreferredSize (new java.awt.Dimension(150, 150));
        beansSPane.setMinimumSize (new java.awt.Dimension(150, 150));
        beansSPane.setMaximumSize (new java.awt.Dimension(150, 150));


        //NB beansRepository = RepositoryBeanViewPane.createRepositoryBeanViewPane();

        useBeansLabel = new JLabel(resBundle.getString("JBW_JSPBeansTableName"));					 // NOI18N
        useBeansTable = new JSPUseBeanTable();

        buttonsPanel = new javax.swing.JPanel();

        addButton = new javax.swing.JButton ();
        addButton.setText (resBundle.getString("JBW_AddButton"));									 // NOI18N
        //addButton.setMinimumSize (new java.awt.Dimension(73, 15));
        //addButton.setMaximumSize (new java.awt.Dimension(100, 27));


        removeButton = new javax.swing.JButton ();
        removeButton.setText (resBundle.getString("JBW_RemoveButton"));							 // NOI18N
        //removeButton.setMinimumSize (new java.awt.Dimension(73, 15));
        //removeButton.setMaximumSize (new java.awt.Dimension(100, 27));


        moveUpButton = new javax.swing.JButton ();
        moveUpButton.setText (resBundle.getString("JBW_MoveUpButton"));							 // NOI18N
        //moveUpButton.setMinimumSize (new java.awt.Dimension(73, 15));
        //moveUpButton.setMaximumSize (new java.awt.Dimension(100, 27));


        moveDownButton = new javax.swing.JButton ();
        moveDownButton.setText (resBundle.getString("JBW_MoveDownButton"));						 // NOI18N
        //moveDownButton.setMinimumSize (new java.awt.Dimension(73, 15));
        //moveDownButton.setMaximumSize (new java.awt.Dimension(100, 27));

        //// layout and add components
        arrangeComponents();


        //// create and add listeners

        beansListModel = JSPPageWizard.simpleJSPPage.getAvailableBeansModel();
        beansList.setModel(beansListModel);

        useBeansTableModel = JSPPageWizard.simpleJSPPage.getJSPBeansModel();
        this.useBeansTable.setTableModel(useBeansTableModel);

        addButton.addActionListener( new ActionListener() {
                                         public void actionPerformed(ActionEvent evt) {
                                             addToUseBeansList();
                                         }
                                     });

        removeButton.addActionListener( new ActionListener() {
                                            public void actionPerformed(ActionEvent evt) {
                                                removeFromUseBeansList();
                                            }
                                        });

        moveUpButton.addActionListener( new ActionListener() {
                                            public void actionPerformed(ActionEvent evt) {
                                                moveUpUseBean();
                                            }
                                        });

        moveDownButton.addActionListener( new ActionListener() {
                                              public void actionPerformed(ActionEvent evt) {
                                                  moveDownUseBean();
                                              }
                                          });

        beanPackageBrowseB.addActionListener( new ActionListener() {
                                                  public void actionPerformed(ActionEvent evt) {
                                                      browseForBeanPacakge();
                                                      loadBeansFromBeanPackage();
                                                  }
                                              });



    }//GEN-END:initComponents

    //// layout components in
    private void arrangeComponents() {
        createBeanPackageComponents();
        createButtonPanelComponents();
        arrangeCompsWithGridBag();
    }

    private void addGridBagComponent(Container parent, Component comp,
                                     int gridx, int gridy, int gridwidth, int gridheight,
                                     double weightx, double weighty,
                                     int anchor, int fill,
                                     Insets insets, int ipadx, int ipady
                                    ) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = gridx;
        cons.gridy = gridy;
        cons.gridwidth = gridwidth;
        cons.gridheight = gridheight;
        cons.weightx = weightx;
        cons.weighty = weighty;
        cons.anchor = anchor;
        cons.fill = fill;
        cons.insets = insets;
        cons.ipadx = ipadx;
        cons.ipady = ipady;
        parent.add(comp,cons);
    }

    public void createBeanPackageComponents() {
        beanPackagePanel.setLayout(new GridBagLayout());

        Component packPanelSep = Box.createVerticalStrut(2);

        int gridy = 0;
        addGridBagComponent(this.beanPackagePanel,beanPackageMsg,
                            0,gridy,3,1,
                            0,0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.beanPackagePanel,beanPackageLabel,
                            0,++gridy,1,1,
                            0,0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.beanPackagePanel,beanPackageTF,
                            1,gridy,1,1,
                            100,0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.beanPackagePanel,beanPackageBrowseB,
                            2,gridy,1,1,
                            0,0,
                            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.beanPackagePanel,packPanelSep,
                            0,++gridy,3,1,
                            100,100,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2,2,2,2),5,5	);


    }


    private void createButtonPanelComponents() {

        this.buttonsPanel.setLayout(new GridBagLayout());

        Component moveGap = Box.createVerticalStrut(6);

        Component remainderGlue = Box.createGlue();

        addGridBagComponent(this.buttonsPanel,addButton,
                            0,0,1,1,
                            100,00,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2,2,2,2),5,5	);
        addGridBagComponent(this.buttonsPanel,removeButton,
                            0,1,1,1,
                            100,0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.buttonsPanel,moveGap,
                            0,2,1,1,
                            100,0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.buttonsPanel,moveUpButton,
                            0,3,1,1,
                            100,0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.buttonsPanel,moveDownButton,
                            0,4,1,1,
                            100,0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.buttonsPanel,remainderGlue,
                            0,5,1,1,
                            100,100,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2,2,2,2),5,5	);


    }

    private void arrangeCompsWithGridBag() {

        this.contentPane.setLayout(new GridBagLayout());

        int gridy = 0;

        addGridBagComponent(this.contentPane,beanPackagePanel,
                            0,gridy,3,1,
                            0,0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.contentPane,beansLabel,
                            0,++gridy,1,1,
                            0,0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.contentPane,useBeansLabel,
                            2,gridy,1,1,
                            100,0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.contentPane,beansSPane,
                            0,++gridy,1,1,
                            0,100,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.contentPane,buttonsPanel,
                            1,gridy,1,1,
                            0,100,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2,2,2,2),5,5	);

        addGridBagComponent(this.contentPane,useBeansTable,
                            2,gridy,1,1,
                            100,100,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2,2,2,2),5,5	);
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables


    private javax.swing.JScrollPane beansSPane;
    private javax.swing.JList beansList;
    private javax.swing.JLabel beansLabel;

    //NB private RepositoryBeanViewPane beansRepository;

    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton addButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton moveUpButton;
    private javax.swing.JButton moveDownButton;


    private javax.swing.JLabel useBeansLabel;
    private JSPUseBeanTable useBeansTable;

    private javax.swing.JPanel beanPackagePanel;
    private MultiLineLabel beanPackageMsg;
    private javax.swing.JLabel beanPackageLabel;
    private javax.swing.JTextField beanPackageTF;
    private javax.swing.JButton beanPackageBrowseB;

    // store locally for avoiding reloading beans when same pacakge is selected multiple
    // times or when navigating back and forth from this panel
    DataFolder beanPakFolder = null;

    private JSPItemListModel beansListModel;
    private JSPBeanTableModel useBeansTableModel;

    private int pageType = JSPPage.INPUT_PAGE;

    // End of variables declaration//GEN-END:variables

    public void addToUseBeansList() {
        int[] idx = beansList.getSelectedIndices();
        int oldRows = useBeansTable.getTable().getRowCount();
        if( (idx.length > 0) && (idx[0] >= 0)) {
            for (int i=0; i<idx.length; i++) {
                // Object obj = beansListModel.remove(idx);
                JSPBean jspBean = (JSPBean) beansListModel.get(idx[i]);
                useBeansTableModel.add((JSPBean)jspBean.clone());
            }
            int rows = useBeansTable.getTable().getRowCount();
            useBeansTable.getTable().getSelectionModel().setSelectionInterval(
                oldRows, rows-1);
            setButtonsEnabled();
        }
    }

    public void removeFromUseBeansList() {
        int[] idx = useBeansTable.getTable().getSelectedRows();
        int count = 0;
        if((idx.length > 0) && (idx[0] >= 0)) {
            for (int i=0; i<idx.length; i++) {
                // always remove item 0, since index gets rearranged
                Object obj = useBeansTableModel.remove(idx[0]-count);
                count++;
            }
            setButtonsEnabled();
        }
    }

    public void moveUpUseBean() {
        int[] idx = useBeansTable.getTable().getSelectedRows();
        if((idx.length > 0) && (idx[0] > 0 )) {
            for (int i=0; i<idx.length; i++){
                // idx = useBeansTableModel.moveUp(idx);
                useBeansTableModel.moveUp(idx[i]);
            }
            useBeansTable.getTable().getSelectionModel().setSelectionInterval(
                idx[0]-1, idx[idx.length-1]-1);
            checkButtons();
        }
    }

    public void moveDownUseBean() {
        int[] idx = useBeansTable.getTable().getSelectedRows();
        int rowCount = useBeansTable.getTable().getRowCount();
        if((idx.length >0) && (idx[0] >= 0) && (idx[idx.length-1] < rowCount-1)) {
            for (int i=idx.length-1; i>=0; i--){
                useBeansTableModel.moveDown(idx[i]);
            }
            useBeansTable.getTable().getSelectionModel().setSelectionInterval(
                idx[0]+1,idx[idx.length-1]+1);
            checkButtons();
        }
    }

    public void setButtonsEnabled(){
        checkAddButton();
        checkButtons();
    }

    private void checkAddButton(){
        if ((beansListModel.getSize() > 0) && (beansList.getSelectedIndex()>=0))
            addButton.setEnabled(true);
        else
            addButton.setEnabled(false);
    }
    private void checkButtons(){
        int rows = useBeansTable.getTable().getRowCount();
        int[] srow = useBeansTable.getTable().getSelectedRows();
        if ((rows > 0) && (srow.length >0)){
            removeButton.setEnabled(true);
        } else {
            removeButton.setEnabled(false);
        }
        if ((rows > 1) && (srow.length >0) && (srow[0] > 0)){
            moveUpButton.setEnabled(true);
        } else {
            moveUpButton.setEnabled(false);
        }
        if ((rows > 1) && (srow.length >0) && (srow[srow.length -1] < rows -1)){
            moveDownButton.setEnabled(true);
        } else {
            moveDownButton.setEnabled(false);
        }
    }

    private void addSelectionListeners(){
        beansList.addListSelectionListener(new ListSelectionListener(){
                                               public void valueChanged(ListSelectionEvent e){
                                                   checkAddButton();
                                               }
                                           });
        useBeansTable.getTable().getSelectionModel().addListSelectionListener(
            new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent e){
                    checkButtons();
                }
            });
    }

    private void addDoubleClickListeners(){
        beansList.addMouseListener (new java.awt.event.MouseAdapter () {
                                        public void mouseClicked (java.awt.event.MouseEvent evt) {
                                            beansListMouseClicked(evt);
                                        }
                                    });
        useBeansTable.getTable().addMouseListener (new java.awt.event.MouseAdapter () {
                    public void mouseClicked (java.awt.event.MouseEvent evt) {
                        useBeansTableMouseClicked(evt);
                    }
                });
    }

    private void beansListMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            addToUseBeansList();
        }
    }

    private void useBeansTableMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            removeFromUseBeansList();
        }
    }

    /* this method gets called on next && prev */
    public void storeSettings (Object settings) {
        JSPPageWizard.updateBizMethodsPanel();
        JSPPageWizard.updateDisplayFieldsPanel();
        JSPPageWizard.updateInputFieldsPanel();
    }
    //// bean pacakge selection methods
    private void loadBeansFromBeanPackage() {
        if(beanPakFolder != null) {
            if(JSPPageWizard.beanPakFolder == null) {
                JSPPageWizard.beanPakFolder = beanPakFolder;
            } else if(JSPPageWizard.beanPakFolder.getName().equals(beanPakFolder.getName())){
                return;
            } else {
                JSPPageWizard.beanPakFolder = beanPakFolder;
            }
            loadBeansList();
            JSPPageWizard.updateBeanSelectionPanel();
        }
    }

    private void browseForBeanPacakge() {
        beanPakFolder = IDEHelper.browseForBeanPackage();

        String beanPackageName = "";				 // NOI18N

        if(beanPakFolder != null) {
            beanPackageName = beanPakFolder.getPrimaryFile().getPackageName('.');
        }

        beanPackageTF.setText(beanPackageName);
    }


    public void loadBeansList() {

        Vector javaBeans = IDEHelper.findBeansInFolder(JSPPageWizard.beanPakFolder);
        JSPVector jspBeans = (JSPVector) JSPPage.beanManager.createValidJSPBeans(javaBeans);

        // JSPVector jspBeans = (JSPVector) JSPPage.beanManager.getValidJSPBeans(basePath,packageName);
        // Debug.println("Loading jsp beans: "+jspBeans.size());
        JSPPageWizard.simpleJSPPage.loadBeansList(jspBeans);

    }


    public static void main(String[] args) {
        if( Debug.TEST ) {
            JFrame testFrame = new JFrame("This is Test Frame");		 // NOI18N
            testFrame.getContentPane().add(new JSPBeansSelectionPanel(),SwingConstants.CENTER);
            testFrame.setSize(500,300);
            testFrame.pack();
            testFrame.show();
        }

    }

}
