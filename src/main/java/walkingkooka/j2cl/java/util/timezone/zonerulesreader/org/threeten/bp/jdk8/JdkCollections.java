/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.jdk8;

import walkingkooka.collect.map.Maps;

import java.util.Map;

/**
 * Provides factory methods for concurrency {@link Map maps}, which will be replaced with a regular {@link Map} when transpiled.
 */
public final class JdkCollections {

    public static <K, V> Map<K, V> concurrentHashMap() {
        //return new ConcurrentHashMap<>();
        return Maps.hash();
    }

    public static <K, V> Map<K, V> concurrentHashMap(final int initialCapacity,
                                                     final float loadRatio,
                                                     final int concurrencyLevel) {
        //return new ConcurrentHashMap<>(initialCapacity, loadRatio, concurrencyLevel);
        return concurrentHashMap();
    }

    private JdkCollections() {
        throw new UnsupportedOperationException();
    }
}
