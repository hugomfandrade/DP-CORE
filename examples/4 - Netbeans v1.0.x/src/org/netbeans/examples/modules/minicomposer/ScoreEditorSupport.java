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

package org.netbeans.examples.modules.minicomposer;
import org.openide.cookies.EditCookie;
import org.openide.loaders.MultiDataObject;
import org.openide.text.EditorSupport;
public class ScoreEditorSupport extends EditorSupport implements EditCookie {
    public ScoreEditorSupport (MultiDataObject.Entry entry) {
        super (entry);
        setMIMEType ("text/plain");
    }
    protected boolean canClose () {
        ScoreOpenSupport sos = (ScoreOpenSupport) entry.getDataObject ().getCookie (ScoreOpenSupport.class);
        if (sos != null && sos.isOpen ()) {
            return true;
        } else {
            return superCanClose ();
        }
    }
    boolean superCanClose () {
        return super.canClose ();
    }
}
