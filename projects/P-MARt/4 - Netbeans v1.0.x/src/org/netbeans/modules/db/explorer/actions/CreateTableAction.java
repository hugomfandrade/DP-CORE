/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2001 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.db.explorer.actions;

import java.sql.Connection;
import org.openide.*;
import org.openide.nodes.*;
import org.netbeans.lib.ddl.impl.Specification;
import org.netbeans.modules.db.explorer.nodes.*;
import org.netbeans.modules.db.explorer.infos.DatabaseNodeInfo;
import org.netbeans.modules.db.explorer.infos.TableOwnerOperations;
import org.netbeans.modules.db.explorer.dlg.CreateTableDialog;

public class CreateTableAction extends DatabaseAction
{
    static final long serialVersionUID =-7008851466327604724L;
    public void performAction (Node[] activatedNodes)
    {
        Node node;
        if (activatedNodes != null && activatedNodes.length>0) node = activatedNodes[0];
        else return;

        try {
            DatabaseNodeInfo xnfo = (DatabaseNodeInfo)node.getCookie(DatabaseNodeInfo.class);
            TableOwnerOperations nfo = (TableOwnerOperations)xnfo.getParent(nodename);
            Specification spec = (Specification)xnfo.getSpecification();
            CreateTableDialog dlg = new CreateTableDialog(spec, (DatabaseNodeInfo)nfo);
            if (dlg.run()) nfo.addTable(dlg.getTableName());
        } catch(Exception e) {
            TopManager.getDefault().notify(new NotifyDescriptor.Message("Unable to create table "+node.getName()+", "+e.getMessage(), NotifyDescriptor.ERROR_MESSAGE));
        }
    }
}
/*
 * <<Log>>
 *  8    Gandalf   1.7         11/27/99 Patrik Knakal   
 *  7    Gandalf   1.6         10/23/99 Ian Formanek    NO SEMANTIC CHANGE - Sun
 *       Microsystems Copyright in File Comment
 *  6    Gandalf   1.5         6/9/99   Ian Formanek    ---- Package Change To 
 *       org.openide ----
 *  5    Gandalf   1.4         5/21/99  Slavek Psenicka new version
 *  4    Gandalf   1.3         5/14/99  Slavek Psenicka new version
 *  3    Gandalf   1.2         4/27/99  Slavek Psenicka create table action
 *  2    Gandalf   1.1         4/23/99  Slavek Psenicka oprava activatedNode[0] 
 *       check
 *  1    Gandalf   1.0         4/23/99  Slavek Psenicka 
 * $
 */
