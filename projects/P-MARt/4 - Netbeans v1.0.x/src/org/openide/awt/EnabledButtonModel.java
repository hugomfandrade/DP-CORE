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

package org.openide.awt;

import java.awt.event.*;
import java.io.Serializable;
import java.util.EventListener;
import javax.swing.DefaultButtonModel;

/**
 * This class implements a special button model that allows updates of the
 * model's state even if the model is not enabled.  However, all the state
 * change events are fired only if the model is enabled.
 *
 * This behaviour is needed in order to maintain proper consistency of model's
 * state because models can be disabled while in non-default state.
 *
 * @version 1.1, August 31, 1998
 * @author David Peroutka
 */
class EnabledButtonModel extends DefaultButtonModel implements Serializable {
    static final long serialVersionUID =-2064291683066300065L;
    /**
     * Creates a new model.
     */
    public EnabledButtonModel() {
    }

    /**
     * Identifies the button as "armed".
     */
    public void setArmed(boolean b) {
        if (isArmed() != b) {
            if (b)
                stateMask |= ARMED;
            else
                stateMask &= ~ARMED;
            if (isEnabled())
                fireStateChanged();
        }
    }

    /**
     * Sets the selected state of the button.
     */
    public void setSelected(boolean b) {
        if (isSelected() != b) {
            if (b)
                stateMask |= SELECTED;
            else
                stateMask &= ~SELECTED;
            if (isEnabled()) {
                fireItemStateChanged(new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED,
                                                   this, b ? ItemEvent.SELECTED : ItemEvent.DESELECTED));
                fireStateChanged();
            }
        }
    }


    /**
     * Sets the button to pressed state.
     */
    public void setPressed(boolean b) {
        if (isPressed() != b) {
            if (b)
                stateMask |= PRESSED;
            else
                stateMask &= ~PRESSED;
            if (isEnabled()) {
                if (!isPressed() && isArmed())
                    fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, getActionCommand()));
                fireStateChanged();
            }
        }
    }

    /**
     * Sets the button to the rollover state.
     */
    public void setRollover(boolean b) {
        if (isRollover() != b) {
            if (b)
                stateMask |= ROLLOVER;
            else
                stateMask &= ~ROLLOVER;
            if (isEnabled())
                fireStateChanged();
        }
    }
}

/*
 * Log
 *  6    Gandalf   1.5         10/22/99 Ian Formanek    NO SEMANTIC CHANGE - Sun
 *       Microsystems Copyright in File Comment
 *  5    Gandalf   1.4         8/9/99   Ian Formanek    Generated Serial Version
 *       UID
 *  4    Gandalf   1.3         6/8/99   Ian Formanek    ---- Package Change To 
 *       org.openide ----
 *  3    Gandalf   1.2         3/9/99   Jaroslav Tulach 
 *  2    Gandalf   1.1         1/6/99   Ian Formanek    Reflecting changes in 
 *       location of package "awt"
 *  1    Gandalf   1.0         1/5/99   Ian Formanek    
 * $
 */
