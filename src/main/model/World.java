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
import main.items.BaseItem;
import main.utilities.Helpers;
import main.utilities.Loc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ithmeer on 4/19/2016.
 */
public class World
{
    private final ScreenRenderer _renderer;
    private final ItemDatabase _itemDatabase;
    private final AsciiPresenter _presenter;
    private final List<Entity> _entities;
    private final List<BaseItem> _droppedItems;
    private final List<Loc> _dirty;
    private Map _currentMap;

    public World(ScreenRenderer renderer)
    {
        _renderer = renderer;
        _itemDatabase = ItemDatabase.getInstance();
        _currentMap = FieldMapGenerator.generateField(80, 25);
        _presenter = new AsciiPresenter(_renderer);
        _dirty = new ArrayList<>();
        _droppedItems = new ArrayList<>();

        BaseItem hoe = _itemDatabase.getItem("hoe");
        hoe.setLoc(new Loc(7, 7));
        _droppedItems.add(hoe);

        synchronized (this)
        {
            _entities = new ArrayList<>();
        }

        _presenter.draw(_currentMap);
        _renderer.refresh();
    }

    public void draw()
    {
        _dirty.forEach(loc -> _presenter.draw(_currentMap.getSquare(loc)));
        _droppedItems.forEach(_presenter::draw);

        synchronized (this)
        {
            _entities.forEach(_presenter::draw);
        }

        _renderer.refresh();
    }

    public void tick()
    {
        synchronized (this)
        {
            // give each actor a turn
            _entities.stream()
                     .map(e -> Helpers.safeCast(e, Actor.class))
                     .filter(Objects::nonNull)
                     .forEach(Actor::takeTurn);
        }
    }

    public void addDirty(Loc p)
    {
        _dirty.add(p);
    }

    public synchronized void addEntity(Entity ent)
    {
        _entities.add(ent);
    }

    public boolean collides(Loc loc)
    {
        if (loc.x < 0 || loc.x >= _currentMap.getWidth() ||
                loc.y < 0 || loc.y >= _currentMap.getHeight())
            return true;

        if (_currentMap.getSquare(loc).isSolid())
            return true;

        synchronized (this)
        {
            for (Entity ent : _entities)
            {
                if (ent.getLoc().equals(loc))
                    return true;
            }
        }

        return false;
    }

    public BaseItem getSquare(Loc loc)
    {
        return _currentMap.getSquare(loc);
    }

    public BaseItem getDroppedItem(Loc loc)
    {
        return _droppedItems.stream()
                            .filter(i -> i.getLoc().equals(loc)).findFirst().orElse(null);
    }

    public void removeDroppedItem(BaseItem item)
    {
        _droppedItems.remove(item);
    }
}
