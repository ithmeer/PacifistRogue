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

/**
 * A class for static helper methods.
 *
 * Created by Ithmeer on 7/16/2017.
 */
public class Helpers
{
    /**
     * Safely cast the given object to the given class.
     *
     * @param o the object
     * @param desiredType the Class to cast to
     * @param <T> the type to cast to
     * @return the object as the correct type, or null if invalid
     */
    @SuppressWarnings("unchecked")
    public static <T> T safeCast(Object o, Class<T> desiredType)
    {
        return desiredType.isInstance(o) ? (T) o : null;
    }

}
