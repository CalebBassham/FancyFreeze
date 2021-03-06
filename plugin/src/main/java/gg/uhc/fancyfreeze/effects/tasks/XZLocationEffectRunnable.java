/*
 * Project: FancyFreeze
 * Class: gg.uhc.fancyfreeze.effects.tasks.XZLocationEffectRunnable
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Graham Howden <graham_howden1 at yahoo.co.uk>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package gg.uhc.fancyfreeze.effects.tasks;

import gg.uhc.fancyfreeze.api.CustomEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.ref.WeakReference;

public class XZLocationEffectRunnable extends BukkitRunnable {

    protected final WeakReference<Entity> reference;
    protected final CustomEffect particleEffect;
    protected final Location location;

    public XZLocationEffectRunnable(CustomEffect particleEffect, Entity entity, Location location) {
        this.particleEffect = particleEffect;
        this.reference = new WeakReference<>(entity);
        this.location = location;
    }

    @Override
    public void run() {
        Entity entity = reference.get();

        Location at = location.clone();
        if (entity != null) {
            at.setY(entity.getLocation().getY());
        }

        particleEffect.playAtLocation(at);
    }
}
