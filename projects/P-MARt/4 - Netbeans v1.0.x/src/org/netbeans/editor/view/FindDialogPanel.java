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

package org.netbeans.editor.view;

/**
 *
 * @author Miloslav Metelka
 * @version 1.0
 */
public class FindDialogPanel extends javax.swing.JPanel {

    static final long serialVersionUID =5048601763767383114L;

    /** Initializes the Form */
    public FindDialogPanel() {
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents () {//GEN-BEGIN:initComponents
        setLayout (new java.awt.BorderLayout ());

        mainPanel = new javax.swing.JPanel ();
        mainPanel.setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints1;

        findWhatPanel = new javax.swing.JPanel ();
        findWhatPanel.setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints2;

        findWhatLabel = new javax.swing.JLabel ();
        findWhatLabel.setText ("Find What:"); // NOI18N

        gridBagConstraints2 = new java.awt.GridBagConstraints ();
        gridBagConstraints2.gridwidth = -1;
        gridBagConstraints2.insets = new java.awt.Insets (0, 2, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        findWhatPanel.add (findWhatLabel, gridBagConstraints2);

        findWhat = new javax.swing.JComboBox ();
        findWhat.setEditable (true);

        gridBagConstraints2 = new java.awt.GridBagConstraints ();
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets (0, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        findWhatPanel.add (findWhat, gridBagConstraints2);

        replaceWithLabel = new javax.swing.JLabel ();
        replaceWithLabel.setText ("Replace With:"); // NOI18N

        gridBagConstraints2 = new java.awt.GridBagConstraints ();
        gridBagConstraints2.insets = new java.awt.Insets (0, 2, 2, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        findWhatPanel.add (replaceWithLabel, gridBagConstraints2);

        replaceWith = new javax.swing.JComboBox ();
        replaceWith.setEditable (true);

        gridBagConstraints2 = new java.awt.GridBagConstraints ();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets (0, 5, 2, 5);
        gridBagConstraints2.weightx = 1.0;
        findWhatPanel.add (replaceWith, gridBagConstraints2);

        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets (5, 5, 8, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints1.weightx = 1.0;
        mainPanel.add (findWhatPanel, gridBagConstraints1);

        highlightSearch = new javax.swing.JCheckBox ();
        highlightSearch.setText ("Highlight Search"); // NOI18N
        highlightSearch.setToolTipText ("Highlights the occurrences in the text"); // NOI18N

        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new java.awt.Insets (0, 5, 2, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add (highlightSearch, gridBagConstraints1);

        incSearch = new javax.swing.JCheckBox ();
        incSearch.setText ("Incremental Search"); // NOI18N
        incSearch.setToolTipText ("Tries to find the text as you type"); // NOI18N

        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new java.awt.Insets (0, 5, 2, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add (incSearch, gridBagConstraints1);

        matchCase = new javax.swing.JCheckBox ();
        matchCase.setText ("Match Case"); // NOI18N
        matchCase.setToolTipText ("Matches search text only to text with the same capitalization"); // NOI18N

        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.insets = new java.awt.Insets (0, 5, 2, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add (matchCase, gridBagConstraints1);

        smartCase = new javax.swing.JCheckBox ();
        smartCase.setText ("Smart Case"); // NOI18N
        smartCase.setToolTipText ("Matches the case if at least one character in the searched text is uppercase"); // NOI18N

        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 4;
        gridBagConstraints1.insets = new java.awt.Insets (0, 5, 2, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add (smartCase, gridBagConstraints1);

        wholeWords = new javax.swing.JCheckBox ();
        wholeWords.setText ("Match Whole Words Only"); // NOI18N
        wholeWords.setToolTipText ("Matches search text only to whole words in the text"); // NOI18N

        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 5;
        gridBagConstraints1.gridheight = 0;
        gridBagConstraints1.insets = new java.awt.Insets (0, 5, 5, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints1.weighty = 1.0;
        mainPanel.add (wholeWords, gridBagConstraints1);

        bwdSearch = new javax.swing.JCheckBox ();
        bwdSearch.setText ("Backward Search"); // NOI18N
        bwdSearch.setToolTipText ("Searches backward from current caret position"); // NOI18N

        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new java.awt.Insets (0, 5, 2, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints1.weightx = 1.0;
        mainPanel.add (bwdSearch, gridBagConstraints1);

        wrapSearch = new javax.swing.JCheckBox ();
        wrapSearch.setText ("Wrap Search"); // NOI18N
        wrapSearch.setToolTipText ("Continues search from the begining/end of document"); // NOI18N

        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new java.awt.Insets (0, 5, 2, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints1.weightx = 1.0;
        mainPanel.add (wrapSearch, gridBagConstraints1);

        regExp = new javax.swing.JCheckBox ();
        regExp.setText ("Regular Expressions"); // NOI18N

        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.gridheight = 0;
        gridBagConstraints1.insets = new java.awt.Insets (0, 5, 2, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints1.weightx = 1.0;
        mainPanel.add (regExp, gridBagConstraints1);


        add (mainPanel, "Center"); // NOI18N

    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JPanel mainPanel;
    protected javax.swing.JPanel findWhatPanel;
    protected javax.swing.JLabel findWhatLabel;
    protected javax.swing.JComboBox findWhat;
    protected javax.swing.JLabel replaceWithLabel;
    protected javax.swing.JComboBox replaceWith;
    protected javax.swing.JCheckBox highlightSearch;
    protected javax.swing.JCheckBox incSearch;
    protected javax.swing.JCheckBox matchCase;
    protected javax.swing.JCheckBox smartCase;
    protected javax.swing.JCheckBox wholeWords;
    protected javax.swing.JCheckBox bwdSearch;
    protected javax.swing.JCheckBox wrapSearch;
    protected javax.swing.JCheckBox regExp;
    // End of variables declaration//GEN-END:variables

}

/*
 * Log
 *  6    Gandalf   1.5         1/13/00  Miloslav Metelka Localization
 *  5    Gandalf   1.4         10/23/99 Ian Formanek    NO SEMANTIC CHANGE - Sun
 *       Microsystems Copyright in File Comment
 *  4    Gandalf   1.3         8/27/99  Miloslav Metelka 
 *  3    Gandalf   1.2         8/17/99  Ian Formanek    Generated serial version
 *       UID
 *  2    Gandalf   1.1         6/29/99  Miloslav Metelka Scrolling and patches
 *  1    Gandalf   1.0         5/5/99   Miloslav Metelka 
 * $
 */
