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
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.Instant;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.LocalDate;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.LocalDateTime;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.LocalTime;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.ZoneId;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.ZoneOffset;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.format.DateTimeFormatter;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.jdk8.DefaultInterfaceTemporal;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.jdk8.Jdk8Methods;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoField;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.Temporal;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalAccessor;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalAdjuster;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalAmount;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalField;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalQueries;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalQuery;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalUnit;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.zone.ZoneRules;

import java.util.Comparator;

import static walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoField.EPOCH_DAY;
import static walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoField.NANO_OF_DAY;
import static walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoUnit.NANOS;

/**
 * A date-time without a time-zone in an arbitrary chronology, intended
 * for advanced globalization use cases.
 * <p>
 * <b>Most applications should declare method signatures, fields and variables
 * as {@link LocalDateTime}, not this interface.</b>
 * <p>
 * A {@code ChronoLocalDateTime} is the abstract representation of a local date-time
 * where the {@code Chronology chronology}, or calendar system, is pluggable.
 * The date-time is defined in terms of fields expressed by {@link TemporalField},
 * where most common implementations are defined in {@link ChronoField}.
 * The chronology defines how the calendar system operates and the meaning of
 * the standard fields.
 *
 * <h4>When to use this interface</h4>
 * The design of the API encourages the use of {@code LocalDateTime} rather than this
 * interface, even in the case where the application needs to deal with multiple
 * calendar systems. The rationale for this is explored in detail in {@link ChronoLocalDate}.
 * <p>
 * Ensure that the discussion in {@code ChronoLocalDate} has been read and understood
 * before using this interface.
 *
 * <h3>Specification for implementors</h3>
 * This interface must be implemented with care to ensure other classes operate correctly.
 * All implementations that can be instantiated must be final, immutable and thread-safe.
 * Subclasses should be Serializable wherever possible.
 * <p>
 * In JDK 8, this is an interface with default methods.
 * Since there are no default methods in JDK 7, an abstract class is used.
 *
 * @param <D> the date type
 */
public abstract class ChronoLocalDateTime<D extends ChronoLocalDate>
        extends DefaultInterfaceTemporal
        implements Temporal, TemporalAdjuster, Comparable<ChronoLocalDateTime<?>> {

    /**
     * Gets a comparator that compares {@code ChronoLocalDateTime} in
     * time-line order ignoring the chronology.
     * <p>
     * This comparator differs from the comparison in {@link #compareTo} in that it
     * only compares the underlying date-time and not the chronology.
     * This allows dates in different calendar systems to be compared based
     * on the position of the date-time on the local time-line.
     * The underlying comparison is equivalent to comparing the epoch-day and nano-of-day.
     *
     * @return a comparator that compares in time-line order ignoring the chronology
     * @see #isAfter
     * @see #isBefore
     * @see #isEqual
     */
    public static Comparator<ChronoLocalDateTime<?>> timeLineOrder() {
        return DATE_TIME_COMPARATOR;
    }
    private static final Comparator<ChronoLocalDateTime<?>> DATE_TIME_COMPARATOR =
            new Comparator<ChronoLocalDateTime<?>>() {
        @Override
        public int compare(ChronoLocalDateTime<?> datetime1, ChronoLocalDateTime<?> datetime2) {
            int cmp = Jdk8Methods.compareLongs(datetime1.toLocalDate().toEpochDay(), datetime2.toLocalDate().toEpochDay());
            if (cmp == 0) {
                cmp = Jdk8Methods.compareLongs(datetime1.toLocalTime().toNanoOfDay(), datetime2.toLocalTime().toNanoOfDay());
            }
            return cmp;
        }
    };

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of {@code ChronoLocalDateTime} from a temporal object.
     * <p>
     * This obtains a local date-time based on the specified temporal.
     * A {@code TemporalAccessor} represents an arbitrary set of date and time information,
     * which this factory converts to an instance of {@code ChronoLocalDateTime}.
     * <p>
     * The conversion extracts and combines the chronology and the date-time
     * from the temporal object. The behavior is equivalent to using
     * {@link Chronology#localDateTime(TemporalAccessor)} with the extracted chronology.
     * Implementations are permitted to perform optimizations such as accessing
     * those fields that are equivalent to the relevant objects.
     * <p>
     * This method matches the signature of the functional interface {@link TemporalQuery}
     * allowing it to be used as a query via method reference, {@code ChronoLocalDateTime::from}.
     *
     * @param temporal  the temporal object to convert, not null
     * @return the date-time, not null
     * @throws DateTimeException if unable to convert to a {@code ChronoLocalDateTime}
     * @see Chronology#localDateTime(TemporalAccessor)
     */
    public static ChronoLocalDateTime<?> from(TemporalAccessor temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        if (temporal instanceof ChronoLocalDateTime) {
            return (ChronoLocalDateTime<?>) temporal;
        }
        Chronology chrono = temporal.query(TemporalQueries.chronology());
        if (chrono == null) {
            throw new DateTimeException("No Chronology found to create ChronoLocalDateTime: " + temporal.getClass());
        }
        return chrono.localDateTime(temporal);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology of this date-time.
     * <p>
     * The {@code Chronology} represents the calendar system in use.
     * The era and other fields in {@link ChronoField} are defined by the chronology.
     *
     * @return the chronology, not null
     */
    public Chronology getChronology() {
        return toLocalDate().getChronology();
    }

    /**
     * Gets the local date part of this date-time.
     * <p>
     * This returns a local date with the same year, month and day
     * as this date-time.
     *
     * @return the date part of this date-time, not null
     */
    public abstract D toLocalDate() ;

    /**
     * Gets the local time part of this date-time.
     * <p>
     * This returns a local time with the same hour, minute, second and
     * nanosecond as this date-time.
     *
     * @return the time part of this date-time, not null
     */
    public abstract LocalTime toLocalTime();

    //-------------------------------------------------------------------------
    // override for covariant return type
    @Override
    public ChronoLocalDateTime<D> with(TemporalAdjuster adjuster) {
        return toLocalDate().getChronology().ensureChronoLocalDateTime(super.with(adjuster));
    }

    @Override
    public abstract ChronoLocalDateTime<D> with(TemporalField field, long newValue);

    @Override
    public ChronoLocalDateTime<D> plus(TemporalAmount amount) {
        return toLocalDate().getChronology().ensureChronoLocalDateTime(super.plus(amount));
    }

    @Override
    public abstract ChronoLocalDateTime<D> plus(long amountToAdd, TemporalUnit unit);

    @Override
    public ChronoLocalDateTime<D> minus(TemporalAmount amount) {
        return toLocalDate().getChronology().ensureChronoLocalDateTime(super.minus(amount));
    }

    @Override
    public ChronoLocalDateTime<D> minus(long amountToSubtract, TemporalUnit unit) {
        return toLocalDate().getChronology().ensureChronoLocalDateTime(super.minus(amountToSubtract, unit));
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.chronology()) {
            return (R) getChronology();
        } else if (query == TemporalQueries.precision()) {
            return (R) NANOS;
        } else if (query == TemporalQueries.localDate()) {
            return (R) LocalDate.ofEpochDay(toLocalDate().toEpochDay());
        } else if (query == TemporalQueries.localTime()) {
            return (R) toLocalTime();
        } else if (query == TemporalQueries.zone() || query == TemporalQueries.zoneId() || query == TemporalQueries.offset()) {
            return null;
        }
        return super.query(query);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal
                .with(EPOCH_DAY, toLocalDate().toEpochDay())
                .with(NANO_OF_DAY, toLocalTime().toNanoOfDay());
    }

    /**
     * Formats this date-time using the specified formatter.
     * <p>
     * This date-time will be passed to the formatter to produce a string.
     * <p>
     * The default implementation must behave as follows:
     * <pre>
     *  return formatter.format(this);
     * </pre>
     *
     * @param formatter  the formatter to use, not null
     * @return the formatted date-time string, not null
     * @throws DateTimeException if an error occurs during printing
     */
    public String format(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Combines this time with a time-zone to create a {@code ChronoZonedDateTime}.
     * <p>
     * This returns a {@code ChronoZonedDateTime} formed from this date-time at the
     * specified time-zone. The result will match this date-time as closely as possible.
     * Time-zone rules, such as daylight savings, mean that not every local date-time
     * is valid for the specified zone, thus the local date-time may be adjusted.
     * <p>
     * The local date-time is resolved to a single instant on the time-line.
     * This is achieved by finding a valid offset from UTC/Greenwich for the local
     * date-time as defined by the {@link ZoneRules rules} of the zone ID.
     *<p>
     * In most cases, there is only one valid offset for a local date-time.
     * In the case of an overlap, where clocks are set back, there are two valid offsets.
     * This method uses the earlier offset typically corresponding to "summer".
     * <p>
     * In the case of a gap, where clocks jump forward, there is no valid offset.
     * Instead, the local date-time is adjusted to be later by the length of the gap.
     * For a typical one hour daylight savings change, the local date-time will be
     * moved one hour later into the offset typically corresponding to "summer".
     * <p>
     * To obtain the later offset during an overlap, call
     * {@link ChronoZonedDateTime#withLaterOffsetAtOverlap()} on the result of this method.
     *
     * @param zone  the time-zone to use, not null
     * @return the zoned date-time formed from this date-time, not null
     */
    public abstract ChronoZonedDateTime<D> atZone(ZoneId zone);

    //-----------------------------------------------------------------------
    /**
     * Converts this date-time to an {@code Instant}.
     * <p>
     * This combines this local date-time and the specified offset to form
     * an {@code Instant}.
     *
     * @param offset  the offset to use for the conversion, not null
     * @return an {@code Instant} representing the same instant, not null
     */
    public Instant toInstant(ZoneOffset offset) {
        return Instant.ofEpochSecond(toEpochSecond(offset), toLocalTime().getNano());
    }

    /**
     * Converts this date-time to the number of seconds from the epoch
     * of 1970-01-01T00:00:00Z.
     * <p>
     * This combines this local date-time and the specified offset to calculate the
     * epoch-second value, which is the number of elapsed seconds from 1970-01-01T00:00:00Z.
     * Instants on the time-line after the epoch are positive, earlier are negative.
     *
     * @param offset  the offset to use for the conversion, not null
     * @return the number of seconds from the epoch of 1970-01-01T00:00:00Z
     */
    public long toEpochSecond(ZoneOffset offset) {
        Jdk8Methods.requireNonNull(offset, "offset");
        long epochDay = toLocalDate().toEpochDay();
        long secs = epochDay * 86400 + toLocalTime().toSecondOfDay();
        secs -= offset.getTotalSeconds();
        return secs;
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this date-time to another date-time, including the chronology.
     * <p>
     * The comparison is based first on the underlying time-line date-time, then
     * on the chronology.
     * It is "consistent with equals", as defined by {@link Comparable}.
     * <p>
     * For example, the following is the comparator order:
     * <ol>
     * <li>{@code 2012-12-03T12:00 (ISO)}</li>
     * <li>{@code 2012-12-04T12:00 (ISO)}</li>
     * <li>{@code 2555-12-04T12:00 (ThaiBuddhist)}</li>
     * <li>{@code 2012-12-05T12:00 (ISO)}</li>
     * </ol>
     * Values #2 and #3 represent the same date-time on the time-line.
     * When two values represent the same date-time, the chronology ID is compared to distinguish them.
     * This step is needed to make the ordering "consistent with equals".
     * <p>
     * If all the date-time objects being compared are in the same chronology, then the
     * additional chronology stage is not required and only the local date-time is used.
     *
     * @param other  the other date-time to compare to, not null
     * @return the comparator value, negative if less, positive if greater
     */
    @Override
    public int compareTo(ChronoLocalDateTime<?> other) {
        int cmp = toLocalDate().compareTo(other.toLocalDate());
        if (cmp == 0) {
            cmp = toLocalTime().compareTo(other.toLocalTime());
            if (cmp == 0) {
                cmp = getChronology().compareTo(other.getChronology());
            }
        }
        return cmp;
    }

    /**
     * Checks if this date-time is after the specified date-time ignoring the chronology.
     * <p>
     * This method differs from the comparison in {@link #compareTo} in that it
     * only compares the underlying date-time and not the chronology.
     * This allows dates in different calendar systems to be compared based
     * on the time-line position.
     *
     * @param other  the other date-time to compare to, not null
     * @return true if this is after the specified date-time
     */
    public boolean isAfter(ChronoLocalDateTime<?> other) {
        long thisEpDay = this.toLocalDate().toEpochDay();
        long otherEpDay = other.toLocalDate().toEpochDay();
        return thisEpDay > otherEpDay ||
            (thisEpDay == otherEpDay && this.toLocalTime().toNanoOfDay() > other.toLocalTime().toNanoOfDay());
    }

    /**
     * Checks if this date-time is before the specified date-time ignoring the chronology.
     * <p>
     * This method differs from the comparison in {@link #compareTo} in that it
     * only compares the underlying date-time and not the chronology.
     * This allows dates in different calendar systems to be compared based
     * on the time-line position.
     *
     * @param other  the other date-time to compare to, not null
     * @return true if this is before the specified date-time
     */
    public boolean isBefore(ChronoLocalDateTime<?> other) {
        long thisEpDay = this.toLocalDate().toEpochDay();
        long otherEpDay = other.toLocalDate().toEpochDay();
        return thisEpDay < otherEpDay ||
            (thisEpDay == otherEpDay && this.toLocalTime().toNanoOfDay() < other.toLocalTime().toNanoOfDay());
    }

    /**
     * Checks if this date-time is equal to the specified date-time ignoring the chronology.
     * <p>
     * This method differs from the comparison in {@link #compareTo} in that it
     * only compares the underlying date and time and not the chronology.
     * This allows date-times in different calendar systems to be compared based
     * on the time-line position.
     *
     * @param other  the other date-time to compare to, not null
     * @return true if the underlying date-time is equal to the specified date-time on the timeline
     */
    public boolean isEqual(ChronoLocalDateTime<?> other) {
        // Do the time check first, it is cheaper than computing EPOCH day.
        return this.toLocalTime().toNanoOfDay() == other.toLocalTime().toNanoOfDay() &&
               this.toLocalDate().toEpochDay() == other.toLocalDate().toEpochDay();
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this date-time is equal to another date-time, including the chronology.
     * <p>
     * Compares this date-time with another ensuring that the date-time and chronology are the same.
     *
     * @param obj  the object to check, null returns false
     * @return true if this is equal to the other date
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChronoLocalDateTime) {
            return compareTo((ChronoLocalDateTime<?>) obj) == 0;
        }
        return false;
    }

    /**
     * A hash code for this date-time.
     *
     * @return a suitable hash code
     */
    @Override
    public int hashCode() {
        return toLocalDate().hashCode() ^ toLocalTime().hashCode();
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs this date-time as a {@code String}.
     * <p>
     * The output will include the full local date-time and the chronology ID.
     *
     * @return a string representation of this date-time, not null
     */
    @Override
    public String toString() {
        return toLocalDate().toString() + 'T' + toLocalTime().toString();
    }

}
