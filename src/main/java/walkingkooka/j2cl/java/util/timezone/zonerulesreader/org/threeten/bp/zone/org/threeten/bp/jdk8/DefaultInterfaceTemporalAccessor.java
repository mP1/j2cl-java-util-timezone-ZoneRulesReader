/*
 * Copyright (c) 2007-present, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.jdk8;

import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoField;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalAccessor;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalField;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalQueries;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalQuery;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ValueRange;

/**
 * A temporary class providing implementations that will become default interface
 * methods once integrated into JDK 8.
 */
public abstract class DefaultInterfaceTemporalAccessor implements TemporalAccessor {

    @Override
    public ValueRange range(TemporalField field) {
        if (field instanceof ChronoField) {
            if (isSupported(field)) {
                return field.range();
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
        }
        return field.rangeRefinedBy(this);
    }

    @Override
    public int get(TemporalField field) {
        return range(field).checkValidIntValue(getLong(field), field);
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.zoneId() || query == TemporalQueries.chronology() || query == TemporalQueries.precision()) {
            return null;
        }
        return query.queryFrom(this);
    }

}
