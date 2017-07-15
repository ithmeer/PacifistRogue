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

package utilities;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by Ithmeer on 5/22/2016.
 */
public class CharAdapter extends XmlAdapter<String, Character>
{
    @Override
    public Character unmarshal(String s) throws Exception
    {
        return s.charAt(0);
    }

    @Override
    public String marshal(Character c) throws Exception
    {
        return c.toString();
    }
}
