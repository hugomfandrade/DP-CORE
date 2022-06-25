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

import java.util.Vector;
import org.openide.TopManager;
import org.openide.NotifyDescriptor;
/**
 *
 * @author  Pavel Buzek
 * @version
 */
public class EventCustomEditor extends javax.swing.JPanel {

    static final long serialVersionUID =-4825059521634962952L;
    /** Creates new form EventCustomEditor */
    public EventCustomEditor(RADComponent.EventProperty eventProperty) {
        this.eventProperty = eventProperty;
        changes = eventProperty.new HandlerSetChange ();
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents () {//GEN-BEGIN:initComponents
        jScrollPane1 = new javax.swing.JScrollPane ();
        Vector h = eventProperty.event.getHandlers ();
        for (int i=0, n=h.size (); i<n; i++) {
            handlersModel.addElement (((EventsManager.EventHandler) (h.get (i))).getName ());
        }
        handlersList = new javax.swing.JList ();
        handlersList.setModel (handlersModel);
        addButton = new javax.swing.JButton ();
        removeButton = new javax.swing.JButton ();
        editButton = new javax.swing.JButton ();
        setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints1;
        setPreferredSize (new java.awt.Dimension(300, 300));


        handlersList.addListSelectionListener (new javax.swing.event.ListSelectionListener () {
                                                   public void valueChanged (javax.swing.event.ListSelectionEvent evt) {
                                                       handlersListValueChanged (evt);
                                                   }
                                               }
                                              );

        jScrollPane1.setViewportView (handlersList);


        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridheight = 4;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets (8, 8, 8, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 0.9;
        gridBagConstraints1.weighty = 1.0;
        add (jScrollPane1, gridBagConstraints1);

        addButton.setLabel (FormEditor.getFormBundle ().getString ("CTL_EE_ADD"));
        addButton.addActionListener (new java.awt.event.ActionListener () {
                                         public void actionPerformed (java.awt.event.ActionEvent evt) {
                                             addButtonActionPerformed (evt);
                                         }
                                     }
                                    );


        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets (8, 8, 0, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints1.weightx = 0.1;
        add (addButton, gridBagConstraints1);

        removeButton.setLabel (FormEditor.getFormBundle ().getString ("CTL_EE_REMOVE"));
        removeButton.addActionListener (new java.awt.event.ActionListener () {
                                            public void actionPerformed (java.awt.event.ActionEvent evt) {
                                                removeButtonActionPerformed (evt);
                                            }
                                        }
                                       );


        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets (8, 8, 0, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints1.weightx = 0.1;
        add (removeButton, gridBagConstraints1);

        editButton.setLabel (FormEditor.getFormBundle ().getString ("CTL_EE_RENAME"));
        editButton.addActionListener (new java.awt.event.ActionListener () {
                                          public void actionPerformed (java.awt.event.ActionEvent evt) {
                                              editButtonActionPerformed (evt);
                                          }
                                      }
                                     );


        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets (8, 8, 0, 8);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints1.weightx = 0.1;
        add (editButton, gridBagConstraints1);

    }//GEN-END:initComponents

    private void handlersListValueChanged (javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_handlersListValueChanged
        // Add your handling code here:
        if (handlersList.isSelectionEmpty()) {
            removeButton.setEnabled(false);
            editButton.setEnabled(false);
        } else {
            removeButton.setEnabled(true);
            editButton.setEnabled(true);
        }
    }//GEN-LAST:event_handlersListValueChanged

    private void editButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // Add your handling code here:
        NotifyDescriptor.InputLine nd = new NotifyDescriptor.InputLine (FormEditor.getFormBundle ().getString ("CTL_EE_RENAME_LABEL"), FormEditor.getFormBundle ().getString ("CTL_EE_RENAME_CAPTION"));
        Object[] handlers = handlersList.getSelectedValues();
        for (int i=0, n=handlers.length; i<n; i++) {
            nd.setInputText((String)handlers[i]);
            if (TopManager.getDefault().notify(nd).equals (NotifyDescriptor.OK_OPTION)) {
                if (org.openide.util.Utilities.isJavaIdentifier (nd.getInputText())) {
                    changes.getRenamedOldNames ().add (handlers[i]);
                    changes.getRenamedNewNames ().add (nd.getInputText());
                    int pos = handlersModel.indexOf(handlers[i]);
                    handlersModel.remove(pos);
                    handlersModel.add(pos, nd.getInputText());
                } else {
                    NotifyDescriptor.Message msg = new NotifyDescriptor.Message (FormEditor.getFormBundle ().getString ("CTL_EE_NOT_IDENTIFIER"), NotifyDescriptor.WARNING_MESSAGE);
                    TopManager.getDefault().notify(msg);
                }
            }
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void removeButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // Add your handling code here:
        Object[] handlers = handlersList.getSelectedValues();
        for (int i=0, n=handlers.length; i<n; i++) {
            changes.getRemoved ().add (handlers[i]);
            handlersModel.removeElement(handlers[i]);
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void addButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // Add your handling code here:
        NotifyDescriptor.InputLine nd = new NotifyDescriptor.InputLine (FormEditor.getFormBundle ().getString ("CTL_EE_ADD_LABEL"), FormEditor.getFormBundle ().getString ("CTL_EE_ADD_CAPTION"));
        if (TopManager.getDefault().notify(nd).equals (NotifyDescriptor.OK_OPTION)) {
            if (org.openide.util.Utilities.isJavaIdentifier (nd.getInputText())) {
                changes.getAdded ().add (nd.getInputText());
                handlersModel.addElement(nd.getInputText());
            } else {
                NotifyDescriptor.Message msg = new NotifyDescriptor.Message (FormEditor.getFormBundle ().getString ("CTL_EE_NOT_IDENTIFIER"), NotifyDescriptor.WARNING_MESSAGE);
                TopManager.getDefault().notify(msg);
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    public void doChanges () {
        try {
            eventProperty.setValue (changes);
        } catch (java.lang.reflect.InvocationTargetException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList handlersList;
    private javax.swing.JButton addButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton editButton;
    // End of variables declaration//GEN-END:variables

    RADComponent.EventProperty eventProperty;
    javax.swing.DefaultListModel handlersModel = new javax.swing.DefaultListModel ();
    RADComponent.EventProperty.HandlerSetChange changes;
}

/*
 * Log
 *  7    Gandalf   1.6         1/12/00  Pavel Buzek     I18N
 *  6    Gandalf   1.5         1/12/00  Pavel Buzek     #4910 - check if name of
 *       event is valid java identifier
 *  5    Gandalf   1.4         1/1/00   Ian Formanek    Event handlers list is 
 *       in Scroll Pane
 *  4    Gandalf   1.3         11/27/99 Patrik Knakal   
 *  3    Gandalf   1.2         11/26/99 Pavel Buzek     
 *  2    Gandalf   1.1         11/26/99 Pavel Buzek     EventCustomEditor 
 *       changed to panel, displayed via DialogDescriptor
 *  1    Gandalf   1.0         11/25/99 Pavel Buzek     
 * $
 */