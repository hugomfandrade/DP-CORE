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

package org.netbeans.modules.javadoc;

import sun.tools.java.ClassDefinition;

/** This class serves for Turnning On/Off InheritanceCheck in SourceClass
 *
 * @author Petr Hrebejk
 */

class InheritanceChecksSwitch extends  ClassDefinition {

    /** Should be never called just make compiler happy
    */
    private InheritanceChecksSwitch() {
        super (null, 0, null, 0, null, null );
    };

    /** Turns On Inheritance checks
    */
    public static void turnOnInheritanceChecks() {
        doInheritanceChecks = true;
    }

}

/*
 * Log
 *  2    Gandalf   1.1         10/23/99 Ian Formanek    NO SEMANTIC CHANGE - Sun
 *       Microsystems Copyright in File Comment
 *  1    Gandalf   1.0         4/23/99  Petr Hrebejk    
 * $ 
 */ 