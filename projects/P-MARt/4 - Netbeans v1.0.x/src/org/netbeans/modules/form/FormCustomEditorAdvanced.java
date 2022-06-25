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

package org.netbeans.modules.form;

/**
 *
 * @author  jformanek
 * @version 
 */
public class FormCustomEditorAdvanced extends javax.swing.JPanel {

    static final int DEFAULT_WIDTH = 350;
    static final int DEFAULT_HEIGHT = 300;

    static final long serialVersionUID =-885210213146017493L;
    /** Creates new form FormCustomEditorAdvanced */
    public FormCustomEditorAdvanced (String preCode, String postCode) {
        initComponents ();

        preEditorPane.setContentType ("text/x-java");    // allow syntax coloring // NOI18N
        postEditorPane.setContentType ("text/x-java");    // allow syntax coloring // NOI18N

        // restore state according to parameters
        preCheckBox.setSelected (preCode != null);
        postCheckBox.setSelected (postCode != null);

        // restore current pre/post code, if set
        if (preCode != null) preEditorPane.setText (preCode);
        if (postCode != null) postEditorPane.setText (postCode);

        preEditorPane.setEnabled (preCode != null);
        postEditorPane.setEnabled (postCode != null);
    }

    String getPreCode () {
        if (!preCheckBox.isSelected ()) return null;
        if ("".equals (preEditorPane.getText ())) return null; // NOI18N
        return preEditorPane.getText ();
    }

    String getPostCode () {
        if (!postCheckBox.isSelected ()) return null;
        if ("".equals (postEditorPane.getText ())) return null; // NOI18N
        return postEditorPane.getText ();
    }

    public java.awt.Dimension getPreferredSize () {
        java.awt.Dimension inh = super.getPreferredSize ();
        return new java.awt.Dimension (Math.max (inh.width, DEFAULT_WIDTH), Math.max (inh.height, DEFAULT_HEIGHT));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents () {//GEN-BEGIN:initComponents
        prePanel = new javax.swing.JPanel ();
        preCheckBox = new javax.swing.JCheckBox ();
        preScrollPane = new javax.swing.JScrollPane ();
        preEditorPane = new javax.swing.JEditorPane ();
        postPanel = new javax.swing.JPanel ();
        postCheckBox = new javax.swing.JCheckBox ();
        postScrollPane = new javax.swing.JScrollPane ();
        postEditorPane = new javax.swing.JEditorPane ();
        setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints1;

        prePanel.setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints2;

        preCheckBox.setText (FormEditor.getFormBundle().getString ("CTL_GeneratePreInitializationCode"));
        preCheckBox.addChangeListener (new javax.swing.event.ChangeListener () {
                                           public void stateChanged (javax.swing.event.ChangeEvent evt) {
                                               preCheckBoxStateChanged (evt);
                                           }
                                       }
                                      );

        gridBagConstraints2 = new java.awt.GridBagConstraints ();
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        prePanel.add (preCheckBox, gridBagConstraints2);



        preScrollPane.setViewportView (preEditorPane);

        gridBagConstraints2 = new java.awt.GridBagConstraints ();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets (8, 20, 0, 0);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        prePanel.add (preScrollPane, gridBagConstraints2);


        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets (0, 0, 8, 0);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add (prePanel, gridBagConstraints1);

        postPanel.setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints3;

        postCheckBox.setText (FormEditor.getFormBundle ().getString ("CTL_GeneratePostInitializationCode"));
        postCheckBox.addChangeListener (new javax.swing.event.ChangeListener () {
                                            public void stateChanged (javax.swing.event.ChangeEvent evt) {
                                                postCheckBoxStateChanged (evt);
                                            }
                                        }
                                       );

        gridBagConstraints3 = new java.awt.GridBagConstraints ();
        gridBagConstraints3.gridwidth = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        postPanel.add (postCheckBox, gridBagConstraints3);



        postScrollPane.setViewportView (postEditorPane);

        gridBagConstraints3 = new java.awt.GridBagConstraints ();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets (8, 20, 0, 0);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        postPanel.add (postScrollPane, gridBagConstraints3);


        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add (postPanel, gridBagConstraints1);

    }//GEN-END:initComponents

    private void postCheckBoxStateChanged (javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_postCheckBoxStateChanged
        postEditorPane.setEnabled (postCheckBox.isSelected ());
        if (postCheckBox.isSelected ()) {
            postEditorPane.requestFocus ();
        }
    }//GEN-LAST:event_postCheckBoxStateChanged

    private void preCheckBoxStateChanged (javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_preCheckBoxStateChanged
        preEditorPane.setEnabled (preCheckBox.isSelected ());
        if (preCheckBox.isSelected ()) {
            preEditorPane.requestFocus ();
        }
    }//GEN-LAST:event_preCheckBoxStateChanged




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel prePanel;
    private javax.swing.JCheckBox preCheckBox;
    private javax.swing.JScrollPane preScrollPane;
    private javax.swing.JEditorPane preEditorPane;
    private javax.swing.JPanel postPanel;
    private javax.swing.JCheckBox postCheckBox;
    private javax.swing.JScrollPane postScrollPane;
    private javax.swing.JEditorPane postEditorPane;
    // End of variables declaration//GEN-END:variables

}
/*
 * Log
 *  6    Gandalf   1.5         1/13/00  Ian Formanek    NOI18N #2
 *  5    Gandalf   1.4         1/12/00  Pavel Buzek     I18N
 *  4    Gandalf   1.3         1/5/00   Ian Formanek    NOI18N
 *  3    Gandalf   1.2         11/27/99 Patrik Knakal   
 *  2    Gandalf   1.1         10/23/99 Ian Formanek    NO SEMANTIC CHANGE - Sun
 *       Microsystems Copyright in File Comment
 *  1    Gandalf   1.0         9/12/99  Ian Formanek    
 * $
 */
