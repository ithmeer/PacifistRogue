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

package controller;

import interfaces.ScreenRenderer;
import model.Player;
import model.World;
import utilities.Direction;
import utilities.Loc;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ithmeer on 4/19/2016.
 */
public class PlayerController implements KeyEventDispatcher
{
    private final World _world;
    private final Player _player;
    private final ExecutorService _executor;

    public PlayerController(ScreenRenderer renderer)
    {
        _executor = Executors.newCachedThreadPool();
        _world = new World(renderer);
        _player = new Player(_world, new Loc(5, 5));
        _world.addEntity(_player);
        _executor.submit(_world);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e)
    {
        if (e.getID() == KeyEvent.KEY_PRESSED)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_A:
                case KeyEvent.VK_D:
                case KeyEvent.VK_W:
                case KeyEvent.VK_S:
                    _player.move(_player.getLoc().next(Direction.keyToDirection(e.getKeyCode())));
                    break;
                case KeyEvent.VK_E:
                    _player.take();
                    break;
            }

            return true;
        }
        else if (e.getID() == MouseEvent.MOUSE_CLICKED)
        {
            return true;
        }

        return false;
    }
}
