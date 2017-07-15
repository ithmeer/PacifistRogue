/*
 *     PacifistRogue, a roguelike game without combat.
 *     Copyright (C) 2017 Ithmeer
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package model;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Ithmeer on 4/22/2016.
 */
@XmlRootElement(name="item")
public class Item extends Square
{
    public Item()
    {
        // empty constructor
    }

    @XmlAttribute(name="name")
    private String _name;

    @XmlElement(name="desc")
    private String _description;

    @XmlElement(name="solid")
    private boolean _isSolid;

    @XmlElement(name="portable")
    private boolean _isPortable;

    public String getName()
    {
        return _name;
    }

    public String getDescription()
    {
        return _description;
    }

    public boolean isPortable()
    {
        return _isPortable;
    }

    public boolean isSolid()
    {
        return _isSolid;
    }

    public void transform(Item item)
    {
        copy(this, item);
    }

    @Override
    public Item clone()
    {
        Item clone = new Item();
        copy(clone, this);
        return clone;
    }

    protected static void copy(Item first, Item second)
    {
        first._loc = second._loc;
        first._color = second._color;
        first._symbol = second._symbol;
        first._name = second._name;
        first._description = second._description;
        first._isSolid = second._isSolid;
        first._isPortable = second._isPortable;
    }
}
