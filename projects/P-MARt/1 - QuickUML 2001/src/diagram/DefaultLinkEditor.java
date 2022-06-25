/**
 *
    Java Diagram Package; An extremely flexible and fast multipurpose diagram 
    component for Swing.
    Copyright (C) 2001  Eric Crahen <crahen@cse.buffalo.edu>

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 */

package diagram;

import java.awt.Component;
import java.awt.geom.Rectangle2D;

/**
 * @class DefaultLinkEditor
 *
 * @date 08-20-2001
 * @author Eric Crahen
 * @version 1.0
 *
 * This editor can be used to edit both simple & poly link object. The values
 * mapped to those objects are treated as String values used for labels.
 */
public class DefaultLinkEditor extends DefaultFigureEditor {

  protected DefaultLabelRenderer renderer;
   
  /**
   * Create a new link editor
   */
  public DefaultLinkEditor() {
    this(new DefaultLabelRenderer());
  }

  /**
   * Create a new link editor
   */
  public DefaultLinkEditor(DefaultLabelRenderer renderer) {
    super(renderer);
    this.renderer = (DefaultLabelRenderer)getComponent();
  }

  /**
   * Get the component used to perform the editing, which in this case is just the 
   * modified JTextfield that is also used as a renderer
   */
  public Component getFigureEditorComponent(Diagram diagram, Figure figure, boolean isSelected) {
    return renderer.getRendererComponent(diagram, figure, false);
  }


  /**
   * This should return the bounds of the box bounding the label that should be edited
   */
  public Rectangle2D getDecoratedBounds(Diagram diagram, Figure figure, Rectangle2D rcBounds) {
    return renderer.getDecoratedBounds(diagram, figure, rcBounds);
  }


}
