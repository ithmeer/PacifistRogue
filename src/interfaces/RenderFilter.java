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

package interfaces;

import java.awt.*;

/**
 * A filter to modify rendered text.
 * Created by Ithmeer on 4/3/2016.
 */
public interface RenderFilter
{
    /**
     * Filter a color.
     * @param pre the Color to filter
     * @return the filtered color
     */
    Color filter(Color pre);

    /**
     * Filter a char.
     * @param pre the char to filter
     * @return the filtered char
     */
    char filter(char pre);
}
