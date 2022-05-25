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

package org.netbeans.modules.autoupdate;

import java.awt.Dialog;
import java.util.ResourceBundle;
import java.text.MessageFormat;

import org.openide.DialogDescriptor;
import org.openide.NotifyDescriptor;
import org.openide.TopManager;
import org.openide.util.NbBundle;
import org.openide.util.HelpCtx;
import org.openide.util.RequestProcessor;

/** Displays the progress of update checking.
 * @author  phrebejk
 */
public class DownloadProgressPanel extends javax.swing.JPanel implements ProgressDialog {

    private static final String EMPTY_STRING = ""; // NOI18N

    /** The ResourceBundle */
    private static final ResourceBundle bundle = NbBundle.getBundle( DownloadProgressPanel.class );
    /** The task it's progress is showed in the dialog */
    private Runnable task;
    /** The dialog */
    private Dialog dialog;
    /** Title of the dialog */
    private String title;
    /** Current value of the overallBar */
    private int overallValue;
    /** Current value of the partialBar */
    private int partialValue;

    static final long serialVersionUID =1986287669107010921L;
    /** Creates new form CheckProgressPanel */
    public DownloadProgressPanel() {
        initComponents ();
        overallLabel.setText( EMPTY_STRING );
        partialLabel.setText( EMPTY_STRING );
    }

    /** Overload getPreffered size to get a bit bigger dialog */
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension( 350, super.getPreferredSize().height );
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents () {//GEN-BEGIN:initComponents
        jTextArea1 = new javax.swing.JTextArea ();
        partialLabel = new javax.swing.JLabel ();
        partialBar = new javax.swing.JProgressBar ();
        overallLabel = new javax.swing.JLabel ();
        overallBar = new javax.swing.JProgressBar ();
        doneLabel = new javax.swing.JLabel ();
        setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints1;
        setBorder (new javax.swing.border.EmptyBorder(new java.awt.Insets(16, 8, 16, 8)));

        jTextArea1.setLineWrap (true);
        jTextArea1.setWrapStyleWord (true);
        jTextArea1.setBackground ((java.awt.Color) javax.swing.UIManager.getDefaults ().get ("Label.background")); // NOI18N
        jTextArea1.setText (org.openide.util.NbBundle.getBundle(DownloadProgressPanel.class).getString("DownloadProgressPanel.jTextArea1.text"));
        jTextArea1.setEditable (false);


        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets (32, 0, 24, 0);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add (jTextArea1, gridBagConstraints1);

        partialLabel.setText ("jLabel1"); // NOI18N


        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.insets = new java.awt.Insets (8, 0, 8, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        add (partialLabel, gridBagConstraints1);



        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add (partialBar, gridBagConstraints1);

        overallLabel.setText ("jLabel2"); // NOI18N


        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.insets = new java.awt.Insets (16, 0, 8, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        add (overallLabel, gridBagConstraints1);



        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 1.0;
        add (overallBar, gridBagConstraints1);

        doneLabel.setText (org.openide.util.NbBundle.getBundle(DownloadProgressPanel.class).getString("DownloadProgressPanel.jLabel1.text"));
        doneLabel.setFont (new java.awt.Font ("Dialog", 1, 11)); // NOI18N


        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.insets = new java.awt.Insets (24, 0, 16, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        add (doneLabel, gridBagConstraints1);

    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel partialLabel;
    private javax.swing.JProgressBar partialBar;
    private javax.swing.JLabel overallLabel;
    private javax.swing.JProgressBar overallBar;
    private javax.swing.JLabel doneLabel;
    // End of variables declaration//GEN-END:variables

    /** Starts the task in RequestProcessor when the panel is added to dialog */
    public void addNotify() {
        super.addNotify();
        if ( task != null )
            RequestProcessor.postRequest( task );
    }

    // IMPLEMENTATION OF ProgressDialog -----------------------------------------

    /** Shows the dialog and runs the task. The method blocks until the task is
     * done or CancelButton is pressed
     *@param task The task which will be executed
     *@param listener for cancel button
     */
    public void showAndGo(Runnable task, java.awt.event.ActionListener cancelListener) {

        this.task = task;

        // Create progress dialog
        DialogDescriptor dd = new DialogDescriptor(
                                  this,
                                  title,
                                  true,                                                 // Modal
                                  new Object[] { NotifyDescriptor.CANCEL_OPTION },      // Option list
                                  NotifyDescriptor.CANCEL_OPTION,                       // Default
                                  DialogDescriptor.BOTTOM_ALIGN,                        // Align
                                  new HelpCtx ( DownloadProgressPanel.class ),            // Help
                                  cancelListener
                              );

        dialog = TopManager.getDefault().createDialog( dd );
        dialog.show();
    }


    /** Closes the dialog */
    public void close() {
        dialog.setVisible( false );
        dialog.dispose();
        dialog = null;
    }

    /** Indexed getter for property gaugeValue.
     *@param index Index of the property.
     *@return Value of the property at <CODE>index</CODE>.
     */
    public int getGaugeValue(int gauge) {
        if ( gauge == PARTIAL_GAUGE ) {
            return partialValue;
        }
        else if ( gauge == OVERALL_GAUGE ) {
            return overallValue;
        }
        else {
            return -1;
        }
    }

    /** Indexed setter for property gaugeValue.
     *@param index Index of the property.
     *@param gaugeValue New value of the property at <CODE>index</CODE>.
     */
    public void setGaugeValue(int gauge, final int gaugeValue) {
        if ( gauge == PARTIAL_GAUGE ) {
            partialValue = gaugeValue;
            javax.swing.SwingUtilities.invokeLater( new Runnable() {
                                                        public void run() {
                                                            partialBar.setValue( gaugeValue );
                                                        }
                                                    } );
        }
        else if ( gauge == OVERALL_GAUGE ) {
            overallValue = gaugeValue;
            javax.swing.SwingUtilities.invokeLater( new Runnable() {
                                                        public void run() {
                                                            overallBar.setValue( gaugeValue );
                                                        }
                                                    } );
        }
    }

    /** Indexed setter for property gaugeBounds.
     *@param index Index of the property.
     *@param gaugeBounds New value of the property at <CODE>index</CODE>.
     */
    public void setGaugeBounds(int gauge, final int gaugeMin, final int gaugeMax) {
        if ( gauge == PARTIAL_GAUGE ) {
            javax.swing.SwingUtilities.invokeLater( new Runnable() {
                                                        public void run() {
                                                            partialBar.setMinimum( gaugeMin );
                                                            partialBar.setMaximum( gaugeMax );
                                                        }
                                                    } );
        }
        else if ( gauge == OVERALL_GAUGE ) {
            javax.swing.SwingUtilities.invokeLater( new Runnable() {
                                                        public void run() {
                                                            overallBar.setMinimum( gaugeMin );
                                                            overallBar.setMaximum( gaugeMax );
                                                        }
                                                    } );
        }
    }

    /** Indexed getter for property labelText.
     *@param index Index of the property.
     *@return Value of the property at <CODE>index</CODE>.
     */
    public String getLabelText(int label) {
        if ( label == PARTIAL_LABEL ) {
            return partialLabel.getText();
        }
        else if ( label == OVERALL_LABEL ) {
            return overallLabel.getText();
        }
        else if ( label == EXTRA_LABEL ) {
            return doneLabel.getText();
        }

        else {
            return null;
        }
    }

    /** Indexed setter for property labelText.
     *@param index Index of the property.
     *@param labelText New value of the property at <CODE>index</CODE>.
     */
    public void setLabelText(int label, final String labelText) {
        if ( label == PARTIAL_LABEL ) {
            javax.swing.SwingUtilities.invokeLater( new Runnable() {
                                                        public void run() {
                                                            partialLabel.setText( labelText );
                                                        }
                                                    } );
        }
        else if ( label == OVERALL_LABEL ) {
            javax.swing.SwingUtilities.invokeLater( new Runnable() {
                                                        public void run() {
                                                            overallLabel.setText( labelText );
                                                        }
                                                    } );
        }
        else if ( label == EXTRA_LABEL ) {
            javax.swing.SwingUtilities.invokeLater( new Runnable() {
                                                        public void run() {
                                                            doneLabel.setText( labelText );
                                                        }
                                                    } );
        }
    }

    /** Getter for property title.
     *@return Value of property title.
     */
    public String getTitle() {
        return title;
    }

    /** Setter for property title.
     *@param title New value of property title.
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /*
    void setDone( boolean done ) {
      
      doneLabel.setText( done ?
         org.openide.util.NbBundle.getBundle(DownloadProgressPanel.class).getString("DownloadProgressPanel.jLabel1.doneText"):
         org.openide.util.NbBundle.getBundle(DownloadProgressPanel.class).getString("DownloadProgressPanel.jLabel1.text") ) ;
     
}
    */
}
/*
 * Log
 *  7    Gandalf-post-FCS1.5.1.0     4/13/00  Jaroslav Tulach L10N fix. Does not use 
 *       the font "Default".
 *  6    Gandalf   1.5         1/12/00  Petr Hrebejk    i18n
 *  5    Gandalf   1.4         12/1/99  Petr Hrebejk    Checkin signatures of 
 *       NBM files & automatic autoupdate check added
 *  4    Gandalf   1.3         11/27/99 Patrik Knakal   
 *  3    Gandalf   1.2         10/22/99 Ian Formanek    NO SEMANTIC CHANGE - Sun
 *       Microsystems Copyright in File Comment
 *  2    Gandalf   1.1         10/11/99 Petr Hrebejk    Version before Beta 5
 *  1    Gandalf   1.0         10/10/99 Petr Hrebejk    
 * $
 */
