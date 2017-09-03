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

package main.model;

import main.interfaces.ScreenRenderer;

/**
 * Created by Ithmeer on 5/22/2016.
 */
public class AsciiPresenter
{
    private final ScreenRenderer _renderer;

    public AsciiPresenter(ScreenRenderer renderer)
    {
        _renderer = renderer;
    }

    public void draw(Map map)
    {
        for (int x = 0; x < Math.min(map.getWidth(), _renderer.getMaxCols()); x++)
        {
            for (int y = 0; y < Math.min(map.getHeight(), _renderer.getMaxRows()); y++)
            {
                draw(map.getSquare(x, y));
            }
        }
    }

    public void draw(Square sq)
    {
        _renderer.renderPos(sq.getLoc(), sq.getSymbol(), sq.getColor());
    }
}
