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
package walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.chrono;

import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.DateTimeException;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.format.DateTimeFormatterBuilder;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.format.TextStyle;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoField;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoUnit;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.Temporal;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalField;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalQueries;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalQuery;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ValueRange;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Locale;

import static walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoField.ERA;

/**
 * An era in the Thai Buddhist calendar system.
 * <p>
 * The Thai Buddhist calendar system has two eras.
 * <p>
 * <b>Do not use ordinal() to obtain the numeric representation of a ThaiBuddhistEra
 * instance. Use getValue() instead.</b>
 *
 * <h3>Specification for implementors</h3>
 * This is an immutable and thread-safe enum.
 */
public enum ThaiBuddhistEra implements Era {

    /**
     * The singleton instance for the era before the current one, 'Before Buddhist Era',
     * which has the value 0.
     */
    BEFORE_BE,
    /**
     * The singleton instance for the current era, 'Buddhist Era', which has the value 1.
     */
    BE;

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of {@code ThaiBuddhistEra} from a value.
     * <p>
     * The current era (from ISO year -543 onwards) has the value 1
     * The previous era has the value 0.
     *
     * @param thaiBuddhistEra  the era to represent, from 0 to 1
     * @return the BuddhistEra singleton, never null
     * @throws IllegalCalendarFieldValueException if the era is invalid
     */
    public static ThaiBuddhistEra of(int thaiBuddhistEra) {
        switch (thaiBuddhistEra) {
            case 0:
                return BEFORE_BE;
            case 1:
                return BE;
            default:
                throw new DateTimeException("Era is not valid for ThaiBuddhistEra");
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the era numeric value.
     * <p>
     * The current era (from ISO year -543 onwards) has the value 1
     * The previous era has the value 0.
     *
     * @return the era value, from 0 (BEFORE_BE) to 1 (BE)
     */
    @Override
    public int getValue() {
        return ordinal();
    }

    //-----------------------------------------------------------------------
    @Override
    public boolean isSupported(TemporalField field) {
        if (field instanceof ChronoField) {
            return field == ERA;
        }
        return field != null && field.isSupportedBy(this);
    }

    @Override
    public ValueRange range(TemporalField field) {
        if (field == ERA) {
            return field.range();
        } else if (field instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
        }
        return field.rangeRefinedBy(this);
    }

    @Override
    public int get(TemporalField field) {
        if (field == ERA) {
            return getValue();
        }
        return range(field).checkValidIntValue(getLong(field), field);
    }

    @Override
    public long getLong(TemporalField field) {
        if (field == ERA) {
            return getValue();
        } else if (field instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
    }

    //-------------------------------------------------------------------------
    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ERA, getValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.precision()) {
            return (R) ChronoUnit.ERAS;
        }
        if (query == TemporalQueries.chronology() || query == TemporalQueries.zone() ||
                query == TemporalQueries.zoneId() || query == TemporalQueries.offset() ||
                query == TemporalQueries.localDate() || query == TemporalQueries.localTime()) {
            return null;
        }
        return query.queryFrom(this);
    }

    //-----------------------------------------------------------------------
    @Override
    public String getDisplayName(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder().appendText(ERA, style).toFormatter(locale).format(this);
    }

    //-----------------------------------------------------------------------
    private Object writeReplace() {
        return new Ser(Ser.THAIBUDDHIST_ERA_TYPE, this);
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeByte(this.getValue());
    }

    static ThaiBuddhistEra readExternal(DataInput in) throws IOException {
        byte eraValue = in.readByte();
        return ThaiBuddhistEra.of(eraValue);
    }

}
